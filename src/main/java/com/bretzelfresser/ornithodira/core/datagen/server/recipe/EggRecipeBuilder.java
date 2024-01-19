package com.bretzelfresser.ornithodira.core.datagen.server.recipe;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.recipe.EggEntitiesRecipe;
import com.bretzelfresser.ornithodira.common.util.ResourceLocationUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EggRecipeBuilder {

    public static EggRecipeBuilder builder(RecipeSerializer<? extends EggEntitiesRecipe> serializer){
        return new EggRecipeBuilder(serializer);
    }

    protected List<Pair<EntityType<?>, Integer>> entities = new ArrayList<>();
    protected final RecipeSerializer<? extends EggEntitiesRecipe> serializer;

    protected EggRecipeBuilder(RecipeSerializer<? extends EggEntitiesRecipe> serializer) {
        this.serializer = serializer;
    }

    public EggRecipeBuilder add(int weight, EntityType<?>... types){
        for (EntityType<?> type: types) {
            this.entities.add(Pair.of(type, weight));
        }
        return this;
    }

    public EggRecipeBuilder add(int weight, TagKey<EntityType<?>> tag){
        ForgeRegistries.ENTITY_TYPES.tags().getTag(tag).forEach(type -> entities.add(Pair.of(type, weight)));
        return this;
    }

    public void save(Consumer<FinishedRecipe> consumer, String name){
        save(consumer, ResourceLocationUtils.prepend(Ornithodira.modLoc(name), "egg_entities/"));
    }

    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation id){
        consumer.accept(new Result(serializer, entities, id));
    }


    private static class Result implements FinishedRecipe{

        protected final RecipeSerializer<? extends EggEntitiesRecipe> serializer;
        protected final List<Pair<EntityType<?>, Integer>> entities;
        protected final ResourceLocation id;

        public Result(RecipeSerializer<? extends EggEntitiesRecipe> serializer, List<Pair<EntityType<?>, Integer>> entities, ResourceLocation id) {
            this.serializer = serializer;
            this.entities = entities;
            this.id = id;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            JsonArray array = new JsonArray();
            for (Pair<EntityType<?>, Integer> pair : this.entities){
                JsonObject obj = new JsonObject();
                obj.addProperty("entity", ForgeRegistries.ENTITY_TYPES.getKey(pair.getFirst()).toString());
                obj.addProperty("weight", pair.getSecond());
                array.add(obj);
            }
            pJson.add("entries", array);
        }

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return this.serializer;
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }
}
