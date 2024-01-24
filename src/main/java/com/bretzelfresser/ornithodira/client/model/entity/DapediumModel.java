package com.bretzelfresser.ornithodira.client.model.entity;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.entity.ambient.fish.Dapedium;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DapediumModel extends GeoModel<Dapedium> {
    @Override
    public ResourceLocation getModelResource(Dapedium animatable) {
        return Ornithodira.entityGeo("dapedium.json");
    }

    @Override
    public ResourceLocation getTextureResource(Dapedium animatable) {
        return Ornithodira.entityTexture("dapedium.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Dapedium animatable) {
        return Ornithodira.animation("dapedium.animation");
    }
}
