package net.dillon.speedrunnermod.packet;

import net.dillon.speedrunnermod.option.ClientModOptions;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;

public class ClientModPackets {

    /**
     * Registers all client-sided packets.
     */
    public static void registerClientPackets() {
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            sendNewC2SOptions();
        });
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