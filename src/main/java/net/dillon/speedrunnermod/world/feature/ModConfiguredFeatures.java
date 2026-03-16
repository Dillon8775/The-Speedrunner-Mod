package net.dillon.speedrunnermod.world.feature;

import com.google.common.collect.ImmutableList;
import net.dillon.speedrunnermod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaPineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AlterGroundDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

/**
 * All Speedrunner Mod {@code configured features.}
 */
public class ModConfiguredFeatures {
    protected static final RuleTest STONE_ORE_REPLACEABLES = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
    protected static final RuleTest DEEPSLATE_ORE_REPLACEABLES = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
    protected static final RuleTest NETHERRACK = new BlockMatchTest(Blocks.NETHERRACK);
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEAD_SPEEDRUNNER = of("speedrunnermod:dead_speedrunner");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DOOM_TREE = of("speedrunnermod:doom_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_DEAD_SPEEDRUNNER_BUSH = of("speedrunnermod:patch_dead_speedrunner_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SPEEDRUNNER = of("speedrunnermod:ore_speedrunner");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SPEEDRUNNER_SMALL = of("speedrunnermod:ore_speedrunner_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_NETHER_SPEEDRUNNER = of("speedrunnermod:ore_nether_speedrunner");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_IGNEOUS = of("speedrunnermod:ore_igneous");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_NETHER_IGNEOUS = of("speedrunnermod:ore_nether_igneous");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_EXPERIENCE = of("speedrunnermod:ore_experience");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_NETHER_EXPERIENCE = of("speedrunnermod:ore_nether_experience");

    /**
     * See ModWorldGenerator for more.
     */
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
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

        FeatureUtils.register(context, DEAD_SPEEDRUNNER, Feature.TREE, defaultDeadSpeedrunner().build());
        FeatureUtils.register(context, DOOM_TREE, Feature.TREE, doomTree().build());
        FeatureUtils.register(context, PATCH_DEAD_SPEEDRUNNER_BUSH, Feature.RANDOM_PATCH,
                VegetationFeatures.grassPatch(BlockStateProvider.simple(ModBlocks.DEAD_SPEEDRUNNER_BUSH), 3));
        FeatureUtils.register(context, ORE_SPEEDRUNNER, Feature.ORE, new OreConfiguration(speedrunnerOres, 9));
        FeatureUtils.register(context, ORE_SPEEDRUNNER_SMALL,  Feature.ORE, new OreConfiguration(speedrunnerOres, 4));
        FeatureUtils.register(context, ORE_NETHER_SPEEDRUNNER, Feature.ORE, new OreConfiguration(netherSpeedrunnerOres, 10));
        FeatureUtils.register(context, ORE_IGNEOUS, Feature.ORE, new OreConfiguration(igneousOres, 4, 0.2F));
        FeatureUtils.register(context, ORE_NETHER_IGNEOUS, Feature.ORE, new OreConfiguration(netherIgneousOres, 4));
        FeatureUtils.register(context, ORE_EXPERIENCE, Feature.ORE, new OreConfiguration(experienceOres, 3, 0.3F));
        FeatureUtils.register(context, ORE_NETHER_EXPERIENCE, Feature.ORE, new OreConfiguration(netherExperienceOres, 3));
    }

    private static TreeConfiguration.TreeConfigurationBuilder defaultDeadSpeedrunner() {
        return TreeFeatures.createStraightBlobTree(
                        ModBlocks.DEAD_SPEEDRUNNER_LOG, ModBlocks.DEAD_SPEEDRUNNER_LEAVES, 4, 2, 0, 2)
                .ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder doomTree() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.DOOM_LOG),
                new GiantTrunkPlacer(13, 2, 14),
                BlockStateProvider.simple(ModBlocks.DOOM_LEAVES),
                new MegaPineFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), UniformInt.of(13, 17)),
                new TwoLayersFeatureSize(1, 1, 2)
        ).decorators(ImmutableList.of(new AlterGroundDecorator(BlockStateProvider.simple(Blocks.PODZOL))));
    }

    /**
     * Created because original method uses "Identifier.ofVanilla".
     */
    protected static ResourceKey<ConfiguredFeature<?, ?>> of(String id) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.parse(id));
    }
}