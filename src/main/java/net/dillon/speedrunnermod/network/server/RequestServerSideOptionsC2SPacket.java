package net.dillon.speedrunnermod.network.server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Requests mod options from client-to-server.
 */
public record RequestServerSideOptionsC2SPacket() implements CustomPacketPayload {
    public static final Identifier ID = ofSpeedrunnerMod("request_server_side_options_c2s");
    public static final CustomPacketPayload.Type<RequestServerSideOptionsC2SPacket> PACKET = new CustomPacketPayload.Type<>(ID);

    public static final StreamCodec<FriendlyByteBuf, RequestServerSideOptionsC2SPacket> CODEC = StreamCodec.ofMember(
        (buf, packet) -> {},
        buf -> new RequestServerSideOptionsC2SPacket()
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return PACKET;
    }
}