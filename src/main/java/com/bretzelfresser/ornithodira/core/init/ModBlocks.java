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
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
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
    public static Block shaleBlock(MapColor mapColor) { // From Blocks.TUFF
        return new Block(BlockBehaviour.Properties.of().mapColor(mapColor).instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.TUFF).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    }
    public static Block cobbledShaleBlock(MapColor mapColor) {
        return new Block(BlockBehaviour.Properties.of().mapColor(mapColor).instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.TUFF).requiresCorrectToolForDrops().strength(2.0F, 6.0F));
    }

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Ornithodira.MODID);

    public static final RegistryObject<CustomEggBlock> PARAREPTILE_EGG = BLOCKS.register("parareptile_egg",
            () -> eggBlock(ModRecipes.PARAREPTILE_EGG_ENTITIES::get, ModItems.FOSSILIZED_PARAREPTILE_EGG::get, ModItems.PARAREPTILE_EGG::get));
            //new CustomEggBlock(ModRecipes.PARAREPTILE_EGG_ENTITIES::get,.5f, 0f, 0, ModItems.FOSSILIZED_PARAREPTILE_EGG::get, ModItems.PARAREPTILE_EGG::get, BlockBehaviour.Properties.of().dynamicShape().randomTicks().noOcclusion().strength(1.5f).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<CustomEggBlock> SYNAPSID_EGG = BLOCKS.register("synapsid_egg",
            () -> eggBlock(ModRecipes.SYNAPSID_EGG_ENTITIES::get, ModItems.FOSSILIZED_SYNAPSID_EGG::get, ModItems.SYNAPSID_EGG::get));
            // new CustomEggBlock(ModRecipes.SYNAPSID_EGG_ENTITIES::get,.5f, 0f, 0, ModItems.FOSSILIZED_SYNAPSID_EGG::get, ModItems.SYNAPSID_EGG::get, BlockBehaviour.Properties.of().dynamicShape().randomTicks().noOcclusion().strength(1.5f).pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<NingxiatesConeBlock> FOSSILIZED_NINGXIATES_CONE_BLOCK = register("fossilized_ningxiaites_cone", () -> new NingxiatesConeBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).dynamicShape().noOcclusion().strength(1.5f)));


    public static final RegistryObject<Block> BROWN_SHALE = register("brown_shale", () -> shaleBlock(MapColor.TERRACOTTA_BROWN));
    public static final RegistryObject<Block> BROWN_SHALE_STAIRS = register("brown_shale_stairs", () -> new StairBlock(ModBlocks.BROWN_SHALE.get().defaultBlockState(), BlockBehaviour.Properties.copy(ModBlocks.BROWN_SHALE.get())));
    public static final RegistryObject<Block> BROWN_SHALE_SLAB = register("brown_shale_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(ModBlocks.BROWN_SHALE.get())));
    public static final RegistryObject<Block> BROWN_SHALE_WALL = register("brown_shale_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(ModBlocks.BROWN_SHALE.get())));

    public static final RegistryObject<Block> COBBLED_BROWN_SHALE = register("cobbled_brown_shale", () -> cobbledShaleBlock(MapColor.TERRACOTTA_BROWN));
    public static final RegistryObject<Block> COBBLED_BROWN_SHALE_STAIRS = register("cobbled_brown_shale_stairs", () -> new StairBlock(ModBlocks.COBBLED_BROWN_SHALE.get().defaultBlockState(), BlockBehaviour.Properties.copy(ModBlocks.COBBLED_BROWN_SHALE.get())));
    public static final RegistryObject<Block> COBBLED_BROWN_SHALE_SLAB = register("cobbled_brown_shale_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(ModBlocks.COBBLED_BROWN_SHALE.get())));
    public static final RegistryObject<Block> COBBLED_BROWN_SHALE_WALL = register("cobbled_brown_shale_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(ModBlocks.COBBLED_BROWN_SHALE.get())));

    public static final RegistryObject<Block> POLISHED_BROWN_SHALE = register("polished_brown_shale", () -> new Block(BlockBehaviour.Properties.copy(ModBlocks.BROWN_SHALE.get())));
    public static final RegistryObject<Block> POLISHED_BROWN_SHALE_STAIRS = register("polished_brown_shale_stairs", () -> new StairBlock(ModBlocks.POLISHED_BROWN_SHALE.get().defaultBlockState(), BlockBehaviour.Properties.copy(ModBlocks.BROWN_SHALE.get())));
    public static final RegistryObject<Block> POLISHED_BROWN_SHALE_SLAB = register("polished_brown_shale_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(ModBlocks.BROWN_SHALE.get())));
    public static final RegistryObject<Block> POLISHED_BROWN_SHALE_WALL = register("polished_brown_shale_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(ModBlocks.BROWN_SHALE.get())));

    public static final RegistryObject<Block> CHISELED_BROWN_SHALE = register("chiseled_brown_shale", () -> new Block(BlockBehaviour.Properties.copy(ModBlocks.BROWN_SHALE.get())));

    public static final RegistryObject<Block> BROWN_SHALE_BRICKS = register("brown_shale_bricks", () -> new Block(BlockBehaviour.Properties.copy(ModBlocks.BROWN_SHALE.get())));
    public static final RegistryObject<Block> BROWN_SHALE_BRICK_STAIRS = register("brown_shale_brick_stairs", () -> new StairBlock(ModBlocks.BROWN_SHALE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(ModBlocks.BROWN_SHALE.get())));
    public static final RegistryObject<Block> BROWN_SHALE_BRICK_SLAB = register("brown_shale_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(ModBlocks.BROWN_SHALE.get())));
    public static final RegistryObject<Block> BROWN_SHALE_BRICK_WALL = register("brown_shale_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(ModBlocks.BROWN_SHALE.get())));

    public static final RegistryObject<Block> MOSSY_BROWN_SHALE_BRICKS = register("mossy_brown_shale_bricks", () -> new Block(BlockBehaviour.Properties.copy(ModBlocks.BROWN_SHALE.get())));
    public static final RegistryObject<Block> MOSSY_BROWN_SHALE_BRICK_STAIRS = register("mossy_brown_shale_brick_stairs", () -> new StairBlock(ModBlocks.MOSSY_BROWN_SHALE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(ModBlocks.BROWN_SHALE.get())));
    public static final RegistryObject<Block> MOSSY_BROWN_SHALE_BRICK_SLAB = register("mossy_brown_shale_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(ModBlocks.BROWN_SHALE.get())));
    public static final RegistryObject<Block> MOSSY_BROWN_SHALE_BRICK_WALL = register("mossy_brown_shale_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(ModBlocks.BROWN_SHALE.get())));

    public static final RegistryObject<Block> CRACKED_BROWN_SHALE_BRICKS = register("cracked_brown_shale_bricks", () -> new Block(BlockBehaviour.Properties.copy(ModBlocks.BROWN_SHALE.get())));

    public static final RegistryObject<Block> GRAY_SHALE = register("gray_shale", () -> shaleBlock(MapColor.TERRACOTTA_GRAY));
    public static final RegistryObject<Block> RED_SHALE = register("red_shale", () -> shaleBlock(MapColor.TERRACOTTA_RED));
    public static final RegistryObject<Block> RICH_RED_SHALE = register("rich_red_shale", () -> shaleBlock(MapColor.TERRACOTTA_RED));



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
