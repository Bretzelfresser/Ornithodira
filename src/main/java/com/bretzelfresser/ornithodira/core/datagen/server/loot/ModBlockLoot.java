package com.bretzelfresser.ornithodira.core.datagen.server.loot;

import com.bretzelfresser.ornithodira.common.block.CustomEggBlock;
import com.bretzelfresser.ornithodira.common.loot.BlocksStateNumberProvider;
import com.bretzelfresser.ornithodira.core.init.ModBlocks;
import com.bretzelfresser.ornithodira.core.init.ModItems;
import com.bretzelfresser.ornithodira.core.init.ModLootTables;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.*;
import java.util.function.BiConsumer;

public class ModBlockLoot extends BlockLootSubProvider {

    protected List<Block> knownBlocks = new ArrayList<>();

    public ModBlockLoot() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        makeCustomEggLootTable(ModBlocks.PARAREPTILE_EGG.get(), ModItems.PARAREPTILE_EGG.get(), ModItems.FOSSILIZED_PARAREPTILE_EGG.get());
        makeCustomEggLootTable(ModBlocks.SYNAPSID_EGG.get(), ModItems.SYNAPSID_EGG.get(), ModItems.FOSSILIZED_SYNAPSID_EGG.get());
        dropWhenSilkTouch(ModBlocks.FOSSILIZED_NINGXIATES_CONE_BLOCK.get());
        this.map.put(ModLootTables.NINGXIATES_BRUSH_LOOT, createSingleItemTable(ModItems.NINGXIAITES_CONE.get()));

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

    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
        this.generate();
        Set<ResourceLocation> set = new HashSet<>();

        for(Block block : getKnownBlocks()) {
            if (block.isEnabled(this.enabledFeatures)) {
                ResourceLocation resourcelocation = block.getLootTable();
                if (resourcelocation != BuiltInLootTables.EMPTY && set.add(resourcelocation)) {
                    LootTable.Builder loottable$builder = this.map.remove(resourcelocation);
                    if (loottable$builder == null) {
                        throw new IllegalStateException(String.format(Locale.ROOT, "Missing loottable '%s' for '%s'", resourcelocation, BuiltInRegistries.BLOCK.getKey(block)));
                    }

                    consumer.accept(resourcelocation, loottable$builder);
                }
            }
        }

        if (!this.map.isEmpty()) {
            for (Map.Entry<ResourceLocation, LootTable.Builder> entry : this.map.entrySet()){
                consumer.accept(entry.getKey(), entry.getValue());
            }
        }
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
