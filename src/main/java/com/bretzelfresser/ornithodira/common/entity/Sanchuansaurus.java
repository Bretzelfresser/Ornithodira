package com.bretzelfresser.ornithodira.common.entity;

import com.bretzelfresser.ornithodira.common.entity.util.SpecialKeyBindEntity;
import com.bretzelfresser.ornithodira.core.init.ModEntities;
import com.bretzelfresser.ornithodira.core.tags.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DragonEggBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class Sanchuansaurus extends Animal implements GeoEntity, Saddleable, SpecialKeyBindEntity {

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 12d)
                .add(Attributes.ATTACK_DAMAGE, 8d)
                .add(Attributes.MOVEMENT_SPEED, .6d);
    }

    public static final AttributeModifier BABY_MAX_HEALTH = new AttributeModifier("baby_max_health", -9, AttributeModifier.Operation.ADDITION);

    public static final EntityDataAccessor<Boolean> HAS_SADDLE = SynchedEntityData.defineId(Sanchuansaurus.class, EntityDataSerializers.BOOLEAN);

    protected AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    protected int blockBreakCooldown = 0;

    public Sanchuansaurus(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, .6d, true));
        this.goalSelector.addGoal(2, new HurtByTargetGoal(this));

        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, .2f));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6f));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HAS_SADDLE, false);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setSaddled(pCompound.getBoolean("saddled"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("saddled", this.isSaddled());
    }

    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        boolean flag = this.isFood(pPlayer.getItemInHand(pHand));
        if (!flag && this.isSaddled() && !this.isVehicle() && !pPlayer.isSecondaryUseActive()) {
            if (!this.level().isClientSide) {
                pPlayer.startRiding(this);
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else {
            InteractionResult interactionresult = super.mobInteract(pPlayer, pHand);
            if (!interactionresult.consumesAction()) {
                ItemStack itemstack = pPlayer.getItemInHand(pHand);
                return itemstack.is(Items.SADDLE) ? itemstack.interactLivingEntity(pPlayer, this, pHand) : InteractionResult.PASS;
            } else {
                return interactionresult;
            }
        }
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return false;
    }

    protected PlayState predicate(AnimationState<Sanchuansaurus> state) {
        return PlayState.CONTINUE;
    }

    @Override
    @javax.annotation.Nullable
    public LivingEntity getControllingPassenger() {
        if (this.isSaddled()) {
            Entity entity = this.getFirstPassenger();
            if (entity instanceof Player) {
                Player player = (Player) entity;
                if (player.getMainHandItem().is(ModTags.Items.SANCHUANSAURUS_STEER_ITEMS) || player.getOffhandItem().is(ModTags.Items.SANCHUANSAURUS_STEER_ITEMS)) {
                    return player;
                }
            }
        }

        return null;
    }

    @Override
    public void tick() {
        if (!this.level().isClientSide) {
            if (this.blockBreakCooldown > 0)
                this.blockBreakCooldown--;
            if (this.isBaby() && !getAttribute(Attributes.MAX_HEALTH).hasModifier(BABY_MAX_HEALTH)){
                getAttribute(Attributes.MAX_HEALTH).addTransientModifier(BABY_MAX_HEALTH);
            }else if (!this.isBaby() && getAttribute(Attributes.MAX_HEALTH).hasModifier(BABY_MAX_HEALTH)){
                getAttribute(Attributes.MAX_HEALTH).removeModifier(BABY_MAX_HEALTH);
                this.heal(16);
            }
        }
        super.tick();
    }

    @Override
    public boolean isSaddleable() {
        return this.isAlive() && !this.isBaby();
    }

    @Override
    public void equipSaddle(@Nullable SoundSource pSource) {
        setSaddled(true);
        if (pSource != null) {
            this.level().playSound((Player) null, this, SoundEvents.PIG_SADDLE, pSource, 0.5F, 1.0F);
        }
    }

    public boolean isSaddled() {
        return this.entityData.get(HAS_SADDLE);
    }

    public void setSaddled(boolean saddled) {
        this.entityData.set(HAS_SADDLE, saddled);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return ModEntities.SANCHUANSAURUS.get().create(pLevel);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, 10, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    protected void dropEquipment() {
        super.dropEquipment();
        if (this.isSaddled()) {
            this.spawnAtLocation(Items.SADDLE);
        }

    }

    @Override
    protected Vec3 getRiddenInput(Player pPlayer, Vec3 pTravelVector) {
        return new Vec3(0.0D, 0.0D, 1d);
    }

    @Override
    protected float getRiddenSpeed(Player pPlayer) {
        return (float) (this.getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.225D * 3.5d / 5d);
    }

    @Override
    public double getPassengersRidingOffset() {
        return super.getPassengersRidingOffset() - .1d;
    }

    @Override
    protected void tickRidden(Player pPlayer, Vec3 pTravelVector) {
        super.tickRidden(pPlayer, pTravelVector);
        this.setRot(pPlayer.getYRot(), pPlayer.getXRot() * 0.5F);
        this.yRotO = this.yBodyRot = this.yHeadRot = this.getYRot();
    }

    @Override
    public Vec3 getDismountLocationForPassenger(LivingEntity pLivingEntity) {
        Direction direction = this.getMotionDirection();
        if (direction.getAxis() != Direction.Axis.Y) {
            int[][] aint = DismountHelper.offsetsForDirection(direction);
            BlockPos blockpos = this.blockPosition();
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for (Pose pose : pLivingEntity.getDismountPoses()) {
                AABB aabb = pLivingEntity.getLocalBoundsForPose(pose);

                for (int[] aint1 : aint) {
                    blockpos$mutableblockpos.set(blockpos.getX() + aint1[0], blockpos.getY(), blockpos.getZ() + aint1[1]);
                    double d0 = this.level().getBlockFloorHeight(blockpos$mutableblockpos);
                    if (DismountHelper.isBlockFloorValid(d0)) {
                        Vec3 vec3 = Vec3.upFromBottomCenterOf(blockpos$mutableblockpos, d0);
                        if (DismountHelper.canDismountTo(this.level(), pLivingEntity, aabb.move(vec3))) {
                            pLivingEntity.setPose(pose);
                            return vec3;
                        }
                    }
                }
            }

        }
        return super.getDismountLocationForPassenger(pLivingEntity);
    }

    public int getBlockBreakMaxCooldown() {
        return 5 * 20;
    }

    @Override
    public boolean canExecute(Player sender) {
        return this.blockBreakCooldown <= 0 && this.getControllingPassenger() != null && this.isVehicle() && this.getControllingPassenger().getUUID().equals(sender.getUUID());
    }

    @Override
    public void execute(Player sender) {
        Direction facing = this.getMotionDirection();
        BlockPos firstPos = this.getOnPos().relative(facing);
        boolean destroyedOneBlock = false;
        for (int y = 1; y <= 2; y++) {
            BlockPos pos = firstPos.above(y);
            BlockState state = level().getBlockState(pos);
            if (state.getDestroySpeed(level(), pos) > 0 && ForgeEventFactory.doPlayerHarvestCheck(sender, state, !state.requiresCorrectToolForDrops() || Items.IRON_PICKAXE.isCorrectToolForDrops(new ItemStack(Items.IRON_PICKAXE), state))) {
                if (state.getBlock() instanceof DragonEggBlock eggBlock){
                    eggBlock.attack(state, level(), pos, sender);
                }else {
                    this.level().destroyBlock(pos, true, this, 1 << 5);
                }
                destroyedOneBlock = true;
            }
        }
        if (destroyedOneBlock) {
            this.blockBreakCooldown = getBlockBreakMaxCooldown();
            ForgeRegistries.ITEMS.tags().getTag(ModTags.Items.SANCHUANSAURUS_STEER_ITEMS).forEach(item -> sender.getCooldowns().addCooldown(item, getBlockBreakMaxCooldown()));
        }
    }
}
