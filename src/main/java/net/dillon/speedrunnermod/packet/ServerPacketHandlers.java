package net.dillon.speedrunnermod.packet;

import net.blay09.mods.balm.Balm;
import net.dillon.speedrunnermod.option.ModCommonOptions;
import net.dillon.speedrunnermod.packet.clientbound.MatchClientOptionsWithServerS2CPacket;
import net.dillon.speedrunnermod.packet.serverbound.ClientPreferencesC2SPacket;
import net.dillon.speedrunnermod.packet.serverbound.MatchServerOptionsWithClientC2SPacket;
import net.dillon.speedrunnermod.packet.serverbound.RequestServerSideOptionsC2SPacket;
import net.dillon.speedrunnermod.server.DedicatedServerStorage;
import net.minecraft.server.level.ServerPlayer;

import java.util.UUID;

import static net.dillon.speedrunnermod.main.CommonMain.LOGGER;
import static net.dillon.speedrunnermod.main.CommonMain.common;

/**
 * Handles server-bound packets.
 */
public class ServerPacketHandlers {

    /**
     * Handles client preferences being sent over to the server.
     */
    public static void handleClientPreferences(ServerPlayer player, ClientPreferencesC2SPacket packet) {
        UUID playerUuid = player.getUUID();
        DedicatedServerStorage.setActionbarPref(playerUuid, packet.actionbar());
        DedicatedServerStorage.setWarningMessages(playerUuid, packet.warningMessages());
        DedicatedServerStorage.setIcarusFireworkSlot(playerUuid, packet.iCarusFireworksInventorySlot());
        DedicatedServerStorage.setInfiniPearlSlot(playerUuid, packet.infiniPearlInventorySlot());
    }

    /**
     * Handles requesting the server-side options.
     */
    public static void handleRequestServerSideOptions(ServerPlayer player, RequestServerSideOptionsC2SPacket packet) {
        ModCommonOptions serverOptions = common();
        Balm.networking().sendTo(player, MatchClientOptionsWithServerS2CPacket.from(serverOptions));
        LOGGER.info("{} requested this server's speedrunner mod settings.", player.getDisplayName().getString());
    }

    /**
     * Handles matching the server-side options with an incoming user.
     */
    public static void handleMatchServerOptionsWithUser(ServerPlayer player, MatchServerOptionsWithClientC2SPacket packet) {
        ModCommonOptions clientOptions = packet.toOptions();
        String playerName = packet.playerName();
        DedicatedServerStorage.storePendingSyncRequest(playerName, clientOptions);
        LOGGER.info("/syncoptions command was used by {}. This means the server's speedrunner mod settings will be set to this player's speedrunner mod settings. Do you wish to authorize this? Execute the following command: \"syncoptionsauthorize {} accept/deny\".",
                playerName,
                playerName
        );
    }
}