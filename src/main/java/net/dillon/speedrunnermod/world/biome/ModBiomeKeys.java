package net.dillon.speedrunnermod.world.biome;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code biome keys.}
 * <p>Note: These do not contain the biome's features, it just registers the biome itself. See {@link ModBiomes} for biome features.</p>
 */
public class ModBiomeKeys {
    public static final ResourceKey<Biome> SPEEDRUNNERS_WASTELAND_KEY = ResourceKey.create(Registries.BIOME, ofSpeedrunnerMod("speedrunners_wasteland"));

    /**
     * Initializes this class, registering the {@code Speedrunner's Wasteland} biome.
     */
    public static void initializeBiomeKeys() {
        SpeedrunnerMod.debug("Initialized the Speedrunner's Wasteland biome.");
    }
}