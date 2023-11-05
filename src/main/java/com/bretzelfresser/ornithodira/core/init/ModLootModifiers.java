package com.bretzelfresser.ornithodira.core.init;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.loot.AddItemModifier;
import com.mojang.serialization.Codec;
import net.minecraft.world.entity.animal.Cod;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifiers {

    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZER = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Ornithodira.MODID);

    public static final RegistryObject<Codec<AddItemModifier>> ADD_ITEM_MODIFIER_SERIALIZER = LOOT_MODIFIER_SERIALIZER.register("add_item", AddItemModifier.CODEC);
}
