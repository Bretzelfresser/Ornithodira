package com.bretzelfresser.ornithodira.client.screens;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.menu.ScannerMenu;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.LockIconButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;

public class ScannerScreen extends AbstractContainerScreen<ScannerMenu> {

    public static final ResourceLocation TEXTURE = Ornithodira.modLoc("textures/gui/paradox_scanner_gui.png");

    public ScannerScreen(ScannerMenu p_97741_, Inventory p_97742_, Component p_97743_) {
        super(p_97741_, p_97742_, p_97743_);
    }


    @Override
    protected void init() {
        this.imageWidth = 176;
        this.imageHeight = 188;
        super.init();
        //this.addWidget(new ImageButton())
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int p_282681_, int p_283686_) {
        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);
        guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 4210752, false);
        drawCenteredString(guiGraphics, this.font, Component.translatable("container." + Ornithodira.MODID + ".scanner.possibilities"), 121, this.titleLabelY, 4210752, false);
    }

    public static void drawCenteredString(GuiGraphics graphics, Font pFont, Component pText, int pX, int pY, int pColor, boolean dropShadow) {
        FormattedCharSequence formattedcharsequence = pText.getVisualOrderText();
        graphics.drawString(pFont, formattedcharsequence, pX - pFont.width(formattedcharsequence) / 2, pY, pColor, dropShadow);
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        pGuiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, 176, 166, this.imageWidth, this.imageHeight);
        for (Slot s : this.menu.slots) {
            if (s instanceof ScannerMenu.ScannerLevelSlot scannerLevelSlot && !scannerLevelSlot.isActive()) {
                drawLocks(pGuiGraphics, scannerLevelSlot.getRequiredLevel(), this.leftPos + s.x, this.topPos + s.y);
            }
        }

    }

    protected void drawLocks(GuiGraphics graphics, int level, int x, int y) {
        int offsetX, offsetY;
        switch (level) {
            case 0:
            case 1:
                return;
            case 2:
                offsetX = 45;
                offsetY = 169;
                break;
            default:
                offsetX = 66;
                offsetY = 169;
                break;
        }
        x += 3;
        y += 2;
        graphics.blit(TEXTURE, x, y, offsetX, offsetY, 10, 12, this.imageWidth, this.imageHeight);
    }
}
