package net.dillon.speedrunnermod.packet;

import net.dillon.speedrunnermod.client.ClientSyncedServerOptions;
import net.dillon.speedrunnermod.client.screen.base.synced.ModeDoesntMatchScreen;
import net.dillon.speedrunnermod.client.screen.base.synced.TimedScreen;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.option.ClientModOptions;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.packet.client.CheckModeS2CPacket;
import net.dillon.speedrunnermod.packet.client.CompleteTutorialStepS2CPacket;
import net.dillon.speedrunnermod.packet.client.MatchClientOptionsWithServerS2CPacket;
import net.dillon.speedrunnermod.packet.client.UpdateLastCompletedTutorialStepTranslationsS2CPacket;
import net.dillon.speedrunnermod.packet.server.ClientPreferencesC2SPacket;
import net.dillon.speedrunnermod.packet.server.RequestServerSideOptionsC2SPacket;
import net.dillon.speedrunnermod.packet.server.TutorialStepCompleteC2SPacket;
import net.dillon.speedrunnermod.util.ModTexts;
import net.dillon.speedrunnermod.util.ModUtil;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.*;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.option.ClientModOptions.isActionbar;
import static net.dillon.speedrunnermod.util.ModUtil.sendWithPrefix;

public class ClientModPackets {

    /**
     * Registers the {@code server-to-client} packet to ensure that client-side mode option matches server-side.
     */
    private static void registerS2CCheckModePacket() {
        PayloadTypeRegistry.playS2C().register(CheckModeS2CPacket.PACKET, CheckModeS2CPacket.CODEC);

        ClientPlayNetworking.registerGlobalReceiver(CheckModeS2CPacket.PACKET, (packet, context) -> {
            if (options().main.mode.getCurrentValue() != packet.serverSideMode()) {
                context.client().getNetworkHandler().getConnection().disconnect(ModTexts.MODE_DOESNT_MATCH_SERVER_SETTING);
                context.client().disconnect(new ModeDoesntMatchScreen(packet.serverSideMode()));
            }
        });
    }

    /**
     * Registers {@code server-to-client complete tutorial step} packet.
     */
    private static void registerS2CCompleteTutorialStep() {
        PayloadTypeRegistry.playS2C().register(CompleteTutorialStepS2CPacket.PACKET, CompleteTutorialStepS2CPacket.CODEC);

        ClientPlayNetworking.registerGlobalReceiver(CompleteTutorialStepS2CPacket.PACKET, (packet, context) -> {
            clientOptions().tutorialMode.completeStep(packet.step(), context.player(), packet.messageKeys().toArray(new String[0]));
        });
    }

    /**
     * Registers the receiver for matching client-side options with server-side options.
     */
    private static void registerS2CMatchClientOptionsWithServer() {
        PayloadTypeRegistry.playS2C().register(MatchClientOptionsWithServerS2CPacket.PACKET, MatchClientOptionsWithServerS2CPacket.CODEC);

        ClientPlayNetworking.registerGlobalReceiver(MatchClientOptionsWithServerS2CPacket.PACKET, (packet, context) -> {
            ModOptions serverOptions = packet.toOptions();
            configHandler().matchWithServer(serverOptions);
            context.client().getNetworkHandler().getConnection().disconnect(ModTexts.MATCHED_SETTINGS_WITH_SERVER);
            context.client().disconnect(new TimedScreen(null, 5));
        });
    }

    /**
     * Registers {@code server-to-client tutorial step translations}  packet.
     */
    private static void registerS2CLastCompletedTutorialStepTranslations() {
        PayloadTypeRegistry.playS2C().register(UpdateLastCompletedTutorialStepTranslationsS2CPacket.PACKET, UpdateLastCompletedTutorialStepTranslationsS2CPacket.CODEC);

        ClientPlayNetworking.registerGlobalReceiver(UpdateLastCompletedTutorialStepTranslationsS2CPacket.PACKET, (packet, context) -> {
            ClientSyncedServerOptions.setLastSentTutorialModeMessageTranslations(context.player().getUuid(), packet.lastCompletedTutorialStepTranslations());
        });
    }

    /**
     * Registers {@code client-to-server preferences} packet.
     */
    private static void registerC2SOnServer() {
        // only register on server
        if (isEnvironmentTypeServer()) {
            PayloadTypeRegistry.playC2S().register(RequestServerSideOptionsC2SPacket.PACKET, RequestServerSideOptionsC2SPacket.CODEC);
            PayloadTypeRegistry.playC2S().register(TutorialStepCompleteC2SPacket.PACKET, TutorialStepCompleteC2SPacket.CODEC);
            PayloadTypeRegistry.playC2S().register(ClientPreferencesC2SPacket.PACKET, ClientPreferencesC2SPacket.CODEC);
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

                List<String> translations = ClientSyncedServerOptions.getLastSentTutorialModeMessageTranslations(player.getUuid());
                ClientSyncedServerOptions.setLastSentTutorialModeMessageTranslations(player.getUuid(), translations);
                if (ClientSyncedServerOptions.tutorialModeMessageTranslationsContainsPlayerUuid(player.getUuid()) && clientOptions().client.tutorialMode.getCurrentValue()) {
                    for (String s : translations) {
                        sendWithPrefix(s, player);
                    }
                }
            }
        });

        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
            if (client.player != null) {
                ClientSyncedServerOptions.writeAndClearTutorialModeMessageTranslations(client.player.getUuid());
            }
        });
    }

    /**
     * Registers all client-sided packets.
     */
    public static void registerClientPackets() {
        registerS2CCheckModePacket();
        registerS2CCompleteTutorialStep();
        registerS2CMatchClientOptionsWithServer();
        registerS2CLastCompletedTutorialStepTranslations();

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
            int i = integratedServer.getPermissionLevel(client.player.getGameProfile());
            client.player.setClientPermissionLevel(i);

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