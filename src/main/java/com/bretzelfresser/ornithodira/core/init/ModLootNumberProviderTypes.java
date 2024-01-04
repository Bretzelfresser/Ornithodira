package com.bretzelfresser.ornithodira.core.init;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.loot.BlocksStateNumberProvider;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.storage.loot.providers.number.LootNumberProviderType;

public class ModLootNumberProviderTypes {

    public static final LootNumberProviderType BLOCK_STATE_NUMBER_PROVIDER_TYPE = new LootNumberProviderType(new BlocksStateNumberProvider.Serializer());


    public static void registerTypes(){
        Registry.register(BuiltInRegistries.LOOT_NUMBER_PROVIDER_TYPE, Ornithodira.modLoc("block_state_number_provider"), BLOCK_STATE_NUMBER_PROVIDER_TYPE);
    }
}
