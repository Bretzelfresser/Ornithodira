package com.bretzelfresser.ornithodira.core.worldgen;

import com.bretzelfresser.ornithodira.Ornithodira;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> ORE_BROWN_SHALE = registerKey("ore_brown_shale");
    public static final ResourceKey<PlacedFeature> ORE_GRAY_SHALE = registerKey("ore_gray_shale");
    public static final ResourceKey<PlacedFeature> ORE_RED_SHALE = registerKey("ore_red_shale");
    public static final ResourceKey<PlacedFeature> ORE_RICH_RED_SHALE = registerKey("ore_rich_red_shale");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, ORE_BROWN_SHALE, configuredFeatures.getOrThrow(ModConfiguredFeatures.ORE_BROWN_SHALE),
                ModOrePlacement.commonOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(69 + 42))));
        register(context, ORE_GRAY_SHALE, configuredFeatures.getOrThrow(ModConfiguredFeatures.ORE_GRAY_SHALE),
                ModOrePlacement.commonOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(0))));
        register(context, ORE_RED_SHALE, configuredFeatures.getOrThrow(ModConfiguredFeatures.ORE_RED_SHALE),
                ModOrePlacement.commonOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(30), VerticalAnchor.absolute(80))));
        register(context, ORE_RICH_RED_SHALE, configuredFeatures.getOrThrow(ModConfiguredFeatures.ORE_RICH_RED_SHALE),
                ModOrePlacement.commonOrePlacement(256, HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(30), VerticalAnchor.absolute(60))));
    }



    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Ornithodira.MODID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
