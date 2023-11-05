package com.bretzelfresser.ornithodira;

import com.bretzelfresser.ornithodira.core.init.ModBlocks;
import com.bretzelfresser.ornithodira.core.init.ModItems;
import com.bretzelfresser.ornithodira.core.init.ModLootModifiers;
import com.bretzelfresser.ornithodira.core.init.ModTabs;
import com.mojang.datafixers.util.Pair;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.function.Supplier;


@Mod(Ornithodira.MODID)
public class Ornithodira {
    public static final String MODID = "ornithodira";
    public static final Logger LOGGER = LogUtils.getLogger();


    public Ornithodira() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();




        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModTabs.CREATIVE_MODE_TABS.register(modEventBus);
        ModLootModifiers.LOOT_MODIFIER_SERIALIZER.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
       if (event.getTab() == ModTabs.ITEMS.get()) {
           event.accept(ModItems.PARADOX_AMULET);
       }
    }


}
