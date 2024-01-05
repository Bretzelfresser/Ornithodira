package com.bretzelfresser.ornithodira.core.datagen.client;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.block.CustomEggBlock;
import com.bretzelfresser.ornithodira.core.init.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
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
        makeFossilizedEggBlock(ModBlocks.SANCHUANSAURUS_EGG.get());
    }


    protected void makeFossilizedEggBlock(Block egg) {
        makeFossilizedEggBlock(egg, name(egg));
    }

    protected void makeFossilizedEggBlock(Block egg, String baseName) {
        getVariantBuilder(egg).forAllStates(state -> {
            String textureName = baseName;
            String modelName = "custom_egg";
            if (state.getValue(CustomEggBlock.FOSSILIZED))
                textureName = "fossilized_" + textureName;
            int eggs = state.getValue(BlockStateProperties.EGGS);
            textureName += "_" + eggs;
            modelName += "_" + eggs;
            if (!state.getValue(CustomEggBlock.FOSSILIZED) && state.getValue(BlockStateProperties.HATCH) > 0){
                int hatch = state.getValue(BlockStateProperties.HATCH);
                textureName += "_hatching_" + hatch;
            }
            return ConfiguredModel.builder().modelFile(models().withExistingParent(modLoc(textureName).toString(), modLoc(modelName)).texture("texture", modLoc("block/" + textureName))).rotationY((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot()).build();
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
