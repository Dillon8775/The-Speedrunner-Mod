package net.dillon.speedrunnermod.world.feature;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.StructureSet;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod structure sets.
 */
@Deprecated
@SuppressWarnings("unused")
public class ModStructureSets {

    /**
     * Bootstraps mod structure sets.
     */
    public static void bootstrap(final BootstrapContext<StructureSet> context) {
    }

    /**
     * Creates a {@code structure set key.}
     */
    private static ResourceKey<StructureSet> createStructureSetKey(final String name) {
        return ResourceKey.create(Registries.STRUCTURE_SET, ofSpeedrunnerMod(name));
    }

    /**
     * Initializes all speedrunner mod structure sets.
     */
    public static void initializeStructureSets() {
        SpeedrunnerMod.LOGGER.debug("Initialized structure sets.");
    }
}