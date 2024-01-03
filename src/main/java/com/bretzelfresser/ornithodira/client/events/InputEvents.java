package com.bretzelfresser.ornithodira.client.events;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.network.SpeciaKeyPacket;
import com.bretzelfresser.ornithodira.core.init.ModKeyBindings;
import com.bretzelfresser.ornithodira.core.init.ModNetworks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Ornithodira.MODID,bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class InputEvents {


    @SubscribeEvent
    public static void handleKeyInputs(TickEvent.ClientTickEvent event){
        if (event.phase == TickEvent.Phase.END){
            while (ModKeyBindings.SPECIAL_ABILITY_KEY.consumeClick()){
                ModNetworks.INSTANCE.sendToServer(new SpeciaKeyPacket());
            }
        }
    }
}
