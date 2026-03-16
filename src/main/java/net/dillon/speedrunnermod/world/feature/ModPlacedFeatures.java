package net.dillon.speedrunnermod.world.feature;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import static net.minecraft.data.worldgen.placement.OrePlacements.commonOrePlacement;

/**
 * All Speedrunner Mod {@code placed features.}
 */
public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> DEAD_SPEEDRUNNER_PLACED = of("speedrunnermod:dead_speedrunner_placed");
    public static final ResourceKey<PlacedFeature> DEAD_SPEEDRUNNER_PLACED_NETHER = of("speedrunnermod:dead_speedrunner_placed_nether");
    public static final ResourceKey<PlacedFeature> DOOM_TREE_PLACED = of("speedrunnermod:doom_tree_placed");
    public static final ResourceKey<PlacedFeature> ORE_SPEEDRUNNER_UPPER = of("speedrunnermod:ore_speedrunner_upper");
    public static final ResourceKey<PlacedFeature> ORE_SPEEDRUNNER_MIDDLE = of("speedrunnermod:ore_speedrunner_middle");
    public static final ResourceKey<PlacedFeature> ORE_SPEEDRUNNER_SMALL = of("speedrunnermod:ore_speedrunner_small");
    public static final ResourceKey<PlacedFeature> ORE_SPEEDRUNNER_DELTAS = of("speedrunnermod:ore_speedrunner_deltas");
    public static final ResourceKey<PlacedFeature> ORE_SPEEDRUNNER_NETHER = of("speedrunnermod:ore_speedrunner_nether");
    public static final ResourceKey<PlacedFeature> ORE_IGNEOUS = of("speedrunnermod:ore_igneous");
    public static final ResourceKey<PlacedFeature> ORE_IGNEOUS_DELTAS = of("speedrunnermod:ore_igneous_deltas");
    public static final ResourceKey<PlacedFeature> ORE_IGNEOUS_NETHER = of("speedrunnermod:ore_igneous_nether");
    public static final ResourceKey<PlacedFeature> ORE_EXPERIENCE = of("speedrunnermod:ore_experience");
    public static final ResourceKey<PlacedFeature> ORE_EXPERIENCE_DELTAS = of("speedrunnermod:ore_experience_deltas");
    public static final ResourceKey<PlacedFeature> ORE_EXPERIENCE_NETHER = of("speedrunnermod:ore_experience_nether");
    public static final ResourceKey<PlacedFeature> PATCH_DEAD_SPEEDRUNNER_BUSH_SWAMP = of("speedrunnermod:patch_dead_speedrunner_bush_swamp");
    public static final ResourceKey<PlacedFeature> PATCH_DEAD_SPEEDRUNNER_BUSH_DESERT = of("speedrunnermod:patch_dead_speedrunner_bush_desert");
    public static final ResourceKey<PlacedFeature> PATCH_DEAD_SPEEDRUNNER_BUSH_BADLANDS = of("speedrunnermod:patch_dead_speedrunner_bush_badlands");

    /**
     * See ModWorldGenerator for more.
     */
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> registryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(context, DEAD_SPEEDRUNNER_PLACED, registryEntryLookup.getOrThrow(ModConfiguredFeatures.DEAD_SPEEDRUNNER),
                PlacementUtils.countExtra(0, 0.10F, 1),
                InSquarePlacement.spread(),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());
        PlacementUtils.register(context, DEAD_SPEEDRUNNER_PLACED_NETHER, registryEntryLookup.getOrThrow(ModConfiguredFeatures.DEAD_SPEEDRUNNER),
                CountOnEveryLayerPlacement.of(1),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING.defaultBlockState(), BlockPos.ZERO)),
                BiomeFilter.biome());
        PlacementUtils.register(context, DOOM_TREE_PLACED, registryEntryLookup.getOrThrow(ModConfiguredFeatures.DOOM_TREE), VegetationPlacements.worldSurfaceSquaredWithCount(1));
        PlacementUtils.register(context, ORE_SPEEDRUNNER_UPPER, registryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_SPEEDRUNNER),
                commonOrePlacement(72, HeightRangePlacement.triangle(VerticalAnchor.absolute(80), VerticalAnchor.absolute(384))));
        PlacementUtils.register(context, ORE_SPEEDRUNNER_MIDDLE, registryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_SPEEDRUNNER),
                commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56))));
        PlacementUtils.register(context, ORE_SPEEDRUNNER_SMALL, registryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_SPEEDRUNNER_SMALL),
                commonOrePlacement(9, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(72))));
        PlacementUtils.register(context, ORE_SPEEDRUNNER_DELTAS, registryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_NETHER_SPEEDRUNNER),
                commonOrePlacement(20, PlacementUtils.RANGE_10_10));
        PlacementUtils.register(context, ORE_SPEEDRUNNER_NETHER, registryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_NETHER_SPEEDRUNNER),
                commonOrePlacement(10, PlacementUtils.RANGE_10_10));
        PlacementUtils.register(context, ORE_IGNEOUS, registryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_IGNEOUS),
                commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(24))));
        PlacementUtils.register(context, ORE_IGNEOUS_DELTAS, registryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_NETHER_IGNEOUS),
                commonOrePlacement(20, PlacementUtils.RANGE_10_10));
        PlacementUtils.register(context, ORE_IGNEOUS_NETHER, registryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_NETHER_IGNEOUS),
                commonOrePlacement(10, PlacementUtils.RANGE_10_10));
        PlacementUtils.register(context, ORE_EXPERIENCE, registryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_EXPERIENCE),
                commonOrePlacement(28, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(49))));
        PlacementUtils.register(context, ORE_EXPERIENCE_DELTAS, registryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_NETHER_EXPERIENCE),
                commonOrePlacement(108, PlacementUtils.RANGE_10_10));
        PlacementUtils.register(context, ORE_EXPERIENCE_NETHER, registryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_NETHER_EXPERIENCE),
                commonOrePlacement(92, PlacementUtils.RANGE_10_10));
        PlacementUtils.register(context, PATCH_DEAD_SPEEDRUNNER_BUSH_SWAMP, registryEntryLookup.getOrThrow(ModConfiguredFeatures.PATCH_DEAD_SPEEDRUNNER_BUSH),
                InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        PlacementUtils.register(context, PATCH_DEAD_SPEEDRUNNER_BUSH_DESERT, registryEntryLookup.getOrThrow(ModConfiguredFeatures.PATCH_DEAD_SPEEDRUNNER_BUSH),
                VegetationPlacements.worldSurfaceSquaredWithCount(2));
        PlacementUtils.register(context, PATCH_DEAD_SPEEDRUNNER_BUSH_BADLANDS, registryEntryLookup.getOrThrow(ModConfiguredFeatures.PATCH_DEAD_SPEEDRUNNER_BUSH),
                VegetationPlacements.worldSurfaceSquaredWithCount(3));
    }

    /**
     * Created because original method uses "Identifier.ofVanilla".
     */
    protected static ResourceKey<PlacedFeature> of(String id) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.parse(id));
    }
}