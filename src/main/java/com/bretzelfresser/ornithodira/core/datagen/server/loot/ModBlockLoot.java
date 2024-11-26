package com.bretzelfresser.ornithodira.core.datagen.server.loot;

import com.bretzelfresser.ornithodira.common.block.CustomEggBlock;
import com.bretzelfresser.ornithodira.core.init.ModBlocks;
import com.bretzelfresser.ornithodira.core.init.ModItems;
import com.bretzelfresser.ornithodira.core.init.ModLootTables;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
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

        this.add(ModBlocks.BROWN_SHALE.get(), (penis) -> {
            return this.createSingleItemTableWithSilkTouch(penis, ModBlocks.COBBLED_BROWN_SHALE.get());
        });
        stairSlabAndWallLoot(ModBlocks.BROWN_SHALE_STAIRS.get(), ModBlocks.BROWN_SHALE_SLAB.get(), ModBlocks.BROWN_SHALE_WALL.get());

        this.dropSelf(ModBlocks.COBBLED_BROWN_SHALE.get());
        stairSlabAndWallLoot(ModBlocks.COBBLED_BROWN_SHALE_STAIRS.get(), ModBlocks.COBBLED_BROWN_SHALE_SLAB.get(), ModBlocks.COBBLED_BROWN_SHALE_WALL.get());
        this.dropSelf(ModBlocks.POLISHED_BROWN_SHALE.get());
        stairSlabAndWallLoot(ModBlocks.POLISHED_BROWN_SHALE_STAIRS.get(), ModBlocks.POLISHED_BROWN_SHALE_SLAB.get(), ModBlocks.POLISHED_BROWN_SHALE_WALL.get());
        this.dropSelf(ModBlocks.CHISELED_BROWN_SHALE.get());
        this.dropSelf(ModBlocks.BROWN_SHALE_BRICKS.get());
        stairSlabAndWallLoot(ModBlocks.BROWN_SHALE_BRICK_STAIRS.get(), ModBlocks.BROWN_SHALE_BRICK_SLAB.get(), ModBlocks.BROWN_SHALE_BRICK_WALL.get());
        this.dropSelf(ModBlocks.MOSSY_BROWN_SHALE_BRICKS.get());
        stairSlabAndWallLoot(ModBlocks.MOSSY_BROWN_SHALE_BRICK_STAIRS.get(), ModBlocks.MOSSY_BROWN_SHALE_BRICK_SLAB.get(), ModBlocks.MOSSY_BROWN_SHALE_BRICK_WALL.get());
        this.dropSelf(ModBlocks.CRACKED_BROWN_SHALE_BRICKS.get());

        this.dropSelf(ModBlocks.GRAY_SHALE.get());
        this.dropSelf(ModBlocks.RED_SHALE.get());
        this.dropSelf(ModBlocks.RICH_RED_SHALE.get());
    }

    public void stairSlabAndWallLoot(Block stairs, Block slab, Block wall) {
        this.dropSelf(stairs);
        slabLoot(slab);
        this.dropSelf(wall);
    }

    public void slabLoot(Block slab) {
        this.add(slab, block -> createSlabItemTable(slab));
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
