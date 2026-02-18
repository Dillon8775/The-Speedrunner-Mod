package net.dillon.speedrunnermod.packet;

import net.dillon.speedrunnermod.client.screen.base.synced.ModeDoesntMatchScreen;
import net.dillon.speedrunnermod.client.screen.base.synced.TimedScreen;
import net.dillon.speedrunnermod.client.screen.feature.FeaturesScreen;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.option.ClientModOptions;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.packet.client.CheckModeS2CPacket;
import net.dillon.speedrunnermod.packet.client.MatchClientOptionsWithServerS2CPacket;
import net.dillon.speedrunnermod.packet.client.OpenFeaturesScreenS2CPacket;
import net.dillon.speedrunnermod.packet.client.RequestClientSideOptionsS2CPacket;
import net.dillon.speedrunnermod.packet.server.ClientPreferencesC2SPacket;
import net.dillon.speedrunnermod.packet.server.MatchServerOptionsWithClientC2SPacket;
import net.dillon.speedrunnermod.packet.server.RequestServerSideOptionsC2SPacket;
import net.dillon.speedrunnermod.util.ModTexts;
import net.dillon.speedrunnermod.util.ModUtil;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.command.permission.PermissionPredicate;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

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
        PayloadTypeRegistry.playS2C().register(CheckModeS2CPacket.PACKET, CheckModeS2CPacket.CODEC);

        ClientPlayNetworking.registerGlobalReceiver(CheckModeS2CPacket.PACKET, (packet, context) -> {
            if (options().main.mode.getCurrentValue() != packet.serverSideMode()) {
                context.client().getNetworkHandler().getConnection().disconnect(ModTexts.MODE_DOESNT_MATCH_SERVER_SETTING);
                context.client().disconnect(new ModeDoesntMatchScreen(packet.serverSideMode()), false, false);
            }
        });
    }

    /**
     * Registers the receiver for matching client-side options with server-side options.
     */
    private static void registerS2CMatchClientOptionsWithServer() {
        PayloadTypeRegistry.playS2C().register(MatchClientOptionsWithServerS2CPacket.PACKET, MatchClientOptionsWithServerS2CPacket.CODEC);

        ClientPlayNetworking.registerGlobalReceiver(MatchClientOptionsWithServerS2CPacket.PACKET, (packet, context) -> {
            ModOptions serverOptions = packet.toOptions();
            configHandler().match(serverOptions);
            context.client().getNetworkHandler().getConnection().disconnect(ModTexts.MATCHED_SETTINGS_WITH_SERVER);
            context.client().disconnect(new TimedScreen(null, 5, true), false, false);
        });
    }

    /**
     * Registers the {@code server-to-client open features screen} packet.
     */
    private static void registerS2COpenFeaturesScreen() {
        PayloadTypeRegistry.playS2C().register(OpenFeaturesScreenS2CPacket.PACKET, OpenFeaturesScreenS2CPacket.CODEC);

        ClientPlayNetworking.registerGlobalReceiver(OpenFeaturesScreenS2CPacket.PACKET, (payload, context) -> {
            context.player().swingHand(context.player().getActiveHand(), true);
            context.client().setScreen(new FeaturesScreen(null));
        });
    }

    /**
     * Registers the {@code server-to-client} requesting syncing options packet.
     */
    private static void registerS2CRequestClientSideOptions() {
        PayloadTypeRegistry.playS2C().register(RequestClientSideOptionsS2CPacket.PACKET, RequestClientSideOptionsS2CPacket.CODEC);

        ClientPlayNetworking.registerGlobalReceiver(RequestClientSideOptionsS2CPacket.PACKET, (payload, context) -> {
            ClientPlayNetworking.send(MatchServerOptionsWithClientC2SPacket.from(options(), context.player().getName().getString()));
            context.player().sendMessage(Text.translatable("speedrunnermod.client_options_sent"), false);
        });
    }

    /**
     * Registers {@code client-to-server preferences} packet.
     */
    private static void registerC2SOnServer() {
        // only register on server
        if (isEnvironmentTypeServer()) {
            PayloadTypeRegistry.playC2S().register(ClientPreferencesC2SPacket.PACKET, ClientPreferencesC2SPacket.CODEC);
            PayloadTypeRegistry.playC2S().register(MatchServerOptionsWithClientC2SPacket.PACKET, MatchServerOptionsWithClientC2SPacket.CODEC);
            PayloadTypeRegistry.playC2S().register(RequestServerSideOptionsC2SPacket.PACKET, RequestServerSideOptionsC2SPacket.CODEC);
        }
    }

    /**
     * Registers {@code client-side join and disconnect} events.
     */
    private static void registerClientJoinAndDisconnectEvents() {
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            ModUtil.errorMessagesSent = 0;
            sendNewC2SOptions();

            ClientPlayerEntity player = client.player;
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
    public static void syncFwc(MinecraftClient client, int delayTicks) {
        IntegratedServer integratedServer = client.getServer();
        if (integratedServer != null) {
            integratedServer.getPlayerManager().setCheatsAllowed(clientOptions().client.allowCheats.getCurrentValue());
            PermissionPredicate permissionPredicate = integratedServer.getPermissionLevel(client.player.getPlayerConfigEntry());
            client.player.setPermissions(permissionPredicate);

            for (ServerPlayerEntity serverPlayerEntity : integratedServer.getPlayerManager().getPlayerList()) {
                integratedServer.getCommandManager().sendCommandTree(serverPlayerEntity);
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