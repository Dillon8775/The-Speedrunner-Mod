package net.dillon.speedrunnermod.world;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.world.biome.ModBiomeKeys;
import net.dillon.speedrunnermod.world.feature.ModPlacedFeatures;
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
public class ModWorldGen {

    /**
     * Initializes all Speedrunner Mod {@code custom world gen features.}
     */
    public static void initializeWorldGenFeatures() {
        ModBiomeKeys.initializeBiomeKeys();

        addOres();
        addVegetalDecoration();

        if (isDoomMode()) {
            if (options().main.strongholdLibraryCount.getCurrentValue() > 5) {
                options().main.strongholdLibraryCount.set(5);
                warn("Doom mode is on, and detected too high stronghold library count. Setting to 5. May require a restart to take full effect.");
            }
        }

        SpeedrunnerMod.debug("Initialized world gen features.");
    }

    /**
     * All Speedrunner Mod {@code underground ore features.}
     */
    private static void addOres() {
        BiomeModifications.addFeature(BiomeSelectors.excludeByKey(ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.ORE_SPEEDRUNNER_UPPER);

        BiomeModifications.addFeature(BiomeSelectors.excludeByKey(ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.ORE_SPEEDRUNNER_MIDDLE);

        BiomeModifications.addFeature(BiomeSelectors.excludeByKey(ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.ORE_SPEEDRUNNER_SMALL);

        BiomeModifications.addFeature(BiomeSelectors.excludeByKey(ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.ORE_EXPERIENCE);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.BASALT_DELTAS),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.ORE_SPEEDRUNNER_DELTAS);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.BASALT_DELTAS),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.ORE_IGNEOUS_DELTAS);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.BASALT_DELTAS),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.ORE_EXPERIENCE_DELTAS);

        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.ORE_SPEEDRUNNER_NETHER);

        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.ORE_IGNEOUS_NETHER);

        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.ORE_EXPERIENCE_NETHER);
    }

    /**
     * All Speedrunner Mod {@code vegetation decoration features.}
     */
    private static void addVegetalDecoration() {
        if (options().advanced.generateSpeedrunnerWood.getCurrentValue()) {
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                            Biomes.DESERT,
                            Biomes.BADLANDS),
                    GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.DEAD_SPEEDRUNNER_PLACED);

            BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.HAS_SWAMP_HUT),
                    GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.PATCH_DEAD_SPEEDRUNNER_BUSH_SWAMP);

            BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.HAS_DESERT_PYRAMID),
                    GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.PATCH_DEAD_SPEEDRUNNER_BUSH_DESERT);

            BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_BADLANDS),
                    GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.PATCH_DEAD_SPEEDRUNNER_BUSH_BADLANDS);

            BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SOUL_SAND_VALLEY),
                    GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.DEAD_SPEEDRUNNER_PLACED_NETHER);
        }

        if (isDoomMode()) {
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.THE_END),
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    ModPlacedFeatures.DOOM_TREE_PLACED);
        }
    }
}