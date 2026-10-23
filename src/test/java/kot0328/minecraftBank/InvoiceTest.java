package kot0328.minecraftBank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class InvoiceTest {
   @Test
   void roundTripKeepsMemoWithSeparators() {
      Invoice inv = new Invoice();
      inv.from = UUID.randomUUID();
      inv.to = UUID.randomUUID();
      inv.amount = 1500.5;
      inv.memo = "ピザ代 | 割り勘 <b>";
      inv.createdAt = 10L;
      inv.expiresAt = 20L;
      Invoice back = Invoice.parse(inv.id, inv.serialize());
      assertNotNull(back);
      assertEquals(inv.id, back.id);
      assertEquals(inv.from, back.from);
      assertEquals(inv.to, back.to);
      assertEquals(1500.5, back.amount);
      assertEquals("ピザ代 | 割り勘 <b>", back.memo);
      assertEquals(20L, back.expiresAt);
   }

   @Test
   void rejectsBrokenOrNonPositiveData() {
      assertNull(Invoice.parse("x", "garbage"));
      UUID a = UUID.randomUUID();
      assertNull(Invoice.parse("x", a + "|" + a + "|-5|0|0|"));
      assertNull(Invoice.parse("x", a + "|" + a + "|NaN|0|0|"));
   }
}
