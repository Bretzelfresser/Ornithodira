package com.bretzelfresser.ornithodira.core.init;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.recipe.EggEntitiesRecipe;
import com.bretzelfresser.ornithodira.common.recipe.ParareptileEggEntitiesRecipe;
import com.bretzelfresser.ornithodira.common.recipe.SynapsidEggEntitiesRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModRecipes {

    protected static final DeferredRegister<RecipeType<?>> RECIPES_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, Ornithodira.MODID);
    protected static final DeferredRegister<RecipeSerializer<?>> RECIPES_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Ornithodira.MODID);


    public static final RegistryObject<RecipeType<ParareptileEggEntitiesRecipe>> PARAREPTILE_EGG_ENTITIES = register("parareptile_egg_entities", () -> ParareptileEggEntitiesRecipe.SERIALIZER);
    public static final RegistryObject<RecipeType<SynapsidEggEntitiesRecipe>> SYNAPSID_EGG_ENTITIES = register("synapsid_egg_entities", () -> SynapsidEggEntitiesRecipe.SERIALIZER);

    public static <T extends Recipe<?>> RegistryObject<RecipeType<T>> register(String name, Supplier<RecipeSerializer<T>> serializer){
        RegistryObject<RecipeType<T>> type = RECIPES_TYPES.register(name, () -> new RecipeType<T>() {
            @Override
            public String toString() {
                return Ornithodira.modLoc(name).toString();
            }
        });
        RECIPES_SERIALIZER.register(name, serializer);
        return type;

    }

    public static void register(IEventBus bus){
        RECIPES_TYPES.register(bus);
        RECIPES_SERIALIZER.register(bus);
    }


}
