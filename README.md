# bank

`minecraft_bank-1_3.jar` を CFR 0.152 でデコンパインしたソースコードです。

- `src/main/java/kot0328/minecraftBank/MinecraftBank.java`: プラグイン本体（内部クラスを含む）
- `src/main/java/kot0328/minecraftBank/MinecraftBankAPI.java`
- `src/main/resources/plugin.yml`, `config.yml`

`onInventoryClick` メソッドの1箇所のみ、CFRが制御構造を完全に復元できずコメント付きで出力されています。
