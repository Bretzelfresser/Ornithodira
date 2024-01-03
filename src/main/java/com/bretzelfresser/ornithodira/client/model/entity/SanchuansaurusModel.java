package com.bretzelfresser.ornithodira.client.model.entity;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.entity.Sanchuansaurus;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SanchuansaurusModel extends GeoModel<Sanchuansaurus> {
    @Override
    public ResourceLocation getModelResource(Sanchuansaurus animatable) {
        if (animatable.isBaby())
            return Ornithodira.entityGeo("sanchuan_baby.geo.json");
        return Ornithodira.entityGeo("sanchuansaurus.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Sanchuansaurus animatable) {
        if (animatable.isBaby())
            return Ornithodira.entityTexture("sanchuan_baby.png");
        return Ornithodira.entityTexture("sanchuansaurus_striped.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Sanchuansaurus animatable) {
        if (animatable.isBaby()){
            return Ornithodira.animation("sanchuan_baby.animation.json");
        }
        return Ornithodira.animation("sanchuansaurus.animation.json");
    }
}
