package net.dillon.speedrunnermod.packet.client;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

public record RequestClientSideOptionsS2CPacket() implements CustomPacketPayload {
    public static final Identifier ID = ofSpeedrunnerMod("request_client_side_options_s2c");
    public static final CustomPacketPayload.Type<RequestClientSideOptionsS2CPacket> PACKET = new CustomPacketPayload.Type<>(ID);

    public static final StreamCodec<FriendlyByteBuf, RequestClientSideOptionsS2CPacket> CODEC = StreamCodec.ofMember(
            (buf, packet) -> {},
            buf -> new RequestClientSideOptionsS2CPacket()
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return PACKET;
    }
}