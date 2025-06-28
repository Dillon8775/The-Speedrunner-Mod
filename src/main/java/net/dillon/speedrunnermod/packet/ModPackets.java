package net.dillon.speedrunnermod.packet;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.server.ServerSyncedClientOptions;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.debug;

public class ModPackets {

    /**
     * Registers all speedrunner mod payloads/packets.
     */
    public static void registerPackets() {
        PayloadTypeRegistry.playC2S().register(UpdateClientPreferencesC2SPacket.PAYLOAD_ID, UpdateClientPreferencesC2SPacket.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(UpdateClientPreferencesC2SPacket.PAYLOAD_ID, (payload, context) -> {
            ServerSyncedClientOptions.setActionbarPreference(context.player().getUuid(), payload.actionbar());
        });

        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            ServerSyncedClientOptions.clear(handler.getPlayer().getUuid());
        });

        SpeedrunnerMod.debug("Registered client-to-server packets.");
    }
}