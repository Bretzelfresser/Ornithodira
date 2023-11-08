package com.bretzelfresser.ornithodira.client.model.block;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.blockentity.ScannerBlockEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.model.GeoModel;

@OnlyIn(Dist.CLIENT)
public class ScannerModel extends GeoModel<ScannerBlockEntity> {
    @Override
    public ResourceLocation getModelResource(ScannerBlockEntity animatable) {
        return Ornithodira.modLoc("geo/block/paradox_scanner.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ScannerBlockEntity animatable) {
        return Ornithodira.modLoc("textures/block/paradox_scanner_1.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ScannerBlockEntity animatable) {
        return Ornithodira.modLoc("animations/paradox_scanner.animation.json");
    }
}
