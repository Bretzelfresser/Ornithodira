package com.bretzelfresser.ornithodira.client.model.entity;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.entity.ambient.terrestrial.Juravenator;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class JuravenatorModel extends GeoModel<Juravenator> {
    @Override
    public ResourceLocation getModelResource(Juravenator animatable) {
        return Ornithodira.entityGeo("juravenator.json");
    }

    @Override
    public ResourceLocation getTextureResource(Juravenator animatable) {
        return Ornithodira.entityTexture("juravenator.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Juravenator animatable) {
        return Ornithodira.animation("juravenator.animation.json");
    }
}
