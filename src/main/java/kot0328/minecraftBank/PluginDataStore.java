package kot0328.minecraftBank;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

final class PluginDataStore {
   private Connection conn;
   private final java.util.logging.Logger log;
   private final Map<String, Map<String, String>> mirror = new HashMap<>();
   private final Map<String, Map<String, String>> persisted = new HashMap<>();
   private final Set<String> touched = new HashSet<>();
   private boolean inTransaction = false;
   private final java.util.concurrent.ConcurrentLinkedQueue<String[]> retryOps = new java.util.concurrent.ConcurrentLinkedQueue<>();
   private final java.util.concurrent.ConcurrentLinkedQueue<Object[]> ledgerQueue = new java.util.concurrent.ConcurrentLinkedQueue<>();
   private final java.util.concurrent.ExecutorService writer = java.util.concurrent.Executors.newSingleThreadExecutor(r -> {
      Thread t = new Thread(r, "MinecraftBank-DB");
      t.setDaemon(true);
      return t;
   });

   private final String url;
   private final String user;
   private final String password;
   private final boolean mysql;
   private final String dataTable;
   private final String ledgerTable;
   private final boolean hadDataAtStartup;
   private volatile String syncOrigin = null;
   private volatile long lastPullAt = 0L;
   private final Set<String> pushedSincePull = new HashSet<>();

   PluginDataStore(File dbFile, java.util.logging.Logger log) throws SQLException {
      this("jdbc:sqlite:" + dbFile.getAbsolutePath(), null, null, false, "", log, null);
   }

   static PluginDataStore mysql(
      String host, int port, String database, String user, String password, String prefix, boolean ssl, java.util.logging.Logger log, File importFrom
   ) throws SQLException {
      String url = "jdbc:mysql://"
         + host
         + ":"
         + port
         + "/"
         + database
         + "?useSSL="
         + ssl
         + "&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8&connectTimeout=10000&socketTimeout=60000";
      return new PluginDataStore(url, user, password, true, prefix, log, importFrom);
   }

   private PluginDataStore(String url, String user, String password, boolean mysql, String prefix, java.util.logging.Logger log, File importFrom) throws SQLException {
      this.log = log;
      this.url = url;
      this.user = user;
      this.password = password;
      this.mysql = mysql;
      String safePrefix = prefix == null ? "" : prefix.replaceAll("[^A-Za-z0-9_]", "");
      this.dataTable = safePrefix + "plugin_data";
      this.ledgerTable = safePrefix + "ledger";

      try {
         Class.forName(mysql ? "com.mysql.cj.jdbc.Driver" : "org.sqlite.JDBC");
      } catch (ClassNotFoundException var13) {
      }

      this.conn = this.openConnection();

      try (Statement st = this.conn.createStatement()) {
         if (mysql) {
            st.execute(
               "CREATE TABLE IF NOT EXISTS "
                  + this.dataTable
                  + " (section VARCHAR(191) NOT NULL, path VARCHAR(255) NOT NULL, value LONGTEXT, PRIMARY KEY (section, path)) DEFAULT CHARSET=utf8mb4"
            );
            st.execute(
               "CREATE TABLE IF NOT EXISTS "
                  + this.ledgerTable
                  + " (id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, ts BIGINT NOT NULL, player VARCHAR(36) NOT NULL, delta DOUBLE NOT NULL, source VARCHAR(191), INDEX idx_ledger_player (player, id), INDEX idx_ledger_ts (ts)) DEFAULT CHARSET=utf8mb4"
            );
         } else {
            st.execute("CREATE TABLE IF NOT EXISTS plugin_data (section TEXT NOT NULL, path TEXT NOT NULL, value TEXT, PRIMARY KEY (section, path))");
            st.execute("CREATE INDEX IF NOT EXISTS idx_plugin_data_section ON plugin_data(section)");
            st.execute("CREATE TABLE IF NOT EXISTS ledger (id INTEGER PRIMARY KEY AUTOINCREMENT, ts INTEGER NOT NULL, player TEXT NOT NULL, delta REAL NOT NULL, source TEXT)");
            st.execute("CREATE INDEX IF NOT EXISTS idx_ledger_player ON ledger(player, id)");
            st.execute("CREATE INDEX IF NOT EXISTS idx_ledger_ts ON ledger(ts)");
         }

         if (mysql && importFrom != null && importFrom.exists() && this.isTableEmpty(st)) {
            this.importFromSqlite(importFrom);
         }

         try (ResultSet rs = st.executeQuery("SELECT section, path, value FROM " + this.dataTable + (mysql ? "" : " ORDER BY rowid"))) {
            while (rs.next()) {
               String value = rs.getString(3);
               if (value != null) {
                  this.mirror.computeIfAbsent(rs.getString(1), k -> new LinkedHashMap<>()).put(rs.getString(2), value);
                  this.persisted.computeIfAbsent(rs.getString(1), k -> new LinkedHashMap<>()).put(rs.getString(2), value);
               }
            }
         }
      }

      this.hadDataAtStartup = !this.mirror.isEmpty();
   }

   private Connection openConnection() throws SQLException {
      return this.user == null ? DriverManager.getConnection(this.url) : DriverManager.getConnection(this.url, this.user, this.password);
   }

   private void ensureConnection() throws SQLException {
      boolean valid;
      try {
         valid = this.conn != null && this.conn.isValid(3);
      } catch (SQLException var3) {
         valid = false;
      }

      if (!valid) {
         try {
            if (this.conn != null) {
               this.conn.close();
            }
         } catch (SQLException var2) {
         }

         this.conn = this.openConnection();
         this.log.info("[データベース] 接続を再確立しました。");
      }
   }

   private boolean isTableEmpty(Statement st) throws SQLException {
      try (ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM " + this.dataTable)) {
         return rs.next() && rs.getLong(1) == 0L;
      }
   }

   private void importFromSqlite(File sqliteFile) throws SQLException {
      int rows = 0;
      int ledgerRows = 0;

      try (Connection src = DriverManager.getConnection("jdbc:sqlite:" + sqliteFile.getAbsolutePath())) {
         this.conn.setAutoCommit(false);

         try (
            Statement read = src.createStatement();
            ResultSet rs = read.executeQuery("SELECT section, path, value FROM plugin_data");
            PreparedStatement ins = this.conn.prepareStatement("REPLACE INTO " + this.dataTable + "(section, path, value) VALUES (?, ?, ?)");
         ) {
            while (rs.next()) {
               ins.setString(1, rs.getString(1));
               ins.setString(2, rs.getString(2));
               ins.setString(3, rs.getString(3));
               ins.addBatch();
               if (++rows % 500 == 0) {
                  ins.executeBatch();
               }
            }

            ins.executeBatch();
         }

         try (
            Statement read = src.createStatement();
            ResultSet rs = read.executeQuery("SELECT ts, player, delta, source FROM ledger");
            PreparedStatement ins = this.conn.prepareStatement("INSERT INTO " + this.ledgerTable + "(ts, player, delta, source) VALUES (?, ?, ?, ?)");
         ) {
            while (rs.next()) {
               ins.setLong(1, rs.getLong(1));
               ins.setString(2, rs.getString(2));
               ins.setDouble(3, rs.getDouble(3));
               ins.setString(4, rs.getString(4));
               ins.addBatch();
               if (++ledgerRows % 500 == 0) {
                  ins.executeBatch();
               }
            }

            ins.executeBatch();
         } catch (SQLException var14) {
         }

         this.conn.commit();
         this.log.info("[MySQL移行] data.db(SQLite) から " + rows + " 件のデータと " + ledgerRows + " 件の台帳をMySQLへ移しました。");
      } catch (SQLException e) {
         this.conn.rollback();
         throw e;
      } finally {
         this.conn.setAutoCommit(true);
      }
   }

   boolean isMySql() {
      return this.mysql;
   }

   private String counterTable() {
      return this.dataTable.substring(0, this.dataTable.length() - "plugin_data".length()) + "counters";
   }

   void enableSync(String origin) throws SQLException {
      if (!this.mysql) {
         throw new SQLException("サーバー間同期はMySQLでのみ使えます");
      } else {
         DatabaseMetaData meta = this.conn.getMetaData();
         boolean hasUpdated;
         try (ResultSet rs = meta.getColumns(this.conn.getCatalog(), null, this.dataTable, "updated_at")) {
            hasUpdated = rs.next();
         }

         try (Statement st = this.conn.createStatement()) {
            if (!hasUpdated) {
               st.execute("ALTER TABLE " + this.dataTable + " ADD COLUMN updated_at BIGINT NOT NULL DEFAULT 0, ADD COLUMN origin VARCHAR(64) NOT NULL DEFAULT ''");
               st.execute("CREATE INDEX idx_" + this.dataTable + "_updated ON " + this.dataTable + "(updated_at)");
            }

            st.execute("CREATE TABLE IF NOT EXISTS " + this.counterTable() + " (name VARCHAR(191) NOT NULL PRIMARY KEY, value DOUBLE NOT NULL) DEFAULT CHARSET=utf8mb4");
            boolean counterHasUpdated;
            try (ResultSet rs = meta.getColumns(this.conn.getCatalog(), null, this.counterTable(), "updated_at")) {
               counterHasUpdated = rs.next();
            }

            if (!counterHasUpdated) {
               st.execute("ALTER TABLE " + this.counterTable() + " MODIFY name VARCHAR(191) NOT NULL, ADD COLUMN updated_at BIGINT NOT NULL DEFAULT 0, ADD COLUMN origin VARCHAR(64) NOT NULL DEFAULT ''");
               st.execute("CREATE INDEX idx_" + this.counterTable() + "_updated ON " + this.counterTable() + "(updated_at)");
            }

            try (ResultSet rs = st.executeQuery("SELECT ROUND(UNIX_TIMESTAMP(CURRENT_TIMESTAMP(3)) * 1000)")) {
               this.lastPullAt = rs.next() ? rs.getLong(1) : 0L;
            }
         }

         this.syncOrigin = origin;
      }
   }

   private volatile long lastKeyedPullAt = 0L;

   /** initial: seeds only fill missing rows and take the shared total; later seeds are added, since the shared total never included them. */
   CompletableFuture<Map<String, Double>> syncKeyedAsync(Map<String, Double> seeds, Map<String, Double> deltas, boolean initial) {
      return CompletableFuture.supplyAsync(() -> {
         Map<String, Double> totals = new HashMap<>();
         String nowExpr = "ROUND(UNIX_TIMESTAMP(CURRENT_TIMESTAMP(3)) * 1000)";

         try {
            this.ensureConnection();
            if (this.lastKeyedPullAt == 0L) {
               this.lastKeyedPullAt = this.lastPullAt;
            }

            this.conn.setAutoCommit(false);

            try (
               PreparedStatement seed = this.conn
                  .prepareStatement("INSERT IGNORE INTO " + this.counterTable() + "(name, value, updated_at, origin) VALUES (?, ?, " + nowExpr + ", ?)");
               PreparedStatement add = this.conn
                  .prepareStatement(
                     "INSERT INTO "
                        + this.counterTable()
                        + "(name, value, updated_at, origin) VALUES (?, ?, "
                        + nowExpr
                        + ", ?) ON DUPLICATE KEY UPDATE value = value + ?, updated_at = VALUES(updated_at), origin = VALUES(origin)"
                  );
            ) {
               if (initial) {
                  for (Entry<String, Double> en : seeds.entrySet()) {
                     seed.setString(1, en.getKey());
                     seed.setDouble(2, en.getValue());
                     seed.setString(3, this.syncOrigin);
                     seed.addBatch();
                  }

                  seed.executeBatch();
               }

               Map<String, Double> adds = new HashMap<>(deltas);
               if (!initial) {
                  adds.putAll(seeds);
               }

               for (Entry<String, Double> en : adds.entrySet()) {
                  add.setString(1, en.getKey());
                  add.setDouble(2, en.getValue());
                  add.setString(3, this.syncOrigin);
                  add.setDouble(4, en.getValue());
                  add.addBatch();
               }

               add.executeBatch();
            }

            // read the totals inside the transaction: if this fails nothing is committed and the deltas are resent once
            List<String> seedKeys = new ArrayList<>(seeds.keySet());

            for (int i = 0; i < seedKeys.size(); i += 500) {
               List<String> chunk = seedKeys.subList(i, Math.min(seedKeys.size(), i + 500));
               String marks = String.join(",", Collections.nCopies(chunk.size(), "?"));

               try (PreparedStatement sel = this.conn.prepareStatement("SELECT name, value FROM " + this.counterTable() + " WHERE name IN (" + marks + ")")) {
                  for (int j = 0; j < chunk.size(); j++) {
                     sel.setString(j + 1, chunk.get(j));
                  }

                  try (ResultSet rs = sel.executeQuery()) {
                     while (rs.next()) {
                        totals.put(rs.getString(1), rs.getDouble(2));
                     }
                  }
               }
            }

            try (PreparedStatement sel = this.conn
                  .prepareStatement("SELECT name, value, updated_at FROM " + this.counterTable() + " WHERE updated_at > ? AND name LIKE '%:%'")) {
               sel.setLong(1, this.lastKeyedPullAt - 5000L);

               long max = this.lastKeyedPullAt;

               try (ResultSet rs = sel.executeQuery()) {
                  while (rs.next()) {
                     totals.put(rs.getString(1), rs.getDouble(2));
                     max = Math.max(max, rs.getLong(3));
                  }
               }

               this.conn.commit();
               this.lastKeyedPullAt = max;
            }

            return totals;
         } catch (SQLException e) {
            try {
               this.conn.rollback();
            } catch (SQLException var10) {
            }

            throw new RuntimeException(e);
         } finally {
            try {
               this.conn.setAutoCommit(true);
            } catch (SQLException var9) {
            }
         }
      }, this.writer);
   }

   boolean isSyncEnabled() {
      return this.syncOrigin != null;
   }

   CompletableFuture<List<String[]>> pullRemoteAsync() {
      synchronized (this) {
         this.pushedSincePull.clear();
      }

      return CompletableFuture.supplyAsync(() -> {
         List<String[]> rows = new ArrayList<>();

         try {
            this.ensureConnection();

            try (PreparedStatement ps = this.conn
                  .prepareStatement("SELECT section, path, value, updated_at FROM " + this.dataTable + " WHERE updated_at > ? AND origin <> ?")) {
               ps.setLong(1, this.lastPullAt - 5000L);
               ps.setString(2, this.syncOrigin);

               try (ResultSet rs = ps.executeQuery()) {
                  long max = this.lastPullAt;

                  while (rs.next()) {
                     rows.add(new String[]{rs.getString(1), rs.getString(2), rs.getString(3)});
                     max = Math.max(max, rs.getLong(4));
                  }

                  this.lastPullAt = max;
               }
            }

            return rows;
         } catch (SQLException e) {
            throw new RuntimeException(e);
         }
      }, this.writer);
   }

   synchronized Set<String> applyRemote(List<String[]> rows, Set<String> skipKeys) {
      return this.applyRemote(rows, (section, path) -> skipKeys.contains(section + "\u0000" + path));
   }

   synchronized Set<String> applyRemote(List<String[]> rows, java.util.function.BiPredicate<String, String> skip) {
      Set<String> changed = new HashSet<>();

      for (String[] row : rows) {
         String key = row[0] + "\u0000" + row[1];
         if (!this.pushedSincePull.contains(key) && !skip.test(row[0], row[1])) {
            Map<String, String> m = this.mirror.get(row[0]);
            String cur = m != null ? m.get(row[1]) : null;
            if (!Objects.equals(cur, row[2])) {
               if (row[2] == null) {
                  m.remove(row[1]);
                  Map<String, String> p = this.persisted.get(row[0]);
                  if (p != null) {
                     p.remove(row[1]);
                  }
               } else {
                  this.mirror.computeIfAbsent(row[0], k -> new LinkedHashMap<>()).put(row[1], row[2]);
                  this.persisted.computeIfAbsent(row[0], k -> new LinkedHashMap<>()).put(row[1], row[2]);
               }

               changed.add(row[0]);
            }
         }
      }

      return changed;
   }

   CompletableFuture<double[]> syncCountersAsync(String[] names, double[] localValues, double[] deltas, boolean seed) {
      return CompletableFuture.supplyAsync(() -> {
         double[] totals = new double[names.length];

         try {
            this.ensureConnection();
            this.conn.setAutoCommit(false);

            for (int i = 0; i < names.length; i++) {
               if (seed) {
                  try (PreparedStatement ins = this.conn.prepareStatement("INSERT IGNORE INTO " + this.counterTable() + "(name, value) VALUES (?, ?)")) {
                     ins.setString(1, names[i]);
                     ins.setDouble(2, localValues[i]);
                     ins.executeUpdate();
                  }
               } else if (deltas[i] != 0.0) {
                  try (PreparedStatement up = this.conn
                        .prepareStatement("INSERT INTO " + this.counterTable() + "(name, value) VALUES (?, ?) ON DUPLICATE KEY UPDATE value = value + ?")) {
                     up.setString(1, names[i]);
                     up.setDouble(2, localValues[i]);
                     up.setDouble(3, deltas[i]);
                     up.executeUpdate();
                  }
               }

               try (PreparedStatement sel = this.conn.prepareStatement("SELECT value FROM " + this.counterTable() + " WHERE name = ?")) {
                  sel.setString(1, names[i]);

                  try (ResultSet rs = sel.executeQuery()) {
                     totals[i] = rs.next() ? rs.getDouble(1) : localValues[i];
                  }
               }
            }

            this.conn.commit();
            return totals;
         } catch (SQLException e) {
            try {
               this.conn.rollback();
            } catch (SQLException var12) {
            }

            throw new RuntimeException(e);
         } finally {
            try {
               this.conn.setAutoCommit(true);
            } catch (SQLException var11) {
            }
         }
      }, this.writer);
   }

   boolean hadDataAtStartup() {
      return this.hadDataAtStartup;
   }

   void writeSqliteSnapshot(File dest) throws SQLException {
      List<String[]> rows = new ArrayList<>();
      synchronized (this) {
         for (Entry<String, Map<String, String>> sec : this.persisted.entrySet()) {
            for (Entry<String, String> e : sec.getValue().entrySet()) {
               rows.add(new String[]{sec.getKey(), e.getKey(), e.getValue()});
            }
         }
      }

      try (Connection out = DriverManager.getConnection("jdbc:sqlite:" + dest.getAbsolutePath())) {
         try (Statement st = out.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS plugin_data (section TEXT NOT NULL, path TEXT NOT NULL, value TEXT, PRIMARY KEY (section, path))");
         }

         out.setAutoCommit(false);

         try (PreparedStatement ps = out.prepareStatement("INSERT OR REPLACE INTO plugin_data(section, path, value) VALUES (?, ?, ?)")) {
            for (String[] row : rows) {
               ps.setString(1, row[0]);
               ps.setString(2, row[1]);
               ps.setString(3, row[2]);
               ps.addBatch();
            }

            ps.executeBatch();
         }

         out.commit();
      }
   }

   void close() {
      synchronized (this) {
         this.flushTouched();
      }

      if (!this.retryOps.isEmpty()) {
         this.writer.submit(() -> this.applyOps(new ArrayList<>()));
      }

      this.writer.submit(this::drainLedger);
      this.writer.shutdown();

      try {
         if (!this.writer.awaitTermination(60L, TimeUnit.SECONDS)) {
            this.log.severe("[SQLite] 書き込みが60秒以内に完了しませんでした。");
         }
      } catch (InterruptedException var3) {
         Thread.currentThread().interrupt();
      }

      try {
         this.conn.close();
      } catch (SQLException var2) {
      }
   }

   synchronized void beginTransaction() {
      this.inTransaction = true;
   }

   synchronized void rollbackTransaction() {
      this.inTransaction = false;

      for (String section : this.touched) {
         Map<String, String> old = this.persisted.get(section);
         if (old == null) {
            this.mirror.remove(section);
         } else {
            this.mirror.put(section, new LinkedHashMap<>(old));
         }
      }

      this.touched.clear();
   }

   synchronized void endTransaction() {
      this.inTransaction = false;
      this.flushTouched();
   }

   private void touch(String section) {
      this.touched.add(section);
      if (!this.inTransaction) {
         this.flushTouched();
      }
   }

   private void flushTouched() {
      if (!this.touched.isEmpty()) {
         List<String[]> ops = new ArrayList<>();

         for (String section : this.touched) {
            Map<String, String> cur = this.mirror.getOrDefault(section, Collections.emptyMap());
            Map<String, String> old = this.persisted.getOrDefault(section, Collections.emptyMap());

            for (Entry<String, String> e : cur.entrySet()) {
               if (!e.getValue().equals(old.get(e.getKey()))) {
                  ops.add(new String[]{section, e.getKey(), e.getValue()});
                  this.pushedSincePull.add(section + "\u0000" + e.getKey());
               }
            }

            for (String key : old.keySet()) {
               if (!cur.containsKey(key)) {
                  ops.add(new String[]{section, key, null});
                  this.pushedSincePull.add(section + "\u0000" + key);
               }
            }

            if (cur.isEmpty()) {
               this.persisted.remove(section);
               this.mirror.remove(section);
            } else {
               this.persisted.put(section, new LinkedHashMap<>(cur));
            }
         }

         this.touched.clear();
         if (!ops.isEmpty()) {
            this.writer.submit(() -> this.applyOps(ops));
         }
      }
   }

   private void applyOps(List<String[]> newOps) {
      List<String[]> ops = new ArrayList<>();

      String[] retry;
      while ((retry = this.retryOps.poll()) != null) {
         ops.add(retry);
      }

      ops.addAll(newOps);

      try {
         this.ensureConnection();
         this.conn.setAutoCommit(false);

         try (
            PreparedStatement up = this.conn
               .prepareStatement((this.mysql ? "REPLACE INTO " : "INSERT OR REPLACE INTO ") + this.dataTable + "(section, path, value) VALUES (?, ?, ?)");
            PreparedStatement del = this.conn.prepareStatement("DELETE FROM " + this.dataTable + " WHERE section=? AND path=?");
         ) {
            String origin = this.syncOrigin;
            if (origin != null) {
               try (PreparedStatement sy = this.conn
                     .prepareStatement(
                        "INSERT INTO "
                           + this.dataTable
                           + "(section, path, value, updated_at, origin) VALUES (?, ?, ?, ROUND(UNIX_TIMESTAMP(CURRENT_TIMESTAMP(3)) * 1000), ?) ON DUPLICATE KEY UPDATE value = VALUES(value), updated_at = VALUES(updated_at), origin = VALUES(origin)"
                     )) {
                  for (String[] op : ops) {
                     sy.setString(1, op[0]);
                     sy.setString(2, op[1]);
                     sy.setString(3, op[2]);
                     sy.setString(4, origin);
                     sy.executeUpdate();
                  }
               }

            }

            for (String[] op : origin != null ? List.<String[]>of() : ops) {
               if (op[2] == null) {
                  del.setString(1, op[0]);
                  del.setString(2, op[1]);
                  del.executeUpdate();
               } else {
                  up.setString(1, op[0]);
                  up.setString(2, op[1]);
                  up.setString(3, op[2]);
                  up.executeUpdate();
               }
            }
         }

         this.conn.commit();
      } catch (SQLException e) {
         try {
            this.conn.rollback();
         } catch (SQLException var13) {
         }

         this.retryOps.addAll(ops);
         this.log.severe("[SQLite] 保存に失敗しました。次回の保存時に再試行します: " + e.getMessage());
      } finally {
         try {
            this.conn.setAutoCommit(true);
         } catch (SQLException var12) {
         }
      }
   }

   void ledgerAppend(long ts, String player, double delta, String source) {
      this.ledgerQueue.add(new Object[]{ts, player, delta, source});
   }

   void flushLedgerAsync() {
      if (!this.ledgerQueue.isEmpty() && !this.writer.isShutdown()) {
         this.writer.submit(this::drainLedger);
      }
   }

   private void drainLedger() {
      if (!this.ledgerQueue.isEmpty()) {
         List<Object[]> batch = new ArrayList<>();

         try {
            this.ensureConnection();
            this.conn.setAutoCommit(false);

            try (PreparedStatement ps = this.conn.prepareStatement("INSERT INTO " + this.ledgerTable + "(ts, player, delta, source) VALUES (?, ?, ?, ?)")) {
               Object[] row;
               while ((row = this.ledgerQueue.poll()) != null) {
                  batch.add(row);
                  ps.setLong(1, (Long)row[0]);
                  ps.setString(2, (String)row[1]);
                  ps.setDouble(3, (Double)row[2]);
                  String src = (String)row[3];
                  ps.setString(4, src != null && src.length() > 190 ? src.substring(0, 190) : src);
                  ps.executeUpdate();
               }
            }

            this.conn.commit();
         } catch (SQLException e) {
            try {
               this.conn.rollback();
            } catch (SQLException var11) {
            }

            if (this.ledgerQueue.size() < 100000) {
               this.ledgerQueue.addAll(batch);
            }

            this.log.warning("[台帳] 書き込みに失敗しました(次回再試行): " + e.getMessage());
         } finally {
            try {
               this.conn.setAutoCommit(true);
            } catch (SQLException var10) {
            }
         }
      }
   }

   void pruneLedgerAsync(long cutoff) {
      if (!this.writer.isShutdown()) {
         this.writer.submit(() -> {
            try {
               this.ensureConnection();
            } catch (SQLException e) {
               this.log.warning("[台帳] データベースに接続できません: " + e.getMessage());
               return;
            }

            if (this.syncOrigin != null) {
               try (PreparedStatement tomb = this.conn.prepareStatement("DELETE FROM " + this.dataTable + " WHERE value IS NULL AND updated_at < ?")) {
                  tomb.setLong(1, System.currentTimeMillis() - 604800000L);
                  tomb.executeUpdate();
               } catch (SQLException e) {
                  this.log.warning("[サーバー間同期] 古い削除記録の掃除に失敗しました: " + e.getMessage());
               }
            }

            try (PreparedStatement ps = this.conn.prepareStatement("DELETE FROM " + this.ledgerTable + " WHERE ts < ?")) {
               ps.setLong(1, cutoff);
               int n = ps.executeUpdate();
               if (n > 0) {
                  this.log.info("[台帳] 保存期間を過ぎた " + n + " 件を削除しました。");
               }
            } catch (SQLException e) {
               this.log.warning("[台帳] 古い記録の削除に失敗しました: " + e.getMessage());
            }
         });
      }
   }

   CompletableFuture<List<Object[]>> queryAsync(String sql, Object... params) {
      return CompletableFuture.supplyAsync(() -> {
         this.drainLedger();
         List<Object[]> rows = new ArrayList<>();

         try {
            this.ensureConnection();
         } catch (SQLException e) {
            throw new RuntimeException(e);
         }

         try (PreparedStatement ps = this.conn.prepareStatement(sql.replace("{ledger}", this.ledgerTable))) {
            for (int i = 0; i < params.length; i++) {
               ps.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = ps.executeQuery()) {
               int cols = rs.getMetaData().getColumnCount();

               while (rs.next()) {
                  Object[] row = new Object[cols];

                  for (int c = 0; c < cols; c++) {
                     row[c] = rs.getObject(c + 1);
                  }

                  rows.add(row);
               }
            }

            return rows;
         } catch (SQLException e) {
            throw new RuntimeException(e);
         }
      }, this.writer);
   }

   void runOnWriter(Runnable task) {
      if (this.writer.isShutdown()) {
         task.run();
      } else {
         this.writer.submit(task);
      }
   }

   synchronized void setString(String section, String path, String value) {
      if (value == null) {
         this.remove(section, path);
      } else {
         this.mirror.computeIfAbsent(section, k -> new LinkedHashMap<>()).put(path, value);
         this.touch(section);
      }
   }

   synchronized String getString(String section, String path, String def) {
      Map<String, String> m = this.mirror.get(section);
      String v = m != null ? m.get(path) : null;
      return v != null ? v : def;
   }

   void setDouble(String section, String path, double value) {
      this.setString(section, path, Double.toString(value));
   }

   double getDouble(String section, String path, double def) {
      String v = this.getString(section, path, null);
      if (v == null) {
         return def;
      }

      try {
         return Double.parseDouble(v);
      } catch (NumberFormatException e) {
         return def;
      }
   }

   void setLong(String section, String path, long value) {
      this.setString(section, path, Long.toString(value));
   }

   long getLong(String section, String path, long def) {
      String v = this.getString(section, path, null);
      if (v == null) {
         return def;
      }

      try {
         return Long.parseLong(v);
      } catch (NumberFormatException e) {
         return def;
      }
   }

   void setInt(String section, String path, int value) {
      this.setString(section, path, Integer.toString(value));
   }

   int getInt(String section, String path, int def) {
      String v = this.getString(section, path, null);
      if (v == null) {
         return def;
      }

      try {
         return Integer.parseInt(v);
      } catch (NumberFormatException e) {
         return def;
      }
   }

   void setBoolean(String section, String path, boolean value) {
      this.setString(section, path, Boolean.toString(value));
   }

   boolean getBoolean(String section, String path, boolean def) {
      String v = this.getString(section, path, null);
      return v == null ? def : Boolean.parseBoolean(v);
   }

   void setStringList(String section, String path, List<String> values) {
      if (values != null && !values.isEmpty()) {
         this.setString(section, path, String.join("\n", values));
      } else {
         this.remove(section, path);
      }
   }

   List<String> getStringList(String section, String path) {
      String v = this.getString(section, path, null);
      return v != null && !v.isEmpty() ? new ArrayList<>(Arrays.asList(v.split("\n", -1))) : new ArrayList<>();
   }

   void setItemStack(String section, String path, ItemStack item) {
      if (item == null) {
         this.remove(section, path);
      } else {
         try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BukkitObjectOutputStream oos = new BukkitObjectOutputStream(baos);

            try {
               oos.writeObject(item);
            } catch (Throwable var9) {
               try {
                  oos.close();
               } catch (Throwable var8) {
                  var9.addSuppressed(var8);
               }

               throw var9;
            }

            oos.close();
            this.setString(section, path, Base64.getEncoder().encodeToString(baos.toByteArray()));
         } catch (IOException e) {
            throw new RuntimeException("ItemStackのシリアライズに失敗しました: " + section + "/" + path, e);
         }
      }
   }

   ItemStack getItemStack(String section, String path) {
      String v = this.getString(section, path, null);
      if (v == null) {
         return null;
      }

      try {
         byte[] bytes = Base64.getDecoder().decode(v);
         BukkitObjectInputStream ois = new BukkitObjectInputStream(new ByteArrayInputStream(bytes));

         ItemStack var7;
         try {
            Object obj = ois.readObject();
            var7 = obj instanceof ItemStack ? (ItemStack)obj : null;
         } catch (Throwable var9) {
            try {
               ois.close();
            } catch (Throwable var8) {
               var9.addSuppressed(var8);
            }

            throw var9;
         }

         ois.close();
         return var7;
      } catch (Exception e) {
         throw new RuntimeException("ItemStackのデシリアライズに失敗しました: " + section + "/" + path, e);
      }
   }

   void setItemStackList(String section, String path, List<ItemStack> items) {
      if (items != null && !items.isEmpty()) {
         try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BukkitObjectOutputStream oos = new BukkitObjectOutputStream(baos);

            try {
               oos.writeObject(new ArrayList<>(items));
            } catch (Throwable var9) {
               try {
                  oos.close();
               } catch (Throwable var8) {
                  var9.addSuppressed(var8);
               }

               throw var9;
            }

            oos.close();
            this.setString(section, path, Base64.getEncoder().encodeToString(baos.toByteArray()));
         } catch (IOException e) {
            throw new RuntimeException("ItemStackリストのシリアライズに失敗しました: " + section + "/" + path, e);
         }
      } else {
         this.remove(section, path);
      }
   }

   List<ItemStack> getItemStackList(String section, String path) {
      String v = this.getString(section, path, null);
      if (v == null) {
         return new ArrayList<>();
      }

      try {
         byte[] bytes = Base64.getDecoder().decode(v);
         BukkitObjectInputStream ois = new BukkitObjectInputStream(new ByteArrayInputStream(bytes));

         ArrayList var11;
         label37: {
            try {
               Object obj = ois.readObject();
               if (obj instanceof List) {
                  var11 = new ArrayList((List)obj);
                  break label37;
               }

               var11 = new ArrayList();
            } catch (Throwable var9) {
               try {
                  ois.close();
               } catch (Throwable var8) {
                  var9.addSuppressed(var8);
               }

               throw var9;
            }

            ois.close();
            return var11;
         }

         ois.close();
         return var11;
      } catch (Exception e) {
         throw new RuntimeException("ItemStackリストのデシリアライズに失敗しました: " + section + "/" + path, e);
      }
   }

   Set<String> getKeys(String section) {
      return this.getKeys(section, null);
   }

   synchronized Set<String> getKeys(String section, String pathPrefix) {
      LinkedHashSet<String> result = new LinkedHashSet<>();
      Map<String, String> m = this.mirror.get(section);
      if (m == null) {
         return result;
      } else {
         boolean root = pathPrefix == null || pathPrefix.isEmpty();
         for (String path : m.keySet()) {
            if (root) {
               int dot = path.indexOf(46);
               result.add(dot >= 0 ? path.substring(0, dot) : path);
            } else {
               String needle = pathPrefix + ".";
               if (path.startsWith(needle)) {
                  String rest = path.substring(needle.length());
                  int dot = rest.indexOf(46);
                  result.add(dot >= 0 ? rest.substring(0, dot) : rest);
               }
            }
         }

         return result;
      }
   }

   synchronized void remove(String section, String path) {
      Map<String, String> m = this.mirror.get(section);
      if (m != null && m.remove(path) != null) {
         this.touch(section);
      }
   }

   synchronized void removeSection(String section) {
      this.mirror.remove(section);
      this.touch(section);
   }

   synchronized void removeByPathPrefix(String section, String pathPrefix) {
      Map<String, String> m = this.mirror.get(section);
      if (m != null && m.keySet().removeIf(k -> k.equals(pathPrefix) || k.startsWith(pathPrefix + "."))) {
         this.touch(section);
      }
   }
}
