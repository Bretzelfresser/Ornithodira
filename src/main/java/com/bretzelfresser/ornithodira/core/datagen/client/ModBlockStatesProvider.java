package com.bretzelfresser.ornithodira.core.datagen.client;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.core.init.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlockStatesProvider extends BlockStateProvider {

    public static final ResourceLocation BUIlTIN_ENTITY = new ResourceLocation("builtin/entity");

    public ModBlockStatesProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Ornithodira.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        makeScanner(ModBlocks.SCANNER_1.get());
    }

    protected void makeScanner(Block scanner){
        ModelFile model = models().getExistingFile(key(scanner));
        horizontalBlock(scanner, model);
        simpleBlockItem(scanner, model);
    }


    protected ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    protected String name(Block block) {
        return key(block).getPath();
    }
}
