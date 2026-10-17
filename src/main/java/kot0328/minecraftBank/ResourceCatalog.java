package kot0328.minecraftBank;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Material;

/** Static price tables: resource shop, traveling merchant, VIP shop and country currencies. */
final class ResourceCatalog {
   private ResourceCatalog() {
   }

   static void register(
      Map<String, String> COUNTRY_CURRENCY_MAP,
      LinkedHashMap<Material, Double> RESOURCE_BASE_PRICES,
      Map<String, List<Material>> RESOURCE_CATEGORIES,
      LinkedHashMap<Material, Double> MERCHANT_ITEM_POOL,
      LinkedHashMap<Material, Double> VIP_SHOP_POOL
   ) {
   COUNTRY_CURRENCY_MAP.put("japan", "円");
   COUNTRY_CURRENCY_MAP.put("united states", "USD");
   COUNTRY_CURRENCY_MAP.put("usa", "USD");
   COUNTRY_CURRENCY_MAP.put("united kingdom", "GBP");
   COUNTRY_CURRENCY_MAP.put("uk", "GBP");
   COUNTRY_CURRENCY_MAP.put("germany", "EUR");
   COUNTRY_CURRENCY_MAP.put("france", "EUR");
   COUNTRY_CURRENCY_MAP.put("italy", "EUR");
   COUNTRY_CURRENCY_MAP.put("spain", "EUR");
   COUNTRY_CURRENCY_MAP.put("canada", "CAD");
   COUNTRY_CURRENCY_MAP.put("australia", "AUD");
   COUNTRY_CURRENCY_MAP.put("hong kong", "HKD");
   COUNTRY_CURRENCY_MAP.put("china", "CNY");
   COUNTRY_CURRENCY_MAP.put("south korea", "KRW");
   COUNTRY_CURRENCY_MAP.put("korea", "KRW");
   COUNTRY_CURRENCY_MAP.put("india", "INR");
   COUNTRY_CURRENCY_MAP.put("brazil", "BRL");
   COUNTRY_CURRENCY_MAP.put("switzerland", "CHF");
   COUNTRY_CURRENCY_MAP.put("singapore", "SGD");
   COUNTRY_CURRENCY_MAP.put("new zealand", "NZD");
   COUNTRY_CURRENCY_MAP.put("mexico", "MXN");
   COUNTRY_CURRENCY_MAP.put("taiwan", "TWD");
   COUNTRY_CURRENCY_MAP.put("thailand", "THB");
   COUNTRY_CURRENCY_MAP.put("russia", "RUB");
   RESOURCE_BASE_PRICES.put(Material.COAL, 30.0);
   RESOURCE_BASE_PRICES.put(Material.IRON_INGOT, 95.0);
   RESOURCE_BASE_PRICES.put(Material.GOLD_INGOT, 150.0);
   RESOURCE_BASE_PRICES.put(Material.COPPER_INGOT, 40.0);
   RESOURCE_BASE_PRICES.put(Material.REDSTONE, 20.0);
   RESOURCE_BASE_PRICES.put(Material.LAPIS_LAZULI, 25.0);
   RESOURCE_BASE_PRICES.put(Material.DIAMOND, 950.0);
   RESOURCE_BASE_PRICES.put(Material.EMERALD, 550.0);
   RESOURCE_BASE_PRICES.put(Material.NETHERITE_SCRAP, 8000.0);
   RESOURCE_BASE_PRICES.put(Material.WHEAT, 12.0);
   RESOURCE_BASE_PRICES.put(Material.CARROT, 8.0);
   RESOURCE_BASE_PRICES.put(Material.POTATO, 8.0);
   RESOURCE_BASE_PRICES.put(Material.BEETROOT, 8.0);
   RESOURCE_BASE_PRICES.put(Material.MELON_SLICE, 8.0);
   RESOURCE_BASE_PRICES.put(Material.PUMPKIN, 15.0);
   RESOURCE_BASE_PRICES.put(Material.SUGAR_CANE, 12.0);
   RESOURCE_BASE_PRICES.put(Material.NETHER_WART, 20.0);
   RESOURCE_BASE_PRICES.put(Material.COCOA_BEANS, 15.0);
   RESOURCE_BASE_PRICES.put(Material.ROTTEN_FLESH, 4.0);
   RESOURCE_BASE_PRICES.put(Material.BONE, 8.0);
   RESOURCE_BASE_PRICES.put(Material.STRING, 8.0);
   RESOURCE_BASE_PRICES.put(Material.GUNPOWDER, 23.0);
   RESOURCE_BASE_PRICES.put(Material.SPIDER_EYE, 12.0);
   RESOURCE_BASE_PRICES.put(Material.SLIME_BALL, 20.0);
   RESOURCE_BASE_PRICES.put(Material.ENDER_PEARL, 150.0);
   RESOURCE_BASE_PRICES.put(Material.BLAZE_ROD, 115.0);
   RESOURCE_BASE_PRICES.put(Material.GHAST_TEAR, 225.0);
   RESOURCE_BASE_PRICES.put(Material.OAK_LOG, 8.0);
   RESOURCE_BASE_PRICES.put(Material.SPRUCE_LOG, 8.0);
   RESOURCE_BASE_PRICES.put(Material.BIRCH_LOG, 8.0);
   RESOURCE_BASE_PRICES.put(Material.JUNGLE_LOG, 8.0);
   RESOURCE_BASE_PRICES.put(Material.ACACIA_LOG, 8.0);
   RESOURCE_BASE_PRICES.put(Material.DARK_OAK_LOG, 8.0);
   RESOURCE_BASE_PRICES.put(Material.MANGROVE_LOG, 8.0);
   RESOURCE_BASE_PRICES.put(Material.CHERRY_LOG, 8.0);
   RESOURCE_BASE_PRICES.put(Material.RAW_IRON, 91.0);
   RESOURCE_BASE_PRICES.put(Material.RAW_GOLD, 143.0);
   RESOURCE_BASE_PRICES.put(Material.RAW_COPPER, 38.0);
   RESOURCE_BASE_PRICES.put(Material.IRON_NUGGET, 10.56);
   RESOURCE_BASE_PRICES.put(Material.GOLD_NUGGET, 16.67);
   RESOURCE_BASE_PRICES.put(Material.QUARTZ, 30.0);
   RESOURCE_BASE_PRICES.put(Material.AMETHYST_SHARD, 30.0);
   RESOURCE_BASE_PRICES.put(Material.GLOWSTONE_DUST, 15.0);
   RESOURCE_BASE_PRICES.put(Material.FLINT, 6.0);
   RESOURCE_BASE_PRICES.put(Material.CLAY_BALL, 6.0);
   RESOURCE_BASE_PRICES.put(Material.NETHERITE_INGOT, 30000.0);
   RESOURCE_BASE_PRICES.put(Material.SWEET_BERRIES, 6.0);
   RESOURCE_BASE_PRICES.put(Material.GLOW_BERRIES, 8.0);
   RESOURCE_BASE_PRICES.put(Material.APPLE, 15.0);
   RESOURCE_BASE_PRICES.put(Material.BAMBOO, 3.0);
   RESOURCE_BASE_PRICES.put(Material.CACTUS, 6.0);
   RESOURCE_BASE_PRICES.put(Material.KELP, 3.0);
   RESOURCE_BASE_PRICES.put(Material.CHORUS_FRUIT, 15.0);
   RESOURCE_BASE_PRICES.put(Material.BROWN_MUSHROOM, 8.0);
   RESOURCE_BASE_PRICES.put(Material.RED_MUSHROOM, 8.0);
   RESOURCE_BASE_PRICES.put(Material.SUGAR, 11.0);
   RESOURCE_BASE_PRICES.put(Material.WHEAT_SEEDS, 2.0);
   RESOURCE_BASE_PRICES.put(Material.HAY_BLOCK, 108.0);
   RESOURCE_BASE_PRICES.put(Material.MELON, 60.0);
   RESOURCE_BASE_PRICES.put(Material.VINE, 3.0);
   RESOURCE_BASE_PRICES.put(Material.LILY_PAD, 6.0);
   RESOURCE_BASE_PRICES.put(Material.EGG, 6.0);
   RESOURCE_BASE_PRICES.put(Material.LEATHER, 20.0);
   RESOURCE_BASE_PRICES.put(Material.FEATHER, 8.0);
   RESOURCE_BASE_PRICES.put(Material.INK_SAC, 10.0);
   RESOURCE_BASE_PRICES.put(Material.GLOW_INK_SAC, 25.0);
   RESOURCE_BASE_PRICES.put(Material.PHANTOM_MEMBRANE, 60.0);
   RESOURCE_BASE_PRICES.put(Material.RABBIT_HIDE, 15.0);
   RESOURCE_BASE_PRICES.put(Material.PRISMARINE_SHARD, 20.0);
   RESOURCE_BASE_PRICES.put(Material.PRISMARINE_CRYSTALS, 25.0);
   RESOURCE_BASE_PRICES.put(Material.MAGMA_CREAM, 40.0);
   RESOURCE_BASE_PRICES.put(Material.SHULKER_SHELL, 1200.0);
   RESOURCE_BASE_PRICES.put(Material.NAUTILUS_SHELL, 600.0);
   RESOURCE_BASE_PRICES.put(Material.ARMADILLO_SCUTE, 60.0);
   RESOURCE_BASE_PRICES.put(Material.TURTLE_SCUTE, 120.0);
   RESOURCE_BASE_PRICES.put(Material.BREEZE_ROD, 150.0);
   RESOURCE_BASE_PRICES.put(Material.WHITE_WOOL, 12.0);
   RESOURCE_BASE_PRICES.put(Material.ECHO_SHARD, 800.0);
   RESOURCE_BASE_PRICES.put(Material.HONEYCOMB, 20.0);
   RESOURCE_BASE_PRICES.put(Material.BLAZE_POWDER, 50.0);
   RESOURCE_BASE_PRICES.put(Material.PALE_OAK_LOG, 8.0);
   RESOURCE_BASE_PRICES.put(Material.CRIMSON_STEM, 10.0);
   RESOURCE_BASE_PRICES.put(Material.WARPED_STEM, 10.0);
   RESOURCE_BASE_PRICES.put(Material.BAMBOO_BLOCK, 6.0);
   RESOURCE_BASE_PRICES.put(Material.OAK_PLANKS, 1.8);
   RESOURCE_BASE_PRICES.put(Material.STICK, 0.9);
   RESOURCE_BASE_PRICES.put(Material.STONE, 1.05);
   RESOURCE_BASE_PRICES.put(Material.COBBLESTONE, 1.0);
   RESOURCE_BASE_PRICES.put(Material.DEEPSLATE, 1.55);
   RESOURCE_BASE_PRICES.put(Material.COBBLED_DEEPSLATE, 1.5);
   RESOURCE_BASE_PRICES.put(Material.GRANITE, 2.0);
   RESOURCE_BASE_PRICES.put(Material.DIORITE, 2.0);
   RESOURCE_BASE_PRICES.put(Material.ANDESITE, 2.0);
   RESOURCE_BASE_PRICES.put(Material.TUFF, 2.0);
   RESOURCE_BASE_PRICES.put(Material.CALCITE, 3.0);
   RESOURCE_BASE_PRICES.put(Material.DIRT, 1.0);
   RESOURCE_BASE_PRICES.put(Material.GRAVEL, 2.0);
   RESOURCE_BASE_PRICES.put(Material.SAND, 2.0);
   RESOURCE_BASE_PRICES.put(Material.RED_SAND, 3.0);
   RESOURCE_BASE_PRICES.put(Material.SANDSTONE, 7.0);
   RESOURCE_BASE_PRICES.put(Material.GLASS, 2.1);
   RESOURCE_BASE_PRICES.put(Material.BRICKS, 22.0);
   RESOURCE_BASE_PRICES.put(Material.TERRACOTTA, 6.0);
   RESOURCE_BASE_PRICES.put(Material.CLAY, 22.0);
   RESOURCE_BASE_PRICES.put(Material.SNOW_BLOCK, 4.0);
   RESOURCE_BASE_PRICES.put(Material.ICE, 6.0);
   RESOURCE_BASE_PRICES.put(Material.PACKED_ICE, 50.0);
   RESOURCE_BASE_PRICES.put(Material.BLUE_ICE, 400.0);
   RESOURCE_BASE_PRICES.put(Material.OBSIDIAN, 60.0);
   RESOURCE_BASE_PRICES.put(Material.CRYING_OBSIDIAN, 120.0);
   RESOURCE_BASE_PRICES.put(Material.MOSS_BLOCK, 8.0);
   RESOURCE_BASE_PRICES.put(Material.MUD, 3.0);
   RESOURCE_BASE_PRICES.put(Material.END_STONE, 6.0);
   RESOURCE_BASE_PRICES.put(Material.NETHERRACK, 1.0);
   RESOURCE_BASE_PRICES.put(Material.SOUL_SAND, 4.0);
   RESOURCE_BASE_PRICES.put(Material.BASALT, 2.0);
   RESOURCE_BASE_PRICES.put(Material.BLACKSTONE, 2.0);
   RESOURCE_BASE_PRICES.put(Material.MAGMA_BLOCK, 145.0);
   RESOURCE_BASE_PRICES.put(Material.PRISMARINE, 72.0);
   RESOURCE_BASE_PRICES.put(Material.SEA_LANTERN, 185.0);
   RESOURCE_BASE_PRICES.put(Material.GLOWSTONE, 54.0);
   RESOURCE_BASE_PRICES.put(Material.QUARTZ_BLOCK, 108.0);
   RESOURCE_BASE_PRICES.put(Material.BEEF, 10.0);
   RESOURCE_BASE_PRICES.put(Material.COOKED_BEEF, 10.5);
   RESOURCE_BASE_PRICES.put(Material.PORKCHOP, 10.0);
   RESOURCE_BASE_PRICES.put(Material.COOKED_PORKCHOP, 10.5);
   RESOURCE_BASE_PRICES.put(Material.CHICKEN, 8.0);
   RESOURCE_BASE_PRICES.put(Material.COOKED_CHICKEN, 8.4);
   RESOURCE_BASE_PRICES.put(Material.MUTTON, 8.0);
   RESOURCE_BASE_PRICES.put(Material.COOKED_MUTTON, 8.4);
   RESOURCE_BASE_PRICES.put(Material.COD, 8.0);
   RESOURCE_BASE_PRICES.put(Material.COOKED_COD, 8.4);
   RESOURCE_BASE_PRICES.put(Material.SALMON, 10.0);
   RESOURCE_BASE_PRICES.put(Material.COOKED_SALMON, 10.5);
   RESOURCE_BASE_PRICES.put(Material.BREAD, 32.0);
   RESOURCE_BASE_PRICES.put(Material.BAKED_POTATO, 8.4);
   RESOURCE_BASE_PRICES.put(Material.PUMPKIN_PIE, 28.0);
   RESOURCE_BASE_PRICES.put(Material.CAKE, 55.0);
   RESOURCE_BASE_PRICES.put(Material.COOKIE, 4.0);
   RESOURCE_BASE_PRICES.put(Material.MUSHROOM_STEW, 15.0);
   RESOURCE_BASE_PRICES.put(Material.HONEY_BOTTLE, 25.0);
   RESOURCE_BASE_PRICES.put(Material.GOLDEN_CARROT, 130.0);
   RESOURCE_BASE_PRICES.put(Material.GOLDEN_APPLE, 1100.0);
   RESOURCE_BASE_PRICES.put(Material.EXPERIENCE_BOTTLE, 120.0);
   RESOURCE_BASE_PRICES.put(Material.ARROW, 3.0);
   RESOURCE_BASE_PRICES.put(Material.TORCH, 2.0);
   RESOURCE_BASE_PRICES.put(Material.PAPER, 11.0);
   RESOURCE_BASE_PRICES.put(Material.BOOK, 34.0);
   RESOURCE_BASE_PRICES.put(Material.LEAD, 24.0);
   RESOURCE_BASE_PRICES.put(Material.BUCKET, 255.0);
   RESOURCE_BASE_PRICES.put(Material.FIREWORK_ROCKET, 10.0);
   RESOURCE_BASE_PRICES.put(Material.TNT, 110.0);
   RESOURCE_BASE_PRICES.put(Material.SLIME_BLOCK, 180.0);
   RESOURCE_BASE_PRICES.put(Material.HONEY_BLOCK, 90.0);
   RESOURCE_BASE_PRICES.put(Material.COBWEB, 40.0);
   RESOURCE_BASE_PRICES.put(Material.ENDER_EYE, 180.0);
   RESOURCE_BASE_PRICES.put(Material.DRAGON_BREATH, 300.0);
   RESOURCE_BASE_PRICES.put(Material.TRIAL_KEY, 500.0);
   RESOURCE_BASE_PRICES.put(Material.SPONGE, 600.0);
   RESOURCE_CATEGORIES.put(
      "ore",
      List.of(
         Material.COAL,
         Material.IRON_INGOT,
         Material.GOLD_INGOT,
         Material.COPPER_INGOT,
         Material.REDSTONE,
         Material.LAPIS_LAZULI,
         Material.DIAMOND,
         Material.EMERALD,
         Material.NETHERITE_SCRAP
      )
   );
   RESOURCE_CATEGORIES.put(
      "crop",
      List.of(
         Material.WHEAT,
         Material.CARROT,
         Material.POTATO,
         Material.BEETROOT,
         Material.MELON_SLICE,
         Material.PUMPKIN,
         Material.SUGAR_CANE,
         Material.NETHER_WART,
         Material.COCOA_BEANS
      )
   );
   RESOURCE_CATEGORIES.put(
      "drop",
      List.of(
         Material.ROTTEN_FLESH,
         Material.BONE,
         Material.STRING,
         Material.GUNPOWDER,
         Material.SPIDER_EYE,
         Material.SLIME_BALL,
         Material.ENDER_PEARL,
         Material.BLAZE_ROD,
         Material.GHAST_TEAR
      )
   );
   RESOURCE_CATEGORIES.put(
      "wood",
      List.of(
         Material.OAK_LOG,
         Material.SPRUCE_LOG,
         Material.BIRCH_LOG,
         Material.JUNGLE_LOG,
         Material.ACACIA_LOG,
         Material.DARK_OAK_LOG,
         Material.MANGROVE_LOG,
         Material.CHERRY_LOG
      )
   );
   RESOURCE_CATEGORIES.merge("ore", List.of(Material.RAW_IRON, Material.RAW_GOLD, Material.RAW_COPPER, Material.IRON_NUGGET, Material.GOLD_NUGGET, Material.QUARTZ, Material.AMETHYST_SHARD, Material.GLOWSTONE_DUST, Material.FLINT, Material.CLAY_BALL, Material.NETHERITE_INGOT), (a, b) -> {
      List<Material> merged = new ArrayList<>(a);
      merged.addAll(b);
      return List.copyOf(merged);
   });
   RESOURCE_CATEGORIES.merge("crop", List.of(Material.SWEET_BERRIES, Material.GLOW_BERRIES, Material.APPLE, Material.BAMBOO, Material.CACTUS, Material.KELP, Material.CHORUS_FRUIT, Material.BROWN_MUSHROOM, Material.RED_MUSHROOM, Material.SUGAR, Material.WHEAT_SEEDS, Material.HAY_BLOCK, Material.MELON, Material.VINE, Material.LILY_PAD, Material.EGG), (a, b) -> {
      List<Material> merged = new ArrayList<>(a);
      merged.addAll(b);
      return List.copyOf(merged);
   });
   RESOURCE_CATEGORIES.merge("drop", List.of(Material.LEATHER, Material.FEATHER, Material.INK_SAC, Material.GLOW_INK_SAC, Material.PHANTOM_MEMBRANE, Material.RABBIT_HIDE, Material.PRISMARINE_SHARD, Material.PRISMARINE_CRYSTALS, Material.MAGMA_CREAM, Material.SHULKER_SHELL, Material.NAUTILUS_SHELL, Material.ARMADILLO_SCUTE, Material.TURTLE_SCUTE, Material.BREEZE_ROD, Material.WHITE_WOOL, Material.ECHO_SHARD, Material.HONEYCOMB, Material.BLAZE_POWDER), (a, b) -> {
      List<Material> merged = new ArrayList<>(a);
      merged.addAll(b);
      return List.copyOf(merged);
   });
   RESOURCE_CATEGORIES.merge("wood", List.of(Material.PALE_OAK_LOG, Material.CRIMSON_STEM, Material.WARPED_STEM, Material.BAMBOO_BLOCK, Material.OAK_PLANKS, Material.STICK), (a, b) -> {
      List<Material> merged = new ArrayList<>(a);
      merged.addAll(b);
      return List.copyOf(merged);
   });
   RESOURCE_CATEGORIES.put("block", List.of(Material.STONE, Material.COBBLESTONE, Material.DEEPSLATE, Material.COBBLED_DEEPSLATE, Material.GRANITE, Material.DIORITE, Material.ANDESITE, Material.TUFF, Material.CALCITE, Material.DIRT, Material.GRAVEL, Material.SAND, Material.RED_SAND, Material.SANDSTONE, Material.GLASS, Material.BRICKS, Material.TERRACOTTA, Material.CLAY, Material.SNOW_BLOCK, Material.ICE, Material.PACKED_ICE, Material.BLUE_ICE, Material.OBSIDIAN, Material.CRYING_OBSIDIAN, Material.MOSS_BLOCK, Material.MUD, Material.END_STONE, Material.NETHERRACK, Material.SOUL_SAND, Material.BASALT, Material.BLACKSTONE, Material.MAGMA_BLOCK, Material.PRISMARINE, Material.SEA_LANTERN, Material.GLOWSTONE, Material.QUARTZ_BLOCK));
   RESOURCE_CATEGORIES.put("food", List.of(Material.BEEF, Material.COOKED_BEEF, Material.PORKCHOP, Material.COOKED_PORKCHOP, Material.CHICKEN, Material.COOKED_CHICKEN, Material.MUTTON, Material.COOKED_MUTTON, Material.COD, Material.COOKED_COD, Material.SALMON, Material.COOKED_SALMON, Material.BREAD, Material.BAKED_POTATO, Material.PUMPKIN_PIE, Material.CAKE, Material.COOKIE, Material.MUSHROOM_STEW, Material.HONEY_BOTTLE, Material.GOLDEN_CARROT, Material.GOLDEN_APPLE));
   RESOURCE_CATEGORIES.put("misc", List.of(Material.EXPERIENCE_BOTTLE, Material.ARROW, Material.TORCH, Material.PAPER, Material.BOOK, Material.LEAD, Material.BUCKET, Material.FIREWORK_ROCKET, Material.TNT, Material.SLIME_BLOCK, Material.HONEY_BLOCK, Material.COBWEB, Material.ENDER_EYE, Material.DRAGON_BREATH, Material.TRIAL_KEY, Material.SPONGE));
   MERCHANT_ITEM_POOL.put(Material.ELYTRA, 8000.0);
   MERCHANT_ITEM_POOL.put(Material.TOTEM_OF_UNDYING, 6000.0);
   MERCHANT_ITEM_POOL.put(Material.NETHERITE_INGOT, 4000.0);
   MERCHANT_ITEM_POOL.put(Material.NETHERITE_BLOCK, 30000.0);
   MERCHANT_ITEM_POOL.put(Material.ENCHANTED_GOLDEN_APPLE, 5000.0);
   MERCHANT_ITEM_POOL.put(Material.SADDLE, 800.0);
   MERCHANT_ITEM_POOL.put(Material.NAME_TAG, 600.0);
   MERCHANT_ITEM_POOL.put(Material.SHULKER_BOX, 3000.0);
   MERCHANT_ITEM_POOL.put(Material.TRIDENT, 3500.0);
   MERCHANT_ITEM_POOL.put(Material.NETHER_STAR, 4500.0);
   MERCHANT_ITEM_POOL.put(Material.DRAGON_EGG, 50000.0);
   MERCHANT_ITEM_POOL.put(Material.BEACON, 8000.0);
   MERCHANT_ITEM_POOL.put(Material.DIAMOND_BLOCK, 2200.0);
   MERCHANT_ITEM_POOL.put(Material.EMERALD_BLOCK, 1300.0);
   MERCHANT_ITEM_POOL.put(Material.MUSIC_DISC_PIGSTEP, 1500.0);
   MERCHANT_ITEM_POOL.put(Material.MUSIC_DISC_OTHERSIDE, 1500.0);
   MERCHANT_ITEM_POOL.put(Material.HEART_OF_THE_SEA, 2500.0);
   MERCHANT_ITEM_POOL.put(Material.CONDUIT, 4000.0);
   MERCHANT_ITEM_POOL.put(Material.END_CRYSTAL, 2000.0);
   MERCHANT_ITEM_POOL.put(Material.GOLDEN_CARROT, 200.0);
   VIP_SHOP_POOL.put(Material.ELYTRA, 8000.0);
   VIP_SHOP_POOL.put(Material.TOTEM_OF_UNDYING, 6000.0);
   VIP_SHOP_POOL.put(Material.NETHERITE_INGOT, 4000.0);
   VIP_SHOP_POOL.put(Material.NETHERITE_BLOCK, 30000.0);
   VIP_SHOP_POOL.put(Material.ENCHANTED_GOLDEN_APPLE, 5000.0);
   VIP_SHOP_POOL.put(Material.NETHERITE_SCRAP, 2000.0);
   VIP_SHOP_POOL.put(Material.SHULKER_BOX, 3000.0);
   VIP_SHOP_POOL.put(Material.TRIDENT, 3500.0);
   VIP_SHOP_POOL.put(Material.NETHER_STAR, 4500.0);
   VIP_SHOP_POOL.put(Material.BEACON, 8000.0);
   VIP_SHOP_POOL.put(Material.DIAMOND_BLOCK, 2200.0);
   VIP_SHOP_POOL.put(Material.EMERALD_BLOCK, 1300.0);
   VIP_SHOP_POOL.put(Material.HEART_OF_THE_SEA, 2500.0);
   VIP_SHOP_POOL.put(Material.CONDUIT, 4000.0);
   VIP_SHOP_POOL.put(Material.GOLDEN_APPLE, 800.0);
   }
}
