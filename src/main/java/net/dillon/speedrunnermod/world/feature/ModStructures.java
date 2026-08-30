package net.dillon.speedrunnermod.world.feature;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod structures.
 */
@Deprecated
@SuppressWarnings("unused")
public class ModStructures {

    /**
     * Bootstraps mod structures.
     */
    public static void bootstrap(final BootstrapContext<Structure> context) {
    }

    /**
     * Creates a {@code structure key.}
     */
    private static ResourceKey<Structure> createStructureKey(final String name) {
        return ResourceKey.create(Registries.STRUCTURE, ofSpeedrunnerMod(name));
    }

    /**
     * Initializes all speedrunner mod structures.
     */
    public static void initializeStructures() {
        SpeedrunnerMod.LOGGER.debug("Initialized structures.");
    }
}