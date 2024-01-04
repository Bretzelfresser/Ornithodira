package com.bretzelfresser.ornithodira.core.init;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.block.CustomEggBlock;
import com.bretzelfresser.ornithodira.common.item.BrushTool;
import com.bretzelfresser.ornithodira.common.item.ModBlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Ornithodira.MODID);

    public static final RegistryObject<Item> PARADOX_AMULET = ITEMS.register("paradox_amulet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<BrushTool> METAL_BRUSH = ITEMS.register("metal_brush", () -> new BrushTool(0, new Item.Properties().durability(10)));
    public static final RegistryObject<BrushTool> DIAMOND_BRUSH = ITEMS.register("diamond_brush", () -> new BrushTool(10, new Item.Properties().durability(10)));

    public static final RegistryObject<ModBlockItem> SANCHUANSAURUS_EGG = ITEMS.register("sanchuansaurus_egg", () -> new ModBlockItem(ModBlocks.SANCHUANSAURUS_EGG.get(), state -> state.setValue(CustomEggBlock.FOSSILIZED, false), new Item.Properties()));
    public static final RegistryObject<ModBlockItem> FOSSILIZED_SANCHUANSAURUS_EGG = ITEMS.register("fossilized_sanchuansaurus_egg", () -> new ModBlockItem(ModBlocks.SANCHUANSAURUS_EGG.get(), state -> state.setValue(CustomEggBlock.FOSSILIZED, true), new Item.Properties()));

    public static final RegistryObject<Item> NINGXIAITES_CONE_STICK = ITEMS.register("ningxiaites_cone_on_a_stick", () -> new Item(new Item.Properties()));


    public static final RegistryObject<ForgeSpawnEggItem> SANCHUANSAURUS_SPAWN_EGG = ITEMS.register("sanchuansaurus_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.SANCHUANSAURUS, 0x51382c, 0x993b13, new Item.Properties()));

}
