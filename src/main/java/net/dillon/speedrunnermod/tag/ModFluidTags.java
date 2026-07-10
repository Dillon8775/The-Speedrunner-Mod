package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code fluid tags.}
 */
public class ModFluidTags {
    public static final TagKey<Fluid> BOAT_SAFE_FLUIDS = createFluidTag("boat_safe_fluids");

    /**
     * Registers a {@code fluid tag.}
     */
    private static TagKey<Fluid> createFluidTag(String path) {
        return TagKey.create(Registries.FLUID, ofSpeedrunnerMod(path));
    }

    /**
     * Initializes all Speedrunner Mod {@code fluid tags.}
     */
    public static void initializeFluidTags() {
        SpeedrunnerMod.debug("Initialized fluid tags.");
    }
}