package net.dillon.speedrunnermod.option;

import net.dillon.dillonlib.util.Arithmetics;
import net.dillon.speedrunnermod.option.eum.Mode;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;

/**
 * All Speedrunner Mod {@code options.}
 */
public class ModCommonOptions {
    public static final ModOptionsHandler INSTANCE = new ModOptionsHandler();
    private final General general = new General();
    private final Worldgen worldgen = new Worldgen();
    private final Accessibility accessibility = new Accessibility();
    private final StructureConfigs structureConfigs = new StructureConfigs();
    private final Mixins mixins = new Mixins();

    public General general() {
        return this.general;
    }

    public Worldgen worldgen() {
        return this.worldgen;
    }

    public Accessibility accessibility() {
        return accessibility;
    }

    public StructureConfigs structureConfigs() {
        return this.structureConfigs;
    }

    public Mixins mixins() {
        return this.mixins;
    }

    public static class ModOptionsHandler extends ModBaseOptionsHandler<ModCommonOptions> {

        protected ModOptionsHandler() {
            super("speedrunnermod.json");
        }

        @Override
        protected ModCommonOptions createDefault() {
            return new ModCommonOptions();
        }

        @Override
        protected Class<ModCommonOptions> getConfigClass() {
            return ModCommonOptions.class;
        }
    }

    /**
     * All {@code General Speedrunner Mod options.}
     */
    public static class General {

        /**
         * Determines the mode of the mod. The mode determines what features are added.
         */
        public Mode mode = Mode.EASY;

        /**
         * Allows certain blocks to be broken faster.
         */
        public boolean fasterBlockBreaking = true;

        /**
         * The multiplier for how faster blocks can be broken.
         */
        public int blockBreakingMultiplier = 1;

        /**
         * Grants the player with a pre-equipped unbreakable elytra and a stack of flight duration 3 firework rockets.
         */
        public boolean iCarusMode = false;

        /**
         * Grants the player with an ender pearl that does defaultValue do damage nor get consumed upon use.
         */
        public boolean infiniPearlMode = false;

        /**
         *  Determines the amount of time (in seconds) that it takes for the ender dragon to automatically perch upon entering the end.
         *  <p>Note: {@code 8 = OFF, 9 = Instant.}</p>
         */
        public int dragonPerchTime = 8;

        /**
         * Instantly kills a ghast when they shoot a fireball.
         */
        public boolean killGhastOnFireball = false;

        /**
         * Improves most vanilla food items to restore more hunger bars and give more saturation.
         */
        public boolean betterFoods = true;

        /**
         * Enables/disables fall damage.
         */
        public boolean fallDamage = true;

        /**
         * Enables/disables "kinetic" damage (damage taken when flying into walls with an elytra).
         */
        public boolean kineticDamage = true;

        /**
         * Allows the player to breath for a longer period of time while underwater, and also allows the player to regain oxygen when coming out of water blocks.
         */
        public boolean increasedOxygen = true;

        /**
         * Allows fireballs to be thrown.
         */
        public boolean throwableFireballs = true;

        /**
         * Determines the explosion power for fireballs when thrown with a fire charge.
         */
        public int fireballExplosionPower = 1;

        /**
         * Removes the "too expensive" feature from anvils, and also lowers the maximum cost for block use.
         */
        public boolean betterAnvil = true;

        /**
         * Sets the maximum cost that is allowed when using an anvil.
         * <p>If the cost goes above this value, the cost will instead be this value.</p>
         */
        public int anvilCostLimit = 10;

        /**
         * Allows the combination of two maximum level enchanted items to go above the enchantment level cap.
         */
        public boolean higherEnchantmentLevels = true;

        /**
         * Allows the player to right-click on an ore block and remove the silk touch enchantment from their handheld item.
         */
        public boolean rightClickToRemoveSilkTouch = true;

        /**
         * Increases the rate at which mobs spawn from spawner blocks.
         */
        public boolean fasterSpawners = true;

        /**
         * Makes everything smelt faster.
         */
        public boolean fasterSmelting = true;

        /**
         * Makes everything brew faster.
         */
        public boolean fasterBrewing = true;

        /**
         * Sends the players coordinates to chat upon death, and displays them on the death screen.
         */
        public boolean showDeathCords = true;
    }

    /**
     * All {@code WorldGen Speedrunner Mod options.}
     */
    public static class Worldgen {

        /**
         * Determines how frequently Minecraft structures generate throughout the world.
         */
        public boolean makeStructuresMoreCommon = true;

        /**
         * Allows certain ores to generate more commonly.
         */
        public boolean commonOres = true;

        /**
         * Allows certain biomes, such as plains, deserts, savannas, etc. to generate more commonly.
         */
        public boolean betterBiomes = true;

        /**
         * Allows the Speedrunner's Wasteland biome to generate.
         */
        public boolean generateSpeedrunnersWasteland = true;

        /**
         * Allow all types/variants of speedrunner wood to generate across the world. This includes the different variants of speedrunner trees, dead speedrunner trees, and dead speedrunner bushes.
         */
        public boolean generateSpeedrunnerWood = true;

        /**
         * Allows plain trees to generate more commonly in plains biomes.
         */
        public boolean commonPlainTrees = true;

        /**
         * Allows nether portals to be built in the end.
         */
        public boolean globalNetherPortals = true;

        /**
         * Allows water to be placed in the nether.
         */
        public boolean netherWater = true;

        /**
         * Sets the delay when entering/exiting the nether via a nether portal block.
         */
        public int netherPortalDelay = 2;

        /**
         * Allows arrows to blow up beds.
         */
        public boolean arrowsDestroyBeds = true;
    }

    /**
     * All {@code Advanced Speedrunner Mod options.}
     */
    public static class Accessibility {

        /**
         * Allows strongholds to generate differently, or smaller.
         */
        public boolean modifiedStrongholdGeneration = true;

        /**
         * Allows strongholds to generate at higher/lower Y-levels, depending on if doom mode is enabled or defaultValue.
         */
        public boolean modifiedStrongholdYGeneration = true;

        /**
         * Allows nether fortresses to generate differently, or smaller, along with more than two blaze spawners per fortress.
         */
        public boolean modifiedNetherFortressGeneration = true;

        /**
         * In vanilla Minecraft, the ender dragon will fly away after perching when it takes so much damage. However, this option extends that damage amount, to allow the dragon to stay perched for a longer period of time, even after taking a large amount of damage.
         */
        public boolean longerDragonPerchStayTime = true;

        /**
         * Piglins get scared of zombified piglin if they are within 6 blocks of the zombified piglin. This option decreases that distance to 2 blocks.
         */
        public boolean decreasedZombifiedPiglinScareDistance = true;

        /**
         * Determines how long it takes (in ticks) for an eye of ender to break after throwing it.
         */
        public int enderEyeBreakingCooldown = 4;

        /**
         * Determines the total amount of piglin that can teleport to the player per time using the piglin awakener item (Default = 10).
         */
        public int piglinAwakenerPiglinCount = 10;

        /**
         * Determines if -- while the player is looking at a block, if they need to be shifting to throw a fireball. Disable this if you want to do things like fireball jump.
         */
        public boolean shiftToThrowFireball = true;

        /**
         * Makes the ender dragon kill all nearby hostile entities upon dying, excluding enderman.
         */
        public boolean dragonKillsNearbyHostileEntities = true;

        /**
         * On doom mode, the ender dragon cannot be killed by any means if Goliath and the Wither are still alive in the end.
         */
        public boolean dragonImmunityFromGoliathAndWither = true;
    }

    /**
     * Stores all structure spawn rates for certain structures.
     */
    public static class StructureConfigs {

        public StructureConfig ancientCity = new StructureConfig(10, 6);
        public StructureConfig desertPyramid = new StructureConfig(10, 8);
        public StructureConfig endCity = new StructureConfig(7, 6);
        public StructureConfig igloo = new StructureConfig(9, 6);
        public StructureConfig junglePyramid = new StructureConfig(10, 8);
        public MineshaftConfig mineshaft = new MineshaftConfig(14.0F);
        public StructureConfig netherComplexes = new StructureConfig(10, 8);
        public StructureConfig oceanRuin = new StructureConfig(10, 5);
        public StructureConfig pillagerOutpost = new StructureConfig(10, 8);
        public StructureConfig ruinedPortal = new StructureConfig(9, 8);
        public StructureConfig shipwreck = new StructureConfig(10, 8);
        public StructureConfig swampHut = new StructureConfig(12, 6);
        public StructureConfig trailRuin = new StructureConfig(11, 7);
        public StructureConfig trialChamber  = new StructureConfig(12, 8);
        public StructureConfig village = new StructureConfig(16, 9);
        public StructureConfig woodlandMansion = new StructureConfig(16, 10);
        public StrongholdConfig stronghold = new StrongholdConfig(128, 4, 3, 3, 2);
    }

    /**
     * All {@code Mixin} control options.
     */
    public static class Mixins {

        /**
         * Applies the end gateway block entity mixin into the game.
         * <p>Disable this if you do defaultValue want doom stone to generate throughout the end when doom mode is enabled, or if another mod is trying to generate other blocks.</p>
         */
        public boolean theEndGatewayBlockEntityMixin = true;

        /**
         * Applies the item stack mixin into the game
         * <p>Disable this if you do defaultValue want modded attributes to be grouped correctly.</p>
         */
        public boolean itemStackMixin = true;
    }

    /**
     * @return {@code true} if the {@code Dragon Perch Time} option is {@code on.}
     */
    public boolean isDragonPerchTimeOn() {
        return isInBounds(general.dragonPerchTime, 10);
    }

    /**
     * @return {@code true} if the {@code Dragon Perch Time} option is {@code "instant".}
     */
    public boolean isInstantDragonPerchTime() {
        return common().general().dragonPerchTime == 9;
    }

    /**
     * Returns the current {@code Dragon Perch Time} option in milliseconds.
     */
    public int getDragonPerchTime() {
        return common().general().dragonPerchTime;
    }

    /**
     * Returns the current {@code Ender Eye Breaking Cooldown} option in ticks.
     */
    public int getEnderEyeBreakingCooldown() {
        return Arithmetics.S_asTick(common().accessibility().enderEyeBreakingCooldown);
    }

    /**
     * Determines if a certain option is {@code greater than} or {@code equal to} inputted parameters.
     */
    public static boolean isInBounds(int option, int min) {
        return option >= min;
    }

    /**
     * @return the {@code Easy} mode option.
     */
    public static boolean isEasyMode() {
        return common().general().mode.equals(Mode.EASY);
    }

    /**
     * @return the {@code Balanced} mode option.
     */
    public static boolean isBalancedMode() {
        return common().general().mode.equals(Mode.BALANCED);
    }

    /**
     * @return the {@code Doom} mode option.
     */
    public static boolean isDoomMode() {
        return common().general().mode.equals(Mode.DOOM);
    }

    /**
     * @return a {@code int} value based on doom mode.
     */
    public static int doomOrDefault(int doomValue, int defaultValue) {
        return isDoomMode() ? doomValue : defaultValue;
    }

    /**
     * @return a {@code double} value based on doom mode.
     */
    public static double doomOrDefault(double doomValue, double defaultValue) {
        return isDoomMode() ? doomValue : defaultValue;
    }

    /**
     * @return a {@code float} value based on doom mode.
     */
    public static float doomOrDefault(float doomValue, float defaultValue) {
        return isDoomMode() ? doomValue : defaultValue;
    }
}