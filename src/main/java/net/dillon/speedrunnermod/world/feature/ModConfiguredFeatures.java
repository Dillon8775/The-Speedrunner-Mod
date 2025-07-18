package net.dillon.speedrunnermod.world.feature;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

/**
 * All Speedrunner Mod {@code configured features.}
 */
public class ModConfiguredFeatures {
    protected static final RuleTest STONE_ORE_REPLACEABLES = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
    protected static final RuleTest DEEPSLATE_ORE_REPLACEABLES = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
    protected static final RuleTest NETHERRACK = new BlockMatchRuleTest(Blocks.NETHERRACK);
    public static final RegistryKey<ConfiguredFeature<?, ?>> DEAD_SPEEDRUNNER = of("speedrunnermod:dead_speedrunner");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DOOM_TREE = of("speedrunnermod:doom_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_DEAD_SPEEDRUNNER_BUSH = of("speedrunnermod:patch_dead_speedrunner_bush");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_SPEEDRUNNER = of("speedrunnermod:ore_speedrunner");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_SPEEDRUNNER_SMALL = of("speedrunnermod:ore_speedrunner_small");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_NETHER_SPEEDRUNNER = of("speedrunnermod:ore_nether_speedrunner");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_IGNEOUS = of("speedrunnermod:ore_igneous");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_NETHER_IGNEOUS = of("speedrunnermod:ore_nether_igneous");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_EXPERIENCE = of("speedrunnermod:ore_experience");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_NETHER_EXPERIENCE = of("speedrunnermod:ore_nether_experience");

    /**
     * See ModWorldGenerator for more.
     */
    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        List<OreFeatureConfig.Target> speedrunnerOres = List.of(
                OreFeatureConfig.createTarget(STONE_ORE_REPLACEABLES, ModBlocks.SPEEDRUNNER_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> netherSpeedrunnerOres = List.of(
                OreFeatureConfig.createTarget(NETHERRACK, ModBlocks.NETHER_SPEEDRUNNER_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> igneousOres = List.of(
                OreFeatureConfig.createTarget(STONE_ORE_REPLACEABLES, ModBlocks.IGNEOUS_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_IGNEOUS_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> netherIgneousOres = List.of(
                OreFeatureConfig.createTarget(NETHERRACK, ModBlocks.NETHER_IGNEOUS_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> experienceOres = List.of(
                OreFeatureConfig.createTarget(STONE_ORE_REPLACEABLES, ModBlocks.EXPERIENCE_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_EXPERIENCE_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> netherExperienceOres = List.of(
                OreFeatureConfig.createTarget(NETHERRACK, ModBlocks.NETHER_EXPERIENCE_ORE.getDefaultState()));

        ConfiguredFeatures.register(context, DEAD_SPEEDRUNNER, Feature.TREE, defaultDeadSpeedrunner().build());
        ConfiguredFeatures.register(context, DOOM_TREE, Feature.TREE, doomTree().build());
        ConfiguredFeatures.register(context, PATCH_DEAD_SPEEDRUNNER_BUSH, Feature.RANDOM_PATCH,
                VegetationConfiguredFeatures.createRandomPatchFeatureConfig(BlockStateProvider.of(ModBlocks.DEAD_SPEEDRUNNER_BUSH), 3));
        ConfiguredFeatures.register(context, ORE_SPEEDRUNNER, Feature.ORE, new OreFeatureConfig(speedrunnerOres, 9));
        ConfiguredFeatures.register(context, ORE_SPEEDRUNNER_SMALL,  Feature.ORE, new OreFeatureConfig(speedrunnerOres, 4));
        ConfiguredFeatures.register(context, ORE_NETHER_SPEEDRUNNER, Feature.ORE, new OreFeatureConfig(netherSpeedrunnerOres, 10));
        ConfiguredFeatures.register(context, ORE_IGNEOUS, Feature.ORE, new OreFeatureConfig(igneousOres, 4, 0.2F));
        ConfiguredFeatures.register(context, ORE_NETHER_IGNEOUS, Feature.ORE, new OreFeatureConfig(netherIgneousOres, 4));
        ConfiguredFeatures.register(context, ORE_EXPERIENCE, Feature.ORE, new OreFeatureConfig(experienceOres, 3, 0.3F));
        ConfiguredFeatures.register(context, ORE_NETHER_EXPERIENCE, Feature.ORE, new OreFeatureConfig(netherExperienceOres, 3));
    }

    private static TreeFeatureConfig.Builder defaultDeadSpeedrunner() {
        return TreeConfiguredFeatures.builder(
                        ModBlocks.DEAD_SPEEDRUNNER_LOG, ModBlocks.DEAD_SPEEDRUNNER_LEAVES, 4, 2, 0, 2)
                .ignoreVines();
    }

    private static TreeFeatureConfig.Builder doomTree() {
        return new TreeFeatureConfig.Builder(BlockStateProvider.of(
                ModBlocks.DOOM_LOG),
                new LargeOakTrunkPlacer(3, 11, 0), BlockStateProvider.of(ModBlocks.DOOM_LEAVES),
                new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(4), 4),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))
                .ignoreVines();
    }

    /**
     * Created because original method uses "Identifier.ofVanilla".
     */
    protected static RegistryKey<ConfiguredFeature<?, ?>> of(String id) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(id));
    }
}