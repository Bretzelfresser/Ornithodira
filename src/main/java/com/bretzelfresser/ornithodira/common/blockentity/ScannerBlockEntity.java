package com.bretzelfresser.ornithodira.common.blockentity;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.menu.ScannerMenu;
import com.bretzelfresser.ornithodira.core.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ScannerBlockEntity extends InventoryBlockEntity implements GeoBlockEntity {

    protected int scannerLevel;
    protected final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public ScannerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.SCANNER.get(), pPos, pBlockState, 9);
    }

    protected PlayState deployAnimController(final AnimationState<ScannerBlockEntity> state) {
        return PlayState.CONTINUE;
    }

    public void setScannerLevel(int scannerLevel) {
        this.scannerLevel = scannerLevel;
    }

    public int getScannerLevel() {
        return scannerLevel;
    }

    @Override
    protected String setName() {
        return "scanner";
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInv, Player player) {
        return new ScannerMenu(id, playerInv, this);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, this::deployAnimController));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
