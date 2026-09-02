package net.dillon.speedrunnermod.world.feature;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.mixin.accessor.TreeFeaturesInvoker;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BlockStateProviders;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.*;
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
    public static final ResourceKey<Feature> DEAD_SPEEDRUNNER = create("dead_speedrunner");
    protected static final ResourceKey<Feature> DOOM_TREE = create("doom_tree");
    protected static final ResourceKey<Feature> PATCH_DEAD_SPEEDRUNNER_BUSH = create("patch_dead_speedrunner_bush");
    protected static final ResourceKey<Feature> ORE_SPEEDRUNNER = create("ore_speedrunner");
    protected static final ResourceKey<Feature> ORE_SPEEDRUNNER_SMALL = create("ore_speedrunner_small");
    protected static final ResourceKey<Feature> ORE_NETHER_SPEEDRUNNER = create("ore_nether_speedrunner");
    protected static final ResourceKey<Feature> ORE_IGNEOUS = create("ore_igneous");
    protected static final ResourceKey<Feature> ORE_NETHER_IGNEOUS = create("ore_nether_igneous");
    protected static final ResourceKey<Feature> ORE_EXPERIENCE = create("ore_experience");
    protected static final ResourceKey<Feature> ORE_NETHER_EXPERIENCE = create("ore_nether_experience");

    /**
     * See ModWorldGenerator for more.
     */
    public static void bootstrap(BootstrapContext<Feature> context) {
        HolderGetter<BlockStateProvider> blockStateProviders = context.lookup(Registries.BLOCK_STATE_PROVIDER);
        Holder<BlockStateProvider> belowTrunkProvider = blockStateProviders.getOrThrow(BlockStateProviders.SOIL_BENEATH_TREE);

        List<BlockReplacement> speedrunnerOres = List.of(
                BlockReplacement.replace(STONE_ORE_REPLACEABLES, ModBlocks.SPEEDRUNNER_ORE.defaultBlockState()),
                BlockReplacement.replace(DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE.defaultBlockState()));

        List<BlockReplacement> netherSpeedrunnerOres = List.of(
                BlockReplacement.replace(NETHERRACK, ModBlocks.NETHER_SPEEDRUNNER_ORE.defaultBlockState()));

        List<BlockReplacement> igneousOres = List.of(
                BlockReplacement.replace(STONE_ORE_REPLACEABLES, ModBlocks.IGNEOUS_ORE.defaultBlockState()),
                BlockReplacement.replace(DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_IGNEOUS_ORE.defaultBlockState()));

        List<BlockReplacement> netherIgneousOres = List.of(
                BlockReplacement.replace(NETHERRACK, ModBlocks.NETHER_IGNEOUS_ORE.defaultBlockState()));

        List<BlockReplacement> experienceOres = List.of(
                BlockReplacement.replace(STONE_ORE_REPLACEABLES, ModBlocks.EXPERIENCE_ORE.defaultBlockState()),
                BlockReplacement.replace(DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_EXPERIENCE_ORE.defaultBlockState()));

        List<BlockReplacement> netherExperienceOres = List.of(
                BlockReplacement.replace(NETHERRACK, ModBlocks.NETHER_EXPERIENCE_ORE.defaultBlockState()));

        context.register(DEAD_SPEEDRUNNER, defaultDeadSpeedrunner(belowTrunkProvider).build());
        context.register(DOOM_TREE, doomTree(belowTrunkProvider).build());
        context.register(PATCH_DEAD_SPEEDRUNNER_BUSH, new SimpleBlockFeature(BlockStateProvider.of(ModBlocks.DEAD_SPEEDRUNNER_BUSH)));
        context.register(ORE_SPEEDRUNNER, new OreFeature(speedrunnerOres, 9));
        context.register(ORE_SPEEDRUNNER_SMALL,  new OreFeature(speedrunnerOres, 4));
        context.register(ORE_NETHER_SPEEDRUNNER, new OreFeature(netherSpeedrunnerOres, 10));
        context.register(ORE_IGNEOUS, new OreFeature(igneousOres, 4, 0.2F));
        context.register(ORE_NETHER_IGNEOUS, new OreFeature(netherIgneousOres, 4));
        context.register(ORE_EXPERIENCE, new OreFeature(experienceOres, 3, 0.3F));
        context.register(ORE_NETHER_EXPERIENCE, new OreFeature(netherExperienceOres, 3));
    }

    private static TreeFeature.Builder defaultDeadSpeedrunner(final Holder<BlockStateProvider> belowTrunkProvider) {
        return TreeFeaturesInvoker.invokeCreateStraightBlobTree(
                        ModBlocks.DEAD_SPEEDRUNNER_LOG, ModBlocks.DEAD_SPEEDRUNNER_LEAVES, 4, 2, 0, 2, belowTrunkProvider)
                .ignoreVines();
    }

    private static TreeFeature.Builder doomTree(final Holder<BlockStateProvider> belowTrunkProvider) {
        return new TreeFeature.Builder(
                BlockStateProvider.of(ModBlocks.DOOM_LOG),
                new FancyTrunkPlacer(3, 11, 0),
                BlockStateProvider.of(ModBlocks.DOOM_LEAVES),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)),
                belowTrunkProvider
        )
                .ignoreVines();
    }

    /**
     * Creates a mod world feature.
     */
    protected static ResourceKey<Feature> create(final String id) {
        return ResourceKey.create(Registries.FEATURE, ofSpeedrunnerMod(id));
    }
}