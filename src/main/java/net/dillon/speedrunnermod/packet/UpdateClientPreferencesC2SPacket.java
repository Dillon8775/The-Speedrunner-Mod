package net.dillon.speedrunnermod.packet;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

public record UpdateClientPreferencesC2SPacket(boolean actionbar) implements CustomPayload {
    public static final Identifier ID = ofSpeedrunnerMod("update_client_prefs");
    public static final CustomPayload.Id<UpdateClientPreferencesC2SPacket> PAYLOAD_ID = new CustomPayload.Id<>(ID);

    public static final PacketCodec<RegistryByteBuf, UpdateClientPreferencesC2SPacket> CODEC =
            PacketCodec.of((buf, payload) -> {
                payload.writeBoolean(buf.actionbar());
                    },
                    buf -> new UpdateClientPreferencesC2SPacket
                            (
                                    buf.readBoolean()
                            )
            );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return PAYLOAD_ID;
    }
}