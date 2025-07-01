package net.dillon.speedrunnermod.option;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.*;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.saveClientChanges;

/**
 * The main class for the leaderboards in the Speedrunner Mod.
 */
@Deprecated // deprecated because it's not used anymore
@Environment(EnvType.CLIENT)
public class Leaderboards {
    public static List<Text> ineligibleOptions = new ArrayList<>();
    private static final ModOptions.Main options = options().main;
    private static final ClientModOptions.Client cloptions = clientOptions().client;
    private static boolean currentLeaderboardsMode;
    private static ModOptions.StructureSpawnRate currentStructureSpawnRates;
    private static int currentBlockBreakingMultiplier;
    private static int currentDragonPerchTime;
    private static ClientModOptions.Difficulty currentDifficulty;
    private static int currentStrongholdCount;
    private static int currentStrongholdDistance;
    private static int currentStrongholdSpread;
    private static int currentStrongholdPortalRoomCount;
    private static int currentStrongholdLibrariesCount;
    private static int currentAnvilCostLimit;
    private static int currentNetherPortalCooldown;

    /**
     * Used to call the Leaderboards class and initialize it.
     */
    public static void initializeLeaderboards() {}

    /**
     * Determines if the current speedrunner mod settings are eligible for a leaderboard submission.
     */
    public static boolean isEligibleForLeaderboardRuns() {
        return areStructureSpawnRatesEligible() &&
                isDragonPerchTimeEligible() &&
                isBlockBreakingMultiplierEligible() &&
                isDifficultyEligible() &&
                isStrongholdCountEligible() &&
                isStrongholdDistanceEligible() &&
                isStrongholdSpreadEligible() &&
                isStrongholdPortalRoomsEligible() &&
                isStrongholdLibrariesEligible() &&
                isAnvilCostLimitEligible() &&
                isNetherPortalCooldownEligible() &&
                isFasterSpawnersEligible() &&
                isIcarusModeEligible() &&
                isInfiniPearlModeEligible() &&
                isFallDamageEligible() &&
                isKineticDamageEligible() &&
                isAllowCheatsEligible() &&
                isKillGhastOnFireballEligible();
    }

    /**
     * Checks for the specific {@code ineligible option} that needs to be modified in order for the run to be submitted to the leaderboards.
     */
    public static void checkForIneligibleOptions() {
        ineligibleOptions.clear();
        if (!areStructureSpawnRatesEligible()) {
            warnIneligible("Structure Spawn Rates");
            addIneligible("structure_spawn_rates", withFormatting(options.structureSpawnRates.toString(), options.structureSpawnRates.everywhere() ? Formatting.AQUA : options.structureSpawnRates.ddefault() || options.structureSpawnRates.equals(ModOptions.StructureSpawnRate.DISABLED) ? Formatting.WHITE : options.structureSpawnRates.rare() ? Formatting.LIGHT_PURPLE : options.structureSpawnRates.veryRare() ? Formatting.DARK_PURPLE : Formatting.RED, Formatting.BOLD));
        }

        if (!isDragonPerchTimeEligible()) {
            warnIneligible("Dragon Perch Time");
            addIneligible("dragon_perch_time", withFormatting(options.dragonPerchTime, Formatting.RED, Formatting.BOLD));
        }

        if (!isBlockBreakingMultiplierEligible()) {
            warnIneligible("Block Breaking Multiplier");
            addIneligible("block_breaking_multiplier", withFormatting(options.blockBreakingMultiplier, Formatting.RED, Formatting.BOLD));
        }

        if (!isDifficultyEligible()) {
            warnIneligible("Difficulty");
            addIneligible("difficulty", withFormatting(cloptions.difficulty.toString(), Formatting.BOLD));
        }

        if (!isStrongholdCountEligible()) {
            warnIneligible("Stronghold Count");
            addIneligible("stronghold_count", withFormatting(options.strongholdCount, Formatting.RED, Formatting.BOLD));
        }

        if (!isStrongholdDistanceEligible()) {
            warnIneligible("Stronghold Distance");
            addIneligible("stronghold_distance", withFormatting(options.strongholdDistance, Formatting.RED, Formatting.BOLD));
        }

        if (!isStrongholdSpreadEligible()) {
            warnIneligible("Stronghold Spread");
            addIneligible("stronghold_spread", withFormatting(options.strongholdSpread, Formatting.RED, Formatting.BOLD));
        }

        if (!isStrongholdPortalRoomsEligible()) {
            warnIneligible("Stronghold Portal Room Count");
            addIneligible("stronghold_portal_room_count", withFormatting(options.strongholdPortalRoomCount, Formatting.RED, Formatting.BOLD));
        }

        if (!isStrongholdLibrariesEligible()) {
            warnIneligible("Stronghold Library Count");
            addIneligible("stronghold_library_count", withFormatting(options.strongholdLibraryCount, Formatting.RED, Formatting.BOLD));
        }

        if (!isAnvilCostLimitEligible()) {
            warnIneligible("Anvil Cost Limit");
            addIneligible("anvil_cost_limit", withFormatting(options.anvilCostLimit, Formatting.RED, Formatting.BOLD));
        }

        if (!isNetherPortalCooldownEligible()) {
            warnIneligible("Nether Portal Cooldown");
            addIneligible("nether_portal_cooldown", withFormatting(options.netherPortalDelay, Formatting.RED, Formatting.BOLD));
        }

        if (!isIcarusModeEligible()) {
            warnIneligible("iCarus Mode");
            addIneligible("icarus_mode");
        }

        if (!isInfiniPearlModeEligible()) {
            warnIneligible("Infini Pearl Mode");
            addIneligible("infini_pearl_mode");
        }

        if (!isFallDamageEligible()) {
            warnIneligible("Fall Damage");
            addIneligible("fall_damage");
        }

        if (!isKineticDamageEligible()) {
            warnIneligible("Kinetic Damage");
            addIneligible("kinetic_damage");
        }

        if (!isAllowCheatsEligible()) {
            warnIneligible("Allow Cheats");
            addIneligible("allow_cheats");
        }

        if (!isKillGhastOnFireballEligible()) {
            warnIneligible("Kill Ghast On Fireball");
            addIneligible("kill_ghast_on_fireball");
        }

        if (!isFasterSpawnersEligible()) {
            warnIneligible("Faster Spawners");
            addIneligible("faster_spawners");
        }
    }

    /**
     * Gets the current options when opening the mod options screen to check for changes to ineligibility.
     */
    public static void getCurrentOptions() {
        currentStructureSpawnRates = options.structureSpawnRates;
        currentBlockBreakingMultiplier = options.blockBreakingMultiplier;
        currentDragonPerchTime = options.dragonPerchTime;
        currentDifficulty = cloptions.difficulty;
        currentStrongholdCount = options.strongholdCount;
        currentStrongholdDistance = options.strongholdDistance;
        currentStrongholdSpread = options.strongholdSpread;
        currentStrongholdPortalRoomCount = options.strongholdPortalRoomCount;
        currentStrongholdLibrariesCount = options.strongholdLibraryCount;
        currentAnvilCostLimit = options.anvilCostLimit;
        currentNetherPortalCooldown = options.netherPortalDelay;
    }

    /**
     * Gets the current state of the leaderboards mode option.
     */
    public static void getCurrentLeaderboardsMode() {
        currentLeaderboardsMode = options.leaderboardsMode;
    }

    /**
     * Checks to see if the leaderboard mode option was changed.
     */
    public static boolean wasLeaderboardsModeChanged() {
        return currentLeaderboardsMode != options.leaderboardsMode;
    }

    /**
     * Checks if there were no options that go against the leaderboards criteria were changed after closing the screen.
     */
    public static boolean noOptionsWereChanged() {
        return currentStructureSpawnRates == options.structureSpawnRates &&
                currentDragonPerchTime == options.dragonPerchTime &&
                currentBlockBreakingMultiplier == options.blockBreakingMultiplier &&
                currentDifficulty == cloptions.difficulty &&
                currentStrongholdCount == options.strongholdCount &&
                currentStrongholdDistance == options.strongholdDistance &&
                currentStrongholdSpread == options.strongholdSpread &&
                currentStrongholdPortalRoomCount == options.strongholdPortalRoomCount &&
                currentStrongholdLibrariesCount == options.strongholdLibraryCount &&
                currentAnvilCostLimit == options.anvilCostLimit &&
                currentNetherPortalCooldown == options.netherPortalDelay;
    }

    /**
     * Fixes the speedrunner mod options to be eligible for leaderboard submissions.
     */
    public static void fixOptions() {
        if (!areStructureSpawnRatesEligible()) {
            options.structureSpawnRates = ModOptions.StructureSpawnRate.COMMON;
        }

        if (!isDragonPerchTimeEligible()) {
            options.dragonPerchTime = 8;
        }

        if (!isBlockBreakingMultiplierEligible()) {
            options.blockBreakingMultiplier = 1;
        }

        if (!isDifficultyEligible()) {
            cloptions.difficulty = ClientModOptions.Difficulty.EASY;
        }

        if (!isStrongholdCountEligible()) {
            options.strongholdCount = 128;
        }

        if (!isStrongholdDistanceEligible()) {
            options.strongholdDistance = 4;
        }

        if (!isStrongholdSpreadEligible()) {
            options.strongholdSpread = 3;
        }

        if (!isStrongholdPortalRoomsEligible()) {
            options.strongholdPortalRoomCount = 3;
        }

        if (!isStrongholdLibrariesEligible()) {
            options.strongholdLibraryCount = 2;
        }

        if (!isAnvilCostLimitEligible()) {
            options.anvilCostLimit = 10;
        }

        if (!isNetherPortalCooldownEligible()) {
            options.netherPortalDelay = 2;
        }

        if (!isFasterSpawnersEligible()) {
            options.fasterSpawners = true;
        }

        fixBooleanOptions();
    }

    /**
     * Reverts the changes made to the leaderboards ineligible options, to allow the player to continue submitting runs.
     */
    public static void revertChanges() {
        if (wasLeaderboardsModeChanged()) {
            options.leaderboardsMode = true;
        }

        if (!areStructureSpawnRatesEligible()) {
            options.structureSpawnRates = currentStructureSpawnRates;
        }

        if (!isBlockBreakingMultiplierEligible()) {
            options.blockBreakingMultiplier = currentBlockBreakingMultiplier;
        }

        if (!isDragonPerchTimeEligible()) {
            options.dragonPerchTime = currentDragonPerchTime;
        }

        if (!isDifficultyEligible()) {
            cloptions.difficulty = currentDifficulty;
        }

        if (!isStrongholdCountEligible()) {
            options.strongholdCount = currentStrongholdCount;
        }

        if (!isStrongholdDistanceEligible()) {
            options.strongholdDistance = currentStrongholdDistance;
        }

        if (!isStrongholdSpreadEligible()) {
            options.strongholdSpread = currentStrongholdSpread;
        }

        if (!isStrongholdPortalRoomsEligible()) {
            options.strongholdPortalRoomCount = currentStrongholdPortalRoomCount;
        }

        if (!isStrongholdLibrariesEligible()) {
            options.strongholdLibraryCount = currentStrongholdLibrariesCount;
        }

        if (!isAnvilCostLimitEligible()) {
            options.anvilCostLimit = currentAnvilCostLimit;
        }

        if (!isNetherPortalCooldownEligible()) {
            options.netherPortalDelay = currentNetherPortalCooldown;
        }

        fixBooleanOptions();
    }

    /**
     * Reverts/fixes all boolean options for leaderboard eligibility.
     */
    private static void fixBooleanOptions() {
        if (!isIcarusModeEligible()) {
            options.iCarusMode = false;
        }

        if (!isInfiniPearlModeEligible()) {
            options.infiniPearlMode = false;
        }

        if (!isFallDamageEligible()) {
            options.fallDamage = true;
        }

        if (!isKineticDamageEligible()) {
            options.kineticDamage = true;
        }

        if (!isAllowCheatsEligible()) {
            cloptions.allowCheats = false;
        }

        if (!isKillGhastOnFireballEligible()) {
            options.killGhastOnFireball = false;
        }
    }

    /**
     * Adds the ineligible option to a translation key, which then gets displayed on the {@code Ineligible Options screen.}
     */
    private static void addIneligible(String translation, Object... args) {
        Text baseText = Text.translatable("speedrunnermod.leaderboards.ineligible_options." + translation, args);
        if (!ineligibleOptions.contains(baseText)) {
            ineligibleOptions.add(baseText);
        }
    }

    /**
     * Gets the translation key or value of an option and returns it with a new formatting.
     */
    private static Text withFormatting(String optionString, Formatting... formatting) {
        return Text.translatable(optionString).formatted(formatting);
    }

    /**
     * Gets the translation key or value of an option and returns it with a new formatting, for integer values.
     */
    private static Text withFormatting(int intOption, Formatting... formatting) {
        return Text.translatable(String.valueOf(intOption)).formatted(formatting);
    }

    /**
     * Disables leaderboards mode.
     */
    public static void disableLeaderboardsMode() {
        info("Disabling leaderboards mode and closing game. Re-launch to apply changes.");
        options().main.leaderboardsMode = false;
        saveDedicatedServerChanges();
        if (!isEnvironmentTypeServer()) {
            saveClientChanges();
        }
    }

    /**
     * Sends a warning log/message to console that the user ignored the ineligible options, and cannot submit any runs until turned back on.
     */
    public static void sendIgnoreWarning() {
        warn("Proceeding. Because you chose to ignore, you will not be able to submit any speedruns to the leaderboards, unless you re-enable the leaderboards mode and restart your game.");
    }

    /**
     * Sends a warning log to console, displaying the specific ineligible option that needs to be changed.
     */
    private static void warnIneligible(String optionName) {
        warn("Current Option \"" + optionName + "\" is ineligible for a leaderboard submission.");
    }

    private static boolean areStructureSpawnRatesEligible() {
        return options.structureSpawnRates.veryCommon() ||
                options.structureSpawnRates.common() ||
                options.structureSpawnRates.normal();
    }

    private static boolean isBlockBreakingMultiplierEligible() {
        return options.blockBreakingMultiplier == 1;
    }

    private static boolean isDragonPerchTimeEligible() {
        return options().inBounds(options.dragonPerchTime, 8, 90);
    }

    private static boolean isDifficultyEligible() {
        return clientOptions().client.difficulty != ClientModOptions.Difficulty.PEACEFUL;
    }

    private static boolean isStrongholdCountEligible() {
        return options().inBounds(options.strongholdCount, 128, 156);
    }

    private static boolean isStrongholdDistanceEligible() {
        return options().inBounds(options.strongholdDistance, 3, 18);
    }

    private static boolean isStrongholdSpreadEligible() {
        return options().inBounds(options.strongholdSpread, 2, 12);
    }

    private static boolean isStrongholdPortalRoomsEligible() {
        return options().inBounds(options.strongholdPortalRoomCount, 1, 3);
    }

    private static boolean isStrongholdLibrariesEligible() {
        return options().inBounds(options.strongholdLibraryCount, 1, 4);
    }

    private static boolean isAnvilCostLimitEligible() {
        return options().inBounds(options.anvilCostLimit, 10);
    }

    private static boolean isNetherPortalCooldownEligible() {
        return options().inBounds(options.netherPortalDelay, 1, 20);
    }

    private static boolean isFasterSpawnersEligible() {
        return options.fasterSpawners;
    }

    private static boolean isIcarusModeEligible() {
        return !options.iCarusMode;
    }

    private static boolean isInfiniPearlModeEligible() {
        return !options.infiniPearlMode;
    }

    private static boolean isFallDamageEligible() {
        return options.fallDamage;
    }

    private static boolean isKineticDamageEligible() {
        return options.kineticDamage;
    }

    private static boolean isAllowCheatsEligible() {
        return !cloptions.allowCheats;
    }

    private static boolean isKillGhastOnFireballEligible() {
        return !options.killGhastOnFireball;
    }
}