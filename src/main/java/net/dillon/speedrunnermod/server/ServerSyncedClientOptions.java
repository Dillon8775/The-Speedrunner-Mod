package net.dillon.speedrunnermod.server;

import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Stores server-synced client options.
 */
@ChatGPT(Credit.FULL_CREDIT)
public class ServerSyncedClientOptions {
    private static final Map<UUID, Boolean> ACTIONBAR_PREFS = new HashMap<>();

    public static void setActionbarPreference(UUID playerUuid, boolean showInActionbar) {
        ACTIONBAR_PREFS.put(playerUuid, showInActionbar);
    }

    public static boolean shouldShowInActionbar(UUID playerUuid) {
        return ACTIONBAR_PREFS.getOrDefault(playerUuid, true);
    }

    public static void clear(UUID playerUuid) {
        ACTIONBAR_PREFS.remove(playerUuid);
    }
}