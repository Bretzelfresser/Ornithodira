package com.bretzelfresser.ornithodira.client.model.entity;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.entity.Taoheodon;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TaoheodonModel extends GeoModel<Taoheodon> {
    @Override
    public ResourceLocation getModelResource(Taoheodon animatable) {
        if (animatable.isBaby())
            return Ornithodira.entityGeo("taoheodon_baby.geo.json");
        return Ornithodira.entityGeo("taoheodon.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Taoheodon animatable) {
        if (animatable.isBaby()){
            return Ornithodira.entityTexture("taoheodon_baby.png");
        }
        return Ornithodira.entityTexture("taoheodon.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Taoheodon animatable) {
        return Ornithodira.animation("taoheodon.animation.json");
    }
}
