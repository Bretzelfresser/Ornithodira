package com.bretzelfresser.ornithodira;

import com.bretzelfresser.ornithodira.core.init.*;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;


@Mod(Ornithodira.MODID)
public class Ornithodira {
    public static final String MODID = "ornithodira";
    public static final Logger LOGGER = LogUtils.getLogger();


    public Ornithodira() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        modEventBus.addListener(ModKeyBindings::registerKeyBindings);

        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModTabs.CREATIVE_MODE_TABS.register(modEventBus);
        ModLootModifiers.LOOT_MODIFIER_SERIALIZER.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);
        ModMenus.MENUS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

        GeckoLib.initialize();
    }
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
       if (event.getTab() == ModTabs.ITEMS.get()) {
           event.accept(ModItems.PARADOX_AMULET);
           event.accept(ModBlocks.SCANNER_1);
           event.accept(ModItems.METAL_BRUSH);
           event.accept(ModItems.DIAMOND_BRUSH);
       }
       if (event.getTab() == ModTabs.SPAWN_EGGS.get()){
           event.accept(ModItems.SANCHUANSAURUS_SPAWN_EGG);
       }
    }

    public static ResourceLocation modLoc(String name){
        return new ResourceLocation(MODID, name);
    }

    public static ResourceLocation entityGeo(String name){
        return modLoc("geo/entity/" + name);
    }

    public static ResourceLocation entityTexture(String name){
        return modLoc("textures/entity/" + name);
    }

    public static ResourceLocation animation(String name){
        return modLoc("animations/" + name);
    }

}
