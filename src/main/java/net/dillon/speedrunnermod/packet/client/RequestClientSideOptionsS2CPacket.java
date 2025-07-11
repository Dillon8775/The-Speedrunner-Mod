package net.dillon.speedrunnermod.packet.client;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

public record RequestClientSideOptionsS2CPacket() implements CustomPayload {
    public static final Identifier ID = ofSpeedrunnerMod("request_client_side_options_s2c");
    public static final CustomPayload.Id<RequestClientSideOptionsS2CPacket> PACKET = new CustomPayload.Id<>(ID);

    public static final PacketCodec<PacketByteBuf, RequestClientSideOptionsS2CPacket> CODEC = PacketCodec.of(
            (buf, packet) -> {},
            buf -> new RequestClientSideOptionsS2CPacket()
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return PACKET;
    }
}