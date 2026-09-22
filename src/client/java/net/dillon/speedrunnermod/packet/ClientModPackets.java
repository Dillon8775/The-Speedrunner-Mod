package net.dillon.speedrunnermod.packet;

import net.blay09.mods.balm.Balm;
import net.dillon.dillonlib.task.CommonTasks;
import net.dillon.speedrunnermod.helper.ModConstants;
import net.dillon.speedrunnermod.helper.ModHelper;
import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.option.ModClientOptions;
import net.dillon.speedrunnermod.packet.clientbound.CheckModeS2CPacket;
import net.dillon.speedrunnermod.packet.clientbound.MatchClientOptionsWithServerS2CPacket;
import net.dillon.speedrunnermod.packet.clientbound.OpenFeaturesScreenS2CPacket;
import net.dillon.speedrunnermod.packet.clientbound.RequestClientSideOptionsS2CPacket;
import net.dillon.speedrunnermod.packet.serverbound.ClientPreferencesC2SPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.server.IntegratedServer;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.permissions.PermissionSet;

import java.util.Timer;
import java.util.TimerTask;

import static net.dillon.dillonlib.task.ClientTasks.executeIfClientPlayer;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.client;
import static net.dillon.speedrunnermod.option.ModClientOptions.isOverlay;

public class ClientModPackets {

    /**
     * Registers all client-bound packets.
     */
    public static void registerClientBoundPackets() {
        registerClientJoinAndDisconnectEvents();

        Balm.networking().registerClientboundPacket(
                CheckModeS2CPacket.PACKET_TYPE,
                CheckModeS2CPacket.class,
                CheckModeS2CPacket.CODEC,
                ClientPacketHandlers::handleCheckMode
        );

        Balm.networking().registerClientboundPacket(
                MatchClientOptionsWithServerS2CPacket.PACKET_TYPE,
                MatchClientOptionsWithServerS2CPacket.class,
                MatchClientOptionsWithServerS2CPacket.CODEC,
                ClientPacketHandlers::handleMatchUserOptionsWithServer
        );

        Balm.networking().registerClientboundPacket(
                OpenFeaturesScreenS2CPacket.PACKET_TYPE,
                OpenFeaturesScreenS2CPacket.class,
                OpenFeaturesScreenS2CPacket.CODEC,
                ClientPacketHandlers::handleOpenFeaturesScreen
        );

        Balm.networking().registerClientboundPacket(
                RequestClientSideOptionsS2CPacket.PACKET_TYPE,
                RequestClientSideOptionsS2CPacket.class,
                RequestClientSideOptionsS2CPacket.CODEC,
                ClientPacketHandlers::handleMatchClientOptionsWithServer
        );
    }

    /**
     * Registers {@code client-side join and disconnect} events.
     */
    private static void registerClientJoinAndDisconnectEvents() {
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            ModHelper.errorMessagesSent = 0;
            sendNewC2SOptions();

            executeIfClientPlayer(player -> {
                int delayInTicks = 150;
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        syncFwc(client, delayInTicks);
                    }
                }, delayInTicks);

                if (ModConstants.HAS_UPDATE) {
                    CommonTasks.sendUpdateMessage(player, ModTexts.TITLE.copy().withStyle(ChatFormatting.AQUA), "https://modrinth.com/mod/speedrunner-mod/versions", TextColor.AQUA.getValue());
                }
            });
        });
    }
    /**
     * Syncs the {@code fast world creation} options with the world.
     */
    public static void syncFwc(Minecraft client, int delayTicks) {
        IntegratedServer integratedServer = client.getSingleplayerServer();
        if (integratedServer != null) {
            integratedServer.setWorldAllowCommands(client().worldCreation().allowCommands);
            PermissionSet permissionPredicate = integratedServer.getProfilePermissions(client.player.nameAndId());
            client.player.setPermissions(permissionPredicate);

            for (ServerPlayer serverPlayerEntity : integratedServer.getPlayerList().getPlayers()) {
                integratedServer.getCommands().sendCommands(serverPlayerEntity);
            }

            SpeedrunnerMod.LOGGER.info("Synced fast world creation settings with world in {} ticks.", delayTicks);
        }
    }

    /**
     * Updates client-sided options and sends to server-side.
     */
    public static void sendNewC2SOptions() {
        ModClientOptions.General options = client().general();
        ClientPlayNetworking.send(new ClientPreferencesC2SPacket(
                isOverlay(), options.warningMessages, options.iCarusFireworksInventorySlot, options.infiniPearlInventorySlot
        ));
    }
}