package com.bretzelfresser.ornithodira.core.datagen.client;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.core.init.ModBlocks;
import com.bretzelfresser.ornithodira.core.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;

public class ModItemModelsProvider extends ItemModelProvider {

    public final ModelFile generated = getExistingFile(mcLoc("item/generated"));
    public final ModelFile spawnEgg = getExistingFile(mcLoc("item/template_spawn_egg"));

    public final ModelFile rod = getExistingFile(mcLoc("item/handheld_rod"));
    public ModItemModelsProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Ornithodira.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ModItems.ITEMS.getEntries().stream().map(RegistryObject::get).filter(i -> i instanceof ForgeSpawnEggItem).forEach(this::spawnEgg);
        rod(ModItems.NINGXIAITES_CONE_STICK.get());
        simple(ModItems.DIAMOND_BRUSH.get(), ModItems.METAL_BRUSH.get());
        simple(ModItems.FOSSILIZED_SYNAPSID_EGG.get(), ModItems.SYNAPSID_EGG.get());
        simple(ModItems.FOSSILIZED_PARAREPTILE_EGG.get(), ModItems.PARAREPTILE_EGG.get());
        simple(ModItems.NINGXIAITES_CONE.get(), ModBlocks.FOSSILIZED_NINGXIATES_CONE_BLOCK.get());

        //itemFromBlock(ModBlocks.SHALE_STAIRS.get());
        //itemFromBlock(ModBlocks.SHALE_SLAB.get());


        wallItem(ModBlocks.SHALE_WALL, ModBlocks.SHALE);
    }

    public void itemFromBlock(Block block) {
        ResourceLocation itemLoc = ForgeRegistries.ITEMS.getKey(block.asItem());
        ResourceLocation blockLoc = ForgeRegistries.BLOCKS.getKey(block);
        System.out.println("itemLoc toString: " + itemLoc.toString());
        getBuilder(itemLoc.toString())
                .parent(new ModelFile.UncheckedModelFile(Ornithodira.MODID + ":block/" + blockLoc.getPath()));
    }

    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  new ResourceLocation(Ornithodira.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private void simple(Item... items) {
        for (Item item : items) {
            String name = ForgeRegistries.ITEMS.getKey(item).getPath();
            getBuilder(name).parent(generated).texture("layer0", "item/" + name);
        }
    }

    private void simple(ItemLike... items){
        for (ItemLike itemProvider : items){
            simple(itemProvider.asItem());
        }
    }

    private void spawnEgg(Item... items) {
        for (Item item : items) {
            String name = ForgeRegistries.ITEMS.getKey(item).getPath();
            getBuilder(name).parent(spawnEgg);
        }
    }

    private void rod(Item... items) {
        for (Item item : items) {
            String name = ForgeRegistries.ITEMS.getKey(item).getPath();
            getBuilder(name).parent(rod).texture("layer0", "item/" + name);
        }
    }
}
