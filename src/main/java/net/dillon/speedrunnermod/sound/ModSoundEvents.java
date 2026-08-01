package net.dillon.speedrunnermod.sound;

import net.dillon.dillonlib.factory.Factories;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.sounds.SoundEvent;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code custom sounds.}
 */
public class ModSoundEvents {
    public static final SoundEvent ENTITY_BOAT_PADDLE_LAVA = Factories.registerSoundEvent(ofSpeedrunnerMod("entity.boat.paddle_lava"));
    public static final SoundEvent WORKBENCH_USE_BOOK = Factories.registerSoundEvent(ofSpeedrunnerMod("workbench.use_book"));
    public static final SoundEvent WORKBENCH_USE_TRANSFER = Factories.registerSoundEvent(ofSpeedrunnerMod("workbench.use_transfer"));

    /**
     * Initializes all speedrunner mod {@code custom sounds.}
     */
    public static void initializeSoundEvents() {
        SpeedrunnerMod.debug("Initialized sound events.");
    }
}