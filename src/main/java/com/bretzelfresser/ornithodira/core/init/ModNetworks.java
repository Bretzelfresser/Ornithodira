package com.bretzelfresser.ornithodira.core.init;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.network.SpeciaKeyPacket;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModNetworks {

    private static final String PROTOCOL_VERSION = "1";
    private static int ID = 0;
    public static final SimpleChannel INSTANCE = registerSimpleChannel("main", PROTOCOL_VERSION);


    /**
     * this is called on the {@link net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent}
     */
    public static void registerPackets(){
        INSTANCE.registerMessage(++ID, SpeciaKeyPacket.class, SpeciaKeyPacket::encode, SpeciaKeyPacket::new, SpeciaKeyPacket::handle);
    }



    protected static SimpleChannel registerSimpleChannel(String id, String networkVersion) {
        return NetworkRegistry.newSimpleChannel(Ornithodira.modLoc(id), () -> networkVersion, networkVersion::equals, networkVersion::equals);
    }
}
