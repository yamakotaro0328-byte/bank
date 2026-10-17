# bank

`minecraft_bank-1.jar` を Vineflower 1.12.0 でデコンパイルしたソースコードです（CFRは`onInventoryClick`の一部を正しく復元できなかったため切り替えました）。

- `src/main/java/kot0328/minecraftBank/MinecraftBank.java`: プラグイン本体（内部クラスを含む）
- `src/main/java/kot0328/minecraftBank/MinecraftBankAPI.java`
- `src/main/resources/plugin.yml`, `config.yml`

## ビルド

Java 25 と Gradle Wrapper でビルドできます。

```
./gradlew build
```

成果物は `build/libs/MinecraftBank-2.0.jar` に出力されます。GitHub Actions (`.github/workflows/build.yml`) でも push / PR 時に自動ビルドし、jarをアーティファクトとしてアップロードします。

## データベース / 複数サーバー共有

`config.yml` の `storage` で保存先を選べます（既定は SQLite の `data.db`）。

```yaml
storage:
  type: mysql            # sqlite | mysql
  mysql:
    host: localhost
    port: 3306
    database: minecraft
    username: root
    password: ""
    table-prefix: mbank_
    use-ssl: false
  sync:                  # 複数サーバーで同じデータを共有する場合
    enabled: true
    server-id: lobby     # サーバーごとに別の名前
    leader: true         # 定期処理(利息・期限・オークション終了など)を担当するサーバーを1台だけ true
    interval-seconds: 5
```

- MySQL側が空なら、起動時に `data.db` の内容(台帳含む)を自動で移行します。
- 同期は行単位で最終書き込みが優先されます。国庫・税収・週間取引高・宝くじの賞金プールは増減分を合算するので取りこぼしません。
- 所持金(Vault)は経済プラグイン側の管理です。サーバー間で共有するには、所持金をMySQLで共有できる経済プラグインを併用してください。
