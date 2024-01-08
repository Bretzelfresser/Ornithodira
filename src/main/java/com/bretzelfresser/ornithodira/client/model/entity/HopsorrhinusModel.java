package com.bretzelfresser.ornithodira.client.model.entity;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.entity.Hopsorrhinus;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HopsorrhinusModel extends GeoModel<Hopsorrhinus> {
    @Override
    public ResourceLocation getModelResource(Hopsorrhinus animatable) {
        return Ornithodira.entityGeo("hopsorrhinus.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Hopsorrhinus animatable) {
        return Ornithodira.entityTexture("hopsorrhinus.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Hopsorrhinus animatable) {
        return Ornithodira.animation("hopsorrhinus.animation.json");
    }
}
