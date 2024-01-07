package com.bretzelfresser.ornithodira.core.datagen.server.loot;

import com.bretzelfresser.ornithodira.core.init.ModItems;
import com.bretzelfresser.ornithodira.core.init.ModLootTables;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class ModArcheologyLoot implements LootTableSubProvider {
    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
        consumer.accept(ModLootTables.TAOHEODON_DIGG_LOOT, LootTable.lootTable().withPool(createFirstDiggPool()));
    }

    protected LootPool.Builder createFirstDiggPool(){
        return LootPool.lootPool().setRolls(UniformGenerator.between(1, 4))
                .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(1))
                .add(LootItem.lootTableItem(ModItems.SANCHUANSAURUS_EGG.get()).setWeight(2))
                .add(LootItem.lootTableItem(Items.SAND).setWeight(30))
                .add(LootItem.lootTableItem(Items.GRAVEL).setWeight(30))
                .add(LootItem.lootTableItem(Items.DIRT).setWeight(30))
                .add(LootItem.lootTableItem(ModItems.FOSSILIZED_SANCHUANSAURUS_EGG.get()).setWeight(7));
    }


}
