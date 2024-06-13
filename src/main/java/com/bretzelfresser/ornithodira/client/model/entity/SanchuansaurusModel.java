package com.bretzelfresser.ornithodira.client.model.entity;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.entity.Sanchuansaurus;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.model.GeoModel;

public class SanchuansaurusModel extends GeoModel<Sanchuansaurus> {
    @Override
    public ResourceLocation getModelResource(Sanchuansaurus animatable) {
        return Ornithodira.entityGeo("sanchuansaurus.json");
    }

    @Override
    public ResourceLocation getTextureResource(Sanchuansaurus animatable) {
        return Ornithodira.entityTexture("sanchuansaurus.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Sanchuansaurus animatable) {
        return Ornithodira.animation("sanchuansaurus.animation.json");
    }
}
