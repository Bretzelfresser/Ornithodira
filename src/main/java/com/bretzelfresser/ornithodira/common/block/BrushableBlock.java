package com.bretzelfresser.ornithodira.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface BrushableBlock {

    int getMinRequiredToolLevel();

    boolean canBrush(BlockState state);

    void brush(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand);

}
