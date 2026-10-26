package kot0328.minecraftBank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

/**
 * Cash-back points: spending money with the server (shops run by the server, lottery, rent, fees) earns points,
 * which can be exchanged for rewards configured under points.rewards.
 */
final class BankPoints implements Listener {
   /**
    * Methods whose payments count as one-way spending with the server. Player-to-player payments and anything
    * that can be sold straight back (the resource shop, stocks, auction bids) never earn points.
    */
   static final Set<String> EARNING_SOURCES = Set.of(
      "executeMerchantPurchase", "executeVipShopPurchase", "tryStartMerchantInstallment",
      "buyLotteryTickets", "payOrStartStorageRent", "checkStorageRent", "checkTreasureLocationPaid"
   );

   private record Menu() implements InventoryHolder {
      @Override
      public Inventory getInventory() {
         return null;
      }
   }

   private static final int[] REWARD_SLOTS = {10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25};

   private final MinecraftBank plugin;
   private final Map<UUID, Double> points = new HashMap<>();
   private final Map<UUID, Double> earnedToday = new HashMap<>();
   private LocalDate today = LocalDate.now();

   BankPoints(MinecraftBank plugin) {
      this.plugin = plugin;
   }

   private boolean enabled() {
      return this.plugin.getConfig().getBoolean("points.enabled", true);
   }

   private double rate() {
      return Math.max(0.0, this.plugin.getConfig().getDouble("points.rate", 0.01));
   }

   private double dailyCap() {
      return Math.max(0.0, this.plugin.getConfig().getDouble("points.daily-cap", 1000.0));
   }

   List<BankUtil.PointReward> rewards() {
      List<BankUtil.PointReward> list = new ArrayList<>();

      for (String raw : this.plugin.getConfig().getStringList("points.rewards")) {
         BankUtil.PointReward r = BankUtil.parsePointReward(raw);
         if (r != null) {
            list.add(r);
         }
      }

      return list;
   }

   double balance(UUID u) {
      return this.points.getOrDefault(u, 0.0);
   }

   static String fmt(double pts) {
      return String.format("%,d pt", (long)Math.floor(pts));
   }

   /** Called for every successful withdrawal; only spending from EARNING_SOURCES earns points. */
   void onSpend(UUID u, double amount, String sourceMethod) {
      if (!this.enabled() || amount <= 0.0 || !EARNING_SOURCES.contains(sourceMethod)) {
         return;
      }

      LocalDate now = LocalDate.now();
      if (!now.equals(this.today)) {
         this.today = now;
         this.earnedToday.clear();
      }

      double room = this.dailyCap() - this.earnedToday.getOrDefault(u, 0.0);
      double gain = Math.min(room, amount * this.rate());
      if (gain <= 0.0) {
         return;
      }

      this.points.merge(u, gain, Double::sum);
      this.earnedToday.merge(u, gain, Double::sum);
      Player online = Bukkit.getPlayer(u);
      if (online != null && gain >= 1.0) {
         online.sendActionBar(this.plugin.mm("<yellow>+" + fmt(gain) + "</yellow> <gray>ポイント獲得（合計 " + fmt(this.balance(u)) + "）</gray>"));
      }
   }

   void clear() {
      this.points.clear();
      this.earnedToday.clear();
   }

   void load(PluginDataStore db) {
      this.clear();

      for (String key : db.getKeys("points")) {
         try {
            this.points.put(UUID.fromString(key), db.getDouble("points", key, 0.0));
         } catch (IllegalArgumentException ignored) {
         }
      }

      String day = db.getString("points_meta", "day", "");
      if (day.equals(LocalDate.now().toString())) {
         for (String key : db.getKeys("points_today")) {
            try {
               this.earnedToday.put(UUID.fromString(key), db.getDouble("points_today", key, 0.0));
            } catch (IllegalArgumentException ignored) {
            }
         }
      }
   }

   void save(PluginDataStore db) {
      db.removeSection("points");
      db.removeSection("points_today");
      this.points.forEach((u, v) -> {
         if (v > 0.0) {
            db.setDouble("points", u.toString(), v);
         }
      });
      this.earnedToday.forEach((u, v) -> db.setDouble("points_today", u.toString(), v));
      db.setString("points_meta", "day", this.today.toString());
   }

   // ------------------------------------------------------------------ exchange menu

   void open(Player p) {
      if (!this.enabled()) {
         this.plugin.msgKey(p, "feature.disabled");
         this.plugin.errorSound(p);
         return;
      }

      Inventory gui = Bukkit.createInventory(new Menu(), 36, this.plugin.guiTitle("ポイント交換所", "gold"));
      double have = this.balance(p.getUniqueId());
      gui.setItem(4, this.plugin.createItem(Material.NETHER_STAR, "<gold><bold>" + fmt(have) + "</bold></gold>",
         "<gray>サーバーのお店・宝くじ・家賃などで</gray>",
         "<gray>使った金額の " + BankUtil.percentText(this.rate()) + " がポイントになります</gray>",
         "<dark_gray>1日に貯まるのは " + fmt(this.dailyCap()) + " まで</dark_gray>"));
      List<BankUtil.PointReward> rewards = this.rewards();

      for (int i = 0; i < rewards.size() && i < REWARD_SLOTS.length; i++) {
         BankUtil.PointReward r = rewards.get(i);
         boolean affordable = have >= r.cost();
         gui.setItem(REWARD_SLOTS[i], this.plugin.createItem(this.iconFor(r), (affordable ? "<yellow><bold>" : "<gray><bold>") + r.name() + "</bold>" + (affordable ? "</yellow>" : "</gray>"),
            "<gray>必要ポイント:</gray> " + (affordable ? "<gold>" : "<red>") + fmt(r.cost()) + (affordable ? "</gold>" : "</red>"),
            affordable ? "<yellow>クリックで交換</yellow>" : "<red>ポイントが足りません</red>"));
      }

      if (rewards.isEmpty()) {
         gui.setItem(22, this.plugin.createItem(Material.BARRIER, "<gray>交換できる景品がありません</gray>", "<dark_gray>管理者が config.yml の points.rewards で設定できます</dark_gray>"));
      }

      gui.setItem(31, this.plugin.createItem(Material.IRON_DOOR, "<gray>閉じる</gray>"));
      this.plugin.fillGlass(gui);
      p.openInventory(gui);
      this.plugin.playUiSound(p, "open");
   }

   private Material iconFor(BankUtil.PointReward r) {
      return switch (r.type()) {
         case "money" -> Material.GOLD_INGOT;
         case "lottery" -> Material.FIREWORK_STAR;
         case "item" -> {
            Material m = Material.matchMaterial(r.value().split(":")[0]);
            yield m != null && m.isItem() ? m : Material.CHEST;
         }
         default -> Material.COMMAND_BLOCK;
      };
   }

   @EventHandler
   public void onClick(InventoryClickEvent e) {
      if (!(e.getView().getTopInventory().getHolder() instanceof Menu) || !(e.getWhoClicked() instanceof Player p)) {
         return;
      }

      e.setCancelled(true);
      if (e.getClickedInventory() != e.getView().getTopInventory()) {
         return;
      }

      int slot = e.getRawSlot();
      if (slot == 31) {
         p.closeInventory();
         return;
      }

      List<BankUtil.PointReward> rewards = this.rewards();

      for (int i = 0; i < rewards.size() && i < REWARD_SLOTS.length; i++) {
         if (REWARD_SLOTS[i] == slot) {
            this.redeem(p, rewards.get(i));
            this.open(p);
            return;
         }
      }
   }

   @EventHandler
   public void onDrag(InventoryDragEvent e) {
      if (e.getView().getTopInventory().getHolder() instanceof Menu) {
         e.setCancelled(true);
      }
   }

   private void redeem(Player p, BankUtil.PointReward r) {
      UUID u = p.getUniqueId();
      if (this.balance(u) < r.cost()) {
         this.plugin.msgKey(p, "points.not-enough", "cost", fmt(r.cost()));
         this.plugin.errorSound(p);
         return;
      }

      boolean given = switch (r.type()) {
         case "money" -> {
            double amount = Double.parseDouble(r.value());
            yield this.plugin.payFromTreasury(p, amount);
         }
         case "lottery" -> {
            this.plugin.grantLotteryTickets(u, Integer.parseInt(r.value()));
            yield true;
         }
         case "item" -> {
            String[] parts = r.value().split(":");
            Material m = Material.matchMaterial(parts[0]);
            int count = parts.length > 1 ? Integer.parseInt(parts[1]) : 1;
            if (m == null || !m.isItem()) {
               yield false;
            }

            for (ItemStack left : p.getInventory().addItem(new ItemStack(m, count)).values()) {
               p.getWorld().dropItemNaturally(p.getLocation(), left);
            }

            yield true;
         }
         default -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), r.value().replace("{player}", p.getName()));
      };
      if (!given) {
         this.plugin.msgKey(p, "points.failed");
         this.plugin.errorSound(p);
         return;
      }

      this.points.merge(u, -r.cost(), Double::sum);
      this.plugin.addLog(u, "ポイント交換: " + r.name() + " (-" + fmt(r.cost()) + ")");
      this.plugin.msgKey(p, "points.redeemed", "reward", r.name(), "cost", fmt(r.cost()), "left", fmt(this.balance(u)));
      this.plugin.playUiSound(p, "success");
      this.plugin.fxSparkle(p, false);
   }
}
