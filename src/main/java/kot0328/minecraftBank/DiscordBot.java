package kot0328.minecraftBank;

import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.bukkit.Bukkit;

/**
 * Discord bot: players link their account with a one-time code, then check balances, send money and
 * receive DMs about income. All economy work is handed to the server thread; JDA threads only talk to Discord.
 */
final class DiscordBot extends ListenerAdapter {
   private record LinkCode(UUID player, long expiresAt) {
   }

   private record PendingPay(UUID from, String toName, double amount, long expiresAt) {
   }

   private final MinecraftBank plugin;
   private final SecureRandom random = new SecureRandom();
   private final Map<UUID, String> links = new ConcurrentHashMap<>();
   private final Map<String, UUID> byDiscord = new ConcurrentHashMap<>();
   private final Set<UUID> dmOff = ConcurrentHashMap.newKeySet();
   private final Map<String, LinkCode> codes = new ConcurrentHashMap<>();
   private final Map<String, PendingPay> pending = new ConcurrentHashMap<>();
   private volatile JDA jda;

   DiscordBot(MinecraftBank plugin) {
      this.plugin = plugin;
   }

   boolean running() {
      return this.jda != null;
   }

   boolean linked(UUID u) {
      return this.links.containsKey(u);
   }

   boolean dmEnabled(UUID u) {
      return !this.dmOff.contains(u);
   }

   // ------------------------------------------------------------------ lifecycle

   void start() {
      String token = this.plugin.getConfig().getString("discord-bot.token", "");
      if (!this.plugin.getConfig().getBoolean("discord-bot.enabled", false) || token == null || token.isBlank()) {
         return;
      }

      String guildId = this.plugin.getConfig().getString("discord-bot.guild-id", "");
      Thread starter = new Thread(() -> {
         try {
            JDA started = JDABuilder.createLight(token.trim()).addEventListeners(this).build().awaitReady();
            List<SlashCommandData> commands = List.of(
               Commands.slash("link", "Minecraftのアカウントと連携します").addOption(OptionType.STRING, "code", "ゲーム内の /meco discord link で表示されたコード", true),
               Commands.slash("unlink", "Minecraftアカウントとの連携を解除します"),
               Commands.slash("balance", "所持金・預金・ポイントを確認します"),
               Commands.slash("points", "ポイントの残高を確認します"),
               Commands.slash("pay", "他のプレイヤーにお金を送ります")
                  .addOption(OptionType.STRING, "player", "送り先のプレイヤー名", true)
                  .addOption(OptionType.NUMBER, "amount", "金額", true),
               Commands.slash("notify", "入金のお知らせDMをON/OFFします").addOption(OptionType.BOOLEAN, "enabled", "DMを受け取る", true)
            );
            Guild guild = guildId == null || guildId.isBlank() ? null : started.getGuildById(guildId.trim());
            if (guild != null) {
               guild.updateCommands().addCommands(commands).queue();
            } else {
               started.updateCommands().addCommands(commands).queue();
            }

            this.jda = started;
            this.plugin.getLogger().info("[Discord] ボットに接続しました: " + started.getSelfUser().getName());
         } catch (Exception ex) {
            this.plugin.getLogger().warning("[Discord] ボットを起動できませんでした（トークンを確認してください）: " + ex.getMessage());
         }
      }, "MinecraftBank-Discord");
      starter.setDaemon(true);
      starter.start();
   }

   void stop() {
      JDA current = this.jda;
      this.jda = null;
      if (current != null) {
         current.shutdownNow();
      }
   }

   // ------------------------------------------------------------------ storage

   void clear() {
      this.links.clear();
      this.byDiscord.clear();
      this.dmOff.clear();
   }

   void load(PluginDataStore db) {
      this.clear();

      for (String key : db.getKeys("discord_links")) {
         try {
            UUID u = UUID.fromString(key);
            String id = db.getString("discord_links", key, null);
            if (id != null) {
               this.links.put(u, id);
               this.byDiscord.put(id, u);
            }
         } catch (IllegalArgumentException ignored) {
         }
      }

      for (String key : db.getKeys("discord_dm_off")) {
         try {
            this.dmOff.add(UUID.fromString(key));
         } catch (IllegalArgumentException ignored) {
         }
      }
   }

   void save(PluginDataStore db) {
      db.removeSection("discord_links");
      db.removeSection("discord_dm_off");
      this.links.forEach((u, id) -> db.setString("discord_links", u.toString(), id));
      this.dmOff.forEach(u -> db.setString("discord_dm_off", u.toString(), "1"));
   }

   // ------------------------------------------------------------------ in-game side

   /** A fresh six digit code the player types into Discord's /link within ten minutes. */
   String newLinkCode(UUID player) {
      this.codes.values().removeIf(c -> c.player().equals(player) || c.expiresAt() < System.currentTimeMillis());
      String code;
      do {
         code = String.format("%06d", this.random.nextInt(1_000_000));
      } while (this.codes.containsKey(code));

      this.codes.put(code, new LinkCode(player, System.currentTimeMillis() + 600_000L));
      return code;
   }

   void unlink(UUID player) {
      String id = this.links.remove(player);
      if (id != null) {
         this.byDiscord.remove(id);
      }
   }

   boolean toggleDm(UUID player) {
      if (!this.dmOff.remove(player)) {
         this.dmOff.add(player);
         return false;
      }

      return true;
   }

   /** Sends a DM if the player linked an account and wants notifications; never blocks the caller. */
   void dm(UUID player, String text) {
      JDA current = this.jda;
      String id = this.links.get(player);
      if (current == null || id == null || this.dmOff.contains(player)) {
         return;
      }

      current.retrieveUserById(id)
         .flatMap(User::openPrivateChannel)
         .flatMap(channel -> channel.sendMessage(text))
         .queue(ok -> {}, err -> {});
   }

   // ------------------------------------------------------------------ Discord side

   @Override
   public void onSlashCommandInteraction(SlashCommandInteractionEvent e) {
      String discordId = e.getUser().getId();
      UUID player = this.byDiscord.get(discordId);
      switch (e.getName()) {
         case "link" -> {
            String code = e.getOption("code", "", OptionMapping::getAsString).trim();
            LinkCode lc = this.codes.remove(code);
            if (lc == null || lc.expiresAt() < System.currentTimeMillis()) {
               e.reply("コードが正しくないか、期限切れです。ゲーム内で `/meco discord link` を実行して新しいコードを出してください。").setEphemeral(true).queue();
               return;
            }

            this.unlink(lc.player());
            UUID previous = this.byDiscord.remove(discordId);
            if (previous != null) {
               this.links.remove(previous);
            }

            this.links.put(lc.player(), discordId);
            this.byDiscord.put(discordId, lc.player());
            String name = Bukkit.getOfflinePlayer(lc.player()).getName();
            e.reply("✅ **" + (name != null ? name : "?") + "** と連携しました。`/balance` で残高を確認できます。").setEphemeral(true).queue();
            Bukkit.getScheduler().runTask(this.plugin, () -> this.plugin.onDiscordLinked(lc.player(), e.getUser().getName()));
         }
         case "unlink" -> {
            if (player == null) {
               e.reply("連携していません。").setEphemeral(true).queue();
               return;
            }

            this.unlink(player);
            e.reply("連携を解除しました。").setEphemeral(true).queue();
         }
         case "notify" -> {
            if (player == null) {
               this.replyNotLinked(e);
               return;
            }

            boolean on = e.getOption("enabled", true, OptionMapping::getAsBoolean);
            if (on) {
               this.dmOff.remove(player);
            } else {
               this.dmOff.add(player);
            }

            e.reply(on ? "🔔 入金のお知らせをDMで送ります。" : "🔕 入金のお知らせDMを止めました。").setEphemeral(true).queue();
         }
         case "balance", "points" -> {
            if (player == null) {
               this.replyNotLinked(e);
               return;
            }

            e.deferReply(true).queue();
            boolean pointsOnly = e.getName().equals("points");
            Bukkit.getScheduler().runTask(this.plugin, () -> e.getHook().editOriginal(this.plugin.discordSummary(player, pointsOnly)).queue());
         }
         case "pay" -> {
            if (player == null) {
               this.replyNotLinked(e);
               return;
            }

            if (!this.plugin.getConfig().getBoolean("discord-bot.pay-enabled", true)) {
               e.reply("Discordからの送金は無効になっています。").setEphemeral(true).queue();
               return;
            }

            String to = e.getOption("player", "", OptionMapping::getAsString).trim();
            double amount = Math.floor(e.getOption("amount", 0.0, OptionMapping::getAsDouble) * 100.0) / 100.0;
            if (!Double.isFinite(amount) || amount <= 0.0 || to.isEmpty()) {
               e.reply("送り先と金額を正しく入力してください。").setEphemeral(true).queue();
               return;
            }

            String nonce = UUID.randomUUID().toString().substring(0, 12);
            this.pending.values().removeIf(p -> p.expiresAt() < System.currentTimeMillis());
            this.pending.put(nonce, new PendingPay(player, to, amount, System.currentTimeMillis() + 60_000L));
            e.reply("**" + to + "** さんに **" + BankUtil.plainNumber(amount) + "** を送ります。よろしいですか？（60秒以内に確定してください）")
               .setEphemeral(true)
               .addComponents(ActionRow.of(Button.success("mbpay:" + nonce, "送金する"), Button.secondary("mbcancel:" + nonce, "やめる")))
               .queue();
         }
         default -> {
         }
      }
   }

   private void replyNotLinked(SlashCommandInteractionEvent e) {
      e.reply("まだ連携していません。ゲーム内で `/meco discord link` を実行し、表示されたコードを `/link` に入力してください。").setEphemeral(true).queue();
   }

   @Override
   public void onButtonInteraction(ButtonInteractionEvent e) {
      String id = e.getComponentId();
      if (!id.startsWith("mbpay:") && !id.startsWith("mbcancel:")) {
         return;
      }

      PendingPay pay = this.pending.remove(id.substring(id.indexOf(':') + 1));
      UUID clicker = this.byDiscord.get(e.getUser().getId());
      if (id.startsWith("mbcancel:")) {
         e.editMessage("送金をやめました。").setComponents().queue();
         return;
      }

      if (pay == null || pay.expiresAt() < System.currentTimeMillis() || !pay.from().equals(clicker)) {
         e.editMessage("この送金は期限切れです。もう一度 `/pay` を実行してください。").setComponents().queue();
         return;
      }

      e.deferEdit().queue();
      Bukkit.getScheduler().runTask(this.plugin, () -> {
         String result = this.plugin.discordTransfer(pay.from(), pay.toName(), pay.amount());
         e.getHook().editOriginal(result).setComponents().queue();
      });
   }
}
