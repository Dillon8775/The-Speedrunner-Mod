package net.dillon.speedrunnermod.entity;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Supplier;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All different entity types for the speedrunner mod.
 */
public class ModEntityTypes {
    public static final EntityType<BoatEntity> SPEEDRUNNER_BOAT = register("speedrunner_boat",
            EntityType.Builder.create(EntityType.getBoatFactory(() -> ModItems.SPEEDRUNNER_BOAT), SpawnGroup.MISC)
                    .dropsNothing()
                    .dimensions(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .maxTrackingRange(10));

    public static final EntityType<ChestBoatEntity> SPEEDRUNNER_CHEST_BOAT = register("speedrunner_chest_boat",
            EntityType.Builder.create(EntityType.getChestBoatFactory(() -> ModItems.SPEEDRUNNER_CHEST_BOAT), SpawnGroup.MISC)
                    .dropsNothing()
                    .dimensions(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .maxTrackingRange(10));

    public static final EntityType<BoatEntity> DEAD_SPEEDRUNNER_BOAT = register("dead_speedrunner_boat",
            EntityType.Builder.create(EntityType.getBoatFactory(() -> ModItems.DEAD_SPEEDRUNNER_BOAT), SpawnGroup.MISC)
                    .dropsNothing()
                    .dimensions(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .maxTrackingRange(10));

    public static final EntityType<ChestBoatEntity> DEAD_SPEEDRUNNER_CHEST_BOAT = register("dead_speedrunner_chest_boat",
            EntityType.Builder.create(EntityType.getChestBoatFactory(() -> ModItems.DEAD_SPEEDRUNNER_CHEST_BOAT), SpawnGroup.MISC)
                    .dropsNothing()
                    .dimensions(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .maxTrackingRange(10));

    public static final EntityType<BoatEntity> CRIMSON_BOAT = register("crimson_boat",
            EntityType.Builder.create(EntityType.getBoatFactory(() -> ModItems.CRIMSON_BOAT), SpawnGroup.MISC)
                    .dropsNothing()
                    .dimensions(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .maxTrackingRange(10));

    public static final EntityType<ChestBoatEntity> CRIMSON_CHEST_BOAT = register("crimson_chest_boat",
            EntityType.Builder.create(EntityType.getChestBoatFactory(() -> ModItems.CRIMSON_CHEST_BOAT), SpawnGroup.MISC)
                    .dropsNothing()
                    .dimensions(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .maxTrackingRange(10));

    public static final EntityType<BoatEntity> WARPED_BOAT = register("warped_boat",
            EntityType.Builder.create(EntityType.getBoatFactory(() -> ModItems.WARPED_BOAT), SpawnGroup.MISC)
                    .dropsNothing()
                    .dimensions(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .maxTrackingRange(10));

    public static final EntityType<ChestBoatEntity> WARPED_CHEST_BOAT = register("warped_chest_boat",
            EntityType.Builder.create(EntityType.getChestBoatFactory(() -> ModItems.WARPED_CHEST_BOAT), SpawnGroup.MISC)
                    .dropsNothing()
                    .dimensions(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .maxTrackingRange(10));

    /**
     * Helper method for registering boat types.
     */
    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        return register(keyOf(id), type);
    }

    /**
     * Helper method for registering boat types.
     */
    private static <T extends Entity> EntityType<T> register(RegistryKey<EntityType<?>> key, EntityType.Builder<T> type) {
        return Registry.register(Registries.ENTITY_TYPE, key, type.build(key));
    }

    /**
     * Returns a registry key with the speedrunner mod namespace.
     */
    private static RegistryKey<EntityType<?>> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.ENTITY_TYPE, ofSpeedrunnerMod(id));
    }

    /**
     * Determines {@code "fireproof"} boats.
     */
    public static boolean isFireproofBoat(Supplier<Item> itemSupplier) {
        return itemSupplier.get().getDefaultStack().isIn(ModItemTags.FIREPROOF_BOATS) || itemSupplier.get().getDefaultStack().isIn(ModItemTags.FIREPROOF_CHEST_BOATS);
    }

    /**
     * <p>Determines {@code "faster"} boats, boats that ride slightly faster than normal boats.
     */
    public static boolean isFastBoat(Supplier<Item> itemSupplier) {
        return itemSupplier.get().getDefaultStack().isIn(ModItemTags.FASTER_BOATS) || itemSupplier.get().getDefaultStack().isIn(ModItemTags.FASTER_CHEST_BOATS);
    }

    /**
     * Initializes all {@code Speedrunner Mod boats.}
     */
    public static void initializeEntityTypes() {
    }
}