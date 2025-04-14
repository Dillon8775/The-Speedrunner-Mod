package net.dillon.speedrunnermod.world.biome;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.biome.Biome;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;
import static net.dillon.speedrunnermod.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code biome keys.}
 * <p>Note: These do not contain the biome's features, it just registers the biome itself. See {@link ModBiomes} for biome features.</p>
 */
public class ModBiomeKeys {
    public static final RegistryKey<Biome> SPEEDRUNNERS_WASTELAND_KEY = RegistryKey.of(RegistryKeys.BIOME, ofSpeedrunnerMod("speedrunners_wasteland"));

    /**
     * Initializes this class, registering the {@code Speedrunner's Wasteland} biome.
     */
    public static void initializeBiomeKeys() {
        info("Initialized the Speedrunner's Wasteland biome.");
    }
}