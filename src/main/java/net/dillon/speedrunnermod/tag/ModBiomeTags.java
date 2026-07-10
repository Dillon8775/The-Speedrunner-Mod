package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod biome tags.
 */
public class ModBiomeTags {

    /**
     * Registers a {@code biome tag.}
     */
    private static TagKey<Biome> createBiomeTag(String path) {
        return TagKey.create(Registries.BIOME, ofSpeedrunnerMod(path));
    }

    /**
     * Initializes all Speedrunner Mod {@code attribute tags.}
     */
    public static void initializeBiomeTags() {
        SpeedrunnerMod.debug("Initialized biome tags.");
    }
}