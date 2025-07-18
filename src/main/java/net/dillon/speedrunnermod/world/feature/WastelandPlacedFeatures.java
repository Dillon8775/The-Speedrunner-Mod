package net.dillon.speedrunnermod.world.feature;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;

import static net.minecraft.world.gen.feature.OrePlacedFeatures.modifiersWithCount;

/**
 * All Speedrunner Mod {@code placed features}, specifically for the {@code speedrunner's wasteland} biome.
 */
public class WastelandPlacedFeatures {
    public static final RegistryKey<PlacedFeature> DEFAULT_SPEEDRUNNER_PLACED = ModPlacedFeatures.of("speedrunnermod:wasteland_default_speedrunner_placed");
    public static final RegistryKey<PlacedFeature> FANCY_SPEEDRUNNER_PLACED = ModPlacedFeatures.of("speedrunnermod:wasteland_fancy_speedrunner_placed");
    public static final RegistryKey<PlacedFeature> PATCH_RAW_SPEEDRUNNER_BLOCK_PLACED = ModPlacedFeatures.of("speedrunnermod:patch_raw_speedrunner_block");
    public static final RegistryKey<PlacedFeature> ORE_SPEEDRUNNER_UPPER = ModPlacedFeatures.of("speedrunnermod:wasteland_ore_speedrunner_upper");
    public static final RegistryKey<PlacedFeature> ORE_SPEEDRUNNER_MIDDLE = ModPlacedFeatures.of("speedrunnermod:wasteland_ore_speedrunner_middle");
    public static final RegistryKey<PlacedFeature> ORE_SPEEDRUNNER_SMALL = ModPlacedFeatures.of("speedrunnermod:wasteland_ore_speedrunner_small");
    public static final RegistryKey<PlacedFeature> ORE_EXPERIENCE = ModPlacedFeatures.of("speedrunnermod:wasteland_ore_experience");
    public static final RegistryKey<PlacedFeature> ORE_DIAMOND = ModPlacedFeatures.of("speedrunnermod:wasteland_ore_diamond");
    public static final RegistryKey<PlacedFeature> ORE_DIAMOND_BURIED = ModPlacedFeatures.of("speedrunnermod:wasteland_ore_diamond_buried");

    /**
     * See ModWorldGenerator for more.
     */
    public static void bootstrap(Registerable<PlacedFeature> context) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        PlacedFeatures.register(context, DEFAULT_SPEEDRUNNER_PLACED, registryEntryLookup.getOrThrow(WastelandConfiguredFeatures.DEFAULT_SPEEDRUNNER),
                PlacedFeatures.createCountExtraModifier(1, 0.05F, 1),
                SquarePlacementModifier.of(),
                SurfaceWaterDepthFilterPlacementModifier.of(0),
                PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());
        PlacedFeatures.register(context, FANCY_SPEEDRUNNER_PLACED, registryEntryLookup.getOrThrow(WastelandConfiguredFeatures.FANCY_SPEEDRUNNER),
                PlacedFeatures.createCountExtraModifier(0, 0.20F, 1),
                SquarePlacementModifier.of(),
                SurfaceWaterDepthFilterPlacementModifier.of(0),
                PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());
        PlacedFeatures.register(context, PATCH_RAW_SPEEDRUNNER_BLOCK_PLACED, registryEntryLookup.getOrThrow(WastelandConfiguredFeatures.PATCH_RAW_SPEEDRUNNER_BLOCK),
                RarityFilterPlacementModifier.of(300), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
        PlacedFeatures.register(context, ORE_SPEEDRUNNER_UPPER, registryEntryLookup.getOrThrow(WastelandConfiguredFeatures.ORE_SPEEDRUNNER),
                modifiersWithCount(48, HeightRangePlacementModifier.trapezoid(YOffset.fixed(80), YOffset.fixed(384))));
        PlacedFeatures.register(context, ORE_SPEEDRUNNER_MIDDLE, registryEntryLookup.getOrThrow(WastelandConfiguredFeatures.ORE_SPEEDRUNNER),
                modifiersWithCount(8, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-24), YOffset.fixed(56))));
        PlacedFeatures.register(context, ORE_SPEEDRUNNER_SMALL, registryEntryLookup.getOrThrow(WastelandConfiguredFeatures.ORE_SPEEDRUNNER_SMALL),
                modifiersWithCount(7, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(72))));
        PlacedFeatures.register(context, ORE_EXPERIENCE, registryEntryLookup.getOrThrow(WastelandConfiguredFeatures.ORE_EXPERIENCE),
                modifiersWithCount(18, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(62))));
        PlacedFeatures.register(context, ORE_DIAMOND, registryEntryLookup.getOrThrow(WastelandConfiguredFeatures.ORE_DIAMOND),
                modifiersWithCount(ModUtil.DIAMOND_ORE_SPAWN_CHANCE - 1, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));
        PlacedFeatures.register(context, ORE_DIAMOND_BURIED, registryEntryLookup.getOrThrow(WastelandConfiguredFeatures.ORE_DIAMOND_BURIED),
                modifiersWithCount(ModUtil.BURIED_DIAMOND_ORE_SPAWN_CHANCE - 2, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));
    }
}