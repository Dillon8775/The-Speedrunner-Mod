package net.dillon.speedrunnermod.village;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.poi.PointOfInterestType;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * {@code Point of Interest Types} for the speedrunner mod.
 */
public class ModPointOfInterestTypes {
    public static final RegistryKey<PointOfInterestType> RETIRED_SPEEDRUNNER_POI_KEY =
            RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, ofSpeedrunnerMod("retired_speedrunner_poi"));

    public static final PointOfInterestType RETIRED_SPEEDRUNNER_POI = PointOfInterestHelper.register(
            ofSpeedrunnerMod("retired_speedrunner_poi"), 3, 1, ModBlocks.SPEEDRUNNERS_WORKBENCH);

    /**
     * Initializes {@code point of interest types} for the speedrunner mod.
     */
    public static void initializePois() {
        SpeedrunnerMod.debug("Initialized point of interest types.");
    }
}