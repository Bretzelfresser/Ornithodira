package com.bretzelfresser.ornithodira.common.events;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.entity.Cladocyclus;
import com.bretzelfresser.ornithodira.common.entity.Dapedium;
import com.bretzelfresser.ornithodira.common.entity.Sanchuansaurus;
import com.bretzelfresser.ornithodira.common.entity.Taoheodon;
import com.bretzelfresser.ornithodira.core.init.ModEntities;
import com.bretzelfresser.ornithodira.core.init.ModLootNumberProviderTypes;
import com.bretzelfresser.ornithodira.core.init.ModNetworks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Ornithodira.MODID)
public class ServerModBusEvents {

    @SubscribeEvent
    public static void createAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.SANCHUANSAURUS.get(), Sanchuansaurus.createAttributes().build());
        event.put(ModEntities.TAOHEODON.get(), Taoheodon.createAttributes().build());
        event.put(ModEntities.DAPEDIUM.get(), Dapedium.createAttributes().build());
        event.put(ModEntities.CLADOCYCLUS.get(), Cladocyclus.createAttributes().build());
    }

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(ModNetworks::registerPackets);
    }

    @SubscribeEvent
    public static void regsiterLootSerializer(RegisterEvent event){
        if (event.getVanillaRegistry() != null && event.getVanillaRegistry().equals(BuiltInRegistries.LOOT_NUMBER_PROVIDER_TYPE)) {
            ModLootNumberProviderTypes.registerTypes();
        }
    }

}
