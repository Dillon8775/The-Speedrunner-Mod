package net.dillon.speedrunnermod.component;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.attributes.Attribute;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod modded attribute keys.
 */
public class ModAttributeKeys {
    public static final ResourceKey<Attribute> DOOM_BLOCK_PROTECTION = create("doom_block_protection");
    public static final ResourceKey<Attribute> LAVA_INVULNERABILITY = create("lava_invulnerability");
    public static final ResourceKey<Attribute> INVENTORY_PRESERVATION = create("inventory_preservation");
    public static final ResourceKey<Attribute> ADDITIONAL_BOAT_MOVEMENT_SPEED = create("additional_boat_movement_speed");
    public static final ResourceKey<Attribute> ADDITIONAL_BOW_POWER = create("additional_bow_power");
    public static final ResourceKey<Attribute> ADDITIONAL_RANGE = create("additional_range");
    public static final ResourceKey<Attribute> ADDITIONAL_COOLDOWN = create("additional_cooldown");
    public static final ResourceKey<Attribute> ADDITIONAL_TARGET_DAMAGE = create("additional_target_damage");
    public static final ResourceKey<Attribute> ADDITIONAL_INERTIA = create("additional_inertia");

    /**
     * Returns a speedrunner mod recipe identifier.
     */
    protected static ResourceKey<Attribute> create(String path) {
        return ResourceKey.create(Registries.ATTRIBUTE, ofSpeedrunnerMod(path));
    }

    /**
     * Initializes all speedrunner mod attribute keys.
     */
    public static void initializeAttributeKeys() {
        SpeedrunnerMod.debug("Initialized attribute keys.");
    }
}