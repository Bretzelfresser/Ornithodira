package com.bretzelfresser.ornithodira.core.init;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.block.CustomEggBlock;
import com.bretzelfresser.ornithodira.common.block.NingxiatesConeBlock;
import com.bretzelfresser.ornithodira.common.recipe.EggEntitiesRecipe;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static CustomEggBlock eggBlock(Supplier<RecipeType<? extends EggEntitiesRecipe>> recipeSupplier, Supplier<ItemLike> fossilizedEgg, Supplier<ItemLike> cleanEgg) {
        return new CustomEggBlock(recipeSupplier,.5f, 0f, 0, fossilizedEgg, cleanEgg, BlockBehaviour.Properties.of().dynamicShape().randomTicks().noOcclusion().strength(1.5f).pushReaction(PushReaction.DESTROY));
    }

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Ornithodira.MODID);

    public static final RegistryObject<CustomEggBlock> PARAREPTILE_EGG = BLOCKS.register("parareptile_egg",
            () -> eggBlock(ModRecipes.PARAREPTILE_EGG_ENTITIES::get, ModItems.FOSSILIZED_PARAREPTILE_EGG::get, ModItems.PARAREPTILE_EGG::get));
            //new CustomEggBlock(ModRecipes.PARAREPTILE_EGG_ENTITIES::get,.5f, 0f, 0, ModItems.FOSSILIZED_PARAREPTILE_EGG::get, ModItems.PARAREPTILE_EGG::get, BlockBehaviour.Properties.of().dynamicShape().randomTicks().noOcclusion().strength(1.5f).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<CustomEggBlock> SYNAPSID_EGG = BLOCKS.register("synapsid_egg",
            () -> eggBlock(ModRecipes.SYNAPSID_EGG_ENTITIES::get, ModItems.FOSSILIZED_SYNAPSID_EGG::get, ModItems.SYNAPSID_EGG::get));
            // new CustomEggBlock(ModRecipes.SYNAPSID_EGG_ENTITIES::get,.5f, 0f, 0, ModItems.FOSSILIZED_SYNAPSID_EGG::get, ModItems.SYNAPSID_EGG::get, BlockBehaviour.Properties.of().dynamicShape().randomTicks().noOcclusion().strength(1.5f).pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<NingxiatesConeBlock> FOSSILIZED_NINGXIATES_CONE_BLOCK = register("fossilized_ningxiaites_cone", () -> new NingxiatesConeBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).dynamicShape().noOcclusion().strength(1.5f)));


    public static final RegistryObject<Block> SHALE = register("shale", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> SHALE_STAIRS = register("shale_stairs", () -> new StairBlock(ModBlocks.SHALE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> SHALE_SLAB = register("shale_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> SHALE_WALL = register("shale_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> COBBLED_SHALE = register("cobbled_shale", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> COBBLED_SHALE_STAIRS = register("cobbled_shale_stairs", () -> new StairBlock(ModBlocks.SHALE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> COBBLED_SHALE_SLAB = register("cobbled_shale_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> COBBLED_SHALE_WALL = register("cobbled_shale_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> POLISHED_SHALE = register("polished_shale", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE)));
    public static final RegistryObject<Block> POLISHED_SHALE_STAIRS = register("polished_shale_stairs", () -> new StairBlock(ModBlocks.SHALE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> POLISHED_SHALE_SLAB = register("polished_shale_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> POLISHED_SHALE_WALL = register("polished_shale_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> SHALE_BRICKS = register("shale_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
    public static final RegistryObject<Block> SHALE_BRICK_STAIRS = register("shale_brick_stairs", () -> new StairBlock(ModBlocks.SHALE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> SHALE_BRICK_SLAB = register("shale_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> SHALE_BRICK_WALL = register("shale_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> CRACKED_SHALE_BRICKS = register("cracked_shale_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.CRACKED_STONE_BRICKS)));

    public static final RegistryObject<Block> MOSSY_SHALE_BRICKS = register("mossy_shale_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICKS)));
    public static final RegistryObject<Block> MOSSY_SHALE_BRICK_STAIRS = register("mossy_shale_brick_stairs", () -> new StairBlock(ModBlocks.SHALE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> MOSSY_SHALE_BRICK_SLAB = register("mossy_shale_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> MOSSY_SHALE_BRICK_WALL = register("mossy_shale_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier) {
        return register(name, blockSupplier, b -> new BlockItem(b, new Item.Properties()));
    }

    public static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, Supplier<Item.Properties> properties) {
        return register(name, blockSupplier, b -> new BlockItem(b, properties.get()));
    }

    public static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, Function<Block, Item> blockItemFunction) {
        RegistryObject<T> block = BLOCKS.register(name, blockSupplier);
        ModItems.ITEMS.register(name, () -> blockItemFunction.apply(block.get()));
        return block;
    }
}
