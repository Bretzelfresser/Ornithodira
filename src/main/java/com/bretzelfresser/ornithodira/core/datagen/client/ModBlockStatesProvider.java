package com.bretzelfresser.ornithodira.core.datagen.client;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.block.CustomEggBlock;
import com.bretzelfresser.ornithodira.common.block.NingxiatesConeBlock;
import com.bretzelfresser.ornithodira.core.init.ModBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlockStatesProvider extends BlockStateProvider {

    public static final ResourceLocation BUIlTIN_ENTITY = new ResourceLocation("builtin/entity");

    public final ModelFile generated = itemModels().getExistingFile(mcLoc("item/generated"));
    public final ModelFile spawnEgg = itemModels().getExistingFile(mcLoc("item/template_spawn_egg"));

    public ModBlockStatesProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Ornithodira.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        makeFossilizedEggBlock(ModBlocks.PARAREPTILE_EGG.get());
        makeFossilizedEggBlock(ModBlocks.SYNAPSID_EGG.get(), name(ModBlocks.SYNAPSID_EGG.get()), name(ModBlocks.SYNAPSID_EGG.get()), false);
        makeNingxiaitesCone(ModBlocks.FOSSILIZED_NINGXIATES_CONE_BLOCK.get(), "fossilized_ningxiaites_cone");

        blockWithItem(ModBlocks.SHALE.get());
        stairsBlock(ModBlocks.SHALE_STAIRS.get(), ModBlocks.SHALE.get());
        slabBlock(ModBlocks.SHALE_SLAB.get(), ModBlocks.SHALE.get());
        wallBlock(ModBlocks.SHALE_WALL.get(), ModBlocks.SHALE.get());

        blockWithItem(ModBlocks.COBBLED_SHALE.get());
        stairsBlock(ModBlocks.COBBLED_SHALE_STAIRS.get(), ModBlocks.COBBLED_SHALE.get());
        slabBlock(ModBlocks.COBBLED_SHALE_SLAB.get(), ModBlocks.COBBLED_SHALE.get());
        wallBlock(ModBlocks.COBBLED_SHALE_WALL.get(), ModBlocks.COBBLED_SHALE.get());

        blockWithItem(ModBlocks.POLISHED_SHALE.get());
        stairsBlock(ModBlocks.POLISHED_SHALE_STAIRS.get(), ModBlocks.POLISHED_SHALE.get());
        slabBlock(ModBlocks.POLISHED_SHALE_SLAB.get(), ModBlocks.POLISHED_SHALE.get());
        wallBlock(ModBlocks.POLISHED_SHALE_WALL.get(), ModBlocks.POLISHED_SHALE.get());

        blockWithItem(ModBlocks.SHALE_BRICKS.get());
        stairsBlock(ModBlocks.SHALE_BRICK_STAIRS.get(), ModBlocks.SHALE_BRICKS.get());
        slabBlock(ModBlocks.SHALE_BRICK_SLAB.get(), ModBlocks.SHALE_BRICKS.get());
        wallBlock(ModBlocks.SHALE_BRICK_WALL.get(), ModBlocks.SHALE_BRICKS.get());

        blockWithItem(ModBlocks.CRACKED_SHALE_BRICKS.get());

        blockWithItem(ModBlocks.MOSSY_SHALE_BRICKS.get());
        stairsBlock(ModBlocks.MOSSY_SHALE_BRICK_STAIRS.get(), ModBlocks.MOSSY_SHALE_BRICKS.get());
        slabBlock(ModBlocks.MOSSY_SHALE_BRICK_SLAB.get(), ModBlocks.MOSSY_SHALE_BRICKS.get());
        wallBlock(ModBlocks.MOSSY_SHALE_BRICK_WALL.get(), ModBlocks.MOSSY_SHALE_BRICKS.get());
    }

    public void blockWithItem(Block block) {
        simpleBlockWithItem(block, cubeAll(block));
    }
    public void stairsBlock(Block block, Block originalBlock) {
        stairsBlock((StairBlock) block, blockTexture(originalBlock));
        itemFromBlock(block);
    }
    public void slabBlock(Block block, Block originalBlock) {
        slabBlock((SlabBlock) block, blockTexture(originalBlock), blockTexture(originalBlock));
        itemFromBlock(block);
    }
    public void wallBlock(Block block, Block originalBlock) {
        wallBlock((WallBlock) block, blockTexture(originalBlock));
        wallItem(block, originalBlock);
    }

    public void itemFromBlock(Block block) {
        ResourceLocation itemLoc = ForgeRegistries.ITEMS.getKey(block.asItem());
        System.out.println("itemLoc toString: " + itemLoc.toString());
        itemModels().getBuilder(itemLoc.toString())
                .parent(new ModelFile.UncheckedModelFile(Ornithodira.MODID + ":block/" + name(block)));
    }

    public void wallItem(Block block, Block originalBlock) {
        ResourceLocation itemLoc = ForgeRegistries.ITEMS.getKey(block.asItem());
        itemModels().getBuilder(itemLoc.toString())
                .parent(new ModelFile.UncheckedModelFile(mcLoc("block/wall_inventory")))
                .texture("wall", new ResourceLocation(Ornithodira.MODID, "block/" + name(originalBlock)));
    }


    protected void makeNingxiaitesCone(Block block, String textureName){
        getVariantBuilder(block).forAllStates(state -> {
            ConfiguredModel.Builder<?> modelBuilder = ConfiguredModel.builder();
            String prefab = "prefab_ningxiaites_cone";
            int eggs = state.getValue(NingxiatesConeBlock.EGGS);
            prefab += "_" + eggs;
            String name = name(block) + "_eggs_" + eggs;
            modelBuilder = modelBuilder.modelFile(models().withExistingParent(modLoc(name).toString(), modLoc(prefab)).texture("texture", modLoc("block/" + textureName)));
            modelBuilder = modelBuilder.rotationY((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot());

            return modelBuilder.build();
        });
    }

    protected void makeFossilizedEggBlock(Block egg) {
        makeFossilizedEggBlock(egg, name(egg));
    }

    protected void makeFossilizedEggBlock(Block egg, String baseName) {
        getVariantBuilder(egg).forAllStates(state -> {
            String textureName = baseName;
            String modelName = "prefab_" + baseName;
            if (state.getValue(CustomEggBlock.FOSSILIZED))
                textureName = "fossilized_" + textureName;
            int eggs = state.getValue(BlockStateProperties.EGGS);
            textureName += "_" + eggs;
            modelName += "_" + eggs;
            if (!state.getValue(CustomEggBlock.FOSSILIZED) && state.getValue(BlockStateProperties.HATCH) > 0) {
                int hatch = state.getValue(BlockStateProperties.HATCH);
                textureName += "_hatching_" + hatch;
            }
            return ConfiguredModel.builder().modelFile(models().withExistingParent(modLoc(textureName).toString(), modLoc(modelName)).texture("texture", modLoc("block/" + textureName))).rotationY((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot()).build();
        });
    }


    protected void makeFossilizedEggBlock(Block egg, String baseName, String textureName, boolean ignoreHatching) {
        getVariantBuilder(egg).forAllStates(state -> {
            String textureFinal = textureName;
            String parentModelName = "prefab_" + baseName;
            String modelName = textureFinal;
            if (state.getValue(CustomEggBlock.FOSSILIZED)) {
                textureFinal = "fossilized_" + textureFinal;
                modelName = "fossilized_" + modelName;
            }
            int eggs = state.getValue(BlockStateProperties.EGGS);
            parentModelName += "_" + eggs;
            modelName += "_" + eggs;
            if (!ignoreHatching && !state.getValue(CustomEggBlock.FOSSILIZED) && state.getValue(BlockStateProperties.HATCH) > 0) {
                int hatch = state.getValue(BlockStateProperties.HATCH);
                textureFinal += "_hatching_" + hatch;
                modelName += "_hatching_" + hatch;
            }
            return ConfiguredModel.builder().modelFile(models().withExistingParent(modLoc(modelName).toString(), modLoc(parentModelName)).texture("texture", modLoc("block/" + textureFinal)).renderType("translucent")).rotationY((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot()).build();
        });
    }


    protected ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    protected String name(Block block) {
        return key(block).getPath();
    }

    private void simple(Item... items) {
        for (Item item : items) {
            String name = ForgeRegistries.ITEMS.getKey(item).getPath();
            itemModels().getBuilder(name).parent(generated).texture("layer0", "item/" + name);
        }
    }

    private void simple(ItemLike... items) {
        for (ItemLike itemProvider : items) {
            simple(itemProvider.asItem());
        }
    }
}
