package net.dillon.speedrunnermod.villager;

import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.npc.villager.VillagerProfession;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * {@code Custom villager professions} for the speedrunner mod.
 */
public class ModVillagers {
    public static final VillagerProfession RETIRED_SPEEDRUNNER = registerProfession("retired_speedrunner",
            new VillagerProfession(
                    Component.translatable("entity.minecraft.villager.retired_speedrunner"),
                    poiTypeHolder -> poiTypeHolder.is(ModPoiTypes.RETIRED_SPEEDRUNNER),
                    poiTypeHolder -> poiTypeHolder.is(ModPoiTypes.RETIRED_SPEEDRUNNER),
                    ImmutableSet.of(),
                    ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_ARMORER,
                    Int2ObjectMap.ofEntries(
                            Int2ObjectMap.entry(1, ModTradeSets.RETIRED_SPEEDRUNNER_LEVEL_1),
                            Int2ObjectMap.entry(2, ModTradeSets.RETIRED_SPEEDRUNNER_LEVEL_2),
                            Int2ObjectMap.entry(3, ModTradeSets.RETIRED_SPEEDRUNNER_LEVEL_3),
                            Int2ObjectMap.entry(4, ModTradeSets.RETIRED_SPEEDRUNNER_LEVEL_4),
                            Int2ObjectMap.entry(5, ModTradeSets.RETIRED_SPEEDRUNNER_LEVEL_5)
                    )
            ));

    /**
     * Registers a villager profession.
     */
    private static VillagerProfession registerProfession(String name, VillagerProfession profession) {
        return Registry.register(BuiltInRegistries.VILLAGER_PROFESSION, ofSpeedrunnerMod(name), profession);
    }

    /**
     * Registers all speedrunner mod {@code villager professions.}
     */
    public static void initializeVillagerProfessions() {
        SpeedrunnerMod.LOGGER.debug("Initialized villager professions.");
    }
}
