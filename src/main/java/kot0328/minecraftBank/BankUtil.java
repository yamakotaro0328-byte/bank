package kot0328.minecraftBank;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/** Stateless helpers shared by the plugin (and covered by unit tests). */
final class BankUtil {
   private BankUtil() {
   }

   static String ledgerSourceLabel(String raw) {
      String method = raw == null ? "?" : raw;
      int colon = method.indexOf(58);
      if (colon >= 0) {
         method = method.substring(0, colon);
      }

      if (method.startsWith("lambda$")) {
         method = method.substring(7);
         int dollar = method.indexOf(36);
         if (dollar >= 0) {
            method = method.substring(0, dollar);
         }
      }

      return switch (method) {
         case "executeResourceSell" -> "資源ショップ売却";
         case "executeResourceBuy" -> "資源ショップ購入";
         case "finalizeAuctionSale" -> "オークション売上";
         case "processWealthTax" -> "資産税";
         case "processStandingTransfers" -> "定期送金";
         case "onPlayerInteractTreasureChest" -> "埋蔵金";
         case "checkTreasureLocationPaid" -> "埋蔵金の位置調査";
         case "executeTradeSession" -> "プレイヤー間取引";
         case "executeMerchantPurchase" -> "行商人";
         case "fireEconomyEvent" -> "経済イベント";
         case "payWorldStockDividends" -> "世界株の配当";
         case "onCommand" -> "コマンド操作";
         case "onInventoryClick" -> "GUI操作";
         case "onPlayerChatInput" -> "チャット入力操作";
         case "handleMyActionOnMainThread" -> "Web操作";
         default -> method;
      };
   }

   static String plainNumber(double v) {
      return java.math.BigDecimal.valueOf(Math.floor(v * 100.0) / 100.0).stripTrailingZeros().toPlainString();
   }

   static double parseFiniteDouble(String raw) {
      double v = Double.parseDouble(raw.trim());
      if (!Double.isFinite(v)) {
         throw new NumberFormatException("not a finite number: " + raw);
      }

      return v;
   }

   static long parseLongOr(String raw, long fallback) {
      try {
         return Long.parseLong(raw.trim());
      } catch (NumberFormatException ex) {
         return fallback;
      }
   }

   static String percentText(double rate) {
      double pct = rate * 100.0;
      return (pct == Math.floor(pct) ? String.valueOf((long)pct) : String.format("%.2f", pct).replaceAll("0+$", "")) + "%";
   }

   static String sparkline(List<Double> vals) {
      if (vals.size() < 2) {
         return "<dark_gray>(データ収集中)</dark_gray>";
      } else {
         double min = Collections.min(vals);
         double max = Collections.max(vals);
         String bars = "▁▂▃▄▅▆▇█";
         StringBuilder sb = new StringBuilder();

         for (double v : vals) {
            int idx = max - min < 1.0E-9 ? 3 : (int)Math.round((v - min) / (max - min) * 7.0);
            sb.append(bars.charAt(Math.max(0, Math.min(7, idx))));
         }

         String color = vals.get(vals.size() - 1) >= vals.get(0) ? "green" : "red";
         return "<" + color + ">" + sb + "</" + color + ">";
      }
   }

   static String chartSummary(List<Double> vals, java.util.function.Function<Double, String> fmt) {
      double first = vals.get(0);
      double last = vals.get(vals.size() - 1);
      double change = first > 0.0 ? (last - first) / first * 100.0 : 0.0;
      return "最高 "
         + fmt.apply(Collections.max(vals))
         + " / 最安 "
         + fmt.apply(Collections.min(vals))
         + " / 変動 "
         + (change >= 0.0 ? "+" : "")
         + String.format("%.2f", change)
         + "%";
   }

   static boolean isDailyCounterResetDue(UUID u, HashMap<UUID, Long> resetAtMap, long now) {
      long resetAt = resetAtMap.getOrDefault(u, 0L);
      if (now >= resetAt) {
         resetAtMap.put(u, now + 86400000L);
         return true;
      } else {
         return false;
      }
   }

   static String jsonEscape(String s) {
      if (s == null) {
         return "";
      } else {
         StringBuilder sb = new StringBuilder(s.length() + 8);

         for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
               case '"' -> sb.append("\\\"");
               case '\\' -> sb.append("\\\\");
               case '\n' -> sb.append("\\n");
               case '\r' -> sb.append("\\r");
               case '\t' -> sb.append("\\t");
               default -> {
                  if (c < ' ' || c == '\u2028' || c == '\u2029') {
                     sb.append(String.format("\\u%04x", (int)c));
                  } else {
                     sb.append(c);
                  }
               }
            }
         }

         return sb.toString();
      }
   }

   static byte[] pbkdf2(String password, byte[] salt, int iterations) {
      try {
         javax.crypto.SecretKeyFactory f = javax.crypto.SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
         return f.generateSecret(new javax.crypto.spec.PBEKeySpec(password.toCharArray(), salt, iterations, 256)).getEncoded();
      } catch (Exception e) {
         throw new IllegalStateException(e);
      }
   }
}
