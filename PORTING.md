# RenderBlender — 1.21.1 NeoForge 移植进度

本分支 `1.21.1-neoforge` 由 `master`（1.20.1 Forge）移植而来。
当前为 **骨架 + 核心入口迁移** 阶段，尚未达到可编译状态，需后续迭代。

## 已完成

- [x] 构建系统：`net.minecraftforge.gradle` → `net.neoforged.moddev` (0.1.110)
- [x] `settings.gradle`：加入 NeoForged maven、foojay 0.8.0
- [x] `gradle.properties`：MC 1.21.1 / NeoForge 21.1.77 / Parchment 2024.07.28-1.21 / Java 21 / mod_version 2.0.0
- [x] 元数据：`META-INF/mods.toml` → `META-INF/neoforge.mods.toml`（含 `[[mixins]]`、`neoforge` 依赖、`type=required`）
- [x] `pack.mcmeta`：pack_format 15 → 34
- [x] Mixin 配置：`compatibilityLevel` JAVA_17 → JAVA_21
- [x] 全量包名迁移：`net.minecraftforge.*` → `net.neoforged.*`（24 个文件）
  - `eventbus` → `net.neoforged.bus`
  - `api.distmarker` → `net.neoforged.api.distmarker`
  - `fml` → `net.neoforged.fml`
  - 其余（common/client/event/network/registries…）→ `net.neoforged.neoforge.*`
- [x] 核心入口类重写为 NeoForge 1.21.1 API：
  - `RenderBlenderLib`：`@Mod` 构造器注入 `IEventBus` + `ModContainer`；`NeoForge.EVENT_BUS`；`ResourceLocation.fromNamespaceAndPath`
  - `RenderBlenderClient`：`ModelEvent` / `RegisterShadersEvent` 来自 `net.neoforged.neoforge.client.event`

## 待办（导致编译错误的主要项）

- [ ] **Access Transformer 重写**：1.21.1 NeoForge 使用 Mojang 官方映射，旧 SRG 名（`m_44054_`/`f_173331_` 等）已失效，需改写为 Mojang 成员名。当前 `accesstransformer.cfg` 已清空并加注释。
- [ ] **模型加载 API**：1.21 的 `ModelResourceLoader` / `UnbakedModel` 体系与 1.20.1 差异大，`client/model/*` 下的各 `*ModelLoader` 需适配。
- [ ] **顶点消费者 / RenderType**：1.21 重构了 `VertexConsumer`、`BufferSource`、`RenderType`，`api/client/render/buffer/*`、`RBRenderTypes` 等需重写。
- [ ] **着色器系统**：1.21 的 `ShaderInstance` / uniform 注册流程变化，`api/client/shader/*`、`RBShaders` 需适配；`RegisterShadersEvent` 签名可能变。
- [ ] **Mixer / 事件签名**：`init/mixins/*` 中的 mixin 目标在 1.21 可能签名变化，需逐一核对。
- [ ] **ResourceLocation 构造**：全量将 `new ResourceLocation(ns, path)` 改为 `ResourceLocation.fromNamespaceAndPath`（1.21.1 仍可编译但已废弃）。
- [ ] **Registries / Data Gen**：`init/data/*` 注册与数据生成 API 在 NeoForge 1.21 有变化。

## 构建方式（待编译通过后）

```
./gradlew build
```

> 首次构建需联网下载 NeoForge 依赖与 Java 21 工具链。
