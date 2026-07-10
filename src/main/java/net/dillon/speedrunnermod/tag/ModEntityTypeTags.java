package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code entity type tags.}
 */
public class ModEntityTypeTags {
    public static final TagKey<EntityType<?>> BLACKLISTED_WITHER_TARGET_MOBS = createEntityTypeTag("blacklisted_wither_target_mobs");
    public static final TagKey<EntityType<?>> BLACKLISTED_MINION_CALL_MOBS = createEntityTypeTag("blacklisted_minion_call_mobs");
    public static final TagKey<EntityType<?>> BLACKLISTED_ENDER_DRAGON_KILL_MOBS = createEntityTypeTag("blacklisted_ender_dragon_kill_mobs");
    public static final TagKey<EntityType<?>> SPARE_ME_ADVANCEMENT_MOBS = createEntityTypeTag("spare_me_advancement_mobs");
    public static final TagKey<EntityType<?>> SPEEDRUNNER_IMPERATIVE_MOBS = createEntityTypeTag("speedrunner_imperative_mobs");
    public static final TagKey<EntityType<?>> GOLIATH_IMMUNE_MOBS = createEntityTypeTag("goliath_immune_mobs");
    public static final TagKey<EntityType<?>> WITHERED_EFFECTED_MOBS = createEntityTypeTag("withered_effected_mobs");

    /**
     * Registers a {@code entity type tag.}
     */
    private static TagKey<EntityType<?>> createEntityTypeTag(String path) {
        return TagKey.create(Registries.ENTITY_TYPE, ofSpeedrunnerMod(path));
    }

    /**
     * Initializes all Speedrunner Mod {@code entity type tags.}
     */
    public static void initializeEntityTypeTags() {
        SpeedrunnerMod.debug("Initialized entity type tags.");
    }
}