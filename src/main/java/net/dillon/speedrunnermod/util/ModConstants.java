package net.dillon.speedrunnermod.util;

import net.minecraft.world.World;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isPlayingModeDoom;
import static net.dillon.speedrunnermod.option.ModOptions.isPlayingModeEasy;

/**
 * Constant variables used across the Speedrunner Mod.
 */
public class ModConstants {
    public static final int SPEEDRUNNER_WATER_COLOR = 0x85C1E9;
    public static final int SPEEDRUNNER_WATER_FOG_COLOR = 0x85C1E9;
    public static final int DOLPHIN_RANGE = 200;
    public static final int FIRE_DAMAGE_FROM_LAVA_DURATION = isPlayingModeDoom() ? 15 : 7;
    public static final int FIREBALL_FIRE_TIME /* (in seconds) */ = isPlayingModeDoom() ? 6 : 3;
    public static final int BLAZE_FIREBALL_COOLDOWN /* (in ticks) */ = isPlayingModeDoom() ? 60 : 180;
    public static final int GHAST_FIREBALL_COOLDOWN /* (in ticks) */ = isPlayingModeDoom() ? -5 : -40;
    public static final int SLIME_JUMP_TIME /* (in ticks) */ = isPlayingModeDoom() ? 20 : 100;
    public static final int PLAYER_BREATH_TIME = options().advanced.higherBreathTime.getCurrentValue() ? 8 : 4;
    public static final int SILVERFISH_CALL_FOR_HELP_DELAY /* (in ticks) */ = isPlayingModeDoom() ? 20 : 100;
    public static final int WITHER_SKELETON_WITHER_EFFECT_DURATION /* (in ticks) */ = isPlayingModeDoom() ? 200 : 60;
    public static final int ENDER_DRAGON_FIREBALL_INSTANT_DAMAGE_AMPLIFIER = isPlayingModeDoom() ? 1 : 0;
    public static final int TREES_PLAINS_COUNT = 1;
    public static final int DIAMOND_ORE_SPAWN_CHANCE = 8;
    public static final int BURIED_DIAMOND_ORE_SPAWN_CHANCE = 9;
    public static final int LARGE_DIAMOND_ORE_SPAWN_CHANCE = 5;
    public static final int LAPIS_LAZULI_ORE_SPAWN_CHANCE = 3;
    public static final int BURIED_LAPIS_LAZULI_ORE_SPAWN_CHANCE = 4;
    public static final int STRONGHOLD_MIN_Y = isPlayingModeDoom() ? -48 : 27;
    public static final int STRONGHOLD_MAX_Y = isPlayingModeDoom() ? 0 : /* also known as sea level -> */ 63;
    public static final float LAVA_DAMAGE_VALUE = isPlayingModeDoom() ? 4.0F : 2.0F;
    public static final float LAVA_BOAT_VELOCITY_MULTIPLIER = 0.95F;
    public static final float FIREBALL_DAMAGE_VALUE = isPlayingModeDoom() ? 5.0F : 1.0F;
    public static final float FAST_BOAT_VELOCITY_MULTIPLIER = 1.035F;
    public static final float SLIME_DAMAGE_MULTIPLIER = isPlayingModeDoom() ? 2.2F : 1.5F;
    public static final float VEX_DECAY_DAMAGE_VALUE = isPlayingModeDoom() ? 100.0F : 1.0F;
    public static final float PREFILLED_ENDER_EYE_CHANCE = isPlayingModeDoom() ? 0.99F : isPlayingModeEasy() ? 0.6F : 0.9F;
    public static final float ENDER_DRAGON_END_CRYSTAL_HEALING_VALUE = isPlayingModeDoom() ? 1.7F : 0.1F;
    public static final float ENDER_DRAGON_DESTROYED_END_CRYSTAL_DAMAGE_VALUE = isPlayingModeDoom() ? 3.0F : 20.0F;
    public static final float ENDER_DRAGON_DAMAGE_VALUE = isPlayingModeDoom() ? 12.0F : 3.0F;
    public static final float ENDER_DRAGON_SITTING_TIME = options().advanced.longerDragonPerchStayTime.getCurrentValue() ? isPlayingModeDoom() ? 0.18F : 0.60F : 0.25F;
    public static final float ENDER_PEARL_DAMAGE_VALUE = isPlayingModeDoom() ? 5.0F : 2.0F;
    public static final double DOLPHIN_PREDICATE_RANGE = 20.0D;
    public static final double ZOMBIFIED_PIGLIN_RUNAWAY_DISTANCE = options().advanced.decreasedZombifiedPiglinScareDistance.getCurrentValue() ? 2.0D : 6.0D;
    public static final double ENDER_DRAGON_MAX_HEALTH = isPlayingModeDoom() ? 500.0D : 100.0D;
    public static final double WITHER_MAX_HEALTH = isPlayingModeDoom() ? 150.0D : 100.0D;

    /**
     * Returns the bed block explosion power based on the mode and dimension.
     */
    public static float bedBlockExplosionPower(World world) {
        if (isPlayingModeDoom()) {
            return world.getRegistryKey() == World.END ? 15.0F : 5.0F;
        } else {
            return 5.0F;
        }
    }
}