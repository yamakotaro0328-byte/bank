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

## 演出・サイドバー・ランク

- `effects.*`：大事な場面で演出を出します。`titles`、`particles`、`fireworks`、`event-bossbar` で個別にON/OFFできます。花火でダメージは受けません。
  - 演出が出る場面：入金、落札、実績解除、宝くじ当せん、埋蔵金発見、満期、ランクアップ
  - 経済イベント中は、画面上部のボスバーに残り時間を表示します。
- `sidebar.enabled`：所持金・預金・ランク・資産順位・信用スコア・イベントの残り時間を、右側のサイドバーに表示します。
  - プレイヤーは `/meco sidebar` で表示を切り替えられます。
  - 他のプラグインがスコアボードを使っている人には表示しません。
- `ranks.*`：総資産に応じたランクです。
  - 表示場所：チャット、Tabリスト、頭上の名前
  - ランクアップすると全体に告知します。
  - 段階は `ranks.tiers` で `"必要資産|名前|色"` の形で変更できます。

## プレイヤーショップ

1. チェスト（または樽）に、売りたい物・買い取りたい物を入れます。
   - 空のチェストで買取店を開くときは、買い取りたい物をオフハンドに持ってください。
2. そのチェストに看板を付けて、次のように書きます。
   - 1行目：`[ショップ]`（`[shop]` でも可）
   - 2行目：`販売 100`（店が売る）または `買取 50`（店が買う）
   - 3行目：1回あたりの個数（1〜576、省略すると1）
3. 客が看板を右クリックすると取引画面が開き、1回分・×5・×10 から選べます。
   - 売上には取引税がかかり、残りが店主の所持金に入ります。店主がオフラインでも受け取れます。
   - 買取の代金は店主の所持金から支払われます。

保護と設定：
- 店のチェストを開けたり看板とチェストを壊したりできるのは、店主と `bank.admin` を持つ管理者だけです。
- ホッパーでの抜き取りと爆発からも守られます。
- 1人が開ける店の数は `playershop.max-per-player`（初期値 20）で設定します。
- 機能のON/OFFは `features.playershop`、権限は `bank.feature.playershop` です。

一覧の場所：
- ゲーム内：マーケットのメニュー →「プレイヤーショップ」
- Web：`/shops.html`
