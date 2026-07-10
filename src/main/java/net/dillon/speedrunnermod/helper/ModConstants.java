package net.dillon.speedrunnermod.helper;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.util.RandomChance;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.level.Level;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;
import static net.dillon.speedrunnermod.option.ModOptions.isEasyMode;

/**
 * Constant variables and values for the Speedrunner Mod.
 */
public class ModConstants {
    public static final String MOD_VERSION = FabricLoader.getInstance()
            .getModContainer("speedrunnermod")
            .map(c -> c.getMetadata().getVersion().getFriendlyString().split("\\+", 2)[0])
            .orElse("unknown");
    public static final String MC_VERSION = FabricLoader.getInstance().getRawGameVersion();
    public static final String THE_SPEEDRUNNER_MOD_STRING = "The Speedrunner Mod";
    public static final String OPTIONS_ERROR_MESSAGE = "Found error with speedrunner mod settings, launching in safe mode.";
    public static final String OPTIONS_WARNING_MESSAGE = "Found an unusual value in the speedrunner mod settings.";
    public static boolean safeBoot;

    public static final int DEFAULT_DOOM_ZOMBIE_FIREBALL_CHARGE_SPEED = 40;
    public static final int DEFAULT_ZOMBIE_FIREBALL_CHARGE_SPEED = 60;
    public static final int DEFAULT_MINION_FIREBALL_CHARGE_SPEED = 80;

    /**
     * Initializes all constant variables.
     */
    public static void initConstants() {
        SpeedrunnerMod.debug("Initialized mod constants.");
    }

    /**
     * @return the increased experience amount from a XP bottle.
     */
    public static int getIncreasedExperienceAmount() {
        return isDoomMode() ? 48 : 36;
    }

    /**
     * @return the bed block explosion power based.
     */
    public static float getBedBlockExplosionPower(Level level) {
        if (isDoomMode()) {
            return level.dimension() == Level.END ? 15.0F : 5.0F;
        } else {
            return 5.0F;
        }
    }

    /**
     * @return how long an entity should be set on fire for from lava (in seconds).
     */
    public static int getFireDamageFromLavaDuration() {
        return isDoomMode() ? 15 : 7;
    }

    /**
     * @return how long an entity should be set on fire for from a fireball (in seconds).
     */
    public static int getSmallFireballDamageTime() {
        return isDoomMode() ? 6 : 3;
    }

    /**
     * @return the blaze's fireball shooting cooldown (in ticks).
     */
    public static int getBlazeFireballCooldown() {
        return isDoomMode() ? 60 : 180;
    }

    /**
     * @return the ghast's fireball shooting cooldown (in ticks).
     * <p>I don't know why, but these values have to be negative.</p>
     */
    public static int getGhastFireballCooldown() {
        return isDoomMode() ? -5 : -40;
    }

    /**
     * @return how long it takes for a slime to make it's next jump (in ticks).
     */
    public static int getSlimeJumpTime() {
        return isDoomMode() ? 20 : 100;
    }

    /**
     * @return how long it takes for a oldPlayer to lose an air bubble (in seconds).
     */
    public static int getPlayerBreathTime() {
        return options().advanced.increasedOxygen.getCurrentValue() ? 6 : 4;
    }

    /**
     * @return how long it takes for a silverfish to call for more backup (in ticks).
     */
    public static int getSilverfishCallForHelpDelay() {
        return isDoomMode() ? 20 : 100;
    }

    /**
     * @return how long a wither skeleton inflicts the wither effect for (in ticks).
     */
    public static int getWitherSkeletonWitherEffectDuration() {
        return isDoomMode() ? 200 : 60;
    }

    /**
     * @return the minimum y-level that a stronghold can generate at.
     */
    public static int getStrongholdMinY() {
        return isDoomMode() ? -48 : 27;
    }

    /**
     * @return the maximum y-level that a stronghold can generate at.
     */
    public static int getStrongholdMaxY() {
        int seaLevel = 63;
        return isDoomMode() ? 0 : seaLevel;
    }

    /**
     * @return the percentChance of an ender eye being pre-filled in an end portal frame block.
     * <p>The higher the value, the less the percentChance.</p>
     */
    public static float getPrefilledEnderEyeChance() {
        return isDoomMode() ? 0.99F : isEasyMode() ? 0.6F : 0.9F;
    }

    /**
     * @return how much damage lava does to an entity (each 0.5 = half a heart).
     */
    public static float getLavaDamageValue() {
        return isDoomMode() ? 4.0F : 2.0F;
    }

    /**
     * @return how much damage a fireball does when hitting an entity (each 0.5 = half a heart).
     */
    public static float getSmallFireballDamageValue() {
        return isDoomMode() ? RandomChance.intInclusive(3, 5) : 1.0F;
    }

    /**
     * @return how much damage a slime does when attacking.
     */
    public static float getSlimeDamageMultiplier() {
        return isDoomMode() ? 2.2F : 1.5F;
    }

    /**
     * @return how much damage a vex takes each time it takes damage from decaying.
     */
    public static float getVexDecayDamageValue() {
        return isDoomMode() ? 100.0F : 1.0F;
    }

    /**
     * @return how much damage the ender dragon does.
     */
    public static float getEnderDragonDamageValue() {
        return isDoomMode() ? 12.0F : 3.0F;
    }

    /**
     * @return the maximum health for the ender dragon.
     */
    public static double getEnderDragonMaxHealth() {
        return isDoomMode() ? 500.0D : 100.0D;
    }

    /**
     * @return the follow range for the ender dragon.
     */
    public static double getEnderDragonFollowRange() {
        return isDoomMode() ? 64.0D : 16.0D;
    }

    /**
     * @return the amplifier for the instant damage effect upon the ender dragon shooting a fireball from it's mouth.
     */
    public static int getEnderDragonFireballInstantDamageAmplifier() {
        return isDoomMode() ? 1 : 0;
    }

    /**
     * @return how much the ender dragon heals when connecting to an end crystal.
     */
    public static float getEnderDragonEndCrystalHealingValue() {
        return isDoomMode() ? 1.7F : 0.1F;
    }

    /**
     * @return how much damage the ender dragon takes when connected to an end crystal and that end crystal is destroyed.
     */
    public static float getEnderDragonDestroyedEndCrystalDamageValue() {
        return isDoomMode() ? 3.0F : 20.0F;
    }

    /**
     * @return how long the ender dragon should stay sitting.
     */
    public static float getEnderDragonSittingTime() {
        return options().advanced.longerDragonPerchStayTime.getCurrentValue() ? 0.60F : 0.25F;
    }

    /**
     * @return the damage that an ender pearl does to the thrower when landing (each 0.5 = half a heart).
     */
    public static float getEnderPearlDamageValue() {
        return isDoomMode() ? 5.0F : 2.0F;
    }

    /**
     * @return the maximum health for the wither.
     */
    public static double getWitherMaxHealth() {
        return isDoomMode() ? 250.0D : 100.0D;
    }

    /**
     * @return the distance in blocks that a zombified piglin must be in from a piglin in order to get scared and run away.
     */
    public static double getZombifiedPiglinRunawayDistance() {
        return options().advanced.decreasedZombifiedPiglinScareDistance.getCurrentValue() ? 2.0D : 6.0D;
    }
}