package com.bretzelfresser.ornithodira.core.worldgen;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.core.init.ModBlocks;
import com.bretzelfresser.ornithodira.core.tags.ModTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_BROWN_SHALE = registerKey("ore_brown_shale");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GRAY_SHALE = registerKey("ore_gray_shale");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RED_SHALE = registerKey("ore_red_shale");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RICH_RED_SHALE = registerKey("ore_rich_red_shale");


    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest baseStoneOverworld = new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD);
        RuleTest richRedShaleReplaceables = new TagMatchTest(ModTags.Blocks.RICH_RED_SHALE_REPLACEABLES);

        register(context, ORE_BROWN_SHALE, Feature.ORE, new OreConfiguration(baseStoneOverworld, ModBlocks.BROWN_SHALE.get().defaultBlockState(), 64));
        register(context, ORE_GRAY_SHALE, Feature.ORE, new OreConfiguration(baseStoneOverworld, ModBlocks.GRAY_SHALE.get().defaultBlockState(), 64));
        register(context, ORE_RED_SHALE, Feature.ORE, new OreConfiguration(baseStoneOverworld, ModBlocks.RED_SHALE.get().defaultBlockState(), 48));
        register(context, ORE_RICH_RED_SHALE, Feature.ORE, new OreConfiguration(richRedShaleReplaceables, ModBlocks.RICH_RED_SHALE.get().defaultBlockState(), 6));

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Ornithodira.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
