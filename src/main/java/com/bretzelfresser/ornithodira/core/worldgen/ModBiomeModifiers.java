package com.bretzelfresser.ornithodira.core.worldgen;

import com.bretzelfresser.ornithodira.Ornithodira;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_ORE_BROWN_SHALE = registerKey("add_ore_brown_shale");
    public static final ResourceKey<BiomeModifier> ADD_ORE_GRAY_SHALE = registerKey("add_ore_gray_shale");
    public static final ResourceKey<BiomeModifier> ADD_ORE_RED_SHALE = registerKey("add_ore_red_shale");
    public static final ResourceKey<BiomeModifier> ADD_ORE_RICH_RED_SHALE = registerKey("add_ore_rich_red_shale");

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_ORE_BROWN_SHALE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ORE_BROWN_SHALE)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_ORE_GRAY_SHALE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ORE_GRAY_SHALE)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_ORE_RED_SHALE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ORE_RED_SHALE)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_ORE_RICH_RED_SHALE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ORE_RICH_RED_SHALE)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
    }


    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Ornithodira.MODID, name));
    }
}
