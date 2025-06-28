package net.dillon.speedrunnermod.village;

import com.google.common.collect.ImmutableSet;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.village.VillagerProfession;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.debug;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;
import static net.dillon.speedrunnermod.village.ModPointOfInterestTypes.RETIRED_SPEEDRUNNER_POI_KEY;

/**
 * {@code Custom villager professions} for the speedrunner mod.
 */
public class ModVillagers {
    public static final RegistryKey<VillagerProfession> RETIRED_SPEEDRUNNER_KEY =
            RegistryKey.of(RegistryKeys.VILLAGER_PROFESSION, ofSpeedrunnerMod("retired_speedrunner"));

    /**
     * Registers all speedrunner mod {@code villager professions.}
     */
    public static void registerVillagerProfessions() {
        Registry.register(
                Registries.VILLAGER_PROFESSION,
                RETIRED_SPEEDRUNNER_KEY.getValue(),
                new VillagerProfession(
                        Text.translatable("entity.minecraft.villager.retired_speedrunner"),
                        entry -> entry.matchesKey(RETIRED_SPEEDRUNNER_POI_KEY),
                        entry -> entry.matchesKey(RETIRED_SPEEDRUNNER_POI_KEY),
                        ImmutableSet.of(),
                        ImmutableSet.of(),
                        SoundEvents.ENTITY_VILLAGER_WORK_ARMORER
                )
        );
        SpeedrunnerMod.debug("Registered villager professions.");
    }
}