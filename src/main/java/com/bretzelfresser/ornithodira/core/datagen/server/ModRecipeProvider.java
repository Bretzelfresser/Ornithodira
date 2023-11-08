package com.bretzelfresser.ornithodira.core.datagen.server;

import com.bretzelfresser.ornithodira.core.init.ModBlocks;
import com.bretzelfresser.ornithodira.core.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput p_248933_) {
        super(p_248933_);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        makeScanner(consumer);
    }

    private void makeScanner(Consumer<FinishedRecipe> consumer){
        //Level 1
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SCANNER_1.get())
                .define('d', Blocks.CHISELED_DEEPSLATE)
                .define('g', Items.GOLD_INGOT)
                .define('a', ModItems.PARADOX_AMULET.get())
                .pattern("dgd")
                .pattern("gag")
                .pattern("dgd")
                .unlockedBy("hasItem", has(ModItems.PARADOX_AMULET.get())).save(consumer);
    }
}
