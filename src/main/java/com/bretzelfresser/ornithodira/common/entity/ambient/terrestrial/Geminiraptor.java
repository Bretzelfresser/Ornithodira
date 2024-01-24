package com.bretzelfresser.ornithodira.common.entity.ambient.terrestrial;

import com.bretzelfresser.ornithodira.common.util.GeckoUtils;
import com.bretzelfresser.ornithodira.core.init.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class Geminiraptor extends AgeableMob implements GeoEntity {


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8D).add(Attributes.MOVEMENT_SPEED, (double) 0.4F).add(Attributes.ATTACK_DAMAGE, 3);
    }

    protected AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public Geminiraptor(EntityType<? extends AgeableMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return ModEntities.GEMINIRAPTOR.get().create(pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.5D));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.5d, true));

        this.goalSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Rabbit.class, true));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, .6D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 9f));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

    }

    protected PlayState predicate(AnimationState<Geminiraptor> state) {
        return state.setAndContinue(RawAnimation.begin().thenPlay("animation.geminiraptor.walk"));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
