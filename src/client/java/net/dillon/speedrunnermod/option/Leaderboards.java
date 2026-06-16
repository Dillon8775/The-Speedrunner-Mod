package net.dillon.speedrunnermod.option;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

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
public class Leaderboards {
    public static List<Component> ineligibleOptions = new ArrayList<>();
    private static final General general = options().general;
    private static final WorldGen worldGen = options().worldGen;
    private static final ClientModOptions.Client cloptions = clientOptions().client;
    private static boolean currentLeaderboardsMode;
    private static StructureSpawnRate currentStructureSpawnRates;
    private static int currentBlockBreakingMultiplier;
    private static int currentDragonPerchTime;
    private static Difficulty currentDifficulty;
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
            addIneligible("structure_spawn_rates", withFormatting(worldGen.structureSpawnRates.toString(), isSsrEverywhere() ? ChatFormatting.AQUA : isSsrDefault() || isSsrDefault() ? ChatFormatting.WHITE : isSsrRare() ? ChatFormatting.LIGHT_PURPLE : isSsrVeryRare() ? ChatFormatting.DARK_PURPLE : ChatFormatting.RED, ChatFormatting.BOLD));
        }

        if (!isDragonPerchTimeEligible()) {
            warnIneligible("Dragon Perch Time");
            addIneligible("dragon_perch_time", withFormatting(general.dragonPerchTime.getCurrentValue(), ChatFormatting.RED, ChatFormatting.BOLD));
        }

        if (!isBlockBreakingMultiplierEligible()) {
            warnIneligible("Block Breaking Multiplier");
            addIneligible("block_breaking_multiplier", withFormatting(general.blockBreakingMultiplier.getCurrentValue(), ChatFormatting.RED, ChatFormatting.BOLD));
        }

        if (!isDifficultyEligible()) {
            warnIneligible("Difficulty");
            addIneligible("difficulty", withFormatting(cloptions.difficulty.toString(), ChatFormatting.BOLD));
        }

        if (!isStrongholdCountEligible()) {
            warnIneligible("Stronghold Count");
            addIneligible("stronghold_count", withFormatting(worldGen.strongholdCount.getCurrentValue(), ChatFormatting.RED, ChatFormatting.BOLD));
        }

        if (!isStrongholdDistanceEligible()) {
            warnIneligible("Stronghold Distance");
            addIneligible("stronghold_distance", withFormatting(worldGen.strongholdDistance.getCurrentValue(), ChatFormatting.RED, ChatFormatting.BOLD));
        }

        if (!isStrongholdSpreadEligible()) {
            warnIneligible("Stronghold Spread");
            addIneligible("stronghold_spread", withFormatting(worldGen.strongholdSpread.getCurrentValue(), ChatFormatting.RED, ChatFormatting.BOLD));
        }

        if (!isStrongholdPortalRoomsEligible()) {
            warnIneligible("Stronghold Portal Room Count");
            addIneligible("stronghold_portal_room_count", withFormatting(worldGen.strongholdPortalRoomCount.getCurrentValue(), ChatFormatting.RED, ChatFormatting.BOLD));
        }

        if (!isStrongholdLibrariesEligible()) {
            warnIneligible("Stronghold Library Count");
            addIneligible("stronghold_library_count", withFormatting(worldGen.strongholdLibraryCount.getCurrentValue(), ChatFormatting.RED, ChatFormatting.BOLD));
        }

        if (!isAnvilCostLimitEligible()) {
            warnIneligible("Anvil Cost Limit");
            addIneligible("anvil_cost_limit", withFormatting(general.anvilCostLimit.getCurrentValue(), ChatFormatting.RED, ChatFormatting.BOLD));
        }

        if (!isNetherPortalCooldownEligible()) {
            warnIneligible("Nether Portal Cooldown");
            addIneligible("nether_portal_cooldown", withFormatting(worldGen.netherPortalDelay.getCurrentValue(), ChatFormatting.RED, ChatFormatting.BOLD));
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
        currentStructureSpawnRates = worldGen.structureSpawnRates.getCurrentValue();
        currentBlockBreakingMultiplier = general.blockBreakingMultiplier.getCurrentValue();
        currentDragonPerchTime = general.dragonPerchTime.getCurrentValue();
        currentDifficulty = cloptions.difficulty.getCurrentValue();
        currentStrongholdCount = worldGen.strongholdCount.getCurrentValue();
        currentStrongholdDistance = worldGen.strongholdDistance.getCurrentValue();
        currentStrongholdSpread = worldGen.strongholdSpread.getCurrentValue();
        currentStrongholdPortalRoomCount = worldGen.strongholdPortalRoomCount.getCurrentValue();
        currentStrongholdLibrariesCount = worldGen.strongholdLibraryCount.getCurrentValue();
        currentAnvilCostLimit = general.anvilCostLimit.getCurrentValue();
        currentNetherPortalCooldown = worldGen.netherPortalDelay.getCurrentValue();
    }

    /**
     * Gets the current state of the leaderboards mode option.
     */
    public static void getCurrentLeaderboardsMode() {
        currentLeaderboardsMode = general.leaderboardsMode.getCurrentValue();
    }

    /**
     * Checks to see if the leaderboard mode option was changed.
     */
    public static boolean wasLeaderboardsModeChanged() {
        return currentLeaderboardsMode != general.leaderboardsMode.getCurrentValue();
    }

    /**
     * Checks if there were no options that go against the leaderboards criteria were changed after closing the screen.
     */
    public static boolean noOptionsWereChanged() {
        return currentStructureSpawnRates == worldGen.structureSpawnRates.getCurrentValue() &&
                currentDragonPerchTime == general.dragonPerchTime.getCurrentValue() &&
                currentBlockBreakingMultiplier == general.blockBreakingMultiplier.getCurrentValue() &&
                currentDifficulty == cloptions.difficulty.getCurrentValue() &&
                currentStrongholdCount == worldGen.strongholdCount.getCurrentValue() &&
                currentStrongholdDistance == worldGen.strongholdDistance.getCurrentValue() &&
                currentStrongholdSpread == worldGen.strongholdSpread.getCurrentValue() &&
                currentStrongholdPortalRoomCount == worldGen.strongholdPortalRoomCount.getCurrentValue() &&
                currentStrongholdLibrariesCount == worldGen.strongholdLibraryCount.getCurrentValue() &&
                currentAnvilCostLimit == general.anvilCostLimit.getCurrentValue() &&
                currentNetherPortalCooldown == worldGen.netherPortalDelay.getCurrentValue();
    }

    /**
     * Fixes the speedrunner mod options to be eligible for leaderboard submissions.
     */
    public static void fixOptions() {
        if (!areStructureSpawnRatesEligible()) {
            worldGen.structureSpawnRates.reset();
        }

        if (!isDragonPerchTimeEligible()) {
            general.dragonPerchTime.reset();
        }

        if (!isBlockBreakingMultiplierEligible()) {
            general.blockBreakingMultiplier.reset();
        }

        if (!isDifficultyEligible()) {
            cloptions.difficulty.reset();
        }

        if (!isStrongholdCountEligible()) {
            worldGen.strongholdCount.reset();
        }

        if (!isStrongholdDistanceEligible()) {
            worldGen.strongholdDistance.reset();
        }

        if (!isStrongholdSpreadEligible()) {
            worldGen.strongholdSpread.reset();
        }

        if (!isStrongholdPortalRoomsEligible()) {
            worldGen.strongholdPortalRoomCount.reset();
        }

        if (!isStrongholdLibrariesEligible()) {
            worldGen.strongholdLibraryCount.reset();
        }

        if (!isAnvilCostLimitEligible()) {
            general.anvilCostLimit.reset();
        }

        if (!isNetherPortalCooldownEligible()) {
            worldGen.netherPortalDelay.reset();
        }

        if (!isFasterSpawnersEligible()) {
            general.fasterSpawners.reset();
        }

        fixBooleanOptions();
    }

    /**
     * Reverts the changes made to the leaderboards ineligible options, to allow the player to continue submitting runs.
     */
    public static void revertChanges() {
        if (wasLeaderboardsModeChanged()) {
            general.leaderboardsMode.set(true);
        }

        if (!areStructureSpawnRatesEligible()) {
            worldGen.structureSpawnRates.set(currentStructureSpawnRates);
        }

        if (!isBlockBreakingMultiplierEligible()) {
            general.blockBreakingMultiplier.set(currentBlockBreakingMultiplier);
        }

        if (!isDragonPerchTimeEligible()) {
            general.dragonPerchTime.set(currentDragonPerchTime);
        }

        if (!isDifficultyEligible()) {
            cloptions.difficulty.set(currentDifficulty);
        }

        if (!isStrongholdCountEligible()) {
            worldGen.strongholdCount.set(currentStrongholdCount);
        }

        if (!isStrongholdDistanceEligible()) {
            worldGen.strongholdDistance.set(currentStrongholdDistance);
        }

        if (!isStrongholdSpreadEligible()) {
            worldGen.strongholdSpread.set(currentStrongholdSpread);
        }

        if (!isStrongholdPortalRoomsEligible()) {
            worldGen.strongholdPortalRoomCount.set(currentStrongholdPortalRoomCount);
        }

        if (!isStrongholdLibrariesEligible()) {
            worldGen.strongholdLibraryCount.set(currentStrongholdLibrariesCount);
        }

        if (!isAnvilCostLimitEligible()) {
            general.anvilCostLimit.set(currentAnvilCostLimit);
        }

        if (!isNetherPortalCooldownEligible()) {
            worldGen.netherPortalDelay.set(currentAnvilCostLimit);
        }

        fixBooleanOptions();
    }

    /**
     * Reverts/fixes all boolean options for leaderboard eligibility.
     */
    private static void fixBooleanOptions() {
        if (!isIcarusModeEligible()) {
            general.iCarusMode.reset();
        }

        if (!isInfiniPearlModeEligible()) {
            general.infiniPearlMode.reset();
        }

        if (!isFallDamageEligible()) {
            general.fallDamage.reset();
        }

        if (!isKineticDamageEligible()) {
            general.kineticDamage.reset();
        }

        if (!isAllowCheatsEligible()) {
            cloptions.allowCommands.reset();
        }

        if (!isKillGhastOnFireballEligible()) {
            general.killGhastOnFireball.reset();
        }
    }

    /**
     * Adds the ineligible option to a translation key, which then gets displayed on the {@code Ineligible Options screen.}
     */
    private static void addIneligible(String translation, Object... args) {
        Component baseText = Component.translatable("speedrunnermod.leaderboards.ineligible_options." + translation, args);
        if (!ineligibleOptions.contains(baseText)) {
            ineligibleOptions.add(baseText);
        }
    }

    /**
     * Gets the translation key or value of an option and returns it with a new formatting.
     */
    private static Component withFormatting(String optionString, ChatFormatting... formatting) {
        return Component.translatable(optionString).withStyle(formatting);
    }

    /**
     * Gets the translation key or value of an option and returns it with a new formatting, for integer values.
     */
    private static Component withFormatting(int intOption, ChatFormatting... formatting) {
        return Component.translatable(String.valueOf(intOption)).withStyle(formatting);
    }

    /**
     * Disables leaderboards mode.
     */
    public static void disableLeaderboardsMode() {
        info("Disabling leaderboards mode and closing game. Re-launch to apply changes.");
        options().general.leaderboardsMode.set(false);
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
        return general.blockBreakingMultiplier.getCurrentValue() == 1;
    }

    private static boolean isDragonPerchTimeEligible() {
        return isInBounds(general.dragonPerchTime.getCurrentValue(), 8, 90);
    }

    private static boolean isDifficultyEligible() {
        return clientOptions().client.difficulty.getCurrentValue() != Difficulty.PEACEFUL;
    }

    private static boolean isStrongholdCountEligible() {
        return isInBounds(worldGen.strongholdCount.getCurrentValue(), 128, 156);
    }

    private static boolean isStrongholdDistanceEligible() {
        return isInBounds(worldGen.strongholdDistance.getCurrentValue(), 3, 18);
    }

    private static boolean isStrongholdSpreadEligible() {
        return isInBounds(worldGen.strongholdSpread.getCurrentValue(), 2, 12);
    }

    private static boolean isStrongholdPortalRoomsEligible() {
        return isInBounds(worldGen.strongholdPortalRoomCount.getCurrentValue(), 1, 3);
    }

    private static boolean isStrongholdLibrariesEligible() {
        return isInBounds(worldGen.strongholdLibraryCount.getCurrentValue(), 1, 4);
    }

    private static boolean isAnvilCostLimitEligible() {
        return isInBounds(general.anvilCostLimit.getCurrentValue(), 10);
    }

    private static boolean isNetherPortalCooldownEligible() {
        return isInBounds(worldGen.netherPortalDelay.getCurrentValue(), 1, 20);
    }

    private static boolean isFasterSpawnersEligible() {
        return general.fasterSpawners.getCurrentValue();
    }

    private static boolean isIcarusModeEligible() {
        return !general.iCarusMode.getCurrentValue();
    }

    private static boolean isInfiniPearlModeEligible() {
        return !general.infiniPearlMode.getCurrentValue();
    }

    private static boolean isFallDamageEligible() {
        return general.fallDamage.getCurrentValue();
    }

    private static boolean isKineticDamageEligible() {
        return general.kineticDamage.getCurrentValue();
    }

    private static boolean isAllowCheatsEligible() {
        return !cloptions.allowCommands.getCurrentValue();
    }

    private static boolean isKillGhastOnFireballEligible() {
        return !general.killGhastOnFireball.getCurrentValue();
    }
}