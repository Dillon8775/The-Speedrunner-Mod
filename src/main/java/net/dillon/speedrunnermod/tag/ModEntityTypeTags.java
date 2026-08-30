package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

import static net.dillon.dillonlib.factory.Factories.createEntityTypeTag;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code entity type tags.}
 */
public class ModEntityTypeTags {
    public static final TagKey<EntityType<?>> BLACKLISTED_WITHER_TARGET_MOBS = createEntityTypeTag(ofSpeedrunnerMod("blacklisted_wither_target_mobs"));
    public static final TagKey<EntityType<?>> BLACKLISTED_MINION_CALL_MOBS = createEntityTypeTag(ofSpeedrunnerMod("blacklisted_minion_call_mobs"));
    public static final TagKey<EntityType<?>> BLACKLISTED_ENDER_DRAGON_KILL_MOBS = createEntityTypeTag(ofSpeedrunnerMod("blacklisted_ender_dragon_kill_mobs"));
    public static final TagKey<EntityType<?>> SPARE_ME_ADVANCEMENT_MOBS = createEntityTypeTag(ofSpeedrunnerMod("spare_me_advancement_mobs"));
    public static final TagKey<EntityType<?>> SPEEDRUNNER_IMPERATIVE_MOBS = createEntityTypeTag(ofSpeedrunnerMod("speedrunner_imperative_mobs"));
    public static final TagKey<EntityType<?>> GOLIATH_IMMUNE_MOBS = createEntityTypeTag(ofSpeedrunnerMod("goliath_immune_mobs"));
    public static final TagKey<EntityType<?>> WITHERED_EFFECTED_MOBS = createEntityTypeTag(ofSpeedrunnerMod("withered_effected_mobs"));

    /**
     * Initializes all Speedrunner Mod {@code entity type tags.}
     */
    public static void initializeEntityTypeTags() {
        SpeedrunnerMod.LOGGER.debug("Initialized entity type tags.");
    }
}