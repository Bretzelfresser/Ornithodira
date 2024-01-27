package com.bretzelfresser.ornithodira.common.entity;

import com.bretzelfresser.ornithodira.core.init.ModEntities;
import com.bretzelfresser.ornithodira.core.init.ModLootTables;
import com.bretzelfresser.ornithodira.core.tags.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.EnumSet;
import java.util.List;

public class Taoheodon extends Animal implements GeoEntity {

    public static final EntityDataAccessor<Boolean> DIGGING = SynchedEntityData.defineId(Taoheodon.class, EntityDataSerializers.BOOLEAN);

    protected int diggingCooldown, diggingAnimationCooldown;

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 10d)
                .add(Attributes.ATTACK_DAMAGE, 8d)
                .add(Attributes.MOVEMENT_SPEED, .25d);
    }

    protected final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public Taoheodon(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected PlayState predicate(AnimationState<Taoheodon> state) {
        return PlayState.CONTINUE;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4d));
        this.goalSelector.addGoal(2, new DiggingGoal(this, 40, 1000, 300));

        this.goalSelector.addGoal(3, new FindDiggingPosition(this, 20));

        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6f) {
            @Override
            public boolean canContinueToUse() {
                return !isDigging() && super.canContinueToUse();
            }

            @Override
            public boolean canUse() {
                return !isDigging() && super.canUse();
            }
        });
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this) {
            @Override
            public boolean canContinueToUse() {
                return !isDigging() && super.canContinueToUse();
            }

            @Override
            public boolean canUse() {
                return !isDigging() && super.canUse();
            }
        });
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1d) {
            @Override
            public boolean canContinueToUse() {
                return !isDigging() && super.canContinueToUse();
            }

            @Override
            public boolean canUse() {
                return !isDigging() && super.canUse();
            }
        });
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DIGGING, false);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return false;
    }

    public boolean canDigOnBlock(BlockState state) {
        return state.is(ModTags.Blocks.TAHOEODON_DIG_BLOCKS);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("digging", isDigging());
        pCompound.putInt("diggingCooldown", this.diggingCooldown);
        pCompound.putInt("diggingAnimationCooldown", this.diggingAnimationCooldown);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.entityData.set(DIGGING, pCompound.getBoolean("digging"));
        this.diggingCooldown = pCompound.getInt("diggingCooldown");
        this.diggingAnimationCooldown = pCompound.getInt("diggingAnimationCooldown");
    }

    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide) {
            if (this.diggingCooldown > 0)
                this.diggingCooldown--;
        }
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 10) {
            BlockParticleOption option = new BlockParticleOption(ParticleTypes.BLOCK, level().getBlockState(getOnPos()));
            for (int i = 0; i < 10; i++) {
                level().addParticle(option, getX(), getY(), getZ(), 0, 0.1f, 0);
            }
        } else
            super.handleEntityEvent(pId);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return ModEntities.TAOHEODON.get().create(pLevel);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, 10, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    protected void setDigging(boolean digging) {
        this.entityData.set(DIGGING, digging);
    }

    public boolean isDigging() {
        return entityData.get(DIGGING);
    }

    protected boolean shouldPanic() {
        return getLastHurtByMob() != null || isFreezing() || isOnFire();
    }

    public static class FindDiggingPosition extends Goal {

        protected final Taoheodon animal;
        protected final int searchRange;
        protected BlockPos target;
        protected int stuckCooldown;

        public FindDiggingPosition(Taoheodon animal, int searchRange) {
            this.animal = animal;
            this.searchRange = searchRange;
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            if (!this.animal.canDigOnBlock(this.animal.getBlockStateOn()) && !animal.shouldPanic() && animal.getLastHurtByMob() == null && !animal.isBaby() && animal.diggingCooldown <= 0) {
                findTarget();
                return this.target != null;
            }
            return false;
        }


        protected void findTarget() {
            this.target = null;
            for (int i = 0; i < 10; i++) {
                BlockPos pos = new BlockPos((int) (this.animal.getX() + this.animal.random.nextInt(2 * searchRange + 1) - searchRange), animal.level().getMaxBuildHeight(), (int) (this.animal.getZ() + this.animal.random.nextInt(2 * searchRange + 1) - searchRange));
                while (pos.getY() > 0 && !animal.canDigOnBlock(animal.level().getBlockState(pos)) && !animal.level().getBlockState(pos).isCollisionShapeFullBlock(animal.level(), pos)) {
                    pos = pos.below();
                }
                if (animal.canDigOnBlock(animal.level().getBlockState(pos))) {
                    this.target = pos;
                    return;
                }
            }
        }

        @Override
        public void start() {
            this.animal.navigation.moveTo(this.target.getX(), this.target.getY(), this.target.getZ(), 1d);
        }


        @Override
        public void tick() {
            this.animal.getLookControl().setLookAt(Vec3.atCenterOf(this.target));
            if (!this.animal.navigation.moveTo(this.target.getX(), this.target.getY(), this.target.getZ(), 1d)) {
                stuckCooldown++;
            } else {
                stuckCooldown = 0;
            }
        }

        @Override
        public boolean canContinueToUse() {
            if (stuckCooldown > 200) {
                return false;
            }if (this.animal.distanceToSqr(Vec3.atCenterOf(this.target)) <= .25d || this.animal.getNavigation().isDone()) {
                return false;
            }
            return !this.animal.canDigOnBlock(this.animal.getBlockStateOn()) && !animal.shouldPanic() && animal.getLastHurtByMob() == null && !animal.isBaby() && animal.diggingCooldown <= 0;
        }

        @Override
        public void stop() {
            this.animal.getNavigation().stop();
            this.target = null;
            this.stuckCooldown = 0;
        }
    }


    public static class DiggingGoal extends Goal {
        protected final Taoheodon animal;
        protected final int animationCooldown, avgCooldown, range;

        /**
         * @param animal            the animal this goa is applied to
         * @param animationCooldown the time it takes to fully play the animation
         * @param avgCooldown       the average cooldown until the entity can dig again
         * @param range             the range around the avg cooldown
         *                          note that the cooldown is binomial distributed
         */
        public DiggingGoal(Taoheodon animal, int animationCooldown, int avgCooldown, int range) {
            this.animal = animal;
            this.animationCooldown = animationCooldown;
            this.avgCooldown = avgCooldown;
            this.range = range;
        }


        @Override
        public boolean canUse() {
            return animal.canDigOnBlock(animal.getBlockStateOn()) && !animal.shouldPanic() && animal.getLastHurtByMob() == null && !animal.isBaby() && animal.diggingCooldown <= 0;
        }

        @Override
        public void start() {
            animal.setDigging(true);
            animal.diggingAnimationCooldown = animationCooldown;
            animal.goalSelector.disableControlFlag(Flag.MOVE);
            animal.goalSelector.disableControlFlag(Flag.LOOK);
        }

        @Override
        public void tick() {
            if (animal.diggingAnimationCooldown > 0) {
                animal.diggingAnimationCooldown--;
                animal.level().broadcastEntityEvent(animal, (byte) 10);
            }
            if (animal.diggingAnimationCooldown <= 0) {
                animal.diggingCooldown = avgCooldown + animal.random.nextInt(range) - animal.random.nextInt(range);
                spawnDrops();
            }
        }

        protected void spawnDrops() {
            LootParams params = new LootParams.Builder((ServerLevel) animal.level()).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(animal.getOnPos())).withParameter(LootContextParams.THIS_ENTITY, animal).create(LootContextParamSets.ARCHAEOLOGY);
            LootTable loottable = animal.level().getServer().getLootData().getLootTable(ModLootTables.TAOHEODON_DIGG_LOOT);
            List<ItemStack> list = loottable.getRandomItems(params);
            list.forEach(animal::spawnAtLocation);
        }

        @Override
        public void stop() {
            animal.goalSelector.enableControlFlag(Flag.MOVE);
            animal.goalSelector.enableControlFlag(Flag.LOOK);
            animal.setDigging(false);
            animal.diggingAnimationCooldown = 0;
        }
    }
}
