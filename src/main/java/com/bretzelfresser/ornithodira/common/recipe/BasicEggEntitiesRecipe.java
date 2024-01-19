package com.bretzelfresser.ornithodira.common.recipe;

import com.bretzelfresser.ornithodira.core.init.ModRecipes;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.List;

public class BasicEggEntitiesRecipe extends EggEntitiesRecipe{

    public static final EggEntitiesSerializer<BasicEggEntitiesRecipe> SERIALIZER = new EggEntitiesSerializer<>(BasicEggEntitiesRecipe::new);

    public BasicEggEntitiesRecipe(ResourceLocation id, List<Pair<EntityType<?>, Integer>> entries) {
        super(id, entries);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.BASIC_EGG_ENTITIES.get();
    }
}
