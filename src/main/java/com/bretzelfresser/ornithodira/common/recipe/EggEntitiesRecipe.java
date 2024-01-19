package com.bretzelfresser.ornithodira.common.recipe;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class EggEntitiesRecipe implements Recipe<Container> {


    protected final ResourceLocation id;
    protected final List<Pair<EntityType<?>, Integer>> entries;

    public EggEntitiesRecipe(ResourceLocation id, List<Pair<EntityType<?>, Integer>> entries) {
        this.id = id;
        this.entries = entries;
    }

    public List<Pair<EntityType<?>, Integer>> getEntries() {
        return ImmutableList.copyOf(this.entries);
    }

    @Override
    public boolean matches(Container pContainer, Level pLevel) {
        return true;
    }

    @Override
    public ItemStack assemble(Container pContainer, RegistryAccess pRegistryAccess) {
        return null;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return false;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return null;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    public static class EggEntitiesSerializer<T extends EggEntitiesRecipe> implements RecipeSerializer<T>{

        protected final RecipeSupplier<T> supplier;

        public EggEntitiesSerializer(RecipeSupplier<T> supplier) {
            this.supplier = supplier;
        }


        @Override
        public T fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            JsonArray entries = GsonHelper.getAsJsonArray(pSerializedRecipe,"entries");
            List<Pair<EntityType<?>, Integer>> entities = new ArrayList<>();
            for (JsonElement el : entries){
                JsonObject pair = el.getAsJsonObject();
                int weight = GsonHelper.getAsInt(pair, "weight", 1);
                EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(GsonHelper.getAsString(pair, "entity")));
                entities.add(Pair.of(type, weight));
            }
            return supplier.createRecipe(pRecipeId, entities);
        }

        @Override
        public @Nullable T fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            int size = pBuffer.readVarInt();
            List<Pair<EntityType<?>, Integer>> entities = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                int weight = pBuffer.readVarInt();
                EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(pBuffer.readResourceLocation());
                entities.add(Pair.of(type, weight));
            }
            return supplier.createRecipe(pRecipeId, entities);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, T pRecipe) {
            pBuffer.writeVarInt(pRecipe.entries.size());
            for (Pair<EntityType<?>, Integer> pair : pRecipe.entries){
                pBuffer.writeVarInt(pair.getSecond());
                pBuffer.writeResourceLocation(ForgeRegistries.ENTITY_TYPES.getKey(pair.getFirst()));
            }
        }
    }

    public static interface RecipeSupplier<T>{
        T createRecipe(ResourceLocation id, List<Pair<EntityType<?>, Integer>> entries);
    }

}
