package net.dillon.speedrunnermod.sound;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code custom sounds.}
 */
public class ModSoundEvents {
    public static final SoundEvent ENTITY_BOAT_PADDLE_LAVA = of("entity.boat.paddle_lava");

    /**
     * Registers a {@code sound event.}
     */
    private static SoundEvent of(String path) {
        return Registry.register(BuiltInRegistries.SOUND_EVENT, "speedrunnermod:" + path, SoundEvent.createVariableRangeEvent(ofSpeedrunnerMod(path)));
    }

    /**
     * Initializes all speedrunner mod {@code custom sounds.}
     */
    public static void initializeSoundEvents() {
        SpeedrunnerMod.debug("Initialized sound events.");
    }
}