package com.bretzelfresser.ornithodira.core.datagen.client;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.core.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelsProvider extends ItemModelProvider {

    public final ModelFile generated = getExistingFile(mcLoc("item/generated"));
    public final ModelFile spawnEgg = getExistingFile(mcLoc("item/template_spawn_egg"));

    public final ModelFile rod = getExistingFile(mcLoc("item/handheld_rod"));
    public ModItemModelsProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Ornithodira.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ModItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(i -> {
            if (i instanceof ForgeSpawnEggItem)
                spawnEgg(i);
            else if (i == ModItems.NINGXIAITES_CONE_STICK.get())
                rod(i);
            else if (!(i instanceof BlockItem))
                simple(i);
        });
        simple(ModItems.FOSSILIZED_SANCHUANSAURUS_EGG.get(), ModItems.SANCHUANSAURUS_EGG.get());
        //simple(ModItems.TAOHEODON_EGG.get(), ModItems.FOSSILIZED_TAOHEODON_EGG.get());
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
