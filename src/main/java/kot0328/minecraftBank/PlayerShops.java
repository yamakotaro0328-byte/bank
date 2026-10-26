package kot0328.minecraftBank;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.Container;
import org.bukkit.block.DoubleChest;
import org.bukkit.block.Sign;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Chest shops run by players: a sign on a chest sells its contents to customers or buys items into it.
 * Money moves through the plugin's ledgered deposit/withdraw helpers, and sales are taxed like other trades.
 */
final class PlayerShops implements Listener {
   static final class Shop {
      String id;
      UUID owner;
      String world;
      int signX;
      int signY;
      int signZ;
      int chestX;
      int chestY;
      int chestZ;
      ItemStack item;
      boolean selling;
      double price;
      int amount;
      long createdAt;
      long trades;
      double turnover;

      String signKey() {
         return key(this.world, this.signX, this.signY, this.signZ);
      }

      String chestKey() {
         return key(this.world, this.chestX, this.chestY, this.chestZ);
      }
   }

   /** Marks our menus so the click handler never confuses them with other inventories. */
   private record Menu(String shopId, boolean ownerView, int page) implements InventoryHolder {
      @Override
      public Inventory getInventory() {
         return null;
      }
   }

   private static final int[] TRADE_TIMES = {1, 5, 10};
   private static final int[] TRADE_SLOTS = {11, 13, 15};

   private final MinecraftBank plugin;
   private final Map<String, Shop> shops = new LinkedHashMap<>();
   private final Map<String, String> bySign = new HashMap<>();
   private final Map<String, String> byChest = new HashMap<>();
   private volatile String jsonCache = "{\"shops\":[]}";

   PlayerShops(MinecraftBank plugin) {
      this.plugin = plugin;
   }

   static String key(String world, int x, int y, int z) {
      return world + "," + x + "," + y + "," + z;
   }

   private static String key(Block b) {
      return key(b.getWorld().getName(), b.getX(), b.getY(), b.getZ());
   }

   private static boolean isShopContainer(Material m) {
      return m == Material.CHEST || m == Material.TRAPPED_CHEST || m == Material.BARREL;
   }

   private int maxPerPlayer() {
      return Math.max(1, this.plugin.getConfig().getInt("playershop.max-per-player", 20));
   }

   String json() {
      return this.jsonCache;
   }

   // ------------------------------------------------------------------ storage

   void clear() {
      this.shops.clear();
      this.bySign.clear();
      this.byChest.clear();
   }

   void load(PluginDataStore db) {
      this.clear();

      for (String id : db.getKeys("player_shops")) {
         try {
            Shop s = new Shop();
            s.id = id;
            String base = id + ".";
            s.owner = UUID.fromString(db.getString("player_shops", base + "owner", ""));
            s.world = db.getString("player_shops", base + "world", "");
            String[] sign = db.getString("player_shops", base + "sign", "").split(",");
            String[] chest = db.getString("player_shops", base + "chest", "").split(",");
            s.signX = Integer.parseInt(sign[0]);
            s.signY = Integer.parseInt(sign[1]);
            s.signZ = Integer.parseInt(sign[2]);
            s.chestX = Integer.parseInt(chest[0]);
            s.chestY = Integer.parseInt(chest[1]);
            s.chestZ = Integer.parseInt(chest[2]);
            s.selling = db.getBoolean("player_shops", base + "selling", true);
            s.price = db.getDouble("player_shops", base + "price", 0.0);
            s.amount = db.getInt("player_shops", base + "amount", 1);
            s.createdAt = db.getLong("player_shops", base + "created", 0L);
            s.trades = db.getLong("player_shops", base + "trades", 0L);
            s.turnover = db.getDouble("player_shops", base + "turnover", 0.0);
            s.item = db.getItemStack("player_shop_items", id);
            if (s.item != null && s.price > 0.0 && s.amount >= 1) {
               this.index(s);
            }
         } catch (RuntimeException ex) {
            this.plugin.getLogger().warning("[プレイヤーショップ] 読み込めないショップを飛ばしました: " + id + " (" + ex.getMessage() + ")");
         }
      }

      this.rebuildJson();
   }

   void save(PluginDataStore db) {
      db.removeSection("player_shops");
      db.removeSection("player_shop_items");

      for (Shop s : this.shops.values()) {
         String base = s.id + ".";
         db.setString("player_shops", base + "owner", s.owner.toString());
         db.setString("player_shops", base + "world", s.world);
         db.setString("player_shops", base + "sign", s.signX + "," + s.signY + "," + s.signZ);
         db.setString("player_shops", base + "chest", s.chestX + "," + s.chestY + "," + s.chestZ);
         db.setBoolean("player_shops", base + "selling", s.selling);
         db.setDouble("player_shops", base + "price", s.price);
         db.setInt("player_shops", base + "amount", s.amount);
         db.setLong("player_shops", base + "created", s.createdAt);
         db.setLong("player_shops", base + "trades", s.trades);
         db.setDouble("player_shops", base + "turnover", s.turnover);
         db.setItemStack("player_shop_items", s.id, s.item);
      }
   }

   private void index(Shop s) {
      this.shops.put(s.id, s);
      this.bySign.put(s.signKey(), s.id);
      this.byChest.put(s.chestKey(), s.id);
   }

   private void unindex(Shop s) {
      this.shops.remove(s.id);
      this.bySign.remove(s.signKey());
      this.byChest.remove(s.chestKey());
      this.rebuildJson();
   }

   // ------------------------------------------------------------------ lookups

   private Shop atSign(Block b) {
      String id = this.bySign.get(key(b));
      return id == null ? null : this.shops.get(id);
   }

   /** The shop whose stock lives in this block, including the other half of a double chest. */
   private Shop atContainer(Block b) {
      String id = this.byChest.get(key(b));
      if (id == null && b.getState() instanceof Chest chest && chest.getInventory().getHolder() instanceof DoubleChest dc) {
         for (InventoryHolder half : new InventoryHolder[]{dc.getLeftSide(), dc.getRightSide()}) {
            if (half instanceof Chest c) {
               id = this.byChest.get(key(c.getBlock()));
               if (id != null) {
                  break;
               }
            }
         }
      }

      return id == null ? null : this.shops.get(id);
   }

   private Shop atHolder(InventoryHolder holder) {
      if (holder instanceof DoubleChest dc) {
         Shop s = dc.getLeftSide() instanceof Chest l ? this.atContainer(l.getBlock()) : null;
         return s != null ? s : dc.getRightSide() instanceof Chest r ? this.atContainer(r.getBlock()) : null;
      }

      return holder instanceof Container c ? this.atContainer(c.getBlock()) : null;
   }

   private Inventory stockOf(Shop s) {
      World w = Bukkit.getWorld(s.world);
      if (w == null || !w.isChunkLoaded(s.chestX >> 4, s.chestZ >> 4)) {
         return null;
      }

      Block b = w.getBlockAt(s.chestX, s.chestY, s.chestZ);
      return b.getState() instanceof Container c && isShopContainer(b.getType()) ? c.getInventory() : null;
   }

   private Block containerFor(Block sign) {
      if (sign.getBlockData() instanceof WallSign wall) {
         Block behind = sign.getRelative(wall.getFacing().getOppositeFace());
         if (isShopContainer(behind.getType())) {
            return behind;
         }
      }

      for (BlockFace face : new BlockFace[]{BlockFace.DOWN, BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST}) {
         Block near = sign.getRelative(face);
         if (isShopContainer(near.getType())) {
            return near;
         }
      }

      return null;
   }

   String itemName(ItemStack item) {
      ItemMeta meta = item.getItemMeta();
      if (meta != null && meta.hasDisplayName()) {
         return PlainTextComponentSerializer.plainText().serialize(meta.displayName());
      }

      return this.plugin.resourceDisplayName(item.getType());
   }

   // ------------------------------------------------------------------ inventory maths

   private static int count(ItemStack[] contents, ItemStack like) {
      int n = 0;

      for (ItemStack it : contents) {
         if (it != null && it.isSimilar(like)) {
            n += it.getAmount();
         }
      }

      return n;
   }

   private static int room(ItemStack[] contents, ItemStack like) {
      int n = 0;
      int max = like.getMaxStackSize();

      for (ItemStack it : contents) {
         if (it == null || it.getType().isAir()) {
            n += max;
         } else if (it.isSimilar(like)) {
            n += Math.max(0, max - it.getAmount());
         }
      }

      return n;
   }

   private static void take(Inventory inv, ItemStack like, int n, boolean storageOnly) {
      ItemStack[] contents = storageOnly ? inv.getStorageContents() : inv.getContents();

      for (int i = 0; i < contents.length && n > 0; i++) {
         ItemStack it = contents[i];
         if (it != null && it.isSimilar(like)) {
            int used = Math.min(n, it.getAmount());
            it.setAmount(it.getAmount() - used);
            contents[i] = it.getAmount() <= 0 ? null : it;
            n -= used;
         }
      }

      if (storageOnly) {
         inv.setStorageContents(contents);
      } else {
         inv.setContents(contents);
      }
   }

   private static List<ItemStack> stacks(ItemStack like, int n) {
      List<ItemStack> out = new ArrayList<>();

      while (n > 0) {
         ItemStack s = like.clone();
         s.setAmount(Math.min(n, like.getMaxStackSize()));
         n -= s.getAmount();
         out.add(s);
      }

      return out;
   }

   // ------------------------------------------------------------------ creating and removing

   @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
   public void onSignChange(SignChangeEvent e) {
      PlainTextComponentSerializer plain = PlainTextComponentSerializer.plainText();
      String[] lines = new String[4];

      for (int i = 0; i < 4; i++) {
         Component line = e.line(i);
         lines[i] = line == null ? "" : plain.serialize(line);
      }

      BankUtil.ShopSign sign = BankUtil.parseShopSign(lines[0], lines[1], lines[2]);
      if (sign == null) {
         if (BankUtil.parseShopSign(lines[0], "販売 1", "1") != null) {
            this.plugin.msgKey(e.getPlayer(), "shop.invalid-sign");
            this.plugin.errorSound(e.getPlayer());
         }

         return;
      }

      Player p = e.getPlayer();
      if (!this.plugin.featureAllowed(p, "playershop")) {
         e.setCancelled(true);
         return;
      }

      Block container = this.containerFor(e.getBlock());
      if (container == null) {
         this.plugin.msgKey(p, "shop.need-container");
         this.plugin.errorSound(p);
         return;
      }

      if (this.atContainer(container) != null) {
         this.plugin.msgKey(p, "shop.already-shop");
         this.plugin.errorSound(p);
         return;
      }

      // the goods are whatever the owner put in the chest first; the off hand works for an empty buying shop
      ItemStack goods = null;
      if (container.getState() instanceof Container c) {
         for (ItemStack it : c.getInventory().getContents()) {
            if (it != null && !it.getType().isAir()) {
               goods = it;
               break;
            }
         }
      }

      if (goods == null && !p.getInventory().getItemInOffHand().getType().isAir()) {
         goods = p.getInventory().getItemInOffHand();
      }

      if (goods == null) {
         this.plugin.msgKey(p, "shop.need-item");
         this.plugin.errorSound(p);
         return;
      }

      long owned = this.shops.values().stream().filter(s -> s.owner.equals(p.getUniqueId())).count();
      if (owned >= this.maxPerPlayer() && !p.hasPermission("bank.admin")) {
         this.plugin.msgKey(p, "shop.limit", "max", String.valueOf(this.maxPerPlayer()));
         this.plugin.errorSound(p);
         return;
      }

      Shop s = new Shop();
      s.id = UUID.randomUUID().toString();
      s.owner = p.getUniqueId();
      s.world = e.getBlock().getWorld().getName();
      s.signX = e.getBlock().getX();
      s.signY = e.getBlock().getY();
      s.signZ = e.getBlock().getZ();
      s.chestX = container.getX();
      s.chestY = container.getY();
      s.chestZ = container.getZ();
      s.item = goods.clone();
      s.item.setAmount(1);
      s.selling = sign.selling();
      s.price = sign.price();
      s.amount = sign.amount();
      s.createdAt = System.currentTimeMillis();
      this.index(s);
      this.rebuildJson();

      String name = this.itemName(s.item);
      e.line(0, this.plugin.mm("<#1d3f8a><bold>[ショップ]</bold></#1d3f8a>"));
      String mode = s.selling ? "dark_green" : "dark_blue";
      e.line(1, this.plugin.mm("<black>" + esc(this.shorten(name, 14)) + "</black>"));
      e.line(2, this.plugin.mm("<" + mode + ">" + (s.selling ? "販売" : "買取") + "</" + mode + "> <black>" + s.amount + "個</black>"));
      e.line(3, this.plugin.mm("<#8a6312>" + this.plugin.fmtCur(s.price) + "</#8a6312>"));
      Bukkit.getScheduler().runTask(this.plugin, () -> {
         if (e.getBlock().getState() instanceof Sign placed) {
            placed.setWaxed(true);
            placed.update();
         }
      });
      this.plugin.msgKey(p, s.selling ? "shop.created-sell" : "shop.created-buy", "item", name, "amount", String.valueOf(s.amount), "price", this.plugin.fmtCur(s.price));
      this.plugin.playUiSound(p, "success");
      this.plugin.fxSparkle(p, false);
   }

   private static String esc(String s) {
      return MiniMessage.miniMessage().escapeTags(s);
   }

   private String shorten(String s, int max) {
      return s.length() <= max ? s : s.substring(0, max - 1) + "…";
   }

   @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
   public void onBreak(BlockBreakEvent e) {
      Shop s = this.atSign(e.getBlock());
      if (s == null && isShopContainer(e.getBlock().getType())) {
         s = this.atContainer(e.getBlock());
      }

      if (s != null) {
         Player p = e.getPlayer();
         if (!s.owner.equals(p.getUniqueId()) && !p.hasPermission("bank.admin")) {
            e.setCancelled(true);
            this.plugin.msgKey(p, "shop.protected");
            this.plugin.errorSound(p);
         } else {
            this.unindex(s);
            this.plugin.msgKey(p, "shop.removed", "item", this.itemName(s.item));
            this.plugin.playUiSound(p, "click");
         }
      }
   }

   @EventHandler(ignoreCancelled = true)
   public void onEntityExplode(EntityExplodeEvent e) {
      e.blockList().removeIf(b -> this.atSign(b) != null || isShopContainer(b.getType()) && this.atContainer(b) != null);
   }

   @EventHandler(ignoreCancelled = true)
   public void onBlockExplode(BlockExplodeEvent e) {
      e.blockList().removeIf(b -> this.atSign(b) != null || isShopContainer(b.getType()) && this.atContainer(b) != null);
   }

   @EventHandler(ignoreCancelled = true)
   public void onHopper(InventoryMoveItemEvent e) {
      // hoppers may restock a shop, never empty one
      if (this.atHolder(e.getSource().getHolder()) != null) {
         e.setCancelled(true);
      }
   }

   // ------------------------------------------------------------------ using a shop

   @EventHandler(priority = EventPriority.HIGH)
   public void onInteract(PlayerInteractEvent e) {
      if (e.getAction() != Action.RIGHT_CLICK_BLOCK || e.getHand() != EquipmentSlot.HAND || e.getClickedBlock() == null) {
         return;
      }

      Block b = e.getClickedBlock();
      Player p = e.getPlayer();
      Shop s = this.atSign(b);
      if (s != null) {
         e.setCancelled(true);
         if (!this.plugin.isFeatureEnabled("playershop")) {
            this.plugin.featureAllowed(p, "playershop");
            return;
         }

         this.openShop(p, s);
         return;
      }

      if (isShopContainer(b.getType())) {
         s = this.atContainer(b);
         if (s != null && !s.owner.equals(p.getUniqueId()) && !p.hasPermission("bank.admin")) {
            e.setCancelled(true);
            this.plugin.msgKey(p, "shop.chest-locked", "owner", this.ownerName(s));
            this.plugin.errorSound(p);
         }
      }
   }

   private String ownerName(Shop s) {
      String name = Bukkit.getOfflinePlayer(s.owner).getName();
      return name != null ? name : "?";
   }

   void openShop(Player p, Shop s) {
      boolean owner = s.owner.equals(p.getUniqueId());
      Inventory gui = Bukkit.createInventory(new Menu(s.id, owner, 0), 27, this.plugin.guiTitle(owner ? "自分のショップ" : "プレイヤーショップ", "gold"));
      Inventory stock = this.stockOf(s);
      int inStock = stock == null ? 0 : count(stock.getContents(), s.item);
      int space = stock == null ? 0 : room(stock.getStorageContents(), s.item);
      String name = this.itemName(s.item);
      ItemStack display = s.item.clone();
      display.setAmount(Math.max(1, Math.min(s.amount, display.getMaxStackSize())));
      ItemMeta meta = display.getItemMeta();
      if (meta != null) {
         List<Component> lore = new ArrayList<>();
         lore.add(Component.empty());
         lore.add(this.line("<gray>店主:</gray> <white>" + this.ownerName(s) + "</white>"));
         lore.add(this.line("<gray>" + (s.selling ? "販売" : "買取") + ":</gray> <white>" + s.amount + "個</white> <gray>で</gray> <gold>" + this.plugin.fmtCur(s.price) + "</gold>"));
         lore.add(this.line(s.selling
            ? "<gray>在庫:</gray> <white>" + inStock + "個</white> <dark_gray>(" + inStock / s.amount + "回分)</dark_gray>"
            : "<gray>買取枠:</gray> <white>" + space + "個</white> <dark_gray>(" + space / s.amount + "回分)</dark_gray>"));
         meta.lore(lore);
         display.setItemMeta(meta);
      }

      gui.setItem(4, display);
      if (owner) {
         gui.setItem(11, this.plugin.createItem(Material.BOOK, "<gold><bold>売上の記録</bold></gold>",
            "<gray>取引回数:</gray> <white>" + s.trades + "回</white>",
            "<gray>" + (s.selling ? "売上" : "買取額") + "の合計:</gray> <gold>" + this.plugin.fmtCur(s.turnover) + "</gold>",
            "<gray>開店:</gray> <white>" + new java.text.SimpleDateFormat("yyyy/MM/dd").format(new java.util.Date(s.createdAt)) + "</white>"));
         gui.setItem(13, this.plugin.createItem(Material.CHEST, "<aqua><bold>在庫を確認</bold></aqua>",
            s.selling ? "<gray>在庫:</gray> <white>" + inStock + "個</white>" : "<gray>空き:</gray> <white>" + space + "個</white>",
            "<gray>チェストを開けて補充・回収できます</gray>"));
         gui.setItem(15, this.plugin.createItem(Material.BARRIER, "<red><bold>閉店する</bold></red>",
            "<gray>看板とチェストはそのまま残ります</gray>", "<yellow>クリックで閉店</yellow>"));
      } else {
         for (int i = 0; i < TRADE_TIMES.length; i++) {
            int times = TRADE_TIMES[i];
            boolean possible = s.selling ? inStock >= s.amount * times : space >= s.amount * times;
            gui.setItem(TRADE_SLOTS[i], this.plugin.createItem(
               possible ? (s.selling ? Material.EMERALD : Material.GOLD_INGOT) : Material.GRAY_DYE,
               (possible ? "<green><bold>" : "<gray><bold>") + (s.selling ? "購入" : "売却") + " ×" + times + "</bold>" + (possible ? "</green>" : "</gray>"),
               "<gray>" + esc(name) + " ×" + s.amount * times + "</gray>",
               "<gray>" + (s.selling ? "支払い" : "受け取り") + ":</gray> <gold>" + this.plugin.fmtCur(s.price * times) + "</gold>",
               possible ? "<yellow>クリックで" + (s.selling ? "購入" : "売却") + "</yellow>" : "<red>" + (s.selling ? "在庫が足りません" : "買取枠が足りません") + "</red>"));
         }
      }

      gui.setItem(22, this.plugin.createItem(Material.IRON_DOOR, "<gray>閉じる</gray>"));
      this.plugin.fillGlass(gui);
      p.openInventory(gui);
      this.plugin.playUiSound(p, "open");
   }

   private Component line(String mini) {
      return this.plugin.guiText(mini);
   }

   @EventHandler(priority = EventPriority.HIGH)
   public void onMenuClick(InventoryClickEvent e) {
      if (!(e.getView().getTopInventory().getHolder() instanceof Menu menu) || !(e.getWhoClicked() instanceof Player p)) {
         return;
      }

      e.setCancelled(true);
      if (e.getClickedInventory() != e.getView().getTopInventory()) {
         return;
      }

      int slot = e.getRawSlot();
      if (menu.shopId() == null) {
         this.onListClick(p, menu, slot);
         return;
      }

      Shop s = this.shops.get(menu.shopId());
      if (slot == 22 || s == null) {
         p.closeInventory();
         return;
      }

      if (menu.ownerView()) {
         if (slot == 15) {
            this.unindex(s);
            p.closeInventory();
            this.plugin.msgKey(p, "shop.closed", "item", this.itemName(s.item));
            this.plugin.playUiSound(p, "click");
         } else if (slot == 13) {
            p.closeInventory();
            this.plugin.msgKey(p, "shop.restock-hint");
         }

         return;
      }

      for (int i = 0; i < TRADE_SLOTS.length; i++) {
         if (slot == TRADE_SLOTS[i]) {
            this.executeTrade(p, s, TRADE_TIMES[i]);
            if (this.shops.containsKey(s.id)) {
               this.openShop(p, s);
            }

            return;
         }
      }
   }

   @EventHandler
   public void onMenuDrag(InventoryDragEvent e) {
      if (e.getView().getTopInventory().getHolder() instanceof Menu) {
         e.setCancelled(true);
      }
   }

   private void fail(Player p, String key, String... kv) {
      this.plugin.msgKey(p, key, kv);
      this.plugin.errorSound(p);
   }

   private void executeTrade(Player p, Shop s, int times) {
      if (!this.plugin.featureAllowed(p, "playershop")) {
         return;
      }

      Inventory stock = this.stockOf(s);
      if (stock == null) {
         this.fail(p, "shop.broken");
         return;
      }

      OfflinePlayer owner = Bukkit.getOfflinePlayer(s.owner);
      int items = s.amount * times;
      double total = Math.round(s.price * times * 100.0) / 100.0;
      String name = this.itemName(s.item);
      if (s.selling) {
         if (count(stock.getContents(), s.item) < items) {
            this.fail(p, "shop.out-of-stock");
            return;
         }

         if (room(p.getInventory().getStorageContents(), s.item) < items) {
            this.fail(p, "shop.no-space");
            return;
         }

         if (!MinecraftBank.econ.has(p, total)) {
            this.fail(p, "shop.no-money", "amount", this.plugin.fmtCur(total));
            return;
         }

         if (!this.plugin.ecoWithdraw(p, total).transactionSuccess()) {
            this.fail(p, "shop.no-money", "amount", this.plugin.fmtCur(total));
            return;
         }

         double tax = this.plugin.collectSalesTax(s.owner, total);
         if (!this.plugin.ecoDeposit(owner, total - tax).transactionSuccess()) {
            this.plugin.ecoDeposit(p, total);
            this.fail(p, "shop.failed");
            return;
         }

         take(stock, s.item, items, false);

         for (ItemStack left : p.getInventory().addItem(stacks(s.item, items).toArray(new ItemStack[0])).values()) {
            p.getWorld().dropItemNaturally(p.getLocation(), left);
         }

         this.plugin.msgKey(p, "shop.bought", "item", name, "count", String.valueOf(items), "amount", this.plugin.fmtCur(total), "owner", this.ownerName(s));
         this.notifyOwner(s, owner, "shop.owner-sold", p.getName(), name, items, total - tax);
         this.plugin.addLog(p.getUniqueId(), "ショップで購入: " + name + " ×" + items + " -" + this.plugin.fmtCur(total));
         this.plugin.addLog(s.owner, "ショップの売上: " + name + " ×" + items + " +" + this.plugin.fmtCur(total - tax));
      } else {
         if (count(p.getInventory().getStorageContents(), s.item) < items) {
            this.fail(p, "shop.not-enough-items", "item", name, "count", String.valueOf(items));
            return;
         }

         if (room(stock.getStorageContents(), s.item) < items) {
            this.fail(p, "shop.chest-full");
            return;
         }

         if (!MinecraftBank.econ.has(owner, total) || !this.plugin.ecoWithdraw(owner, total).transactionSuccess()) {
            this.fail(p, "shop.owner-no-money");
            return;
         }

         double tax = this.plugin.collectSalesTax(p.getUniqueId(), total);
         if (!this.plugin.ecoDeposit(p, total - tax).transactionSuccess()) {
            this.plugin.ecoDeposit(owner, total);
            this.fail(p, "shop.failed");
            return;
         }

         take(p.getInventory(), s.item, items, true);
         stock.addItem(stacks(s.item, items).toArray(new ItemStack[0]));
         this.plugin.msgKey(p, "shop.sold", "item", name, "count", String.valueOf(items), "amount", this.plugin.fmtCur(total - tax), "owner", this.ownerName(s));
         this.notifyOwner(s, owner, "shop.owner-bought", p.getName(), name, items, total);
         this.plugin.addLog(p.getUniqueId(), "ショップに売却: " + name + " ×" + items + " +" + this.plugin.fmtCur(total - tax));
         this.plugin.addLog(s.owner, "ショップで買取: " + name + " ×" + items + " -" + this.plugin.fmtCur(total));
      }

      s.trades += times;
      s.turnover += total;
      this.plugin.playUiSound(p, "coin");
      this.plugin.fxSparkle(p, false);
      this.rebuildJson();
   }

   private void notifyOwner(Shop s, OfflinePlayer owner, String key, String customer, String item, int count, double amount) {
      Player online = owner.getPlayer();
      if (online != null && online.isOnline()) {
         online.sendActionBar(this.plugin.mm(this.plugin.getMsgFor(online, key, "player", customer, "item", item, "count", String.valueOf(count), "amount", this.plugin.fmtCur(amount))));
         this.plugin.playUiSound(online, "coin");
      }
   }

   // ------------------------------------------------------------------ directory

   void openList(Player p, int page) {
      List<Shop> list = new ArrayList<>(this.shops.values());
      list.sort(Comparator.comparing((Shop s) -> this.itemName(s.item)).thenComparingDouble(s -> s.price / s.amount));
      int pages = Math.max(1, (list.size() + 27) / 28);
      page = Math.max(0, Math.min(page, pages - 1));
      Inventory gui = Bukkit.createInventory(new Menu(null, false, page), 54, this.plugin.guiTitle("ショップ一覧 (" + (page + 1) + "/" + pages + ")", "gold"));
      int[] slots = {10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41, 42, 43};

      for (int i = 0; i < slots.length && page * 28 + i < list.size(); i++) {
         Shop s = list.get(page * 28 + i);
         ItemStack icon = s.item.clone();
         icon.setAmount(1);
         ItemMeta meta = icon.getItemMeta();
         if (meta != null) {
            meta.displayName(this.line((s.selling ? "<green>[販売]</green> " : "<aqua>[買取]</aqua> ") + "<white><bold>" + esc(this.itemName(s.item)) + "</bold></white>"));
            meta.lore(List.of(
               Component.empty(),
               this.line("<gray>" + s.amount + "個で</gray> <gold>" + this.plugin.fmtCur(s.price) + "</gold> <dark_gray>(1個 " + this.plugin.fmtCur(s.price / s.amount) + ")</dark_gray>"),
               this.line("<gray>店主:</gray> <white>" + this.ownerName(s) + "</white>"),
               this.line("<gray>場所:</gray> <white>" + s.world + " " + s.signX + ", " + s.signY + ", " + s.signZ + "</white>")));
            icon.setItemMeta(meta);
         }

         gui.setItem(slots[i], icon);
      }

      if (list.isEmpty()) {
         gui.setItem(22, this.plugin.createItem(Material.OAK_SIGN, "<gray>まだショップがありません</gray>",
            "<gray>商品を入れたチェストに看板を付けて</gray>", "<white>1行目 [ショップ] / 2行目 販売 100</white>", "<gray>と書くと開店できます</gray>"));
      }

      if (page > 0) {
         gui.setItem(45, this.plugin.createItem(Material.ARROW, "<white>← 前のページ</white>"));
      }

      if (page < pages - 1) {
         gui.setItem(53, this.plugin.createItem(Material.ARROW, "<white>次のページ →</white>"));
      }

      gui.setItem(49, this.plugin.createItem(Material.IRON_DOOR, "<gray>閉じる</gray>"));
      this.plugin.fillGlass(gui);
      p.openInventory(gui);
      this.plugin.playUiSound(p, "open");
   }

   private void onListClick(Player p, Menu menu, int slot) {
      if (slot == 45) {
         this.openList(p, menu.page() - 1);
      } else if (slot == 53) {
         this.openList(p, menu.page() + 1);
      } else if (slot == 49) {
         p.closeInventory();
      }
   }

   // ------------------------------------------------------------------ web

   void rebuildJson() {
      StringBuilder sb = new StringBuilder("{\"generated_at\":").append(System.currentTimeMillis()).append(",\"shops\":[");
      boolean first = true;

      for (Shop s : this.shops.values()) {
         Inventory stock = this.stockOf(s);
         int available = stock == null ? -1 : s.selling ? count(stock.getContents(), s.item) : room(stock.getStorageContents(), s.item);
         sb.append(first ? "" : ",")
            .append("{\"item\":\"").append(BankUtil.jsonEscape(this.itemName(s.item)))
            .append("\",\"material\":\"").append(s.item.getType().name())
            .append("\",\"selling\":").append(s.selling)
            .append(",\"price\":").append(s.price)
            .append(",\"amount\":").append(s.amount)
            .append(",\"available\":").append(available)
            .append(",\"owner\":\"").append(BankUtil.jsonEscape(this.ownerName(s)))
            .append("\",\"world\":\"").append(BankUtil.jsonEscape(s.world))
            .append("\",\"x\":").append(s.signX).append(",\"y\":").append(s.signY).append(",\"z\":").append(s.signZ)
            .append(",\"trades\":").append(s.trades)
            .append("}");
         first = false;
      }

      this.jsonCache = sb.append("]}").toString();
   }
}
