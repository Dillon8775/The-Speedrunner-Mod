package net.dillon.speedrunnermod.world.api;

import terrablender.api.Regions;
import terrablender.api.TerraBlenderApi;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.*;

/**
 * Using the {@code TerraBlender API,} this allows the {@code Speedrunner's Wasteland} biome to generate throughout the Minecraft world.
 */
public class ModTerraBlenderAPI implements TerraBlenderApi {

    /**
     * Registers and implements the {@code "region"} for the {@code Speedrunner's Wasteland} biome to generate.
     * <p>Note: The region will not register if the {@code custom biomes and custom biome features} option is disabled.</p>
     */
    @Override
    public void onTerraBlenderInitialized() {
        if (options().main.customBiomesAndCustomBiomeFeatures) {
            Regions.register(new ModOverworldRegion(ofSpeedrunnerMod("overworld"), 9));
            info("Registered the region for the Speedrunner's Wasteland biome.");
            info("You will now see the biome generate throughout each Minecraft world.");
        } else {
            warn("\"Custom Biomes and Custom Biome Features\" option is disabled.");
            warn("You will not see the Speedrunner's Wasteland biome generate. Re-enable to make it generate.");
        }
    }
}