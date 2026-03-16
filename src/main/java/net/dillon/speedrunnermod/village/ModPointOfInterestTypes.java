package net.dillon.speedrunnermod.village;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * {@code Point of Interest Types} for the speedrunner mod.
 */
public class ModPointOfInterestTypes {
    public static final ResourceKey<PoiType> RETIRED_SPEEDRUNNER_POI_KEY =
            ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, ofSpeedrunnerMod("retired_speedrunner_poi"));

    public static final PoiType RETIRED_SPEEDRUNNER_POI = PointOfInterestHelper.register(
            ofSpeedrunnerMod("retired_speedrunner_poi"), 3, 1, ModBlocks.SPEEDRUNNERS_WORKBENCH);

    /**
     * Initializes {@code point of interest types} for the speedrunner mod.
     */
    public static void initializePois() {
        SpeedrunnerMod.debug("Initialized point of interest types.");
    }
}