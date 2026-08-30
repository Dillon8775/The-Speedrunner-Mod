package net.dillon.speedrunnermod.world.biome;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.world.ModWorldGeneration;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;
import static net.dillon.speedrunnermod.world.biome.SpeedrunnersWastelandBiome.createSpeedrunnersWasteland;

/**
 * All Speedrunner Mod {@code biome keys.}
 * <p>Note: These do not contain the biome's features, it just registers the biome itself. See {@link SpeedrunnersWastelandBiome} for biome features.</p>
 */
public class ModBiomes {
    public static final ResourceKey<Biome> SPEEDRUNNERS_WASTELAND = createBiomeKey("speedrunners_wasteland");

    /**
     * See {@link ModWorldGeneration} for more.
     */
    public static void bootstrap(BootstrapContext<Biome> biomeRegisterable) {
        biomeRegisterable.register(ModBiomes.SPEEDRUNNERS_WASTELAND, createSpeedrunnersWasteland(biomeRegisterable));
    }

    /**
     * Creates a {@code biome key.}
     */
    private static ResourceKey<Biome> createBiomeKey(final String name) {
        return ResourceKey.create(Registries.BIOME, ofSpeedrunnerMod(name));
    }

    /**
     * Initializes this class, registering the {@code Speedrunner's Wasteland} biome.
     */
    public static void initializeBiomes() {
        SpeedrunnerMod.LOGGER.debug("Initialized Speedrunner Mod biomes.");
    }
}