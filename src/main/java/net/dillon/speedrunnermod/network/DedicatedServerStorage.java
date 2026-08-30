package net.dillon.speedrunnermod.network;

import net.dillon.speedrunnermod.option.CommonModOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Stores server-synced client options and cached values.
 */
public class DedicatedServerStorage {
    private static final Map<UUID, Boolean> ACTIONBAR_PREFS = new HashMap<>();
    private static final Map<UUID, Boolean> WARNING_MESSAGES = new HashMap<>();
    private static final Map<UUID, Integer> ICARUS_FIREWORK_SLOT = new HashMap<>();
    private static final Map<UUID, Integer> INFINI_PEARL_SLOT = new HashMap<>();
    private static final Map<String, CommonModOptions> pendingRequests = new HashMap<>();

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
     * Sets the {@code warning message} for a player.
     */
    public static void setWarningMessages(UUID playerUuid, boolean showInActionbar) {
        WARNING_MESSAGES.put(playerUuid, showInActionbar);
    }

    /**
     * Gets the {@code warning message} for a player.
     */
    public static boolean shouldReceiveWarningMessages(UUID playerUuid) {
        return WARNING_MESSAGES.getOrDefault(playerUuid, true);
    }

    /**
     * Clears all {@code client preferences} for all players on server-side.
     */
    public static void clearPrefs(UUID playerUuid) {
        ACTIONBAR_PREFS.remove(playerUuid);
        WARNING_MESSAGES.remove(playerUuid);
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
     * Stores a {@code syncoption request} (with the player name and the requesting player's options).
     */
    public static void storePendingSyncRequest(String player, CommonModOptions options) {
        pendingRequests.put(player, options);
    }

    /**
     * Gets the {@code pending sync request} for a player.
     */
    public static CommonModOptions getPendingSyncRequest(String player) {
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