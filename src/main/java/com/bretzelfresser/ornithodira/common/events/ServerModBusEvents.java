package com.bretzelfresser.ornithodira.common.events;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.entity.Sanchuansaurus;
import com.bretzelfresser.ornithodira.core.init.ModEntities;
import com.bretzelfresser.ornithodira.core.init.ModNetworks;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Ornithodira.MODID)
public class ServerModBusEvents {

    @SubscribeEvent
    public static void createAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.SANCHUANSAURUS.get(), Sanchuansaurus.createAttributes().build());
    }

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event){
        ModNetworks.registerPackets();
    }
}
