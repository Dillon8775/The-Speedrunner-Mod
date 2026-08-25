package net.dillon.speedrunnermod.world.feature;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.mixin.accessor.OrePlacementsInvoker;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.*;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code placed features.}
 */
public class ModWorldPlacements {
    public static final ResourceKey<PlacedFeature> DEAD_SPEEDRUNNER_PLACED = create("dead_speedrunner_placed");
    public static final ResourceKey<PlacedFeature> DEAD_SPEEDRUNNER_PLACED_NETHER = create("dead_speedrunner_placed_nether");
    public static final ResourceKey<PlacedFeature> DOOM_TREE_PLACED = create("doom_tree_placed");
    public static final ResourceKey<PlacedFeature> ORE_SPEEDRUNNER_UPPER = create("ore_speedrunner_upper");
    public static final ResourceKey<PlacedFeature> ORE_SPEEDRUNNER_MIDDLE = create("ore_speedrunner_middle");
    public static final ResourceKey<PlacedFeature> ORE_SPEEDRUNNER_SMALL = create("ore_speedrunner_small");
    public static final ResourceKey<PlacedFeature> ORE_SPEEDRUNNER_DELTAS = create("ore_speedrunner_deltas");
    public static final ResourceKey<PlacedFeature> ORE_SPEEDRUNNER_NETHER = create("ore_speedrunner_nether");
    public static final ResourceKey<PlacedFeature> ORE_IGNEOUS = create("ore_igneous");
    public static final ResourceKey<PlacedFeature> ORE_IGNEOUS_DELTAS = create("ore_igneous_deltas");
    public static final ResourceKey<PlacedFeature> ORE_IGNEOUS_NETHER = create("ore_igneous_nether");
    public static final ResourceKey<PlacedFeature> ORE_EXPERIENCE = create("ore_experience");
    public static final ResourceKey<PlacedFeature> ORE_EXPERIENCE_DELTAS = create("ore_experience_deltas");
    public static final ResourceKey<PlacedFeature> ORE_EXPERIENCE_NETHER = create("ore_experience_nether");
    public static final ResourceKey<PlacedFeature> PATCH_DEAD_SPEEDRUNNER_BUSH_SWAMP = create("patch_dead_speedrunner_bush_swamp");
    public static final ResourceKey<PlacedFeature> PATCH_DEAD_SPEEDRUNNER_BUSH_DESERT = create("patch_dead_speedrunner_bush_desert");
    public static final ResourceKey<PlacedFeature> PATCH_DEAD_SPEEDRUNNER_BUSH_BADLANDS = create("patch_dead_speedrunner_bush_badlands");

    /**
     * See ModWorldGenerator for more.
     */
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<Feature> registryEntryLookup = context.lookup(Registries.FEATURE);

        PlacementUtils.register(context, DEAD_SPEEDRUNNER_PLACED, registryEntryLookup.getOrThrow(ModWorldFeatures.DEAD_SPEEDRUNNER),
                PlacementUtils.countExtra(0, 0.10F, 1),
                InSquarePlacement.spread(),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING)), BiomeFilter.biome());
        PlacementUtils.register(context, DEAD_SPEEDRUNNER_PLACED_NETHER, registryEntryLookup.getOrThrow(ModWorldFeatures.DEAD_SPEEDRUNNER),
                CountOnEveryLayerPlacement.of(1),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING)),
                BiomeFilter.biome());
        PlacementUtils.register(context, DOOM_TREE_PLACED, registryEntryLookup.getOrThrow(ModWorldFeatures.DOOM_TREE), VegetationPlacements.worldSurfaceSquaredWithCount(1));
        PlacementUtils.register(context, ORE_SPEEDRUNNER_UPPER, registryEntryLookup.getOrThrow(ModWorldFeatures.ORE_SPEEDRUNNER),
                OrePlacementsInvoker.invokeCommonOrePlacement(72, HeightRangePlacement.triangle(VerticalAnchor.absolute(80), VerticalAnchor.absolute(384))));
        PlacementUtils.register(context, ORE_SPEEDRUNNER_MIDDLE, registryEntryLookup.getOrThrow(ModWorldFeatures.ORE_SPEEDRUNNER),
                OrePlacementsInvoker.invokeCommonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56))));
        PlacementUtils.register(context, ORE_SPEEDRUNNER_SMALL, registryEntryLookup.getOrThrow(ModWorldFeatures.ORE_SPEEDRUNNER_SMALL),
                OrePlacementsInvoker.invokeCommonOrePlacement(9, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(72))));
        PlacementUtils.register(context, ORE_SPEEDRUNNER_DELTAS, registryEntryLookup.getOrThrow(ModWorldFeatures.ORE_NETHER_SPEEDRUNNER),
                OrePlacementsInvoker.invokeCommonOrePlacement(20, PlacementUtils.RANGE_10_10));
        PlacementUtils.register(context, ORE_SPEEDRUNNER_NETHER, registryEntryLookup.getOrThrow(ModWorldFeatures.ORE_NETHER_SPEEDRUNNER),
                OrePlacementsInvoker.invokeCommonOrePlacement(10, PlacementUtils.RANGE_10_10));
        PlacementUtils.register(context, ORE_IGNEOUS, registryEntryLookup.getOrThrow(ModWorldFeatures.ORE_IGNEOUS),
                OrePlacementsInvoker.invokeCommonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(24))));
        PlacementUtils.register(context, ORE_IGNEOUS_DELTAS, registryEntryLookup.getOrThrow(ModWorldFeatures.ORE_NETHER_IGNEOUS),
                OrePlacementsInvoker.invokeCommonOrePlacement(20, PlacementUtils.RANGE_10_10));
        PlacementUtils.register(context, ORE_IGNEOUS_NETHER, registryEntryLookup.getOrThrow(ModWorldFeatures.ORE_NETHER_IGNEOUS),
                OrePlacementsInvoker.invokeCommonOrePlacement(10, PlacementUtils.RANGE_10_10));
        PlacementUtils.register(context, ORE_EXPERIENCE, registryEntryLookup.getOrThrow(ModWorldFeatures.ORE_EXPERIENCE),
                OrePlacementsInvoker.invokeCommonOrePlacement(28, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(49))));
        PlacementUtils.register(context, ORE_EXPERIENCE_DELTAS, registryEntryLookup.getOrThrow(ModWorldFeatures.ORE_NETHER_EXPERIENCE),
                OrePlacementsInvoker.invokeCommonOrePlacement(108, PlacementUtils.RANGE_10_10));
        PlacementUtils.register(context, ORE_EXPERIENCE_NETHER, registryEntryLookup.getOrThrow(ModWorldFeatures.ORE_NETHER_EXPERIENCE),
                OrePlacementsInvoker.invokeCommonOrePlacement(92, PlacementUtils.RANGE_10_10));
        PlacementUtils.register(context, PATCH_DEAD_SPEEDRUNNER_BUSH_SWAMP, registryEntryLookup.getOrThrow(ModWorldFeatures.PATCH_DEAD_SPEEDRUNNER_BUSH),
                InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        PlacementUtils.register(context, PATCH_DEAD_SPEEDRUNNER_BUSH_DESERT, registryEntryLookup.getOrThrow(ModWorldFeatures.PATCH_DEAD_SPEEDRUNNER_BUSH),
                VegetationPlacements.worldSurfaceSquaredWithCount(2));
        PlacementUtils.register(context, PATCH_DEAD_SPEEDRUNNER_BUSH_BADLANDS, registryEntryLookup.getOrThrow(ModWorldFeatures.PATCH_DEAD_SPEEDRUNNER_BUSH),
                VegetationPlacements.worldSurfaceSquaredWithCount(3));
    }

    /**
     * Creates a mod placed feature.
     */
    protected static ResourceKey<PlacedFeature> create(final String id) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ofSpeedrunnerMod(id));
    }
}