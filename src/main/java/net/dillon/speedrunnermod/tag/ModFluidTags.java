package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code fluid tags.}
 */
public class ModFluidTags {
    public static TagKey<Fluid> BOAT_SAFE_FLUIDS = of("boat_safe_fluids");

    /**
     * Registers a {@code fluid tag.}
     */
    private static TagKey<Fluid> of(String path) {
        return TagKey.of(RegistryKeys.FLUID, ofSpeedrunnerMod(path));
    }

    /**
     * Initializes all Speedrunner Mod {@code fluid tags.}
     */
    public static void initializeFluidTags() {
        SpeedrunnerMod.debug("Initialized fluid tags.");
    }
}