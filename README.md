# bank

`minecraft_bank-1.jar` を Vineflower 1.12.0 でデコンパイルしたソースコードです（CFRは`onInventoryClick`の一部を正しく復元できなかったため切り替えました）。

- `src/main/java/kot0328/minecraftBank/MinecraftBank.java`: プラグイン本体（コマンド・GUI・各機能）
- `PluginDataStore.java`: 保存層（SQLite/MySQL・差分保存・台帳・サーバー間同期）
- `DefaultMessages.java` / `ResourceCatalog.java`: 既定メッセージ / 資源ショップ等の価格表
- `BankUtil.java`: 計算・表示用の共通関数
- `Quest.java` `GroupAccount.java` `StandingTransfer.java` ほか: データ用クラス
- `src/test/java/...`: 自動テスト（保存層・価格表の差益チェック・メッセージ書式など）。`./gradlew build` とGitHub Actionsで毎回実行されます
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
- 同期は行単位で最終書き込みが優先されます。ただしお金の残高（個人口座・銀行資本・グループ口座・ファンドの現金/出資額）と国庫・税収・週間取引高・宝くじの賞金プールは、増減分を合算するので同時に変更しても取りこぼしません。
- 銀行家・各種リスト・定期送金・請求書・監査ログは1件ずつ別の行に保存されるので、別サーバーでの同時追加も消えません。
- プレイヤーが参加したときは同期を待ってから参加時の処理（差し押さえ・配布など）を行います。
- リーダーが0台/2台以上のとき、同じ server-id のサーバーが動いているときはログに警告を出します。
- 所持金(Vault)は経済プラグイン側の管理です。サーバー間で共有するには、所持金をMySQLで共有できる経済プラグインを併用してください。

## リソースパック（専用アイコン・効果音）

プラグインに同梱のリソースパックを、参加したプレイヤーに自動で配ります。パックを入れていない人には今まで通りバニラの見た目で表示されます。

- 配布先: `webdashboard.enabled: true` と `webdashboard.public-url` を設定すると、`<public-url>/pack.zip` から配信します。
- 外部に置く場合: `plugins/MinecraftBank/MinecraftBank-pack.zip` をアップロードし、そのURLを `resource-pack.url` に設定します。
- 主な設定: `resource-pack.enabled`、`resource-pack.required`（拒否した人をキックする）、`resource-pack.prompt`、`resource-pack.custom-sounds`。
- 絵や音の差し替え: `plugins/MinecraftBank/resourcepack/` にパック内と同じパスでファイルを置くと上書きされます。
  - 例: `assets/minecraftbank/textures/item/coin.png`
- 同梱の絵と音は `tools/resourcepack/generate.py` で生成しています。ffmpeg が必要です。

## Webダッシュボード

ページは `src/main/resources/web/` にあります。共通のデザインとメニューは `bank.css` と `bank.js` です。
