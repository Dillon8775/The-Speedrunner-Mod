package net.dillon.speedrunnermod.server;

import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.tutorial.TutorialStep;
import net.dillon.speedrunnermod.util.AI;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.*;

/**
 * Stores server-synced client options.
 */
@AI
public class ServerStorage {
    private static final Map<UUID, Boolean> ACTIONBAR_PREFS = new HashMap<>();
    private static final Map<UUID, Integer> ICARUS_FIREWORK_SLOT = new HashMap<>();
    private static final Map<UUID, Integer> INFINI_PEARL_SLOT = new HashMap<>();
    private static final Map<UUID, Set<TutorialStep>> COMPLETED_STEPS = new HashMap<>();
    private static final Map<UUID, Boolean> TUTORIAL_MODE_ENABLED = new HashMap<>();
    private static final Map<String, ModOptions> pendingRequests = new HashMap<>();

    /**
     * Sets the {@code action bar preference} for a player.
     */
    public static void setActionbarPref(UUID playerUuid, boolean showInActionbar) {
        ACTIONBAR_PREFS.put(playerUuid, showInActionbar);
    }

    /**
     * Gets the {@code action bar preference} for a player.
     */
    public static boolean shouldShowInActionbar(UUID playerUuid) {
        return ACTIONBAR_PREFS.getOrDefault(playerUuid, true);
    }

    /**
     * Clears all {@code client preferences} for all players on server-side.
     */
    public static void clearPrefs(UUID playerUuid) {
        ACTIONBAR_PREFS.remove(playerUuid);
        ICARUS_FIREWORK_SLOT.remove(playerUuid);
        INFINI_PEARL_SLOT.remove(playerUuid);
    }

    /**
     * Sets the {@code iCarus firework slot} for a player.
     */
    public static void setIcarusFireworkSlot(UUID playerUuid, int icarusFireworkSlot) {
        ICARUS_FIREWORK_SLOT.put(playerUuid, icarusFireworkSlot);
    }

    /**
     * Gets the {@code iCarus firework slot} for a player.
     */
    public static int getIcarusFireworkSlot(UUID playerUuid) {
        return ICARUS_FIREWORK_SLOT.getOrDefault(playerUuid, 1);
    }

    /**
     * Sets the {@code infini pearl slot} for a player.
     */
    public static void setInfiniPearlSlot(UUID playerUuid, int infiniPearlSlot) {
        INFINI_PEARL_SLOT.put(playerUuid, infiniPearlSlot);
    }

    /**
     * Gets the {@code infini pearl slot} for a player.
     */
    public static int getInfiniPearlSlot(UUID playerUuid) {
        return INFINI_PEARL_SLOT.getOrDefault(playerUuid, 1);
    }

    /**
     * Completes a tutorial step for {@code player.}
     */
    public static void completeTutorialStepC2S(ServerPlayerEntity player, TutorialStep step) {
        COMPLETED_STEPS.computeIfAbsent(player.getUuid(), k -> new HashSet<>()).add(step);
    }

    /**
     * Sets tutorial mode to be enabled for a player.
     */
    public static void setTutorialModeForPlayer(UUID uuid, boolean tutorialMode) {
        TUTORIAL_MODE_ENABLED.put(uuid, tutorialMode);
    }

    /**
     * @return {@code true} if tutorial mode is enabled for a player.
     */
    public static boolean isTutorialModeEnabledForPlayer(UUID uuid) {
        return TUTORIAL_MODE_ENABLED.getOrDefault(uuid, false);
    }

    /**
     * @return {@code true} if {@code player} has completed {@code tutorial step.}
     */
    public static boolean hasCompletedStep(ServerPlayerEntity player, TutorialStep step) {
        if (!isTutorialModeEnabledForPlayer(player.getUuid())) {
            return false;
        }
        return COMPLETED_STEPS.getOrDefault(player.getUuid(), Set.of()).contains(step);
    }

    /**
     * Stores a {@code syncoption request} (with the player name and the requesting player's options).
     */
    public static void storePendingSyncRequest(String player, ModOptions options) {
        pendingRequests.put(player, options);
    }

    /**
     * Gets the {@code pending sync request} for a player.
     */
    public static ModOptions getPendingSyncRequest(String player) {
        return pendingRequests.get(player);
    }

    /**
     * @return {@code true} if a player's syncoptions request exists.
     */
    public static boolean hasPendingSyncRequest(String player) {
        return pendingRequests.containsKey(player);
    }

    /**
     * Removes a {@code syncoptions request.}
     */
    public static void removePendingSyncRequest(String player) {
        pendingRequests.remove(player);
    }
}