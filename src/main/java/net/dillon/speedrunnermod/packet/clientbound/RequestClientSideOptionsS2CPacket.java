package net.dillon.speedrunnermod.packet.clientbound;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

import static net.dillon.speedrunnermod.main.CommonMain.ofSpeedrunnerMod;

public record RequestClientSideOptionsS2CPacket() implements CustomPacketPayload {
    public static final Identifier ID = ofSpeedrunnerMod("request_client_side_options_s2c");
    public static final CustomPacketPayload.Type<RequestClientSideOptionsS2CPacket> PACKET_TYPE = new CustomPacketPayload.Type<>(ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, RequestClientSideOptionsS2CPacket> CODEC = StreamCodec.ofMember(
            (buf, packet) -> {},
            buf -> new RequestClientSideOptionsS2CPacket()
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return PACKET_TYPE;
    }
}