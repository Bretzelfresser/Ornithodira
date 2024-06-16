package com.bretzelfresser.ornithodira.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AgableEntityRenderer<T extends Entity & GeoAnimatable> extends GeoEntityRenderer<T> {

    protected final String head, body;

    public AgableEntityRenderer(EntityRendererProvider.Context renderManager, GeoModel<T> model) {
        this(renderManager, model, "Head", "Body");
    }

    public AgableEntityRenderer(EntityRendererProvider.Context renderManager, GeoModel<T> model, String head, String body) {
        super(renderManager, model);
        this.head = head;
        this.body = body;
    }

    @Override
    public void render(T entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();
        if (entity instanceof LivingEntity living && living.isBaby()){
            poseStack.scale(.6f, .6f, .6f);
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        poseStack.popPose();
    }

    @Override
    public void actuallyRender(PoseStack poseStack, T animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource,
                                VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay,
                                float red, float green, float blue, float alpha) {
        updateAnimatedTextureFrame(animatable);

        for (GeoBone group : model.topLevelBones()) {
            poseStack.pushPose();
            if (animatable instanceof LivingEntity living && living.isBaby() && group.getName().equals(this.body)){
                poseStack.scale(getChildScale(), getChildScale(), getChildScale());
            }
            renderRecursively(poseStack, animatable, group, renderType, bufferSource, buffer, isReRender, partialTick, packedLight,
                    packedOverlay, red, green, blue, alpha);
            poseStack.popPose();
        }
    }

    /**
     *
     * @return the scaling factor in which the body of the entity should be scaled when it is a baby
     */
    protected float getChildScale(){
        return .6f;
    }
}
