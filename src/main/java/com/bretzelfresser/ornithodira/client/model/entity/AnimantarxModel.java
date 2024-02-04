package com.bretzelfresser.ornithodira.client.model.entity;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.entity.Animantarx;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import software.bernie.geckolib.model.GeoModel;

public class AnimantarxModel extends GeoModel<Animantarx> {
    @Override
    public ResourceLocation getModelResource(Animantarx animatable) {
        return animatable.isBaby() ? Ornithodira.entityGeo("animantarx_baby.json") : Ornithodira.entityGeo("animantarx.json");
    }

    @Override
    public ResourceLocation getTextureResource(Animantarx animatable) {
        return animatable.isBaby() ? Ornithodira.entityTexture("animantarx_baby.png") : Ornithodira.entityTexture("animantarx.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Animantarx animatable) {
        return Ornithodira.animation("animantarx.animation.json");
    }
}
