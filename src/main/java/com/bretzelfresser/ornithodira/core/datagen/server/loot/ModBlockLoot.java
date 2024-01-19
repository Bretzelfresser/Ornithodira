package com.bretzelfresser.ornithodira.core.datagen.server.loot;

import com.bretzelfresser.ornithodira.common.block.CustomEggBlock;
import com.bretzelfresser.ornithodira.common.loot.BlocksStateNumberProvider;
import com.bretzelfresser.ornithodira.core.init.ModBlocks;
import com.bretzelfresser.ornithodira.core.init.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ModBlockLoot extends BlockLootSubProvider {

    protected List<Block> knownBlocks = new ArrayList<>();

    public ModBlockLoot() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        makeCustomEggLootTable(ModBlocks.SANCHUANSAURUS_EGG.get(), ModItems.SANCHUANSAURUS_EGG.get(), ModItems.FOSSILIZED_SANCHUANSAURUS_EGG.get());
        //makeCustomEggLootTable(ModBlocks.TAOHEODON_EGG.get(), ModItems.TAOHEODON_EGG.get(), ModItems.FOSSILIZED_TAOHEODON_EGG.get());
    }

    public void makeCustomEggLootTable(Block egg, ItemLike cleanEgg, ItemLike fossilizedEgg) {
        LootTable.Builder table = LootTable.lootTable();
        table.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).
                add(LootItem.lootTableItem(cleanEgg).when(
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(egg).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CustomEggBlock.FOSSILIZED, false))
                ).when(HAS_SILK_TOUCH))
                .add(LootItem.lootTableItem(fossilizedEgg).when(
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(egg).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CustomEggBlock.FOSSILIZED, true))
                ).when(HAS_SILK_TOUCH)));
        add(egg, table);
    }

    @Override
    protected void add(Block pBlock, LootTable.Builder pBuilder) {
        super.add(pBlock, pBuilder);
        this.knownBlocks.add(pBlock);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return this.knownBlocks;
    }
}
