package kot0328.minecraftBank;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

/** A payment request from one player (requester) to another (payer). */
class Invoice {
   String id = UUID.randomUUID().toString();
   UUID from;
   UUID to;
   double amount;
   String memo = "";
   long createdAt;
   long expiresAt;

   String serialize() {
      return this.from + "|" + this.to + "|" + this.amount + "|" + this.createdAt + "|" + this.expiresAt + "|"
         + Base64.getEncoder().encodeToString(this.memo.getBytes(StandardCharsets.UTF_8));
   }

   static Invoice parse(String id, String raw) {
      String[] a = raw.split("\\|", -1);
      if (a.length < 6) {
         return null;
      } else {
         try {
            Invoice inv = new Invoice();
            inv.id = id;
            inv.from = UUID.fromString(a[0]);
            inv.to = UUID.fromString(a[1]);
            inv.amount = Double.parseDouble(a[2]);
            inv.createdAt = Long.parseLong(a[3]);
            inv.expiresAt = Long.parseLong(a[4]);
            inv.memo = new String(Base64.getDecoder().decode(a[5]), StandardCharsets.UTF_8);
            return Double.isFinite(inv.amount) && inv.amount > 0.0 ? inv : null;
         } catch (IllegalArgumentException ex) {
            return null;
         }
      }
   }
}
