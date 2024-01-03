package com.bretzelfresser.ornithodira.client.renderer.entity;

import com.bretzelfresser.ornithodira.client.model.entity.SanchuansaurusModel;
import com.bretzelfresser.ornithodira.common.entity.Sanchuansaurus;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SanchuansaurusRenderer extends GeoEntityRenderer<Sanchuansaurus> {
    public SanchuansaurusRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SanchuansaurusModel());

        addRenderLayer(new SnachuansaurusSaddleLayer(this));
    }
}
