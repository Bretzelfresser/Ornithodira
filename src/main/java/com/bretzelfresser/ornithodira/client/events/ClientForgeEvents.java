package com.bretzelfresser.ornithodira.client.events;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.client.model.entity.TaoheodonModel;
import com.bretzelfresser.ornithodira.client.renderer.entity.SanchuansaurusRenderer;
import com.bretzelfresser.ornithodira.core.init.ModBlockEntities;
import com.bretzelfresser.ornithodira.core.init.ModEntities;
import com.bretzelfresser.ornithodira.core.init.ModMenus;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Ornithodira.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientForgeEvents {


    @SubscribeEvent
    public static void registerRenders(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(ModEntities.SANCHUANSAURUS.get(), SanchuansaurusRenderer::new);
        event.registerEntityRenderer(ModEntities.TAOHEODON.get(), createEntityGeoRenderer(new TaoheodonModel()));
    }

    @SubscribeEvent
    public static void registerScreens(FMLCommonSetupEvent event){
    }

    public static <T extends LivingEntity & GeoEntity> EntityRendererProvider<T> createEntityGeoRenderer(GeoModel<T> model) {
        return m -> new SimpleEntityGeoRenderer<>(m, model);
    }

    protected static class SimpleEntityGeoRenderer<T extends LivingEntity & GeoEntity> extends GeoEntityRenderer<T> {

        public SimpleEntityGeoRenderer(EntityRendererProvider.Context renderManager, GeoModel<T> modelProvider) {
            super(renderManager, modelProvider);
        }
    }

    public static <T extends BlockEntity & GeoBlockEntity> BlockEntityRendererProvider<T> createBlockGeoRenderer(GeoModel<T> model) {
        return (c) -> new GeoBlockRenderer<>(model);
    }
}
