package net.dillon.speedrunnermod.server;

import net.dillon.speedrunnermod.tutorial.TutorialStep;
import net.dillon.speedrunnermod.util.AI;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.*;

/**
 * Stores server-synced client options.
 */
@AI
public class ServerSyncedClientOptions {
    private static final Map<UUID, Boolean> ACTIONBAR_PREFS = new HashMap<>();
    private static final Map<UUID, Integer> ICARUS_FIREWORK_SLOT = new HashMap<>();
    private static final Map<UUID, Integer> INFINI_PEARL_SLOT = new HashMap<>();
    private static final Map<UUID, Set<TutorialStep>> COMPLETED_STEPS = new HashMap<>();

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
     * @return {@code true} if {@code player} has completed {@code tutorial step.}
     */
    public static boolean hasCompletedStep(ServerPlayerEntity player, TutorialStep step) {
        return COMPLETED_STEPS.getOrDefault(player.getUuid(), Set.of()).contains(step);
    }
}