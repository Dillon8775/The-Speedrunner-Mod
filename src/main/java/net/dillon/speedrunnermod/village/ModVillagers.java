package net.dillon.speedrunnermod.village;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

import static net.dillon.speedrunnermod.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code custom villager professions.}
 */
public class ModVillagers {
    public static final RegistryKey<VillagerProfession> RETIRED_SPEEDRUNNER = ofProfession("retired_speedrunner");

    private static RegistryKey<VillagerProfession> ofProfession(String id) {
        return RegistryKey.of(RegistryKeys.VILLAGER_PROFESSION, ofSpeedrunnerMod(id));
    }

    /**
     * Initializes the {@code Retired Speedrunner villager profession.}
     */
    public static void initializeVillagerProfessionsAndRegisterPois() {
    }
}