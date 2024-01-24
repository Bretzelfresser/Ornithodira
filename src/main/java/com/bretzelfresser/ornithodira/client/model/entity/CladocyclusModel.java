package com.bretzelfresser.ornithodira.client.model.entity;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.entity.Cladocyclus;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CladocyclusModel extends GeoModel<Cladocyclus> {
    @Override
    public ResourceLocation getModelResource(Cladocyclus animatable) {
        return Ornithodira.entityGeo("cladocyclus.json");
    }

    @Override
    public ResourceLocation getTextureResource(Cladocyclus animatable) {
        return Ornithodira.entityTexture("cladocyclus.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Cladocyclus animatable) {
        return Ornithodira.animation("cladocyclus.animation.json");
    }
}
