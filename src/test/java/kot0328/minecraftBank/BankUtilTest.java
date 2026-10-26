package kot0328.minecraftBank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class BankUtilTest {
   @Test
   void parseFiniteDoubleRejectsNonFiniteValues() {
      assertEquals(1234.5, BankUtil.parseFiniteDouble(" 1234.5 "));
      assertThrows(NumberFormatException.class, () -> BankUtil.parseFiniteDouble("NaN"));
      assertThrows(NumberFormatException.class, () -> BankUtil.parseFiniteDouble("Infinity"));
      assertThrows(NumberFormatException.class, () -> BankUtil.parseFiniteDouble("abc"));
   }

   @Test
   void shopSignsAreParsedStrictly() {
      assertEquals(new BankUtil.ShopSign(true, 100.0, 16), BankUtil.parseShopSign("[ショップ]", "販売 100", "16"));
      assertEquals(new BankUtil.ShopSign(false, 1234.5, 1), BankUtil.parseShopSign(" [Shop] ", "buy 1,234.5円", ""));
      assertEquals(new BankUtil.ShopSign(true, 0.01, 64), BankUtil.parseShopSign("[店]", "売 0.019", "64個"));
      assertEquals(null, BankUtil.parseShopSign("ショップ", "販売 100", "1"));
      assertEquals(null, BankUtil.parseShopSign("[ショップ]", "販売 NaN", "1"));
      assertEquals(null, BankUtil.parseShopSign("[ショップ]", "販売 -5", "1"));
      assertEquals(null, BankUtil.parseShopSign("[ショップ]", "交換 100", "1"));
      assertEquals(null, BankUtil.parseShopSign("[ショップ]", "販売 100", "0"));
      assertEquals(null, BankUtil.parseShopSign("[ショップ]", "販売 100", "9999"));
      assertEquals("プレイヤーショップ", BankUtil.ledgerSourceLabel("executeTrade:12"));
   }

   @Test
   void pointRewardsAreValidated() {
      assertEquals(new BankUtil.PointReward("1,000円", 1000.0, "money", "1000"), BankUtil.parsePointReward("1,000円|1000|money|1000"));
      assertEquals(new BankUtil.PointReward("ダイヤ", 500.0, "item", "DIAMOND:2"), BankUtil.parsePointReward("ダイヤ|500|item|DIAMOND:2"));
      assertEquals(new BankUtil.PointReward("称号", 50.0, "command", "lp user {player} parent add vip"), BankUtil.parsePointReward("称号|50|COMMAND|lp user {player} parent add vip"));
      assertEquals(null, BankUtil.parsePointReward("壊れた|abc|money|1"));
      assertEquals(null, BankUtil.parsePointReward("ゼロ|0|money|1"));
      assertEquals(null, BankUtil.parsePointReward("券|10|lottery|500"));
      assertEquals(null, BankUtil.parsePointReward("謎|10|teleport|x"));
      assertEquals(null, BankUtil.parsePointReward("足りない|10|money"));
   }

   @Test
   void percentTextDropsTrailingZeros() {
      assertEquals("5%", BankUtil.percentText(0.05));
      assertEquals("0.5%", BankUtil.percentText(0.005));
      assertEquals("12.5%", BankUtil.percentText(0.125));
   }

   @Test
   void plainNumberIsChatFriendly() {
      assertEquals("1000", BankUtil.plainNumber(1000.0));
      assertEquals("12345.67", BankUtil.plainNumber(12345.678));
      assertEquals("10000000", BankUtil.plainNumber(1.0E7));
   }

   @Test
   void ledgerSourceLabelMapsMethodsAndLambdas() {
      assertEquals("資源ショップ売却", BankUtil.ledgerSourceLabel("executeResourceSell:4190"));
      assertEquals("コマンド操作", BankUtil.ledgerSourceLabel("lambda$onCommand$12:5000"));
      assertEquals("someOtherMethod", BankUtil.ledgerSourceLabel("someOtherMethod:1"));
      assertEquals("?", BankUtil.ledgerSourceLabel(null));
   }

   @Test
   void sparklineScalesBetweenMinAndMaxAndColorsByTrend() {
      String up = BankUtil.sparkline(List.of(1.0, 2.0, 3.0));
      assertTrue(up.startsWith("<green>") && up.contains("▁") && up.contains("█"), up);
      String down = BankUtil.sparkline(List.of(3.0, 1.0));
      assertTrue(down.startsWith("<red>"), down);
      assertTrue(BankUtil.sparkline(List.of(5.0)).contains("データ収集中"));
   }

   @Test
   void chartSummaryReportsChangePercent() {
      String s = BankUtil.chartSummary(List.of(100.0, 150.0, 110.0), v -> String.valueOf(v.intValue()));
      assertTrue(s.contains("最高 150") && s.contains("最安 100") && s.contains("+10.00%"), s);
   }

   @Test
   void dailyCounterResetsOncePerDay() {
      UUID u = UUID.randomUUID();
      HashMap<UUID, Long> resetAt = new HashMap<>();
      long now = 1_000_000L;
      assertTrue(BankUtil.isDailyCounterResetDue(u, resetAt, now));
      assertFalse(BankUtil.isDailyCounterResetDue(u, resetAt, now + 1000L));
      assertTrue(BankUtil.isDailyCounterResetDue(u, resetAt, now + 86_400_000L));
   }
}
