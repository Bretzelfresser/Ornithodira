package com.bretzelfresser.ornithodira.core.init;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.entity.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryManager;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Ornithodira.MODID);

    public static final RegistryObject<EntityType<Sanchuansaurus>> SANCHUANSAURUS = register("sanchuansaurus", () -> EntityType.Builder.of(Sanchuansaurus::new, MobCategory.CREATURE).sized(.8f, .6f));

    public static final RegistryObject<EntityType<Taoheodon>> TAOHEODON = register("taoheodon", () -> EntityType.Builder.of(Taoheodon::new, MobCategory.CREATURE).sized(.7f, .5f));

    //Fish
    public static final RegistryObject<EntityType<Dapedium>> DAPEDIUM = register("dapedium", () -> EntityType.Builder.of(Dapedium::new, MobCategory.WATER_AMBIENT).sized(0.7F, 0.4F));
    public static final RegistryObject<EntityType<Cladocyclus>> CLADOCYCLUS = register("cladocyclus", () -> EntityType.Builder.of(Cladocyclus::new, MobCategory.WATER_AMBIENT).sized(0.7F, 0.4F));

    public static final <T extends Entity> RegistryObject<EntityType<T>> register(String name, Supplier<EntityType.Builder<T>> builder){
        return ENTITIES.register(name, () -> builder.get().build(Ornithodira.modLoc(name).toString()));
    }
}
