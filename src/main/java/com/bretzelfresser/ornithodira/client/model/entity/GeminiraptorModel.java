package com.bretzelfresser.ornithodira.client.model.entity;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.entity.ambient.terrestrial.Geminiraptor;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GeminiraptorModel extends GeoModel<Geminiraptor> {
    @Override
    public ResourceLocation getModelResource(Geminiraptor animatable) {
        return Ornithodira.entityGeo("geminiraptor.json");
    }

    @Override
    public ResourceLocation getTextureResource(Geminiraptor animatable) {
        return Ornithodira.entityTexture("geminiraptor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Geminiraptor animatable) {
        return Ornithodira.animation("geminiraptor.animation.json");
    }
}
