package com.bretzelfresser.ornithodira.core.init;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.block.CustomEggBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Ornithodira.MODID);

    public static final RegistryObject<CustomEggBlock> PARAREPTILE_EGG = BLOCKS.register("parareptile_egg", () -> new CustomEggBlock(ModRecipes.PARAREPTILE_EGG_ENTITIES::get,.5f, 0f, 0, ModItems.FOSSILIZED_PARAREPTILE_EGG::get, ModItems.PARAREPTILE_EGG::get, BlockBehaviour.Properties.of().dynamicShape().randomTicks().noOcclusion().strength(1.5f).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<CustomEggBlock> SYNAPSID_EGG = BLOCKS.register("synapsid_egg", () -> new CustomEggBlock(ModRecipes.SYNAPSID_EGG_ENTITIES::get,.5f, 0f, 0, ModItems.FOSSILIZED_SYNAPSID_EGG::get, ModItems.SYNAPSID_EGG::get, BlockBehaviour.Properties.of().dynamicShape().randomTicks().noOcclusion().strength(1.5f).pushReaction(PushReaction.DESTROY)));


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
