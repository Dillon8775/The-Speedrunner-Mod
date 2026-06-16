package net.dillon.speedrunnermod.world;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.world.biome.ModBiomes;
import net.dillon.speedrunnermod.world.feature.ModWorldPlacements;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.warn;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

/**
 * All Speedrunner Mod {@code custom world gen features.}
 */
public class ModWorldGeneration {

    /**
     * Initializes all Speedrunner Mod {@code custom world gen features.}
     */
    public static void initializeWorldGenFeatures() {
        ModBiomes.initializeBiomes();

        addOres();
        addVegetalDecoration();

        if (isDoomMode() && options().worldGen.strongholdLibraryCount.getCurrentValue() > 5) {
            options().worldGen.strongholdLibraryCount.set(5);
            warn("Doom mode is on, and detected too high stronghold library count. Setting to 5. May require a restart to take full effect.");
        }

        SpeedrunnerMod.debug("Initialized world gen features.");
    }

    /**
     * All Speedrunner Mod {@code underground ore features.}
     */
    private static void addOres() {
        BiomeModifications.addFeature(BiomeSelectors.excludeByKey(ModBiomes.SPEEDRUNNERS_WASTELAND),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModWorldPlacements.ORE_SPEEDRUNNER_UPPER);

        BiomeModifications.addFeature(BiomeSelectors.excludeByKey(ModBiomes.SPEEDRUNNERS_WASTELAND),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModWorldPlacements.ORE_SPEEDRUNNER_MIDDLE);

        BiomeModifications.addFeature(BiomeSelectors.excludeByKey(ModBiomes.SPEEDRUNNERS_WASTELAND),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModWorldPlacements.ORE_SPEEDRUNNER_SMALL);

        BiomeModifications.addFeature(BiomeSelectors.excludeByKey(ModBiomes.SPEEDRUNNERS_WASTELAND),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModWorldPlacements.ORE_EXPERIENCE);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.BASALT_DELTAS),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModWorldPlacements.ORE_SPEEDRUNNER_DELTAS);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.BASALT_DELTAS),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModWorldPlacements.ORE_IGNEOUS_DELTAS);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.BASALT_DELTAS),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModWorldPlacements.ORE_EXPERIENCE_DELTAS);

        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModWorldPlacements.ORE_SPEEDRUNNER_NETHER);

        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModWorldPlacements.ORE_IGNEOUS_NETHER);

        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModWorldPlacements.ORE_EXPERIENCE_NETHER);
    }

    /**
     * All Speedrunner Mod {@code vegetation decoration features.}
     */
    private static void addVegetalDecoration() {
        if (options().advanced.generateSpeedrunnerWood.getCurrentValue()) {
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                            Biomes.DESERT,
                            Biomes.BADLANDS),
                    GenerationStep.Decoration.VEGETAL_DECORATION, ModWorldPlacements.DEAD_SPEEDRUNNER_PLACED);

            BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.HAS_SWAMP_HUT),
                    GenerationStep.Decoration.VEGETAL_DECORATION, ModWorldPlacements.PATCH_DEAD_SPEEDRUNNER_BUSH_SWAMP);

            BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.HAS_DESERT_PYRAMID),
                    GenerationStep.Decoration.VEGETAL_DECORATION, ModWorldPlacements.PATCH_DEAD_SPEEDRUNNER_BUSH_DESERT);

            BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_BADLANDS),
                    GenerationStep.Decoration.VEGETAL_DECORATION, ModWorldPlacements.PATCH_DEAD_SPEEDRUNNER_BUSH_BADLANDS);

            BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SOUL_SAND_VALLEY),
                    GenerationStep.Decoration.VEGETAL_DECORATION, ModWorldPlacements.DEAD_SPEEDRUNNER_PLACED_NETHER);
        }

        if (isDoomMode()) {
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.THE_END),
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    ModWorldPlacements.DOOM_TREE_PLACED);
        }
    }
}