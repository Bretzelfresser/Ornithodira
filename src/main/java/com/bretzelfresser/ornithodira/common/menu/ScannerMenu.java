package com.bretzelfresser.ornithodira.common.menu;

import com.bretzelfresser.ornithodira.common.blockentity.ScannerBlockEntity;
import com.bretzelfresser.ornithodira.core.init.ModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class ScannerMenu extends BaseTileEntityMenu<ScannerBlockEntity> {
    public ScannerMenu(int id, Inventory inv, ScannerBlockEntity tileEntity) {
        super(ModMenus.SCANNER_MENU.get(), id, inv, tileEntity);
    }

    public ScannerMenu(int id, Inventory inv, FriendlyByteBuf buffer) {
        super(ModMenus.SCANNER_MENU.get(), id, inv, buffer);
    }

    @Override
    public void init() {

        this.addSlot(new Slot(this.tileEntity, 0, 26, 36));

        int newIndex = this.addHorizontalSlots(this.tileEntity, 1, 82, 24, 4, 21, (scanner, index, x, y) -> {
            if (index < 3 + 1) {
                return new ScannerLevelSlot(scanner, 1, index, x, y);
            } else
                return new ScannerLevelSlot(scanner, 2, index, x, y);
        });
        this.addHorizontalSlots(this.tileEntity, newIndex, 82, 45, 4, 21, (scanner, index, x, y) -> {
            if (index < 1 + newIndex) {
                return new ScannerLevelSlot(scanner, 2, index, x, y);
            } else
                return new ScannerLevelSlot(scanner, 3, index, x, y);
        });

        addPlayerInventory(8, 84);

    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            //move from resultSlot
            if (index > 1 && index < getPlayerBeginSlots()) {
                if (!this.moveItemStackTo(itemstack1,  getPlayerBeginSlots(), getPlayerBeginSlots() + 36, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
                //this moves from the player inventory
            } else if (index >= getPlayerBeginSlots()) {
                //check if we can move it to slot 0
                if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                    return ItemStack.EMPTY;
                    //handle clicked on player inv not the hotbar
                } else if (index >= getPlayerBeginSlots() && index < getPlayerBeginSlots() + 27) {
                    if (!this.moveItemStackTo(itemstack1, getPlayerBeginSlots() + 27, getPlayerBeginSlots() + 27 + 9, false)) {
                        return ItemStack.EMPTY;
                    }
                    //move ot away from the hotbar
                } else if (index >= getPlayerBeginSlots() + 27 && index < getPlayerBeginSlots() + 27 + 9 && !this.moveItemStackTo(itemstack1, getPlayerBeginSlots(), getPlayerBeginSlots() + 27, false)) {
                    return ItemStack.EMPTY;
                }
                //just move it somewhere into the players inventory
            } else if (!this.moveItemStackTo(itemstack1, getPlayerBeginSlots(), getPlayerBeginSlots() + 36, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }

    public static class ScannerLevelSlot extends LockedSlot {

        protected final ScannerBlockEntity scanner;
        protected final int requiredLevel;

        public ScannerLevelSlot(ScannerBlockEntity inventoryIn, int requiredLevel, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
            this.scanner = inventoryIn;
            this.requiredLevel = requiredLevel;
        }

        @Override
        public boolean isActive() {
            return scanner.getScannerLevel() >= requiredLevel;
        }

        public int getRequiredLevel() {
            return requiredLevel;
        }
    }
}
