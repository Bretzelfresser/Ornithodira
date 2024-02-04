package com.bretzelfresser.ornithodira.client.model.entity;

import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public abstract class ScaleableAgeableModel<T extends LivingEntity & GeoEntity> extends GeoModel<T> {


    @Override
    public void setCustomAnimations(T animatable, long instanceId, AnimationState<T> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
        if (animationState == null) return;
        if (animatable.isBaby()) {
            this.getAnimationProcessor().getRegisteredBones().forEach(this::boneChildScaling);
        }else {
            this.getAnimationProcessor().getRegisteredBones().forEach(ScaleableAgeableModel::resetScaling);
        }
    }

    protected void boneChildScaling(CoreGeoBone bone){
        if (!bone.getName().equals(getHeadName())) {
            bone.setScaleX(childScale());
            bone.setScaleY(childScale());
            bone.setScaleZ(childScale());
        }
    }

    public static void resetScaling(CoreGeoBone bone){
        if (bone.getScaleX() != 1)
            bone.setScaleX(1f);
        if (bone.getScaleY() != 1)
            bone.setScaleY(1f);
        if (bone.getScaleZ() != 1)
            bone.setScaleZ(1f);
    }

    public String getHeadName(){
        return "head";
    }

    public float childScale() {
        return .83f;
    }


}
