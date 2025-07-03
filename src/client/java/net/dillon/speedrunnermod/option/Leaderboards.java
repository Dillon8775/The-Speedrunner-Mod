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
import static net.dillon.speedrunnermod.option.ModOptions.*;

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
            addIneligible("structure_spawn_rates", withFormatting(options.structureSpawnRates.toString(), isSsrEverywhere() ? Formatting.AQUA : isSsrDefault() || isSsrDefault() ? Formatting.WHITE : isSsrRare() ? Formatting.LIGHT_PURPLE : isSsrVeryRare() ? Formatting.DARK_PURPLE : Formatting.RED, Formatting.BOLD));
        }

        if (!isDragonPerchTimeEligible()) {
            warnIneligible("Dragon Perch Time");
            addIneligible("dragon_perch_time", withFormatting(options.dragonPerchTime.getCurrentValue(), Formatting.RED, Formatting.BOLD));
        }

        if (!isBlockBreakingMultiplierEligible()) {
            warnIneligible("Block Breaking Multiplier");
            addIneligible("block_breaking_multiplier", withFormatting(options.blockBreakingMultiplier.getCurrentValue(), Formatting.RED, Formatting.BOLD));
        }

        if (!isDifficultyEligible()) {
            warnIneligible("Difficulty");
            addIneligible("difficulty", withFormatting(cloptions.difficulty.toString(), Formatting.BOLD));
        }

        if (!isStrongholdCountEligible()) {
            warnIneligible("Stronghold Count");
            addIneligible("stronghold_count", withFormatting(options.strongholdCount.getCurrentValue(), Formatting.RED, Formatting.BOLD));
        }

        if (!isStrongholdDistanceEligible()) {
            warnIneligible("Stronghold Distance");
            addIneligible("stronghold_distance", withFormatting(options.strongholdDistance.getCurrentValue(), Formatting.RED, Formatting.BOLD));
        }

        if (!isStrongholdSpreadEligible()) {
            warnIneligible("Stronghold Spread");
            addIneligible("stronghold_spread", withFormatting(options.strongholdSpread.getCurrentValue(), Formatting.RED, Formatting.BOLD));
        }

        if (!isStrongholdPortalRoomsEligible()) {
            warnIneligible("Stronghold Portal Room Count");
            addIneligible("stronghold_portal_room_count", withFormatting(options.strongholdPortalRoomCount.getCurrentValue(), Formatting.RED, Formatting.BOLD));
        }

        if (!isStrongholdLibrariesEligible()) {
            warnIneligible("Stronghold Library Count");
            addIneligible("stronghold_library_count", withFormatting(options.strongholdLibraryCount.getCurrentValue(), Formatting.RED, Formatting.BOLD));
        }

        if (!isAnvilCostLimitEligible()) {
            warnIneligible("Anvil Cost Limit");
            addIneligible("anvil_cost_limit", withFormatting(options.anvilCostLimit.getCurrentValue(), Formatting.RED, Formatting.BOLD));
        }

        if (!isNetherPortalCooldownEligible()) {
            warnIneligible("Nether Portal Cooldown");
            addIneligible("nether_portal_cooldown", withFormatting(options.netherPortalDelay.getCurrentValue(), Formatting.RED, Formatting.BOLD));
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
        currentStructureSpawnRates = options.structureSpawnRates.getCurrentValue();
        currentBlockBreakingMultiplier = options.blockBreakingMultiplier.getCurrentValue();
        currentDragonPerchTime = options.dragonPerchTime.getCurrentValue();
        currentDifficulty = cloptions.difficulty.getCurrentValue();
        currentStrongholdCount = options.strongholdCount.getCurrentValue();
        currentStrongholdDistance = options.strongholdDistance.getCurrentValue();
        currentStrongholdSpread = options.strongholdSpread.getCurrentValue();
        currentStrongholdPortalRoomCount = options.strongholdPortalRoomCount.getCurrentValue();
        currentStrongholdLibrariesCount = options.strongholdLibraryCount.getCurrentValue();
        currentAnvilCostLimit = options.anvilCostLimit.getCurrentValue();
        currentNetherPortalCooldown = options.netherPortalDelay.getCurrentValue();
    }

    /**
     * Gets the current state of the leaderboards mode option.
     */
    public static void getCurrentLeaderboardsMode() {
        currentLeaderboardsMode = options.leaderboardsMode.getCurrentValue();
    }

    /**
     * Checks to see if the leaderboard mode option was changed.
     */
    public static boolean wasLeaderboardsModeChanged() {
        return currentLeaderboardsMode != options.leaderboardsMode.getCurrentValue();
    }

    /**
     * Checks if there were no options that go against the leaderboards criteria were changed after closing the screen.
     */
    public static boolean noOptionsWereChanged() {
        return currentStructureSpawnRates == options.structureSpawnRates.getCurrentValue() &&
                currentDragonPerchTime == options.dragonPerchTime.getCurrentValue() &&
                currentBlockBreakingMultiplier == options.blockBreakingMultiplier.getCurrentValue() &&
                currentDifficulty == cloptions.difficulty.getCurrentValue() &&
                currentStrongholdCount == options.strongholdCount.getCurrentValue() &&
                currentStrongholdDistance == options.strongholdDistance.getCurrentValue() &&
                currentStrongholdSpread == options.strongholdSpread.getCurrentValue() &&
                currentStrongholdPortalRoomCount == options.strongholdPortalRoomCount.getCurrentValue() &&
                currentStrongholdLibrariesCount == options.strongholdLibraryCount.getCurrentValue() &&
                currentAnvilCostLimit == options.anvilCostLimit.getCurrentValue() &&
                currentNetherPortalCooldown == options.netherPortalDelay.getCurrentValue();
    }

    /**
     * Fixes the speedrunner mod options to be eligible for leaderboard submissions.
     */
    public static void fixOptions() {
        if (!areStructureSpawnRatesEligible()) {
            options.structureSpawnRates.reset();
        }

        if (!isDragonPerchTimeEligible()) {
            options.dragonPerchTime.reset();
        }

        if (!isBlockBreakingMultiplierEligible()) {
            options.blockBreakingMultiplier.reset();
        }

        if (!isDifficultyEligible()) {
            cloptions.difficulty.reset();
        }

        if (!isStrongholdCountEligible()) {
            options.strongholdCount.reset();
        }

        if (!isStrongholdDistanceEligible()) {
            options.strongholdDistance.reset();
        }

        if (!isStrongholdSpreadEligible()) {
            options.strongholdSpread.reset();
        }

        if (!isStrongholdPortalRoomsEligible()) {
            options.strongholdPortalRoomCount.reset();
        }

        if (!isStrongholdLibrariesEligible()) {
            options.strongholdLibraryCount.reset();
        }

        if (!isAnvilCostLimitEligible()) {
            options.anvilCostLimit.reset();
        }

        if (!isNetherPortalCooldownEligible()) {
            options.netherPortalDelay.reset();
        }

        if (!isFasterSpawnersEligible()) {
            options.fasterSpawners.reset();
        }

        fixBooleanOptions();
    }

    /**
     * Reverts the changes made to the leaderboards ineligible options, to allow the player to continue submitting runs.
     */
    public static void revertChanges() {
        if (wasLeaderboardsModeChanged()) {
            options.leaderboardsMode.set(true);
        }

        if (!areStructureSpawnRatesEligible()) {
            options.structureSpawnRates.set(currentStructureSpawnRates);
        }

        if (!isBlockBreakingMultiplierEligible()) {
            options.blockBreakingMultiplier.set(currentBlockBreakingMultiplier);
        }

        if (!isDragonPerchTimeEligible()) {
            options.dragonPerchTime.set(currentDragonPerchTime);
        }

        if (!isDifficultyEligible()) {
            cloptions.difficulty.set(currentDifficulty);
        }

        if (!isStrongholdCountEligible()) {
            options.strongholdCount.set(currentStrongholdCount);
        }

        if (!isStrongholdDistanceEligible()) {
            options.strongholdDistance.set(currentStrongholdDistance);
        }

        if (!isStrongholdSpreadEligible()) {
            options.strongholdSpread.set(currentStrongholdSpread);
        }

        if (!isStrongholdPortalRoomsEligible()) {
            options.strongholdPortalRoomCount.set(currentStrongholdPortalRoomCount);
        }

        if (!isStrongholdLibrariesEligible()) {
            options.strongholdLibraryCount.set(currentStrongholdLibrariesCount);
        }

        if (!isAnvilCostLimitEligible()) {
            options.anvilCostLimit.set(currentAnvilCostLimit);
        }

        if (!isNetherPortalCooldownEligible()) {
            options.netherPortalDelay.set(currentAnvilCostLimit);
        }

        fixBooleanOptions();
    }

    /**
     * Reverts/fixes all boolean options for leaderboard eligibility.
     */
    private static void fixBooleanOptions() {
        if (!isIcarusModeEligible()) {
            options.iCarusMode.reset();
        }

        if (!isInfiniPearlModeEligible()) {
            options.infiniPearlMode.reset();
        }

        if (!isFallDamageEligible()) {
            options.fallDamage.reset();
        }

        if (!isKineticDamageEligible()) {
            options.kineticDamage.reset();
        }

        if (!isAllowCheatsEligible()) {
            cloptions.allowCheats.reset();
        }

        if (!isKillGhastOnFireballEligible()) {
            options.killGhastOnFireball.reset();
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
        options().main.leaderboardsMode.set(false);
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
        return isSsrVeryCommon() ||
                isSsrCommon() ||
                isSsrNormal();
    }

    private static boolean isBlockBreakingMultiplierEligible() {
        return options.blockBreakingMultiplier.getCurrentValue() == 1;
    }

    private static boolean isDragonPerchTimeEligible() {
        return isInBounds(options.dragonPerchTime.getCurrentValue(), 8, 90);
    }

    private static boolean isDifficultyEligible() {
        return clientOptions().client.difficulty.getCurrentValue() != ClientModOptions.Difficulty.PEACEFUL;
    }

    private static boolean isStrongholdCountEligible() {
        return isInBounds(options.strongholdCount.getCurrentValue(), 128, 156);
    }

    private static boolean isStrongholdDistanceEligible() {
        return isInBounds(options.strongholdDistance.getCurrentValue(), 3, 18);
    }

    private static boolean isStrongholdSpreadEligible() {
        return isInBounds(options.strongholdSpread.getCurrentValue(), 2, 12);
    }

    private static boolean isStrongholdPortalRoomsEligible() {
        return isInBounds(options.strongholdPortalRoomCount.getCurrentValue(), 1, 3);
    }

    private static boolean isStrongholdLibrariesEligible() {
        return isInBounds(options.strongholdLibraryCount.getCurrentValue(), 1, 4);
    }

    private static boolean isAnvilCostLimitEligible() {
        return isInBounds(options.anvilCostLimit.getCurrentValue(), 10);
    }

    private static boolean isNetherPortalCooldownEligible() {
        return isInBounds(options.netherPortalDelay.getCurrentValue(), 1, 20);
    }

    private static boolean isFasterSpawnersEligible() {
        return options.fasterSpawners.getCurrentValue();
    }

    private static boolean isIcarusModeEligible() {
        return !options.iCarusMode.getCurrentValue();
    }

    private static boolean isInfiniPearlModeEligible() {
        return !options.infiniPearlMode.getCurrentValue();
    }

    private static boolean isFallDamageEligible() {
        return options.fallDamage.getCurrentValue();
    }

    private static boolean isKineticDamageEligible() {
        return options.kineticDamage.getCurrentValue();
    }

    private static boolean isAllowCheatsEligible() {
        return !cloptions.allowCheats.getCurrentValue();
    }

    private static boolean isKillGhastOnFireballEligible() {
        return !options.killGhastOnFireball.getCurrentValue();
    }
}