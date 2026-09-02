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
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
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

        List<BlockReplacement> diamondOres = List.of(
                BlockReplacement.replace(ModWorldFeatures.STONE_ORE_REPLACEABLES, Blocks.DIAMOND_ORE.defaultBlockState()),
                BlockReplacement.replace(ModWorldFeatures.DEEPSLATE_ORE_REPLACEABLES, Blocks.DEEPSLATE_DIAMOND_ORE.defaultBlockState()));

        context.register(ModWorldFeatures.DEAD_SPEEDRUNNER, defaultDeadSpeedrunner(belowTrunkProvider).build());
        context.register(ModWorldFeatures.DOOM_TREE, doomTree(belowTrunkProvider).build());
        context.register(ModWorldFeatures.PATCH_DEAD_SPEEDRUNNER_BUSH, new SimpleBlockFeature(BlockStateProvider.of(ModBlocks.DEAD_SPEEDRUNNER_BUSH)));
        context.register(ModWorldFeatures.ORE_SPEEDRUNNER, new OreFeature(speedrunnerOres, 9));
        context.register(ModWorldFeatures.ORE_SPEEDRUNNER_SMALL,  new OreFeature(speedrunnerOres, 4));
        context.register(ModWorldFeatures.ORE_NETHER_SPEEDRUNNER, new OreFeature(netherSpeedrunnerOres, 10));
        context.register(ModWorldFeatures.ORE_IGNEOUS, new OreFeature(igneousOres, 4, 0.2F));
        context.register(ModWorldFeatures.ORE_NETHER_IGNEOUS, new OreFeature(netherIgneousOres, 4));
        context.register(ModWorldFeatures.ORE_EXPERIENCE, new OreFeature(experienceOres, 3, 0.3F));
        context.register(ModWorldFeatures.ORE_NETHER_EXPERIENCE, new OreFeature(netherExperienceOres, 3));

        context.register(WastelandFeatures.DEFAULT_SPEEDRUNNER, speedrunnersWasteland(belowTrunkProvider).build());
        context.register(WastelandFeatures.FANCY_SPEEDRUNNER, fancySpeedrunnersWasteland(belowTrunkProvider).build());
        context.register(
                WastelandFeatures.PATCH_RAW_SPEEDRUNNER_BLOCK,
                new BlockPileFeature(
                        Holder.direct(
                                new WeightedStateProvider(WeightedList.<BlockState>builder()
                                        .add(ModBlocks.RAW_SPEEDRUNNER_BLOCK.defaultBlockState(), 19)
                                        .add(ModBlocks.SPEEDRUNNER_BLOCK.defaultBlockState(), 1))
                        )
                )
        );
        context.register(
                WastelandFeatures.FLOWER_SPEEDRUNNER,
                new SimpleBlockFeature(
                        new WeightedStateProvider(
                                WeightedList.<BlockState>builder()
                                        .add(Blocks.CORNFLOWER.defaultBlockState(), 3)
                                        .add(Blocks.BLUE_ORCHID.defaultBlockState(), 2)
                                        .add(Blocks.WILDFLOWERS.defaultBlockState(), 1)
                        )
                )
        );
        context.register(WastelandFeatures.ORE_SPEEDRUNNER, new OreFeature(speedrunnerOres, 12));
        context.register(WastelandFeatures.ORE_SPEEDRUNNER_SMALL, new OreFeature(speedrunnerOres, 5));
        context.register(WastelandFeatures.ORE_EXPERIENCE, new OreFeature(experienceOres, 4, 0.2F));
        context.register(WastelandFeatures.ORE_DIAMOND, new OreFeature(diamondOres, 8, 0.3F));
        context.register(WastelandFeatures.ORE_DIAMOND_BURIED, new OreFeature(diamondOres, 12, 1.0F));
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

    private static TreeFeature.Builder speedrunnersWasteland(final Holder<BlockStateProvider> belowTrunkProvider) {
        return TreeFeaturesInvoker.invokeCreateStraightBlobTree(
                        ModBlocks.SPEEDRUNNER_LOG, ModBlocks.SPEEDRUNNER_LEAVES, 5, 3, 1, 3, belowTrunkProvider)
                .ignoreVines();
    }

    private static TreeFeature.Builder fancySpeedrunnersWasteland(final Holder<BlockStateProvider> belowTrunkProvider) {
        return new TreeFeature.Builder(BlockStateProvider.of(
                ModBlocks.SPEEDRUNNER_LOG),
                new FancyTrunkPlacer(3, 13, 0), BlockStateProvider.of(ModBlocks.SPEEDRUNNER_LEAVES),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
                new TwoLayersFeatureSize(0, 1, 0, OptionalInt.of(4)),
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