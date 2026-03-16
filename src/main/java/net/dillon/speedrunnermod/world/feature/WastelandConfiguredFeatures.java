package net.dillon.speedrunnermod.world.feature;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

/**
 * All Speedrunner Mod {@code configured features,} specifically for the {@code speedrunner's wasteland} biome.
 */
public class WastelandConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEFAULT_SPEEDRUNNER = ModConfiguredFeatures.of("speedrunnermod:wasteland_default_speedrunner");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_SPEEDRUNNER = ModConfiguredFeatures.of("speedrunnermod:wasteland_fancy_speedrunner");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_RAW_SPEEDRUNNER_BLOCK = ModConfiguredFeatures.of("speedrunnermod:wasteland_patch_raw_speedrunner_block");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SPEEDRUNNER = ModConfiguredFeatures.of("speedrunnermod:wasteland_ore_speedrunner");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SPEEDRUNNER_SMALL = ModConfiguredFeatures.of("speedrunnermod:wasteland_ore_speedrunner_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_EXPERIENCE = ModConfiguredFeatures.of("speedrunnermod:wasteland_ore_experience");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_DIAMOND = ModConfiguredFeatures.of("speedrunnermod:wasteland_ore_diamond");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_DIAMOND_BURIED = ModConfiguredFeatures.of("speedrunnermod:wasteland_ore_diamond_buried");

    /**
     * See ModWorldGenerator for more.
     */
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        List<OreConfiguration.TargetBlockState> speedrunnerOres = List.of(
                OreConfiguration.target(ModConfiguredFeatures.STONE_ORE_REPLACEABLES, ModBlocks.SPEEDRUNNER_ORE.defaultBlockState()),
                OreConfiguration.target(ModConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE.defaultBlockState()));

        List<OreConfiguration.TargetBlockState> experienceOres = List.of(
                OreConfiguration.target(ModConfiguredFeatures.STONE_ORE_REPLACEABLES, ModBlocks.EXPERIENCE_ORE.defaultBlockState()),
                OreConfiguration.target(ModConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_EXPERIENCE_ORE.defaultBlockState()));

        List<OreConfiguration.TargetBlockState> diamondOres = List.of(
                OreConfiguration.target(ModConfiguredFeatures.STONE_ORE_REPLACEABLES, Blocks.DIAMOND_ORE.defaultBlockState()),
                OreConfiguration.target(ModConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, Blocks.DEEPSLATE_DIAMOND_ORE.defaultBlockState()));

        FeatureUtils.register(context, DEFAULT_SPEEDRUNNER, Feature.TREE, speedrunnersWasteland().build());
        FeatureUtils.register(context, FANCY_SPEEDRUNNER, Feature.TREE, fancySpeedrunnersWasteland().build());
        FeatureUtils.register(context, PATCH_RAW_SPEEDRUNNER_BLOCK, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.RAW_SPEEDRUNNER_BLOCK)), List.of(Blocks.GRASS_BLOCK)));
        FeatureUtils.register(context, ORE_SPEEDRUNNER, Feature.ORE, new OreConfiguration(speedrunnerOres, 12));
        FeatureUtils.register(context, ORE_SPEEDRUNNER_SMALL, Feature.ORE, new OreConfiguration(speedrunnerOres, 5));
        FeatureUtils.register(context, ORE_EXPERIENCE, Feature.ORE, new OreConfiguration(experienceOres, 4, 0.2F));
        FeatureUtils.register(context, ORE_DIAMOND, Feature.ORE, new OreConfiguration(diamondOres, 8, 0.3F));
        FeatureUtils.register(context, ORE_DIAMOND_BURIED, Feature.ORE, new OreConfiguration(diamondOres, 12, 1.0F));
    }

    private static TreeConfiguration.TreeConfigurationBuilder speedrunnersWasteland() {
        return TreeFeatures.createStraightBlobTree(
                        ModBlocks.SPEEDRUNNER_LOG, ModBlocks.SPEEDRUNNER_LEAVES, 5, 3, 1, 3)
                .ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder fancySpeedrunnersWasteland() {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(
                ModBlocks.SPEEDRUNNER_LOG),
                new FancyTrunkPlacer(3, 13, 0), BlockStateProvider.simple(ModBlocks.SPEEDRUNNER_LEAVES),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
                new TwoLayersFeatureSize(0, 1, 0, OptionalInt.of(4)))
                .ignoreVines();
    }
}