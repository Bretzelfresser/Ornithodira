package com.bretzelfresser.ornithodira.core.init;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.block.CustomEggBlock;
import com.bretzelfresser.ornithodira.common.block.ScannerBlock;
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

    public static final RegistryObject<ScannerBlock> SCANNER_1 = register("paradox_scanner_1", () -> new ScannerBlock(1), () -> new Item.Properties().fireResistant());
    public static final RegistryObject<ScannerBlock> SCANNER_2 = register("paradox_scanner_2", () -> new ScannerBlock(2), () -> new Item.Properties().fireResistant());

    public static final RegistryObject<CustomEggBlock> SANCHUANSAURUS_EGG = BLOCKS.register("sanchuansaurus_egg", () -> new CustomEggBlock(ModEntities.SANCHUANSAURUS::get,.5f, 0f, 0, ModItems.FOSSILIZED_SANCHUANSAURUS_EGG::get, ModItems.SANCHUANSAURUS_EGG::get, BlockBehaviour.Properties.of().dynamicShape().randomTicks().noOcclusion().strength(1.5f).pushReaction(PushReaction.DESTROY)));


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
