package com.bretzelfresser.ornithodira.core.datagen.server;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.core.init.ModBlocks;
import com.bretzelfresser.ornithodira.core.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeBlockTagsProvider;
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
                ModBlocks.SHALE.get(),
                ModBlocks.COBBLED_SHALE.get(),
                ModBlocks.POLISHED_SHALE.get(),
                ModBlocks.SHALE_BRICKS.get(),
                ModBlocks.CRACKED_SHALE_BRICKS.get(),
                ModBlocks.MOSSY_SHALE_BRICKS.get());

        tag(Tags.Blocks.STONE).add(ModBlocks.SHALE.get(), ModBlocks.POLISHED_SHALE.get());
        tag(Tags.Blocks.COBBLESTONE).add(ModBlocks.COBBLED_SHALE.get());
        tag(BlockTags.WALLS).add(ModBlocks.SHALE_WALL.get());

        tag(ModTags.Blocks.SHALE_BRICKS).add(
                ModBlocks.SHALE_BRICKS.get(),
                ModBlocks.CRACKED_SHALE_BRICKS.get(),
                ModBlocks.MOSSY_SHALE_BRICKS.get());
    }
}
