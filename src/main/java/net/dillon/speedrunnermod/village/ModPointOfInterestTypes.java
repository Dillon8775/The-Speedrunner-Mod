package net.dillon.speedrunnermod.village;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.poi.PointOfInterestType;

import static net.dillon.speedrunnermod.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Point of interest types for the speedrunner mod.
 */
public class ModPointOfInterestTypes {
    public static final RegistryKey<PointOfInterestType> RETIRED_SPEEDRUNNER_POI = PointOfInterestHelper.register(ofSpeedrunnerMod("retired_speedrunner_poi"), 1, 1, ModBlocks.SPEEDRUNNERS_WORKBENCH);
}