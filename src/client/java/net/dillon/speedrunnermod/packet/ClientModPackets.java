package net.dillon.speedrunnermod.packet;

import net.dillon.speedrunnermod.client.ClientSyncedServerOptions;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.option.ClientModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.network.ClientPlayerEntity;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.util.ModUtil.sendWithPrefix;

public class ClientModPackets {

    /**
     * Registers {@code server-to-client preferences} payload.
     */
    private static void registerS2CPreferences() {
        PayloadTypeRegistry.playS2C().register(UpdateClientPreferencesS2CPacket.PAYLOAD_ID, UpdateClientPreferencesS2CPacket.CODEC);

        ClientPlayNetworking.registerGlobalReceiver(UpdateClientPreferencesS2CPacket.PAYLOAD_ID, (payload, context) -> {
            ClientSyncedServerOptions.setLastSentTutorialModeMessageTranslations(context.player().getUuid(), payload.lastCompletedTutorialStepTranslations());
        });

        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            sendNewC2SOptions();
            ClientPlayerEntity player = client.player;
            if (player != null) {
                List<String> translations = ClientSyncedServerOptions.getLastSentTutorialModeMessageTranslations(player.getUuid());
                ClientSyncedServerOptions.setLastSentTutorialModeMessageTranslations(player.getUuid(), translations);
                if (ClientSyncedServerOptions.tutorialModeMessageTranslationsContainsPlayerUuid(player.getUuid()) && clientOptions().client.tutorialMode) {
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
     * Registers {@code server-to-client tutorial step} payload.
     */
    private static void registerS2CTutorialStep() {
        PayloadTypeRegistry.playS2C().register(CompleteTutorialStepS2CPacket.PAYLOAD_ID, CompleteTutorialStepS2CPacket.CODEC);

        ClientPlayNetworking.registerGlobalReceiver(CompleteTutorialStepS2CPacket.PAYLOAD_ID, (payload, context) -> {
            if (clientOptions().client.tutorialMode) {
                clientOptions().tutorialMode.completeStep(payload.step(), context.player(), payload.messageKeys().toArray(new String[0]));
            }
        });
    }

    /**
     * Registers {@code client-to-server preferences} payload.
     */
    private static void registerC2SOnServer() {
        // only register on server
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
            PayloadTypeRegistry.playC2S().register(UpdateClientPreferencesC2SPacket.PAYLOAD_ID, UpdateClientPreferencesC2SPacket.CODEC);
            PayloadTypeRegistry.playC2S().register(StepCompleteC2SPacket.PACKET_ID, StepCompleteC2SPacket.CODEC);
        }
    }

    /**
     * Registers all client-sided packets.
     */
    public static void registerClientPackets() {
        registerS2CPreferences();
        registerC2SOnServer();
        registerS2CTutorialStep();

        SpeedrunnerMod.debug("Registered server-to-client packets.");
    }

    /**
     * Updates client-sided options and sends to server-side.
     */
    public static void sendNewC2SOptions() {
        ClientModOptions.Client options = clientOptions().client;
        ClientPlayNetworking.send(new UpdateClientPreferencesC2SPacket(
                options.itemMessages.isActionbar()
        ));
    }
}