package net.dillon.speedrunnermod.world.feature;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.mixin.accessor.TreeFeaturesInvoker;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.*;
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
    public static final ResourceKey<Feature> DEFAULT_SPEEDRUNNER = ModWorldFeatures.create("wasteland_default_speedrunner");
    public static final ResourceKey<Feature> FANCY_SPEEDRUNNER = ModWorldFeatures.create("wasteland_fancy_speedrunner");
    protected static final ResourceKey<Feature> PATCH_RAW_SPEEDRUNNER_BLOCK = ModWorldFeatures.create("wasteland_patch_raw_speedrunner_block");
    protected static final ResourceKey<Feature> FLOWER_SPEEDRUNNER = ModWorldFeatures.create("flower_speedrunner");
    protected static final ResourceKey<Feature> ORE_SPEEDRUNNER = ModWorldFeatures.create("wasteland_ore_speedrunner");
    protected static final ResourceKey<Feature> ORE_SPEEDRUNNER_SMALL = ModWorldFeatures.create("wasteland_ore_speedrunner_small");
    protected static final ResourceKey<Feature> ORE_EXPERIENCE = ModWorldFeatures.create("wasteland_ore_experience");
    protected static final ResourceKey<Feature> ORE_DIAMOND = ModWorldFeatures.create("wasteland_ore_diamond");
    protected static final ResourceKey<Feature> ORE_DIAMOND_BURIED = ModWorldFeatures.create("wasteland_ore_diamond_buried");

    /**
     * See ModWorldGenerator for more.
     */
    public static void bootstrap(BootstrapContext<Feature> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        BlockStateProvider belowTrunkProvider = TreeFeature.defaultPlaceBelowTreeTrunkProvider(biomes);

        List<BlockReplacement> speedrunnerOres = List.of(
                BlockReplacement.replace(ModWorldFeatures.STONE_ORE_REPLACEABLES, ModBlocks.SPEEDRUNNER_ORE.defaultBlockState()),
                BlockReplacement.replace(ModWorldFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE.defaultBlockState()));

        List<BlockReplacement> experienceOres = List.of(
                BlockReplacement.replace(ModWorldFeatures.STONE_ORE_REPLACEABLES, ModBlocks.EXPERIENCE_ORE.defaultBlockState()),
                BlockReplacement.replace(ModWorldFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_EXPERIENCE_ORE.defaultBlockState()));

        List<BlockReplacement> diamondOres = List.of(
                BlockReplacement.replace(ModWorldFeatures.STONE_ORE_REPLACEABLES, Blocks.DIAMOND_ORE.defaultBlockState()),
                BlockReplacement.replace(ModWorldFeatures.DEEPSLATE_ORE_REPLACEABLES, Blocks.DEEPSLATE_DIAMOND_ORE.defaultBlockState()));

        context.register(DEFAULT_SPEEDRUNNER, speedrunnersWasteland(belowTrunkProvider).build());
        context.register(FANCY_SPEEDRUNNER, fancySpeedrunnersWasteland(belowTrunkProvider).build());
        context.register(
                PATCH_RAW_SPEEDRUNNER_BLOCK,
                new BlockPileFeature(
                        new WeightedStateProvider(WeightedList.<BlockState>builder()
                                .add(ModBlocks.RAW_SPEEDRUNNER_BLOCK.defaultBlockState(), 19)
                                .add(ModBlocks.SPEEDRUNNER_BLOCK.defaultBlockState(), 1))
                )
        );
        context.register(
                FLOWER_SPEEDRUNNER,
                new SimpleBlockFeature(
                        new WeightedStateProvider(
                                WeightedList.<BlockState>builder()
                                        .add(Blocks.CORNFLOWER.defaultBlockState(), 3)
                                        .add(Blocks.BLUE_ORCHID.defaultBlockState(), 2)
                                        .add(Blocks.WILDFLOWERS.defaultBlockState(), 1)
                        )
                )
        );
        context.register(ORE_SPEEDRUNNER, new OreFeature(speedrunnerOres, 12));
        context.register(ORE_SPEEDRUNNER_SMALL, new OreFeature(speedrunnerOres, 5));
        context.register(ORE_EXPERIENCE, new OreFeature(experienceOres, 4, 0.2F));
        context.register(ORE_DIAMOND, new OreFeature(diamondOres, 8, 0.3F));
        context.register(ORE_DIAMOND_BURIED, new OreFeature(diamondOres, 12, 1.0F));
    }

    private static TreeFeature.Builder speedrunnersWasteland(final BlockStateProvider belowTrunkProvider) {
        return TreeFeaturesInvoker.invokeCreateStraightBlobTree(
                        ModBlocks.SPEEDRUNNER_LOG, ModBlocks.SPEEDRUNNER_LEAVES, 5, 3, 1, 3, belowTrunkProvider)
                .ignoreVines();
    }

    private static TreeFeature.Builder fancySpeedrunnersWasteland(final BlockStateProvider belowTrunkProvider) {
        return new TreeFeature.Builder(BlockStateProvider.simple(
                ModBlocks.SPEEDRUNNER_LOG),
                new FancyTrunkPlacer(3, 13, 0), BlockStateProvider.simple(ModBlocks.SPEEDRUNNER_LEAVES),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
                new TwoLayersFeatureSize(0, 1, 0, OptionalInt.of(4)),
                belowTrunkProvider
                )
                .ignoreVines();
    }
}