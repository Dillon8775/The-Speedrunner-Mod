package net.dillon.speedrunnermod.world.feature;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.mixin.accessor.TreeFeaturesInvoker;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.biome.Biome;
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
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;
import java.util.OptionalInt;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code configured features.}
 */
public class ModWorldFeatures {
    protected static final RuleTest STONE_ORE_REPLACEABLES = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
    protected static final RuleTest DEEPSLATE_ORE_REPLACEABLES = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
    protected static final RuleTest NETHERRACK = new BlockMatchTest(Blocks.NETHERRACK);
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEAD_SPEEDRUNNER = create("dead_speedrunner");
    protected static final ResourceKey<ConfiguredFeature<?, ?>> DOOM_TREE = create("doom_tree");
    protected static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_DEAD_SPEEDRUNNER_BUSH = create("patch_dead_speedrunner_bush");
    protected static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SPEEDRUNNER = create("ore_speedrunner");
    protected static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SPEEDRUNNER_SMALL = create("ore_speedrunner_small");
    protected static final ResourceKey<ConfiguredFeature<?, ?>> ORE_NETHER_SPEEDRUNNER = create("ore_nether_speedrunner");
    protected static final ResourceKey<ConfiguredFeature<?, ?>> ORE_IGNEOUS = create("ore_igneous");
    protected static final ResourceKey<ConfiguredFeature<?, ?>> ORE_NETHER_IGNEOUS = create("ore_nether_igneous");
    protected static final ResourceKey<ConfiguredFeature<?, ?>> ORE_EXPERIENCE = create("ore_experience");
    protected static final ResourceKey<ConfiguredFeature<?, ?>> ORE_NETHER_EXPERIENCE = create("ore_nether_experience");

    /**
     * See ModWorldGenerator for more.
     */
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        BlockStateProvider belowTrunkProvider = TreeConfiguration.defaultPlaceBelowTreeTrunkProvider(biomes);

        List<OreConfiguration.TargetBlockState> speedrunnerOres = List.of(
                OreConfiguration.target(STONE_ORE_REPLACEABLES, ModBlocks.SPEEDRUNNER_ORE.defaultBlockState()),
                OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE.defaultBlockState()));

        List<OreConfiguration.TargetBlockState> netherSpeedrunnerOres = List.of(
                OreConfiguration.target(NETHERRACK, ModBlocks.NETHER_SPEEDRUNNER_ORE.defaultBlockState()));

        List<OreConfiguration.TargetBlockState> igneousOres = List.of(
                OreConfiguration.target(STONE_ORE_REPLACEABLES, ModBlocks.IGNEOUS_ORE.defaultBlockState()),
                OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_IGNEOUS_ORE.defaultBlockState()));

        List<OreConfiguration.TargetBlockState> netherIgneousOres = List.of(
                OreConfiguration.target(NETHERRACK, ModBlocks.NETHER_IGNEOUS_ORE.defaultBlockState()));

        List<OreConfiguration.TargetBlockState> experienceOres = List.of(
                OreConfiguration.target(STONE_ORE_REPLACEABLES, ModBlocks.EXPERIENCE_ORE.defaultBlockState()),
                OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_EXPERIENCE_ORE.defaultBlockState()));

        List<OreConfiguration.TargetBlockState> netherExperienceOres = List.of(
                OreConfiguration.target(NETHERRACK, ModBlocks.NETHER_EXPERIENCE_ORE.defaultBlockState()));

        FeatureUtils.register(context, DEAD_SPEEDRUNNER, Feature.TREE, defaultDeadSpeedrunner(belowTrunkProvider).build());
        FeatureUtils.register(context, DOOM_TREE, Feature.TREE, doomTree(belowTrunkProvider).build());
        FeatureUtils.register(context, PATCH_DEAD_SPEEDRUNNER_BUSH, Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.DEAD_SPEEDRUNNER_BUSH)));
        FeatureUtils.register(context, ORE_SPEEDRUNNER, Feature.ORE, new OreConfiguration(speedrunnerOres, 9));
        FeatureUtils.register(context, ORE_SPEEDRUNNER_SMALL,  Feature.ORE, new OreConfiguration(speedrunnerOres, 4));
        FeatureUtils.register(context, ORE_NETHER_SPEEDRUNNER, Feature.ORE, new OreConfiguration(netherSpeedrunnerOres, 10));
        FeatureUtils.register(context, ORE_IGNEOUS, Feature.ORE, new OreConfiguration(igneousOres, 4, 0.2F));
        FeatureUtils.register(context, ORE_NETHER_IGNEOUS, Feature.ORE, new OreConfiguration(netherIgneousOres, 4));
        FeatureUtils.register(context, ORE_EXPERIENCE, Feature.ORE, new OreConfiguration(experienceOres, 3, 0.3F));
        FeatureUtils.register(context, ORE_NETHER_EXPERIENCE, Feature.ORE, new OreConfiguration(netherExperienceOres, 3));
    }

    private static TreeConfiguration.TreeConfigurationBuilder defaultDeadSpeedrunner(final BlockStateProvider belowTrunkProvider) {
        return TreeFeaturesInvoker.invokeCreateStraightBlobTree(
                        ModBlocks.DEAD_SPEEDRUNNER_LOG, ModBlocks.DEAD_SPEEDRUNNER_LEAVES, 4, 2, 0, 2, belowTrunkProvider)
                .ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder doomTree(final BlockStateProvider belowTrunkProvider) {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.DOOM_LOG),
                new FancyTrunkPlacer(3, 11, 0),
                BlockStateProvider.simple(ModBlocks.DOOM_LEAVES),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)),
                belowTrunkProvider
        )
                .ignoreVines();
    }

    /**
     * Creates a mod world feature.
     */
    protected static ResourceKey<ConfiguredFeature<?, ?>> create(final String id) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ofSpeedrunnerMod(id));
    }
}