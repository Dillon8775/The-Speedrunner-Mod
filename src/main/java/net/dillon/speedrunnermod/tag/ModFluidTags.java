package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

import static net.dillon.dillonlib.factory.Factories.createFluidTag;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code fluid tags.}
 */
public class ModFluidTags {
    public static final TagKey<Fluid> BOAT_SAFE_FLUIDS = createFluidTag(ofSpeedrunnerMod("boat_safe_fluids"));

    /**
     * Initializes all Speedrunner Mod {@code fluid tags.}
     */
    public static void initializeFluidTags() {
        SpeedrunnerMod.LOGGER.debug("Initialized fluid tags.");
    }
}