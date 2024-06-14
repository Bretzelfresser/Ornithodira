package com.bretzelfresser.ornithodira.core.datagen.server;

import com.bretzelfresser.ornithodira.common.recipe.ParareptileEggEntitiesRecipe;
import com.bretzelfresser.ornithodira.common.util.ResourceLocationUtils;
import com.bretzelfresser.ornithodira.core.datagen.server.recipe.EggRecipeBuilder;
import com.bretzelfresser.ornithodira.core.init.ModEntities;
import com.bretzelfresser.ornithodira.core.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput p_248933_) {
        super(p_248933_);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        makeBrushRecipes(consumer);

        EggRecipeBuilder.builder(ParareptileEggEntitiesRecipe.SERIALIZER).add(5, ModEntities.SANCHUANSAURUS.get()).add(1, ModEntities.TAOHEODON.get()).save(consumer, "sanchuansaurus_egg_entities");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NINGXIAITES_CONE_STICK.get()).requires(Items.FISHING_ROD).requires(ModItems.NINGXIAITES_CONE.get()).unlockedBy("hasItem", has(ModItems.NINGXIAITES_CONE.get())).save(consumer);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_CLADOCYCLUS.get()), RecipeCategory.FOOD, ModItems.COOKED_CLADOCYCLUS.get(), .3f, 200).unlockedBy("hasItem", has(ModItems.RAW_CLADOCYCLUS.get())).save(consumer);
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ModItems.RAW_CLADOCYCLUS.get()), RecipeCategory.FOOD, ModItems.COOKED_CLADOCYCLUS.get(), .3f, 100).unlockedBy("hasItem", has(ModItems.RAW_CLADOCYCLUS.get())).save(consumer, ResourceLocationUtils.extend(ModItems.COOKED_CLADOCYCLUS.getId(), "_smoking"));
    }

    protected void makeBrushRecipes(Consumer<FinishedRecipe> consumer){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.METAL_BRUSH.get())
                .define('s', Items.STICK)
                .define('c', Items.COPPER_INGOT)
                .define('i', Items.IRON_INGOT)
                .pattern("iii")
                .pattern(" c ")
                .pattern(" s ")
                .unlockedBy("hasItem", has(Items.COPPER_INGOT))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DIAMOND_BRUSH.get()).requires(ModItems.METAL_BRUSH.get()).requires(Items.DIAMOND).unlockedBy("hasItem", has(ModItems.METAL_BRUSH.get())).save(consumer);
    }

    protected void makeSurroundingRecipe(Consumer<FinishedRecipe> consumer, TagKey<Item> middle, ItemLike surrounding, ItemLike result){
        makeSurroundingRecipe(consumer, Ingredient.of(middle), Ingredient.of(surrounding), result);
    }

    protected void makeSurroundingRecipe(Consumer<FinishedRecipe> consumer, ItemLike middle, ItemLike surrounding, ItemLike result){
        makeSurroundingRecipe(consumer, Ingredient.of(middle), Ingredient.of(surrounding), result);
    }
    protected void makeSurroundingRecipe(Consumer<FinishedRecipe> consumer, Ingredient middle, ItemLike surrounding, ItemLike result){
        makeSurroundingRecipe(consumer, middle, Ingredient.of(surrounding), result);
    }

    protected void makeSurroundingRecipe(Consumer<FinishedRecipe> consumer, Ingredient middle, Ingredient surrounding, ItemLike result){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
                .define('s', surrounding)
                .define('m', middle)
                .pattern("sss")
                .pattern("sms")
                .pattern("sss")
                .unlockedBy("hasItem", has(result)).save(consumer);
    }
}
