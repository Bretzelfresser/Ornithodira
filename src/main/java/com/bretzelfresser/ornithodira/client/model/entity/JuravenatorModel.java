package com.bretzelfresser.ornithodira.client.model.entity;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.entity.ambient.terrestrial.Jurvenator;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class JuravenatorModel extends GeoModel<Jurvenator> {
    @Override
    public ResourceLocation getModelResource(Jurvenator animatable) {
        return Ornithodira.entityGeo("juravenator.json");
    }

    @Override
    public ResourceLocation getTextureResource(Jurvenator animatable) {
        return Ornithodira.entityTexture("juravenator.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Jurvenator animatable) {
        return Ornithodira.animation("juravenator.animation.json");
    }
}
