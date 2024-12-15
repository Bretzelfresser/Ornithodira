package com.bretzelfresser.ornithodira.core.datagen.server;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.recipe.ParareptileEggEntitiesRecipe;
import com.bretzelfresser.ornithodira.core.datagen.server.recipe.EggRecipeBuilder;
import com.bretzelfresser.ornithodira.core.init.ModBlocks;
import com.bretzelfresser.ornithodira.core.init.ModEntities;
import com.bretzelfresser.ornithodira.core.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

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

        //brown shale
        genShaleRecipes(consumer,
                ModBlocks.BROWN_SHALE.get(), ModBlocks.BROWN_SHALE_STAIRS.get(), ModBlocks.BROWN_SHALE_SLAB.get(), ModBlocks.BROWN_SHALE_WALL.get(),
                ModBlocks.COBBLED_BROWN_SHALE.get(), ModBlocks.COBBLED_BROWN_SHALE_STAIRS.get(), ModBlocks.COBBLED_BROWN_SHALE_SLAB.get(), ModBlocks.COBBLED_BROWN_SHALE_WALL.get(),
                ModBlocks.CHISELED_BROWN_SHALE.get(),
                ModBlocks.POLISHED_BROWN_SHALE.get(), ModBlocks.POLISHED_BROWN_SHALE_STAIRS.get(), ModBlocks.POLISHED_BROWN_SHALE_SLAB.get(), ModBlocks.POLISHED_BROWN_SHALE_WALL.get(),
                ModBlocks.BROWN_SHALE_BRICKS.get(), ModBlocks.BROWN_SHALE_BRICK_STAIRS.get(), ModBlocks.BROWN_SHALE_BRICK_SLAB.get(), ModBlocks.BROWN_SHALE_BRICK_WALL.get(),
                ModBlocks.CRACKED_BROWN_SHALE_BRICKS.get(),
                ModBlocks.MOSSY_BROWN_SHALE_BRICKS.get(), ModBlocks.MOSSY_BROWN_SHALE_BRICK_STAIRS.get(), ModBlocks.MOSSY_BROWN_SHALE_BRICK_SLAB.get(), ModBlocks.MOSSY_BROWN_SHALE_BRICK_WALL.get()
        );
        //gray shale
        //red shale
    }

    protected void genShaleRecipes(Consumer<FinishedRecipe> consumer,
                                   Block normal, Block nSt, Block nSl, Block nW,
                                   Block cob, Block cobSt, Block cobSl, Block cobW,
                                   Block chis,
                                   Block pol, Block polSt, Block polSl, Block polW,
                                   Block br, Block brSt, Block brSl, Block brW,
                                   Block crack,
                                   Block mos, Block mosSt, Block mosSl, Block mosW
                                   ) {
        //cobbled
        genStairsSlabAndWall(consumer, cob, cobSt, cobSl, cobW);

        //normal
        simpleSmeltingBuilder(consumer, cob, normal, RecipeCategory.BUILDING_BLOCKS);
        genStairsSlabAndWall(consumer, normal, nSt, nSl, nW);

        //chiseled
        chiseled(consumer, RecipeCategory.BUILDING_BLOCKS, chis, nSl);

        //polished
        twoByTwoToFour(consumer, pol, normal);
        genStairsSlabAndWall(consumer, pol, polSt, polSl, polW);

        //bricks
        twoByTwoToFour(consumer, br, pol);
        genStairsSlabAndWall(consumer, br, brSt, brSl, brW);

        //cracked bricks
        simpleSmeltingBuilder(consumer, br, crack, RecipeCategory.BUILDING_BLOCKS);

        //mossy bricks
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, mos).requires(br).requires(Blocks.VINE).group("mossy_shale_bricks").unlockedBy(getHasName(Blocks.VINE), has(Blocks.VINE)).save(consumer, getConversionRecipeName(Blocks.MOSSY_STONE_BRICKS, Blocks.VINE));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, mos).requires(br).requires(Blocks.MOSS_BLOCK).group("mossy_shale_bricks").unlockedBy(getHasName(Blocks.MOSS_BLOCK), has(Blocks.MOSS_BLOCK)).save(consumer, getConversionRecipeName(Blocks.MOSSY_STONE_BRICKS, Blocks.MOSS_BLOCK));
        genStairsSlabAndWall(consumer, mos, mosSt, mosSl, mosW);

        //stonecutting
        massStonecutting(consumer, cob, cobSt, cobSl, cobW);

        massStonecutting(consumer, normal, chis, nSt, nSl, nW);
        massStonecutting(consumer, normal, pol, polSt, polSl, polW);
        massStonecutting(consumer, normal, br, brSt, brSl, brW);

        massStonecutting(consumer, pol, polSt, polSl, polW);
        massStonecutting(consumer, pol, br, brSt, brSl, brW);

        massStonecutting(consumer, br, brSt, brSl, brW);
        massStonecutting(consumer, mos, mosSt, mosSl, mosW);
    }

    protected static void simpleSmeltingBuilder(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, RecipeCategory category) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), category, result, 0.1F, 200).unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer);
    }

    protected static void twoByTwoToFour(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike material) {
        cutBuilder(RecipeCategory.BUILDING_BLOCKS, result, Ingredient.of(material)).unlockedBy(getHasName(material), has(material)).save(consumer);
    }

    protected static void stairs(Consumer<FinishedRecipe> consumer, ItemLike stairs, ItemLike material) {
        stairBuilder(stairs, Ingredient.of(material)).unlockedBy(getHasName(material), has(material)).save(consumer);
    }

    protected static void slab(Consumer<FinishedRecipe> consumer, ItemLike slab, ItemLike material) {
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, slab, Ingredient.of(material)).unlockedBy(getHasName(material), has(material)).save(consumer);
    }

    protected static void wall(Consumer<FinishedRecipe> consumer, ItemLike wall, ItemLike material) {
        wallBuilder(RecipeCategory.DECORATIONS, wall, Ingredient.of(material)).unlockedBy(getHasName(material), has(material)).save(consumer);
    }

    protected static void genStairsSlabAndWall (Consumer<FinishedRecipe> consumer, ItemLike material, ItemLike stairs, ItemLike slab, ItemLike wall) {
        stairs(consumer, stairs, material);
        slab(consumer, slab, material);
        wall(consumer, wall, material);
    }

    protected static void massStonecutting(Consumer<FinishedRecipe> consumer, ItemLike material, ItemLike... results) {
        for (ItemLike result : results) {
            if (result.toString().contains("slab")) {
                stonecutterRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, result, material, 2);
            } else if (result.toString().contains("wall")) {
                stonecutterRecipe(consumer, RecipeCategory.DECORATIONS, result, material, 1);
            } else {
                stonecutterRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, result, material, 1);
            }
        }
    }

    protected static void stonecutterRecipe(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeCategory pCategory, ItemLike pResult, ItemLike pMaterial, int pResultCount) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(pMaterial), pCategory, pResult, pResultCount).unlockedBy(getHasName(pMaterial), has(pMaterial)).save(pFinishedRecipeConsumer, Ornithodira.MODID + ":" + getConversionRecipeName(pResult, pMaterial) + "_stonecutting");
    }

    protected void makeBrushRecipes(Consumer<FinishedRecipe> consumer){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.METAL_BRUSH.get())
                .define('s', Items.STICK)
                .define('c', Items.COPPER_INGOT)
                .define('i', Items.IRON_INGOT)
                .pattern("iii")
                .pattern(" c ")
                .pattern(" s ")
                .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
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
                .unlockedBy(getHasName(result), has(result)).save(consumer);
    }
}
