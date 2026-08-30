package net.dillon.speedrunnermod.option;

import net.dillon.dillonlib.util.Arithmetics;
import net.dillon.speedrunnermod.helper.ModConstants;
import net.dillon.speedrunnermod.helper.ModHelper;

import java.util.List;

import static net.dillon.speedrunnermod.helper.ModConstants.OPTIONS_WARNING_MESSAGE;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.*;

/**
 * All Speedrunner Mod {@code options.}
 * <p>When adding new options...</p>
 * <p>- An {@code "isBroken"} check safe boot screen and in {@link CommonModOptions.ModOptionsHandler#safeCheck()}</p>
 * <p>- and a {@code ModListOption.}</p>
 */
public class CommonModOptions {
    public static final ModOptionsHandler INSTANCE = new ModOptionsHandler();
    public final General general = new General();
    public final WorldGen worldGen = new WorldGen();
    public final Advanced advanced = new Advanced();
    public final CustomStructureSpawnRates customStructureSpawnRates = new CustomStructureSpawnRates();
    public final Mixins mixins = new Mixins();

    /**
     * All {@code General Speedrunner Mod options.}
     * <p>See additional comments inside of static class for option documentation.</p>
     */
    public static class General {

        /**
         * Determines the mode of the mod. The mode determines what features are added.
         */
        public OptionValue<Mode> mode = new OptionValue<>(Mode.EASY, true);

        /**
         * Allows certain blocks to be broken faster.
         */
        public OptionValue<Boolean> fasterBlockBreaking = new OptionValue<>(true, false);

        /**
         * The multiplier for how faster blocks can be broken.
         */
        public IntegerOptionValue blockBreakingMultiplier = new IntegerOptionValue(1, false, 1, 3);

        /**
         * Grants the player with a pre-equipped unbreakable elytra and a stack of flight duration 3 firework rockets.
         */
        public OptionValue<Boolean> iCarusMode = new OptionValue<>(false, false);

        /**
         * Grants the player with an ender pearl that does not do damage nor get consumed upon use.
         */
        public OptionValue<Boolean> infiniPearlMode = new OptionValue<>(false, false);

        /**
         *  Determines the amount of time (in seconds) that it takes for the ender dragon to automatically perch upon entering the end.
         *  <p>Note: {@code 8 = OFF, 9 = Instant.}</p>
         */
        public IntegerOptionValue dragonPerchTime = new IntegerOptionValue(8, false, 8, 90);

        /**
         * Instantly kills a ghast when they shoot a fireball.
         */
        public OptionValue<Boolean> killGhastOnFireball = new OptionValue<>(false, false);

        /**
         * Improves most vanilla food items to restore more hunger bars and give more saturation.
         */
        public OptionValue<Boolean> betterFoods = new OptionValue<>(true, false);

        /**
         * Enables/disables fall damage.
         */
        public OptionValue<Boolean> fallDamage = new OptionValue<>(true, false);

        /**
         * Enables/disables "kinetic" damage (damage taken when flying into walls with an elytra).
         */
        public OptionValue<Boolean> kineticDamage = new OptionValue<>(true, false);

        /**
         * Allows fireballs to be thrown.
         */
        public OptionValue<Boolean> throwableFireballs = new OptionValue<>(true, false);

        /**
         * Determines the explosion power for fireballs when thrown with a fire charge.
         */
        public IntegerOptionValue fireballExplosionPower = new IntegerOptionValue(2, false, 1, 10);

        /**
         * Removes the "too expensive" feature from anvils, and also lowers the maximum cost for block use.
         */
        public OptionValue<Boolean> betterAnvil = new OptionValue<>(true, false);

        /**
         * Sets the maximum cost that is allowed when using an anvil.
         * <p>If the cost goes above this value, the cost will instead be this value.</p>
         */
        public IntegerOptionValue anvilCostLimit = new IntegerOptionValue(10, false, 1, 50);

        /**
         * Allows the combination of two maximum level enchanted items to go above the enchantment level cap.
         */
        public OptionValue<Boolean> higherEnchantmentLevels = new OptionValue<>(true, false);

        /**
         * Allows the player to right-click on an ore block and remove the silk touch enchantment from their handheld item.
         */
        public OptionValue<Boolean> rightClickToRemoveSilkTouch = new OptionValue<>(true, false);

        /**
         * Increases the rate at which mobs spawn from spawner blocks.
         */
        public OptionValue<Boolean> fasterSpawners = new OptionValue<>(true, false);

        /**
         * Makes everything smelt faster.
         */
        public OptionValue<Boolean> fasterSmelting = new OptionValue<>(true, false);

        /**
         * Makes everything brew faster.
         */
        public OptionValue<Boolean> fasterBrewing = new OptionValue<>(true, false);

        /**
         * Sends the players coordinates to chat upon death, and displays them on the death screen.
         */
        public OptionValue<Boolean> showDeathCords = new OptionValue<>(true, false);

        /**
         * Enables leaderboard mode, and applies the leaderboard checks and settings to the mod.
         */
        @Deprecated
        public OptionValue<Boolean> leaderboardsMode = new OptionValue<>(false, true);
    }

    /**
     * All {@code WorldGen Speedrunner Mod options.}
     */
    public static class WorldGen {

        /**
         * Determines how frequently Minecraft structures generate throughout the world.
         */
        public OptionValue<StructureSpawnRate> structureSpawnRates = new OptionValue<>(StructureSpawnRate.COMMON, false);

        /**
         * Determines how big of packs mobs can spawn in.
         */
        public OptionValue<CreatureSpawnRate> creatureSpawnRate = new OptionValue<>(CreatureSpawnRate.HIGH, false);

        /**
         * Allows certain ores to generate more commonly.
         */
        public OptionValue<Boolean> commonOres = new OptionValue<>(true, false);

        /**
         * Allows certain biomes, such as plains, deserts, savannas, etc. to generate more commonly.
         */
        public OptionValue<Boolean> betterBiomes = new OptionValue<>(true, true);

        /**
         * Allows the Speedrunner's Wasteland biome to generate.
         */
        public OptionValue<Boolean> generateSpeedrunnersWasteland = new OptionValue<>(true, true);

        /**
         * Allow all types/variants of speedrunner wood to generate across the world. This includes the different variants of speedrunner trees, dead speedrunner trees, and dead speedrunner bushes.
         */
        public OptionValue<Boolean> generateSpeedrunnerWood = new OptionValue<>(true, true);

        /**
         * Allows plain trees to generate more commonly in plains biomes.
         */
        public OptionValue<Boolean> commonPlainTrees = new OptionValue<>(true, false);

        /**
         * Allows nether portals to be built in the end.
         */
        public OptionValue<Boolean> globalNetherPortals = new OptionValue<>(true, false);

        /**
         * Allows water to be placed in the nether.
         */
        public OptionValue<Boolean> netherWater = new OptionValue<>(true, false);

        /**
         * Sets the delay when entering/exiting the nether via a nether portal block.
         */
        public IntegerOptionValue netherPortalDelay = new IntegerOptionValue(2, false, -1, 5);

        /**
         * Allows arrows to blow up beds.
         */
        public OptionValue<Boolean> arrowsDestroyBeds = new OptionValue<>(true, false);

        /**
         * Determines how far from spawn strongholds can generate.
         */
        public IntegerOptionValue strongholdDistance = new IntegerOptionValue(4, true, 3, 64);

        /**
         * Determines how far apart strongholds can generate from each other.
         */
        public IntegerOptionValue strongholdSpread = new IntegerOptionValue(3, true, 2, 32);

        /**
         * Determines the total amount of strongholds that can generate in a singular Minecraft world.
         */
        public IntegerOptionValue totalStrongholds = new IntegerOptionValue(128, true, 4, 156);

        /**
         * Determines how many stronghold portal rooms can generate per stronghold.
         */
        public IntegerOptionValue totalPortamRooms = new IntegerOptionValue(3, true, 1, 3);

        /**
         * Determines how many libraries can generate per stronghold.
         */
        public IntegerOptionValue totalLibraries = new IntegerOptionValue(2, true, 1, 8);
    }

    /**
     * All {@code Advanced Speedrunner Mod options.}
     */
    public static class Advanced {

        /**
         * Allows strongholds to generate differently, or smaller.
         */
        public OptionValue<Boolean> modifiedStrongholdGeneration = new OptionValue<>(true, true);

        /**
         * Allows strongholds to generate at higher/lower Y-levels, depending on if doom mode is enabled or not.
         */
        public OptionValue<Boolean> modifiedStrongholdYGeneration = new OptionValue<>(true, true);

        /**
         * Allows nether fortresses to generate differently, or smaller, along with more than two blaze spawners per fortress.
         */
        public OptionValue<Boolean> modifiedNetherFortressGeneration = new OptionValue<>(true, true);

        /**
         * Allows the player to breath for a longer period of time while underwater, and also allows the player to regain oxygen when coming out of water blocks.
         */
        public OptionValue<Boolean> increasedOxygen = new OptionValue<>(true, false);

        /**
         * In vanilla Minecraft, the ender dragon will fly away after perching when it takes so much damage. However, this option extends that damage amount, to allow the dragon to stay perched for a longer period of time, even after taking a large amount of damage.
         */
        public OptionValue<Boolean> longerDragonPerchStayTime = new OptionValue<>(true, false);

        /**
         * Piglins get scared of zombified piglin if they are within 6 blocks of the zombified piglin. This option decreases that distance to 2 blocks.
         */
        public OptionValue<Boolean> decreasedZombifiedPiglinScareDistance = new OptionValue<>(true, false);

        /**
         * Determines how long it takes (in ticks) for an eye of ender to break after throwing it.
         */
        public IntegerOptionValue enderEyeBreakingCooldown = new IntegerOptionValue(4, false, 1, 10);

        /**
         * Determines the total amount of piglin that can teleport to the player per time using the piglin awakener item (Default = 10).
         */
        public IntegerOptionValue piglinAwakenerPiglinCount = new IntegerOptionValue(10, false, 3, 25);

        /**
         * Determines if -- while the player is looking at a block, if they need to be shifting to throw a fireball. Disable this if you want to do things like fireball jump.
         */
        public OptionValue<Boolean> shiftToThrowFireball = new OptionValue<>(true, false);

        /**
         * Makes the ender dragon kill all nearby hostile entities upon dying, excluding enderman.
         */
        public OptionValue<Boolean> dragonKillsNearbyHostileEntities = new OptionValue<>(true, false);

        /**
         * On doom mode, the ender dragon cannot be killed by any means if Goliath and the Wither are still alive in the end.
         */
        public OptionValue<Boolean> dragonImmunityFromGoliathAndWither = new OptionValue<>(true, false);

        /**
         * When using the eye of annul stronghold portal room teleporter feature, it iterates through [-X, -Y, -Z, X, Y, Z] all blocks in this location to locate the portal room block. Negative values go below the player, positive values go above.
         */
        public OptionValue<List<Integer>> annulEyeSearchRadius = new OptionValue<>(ModHelper.createListOption(128, 128, 128), false);

        /**
         * When using the piglin awakener, the game will search around the player [X_Y_Z] blocks to find nearby piglin. The higher these numbers, the farther out the game looks. Increasing these numbers however is not recommended, as it could create extreme amounts of lag.
         */
        public OptionValue<List<Integer>> piglinAwakenerSearchRadius = new OptionValue<>(ModHelper.createListOption(100, 100, 100), false);

        /**
         * Determines the distance that the blaze spotter will use to determine the nearest blaze spawner.
         */
        public OptionValue<List<Integer>> blazeSpotterSearchRadius = new OptionValue<>(ModHelper.createListOption(156, 72, 156), false);

        /**
         * When using the raid eradicator, the item will search a distance to search for the nearest raider entities.
         */
        public OptionValue<List<Integer>> raidEradicatorSearchRadius = new OptionValue<>(ModHelper.createListOption(300, 300, 300), false);

        /**
         * The dragon's pearl item will look in the radius of [X_Y_Z] for the nearest ender dragon, and choose that dragon to control perching.
         */
        public OptionValue<List<Integer>> dragonsPearlSearchRadius = new OptionValue<>(ModHelper.createListOption(150, 150, 150), false);

        /**
         * Determines the entities in range that will be killed upon the ender dragon's death.
         * <p>This option is redundant if the option Dragon Kills Nearby Hostile Entities is OFF.</p>
         */
        public OptionValue<List<Integer>> dragonMassKillRadius = new OptionValue<>(ModHelper.createListOption(300, 300, 300), false);

        /**
         * When on doom mode, the dragon cannot die if there is a nearby Goliath. This option specifies the range that the Goliath has to be in from the dragon in order for it to be immune.
         */
        public OptionValue<List<Integer>> dragonImmunityDetectionRadiusForGoliath = new OptionValue<>(ModHelper.createListOption(300, 300, 300), false);

        /**
         * When on doom mode, the dragon cannot die if there is a nearby wither. This option specifies the range that the wither has to be in from the dragon in order for it to be immune.
         */
        public OptionValue<List<Integer>> dragonImmunityDetectionRadiusForWither = new OptionValue<>(ModHelper.createListOption(300, 300, 300), false);

        /**
         * The detection for Goliath and zombie entity detection.
         */
        public OptionValue<List<Integer>> goliathAndZombieEntityDetectionRadius = new OptionValue<>(ModHelper.createListOption(300, 300, 300), false);
    }

    /**
     * {@code Structure Spawn Rates} config.
     * <p>These values are only applied if the {@code Structure Spawn Rates} option is set to {@code CUSTOM.}
     * <p>The {@code first integer} in the option list is the {@code spacing value.}
     * <p>The {@code second integer} in the option list is the {@code separation value.}
     * <p>The {@code separation value} should NEVER be greater than or equal to the spacing value. The game will crash if this happens.
     */
    public static class CustomStructureSpawnRates {
        public OptionValue<List<Integer>> ancientCities = new OptionValue<>(ModHelper.createStructureSpawnRateOption(16, 8), false);
        public OptionValue<List<Integer>> villages = new OptionValue<>(ModHelper.createStructureSpawnRateOption(16, 8), false);
        public OptionValue<List<Integer>> shipwrecks = new OptionValue<>(ModHelper.createStructureSpawnRateOption(10, 5), false);
        public OptionValue<List<Integer>> ruinedPortals = new OptionValue<>(ModHelper.createStructureSpawnRateOption(9, 4), false);
        public OptionValue<List<Integer>> desertPyramids = new OptionValue<>(ModHelper.createStructureSpawnRateOption(10, 5), false);
        public OptionValue<List<Integer>> junglePyramids = new OptionValue<>(ModHelper.createStructureSpawnRateOption(10, 5), false);
        public OptionValue<List<Integer>> igloos = new OptionValue<>(ModHelper.createStructureSpawnRateOption(9, 6), false);
        public OptionValue<List<Integer>> swampHuts = new OptionValue<>(ModHelper.createStructureSpawnRateOption(12, 6), false);
        public OptionValue<List<Integer>> oceanRuins = new OptionValue<>(ModHelper.createStructureSpawnRateOption(10, 5), false);
        public OptionValue<List<Integer>> pillagerOutposts = new OptionValue<>(ModHelper.createStructureSpawnRateOption(10, 5), false);
        public OptionValue<List<Integer>> endCities = new OptionValue<>(ModHelper.createStructureSpawnRateOption(7, 3), false);
        public OptionValue<List<Integer>> woodlandMansions = new OptionValue<>(ModHelper.createStructureSpawnRateOption(25, 12), false);
        public OptionValue<List<Integer>> trialChambers = new OptionValue<>(ModHelper.createStructureSpawnRateOption(12, 6), false);
        public OptionValue<List<Integer>> trailRuins = new OptionValue<>(ModHelper.createStructureSpawnRateOption(12, 6), false);
        public OptionValue<List<Integer>> netherComplexes = new OptionValue<>(ModHelper.createStructureSpawnRateOption(8, 4), false);
        public IntegerOptionValue mineshafts = new IntegerOptionValue(14, false, 2, 50);
    }

    /**
     * All {@code Mixin} control options.
     */
    public static class Mixins {

        /**
         * Applies the end gateway block entity mixin into the game.
         * <p>Disable this if you do not want doom stone to generate throughout the end when doom mode is enabled, or if another mod is trying to generate other blocks.</p>
         */
        public OptionValue<Boolean> theEndGatewayBlockEntityMixin = new OptionValue<>(true, true);

        /**
         * Applies the item stack mixin into the game
         * <p>Disable this if you do not want modded attributes to be grouped correctly.</p>
         */
        public OptionValue<Boolean> itemStackMixin = new OptionValue<>(true, true);
    }

    /**
     * @return {@code true} if the {@link IntegerOptionValue} is valid.
     */
    public static boolean isIntegerOptionValid(IntegerOptionValue option) {
        return isInBounds(option.getCurrentValue(), option.getMinValue(), option.getMaxValue());
    }

    /**
     * @return {@code true} if the {@code Dragon Perch Time} option is {@code on.}
     */
    public boolean isDragonPerchTimeOn() {
        return isInBounds(general.dragonPerchTime.getCurrentValue(), 10);
    }

    /**
     * @return {@code true} if the {@code Dragon Perch Time} option is {@code "instant".}
     */
    public boolean isInstantDragonPerchTime() {
        return common().general.dragonPerchTime.getCurrentValue() == 9;
    }

    /**
     * Returns the current {@code Dragon Perch Time} option in milliseconds.
     */
    public int getDragonPerchTime() {
        return common().general.dragonPerchTime.getCurrentValue();
    }

    /**
     * Returns the current {@code Ender Eye Breaking Cooldown} option in ticks.
     */
    public int getEnderEyeBreakingCooldown() {
        return Arithmetics.sas(common().advanced.enderEyeBreakingCooldown.getCurrentValue());
    }

    /**
     * Determines if a certain option is {@code greater than} or {@code equal to} inputted parameters.
     */
    public static boolean isInBounds(int option, int min) {
        return option >= min;
    }

    /**
     * Determines if a certain option is {@code less than} and {@code greater than} equal to said parameters.
     */
    public static boolean isInBounds(int option, int min, int max) {
        return option >= min && option <= max;
    }

    /**
     * @return the {@code Easy} mode option.
     */
    public static boolean isEasyMode() {
        return common().general.mode.getCurrentValue().equals(Mode.EASY);
    }

    /**
     * @return the {@code Balanced} mode option.
     */
    public static boolean isBalancedMode() {
        return common().general.mode.getCurrentValue().equals(Mode.BALANCED);
    }

    /**
     * @return the {@code Doom} mode option.
     */
    public static boolean isDoomMode() {
        return common().general.mode.getCurrentValue().equals(Mode.DOOM);
    }

    /**
     * Returns the {@code Everywhere} structure spawn rate option.
     */
    public static boolean isSsrEverywhere() {
        return common().worldGen.structureSpawnRates.getCurrentValue().equals(StructureSpawnRate.EVERYWHERE);
    }

    /**
     * Returns the {@code Very Common} structure spawn rate option.
     */
    public static boolean isSsrVeryCommon() {
        return common().worldGen.structureSpawnRates.getCurrentValue().equals(StructureSpawnRate.VERY_COMMON);
    }

    /**
     * Returns the {@code Very Common} or {@code Common} structure spawn rate option.
     */
    public static boolean isSsrVeryCommonCommon() {
        return common().worldGen.structureSpawnRates.getCurrentValue().equals(StructureSpawnRate.VERY_COMMON) || common().worldGen.structureSpawnRates.getCurrentValue().equals(StructureSpawnRate.COMMON);
    }

    /**
     * Returns the {@code Common} structure spawn rate option.
     */
    public static boolean isSsrCommon() {
        return common().worldGen.structureSpawnRates.getCurrentValue().equals(StructureSpawnRate.COMMON);
    }

    /**
     * Returns the {@code Normal} structure spawn rate option.
     */
    public static boolean isSsrNormal() {
        return common().worldGen.structureSpawnRates.getCurrentValue().equals(StructureSpawnRate.NORMAL);
    }

    /**
     * Returns the {@code Default} structure spawn rate option.
     */
    public static boolean isSsrDefault() {
        return common().worldGen.structureSpawnRates.getCurrentValue().equals(StructureSpawnRate.DEFAULT);
    }

    /**
     * Returns the {@code Rare} structure spawn rate option.
     */
    public static boolean isSsrRare() {
        return common().worldGen.structureSpawnRates.getCurrentValue().equals(StructureSpawnRate.RARE);
    }

    /**
     * Returns the {@code Very Rare} structure spawn rate option.
     */
    public static boolean isSsrVeryRare() {
        return common().worldGen.structureSpawnRates.getCurrentValue().equals(StructureSpawnRate.VERY_RARE);
    }

    /**
     * Returns the {@code Custom} structure spawn rate option.
     */
    public static boolean isSsrCustom() {
        return common().worldGen.structureSpawnRates.getCurrentValue().equals(StructureSpawnRate.CUSTOM);
    }

    /**
     * The game is safe to run if the {@code safe} parameter returns true.
     */
    @Deprecated(forRemoval = true)
    public static void isSafe(boolean safe) {
        ModConstants.safeBoot = !safe;
    }

    /**
     * A handler class for handling the main options file.
     */
    public static class ModOptionsHandler extends ModBaseOptionsHandler<CommonModOptions> {

        protected ModOptionsHandler() {
            super(ModHelper.CONFIG_FILE_NAME);
        }

        @Override
        protected CommonModOptions createDefault() {
            return new CommonModOptions();
        }

        @Override
        protected Class<CommonModOptions> getConfigClass() {
            return CommonModOptions.class;
        }

        @Override
        @Deprecated(forRemoval = true)
        protected void safeCheck() {
            if (common().general.mode.getCurrentValue() == null) {
                if (isEnvironmentTypeServer()) {
                    this.throwNullPointerException("Mode", Mode.values());
                } else {
                    this.setBroken(common().general.mode, "mode");
                }
            }

            if (common().worldGen.structureSpawnRates.getCurrentValue() == null) {
                if (isEnvironmentTypeServer()) {
                    this.throwNullPointerException("Structure Spawn Rates", StructureSpawnRate.values());
                } else {
                    this.setBroken(common().worldGen.structureSpawnRates, "structureSpawnRates");
                }
            }

            if (common().worldGen.creatureSpawnRate.getCurrentValue() == null) {
                if (isEnvironmentTypeServer()) {
                    this.throwNullPointerException("Mob Spawning Rate", CreatureSpawnRate.values());
                } else {
                    this.setBroken(common().worldGen.creatureSpawnRate, "mobSpawningRate");
                }
            }

            if (common().general.leaderboardsMode.getCurrentValue()) {
                String message = "Leaderboards mode is ON, please disable, as the leaderboards have been deleted.";
                if (isEnvironmentTypeServer()) {
                    throw new IllegalStateException(message);
                } else {
                    this.setBroken(common().general.leaderboardsMode, message);
                }
            }

            if (common().worldGen.netherPortalDelay.getCurrentValue() < common().worldGen.netherPortalDelay.getMinValue()) {
                if (isEnvironmentTypeServer()) {
                    this.throwNumberLessThanOneException("Nether Portal Cooldown");
                } else {
                    this.setBroken(common().worldGen.netherPortalDelay, "netherPortalDelay");
                }
            } else if (!isIntegerOptionValid(common().worldGen.netherPortalDelay)) {
                LOGGER.warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.netherPortalCooldown");
            }

            if (common().worldGen.strongholdDistance.getCurrentValue() < 1) {
                if (isEnvironmentTypeServer()) {
                    this.throwNumberLessThanOneException("Stronghold Distance");
                } else {
                    this.setBroken(common().worldGen.strongholdDistance, "strongholdDistance");
                }
            } else if (!isIntegerOptionValid(common().worldGen.strongholdDistance)) {
                LOGGER.warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.strongholdDistance");
            }

            if (common().worldGen.strongholdSpread.getCurrentValue() < 1) {
                if (isEnvironmentTypeServer()) {
                    this.throwNumberLessThanOneException("Stronghold Spread");
                } else {
                    this.setBroken(common().worldGen.strongholdSpread, "strongholdSpread");
                }
            } else if (!isIntegerOptionValid(common().worldGen.strongholdSpread)) {
                LOGGER.warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.strongholdSpread");
            }

            if (common().worldGen.totalStrongholds.getCurrentValue() < 1) {
                if (isEnvironmentTypeServer()) {
                    this.throwNumberLessThanOneException("Stronghold Count");
                } else {
                    this.setBroken(common().worldGen.totalStrongholds, "strongholdCount");
                }
            } else if (!isIntegerOptionValid(common().worldGen.totalStrongholds)) {
                LOGGER.warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.strongholdCount");
            }

            if (common().worldGen.totalPortamRooms.getCurrentValue() < 1) {
                if (isEnvironmentTypeServer()) {
                    this.throwNumberLessThanOneException("Stronghold Portal Room Count");
                } else {
                    this.setBroken(common().worldGen.totalPortamRooms, "strongholdPortalRoomCount");
                }
            } else if (!isIntegerOptionValid(common().worldGen.totalPortamRooms)) {
                LOGGER.warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.strongholdPortalRoomCount");
            }

            if (common().worldGen.totalLibraries.getCurrentValue() < 1) {
                if (isEnvironmentTypeServer()) {
                    this.throwNumberLessThanOneException("Stronghold Library Count");
                } else {
                    this.setBroken(common().worldGen.totalLibraries, "strongholdLibraryCount");
                }
            } else if (!isIntegerOptionValid(common().worldGen.totalLibraries)) {
                LOGGER.warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.strongholdLibraryCount");
            }

            if (common().general.blockBreakingMultiplier.getCurrentValue() < 1) {
                if (isEnvironmentTypeServer()) {
                    throw new ArithmeticException("blockBreakingMultiplier cannot be set to a value less than 1.");
                } else {
                    this.setBroken(common().general.blockBreakingMultiplier, "blockBreakingMultiplier");
                    LOGGER.warn("Cannot divide by zero! o_0");
                }
            } else if (!isIntegerOptionValid(common().general.blockBreakingMultiplier)) {
                LOGGER.warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.blockBreakingMultiplier");
            }

            if (!isIntegerOptionValid(common().general.dragonPerchTime)) {
                LOGGER.warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.dragonPerchTime");
            }

            if (!isIntegerOptionValid(common().general.anvilCostLimit)) {
                LOGGER.warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.anvilCostLimit");
            }

            if (!isIntegerOptionValid(common().advanced.enderEyeBreakingCooldown)) {
                LOGGER.warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.eyeOfEnderBreakingCooldown");
            }
        }
    }
}