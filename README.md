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
