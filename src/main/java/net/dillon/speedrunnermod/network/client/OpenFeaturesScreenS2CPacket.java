package net.dillon.speedrunnermod.network.client;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

public record OpenFeaturesScreenS2CPacket() implements CustomPacketPayload {
    public static final Identifier ID = ofSpeedrunnerMod("open_features_screen_s2c");

    public static final CustomPacketPayload.Type<OpenFeaturesScreenS2CPacket> PACKET = new CustomPacketPayload.Type<>(ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, OpenFeaturesScreenS2CPacket> CODEC =
            StreamCodec.ofMember(
                    (buf, packet) -> {},
                    buf -> new OpenFeaturesScreenS2CPacket()
            );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return PACKET;
    }
}