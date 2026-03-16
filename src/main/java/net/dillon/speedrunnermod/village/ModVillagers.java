package net.dillon.speedrunnermod.village;

import com.google.common.collect.ImmutableSet;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.npc.villager.VillagerProfession;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;
import static net.dillon.speedrunnermod.village.ModPointOfInterestTypes.RETIRED_SPEEDRUNNER_POI_KEY;

/**
 * {@code Custom villager professions} for the speedrunner mod.
 */
public class ModVillagers {
    public static final ResourceKey<VillagerProfession> RETIRED_SPEEDRUNNER_KEY =
            ResourceKey.create(Registries.VILLAGER_PROFESSION, ofSpeedrunnerMod("retired_speedrunner"));

    /**
     * Registers all speedrunner mod {@code villager professions.}
     */
    public static void registerVillagerProfessions() {
        Registry.register(
                BuiltInRegistries.VILLAGER_PROFESSION,
                RETIRED_SPEEDRUNNER_KEY.identifier(),
                new VillagerProfession(
                        Component.translatable("entity.minecraft.villager.retired_speedrunner"),
                        entry -> entry.is(RETIRED_SPEEDRUNNER_POI_KEY),
                        entry -> entry.is(RETIRED_SPEEDRUNNER_POI_KEY),
                        ImmutableSet.of(),
                        ImmutableSet.of(),
                        SoundEvents.VILLAGER_WORK_ARMORER
                )
        );
        SpeedrunnerMod.debug("Registered villager professions.");
    }
}