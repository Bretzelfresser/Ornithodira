package com.bretzelfresser.ornithodira.common.blockentity;

import com.bretzelfresser.ornithodira.Ornithodira;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Consumer;

public abstract class InventoryBlockEntity extends BlockEntity implements RecipeHolder, StackedContentsCompatible, Container, MenuProvider {

    protected final int slots;
    protected final LazyOptional<IItemHandlerModifiable> inventory;
    protected final Object2IntOpenHashMap<ResourceLocation> recipes = new Object2IntOpenHashMap<>();

    public InventoryBlockEntity(BlockEntityType<?> typeIn, BlockPos pPos, BlockState pBlockState, int slots) {
        super(typeIn, pPos, pBlockState);
        this.slots = slots;
        this.inventory = LazyOptional.of(() -> createInventory(slots));
    }

    @Override
    public boolean isEmpty() {
        IItemHandlerModifiable inv = getInventory();
        for (int i = 0; i < inv.getSlots(); i++) {
            if (!inv.getStackInSlot(i).isEmpty())
                return false;
        }
        return true;
    }

    public void forAllItems(Consumer<ItemStack> forEach){
        IItemHandlerModifiable inv = getInventory();
        for (int i = 0; i < inv.getSlots(); i++) {
            forEach.accept(inv.getStackInSlot(i));
        }
    }

    @Override
    public ItemStack getItem(int index) {
        return getInventory().getStackInSlot(index);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        return getInventory().extractItem(index, count, false);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return getInventory().extractItem(index,Integer.MAX_VALUE, false);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        getInventory().setStackInSlot(index, stack);
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }


    public IItemHandlerModifiable getInventory() {
        return inventory.orElseThrow(() -> new IllegalStateException("inventory not initialized correctly"));
    }


    @Override
    public Component getDisplayName() {
        return Component.translatable("container." + Ornithodira.MODID + "." + setName());
    }

    @Override
    public int getContainerSize() {
        return this.slots;
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        writeRecipesUsed(compound);
        writeItems(compound);
    }

    protected void readRecipesUsed(CompoundTag nbt){
        CompoundTag compoundnbt = nbt.getCompound("RecipesUsed");

        for(String s : compoundnbt.getAllKeys()) {
            this.recipes.put(new ResourceLocation(s), compoundnbt.getInt(s));
        }
    }

    protected void writeRecipesUsed(CompoundTag nbt){
        CompoundTag recipes = new CompoundTag();
        this.recipes.forEach((recipeId, craftedAmount) -> {
            recipes.putInt(recipeId.toString(), craftedAmount);
        });
        nbt.put("RecipesUsed", recipes);
    }

    protected void writeItems(CompoundTag compound) {
        if (getInventory() instanceof INBTSerializable) {
            compound.put("inventory", ((INBTSerializable<Tag>) getInventory()).serializeNBT());
        }
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        readRecipesUsed(nbt);
        readItems(nbt);
        this.setChanged();
    }

    protected void readItems(CompoundTag nbt) {
        if (nbt.contains("inventory") && getInventory() instanceof INBTSerializable) {
            ((INBTSerializable<Tag>) getInventory()).deserializeNBT(nbt.get("inventory"));
        }
    }

    @Override
    public void fillStackedContents(StackedContents helper) {
        forAllItems(helper::accountStack);
    }

    public void setRecipeUsed(@Nullable Recipe<?> recipe) {
        if (recipe != null) {
            ResourceLocation resourcelocation = recipe.getId();
            this.recipes.addTo(resourcelocation, 1);
        }

    }

    @Override
    public void clearContent() {
        IItemHandlerModifiable inv = getInventory();
        for (int i = 0; i < inv.getSlots(); i++) {
            inv.setStackInSlot(i, ItemStack.EMPTY);
        }
    }

    @Override
    @Nullable
    public Recipe<?> getRecipeUsed() {
        return null;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (!this.remove && cap == ForgeCapabilities.ITEM_HANDLER){
            return this.inventory.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.inventory.invalidate();
    }

    /**
     * @return the name of the container, will create a translation key like:
     * container.MODID.yourName
     */
    protected abstract String setName();

    /**
     * called by the constructor in order to create the inventory
     * u may put ur own inventory handler here but keep in mind that it will only be saved to the nbt when it implements {@link INBTSerializable}
     *
     * @param slots the amount of slots the inventory should have
     * @return the new inventory
     */
    protected IItemHandlerModifiable createInventory(int slots) {
        return new ItemStackHandler(slots);
    }
}
