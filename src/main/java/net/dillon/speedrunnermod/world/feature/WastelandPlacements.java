package net.dillon.speedrunnermod.world.feature;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.helper.ModHelper;
import net.dillon.speedrunnermod.mixin.accessor.OrePlacementsInvoker;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

/**
 * All Speedrunner Mod {@code placed features}, specifically for the {@code speedrunner's wasteland} biome.
 */
public class WastelandPlacements {
    public static final ResourceKey<PlacedFeature> DEFAULT_SPEEDRUNNER_PLACED = ModWorldPlacements.create("wasteland_default_speedrunner_placed");
    public static final ResourceKey<PlacedFeature> FANCY_SPEEDRUNNER_PLACED = ModWorldPlacements.create("wasteland_fancy_speedrunner_placed");
    public static final ResourceKey<PlacedFeature> PATCH_RAW_SPEEDRUNNER_BLOCK_PLACED = ModWorldPlacements.create("patch_raw_speedrunner_block");
    public static final ResourceKey<PlacedFeature> FLOWER_SPEEDRUNNER_PLACED = ModWorldPlacements.create("flower_speedrunner");
    public static final ResourceKey<PlacedFeature> SUGAR_CANE_WASTELAND = ModWorldPlacements.create("sugar_cane_wasteland");
    public static final ResourceKey<PlacedFeature> ORE_SPEEDRUNNER_UPPER = ModWorldPlacements.create("wasteland_ore_speedrunner_upper");
    public static final ResourceKey<PlacedFeature> ORE_SPEEDRUNNER_MIDDLE = ModWorldPlacements.create("wasteland_ore_speedrunner_middle");
    public static final ResourceKey<PlacedFeature> ORE_SPEEDRUNNER_SMALL = ModWorldPlacements.create("wasteland_ore_speedrunner_small");
    public static final ResourceKey<PlacedFeature> ORE_EXPERIENCE = ModWorldPlacements.create("wasteland_ore_experience");
    public static final ResourceKey<PlacedFeature> ORE_DIAMOND = ModWorldPlacements.create("wasteland_ore_diamond");
    public static final ResourceKey<PlacedFeature> ORE_DIAMOND_BURIED = ModWorldPlacements.create("wasteland_ore_diamond_buried");

    /**
     * See ModWorldGenerator for more.
     */
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> registryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(context, DEFAULT_SPEEDRUNNER_PLACED, registryEntryLookup.getOrThrow(WastelandFeatures.DEFAULT_SPEEDRUNNER),
                PlacementUtils.countExtra(1, 0.05F, 1),
                InSquarePlacement.spread(),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());
        PlacementUtils.register(context, FANCY_SPEEDRUNNER_PLACED, registryEntryLookup.getOrThrow(WastelandFeatures.FANCY_SPEEDRUNNER),
                PlacementUtils.countExtra(0, 0.20F, 1),
                InSquarePlacement.spread(),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());
        PlacementUtils.register(context, PATCH_RAW_SPEEDRUNNER_BLOCK_PLACED, registryEntryLookup.getOrThrow(WastelandFeatures.PATCH_RAW_SPEEDRUNNER_BLOCK),
                RarityFilter.onAverageOnceEvery(20), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        PlacementUtils.register(context, FLOWER_SPEEDRUNNER_PLACED, registryEntryLookup.getOrThrow(WastelandFeatures.FLOWER_SPEEDRUNNER),
                RarityFilter.onAverageOnceEvery(2),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome(),
                CountPlacement.of(32),
                RandomOffsetPlacement.ofTriangle(6, 2),
                BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE)
        );
        PlacementUtils.register(context, SUGAR_CANE_WASTELAND, registryEntryLookup.getOrThrow(VegetationFeatures.SUGAR_CANE),
                RarityFilter.onAverageOnceEvery(3),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome(),
                CountPlacement.of(64),
                RandomOffsetPlacement.ofTriangle(3, 0),
                VegetationFeatures.nearWaterPredicate(Blocks.SUGAR_CANE)
        );
        PlacementUtils.register(context, ORE_SPEEDRUNNER_UPPER, registryEntryLookup.getOrThrow(WastelandFeatures.ORE_SPEEDRUNNER),
                OrePlacementsInvoker.invokeCommonOrePlacement(48, HeightRangePlacement.triangle(VerticalAnchor.absolute(80), VerticalAnchor.absolute(384))));
        PlacementUtils.register(context, ORE_SPEEDRUNNER_MIDDLE, registryEntryLookup.getOrThrow(WastelandFeatures.ORE_SPEEDRUNNER),
                OrePlacementsInvoker.invokeCommonOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56))));
        PlacementUtils.register(context, ORE_SPEEDRUNNER_SMALL, registryEntryLookup.getOrThrow(WastelandFeatures.ORE_SPEEDRUNNER_SMALL),
                OrePlacementsInvoker.invokeCommonOrePlacement(7, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(72))));
        PlacementUtils.register(context, ORE_EXPERIENCE, registryEntryLookup.getOrThrow(WastelandFeatures.ORE_EXPERIENCE),
                OrePlacementsInvoker.invokeCommonOrePlacement(18, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(62))));
        PlacementUtils.register(context, ORE_DIAMOND, registryEntryLookup.getOrThrow(WastelandFeatures.ORE_DIAMOND),
                OrePlacementsInvoker.invokeCommonOrePlacement(ModHelper.DIAMOND_ORE_SPAWN_CHANCE - 1, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        PlacementUtils.register(context, ORE_DIAMOND_BURIED, registryEntryLookup.getOrThrow(WastelandFeatures.ORE_DIAMOND_BURIED),
                OrePlacementsInvoker.invokeCommonOrePlacement(ModHelper.BURIED_DIAMOND_ORE_SPAWN_CHANCE - 2, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
    }
}