package net.dillon.speedrunnermod.packet;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.server.ServerSyncedClientOptions;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class ModPackets {

    /**
     * Registers the {@link UpdateClientPreferencesC2SPacket} payload.
     */
    private static void registerC2SPreferences() {
        PayloadTypeRegistry.playC2S().register(UpdateClientPreferencesC2SPacket.PAYLOAD_ID, UpdateClientPreferencesC2SPacket.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(UpdateClientPreferencesC2SPacket.PAYLOAD_ID, (payload, context) -> {
            ServerSyncedClientOptions.setActionbarPref(context.player().getUuid(), payload.actionbar());
        });

        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            ServerSyncedClientOptions.clearActionbarPrefs(handler.getPlayer().getUuid());
        });
    }

    /**
     * Registers the receiver for completing tutorial steps.
     */
    private static void registerC2SStepComplete() {
        PayloadTypeRegistry.playC2S().register(StepCompleteC2SPacket.PACKET_ID, StepCompleteC2SPacket.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(StepCompleteC2SPacket.PACKET_ID, (payload, context) -> {
            ServerSyncedClientOptions.completeTutorialStepC2S(context.player(), payload.step());
            ServerPlayNetworking.send(context.player(), new UpdateClientPreferencesS2CPacket(payload.messageKeys()));
        });
    }

    /**
     * Registers all speedrunner mod payloads/packets.
     */
    public static void registerPackets() {
        registerC2SPreferences();
        registerC2SStepComplete();

        SpeedrunnerMod.debug("Registered client-to-server packets.");
    }
}