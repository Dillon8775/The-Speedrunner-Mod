package net.dillon.speedrunnermod.packet.server;

import net.dillon.speedrunnermod.util.AI;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Requests mod options from client-to-server.
 */
@AI
public record RequestServerSideOptionsC2SPacket() implements CustomPayload {
    public static final Identifier ID = ofSpeedrunnerMod("request_server_side_options_c2s");
    public static final CustomPayload.Id<RequestServerSideOptionsC2SPacket> PACKET = new CustomPayload.Id<>(ID);

    public static final PacketCodec<PacketByteBuf, RequestServerSideOptionsC2SPacket> CODEC = PacketCodec.of(
        (buf, packet) -> {},
        buf -> new RequestServerSideOptionsC2SPacket()
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return PACKET;
    }
}