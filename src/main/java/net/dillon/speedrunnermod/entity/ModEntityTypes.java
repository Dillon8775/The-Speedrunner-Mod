package net.dillon.speedrunnermod.entity;

import net.dillon.dillonlib.factory.Factories;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All different entity types for the speedrunner mod.
 */
public class ModEntityTypes {
    public static final EntityType<Boat> SPEEDRUNNER_BOAT = Factories.registerBoatFactory(
            ofSpeedrunnerMod("speedrunner_boat"),
            () -> ModItems.SPEEDRUNNER_BOAT,
            false
    );

    public static final EntityType<Boat> FIREPROOF_SPEEDRUNNER_BOAT = Factories.registerBoatFactory(
            ofSpeedrunnerMod("fireproof_speedrunner_boat"),
            () -> ModItems.FIREPROOF_SPEEDRUNNER_BOAT,
            false
    );

    public static final EntityType<ChestBoat> SPEEDRUNNER_CHEST_BOAT = Factories.registerBoatFactory(
            ofSpeedrunnerMod("speedrunner_chest_boat"),
            () -> ModItems.SPEEDRUNNER_CHEST_BOAT,
            true
    );

    public static final EntityType<ChestBoat> FIREPROOF_SPEEDRUNNER_CHEST_BOAT = Factories.registerBoatFactory(
            ofSpeedrunnerMod("fireproof_speedrunner_chest_boat"),
            () -> ModItems.FIREPROOF_SPEEDRUNNER_CHEST_BOAT,
            true
    );

    public static final EntityType<Boat> DEAD_SPEEDRUNNER_BOAT = Factories.registerBoatFactory(
            ofSpeedrunnerMod("dead_speedrunner_boat"),
            () -> ModItems.DEAD_SPEEDRUNNER_BOAT,
            false
    );

    public static final EntityType<ChestBoat> DEAD_SPEEDRUNNER_CHEST_BOAT = Factories.registerBoatFactory(
            ofSpeedrunnerMod("dead_speedrunner_chest_boat"),
            () -> ModItems.DEAD_SPEEDRUNNER_CHEST_BOAT,
            true
    );

    public static final EntityType<Boat> CRIMSON_BOAT = Factories.registerBoatFactory(
            ofSpeedrunnerMod("crimson_boat"),
            () -> ModItems.CRIMSON_BOAT,
            false
    );

    public static final EntityType<Boat> FIREPROOF_CRIMSON_BOAT = Factories.registerBoatFactory(
            ofSpeedrunnerMod("fireproof_crimson_boat"),
            () -> ModItems.FIREPROOF_CRIMSON_BOAT,
            false
    );

    public static final EntityType<ChestBoat> CRIMSON_CHEST_BOAT = Factories.registerBoatFactory(
            ofSpeedrunnerMod("crimson_chest_boat"),
            () -> ModItems.CRIMSON_CHEST_BOAT,
            true
    );

    public static final EntityType<ChestBoat> FIREPROOF_CRIMSON_CHEST_BOAT = Factories.registerBoatFactory(
            ofSpeedrunnerMod("fireproof_crimson_chest_boat"),
            () -> ModItems.FIREPROOF_CRIMSON_CHEST_BOAT,
            true
    );

    public static final EntityType<Boat> WARPED_BOAT = Factories.registerBoatFactory(
            ofSpeedrunnerMod("warped_boat"),
            () -> ModItems.WARPED_BOAT,
            false
    );

    public static final EntityType<Boat> FIREPROOF_WARPED_BOAT = Factories.registerBoatFactory(
            ofSpeedrunnerMod("fireproof_warped_boat"),
            () -> ModItems.FIREPROOF_WARPED_BOAT,
            false
    );

    public static final EntityType<ChestBoat> WARPED_CHEST_BOAT = Factories.registerBoatFactory(
            ofSpeedrunnerMod("warped_chest_boat"),
            () -> ModItems.WARPED_CHEST_BOAT,
            true
    );

    public static final EntityType<ChestBoat> FIREPROOF_WARPED_CHEST_BOAT = Factories.registerBoatFactory(
            ofSpeedrunnerMod("fireproof_warped_chest_boat"),
            () -> ModItems.FIREPROOF_WARPED_CHEST_BOAT,
            true
    );

    /**
     * Initializes all {@code Speedrunner Mod entity types.}
     */
    public static void initializeEntityTypes() {
        SpeedrunnerMod.LOGGER.debug("Initialized entity types.");
    }
}