package kot0328.minecraftBank;

import java.util.UUID;

class StandingTransfer {
   String id = UUID.randomUUID().toString();
   UUID from;
   UUID to;
   double amount;
   int intervalHours;
   long nextAt;
   int fails;

   String serialize() {
      return this.from + "|" + this.to + "|" + this.amount + "|" + this.intervalHours + "|" + this.nextAt + "|" + this.fails;
   }

   static StandingTransfer parse(String raw) {
      String[] a = raw.split("\\|");
      if (a.length < 6) {
         return null;
      } else {
         try {
            StandingTransfer st = new StandingTransfer();
            st.from = UUID.fromString(a[0]);
            st.to = UUID.fromString(a[1]);
            st.amount = Double.parseDouble(a[2]);
            st.intervalHours = Math.max(1, Integer.parseInt(a[3]));
            st.nextAt = Long.parseLong(a[4]);
            st.fails = Integer.parseInt(a[5]);
            return Double.isFinite(st.amount) && st.amount > 0.0 ? st : null;
         } catch (IllegalArgumentException ex) {
            return null;
         }
      }
   }
}
