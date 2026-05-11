package net.dillon.speedrunnermod.world.feature;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.mixin.accessor.OrePlacementsInvoker;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

/**
 * All Speedrunner Mod {@code placed features}, specifically for the {@code speedrunner's wasteland} biome.
 */
public class WastelandPlacedFeatures {
    public static final ResourceKey<PlacedFeature> DEFAULT_SPEEDRUNNER_PLACED = ModPlacedFeatures.of("speedrunnermod:wasteland_default_speedrunner_placed");
    public static final ResourceKey<PlacedFeature> FANCY_SPEEDRUNNER_PLACED = ModPlacedFeatures.of("speedrunnermod:wasteland_fancy_speedrunner_placed");
    public static final ResourceKey<PlacedFeature> PATCH_RAW_SPEEDRUNNER_BLOCK_PLACED = ModPlacedFeatures.of("speedrunnermod:patch_raw_speedrunner_block");
    public static final ResourceKey<PlacedFeature> ORE_SPEEDRUNNER_UPPER = ModPlacedFeatures.of("speedrunnermod:wasteland_ore_speedrunner_upper");
    public static final ResourceKey<PlacedFeature> ORE_SPEEDRUNNER_MIDDLE = ModPlacedFeatures.of("speedrunnermod:wasteland_ore_speedrunner_middle");
    public static final ResourceKey<PlacedFeature> ORE_SPEEDRUNNER_SMALL = ModPlacedFeatures.of("speedrunnermod:wasteland_ore_speedrunner_small");
    public static final ResourceKey<PlacedFeature> ORE_EXPERIENCE = ModPlacedFeatures.of("speedrunnermod:wasteland_ore_experience");
    public static final ResourceKey<PlacedFeature> ORE_DIAMOND = ModPlacedFeatures.of("speedrunnermod:wasteland_ore_diamond");
    public static final ResourceKey<PlacedFeature> ORE_DIAMOND_BURIED = ModPlacedFeatures.of("speedrunnermod:wasteland_ore_diamond_buried");

    /**
     * See ModWorldGenerator for more.
     */
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> registryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(context, DEFAULT_SPEEDRUNNER_PLACED, registryEntryLookup.getOrThrow(WastelandConfiguredFeatures.DEFAULT_SPEEDRUNNER),
                PlacementUtils.countExtra(1, 0.05F, 1),
                InSquarePlacement.spread(),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());
        PlacementUtils.register(context, FANCY_SPEEDRUNNER_PLACED, registryEntryLookup.getOrThrow(WastelandConfiguredFeatures.FANCY_SPEEDRUNNER),
                PlacementUtils.countExtra(0, 0.20F, 1),
                InSquarePlacement.spread(),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());
        PlacementUtils.register(context, PATCH_RAW_SPEEDRUNNER_BLOCK_PLACED, registryEntryLookup.getOrThrow(WastelandConfiguredFeatures.PATCH_RAW_SPEEDRUNNER_BLOCK),
                RarityFilter.onAverageOnceEvery(20), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        PlacementUtils.register(context, ORE_SPEEDRUNNER_UPPER, registryEntryLookup.getOrThrow(WastelandConfiguredFeatures.ORE_SPEEDRUNNER),
                OrePlacementsInvoker.invokeCommonOrePlacement(48, HeightRangePlacement.triangle(VerticalAnchor.absolute(80), VerticalAnchor.absolute(384))));
        PlacementUtils.register(context, ORE_SPEEDRUNNER_MIDDLE, registryEntryLookup.getOrThrow(WastelandConfiguredFeatures.ORE_SPEEDRUNNER),
                OrePlacementsInvoker.invokeCommonOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56))));
        PlacementUtils.register(context, ORE_SPEEDRUNNER_SMALL, registryEntryLookup.getOrThrow(WastelandConfiguredFeatures.ORE_SPEEDRUNNER_SMALL),
                OrePlacementsInvoker.invokeCommonOrePlacement(7, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(72))));
        PlacementUtils.register(context, ORE_EXPERIENCE, registryEntryLookup.getOrThrow(WastelandConfiguredFeatures.ORE_EXPERIENCE),
                OrePlacementsInvoker.invokeCommonOrePlacement(18, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(62))));
        PlacementUtils.register(context, ORE_DIAMOND, registryEntryLookup.getOrThrow(WastelandConfiguredFeatures.ORE_DIAMOND),
                OrePlacementsInvoker.invokeCommonOrePlacement(ModUtil.DIAMOND_ORE_SPAWN_CHANCE - 1, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        PlacementUtils.register(context, ORE_DIAMOND_BURIED, registryEntryLookup.getOrThrow(WastelandConfiguredFeatures.ORE_DIAMOND_BURIED),
                OrePlacementsInvoker.invokeCommonOrePlacement(ModUtil.BURIED_DIAMOND_ORE_SPAWN_CHANCE - 2, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
    }
}