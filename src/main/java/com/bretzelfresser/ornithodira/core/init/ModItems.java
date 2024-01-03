package com.bretzelfresser.ornithodira.core.init;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Ornithodira.MODID);

    public static final RegistryObject<Item> PARADOX_AMULET = ITEMS.register("paradox_amulet", () -> new Item(new Item.Properties()));


    public static final RegistryObject<ForgeSpawnEggItem> SANCHUANSAURUS_SPAWN_EGG = ITEMS.register("sanchuansaurus_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.SANCHUANSAURUS, 0x51382c, 0x993b13, new Item.Properties()));

}
