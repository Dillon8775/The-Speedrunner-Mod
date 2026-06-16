package net.dillon.speedrunnermod.world.feature;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.mixin.accessor.TreeFeaturesInvoker;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockPileConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

/**
 * All Speedrunner Mod {@code configured features,} specifically for the {@code speedrunner's wasteland} biome.
 */
public class WastelandFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEFAULT_SPEEDRUNNER = ModWorldFeatures.create("wasteland_default_speedrunner");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_SPEEDRUNNER = ModWorldFeatures.create("wasteland_fancy_speedrunner");
    protected static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_RAW_SPEEDRUNNER_BLOCK = ModWorldFeatures.create("wasteland_patch_raw_speedrunner_block");
    protected static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_SPEEDRUNNER = ModWorldFeatures.create("flower_speedrunner");
    protected static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SPEEDRUNNER = ModWorldFeatures.create("wasteland_ore_speedrunner");
    protected static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SPEEDRUNNER_SMALL = ModWorldFeatures.create("wasteland_ore_speedrunner_small");
    protected static final ResourceKey<ConfiguredFeature<?, ?>> ORE_EXPERIENCE = ModWorldFeatures.create("wasteland_ore_experience");
    protected static final ResourceKey<ConfiguredFeature<?, ?>> ORE_DIAMOND = ModWorldFeatures.create("wasteland_ore_diamond");
    protected static final ResourceKey<ConfiguredFeature<?, ?>> ORE_DIAMOND_BURIED = ModWorldFeatures.create("wasteland_ore_diamond_buried");

    /**
     * See ModWorldGenerator for more.
     */
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        BlockStateProvider belowTrunkProvider = TreeConfiguration.defaultPlaceBelowTreeTrunkProvider(biomes);

        List<OreConfiguration.TargetBlockState> speedrunnerOres = List.of(
                OreConfiguration.target(ModWorldFeatures.STONE_ORE_REPLACEABLES, ModBlocks.SPEEDRUNNER_ORE.defaultBlockState()),
                OreConfiguration.target(ModWorldFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE.defaultBlockState()));

        List<OreConfiguration.TargetBlockState> experienceOres = List.of(
                OreConfiguration.target(ModWorldFeatures.STONE_ORE_REPLACEABLES, ModBlocks.EXPERIENCE_ORE.defaultBlockState()),
                OreConfiguration.target(ModWorldFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_EXPERIENCE_ORE.defaultBlockState()));

        List<OreConfiguration.TargetBlockState> diamondOres = List.of(
                OreConfiguration.target(ModWorldFeatures.STONE_ORE_REPLACEABLES, Blocks.DIAMOND_ORE.defaultBlockState()),
                OreConfiguration.target(ModWorldFeatures.DEEPSLATE_ORE_REPLACEABLES, Blocks.DEEPSLATE_DIAMOND_ORE.defaultBlockState()));

        FeatureUtils.register(context, DEFAULT_SPEEDRUNNER, Feature.TREE, speedrunnersWasteland(belowTrunkProvider).build());
        FeatureUtils.register(context, FANCY_SPEEDRUNNER, Feature.TREE, fancySpeedrunnersWasteland(belowTrunkProvider).build());
        FeatureUtils.register(
                context,
                PATCH_RAW_SPEEDRUNNER_BLOCK,
                Feature.BLOCK_PILE,
                new BlockPileConfiguration(
                        new WeightedStateProvider(WeightedList.<BlockState>builder()
                                .add(ModBlocks.RAW_SPEEDRUNNER_BLOCK.defaultBlockState(), 19)
                                .add(ModBlocks.SPEEDRUNNER_BLOCK.defaultBlockState(), 1))
                )
        );
        FeatureUtils.register(
                context,
                FLOWER_SPEEDRUNNER,
                Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(
                        new WeightedStateProvider(
                                WeightedList.<BlockState>builder()
                                        .add(Blocks.CORNFLOWER.defaultBlockState(), 3)
                                        .add(Blocks.BLUE_ORCHID.defaultBlockState(), 2)
                                        .add(Blocks.WILDFLOWERS.defaultBlockState(), 1)
                        )
                )
        );
        FeatureUtils.register(context, ORE_SPEEDRUNNER, Feature.ORE, new OreConfiguration(speedrunnerOres, 12));
        FeatureUtils.register(context, ORE_SPEEDRUNNER_SMALL, Feature.ORE, new OreConfiguration(speedrunnerOres, 5));
        FeatureUtils.register(context, ORE_EXPERIENCE, Feature.ORE, new OreConfiguration(experienceOres, 4, 0.2F));
        FeatureUtils.register(context, ORE_DIAMOND, Feature.ORE, new OreConfiguration(diamondOres, 8, 0.3F));
        FeatureUtils.register(context, ORE_DIAMOND_BURIED, Feature.ORE, new OreConfiguration(diamondOres, 12, 1.0F));
    }

    private static TreeConfiguration.TreeConfigurationBuilder speedrunnersWasteland(final BlockStateProvider belowTrunkProvider) {
        return TreeFeaturesInvoker.invokeCreateStraightBlobTree(
                        ModBlocks.SPEEDRUNNER_LOG, ModBlocks.SPEEDRUNNER_LEAVES, 5, 3, 1, 3, belowTrunkProvider)
                .ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder fancySpeedrunnersWasteland(final BlockStateProvider belowTrunkProvider) {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(
                ModBlocks.SPEEDRUNNER_LOG),
                new FancyTrunkPlacer(3, 13, 0), BlockStateProvider.simple(ModBlocks.SPEEDRUNNER_LEAVES),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
                new TwoLayersFeatureSize(0, 1, 0, OptionalInt.of(4)),
                belowTrunkProvider
                )
                .ignoreVines();
    }
}