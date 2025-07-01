package net.dillon.speedrunnermod.option;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.mixin.main.command.argument.ItemStackArgumentTypeMixin;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.util.TranslatableOption;
import net.minecraft.util.math.MathHelper;

import java.util.*;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.*;

/**
 * All Speedrunner Mod {@code options.}
 * <p>When adding new options...</p>
 * <p>- Must add a check for restart required in restart required screen, only if necessary,</p>
 * <p>- Determine if it is leaderboard-eligible, and then implement into {@code Leaderboards}.</p>
 * <p>- An {@code "isBroken"} check safe boot screen and in {@link BaseOptions#safeCheck()}</p>
 * <p>- A ModListOption,</p>
 * <p>- A reset option in {@code resetAllOptions()} method.</p>
 */
public class ModOptions {
    public final Main main = new Main();
    public final Advanced advanced = new Advanced();
    public final StructureSpawnRates structureSpawnRates = new StructureSpawnRates();
    public final Mixins mixins = new Mixins();

    public static final Handler OPTIONS = new Handler();

    /**
     * A handler class for handling the main options file.
     */
    public static class Handler extends BaseOptions<ModOptions> {

        protected Handler() {
            super(ModUtil.CONFIG_FILE_NAME);
        }

        @Override
        protected ModOptions createDefault() {
            return new ModOptions();
        }

        @Override
        protected Class<ModOptions> getConfigClass() {
            return ModOptions.class;
        }

        @Override
        protected void safeCheck() {
            if (options().main.playingMode.getCurrentValue() == null) {
                if (isEnvironmentTypeServer()) {
                    this.throwNullPointerException("Playing Mode", PlayingMode.values());
                } else {
                    error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.playingMode");
                    isSafeToPlay(false);
                    BrokenModOptions.playingMode = true;
                }
            }

            if (options().main.structureSpawnRates.getCurrentValue() == null) {
                if (isEnvironmentTypeServer()) {
                    this.throwNullPointerException("Structure Spawn Rates", StructureSpawnRate.values());
                } else {
                    error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.structureSpawnRates");
                    isSafeToPlay(false);
                    BrokenModOptions.structureSpawnRates = true;
                }
            }

            if (options().main.mobSpawningRate.getCurrentValue() == null) {
                if (isEnvironmentTypeServer()) {
                    this.throwNullPointerException("Mob Spawning Rate", MobSpawningRate.values());
                } else {
                    error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.mobSpawningRate");
                    isSafeToPlay(false);
                    BrokenModOptions.mobSpawningRate = true;
                }
            }

            if (options().main.leaderboardsMode.getCurrentValue()) {
                String message = "Leaderboards mode is ON, please disable, as the leaderboards have been deleted.";
                if (isEnvironmentTypeServer()) {
                    throw new IllegalStateException(message);
                } else {
                    error(message);
                    isSafeToPlay(false);
                    BrokenModOptions.leaderboards = true;
                }
            }

            if (options().main.netherPortalDelay.getCurrentValue() < 1) {
                if (isEnvironmentTypeServer()) {
                    this.throwNumberLessThanOneException("Nether Portal Cooldown");
                } else {
                    error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.netherPortalCooldown");
                    isSafeToPlay(false);
                    BrokenModOptions.netherPortalCooldown = true;
                }
            } else if (!options().isNetherPortalCooldownValid()) {
                warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.netherPortalCooldown");
            }

            if (options().main.strongholdDistance.getCurrentValue() < 1) {
                if (isEnvironmentTypeServer()) {
                    this.throwNumberLessThanOneException("Stronghold Distance");
                } else {
                    error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.strongholdDistance");
                    isSafeToPlay(false);
                    BrokenModOptions.strongholdDistance = true;
                }
            } else if (!options().isStrongholdDistanceValid()) {
                warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.strongholdDistance");
            }

            if (options().main.strongholdSpread.getCurrentValue() < 1) {
                if (isEnvironmentTypeServer()) {
                    this.throwNumberLessThanOneException("Stronghold Spread");
                } else {
                    error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.strongholdSpread");
                    isSafeToPlay(false);
                    BrokenModOptions.strongholdSpread = true;
                }
            } else if (!options().isStrongholdSpreadValid()) {
                warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.strongholdSpread");
            }

            if (options().main.strongholdCount.getCurrentValue() < 1) {
                if (isEnvironmentTypeServer()) {
                    this.throwNumberLessThanOneException("Stronghold Count");
                } else {
                    error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.strongholdCount");
                    isSafeToPlay(false);
                    BrokenModOptions.strongholdCount = true;
                }
            } else if (!options().isStrongholdCountValid()) {
                warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.strongholdCount");
            }

            if (options().main.strongholdPortalRoomCount.getCurrentValue() < 1) {
                if (isEnvironmentTypeServer()) {
                    this.throwNumberLessThanOneException("Stronghold Portal Room Count");
                } else {
                    error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.strongholdPortalRoomCount");
                    isSafeToPlay(false);
                    BrokenModOptions.strongholdPortalRoomCount = true;
                }
            } else if (!options().isStrongholdPortalRoomCountValid()) {
                warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.strongholdPortalRoomCount");
            }

            if (options().main.strongholdLibraryCount.getCurrentValue() < 1) {
                if (isEnvironmentTypeServer()) {
                    this.throwNumberLessThanOneException("Stronghold Library Count");
                } else {
                    error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.strongholdLibraryCount");
                    isSafeToPlay(false);
                    BrokenModOptions.strongholdLibraryCount = true;
                }
            } else if (!options().isStrongholdLibraryCountValid()) {
                warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.strongholdLibraryCount");
            }

            if (options().main.blockBreakingMultiplier.getCurrentValue() < 1) {
                if (isEnvironmentTypeServer()) {
                    throw new ArithmeticException("blockBreakingMultiplier cannot be set to a value less than 1.");
                } else {
                    error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.blockBreakingMultiplier");
                    isSafeToPlay(false);
                    BrokenModOptions.blockBreakingMultiplier = true;
                    warn("Cannot divide by zero! o_0");
                }
            } else if (!options().isBlockBreakingMultiplierValid()) {
                warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.blockBreakingMultiplier");
            }

            if (options().advanced.speedrunnersWastelandBiomeWeight.getCurrentValue() < 1) {
                if (isEnvironmentTypeServer()) {
                    throw new IllegalStateException("Option \"Speedrunner's Wasteland Biome Weight\" cannot be set below 1. If you do not want the speedrunner's wasteland biome to generate, turn \"Custom Biomes and Custom Biome Features\" OFF. Otherwise, please set speedrunnersWastelandBiomeWeight to a value greater than or equal to 1.");
                } else {
                    error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.speedrunnersWastelandBiomeWeight");
                    isSafeToPlay(false);
                    BrokenModOptions.speedrunnersWastelandBiomeWeight = true;
                    warn("Speedrunner's Wasteland Biome Weight is below 1. Instead, turn \"Custom Biomes and Custom Biome Features\" OFF.");
                }
            } else if (!options().isSpeedrunnersWastelandBiomeWeightValid()) {
                warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.speedrunnersWastelandBiomeWeight");
                warn("The weight for the Speedrunner's Wasteland biome is either too high or too low. Proceed with caution.");
            }

            if (!options().isDragonPerchTimeValid()) {
                warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.dragonPerchTime");
            }

            if (!options().isAnvilCostLimitValid()) {
                warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.anvilCostLimit");
            }

            if (!options().isEyeOfEnderBreakingCooldownValid()) {
                warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.eyeOfEnderBreakingCooldown");
            }
        }
    }

    /**
     * All {@code Main Speedrunner Mod options.}
     * <p>See additional comments inside of static class for option documentation.</p>
     */
    public static class Main {

        /**
         * Determines the playing mode of the mod. The mode determines what features are added.
         */
        public OptionValue<PlayingMode> playingMode = new OptionValue<>(PlayingMode.EASY, true);

        /**
         * Determines how frequently Minecraft structures generate throughout the world.
         */
        public OptionValue<StructureSpawnRate> structureSpawnRates = new OptionValue<>(StructureSpawnRate.COMMON, false);

        /**
         * Allows certain blocks to be broken faster.
         */
        public OptionValue<Boolean> fasterBlockBreaking = new OptionValue<>(true, false);

        /**
         * The multiplier for how faster blocks can be broken.
         */
        public OptionValue<Integer> blockBreakingMultiplier = new OptionValue<>(1, false);

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
        public OptionValue<Integer> dragonPerchTime = new OptionValue<>(8, false);

        /**
         * Instantly kills a ghast when they shoot a fireball.
         */
        public OptionValue<Boolean> killGhastOnFireball = new OptionValue<>(false, false);

        /**
         * Improves villager trades by making them less expensive and sell better stuff.
         */
        public OptionValue<Boolean> betterVillagerTrades = new OptionValue<>(true, true);

        /**
         * Allows certain items to be fireproof.
         */
        public OptionValue<Boolean> fireproofItems = new OptionValue<>(true, false);

        /**
         * Allows the Speedrunner's Wasteland biome to generate, and allows some additional worldgen features to be added to different biomes.
         */
        public OptionValue<Boolean> customBiomesAndCustomBiomeFeatures = new OptionValue<>(true, true);

        /**
         * Allows certain ores to generate more commonly.
         */
        public OptionValue<Boolean> commonOres = new OptionValue<>(true, false);

        /**
         * Allows certain boats to be fireproof.
         */
        public OptionValue<Boolean> lavaBoats = new OptionValue<>(true, false);

        /**
         * Allows water to be placed in the nether.
         */
        public OptionValue<Boolean> netherWater = new OptionValue<>(true, false);

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
         * Determines how far from spawn strongholds can generate.
         */
        public OptionValue<Integer> strongholdDistance = new OptionValue<>(4, true);

        /**
         * Determines how far apart strongholds can generate from each other.
         */
        public OptionValue<Integer> strongholdSpread = new OptionValue<>(3, true);

        /**
         * Determines the total amount of strongholds that can generate in a singular Minecraft world.
         */
        public OptionValue<Integer> strongholdCount = new OptionValue<>(128, true);

        /**
         * Determines how many stronghold portal rooms can generate per stronghold.
         */
        public OptionValue<Integer> strongholdPortalRoomCount = new OptionValue<>(3, true);

        /**
         * Determines how many libraries can generate per stronghold.
         */
        public OptionValue<Integer> strongholdLibraryCount = new OptionValue<>(2, true);

        /**
         * Determines how big of packs mobs can spawn in.
         */
        public OptionValue<MobSpawningRate> mobSpawningRate = new OptionValue<>(MobSpawningRate.HIGH, false);

        /**
         * Sets the delay when entering/exiting the nether via a nether portal block.
         */
        public OptionValue<Integer> netherPortalDelay = new OptionValue<>(2, false);

        /**
         * Allows fireballs to be thrown.
         */
        public OptionValue<Boolean> throwableFireballs = new OptionValue<>(true, false);

        /**
         * Allows arrows to blow up beds.
         */
        public OptionValue<Boolean> arrowsDestroyBeds = new OptionValue<>(true, false);

        /**
         * Allows nether portals to be built in the end.
         */
        public OptionValue<Boolean> globalNetherPortals = new OptionValue<>(true, false);

        /**
         * Removes the "too expensive" feature from anvils, and also lowers the maximum cost for block use.
         */
        public OptionValue<Boolean> betterAnvil = new OptionValue<>(true, false);

        /**
         * Sets the maximum cost that is allowed when using an anvil.
         * <p>If the cost goes above this value, the cost will instead be this value.</p>
         */
        public OptionValue<Integer> anvilCostLimit = new OptionValue<>(10, false);

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
         * Sends the players coordinates to chat upon death, and displays them on the death screen.
         */
        public OptionValue<Boolean> showDeathCords = new OptionValue<>(true, false);

        /**
         * This allows all world modifications to be applied, which includes making structures more common, modifying mob/creature spawn rates, doom mode features, and more.
         */
        public OptionValue<Boolean> customDataGeneration = new OptionValue<>(true, false);

        /**
         * Enables leaderboard mode, and applies the leaderboard checks and settings to the mod.
         */
        @Deprecated
        public OptionValue<Boolean> leaderboardsMode = new OptionValue<>(false, true);
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
        public OptionValue<Boolean> higherBreathTime = new OptionValue<>(true, true);

        /**
         * Allow all types/variants of speedrunner wood to generate across the world. This includes the different variants of speedrunner trees, dead speedrunner trees, and dead speedrunner bushes.
         */
        public OptionValue<Boolean> generateSpeedrunnerWood = new OptionValue<>(true, false);

        /**
         * The weight for the Speedrunner's Wasteland biome (how commonly it can generate).
         */
        public OptionValue<Integer> speedrunnersWastelandBiomeWeight = new OptionValue<>(9, true);

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
        public OptionValue<Integer> enderEyeBreakingCooldown = new OptionValue<>(60, false);

        /**
         * Determines the total amount of piglin that can teleport to the player per time using the piglin awakener item (Default = 10).
         */
        public OptionValue<Integer> piglinAwakenerPiglinCount = new OptionValue<>(10, false);

        /**
         * Determines the explosion power for fireballs when thrown with a fire charge.
         */
        public OptionValue<Integer> fireballExplosionPower = new OptionValue<>(2, false);

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
        public OptionValue<List<Integer>> annulEyePortalRoomDistanceXYZ = new OptionValue<>(ModUtil.createListOption(-128, -128, -128, 128, 128, 128), false);

        /**
         * When using the piglin awakener, the game will search around the player [X_Y_Z] blocks to find nearby piglin. The higher these numbers, the farther out the game looks. Increasing these numbers however is not recommended, as it could create extreme amounts of lag.
         */
        public OptionValue<List<Double>> piglinAwakenerPiglinDistanceXYZ = new OptionValue<>(ModUtil.createListOption(100.0D, 100.0D, 100.0D), false);

        /**
         * Determines the distance that the blaze spotter will use to determine the nearest blaze spawner.
         */
        public OptionValue<List<Integer>> blazeSpotterDistanceXYZ = new OptionValue<>(ModUtil.createListOption(-156, -72, -156, 156, 72, 156), false);

        /**
         * When using the raid eradicator, the item will search a distance to search for the nearest raider entities.
         */
        public OptionValue<List<Double>> raidEradicatorDistanceXYZ = new OptionValue<>(ModUtil.createListOption(300.0D, 300.0D, 300.0D), false);

        /**
         * The dragon's pearl item will look in the radius of [X_Y_Z] for the nearest ender dragon, and choose that dragon to control perching.
         */
        public OptionValue<List<Double>> dragonsPearlDragonDistanceXYZ = new OptionValue<>(ModUtil.createListOption(150.0D, 150.0D, 150.0D), false);

        /**
         * Determines the entities in range that will be killed upon the ender dragon's death.
         * <p>This option is redundant if the option Dragon Kills Nearby Hostile Entities is OFF.</p>
         */
        public OptionValue<List<Double>> dragonKillsHostileEntitiesDistance = new OptionValue<>(ModUtil.createListOption(200.0D, 200.0D, 200.0D), false);

        /**
         * When on doom mode, the dragon cannot die if there is a nearby Goliath. This option specifies the range that the Goliath has to be in from the dragon in order for it to be immune.
         */
        public OptionValue<List<Double>> dragonImmunityDetectionDistanceForGoliath = new OptionValue<>(ModUtil.createListOption(200.0D, 200.0D, 200.0D), false);

        /**
         * When on doom mode, the dragon cannot die if there is a nearby wither. This option specifies the range that the wither has to be in from the dragon in order for it to be immune.
         */
        public OptionValue<List<Double>> dragonImmunityDetectionDistanceForWither = new OptionValue<>(ModUtil.createListOption(300.0D, 300.0D, 300.0D), false);

        /**
         * A list of all {@code mod IDS} loaded into Minecraft. Add another mod ID to this list if you are running additional mods with the speedrunner mod. This will allow certain commands to work properly. See {@link ItemStackArgumentTypeMixin}.
         * <p>Do NOT remove "minecraft" from this list, whatever you do.</p>
         */
        public OptionValue<List<String>> modIds = new OptionValue<>(new ArrayList<>(), false);
    }

    /**
     * All {@code Mixin} control options.
     */
    public static class Mixins {

        /**
         * Applies the terrablender surface rule data mixin into the game.
         * <p>Disable this if you do not want doom stone to generate throughout the end when doom mode is enabled, or if another mod is trying to generate other blocks.</p>
         */
        public OptionValue<Boolean> terraBlenderSurfaceRuleDataMixin = new OptionValue<>(true, true);
    }

    /**
     * {@code Structure Spawn Rates} config.
     * <p>These values are only applied if the {@code Structure Spawn Rates} option is set to {@code CUSTOM.}
     * <p>The {@code first integer} in the option list is the {@code spacing value.}
     * <p>The {@code second integer} in the option list is the {@code separation value.}
     * <p>The {@code separation value} should NEVER be greater than or equal to the spacing value. The game will crash if this happens.
     */
    public static class StructureSpawnRates {
        public OptionValue<List<Integer>> ancientCities = new OptionValue<>(ModUtil.createStructureSpawnRateOption(16, 8), false);
        public OptionValue<List<Integer>> villages = new OptionValue<>(ModUtil.createStructureSpawnRateOption(16, 8), false);
        public OptionValue<List<Integer>> desertPyramids = new OptionValue<>(ModUtil.createStructureSpawnRateOption(10, 5), false);
        public OptionValue<List<Integer>> junglePyramids = new OptionValue<>(ModUtil.createStructureSpawnRateOption(10, 5), false);
        public OptionValue<List<Integer>> pillagerOutposts = new OptionValue<>(ModUtil.createStructureSpawnRateOption(10, 5), false);
        public OptionValue<List<Integer>> endCities = new OptionValue<>(ModUtil.createStructureSpawnRateOption(7, 3), false);
        public OptionValue<List<Integer>> woodlandMansions = new OptionValue<>(ModUtil.createStructureSpawnRateOption(25, 12), false);
        public OptionValue<List<Integer>> ruinedPortals = new OptionValue<>(ModUtil.createStructureSpawnRateOption(9, 4), false);
        public OptionValue<List<Integer>> shipwrecks = new OptionValue<>(ModUtil.createStructureSpawnRateOption(10, 5), false);
        public OptionValue<List<Integer>> trialChambers = new OptionValue<>(ModUtil.createStructureSpawnRateOption(12, 6), false);
        public OptionValue<List<Integer>> netherComplexes = new OptionValue<>(ModUtil.createStructureSpawnRateOption(8, 4), false);
    }

    /**
     * Returns true if the {@code Dragon Perch Time} option is valid.
     */
    public boolean isDragonPerchTimeValid() {
        return isInBounds(main.dragonPerchTime.getCurrentValue(), 8, 90);
    }

    /**
     * Returns true if the {@code Dragon Perch Time} option is {@code on.}
     */
    public boolean isDragonPerchTimeOn() {
        return isInBounds(main.dragonPerchTime.getCurrentValue(), 10);
    }

    /**
     * Returns true if the {@code Dragon Perch Time} option is {@code "instant".}
     */
    public boolean isInstantDragonPerchTime() {
        return options().main.dragonPerchTime.getCurrentValue() == 9;
    }

    /**
     * Returns the current {@code Dragon Perch Time} option in milliseconds.
     */
    public int getDragonPerchTime() {
        return ModUtil.millisecondsAsSeconds(options().main.dragonPerchTime.getCurrentValue());
    }

    /**
     * Returns true if the {@code Block Breaking Multiplier} option is valid.
     */
    public boolean isBlockBreakingMultiplierValid() {
        return isInBounds(main.blockBreakingMultiplier.getCurrentValue(), 1, 3);
    }

    /**
     * Returns true if the {@code Stronghold Distance} option is valid.
     */
    public boolean isStrongholdDistanceValid() {
        return isInBounds(main.strongholdDistance.getCurrentValue(), 3, 64);
    }

    /**
     * Returns true if the {@code Stronghold Spread} option is valid.
     */
    public boolean isStrongholdSpreadValid() {
        return isInBounds(main.strongholdSpread.getCurrentValue(), 2, 32);
    }

    /**
     * Returns true if the {@code Stronghold Count} option is valid.
     */
    public boolean isStrongholdCountValid() {
        return isInBounds(main.strongholdCount.getCurrentValue(), 4, 156);
    }

    /**
     * Returns true if the {@code Stronghold Portal Room Count} option is valid.
     */
    public boolean isStrongholdPortalRoomCountValid() {
        return isInBounds(main.strongholdPortalRoomCount.getCurrentValue(), 0, 3);
    }

    /**
     * Returns true if the {@code Stronghold Library Count} option is valid.
     */
    public boolean isStrongholdLibraryCountValid() {
        return isInBounds(main.strongholdLibraryCount.getCurrentValue(), 1, 10);
    }

    /**
     * Returns true if the {@code Anvil Cost Limit} option is valid.
     */
    public boolean isAnvilCostLimitValid() {
        return isInBounds(main.anvilCostLimit.getCurrentValue(), 1, 50);
    }

    /**
     * Returns true if the {@code Nether Portal Cooldown} option is valid.
     */
    public boolean isNetherPortalCooldownValid() {
        return isInBounds(main.netherPortalDelay.getCurrentValue(), 0, 20);
    }

    /**
     * Returns true if the {@code Speedrunner's Wasteland Biome Weight} option is valid.
     */
    public boolean isSpeedrunnersWastelandBiomeWeightValid() {
        return isInBounds(advanced.speedrunnersWastelandBiomeWeight.getCurrentValue(), 1, 32);
    }

    /**
     * Returns true if the {@code Eye of Ender Breaking Cooldown} advanced option is valid.
     */
    public boolean isEyeOfEnderBreakingCooldownValid() {
        return isInBounds(advanced.enderEyeBreakingCooldown.getCurrentValue(), 20, 200);
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

    public enum PlayingMode implements TranslatableOption {
        EASY(0, "speedrunnermod.options.playing_mode.easy"),
        BALANCED(1, "speedrunnermod.options.playing_mode.balanced"),
        DOOM(2, "speedrunnermod.options.playing_mode.doom");

        private static final PlayingMode[] VALUES = Arrays.stream(PlayingMode.values()).sorted(Comparator.comparingInt(PlayingMode::getId)).toArray(PlayingMode[]::new);
        private final int id;
        private final String translateKey;

        PlayingMode(int id, String translationKey) {
            this.id = id;
            this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
        }

        /**
         * Returns the {@code id value} of the {@code Playing Mode} option.
         */
        @Override
        public int getId() {
            return this.id;
        }

        /**
         * Returns the {@code translation key} of the {@code Playing Mode} option.
         */
        @Override
        public String getTranslationKey() {
            return this.translateKey;
        }

        /**
         * Not sure what this does to be honest, but it's used in ModListOptions.
         */
        public static PlayingMode byId(int id) {
            return VALUES[MathHelper.floorMod(id, VALUES.length)];
        }
    }

    /**
     * All the different {@code Structure Spawn Rate} options, from extremely common to extremely rare.
     */
    public enum StructureSpawnRate implements TranslatableOption {
        EVERYWHERE(0, "speedrunnermod.options.structure_spawn_rates.everywhere"),
        VERY_COMMON(1, "speedrunnermod.options.structure_spawn_rates.very_common"),
        COMMON(2, "speedrunnermod.options.structure_spawn_rates.common"),
        NORMAL(3, "speedrunnermod.options.structure_spawn_rates.normal"),
        DEFAULT(4, "speedrunnermod.options.structure_spawn_rates.default"),
        RARE(5, "speedrunnermod.options.structure_spawn_rates.rare"),
        VERY_RARE(6, "speedrunnermod.options.structure_spawn_rates.very_rare"),
        CUSTOM(7, "speedrunnermod.options.structure_spawn_rates.custom"),
        DISABLED(8, "speedrunnermod.options.structure_spawn_rates.disabled");

        private static final StructureSpawnRate[] VALUES = Arrays.stream(StructureSpawnRate.values()).sorted(Comparator.comparingInt(StructureSpawnRate::getId)).toArray(StructureSpawnRate[]::new);
        private final int id;
        private final String translateKey;

        StructureSpawnRate(int id, String translationKey) {
            this.id = id;
            this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
        }

        /**
         * Returns the {@code id value} of the {@code Structure Spawn Rate} option.
         */
        @Override
        public int getId() {
            return this.id;
        }

        /**
         * Returns the {@code translation key} of the {@code Structure Spawn Rate} option.
         */
        @Override
        public String getTranslationKey() {
            return this.translateKey;
        }

        /**
         * Not sure what this does to be honest, but it's used in ModListOptions.
         */
        public static StructureSpawnRate byId(int id) {
            return VALUES[MathHelper.floorMod(id, VALUES.length)];
        }
    }

    /**
     * All the different {@code Mob Spawning Rate} options.
     */
    public enum MobSpawningRate implements TranslatableOption {
        LOW(0, "speedrunnermod.options.mob_spawning_rate.low"),
        NORMAL(1, "speedrunnermod.options.mob_spawning_rate.normal"),
        HIGH(2, "speedrunnermod.options.mob_spawning_rate.high");

        private static final MobSpawningRate[] VALUES = Arrays.stream(MobSpawningRate.values()).sorted(Comparator.comparingInt(MobSpawningRate::getId)).toArray(MobSpawningRate[]::new);
        private final int id;
        private final String translateKey;

        MobSpawningRate(int id, String translationKey) {
            this.id = id;
            this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
        }

        /**
         * Returns the {@code id value} of the {@code Mob Spawning Rate} option.
         */
        @Override
        public int getId() {
            return this.id;
        }

        /**
         * Returns the {@code translation key} of the {@code Mob Spawning Rate} option.
         */
        @Override
        public String getTranslationKey() {
            return this.translateKey;
        }

        /**
         * Not sure what this does to be honest, but it's used in ModListOptions.
         */
        public static MobSpawningRate byId(int id) {
            return VALUES[MathHelper.floorMod(id, VALUES.length)];
        }
    }

    /**
     * @return the {@code Easy} playing mode option.
     */
    public static boolean isPlayingModeEasy() {
        return options().main.playingMode.getCurrentValue().equals(PlayingMode.EASY);
    }

    /**
     * @return the {@code Balanced} playing mode option.
     */
    public static boolean isPlayingModeBalanced() {
        return options().main.playingMode.getCurrentValue().equals(PlayingMode.BALANCED);
    }

    /**
     * @return the {@code Doom} playing mode option.
     */
    public static boolean isPlayingModeDoom() {
        return options().main.playingMode.getCurrentValue().equals(PlayingMode.DOOM);
    }

    /**
     * Returns the {@code Everywhere} structure spawn rate option.
     */
    public static boolean isStructureSpawnRatesEverywhere() {
        return options().main.structureSpawnRates.getCurrentValue().equals(StructureSpawnRate.EVERYWHERE);
    }

    /**
     * Returns the {@code Very Common} structure spawn rate option.
     */
    public static boolean isStructureSpawnRatesVeryCommon() {
        return options().main.structureSpawnRates.getCurrentValue().equals(StructureSpawnRate.VERY_COMMON);
    }

    /**
     * Returns the {@code Very Common} or {@code Common} structure spawn rate option.
     */
    public static boolean isStructureSpawnRatesVeryCommonOrCommon() {
        return options().main.structureSpawnRates.getCurrentValue().equals(StructureSpawnRate.VERY_COMMON) || options().main.structureSpawnRates.getCurrentValue().equals(StructureSpawnRate.COMMON);
    }

    /**
     * Returns the {@code Common} structure spawn rate option.
     */
    public static boolean isStructureSpawnRatesCommon() {
        return options().main.structureSpawnRates.getCurrentValue().equals(StructureSpawnRate.COMMON);
    }

    /**
     * Returns the {@code Normal} structure spawn rate option.
     */
    public static boolean isStructureSpawnRatesNormal() {
        return options().main.structureSpawnRates.getCurrentValue().equals(StructureSpawnRate.NORMAL);
    }

    /**
     * Returns the {@code Default} structure spawn rate option,
     */
    public static boolean isStructureSpawnRatesDefault() {
        return options().main.structureSpawnRates.getCurrentValue().equals(StructureSpawnRate.DEFAULT);
    }

    /**
     * Returns the {@code Normal} or {@code Default} structure spawn rate option.
     */
    public static boolean isStructureSpawnRatesCommonNormalOrDefault() {
        return options().main.structureSpawnRates.getCurrentValue().equals(StructureSpawnRate.COMMON) || options().main.structureSpawnRates.getCurrentValue().equals(StructureSpawnRate.NORMAL) || options().main.structureSpawnRates.getCurrentValue().equals(StructureSpawnRate.DEFAULT);
    }

    /**
     * Returns the {@code Rare} structure spawn rate option.
     */
    public static boolean isStructureSpawnRatesRare() {
        return options().main.structureSpawnRates.getCurrentValue().equals(StructureSpawnRate.RARE);
    }

    /**
     * Returns the {@code Very Rare} structure spawn rate option.
     */
    public static boolean isStructureSpawnRatesVeryRare() {
        return options().main.structureSpawnRates.getCurrentValue().equals(StructureSpawnRate.VERY_RARE);
    }

    /**
     * Returns the {@code Custom} structure spawn rate option.
     */
    public static boolean isStructureSpawnRatesCustom() {
        return options().main.structureSpawnRates.getCurrentValue().equals(StructureSpawnRate.CUSTOM);
    }

    /**
     * The game is safe to run if the {@code safe} parameter returns true.
     */
    public static void isSafeToPlay(boolean safe) {
        SpeedrunnerMod.safeBoot = !safe;
    }
}