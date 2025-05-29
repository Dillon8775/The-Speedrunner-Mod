package net.dillon.speedrunnermod.village;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.village.VillagerProfession;

import static net.dillon.speedrunnermod.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code custom villager professions.}
 */
public class ModVillagers {
    public static final RegistryKey<VillagerProfession> RETIRED_SPEEDRUNNER = of("retired_speedrunner");

    private static RegistryKey<VillagerProfession> of(String id) {
        return RegistryKey.of(RegistryKeys.VILLAGER_PROFESSION, ofSpeedrunnerMod(id));
    }

    /**
     * Initializes the {@code Retired Speedrunner villager profession.}
     */
    public static void initializeVillagerProfessions() {}
}