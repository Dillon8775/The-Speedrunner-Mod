package net.dillon.speedrunnermod.packet;

import net.dillon.speedrunnermod.client.ClientSyncedServerOptions;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.option.ClientModOptions;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.network.ClientPlayerEntity;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.util.ModUtil.sendWithPrefix;

public class ClientModPackets {

    /**
     * Registers all client-sided packets.
     */
    public static void registerClientPackets() {
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
                if (ClientSyncedServerOptions.tutorialModeMessageTranslationsContainsPlayerUuid(player.getUuid()) && options().main.tutorialMode) {
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