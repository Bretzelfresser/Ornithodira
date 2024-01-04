package com.bretzelfresser.ornithodira.common.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class ModBlockItem extends BlockItem {

    protected final Function<BlockState, BlockState> placementMapper;

    public ModBlockItem(Block pBlock, Function<BlockState, BlockState> placementMapper, Properties pProperties) {
        super(pBlock, pProperties);
        this.placementMapper = placementMapper;
    }

    public ModBlockItem(Block pBlock, Properties pProperties) {
        this(pBlock, null, pProperties);
    }

    @Nullable
    @Override
    protected BlockState getPlacementState(BlockPlaceContext pContext) {
        BlockState blockstate = this.getBlock().getStateForPlacement(pContext);
        if (this.placementMapper != null)
            blockstate = placementMapper.apply(blockstate);
        return blockstate != null && this.canPlace(pContext, blockstate) ? blockstate : null;
    }

    @Override
    public String getDescriptionId() {
        return getOrCreateDescriptionId();
    }
}
