package kot0328.minecraftBank;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CatalogAndMessagesTest {
   /** Sales tax keeps a small gap from being worth exploiting, so allow only this much. */
   private static final double TAX = 0.05;
   private static final Map<String, String> currencies = new HashMap<>();
   private static final LinkedHashMap<Material, Double> prices = new LinkedHashMap<>();
   private static final Map<String, List<Material>> categories = new LinkedHashMap<>();
   private static final LinkedHashMap<Material, Double> merchant = new LinkedHashMap<>();
   private static final LinkedHashMap<Material, Double> vip = new LinkedHashMap<>();

   @BeforeAll
   static void load() {
      ResourceCatalog.register(currencies, prices, categories, merchant, vip);
   }

   private static double p(Material m) {
      Double v = prices.get(m);
      assertNotNull(v, m + " missing from the resource shop");
      return v;
   }

   @Test
   void everyCategoryItemIsPricedAndFitsTheGui() {
      assertTrue(prices.size() >= 150, "resource shop has " + prices.size() + " items");
      for (Map.Entry<String, List<Material>> cat : categories.entrySet()) {
         assertTrue(cat.getValue().size() <= 36, cat.getKey() + " has more than 36 items (list GUI holds 36)");
         for (Material m : cat.getValue()) {
            assertTrue(p(m) > 0.0, m + " must have a positive price");
         }
      }
      long listed = categories.values().stream().mapToLong(List::size).sum();
      assertEquals(prices.size(), listed, "every priced item should appear in exactly one category");
   }

   /** Buying the ingredients, crafting/smelting and selling the result must not make money. */
   @Test
   void craftingAndSmeltingDoNotCreateMoney() {
      assertNoProfit(p(Material.HAY_BLOCK), 9 * p(Material.WHEAT), "wheat -> hay block");
      assertNoProfit(9 * p(Material.WHEAT), p(Material.HAY_BLOCK), "hay block -> wheat");
      assertNoProfit(p(Material.SLIME_BLOCK), 9 * p(Material.SLIME_BALL), "slime -> block");
      assertNoProfit(9 * p(Material.SLIME_BALL), p(Material.SLIME_BLOCK), "block -> slime");
      assertNoProfit(p(Material.IRON_INGOT), 9 * p(Material.IRON_NUGGET), "nuggets -> iron ingot");
      assertNoProfit(9 * p(Material.IRON_NUGGET), p(Material.IRON_INGOT), "iron ingot -> nuggets");
      assertNoProfit(p(Material.GOLD_INGOT), 9 * p(Material.GOLD_NUGGET), "nuggets -> gold ingot");
      assertNoProfit(9 * p(Material.GOLD_NUGGET), p(Material.GOLD_INGOT), "gold ingot -> nuggets");
      assertNoProfit(p(Material.IRON_INGOT), p(Material.RAW_IRON), "smelt raw iron");
      assertNoProfit(p(Material.GOLD_INGOT), p(Material.RAW_GOLD), "smelt raw gold");
      assertNoProfit(p(Material.COPPER_INGOT), p(Material.RAW_COPPER), "smelt raw copper");
      assertNoProfit(p(Material.GLASS), p(Material.SAND), "smelt sand");
      assertNoProfit(p(Material.STONE), p(Material.COBBLESTONE), "smelt cobblestone");
      assertNoProfit(p(Material.DEEPSLATE), p(Material.COBBLED_DEEPSLATE), "smelt cobbled deepslate");
      assertNoProfit(p(Material.COOKED_BEEF), p(Material.BEEF), "cook beef");
      assertNoProfit(p(Material.COOKED_PORKCHOP), p(Material.PORKCHOP), "cook porkchop");
      assertNoProfit(p(Material.COOKED_CHICKEN), p(Material.CHICKEN), "cook chicken");
      assertNoProfit(p(Material.COOKED_MUTTON), p(Material.MUTTON), "cook mutton");
      assertNoProfit(p(Material.COOKED_COD), p(Material.COD), "cook cod");
      assertNoProfit(p(Material.COOKED_SALMON), p(Material.SALMON), "cook salmon");
      assertNoProfit(p(Material.BAKED_POTATO), p(Material.POTATO), "bake potato");
      // log -> charcoal (smelt) + stick -> 4 torches
      assertNoProfit(4 * p(Material.TORCH), p(Material.OAK_LOG) + p(Material.STICK), "charcoal torches");
      assertNoProfit(4 * p(Material.TORCH), p(Material.COAL) + p(Material.STICK), "coal torches");
      assertNoProfit(p(Material.BREAD), 3 * p(Material.WHEAT), "bread");
      assertNoProfit(p(Material.GOLDEN_APPLE), 8 * p(Material.GOLD_INGOT) + p(Material.APPLE), "golden apple");
      assertNoProfit(p(Material.GOLDEN_CARROT), 8 * p(Material.GOLD_NUGGET) + p(Material.CARROT), "golden carrot");
      assertNoProfit(p(Material.TNT), 5 * p(Material.GUNPOWDER) + 4 * p(Material.SAND), "tnt");
      assertNoProfit(p(Material.ENDER_EYE), p(Material.ENDER_PEARL) + p(Material.BLAZE_POWDER), "ender eye");
      assertNoProfit(2 * p(Material.BLAZE_POWDER), p(Material.BLAZE_ROD), "blaze powder");
      assertNoProfit(p(Material.GLOWSTONE), 4 * p(Material.GLOWSTONE_DUST), "glowstone");
      assertNoProfit(p(Material.QUARTZ_BLOCK), 4 * p(Material.QUARTZ), "quartz block");
      assertNoProfit(p(Material.NETHERITE_INGOT), 4 * p(Material.NETHERITE_SCRAP) + 4 * p(Material.GOLD_INGOT), "netherite ingot");
      assertNoProfit(4 * p(Material.OAK_PLANKS), p(Material.OAK_LOG), "planks");
      assertNoProfit(3 * p(Material.PAPER), 3 * p(Material.SUGAR_CANE), "paper");
      assertNoProfit(p(Material.SUGAR), p(Material.SUGAR_CANE), "sugar");
      assertNoProfit(p(Material.BOOK), 3 * p(Material.PAPER) + p(Material.LEATHER), "book");
   }

   private static void assertNoProfit(double sellValue, double ingredientCost, String what) {
      assertTrue(sellValue * (1.0 - TAX) <= ingredientCost + 1.0E-9, what + ": sells for " + sellValue + " but ingredients cost " + ingredientCost);
   }

   @Test
   void defaultMessagesParseAsMiniMessage() {
      LinkedHashMap<String, String> messages = new LinkedHashMap<>();
      DefaultMessages.register(messages);
      assertTrue(messages.size() > 400, "only " + messages.size() + " messages");
      MiniMessage mm = MiniMessage.miniMessage();
      for (Map.Entry<String, String> e : messages.entrySet()) {
         assertFalse(e.getValue().isBlank(), e.getKey() + " is blank");
         assertDoesNotThrow(() -> mm.deserialize(e.getValue()), e.getKey());
         assertEquals(count(e.getValue(), '{'), count(e.getValue(), '}'), e.getKey() + " has unbalanced placeholders");
      }
   }

   @Test
   void legacyHashesNeverMatchTheCurrentDefault() {
      LinkedHashMap<String, String> messages = new LinkedHashMap<>();
      DefaultMessages.register(messages);
      HashMap<String, int[]> legacy = new HashMap<>();
      DefaultMessages.registerLegacyHashes(legacy);
      for (Map.Entry<String, int[]> e : legacy.entrySet()) {
         String current = messages.get(e.getKey());
         assertNotNull(current, "legacy hash for unknown message " + e.getKey());
         int hash = current.hashCode();
         assertFalse(Arrays.stream(e.getValue()).anyMatch(h -> h == hash), e.getKey() + ": current default is listed as legacy");
      }
   }

   @Test
   void standingTransferRoundTrips() {
      StandingTransfer st = new StandingTransfer();
      st.from = UUID.randomUUID();
      st.to = UUID.randomUUID();
      st.amount = 1234.5;
      st.intervalHours = 24;
      st.nextAt = 99L;
      st.fails = 2;
      StandingTransfer back = StandingTransfer.parse(st.serialize());
      assertNotNull(back);
      assertEquals(st.from, back.from);
      assertEquals(st.to, back.to);
      assertEquals(1234.5, back.amount);
      assertEquals(24, back.intervalHours);
      assertEquals(99L, back.nextAt);
      assertEquals(2, back.fails);
      assertEquals(null, StandingTransfer.parse("garbage"));
      assertEquals(null, StandingTransfer.parse(st.from + "|" + st.to + "|NaN|24|99|0"));
   }

   private static long count(String s, char c) {
      return s.chars().filter(ch -> ch == c).count();
   }
}
