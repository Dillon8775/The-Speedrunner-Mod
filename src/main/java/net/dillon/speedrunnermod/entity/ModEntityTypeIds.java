package net.dillon.speedrunnermod.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;

import static net.dillon.speedrunnermod.main.CommonMain.ofSpeedrunnerMod;

/**
 * All speedrunner mod entity type ids.
 */
public class ModEntityTypeIds {
    public static final ResourceKey<EntityType<?>> SPEEDRUNNER_BOAT = createEntityTypeKey("speedrunner_boat");
    public static final ResourceKey<EntityType<?>> SPEEDRUNNER_CHEST_BOAT = createEntityTypeKey("speedrunner_chest_boat");
    public static final ResourceKey<EntityType<?>> DEAD_SPEEDRUNNER_BOAT = createEntityTypeKey("dead_speedrunner_boat");
    public static final ResourceKey<EntityType<?>> DEAD_SPEEDRUNNER_CHEST_BOAT = createEntityTypeKey("dead_speedrunner_chest_boat");
    public static final ResourceKey<EntityType<?>> CRIMSON_BOAT = createEntityTypeKey("crimson_boat");
    public static final ResourceKey<EntityType<?>> CRIMSON_CHEST_BOAT = createEntityTypeKey("crimson_chest_boat");
    public static final ResourceKey<EntityType<?>> WARPED_BOAT = createEntityTypeKey("warped_boat");
    public static final ResourceKey<EntityType<?>> WARPED_CHEST_BOAT = createEntityTypeKey("warped_chest_boat");

    private static ResourceKey<EntityType<?>> createEntityTypeKey(final String name) {
        return ResourceKey.create(Registries.ENTITY_TYPE, ofSpeedrunnerMod(name));
    }
}