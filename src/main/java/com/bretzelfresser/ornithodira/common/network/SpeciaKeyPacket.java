package com.bretzelfresser.ornithodira.common.network;

import com.bretzelfresser.ornithodira.common.entity.util.SpecialKeyBindEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SpeciaKeyPacket {

    public SpeciaKeyPacket(FriendlyByteBuf buffer){
    }

    public SpeciaKeyPacket(){
    }


    public static void encode(SpeciaKeyPacket packet, FriendlyByteBuf buffer){
    }

    public static void handle(SpeciaKeyPacket msg, Supplier<NetworkEvent.Context> ctx){
        ctx.get().enqueueWork(() -> {
            Entity vehicle = ctx.get().getSender().getVehicle();
            if (vehicle instanceof SpecialKeyBindEntity keyBindEntity){
                if (keyBindEntity.canExecute(ctx.get().getSender())){
                    keyBindEntity.execute(ctx.get().getSender());
                }
            }

        });
    }


}
