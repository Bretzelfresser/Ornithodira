package com.bretzelfresser.ornithodira.client.renderer.entity;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.entity.Sanchuansaurus;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class SnachuansaurusSaddleLayer extends GeoRenderLayer<Sanchuansaurus> {

    public static final ResourceLocation TEXTURE = Ornithodira.entityTexture("sanchuansaurus_saddle_layer.png");

    public SnachuansaurusSaddleLayer(GeoRenderer<Sanchuansaurus> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, Sanchuansaurus animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        if (animatable.isSaddled()) {
            RenderType type = RenderType.entityCutout(TEXTURE);
            poseStack.pushPose();
            this.getRenderer().actuallyRender(poseStack, animatable, bakedModel, type,bufferSource,  bufferSource.getBuffer(type), false, partialTick, packedLight, packedOverlay, 1f, 1f, 1f,1f);
            poseStack.popPose();
        }
    }
}
