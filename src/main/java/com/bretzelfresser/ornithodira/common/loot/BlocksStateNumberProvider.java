package com.bretzelfresser.ornithodira.common.loot;

import com.bretzelfresser.ornithodira.core.init.ModLootNumberProviderTypes;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.providers.number.LootNumberProviderType;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;

public class BlocksStateNumberProvider implements NumberProvider {

    public static BlocksStateNumberProvider create(IntegerProperty property){
        return new BlocksStateNumberProvider(property.getName());
    }

    protected final String propertyName;

    protected BlocksStateNumberProvider(String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public float getFloat(LootContext pLootContext) {
        BlockState state = pLootContext.getParam(LootContextParams.BLOCK_STATE);
        Property<?> property = state.getBlock().getStateDefinition().getProperty(this.propertyName);
        if (property instanceof IntegerProperty integer){
            return state.getValue(integer);
        }
        return 0;
    }

    @Override
    public LootNumberProviderType getType() {
        return ModLootNumberProviderTypes.BLOCK_STATE_NUMBER_PROVIDER_TYPE;
    }

    public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<BlocksStateNumberProvider>{

        @Override
        public void serialize(JsonObject pJson, BlocksStateNumberProvider pValue, JsonSerializationContext pSerializationContext) {
            pJson.addProperty("property", pValue.propertyName);
        }

        @Override
        public BlocksStateNumberProvider deserialize(JsonObject pJson, JsonDeserializationContext pSerializationContext) {
            return new BlocksStateNumberProvider(GsonHelper.getAsString(pJson, "property", ""));
        }
    }
}
