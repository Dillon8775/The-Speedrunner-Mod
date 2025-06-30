package net.dillon.speedrunnermod.server;

import net.dillon.speedrunnermod.tutorial.TutorialStep;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.*;

/**
 * Stores server-synced client options.
 */
@ChatGPT(Credit.FULL_CREDIT)
public class ServerSyncedClientOptions {
    private static final Map<UUID, Boolean> ACTIONBAR_PREFS = new HashMap<>();
    public static final Map<UUID, Set<TutorialStep>> COMPLETED_STEPS = new HashMap<>();

    public static void setActionbarPref(UUID playerUuid, boolean showInActionbar) {
        ACTIONBAR_PREFS.put(playerUuid, showInActionbar);
    }

    public static boolean shouldShowInActionbar(UUID playerUuid) {
        return ACTIONBAR_PREFS.getOrDefault(playerUuid, true);
    }

    public static void clearActionbarPrefs(UUID playerUuid) {
        ACTIONBAR_PREFS.remove(playerUuid);
    }

    public static void completeTutorialStepC2S(ServerPlayerEntity player, TutorialStep step) {
        COMPLETED_STEPS.computeIfAbsent(player.getUuid(), k -> new HashSet<>()).add(step);
    }

    public static boolean hasCompletedStep(ServerPlayerEntity player, TutorialStep step) {
        return COMPLETED_STEPS.getOrDefault(player.getUuid(), Set.of()).contains(step);
    }
}