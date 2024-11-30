package com.bretzelfresser.ornithodira.core.datagen.server;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.core.init.ModBlocks;
import com.bretzelfresser.ornithodira.core.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Ornithodira.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ModTags.Blocks.TAHOEODON_DIG_BLOCKS).add(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.MUD);

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                ModBlocks.BROWN_SHALE.get(), ModBlocks.BROWN_SHALE_STAIRS.get(), ModBlocks.BROWN_SHALE_SLAB.get(), ModBlocks.BROWN_SHALE_WALL.get(),
                ModBlocks.COBBLED_BROWN_SHALE.get(), ModBlocks.COBBLED_BROWN_SHALE_STAIRS.get(), ModBlocks.COBBLED_BROWN_SHALE_SLAB.get(), ModBlocks.COBBLED_BROWN_SHALE_WALL.get(),
                ModBlocks.CHISELED_BROWN_SHALE.get(),
                ModBlocks.POLISHED_BROWN_SHALE.get(), ModBlocks.POLISHED_BROWN_SHALE_STAIRS.get(), ModBlocks.POLISHED_BROWN_SHALE_SLAB.get(), ModBlocks.POLISHED_BROWN_SHALE_WALL.get(),
                ModBlocks.BROWN_SHALE_BRICKS.get(), ModBlocks.BROWN_SHALE_BRICK_STAIRS.get(), ModBlocks.BROWN_SHALE_BRICK_SLAB.get(), ModBlocks.BROWN_SHALE_BRICK_WALL.get(),
                ModBlocks.CRACKED_BROWN_SHALE_BRICKS.get(),
                ModBlocks.MOSSY_BROWN_SHALE_BRICKS.get(), ModBlocks.MOSSY_BROWN_SHALE_BRICK_STAIRS.get(), ModBlocks.MOSSY_BROWN_SHALE_BRICK_SLAB.get(), ModBlocks.MOSSY_BROWN_SHALE_BRICK_WALL.get(),

                ModBlocks.GRAY_SHALE.get(), /*ModBlocks.GRAY_SHALE_STAIRS.get(), ModBlocks.GRAY_SHALE_SLAB.get(), ModBlocks.GRAY_SHALE_WALL.get(),
                ModBlocks.COBBLED_GRAY_SHALE.get(), ModBlocks.COBBLED_GRAY_SHALE_STAIRS.get(), ModBlocks.COBBLED_GRAY_SHALE_SLAB.get(), ModBlocks.COBBLED_GRAY_SHALE_WALL.get(),
                ModBlocks.CHISELED_GRAY_SHALE.get(),
                ModBlocks.POLISHED_GRAY_SHALE.get(), ModBlocks.POLISHED_GRAY_SHALE_STAIRS.get(), ModBlocks.POLISHED_GRAY_SHALE_SLAB.get(), ModBlocks.POLISHED_GRAY_SHALE_WALL.get(),
                ModBlocks.GRAY_SHALE_BRICKS.get(), ModBlocks.GRAY_SHALE_BRICK_STAIRS.get(), ModBlocks.GRAY_SHALE_BRICK_SLAB.get(), ModBlocks.GRAY_SHALE_BRICK_WALL.get(),
                ModBlocks.CRACKED_GRAY_SHALE_BRICKS.get(),
                ModBlocks.MOSSY_GRAY_SHALE_BRICKS.get(), ModBlocks.MOSSY_GRAY_SHALE_BRICK_STAIRS.get(), ModBlocks.MOSSY_GRAY_SHALE_BRICK_SLAB.get(), ModBlocks.MOSSY_GRAY_SHALE_BRICK_WALL.get(),
                */
                ModBlocks.RED_SHALE.get(), /*ModBlocks.RED_SHALE_STAIRS.get(), ModBlocks.RED_SHALE_SLAB.get(), ModBlocks.RED_SHALE_WALL.get(),
                ModBlocks.COBBLED_RED_SHALE.get(), ModBlocks.COBBLED_RED_SHALE_STAIRS.get(), ModBlocks.COBBLED_RED_SHALE_SLAB.get(), ModBlocks.COBBLED_RED_SHALE_WALL.get(),
                ModBlocks.CHISELED_RED_SHALE.get(),
                ModBlocks.POLISHED_RED_SHALE.get(), ModBlocks.POLISHED_RED_SHALE_STAIRS.get(), ModBlocks.POLISHED_RED_SHALE_SLAB.get(), ModBlocks.POLISHED_RED_SHALE_WALL.get(),
                ModBlocks.RED_SHALE_BRICKS.get(), ModBlocks.RED_SHALE_BRICK_STAIRS.get(), ModBlocks.RED_SHALE_BRICK_SLAB.get(), ModBlocks.RED_SHALE_BRICK_WALL.get(),
                ModBlocks.CRACKED_RED_SHALE_BRICKS.get(),
                ModBlocks.MOSSY_RED_SHALE_BRICKS.get(), ModBlocks.MOSSY_RED_SHALE_BRICK_STAIRS.get(), ModBlocks.MOSSY_RED_SHALE_BRICK_SLAB.get(), ModBlocks.MOSSY_RED_SHALE_BRICK_WALL.get(),
                */
                ModBlocks.RICH_RED_SHALE.get()
        );

        tag(Tags.Blocks.STONE).add(
                ModBlocks.BROWN_SHALE.get(), ModBlocks.POLISHED_BROWN_SHALE.get(),
                ModBlocks.GRAY_SHALE.get(), //ModBlocks.POLISHED_GRAY_SHALE.get(),
                ModBlocks.RED_SHALE.get()//, ModBlocks.POLISHED_RED_SHALE.get()
        );
        tag(Tags.Blocks.COBBLESTONE).add(
                ModBlocks.COBBLED_BROWN_SHALE.get()/*,
                ModBlocks.COBBLED_GRAY_SHALE.get(),
                ModBlocks.COBBLED_RED_SHALE.get()*/
        );

        tag(BlockTags.STAIRS).add(
                ModBlocks.BROWN_SHALE_STAIRS.get(),
                ModBlocks.COBBLED_BROWN_SHALE_STAIRS.get(),
                ModBlocks.POLISHED_BROWN_SHALE_STAIRS.get(),
                ModBlocks.BROWN_SHALE_BRICK_STAIRS.get(),
                ModBlocks.MOSSY_BROWN_SHALE_BRICK_STAIRS.get()/*,
                ModBlocks.GRAY_SHALE_STAIRS.get(),
                ModBlocks.COBBLED_GRAY_SHALE_STAIRS.get(),
                ModBlocks.POLISHED_GRAY_SHALE_STAIRS.get(),
                ModBlocks.GRAY_SHALE_BRICK_STAIRS.get(),
                ModBlocks.MOSSY_GRAY_SHALE_BRICK_STAIRS.get(),
                ModBlocks.RED_SHALE_STAIRS.get(),
                ModBlocks.COBBLED_RED_SHALE_STAIRS.get(),
                ModBlocks.POLISHED_RED_SHALE_STAIRS.get(),
                ModBlocks.RED_SHALE_BRICK_STAIRS.get(),
                ModBlocks.MOSSY_RED_SHALE_BRICK_STAIRS.get()*/
        );
        tag(BlockTags.SLABS).add(
                ModBlocks.BROWN_SHALE_SLAB.get(),
                ModBlocks.COBBLED_BROWN_SHALE_SLAB.get(),
                ModBlocks.POLISHED_BROWN_SHALE_SLAB.get(),
                ModBlocks.BROWN_SHALE_BRICK_SLAB.get(),
                ModBlocks.MOSSY_BROWN_SHALE_BRICK_SLAB.get()/*,
                ModBlocks.GRAY_SHALE_SLAB.get(),
                ModBlocks.COBBLED_GRAY_SHALE_SLAB.get(),
                ModBlocks.POLISHED_GRAY_SHALE_SLAB.get(),
                ModBlocks.GRAY_SHALE_BRICK_SLAB.get(),
                ModBlocks.MOSSY_GRAY_SHALE_BRICK_SLAB.get(),
                ModBlocks.RED_SHALE_SLAB.get(),
                ModBlocks.COBBLED_RED_SHALE_SLAB.get(),
                ModBlocks.POLISHED_RED_SHALE_SLAB.get(),
                ModBlocks.RED_SHALE_BRICK_SLAB.get(),
                ModBlocks.MOSSY_RED_SHALE_BRICK_SLAB.get()*/
        );
        tag(BlockTags.WALLS).add(
                ModBlocks.BROWN_SHALE_WALL.get(),
                ModBlocks.COBBLED_BROWN_SHALE_WALL.get(),
                ModBlocks.POLISHED_BROWN_SHALE_WALL.get(),
                ModBlocks.BROWN_SHALE_BRICK_WALL.get(),
                ModBlocks.MOSSY_BROWN_SHALE_BRICK_WALL.get()/*,
                ModBlocks.GRAY_SHALE_WALL.get(),
                ModBlocks.COBBLED_GRAY_SHALE_WALL.get(),
                ModBlocks.POLISHED_GRAY_SHALE_WALL.get(),
                ModBlocks.GRAY_SHALE_BRICK_WALL.get(),
                ModBlocks.MOSSY_GRAY_SHALE_BRICK_WALL.get(),
                ModBlocks.RED_SHALE_WALL.get(),
                ModBlocks.COBBLED_RED_SHALE_WALL.get(),
                ModBlocks.POLISHED_RED_SHALE_WALL.get(),
                ModBlocks.RED_SHALE_BRICK_WALL.get(),
                ModBlocks.MOSSY_RED_SHALE_BRICK_WALL.get()*/
        );

        tag(ModTags.Blocks.RICH_RED_SHALE_REPLACEABLES).add(ModBlocks.RED_SHALE.get());

    }
}
