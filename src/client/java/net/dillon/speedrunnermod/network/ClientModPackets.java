package net.dillon.speedrunnermod.network;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.network.client.CheckModeS2CPacket;
import net.dillon.speedrunnermod.network.client.MatchClientOptionsWithServerS2CPacket;
import net.dillon.speedrunnermod.network.client.OpenFeaturesScreenS2CPacket;
import net.dillon.speedrunnermod.network.client.RequestClientSideOptionsS2CPacket;
import net.dillon.speedrunnermod.network.server.ClientPreferencesC2SPacket;
import net.dillon.speedrunnermod.network.server.MatchServerOptionsWithClientC2SPacket;
import net.dillon.speedrunnermod.network.server.RequestServerSideOptionsC2SPacket;
import net.dillon.speedrunnermod.option.ClientModOptions;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.screen.feature.FeaturesScreen;
import net.dillon.speedrunnermod.screen.synced.ModeDoesntMatchScreen;
import net.dillon.speedrunnermod.screen.synced.TimedScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.dillon.speedrunnermod.util.ModUtil;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.server.IntegratedServer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.permissions.PermissionSet;
import net.minecraft.world.InteractionHand;

import java.util.Timer;
import java.util.TimerTask;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.*;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.option.ClientModOptions.isActionbar;

public class ClientModPackets {

    /**
     * Registers the {@code server-to-client} packet to ensure that client-side mode option matches server-side.
     */
    private static void registerS2CCheckModePacket() {
        PayloadTypeRegistry.clientboundPlay().register(CheckModeS2CPacket.PACKET, CheckModeS2CPacket.CODEC);

        ClientPlayNetworking.registerGlobalReceiver(CheckModeS2CPacket.PACKET, (packet, context) -> {
            if (options().general.mode.getCurrentValue() != packet.serverSideMode()) {
                context.client().getConnection().getConnection().disconnect(ModTexts.MODE_DOESNT_MATCH_SERVER_SETTING);
                context.client().disconnect(new ModeDoesntMatchScreen(packet.serverSideMode()), false, false);
            }
        });
    }

    /**
     * Registers the receiver for matching client-side options with server-side options.
     */
    private static void registerS2CMatchClientOptionsWithServer() {
        PayloadTypeRegistry.clientboundPlay().register(MatchClientOptionsWithServerS2CPacket.PACKET, MatchClientOptionsWithServerS2CPacket.CODEC);

        ClientPlayNetworking.registerGlobalReceiver(MatchClientOptionsWithServerS2CPacket.PACKET, (packet, context) -> {
            ModOptions serverOptions = packet.toOptions();
            configHandler().match(serverOptions);
            context.client().getConnection().getConnection().disconnect(ModTexts.MATCHED_SETTINGS_WITH_SERVER);
            context.client().disconnect(new TimedScreen(null, 5, true), false, false);
        });
    }

    /**
     * Registers the {@code server-to-client open features screen} packet.
     */
    private static void registerS2COpenFeaturesScreen() {
        PayloadTypeRegistry.clientboundPlay().register(OpenFeaturesScreenS2CPacket.PACKET, OpenFeaturesScreenS2CPacket.CODEC);

        ClientPlayNetworking.registerGlobalReceiver(OpenFeaturesScreenS2CPacket.PACKET, (payload, context) -> {
            context.player().swing(InteractionHand.MAIN_HAND, true);
            context.client().gui.setScreen(new FeaturesScreen(null));
        });
    }

    /**
     * Registers the {@code server-to-client} requesting syncing options packet.
     */
    private static void registerS2CRequestClientSideOptions() {
        PayloadTypeRegistry.clientboundPlay().register(RequestClientSideOptionsS2CPacket.PACKET, RequestClientSideOptionsS2CPacket.CODEC);

        ClientPlayNetworking.registerGlobalReceiver(RequestClientSideOptionsS2CPacket.PACKET, (payload, context) -> {
            ClientPlayNetworking.send(MatchServerOptionsWithClientC2SPacket.from(options(), context.player().getName().getString()));
            context.player().sendSystemMessage(Component.translatable("speedrunnermod.client_options_sent"));
        });
    }

    /**
     * Registers {@code client-to-server preferences} packet.
     */
    private static void registerC2SOnServer() {
        // only register on server
        if (isEnvironmentTypeServer()) {
            PayloadTypeRegistry.serverboundPlay().register(ClientPreferencesC2SPacket.PACKET, ClientPreferencesC2SPacket.CODEC);
            PayloadTypeRegistry.serverboundPlay().register(MatchServerOptionsWithClientC2SPacket.PACKET, MatchServerOptionsWithClientC2SPacket.CODEC);
            PayloadTypeRegistry.serverboundPlay().register(RequestServerSideOptionsC2SPacket.PACKET, RequestServerSideOptionsC2SPacket.CODEC);
        }
    }

    /**
     * Registers {@code client-side join and disconnect} events.
     */
    private static void registerClientJoinAndDisconnectEvents() {
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            ModUtil.errorMessagesSent = 0;
            sendNewC2SOptions();

            LocalPlayer player = client.player;
            if (player != null) {
                int delayInTicks = 150;
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        syncFwc(client, delayInTicks);
                    }
                }, delayInTicks);
            }
        });
    }

    /**
     * Registers all client-sided packets.
     */
    public static void registerClientPackets() {
        registerS2CCheckModePacket();
        registerS2CMatchClientOptionsWithServer();
        registerS2COpenFeaturesScreen();
        registerS2CRequestClientSideOptions();

        registerC2SOnServer(); // register client-to-server ONLY on EnvType.SERVER

        registerClientJoinAndDisconnectEvents();

        SpeedrunnerMod.debug("Registered server-to-client packets.");
    }

    /**
     * Syncs the {@code fast world creation} options with the world.
     */
    public static void syncFwc(Minecraft client, int delayTicks) {
        IntegratedServer integratedServer = client.getSingleplayerServer();
        if (integratedServer != null) {
            integratedServer.getPlayerList().setAllowCommandsForAllPlayers(clientOptions().client.allowCommands.getCurrentValue());
            PermissionSet permissionPredicate = integratedServer.getProfilePermissions(client.player.nameAndId());
            client.player.setPermissions(permissionPredicate);

            for (ServerPlayer serverPlayerEntity : integratedServer.getPlayerList().getPlayers()) {
                integratedServer.getCommands().sendCommands(serverPlayerEntity);
            }

            SpeedrunnerMod.debug("Synced fast world creation settings with world in " + delayTicks + " ticks.");
        }
    }

    /**
     * Updates client-sided options and sends to server-side.
     */
    public static void sendNewC2SOptions() {
        ClientModOptions.Client options = clientOptions().client;
        ClientPlayNetworking.send(new ClientPreferencesC2SPacket(
                isActionbar(), options.iCarusFireworksInventorySlot.getCurrentValue(), options.infiniPearlInventorySlot.getCurrentValue()
        ));
    }
}