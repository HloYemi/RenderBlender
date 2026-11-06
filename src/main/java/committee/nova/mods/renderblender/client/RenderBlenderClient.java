package committee.nova.mods.renderblender.client;

import committee.nova.mods.renderblender.RenderBlenderLib;
import committee.nova.mods.renderblender.client.model.*;
import committee.nova.mods.renderblender.client.model.cosmic.CosmicModelLoader;
import committee.nova.mods.renderblender.client.model.cosmic.HaloCosmicModelLoader;
import committee.nova.mods.renderblender.client.model.eternal.EternalModelLoader;
import committee.nova.mods.renderblender.client.model.eternal.HaloEternalModelLoader;
import committee.nova.mods.renderblender.client.model.hell.HaloHellModelLoader;
import committee.nova.mods.renderblender.client.model.hell.HellModelLoader;
import committee.nova.mods.renderblender.client.model.unstable.HaloUnstableModelLoader;
import committee.nova.mods.renderblender.client.model.unstable.UnstableModelLoader;
import committee.nova.mods.renderblender.client.shader.RBShaders;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;

@EventBusSubscriber(modid = RenderBlenderLib.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class RenderBlenderClient {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onRegisterShaders(RegisterShadersEvent event) {
        RBShaders.onRegisterShaders(event);//注册着色器
    }

    @SubscribeEvent
    public static void registerLoaders(ModelEvent.RegisterGeometryLoaders event) {
        event.register(ResourceLocation.fromNamespaceAndPath(RenderBlenderLib.MOD_ID, "cosmic"), CosmicModelLoader.INSTANCE);
        event.register(ResourceLocation.fromNamespaceAndPath(RenderBlenderLib.MOD_ID, "halo"), HaloModelLoader.INSTANCE);
        event.register(ResourceLocation.fromNamespaceAndPath(RenderBlenderLib.MOD_ID, "eternal"), EternalModelLoader.INSTANCE);
        event.register(ResourceLocation.fromNamespaceAndPath(RenderBlenderLib.MOD_ID, "hell"), HellModelLoader.INSTANCE);
        event.register(ResourceLocation.fromNamespaceAndPath(RenderBlenderLib.MOD_ID, "unstable"), UnstableModelLoader.INSTANCE);
        event.register(ResourceLocation.fromNamespaceAndPath(RenderBlenderLib.MOD_ID, "halo_unstable"), HaloUnstableModelLoader.INSTANCE);
        event.register(ResourceLocation.fromNamespaceAndPath(RenderBlenderLib.MOD_ID, "halo_hell"), HaloHellModelLoader.INSTANCE);
        event.register(ResourceLocation.fromNamespaceAndPath(RenderBlenderLib.MOD_ID, "halo_cosmic"), HaloCosmicModelLoader.INSTANCE);
        event.register(ResourceLocation.fromNamespaceAndPath(RenderBlenderLib.MOD_ID, "halo_eternal"), HaloEternalModelLoader.INSTANCE);
        event.register(ResourceLocation.fromNamespaceAndPath(RenderBlenderLib.MOD_ID, "glow_edge"), GlowEdgeModelLoader.INSTANCE);
        event.register(ResourceLocation.fromNamespaceAndPath(RenderBlenderLib.MOD_ID, "scale"), ScaleModelLoader.INSTANCE);
    }
}
