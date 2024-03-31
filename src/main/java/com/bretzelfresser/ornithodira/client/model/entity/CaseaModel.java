package com.bretzelfresser.ornithodira.client.model.entity;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.entity.Casea;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CaseaModel extends GeoModel<Casea> {
    @Override
    public ResourceLocation getModelResource(Casea animatable) {
        return Ornithodira.entityGeo("casea.json");
    }

    @Override
    public ResourceLocation getTextureResource(Casea animatable) {
        return Ornithodira.entityTexture("casea.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Casea animatable) {
        return Ornithodira.animation("casea.animation.json");
    }
}
