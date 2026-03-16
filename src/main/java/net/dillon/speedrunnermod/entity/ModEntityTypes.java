package net.dillon.speedrunnermod.entity;

import net.dillon.speedrunnermod.item.FireproofBoat;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All different entity types for the speedrunner mod.
 */
public class ModEntityTypes {
    public static final EntityType<Boat> SPEEDRUNNER_BOAT = register("speedrunner_boat",
            EntityType.Builder.of(EntityType.boatFactory(() -> ModItems.SPEEDRUNNER_BOAT), MobCategory.MISC)
                    .noLootTable()
                    .sized(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .clientTrackingRange(10));

    public static final EntityType<Boat> FIREPROOF_SPEEDRUNNER_BOAT = register("fireproof_speedrunner_boat",
            EntityType.Builder.of(EntityType.boatFactory(() -> ModItems.FIREPROOF_SPEEDRUNNER_BOAT), MobCategory.MISC)
                    .noLootTable()
                    .sized(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .clientTrackingRange(10));

    public static final EntityType<ChestBoat> SPEEDRUNNER_CHEST_BOAT = register("speedrunner_chest_boat",
            EntityType.Builder.of(EntityType.chestBoatFactory(() -> ModItems.SPEEDRUNNER_CHEST_BOAT), MobCategory.MISC)
                    .noLootTable()
                    .sized(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .clientTrackingRange(10));

    public static final EntityType<ChestBoat> FIREPROOF_SPEEDRUNNER_CHEST_BOAT = register("fireproof_speedrunner_chest_boat",
            EntityType.Builder.of(EntityType.chestBoatFactory(() -> ModItems.FIREPROOF_SPEEDRUNNER_CHEST_BOAT), MobCategory.MISC)
                    .noLootTable()
                    .sized(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .clientTrackingRange(10));

    public static final EntityType<Boat> DEAD_SPEEDRUNNER_BOAT = register("dead_speedrunner_boat",
            EntityType.Builder.of(EntityType.boatFactory(() -> ModItems.DEAD_SPEEDRUNNER_BOAT), MobCategory.MISC)
                    .noLootTable()
                    .sized(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .clientTrackingRange(10));

    public static final EntityType<ChestBoat> DEAD_SPEEDRUNNER_CHEST_BOAT = register("dead_speedrunner_chest_boat",
            EntityType.Builder.of(EntityType.chestBoatFactory(() -> ModItems.DEAD_SPEEDRUNNER_CHEST_BOAT), MobCategory.MISC)
                    .noLootTable()
                    .sized(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .clientTrackingRange(10));

    public static final EntityType<Boat> CRIMSON_BOAT = register("crimson_boat",
            EntityType.Builder.of(EntityType.boatFactory(() -> ModItems.CRIMSON_BOAT), MobCategory.MISC)
                    .noLootTable()
                    .sized(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .clientTrackingRange(10));

    public static final EntityType<Boat> FIREPROOF_CRIMSON_BOAT = register("fireproof_crimson_boat",
            EntityType.Builder.of(EntityType.boatFactory(() -> ModItems.FIREPROOF_CRIMSON_BOAT), MobCategory.MISC)
                    .noLootTable()
                    .sized(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .clientTrackingRange(10));

    public static final EntityType<ChestBoat> CRIMSON_CHEST_BOAT = register("crimson_chest_boat",
            EntityType.Builder.of(EntityType.chestBoatFactory(() -> ModItems.CRIMSON_CHEST_BOAT), MobCategory.MISC)
                    .noLootTable()
                    .sized(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .clientTrackingRange(10));

    public static final EntityType<ChestBoat> FIREPROOF_CRIMSON_CHEST_BOAT = register("fireproof_crimson_chest_boat",
            EntityType.Builder.of(EntityType.chestBoatFactory(() -> ModItems.FIREPROOF_CRIMSON_CHEST_BOAT), MobCategory.MISC)
                    .noLootTable()
                    .sized(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .clientTrackingRange(10));

    public static final EntityType<Boat> WARPED_BOAT = register("warped_boat",
            EntityType.Builder.of(EntityType.boatFactory(() -> ModItems.WARPED_BOAT), MobCategory.MISC)
                    .noLootTable()
                    .sized(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .clientTrackingRange(10));

    public static final EntityType<Boat> FIREPROOF_WARPED_BOAT = register("fireproof_warped_boat",
            EntityType.Builder.of(EntityType.boatFactory(() -> ModItems.FIREPROOF_WARPED_BOAT), MobCategory.MISC)
                    .noLootTable()
                    .sized(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .clientTrackingRange(10));

    public static final EntityType<ChestBoat> WARPED_CHEST_BOAT = register("warped_chest_boat",
            EntityType.Builder.of(EntityType.chestBoatFactory(() -> ModItems.WARPED_CHEST_BOAT), MobCategory.MISC)
                    .noLootTable()
                    .sized(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .clientTrackingRange(10));

    public static final EntityType<ChestBoat> FIREPROOF_WARPED_CHEST_BOAT = register("fireproof_warped_chest_boat",
            EntityType.Builder.of(EntityType.chestBoatFactory(() -> ModItems.FIREPROOF_WARPED_CHEST_BOAT), MobCategory.MISC)
                    .noLootTable()
                    .sized(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .clientTrackingRange(10));

    /**
     * Helper method for registering boat types.
     */
    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        return register(keyOf(id), type);
    }

    /**
     * Helper method for registering boat types.
     */
    private static <T extends Entity> EntityType<T> register(ResourceKey<EntityType<?>> key, EntityType.Builder<T> type) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, type.build(key));
    }

    /**
     * Returns a registry key with the speedrunner mod namespace.
     */
    private static ResourceKey<EntityType<?>> keyOf(String id) {
        return ResourceKey.create(Registries.ENTITY_TYPE, ofSpeedrunnerMod(id));
    }

    /**
     * @return {@code fireproof boats.}
     */
    public static boolean isFireproofBoat(AbstractBoat boat) {
        return ((FireproofBoat)boat).isFireproof() && (boat.dropItem.get().getDefaultInstance().is(ModItemTags.FIREPROOF_BOATS) || boat.dropItem.get().getDefaultInstance().is(ModItemTags.FIREPROOF_CHEST_BOATS));
    }

    /**
     * @return {@code "faster"} boats, which ride slightly faster than normal boats.
     */
    public static boolean isFastBoat(Supplier<Item> itemSupplier) {
        return itemSupplier.get().getDefaultInstance().is(ModItemTags.FASTER_BOATS) || itemSupplier.get().getDefaultInstance().is(ModItemTags.FASTER_CHEST_BOATS);
    }

    /**
     * Initializes all {@code Speedrunner Mod entity types.}
     */
    public static void initializeEntityTypes() {
        SpeedrunnerMod.debug("Initialized entity types.");
    }
}