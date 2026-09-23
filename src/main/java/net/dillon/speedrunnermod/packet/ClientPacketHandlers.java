package net.dillon.speedrunnermod.packet;

import net.dillon.speedrunnermod.main.ClientEvents;
import net.dillon.speedrunnermod.packet.clientbound.CheckModeS2CPacket;
import net.dillon.speedrunnermod.packet.clientbound.MatchClientOptionsWithServerS2CPacket;
import net.dillon.speedrunnermod.packet.clientbound.OpenFeaturesScreenS2CPacket;
import net.dillon.speedrunnermod.packet.clientbound.RequestClientSideOptionsS2CPacket;
import net.minecraft.world.entity.player.Player;

/**
 * Handles client-bound packets.
 */
public class ClientPacketHandlers {

    /**
     * Handles checking the player's mode.
     */
    public static void handleCheckMode(Player player, CheckModeS2CPacket packet) {
        ClientEvents.handleCheckMode(packet);
    }

    /**
     * Handles matching the user's (client) options with the server-side options.
     */
    public static void handleMatchUserOptionsWithServer(Player player, MatchClientOptionsWithServerS2CPacket packet) {
        ClientEvents.handleMatchUserOptionsWithServer(packet);
    }

    /**
     * Handles opening the features screen.
     */
    public static void handleOpenFeaturesScreen(Player player, OpenFeaturesScreenS2CPacket packet) {
        ClientEvents.handleOpenFeaturesScreen(player);
    }

    /**
     * Handles matching and requesting the client-side options with the server options.
     */
    public static void handleMatchClientOptionsWithServer(Player player, RequestClientSideOptionsS2CPacket packet) {
        ClientEvents.handleMatchClientOptionsWithServer(player);
    }
}