package com.bretzelfresser.ornithodira.common.block;

import com.bretzelfresser.ornithodira.common.blockentity.ScannerBlockEntity;
import com.bretzelfresser.ornithodira.core.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class ScannerBlock extends RotatableBlock implements EntityBlock {

    protected final int scannerLevel;

    public ScannerBlock(int scannerLevel) {
        this(scannerLevel, BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).strength(25F, 1200F).noOcclusion());
    }

    public ScannerBlock(int scannerLevel, BlockBehaviour.Properties properties) {
        super(properties);
        this.scannerLevel = scannerLevel;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide){
            BlockEntity be = pLevel.getBlockEntity(pPos);
            if (be instanceof ScannerBlockEntity scannerBlockEntity){
                NetworkHooks.openScreen((ServerPlayer) pPlayer, scannerBlockEntity, pPos);
                return InteractionResult.SUCCESS;
            }
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Override
    public RenderShape getRenderShape(BlockState p_60550_) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        ScannerBlockEntity te = ModBlockEntities.SCANNER.get().create(pos, state);
        te.setScannerLevel(this.scannerLevel);
        return te;
    }
}
