package net.dillon.speedrunnermod.world;

import net.dillon.speedrunnermod.world.biome.ModBiomeKeys;
import net.dillon.speedrunnermod.world.feature.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.structure.NetherFortressGenerator;
import net.minecraft.structure.StrongholdGenerator;
import net.minecraft.util.collection.Pool;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.SpeedrunnerMod.warn;

/**
 * All Speedrunner Mod {@code custom world gen features.}
 */
public class ModWorldGen {
    public static final Pool<SpawnSettings.SpawnEntry> NETHER_FORTRESS_MOB_SPAWNS;
    public static final NetherFortressGenerator.PieceData[] NETHER_FORTRESS_GENERATION_BRIDGE;
    public static final NetherFortressGenerator.PieceData[] NETHER_FORTRESS_GENERATION_CORRIDOR;
    public static final StrongholdGenerator.PieceData[] STRONGHOLD_GENERATION;

    /**
     * Initializes all Speedrunner Mod {@code custom world gen features.}
     */
    public static void initializeWorldGenFeatures() {
        ModBiomeKeys.initializeBiomeKeys();

        addOres();
        addVegetalDecoration();

        if (options().main.playingMode.doom()) {
            if (options().main.strongholdLibraryCount > 5) {
                options().main.strongholdLibraryCount = 5;
                warn("Doom mode is on, and detected too high stronghold library count. Setting to 5. May require a restart to take full effect.");
            }
        }
    }

    /**
     * All Speedrunner Mod {@code underground ore features.}
     */
    private static void addOres() {
        BiomeModifications.addFeature(BiomeSelectors.excludeByKey(ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_SPEEDRUNNER_UPPER);

        BiomeModifications.addFeature(BiomeSelectors.excludeByKey(ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_SPEEDRUNNER_MIDDLE);

        BiomeModifications.addFeature(BiomeSelectors.excludeByKey(ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_SPEEDRUNNER_SMALL);

        if (options().main.playingMode.easy()) {
            BiomeModifications.addFeature(BiomeSelectors.excludeByKey(ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY),
                    GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_EXPERIENCE);
        }

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.BASALT_DELTAS),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_SPEEDRUNNER_DELTAS);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.BASALT_DELTAS),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_IGNEOUS_DELTAS);

        if (options().main.playingMode.easy()) {
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.BASALT_DELTAS),
                    GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_EXPERIENCE_DELTAS);
        }

        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_SPEEDRUNNER_NETHER);

        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_IGNEOUS_NETHER);

        if (options().main.playingMode.easy()) {
            BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(),
                    GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_EXPERIENCE_NETHER);
        }
    }

    /**
     * All Speedrunner Mod {@code vegetation decoration features.}
     */
    private static void addVegetalDecoration() {
        if (options().advanced.generateSpeedrunnerWood) {
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                            BiomeKeys.PLAINS,
                            BiomeKeys.FOREST,
                            BiomeKeys.SAVANNA,
                            BiomeKeys.SWAMP,
                            BiomeKeys.JUNGLE,
                            BiomeKeys.WINDSWEPT_HILLS,
                            BiomeKeys.TAIGA),
                    GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.DEFAULT_SPEEDRUNNER_PLACED);

            BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_FOREST),
                    GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.DEFAULT_SPEEDRUNNER_PLACED_FOREST);

            BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.VILLAGE_PLAINS_HAS_STRUCTURE),
                    GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.FANCY_SPEEDRUNNER_PLACED);

            BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                            BiomeKeys.DESERT,
                            BiomeKeys.BADLANDS),
                    GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.DEAD_SPEEDRUNNER_PLACED);

            BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST, BiomeKeys.OLD_GROWTH_BIRCH_FOREST),
                    GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.BIRCH_SPEEDRUNNER_PLACED);

            BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_SAVANNA),
                    GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SAVANNA_SPEEDRUNNER_PLACED);

            BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_TAIGA),
                    GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.TAIGA_SPEEDRUNNER_PLACED);

            BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.JUNGLE),
                    GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.MEGA_JUNGLE_SPEEDRUNNER_PLACED);

            BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.DARK_FOREST),
                    GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.BIG_SPEEDRUNNER_PLACED);

            BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.SWAMP_HUT_HAS_STRUCTURE),
                    GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.PATCH_DEAD_SPEEDRUNNER_BUSH_SWAMP);

            BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.DESERT_PYRAMID_HAS_STRUCTURE),
                    GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.PATCH_DEAD_SPEEDRUNNER_BUSH_DESERT);

            BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_BADLANDS),
                    GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.PATCH_DEAD_SPEEDRUNNER_BUSH_BADLANDS);

            BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.SOUL_SAND_VALLEY),
                    GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.DEAD_FANCY_SPEEDRUNNER_PLACED);
        }

        if (options().main.playingMode.doom()) {
            BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    ModPlacedFeatures.DOOM_TREE_PLACED);
        }
    }

    static {
        STRONGHOLD_GENERATION = options().main.playingMode.doom() && options().advanced.modifiedStrongholdGeneration ? // Doom Mode generation
                new StrongholdGenerator.PieceData[]{
                        new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 25, 5),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 50, 5),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 25, 5),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 25, 5),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 75, 5),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 50, 5),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 50, 5),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 50, 5),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 25, 5),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.Library.class, 100, options().main.strongholdLibraryCount * 2) {

                            @Override
                            public boolean canGenerate(int chainLength) {
                                return super.canGenerate(chainLength) && chainLength > 3;
                            }
                        }, new StrongholdGenerator.PieceData(StrongholdGenerator.PortalRoom.class, 50, 1) {

                    @Override
                    public boolean canGenerate(int chainLength) {
                        return super.canGenerate(chainLength) && chainLength > 5;
                    }
                }} /* -> DEFAULT Generation (for balanced mode) */ :
                options().main.playingMode.easy() && options().advanced.modifiedStrongholdGeneration ?
                        /* -> Otherwise, do Speedrunner Mod generation */ new StrongholdGenerator.PieceData[]{new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 20, 2),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 5, 1),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 10, 2),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 10, 2),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 20, 1),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 10, 1),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 10, 1),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 10, 2),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 25, 2),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.PortalRoom.class, 200, options().main.strongholdPortalRoomCount) {

                            @Override
                            public boolean canGenerate(int chainLength) {
                                return super.canGenerate(chainLength);
                            }
                        }, new StrongholdGenerator.PieceData(StrongholdGenerator.Library.class, 200, options().main.strongholdLibraryCount) {

                    @Override
                    public boolean canGenerate(int chainLength) {
                        return super.canGenerate(chainLength);
                    }
                }} :
                        StrongholdGenerator.ALL_PIECES; // Land onto default generation otherwise.

        NETHER_FORTRESS_GENERATION_BRIDGE = /* -> DEFAULT Generation if on balanced mode */ options().main.playingMode.balanced() ? NetherFortressGenerator.ALL_BRIDGE_PIECES :
                new NetherFortressGenerator.PieceData[]{ // Otherwise, use modified generation
                new NetherFortressGenerator.PieceData(NetherFortressGenerator.Bridge.class, 10, 1),
                new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeCrossing.class, 10, 2),
                new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeSmallCrossing.class, 10, 2),
                new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeStairs.class, 10, 1),
                new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgePlatform.class, 50, 3),
                new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorExit.class, 10, 1)};

        NETHER_FORTRESS_GENERATION_CORRIDOR = /* -> DEFALT Generation if on balanced mode */ options().main.playingMode.balanced() ? NetherFortressGenerator.ALL_CORRIDOR_PIECES : new NetherFortressGenerator.PieceData[]{
                new NetherFortressGenerator.PieceData(NetherFortressGenerator.SmallCorridor.class, 10, 2), // Otherwise, use modified generation
                new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorCrossing.class, 10, 2),
                new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorRightTurn.class, 25, 3),
                new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorLeftTurn.class, 25, 3),
                new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorStairs.class, 10, 2, true),
                new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorBalcony.class, 7, 2),
                new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorNetherWartsRoom.class, 20, 2)};

        NETHER_FORTRESS_MOB_SPAWNS = options().main.playingMode.doom() ?
                Pool.of(
                        new SpawnSettings.SpawnEntry(EntityType.BLAZE, 50, 1, 4),
                        new SpawnSettings.SpawnEntry(EntityType.PIGLIN_BRUTE, 25, 2, 4),
                        new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 25, 1, 1),
                        new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 75, 4, 12),
                        new SpawnSettings.SpawnEntry(EntityType.SKELETON, 50, 5, 5),
                        new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 20, 1, 4)) :
                Pool.of(
                        new SpawnSettings.SpawnEntry(EntityType.BLAZE, 15, 1, 4),
                        new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 15, 2, 4),
                        new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 3, 1, 2),
                        new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 8, 1, 2),
                        new SpawnSettings.SpawnEntry(EntityType.SKELETON, 1, 1, 2),
                        new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 1, 1, 1));
    }
}