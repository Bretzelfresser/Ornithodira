package com.bretzelfresser.ornithodira.common.item;

import net.minecraft.world.item.Item;

public class BrushTool extends Item {

    protected final int toolLevel;
    public BrushTool(int toolLevel, Properties pProperties) {
        super(pProperties);
        this.toolLevel = toolLevel;
    }

    public int getToolLevel() {
        return toolLevel;
    }
}
