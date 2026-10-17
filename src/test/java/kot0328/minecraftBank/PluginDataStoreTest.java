package kot0328.minecraftBank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class PluginDataStoreTest {
   private static final Logger LOG = Logger.getLogger("test");

   @TempDir
   File dir;

   private PluginDataStore open() throws Exception {
      return new PluginDataStore(new File(this.dir, "data.db"), LOG);
   }

   @Test
   void savesOnlyDifferencesAndSurvivesReopen() throws Exception {
      PluginDataStore db = this.open();
      db.beginTransaction();
      for (int i = 0; i < 100; i++) {
         db.setDouble("personal", "u" + i, i);
      }
      db.endTransaction();

      // the usual saveData pattern: wipe the section and write everything again
      db.beginTransaction();
      db.removeSection("personal");
      for (int i = 0; i < 100; i += 2) {
         db.setDouble("personal", "u" + i, i * 10.0);
      }
      db.endTransaction();
      db.close();

      PluginDataStore again = this.open();
      assertEquals(50, again.getKeys("personal").size());
      assertEquals(40.0, again.getDouble("personal", "u4", -1.0));
      assertEquals(-1.0, again.getDouble("personal", "u3", -1.0));
      again.close();
   }

   @Test
   void rollbackDiscardsUncommittedChanges() throws Exception {
      PluginDataStore db = this.open();
      db.setString("s", "a", "1");
      db.beginTransaction();
      db.removeSection("s");
      db.setString("s", "b", "2");
      db.rollbackTransaction();
      assertEquals("1", db.getString("s", "a", null));
      assertEquals(null, db.getString("s", "b", null));
      db.close();

      PluginDataStore again = this.open();
      assertEquals("1", again.getString("s", "a", null));
      again.close();
   }

   @Test
   void prefixedKeysAndListsRoundTrip() throws Exception {
      PluginDataStore db = this.open();
      db.setString("storage_items", "p1.0", "x");
      db.setString("storage_items", "p1.1", "y");
      db.setString("storage_items", "p2.0", "z");
      db.setStringList("logs", "p1", List.of("a", "b\tc", ""));
      assertEquals(Set.of("p1", "p2"), db.getKeys("storage_items"));
      assertEquals(Set.of("0", "1"), db.getKeys("storage_items", "p1"));
      db.removeByPathPrefix("storage_items", "p1");
      db.close();

      PluginDataStore again = this.open();
      assertEquals(Set.of("p2"), again.getKeys("storage_items"));
      assertEquals(List.of("a", "b\tc", ""), again.getStringList("logs", "p1"));
      again.close();
   }

   @Test
   void ledgerRowsCanBeQueried() throws Exception {
      PluginDataStore db = this.open();
      db.ledgerAppend(1000L, "p1", 500.0, "executeResourceSell:1");
      db.ledgerAppend(2000L, "p1", -200.0, "processWealthTax:2");
      db.ledgerAppend(3000L, "p2", 50.0, "onCommand:3");
      List<Object[]> rows = db.queryAsync("SELECT COUNT(*), SUM(delta) FROM {ledger} WHERE player = ?", "p1").get(10L, TimeUnit.SECONDS);
      assertEquals(2L, ((Number)rows.get(0)[0]).longValue());
      assertEquals(300.0, ((Number)rows.get(0)[1]).doubleValue(), 1.0E-9);

      db.pruneLedgerAsync(2500L);
      List<Object[]> left = db.queryAsync("SELECT COUNT(*) FROM {ledger}").get(10L, TimeUnit.SECONDS);
      assertEquals(1L, ((Number)left.get(0)[0]).longValue());
      db.close();
   }

   @Test
   void writesOutsideTransactionsArePersistedImmediately() throws Exception {
      PluginDataStore db = this.open();
      db.setInt("counter", "_", 7);
      db.setBoolean("flag", "_", true);
      db.close();

      PluginDataStore again = this.open();
      assertEquals(7, again.getInt("counter", "_", 0));
      assertTrue(again.getBoolean("flag", "_", false));
      assertFalse(again.isMySql());
      again.close();
   }
}
