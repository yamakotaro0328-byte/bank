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
