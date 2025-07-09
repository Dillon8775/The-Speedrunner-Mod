package net.dillon.speedrunnermod.packet.client;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

public record OpenFeaturesScreenS2CPacket() implements CustomPayload {
    public static final Identifier ID = ofSpeedrunnerMod("open_features_screen_s2c");

    public static final CustomPayload.Id<OpenFeaturesScreenS2CPacket> PACKET = new CustomPayload.Id<>(ID);
    public static final PacketCodec<RegistryByteBuf, OpenFeaturesScreenS2CPacket> CODEC =
            PacketCodec.of(
                    (buf, packet) -> {},
                    buf -> new OpenFeaturesScreenS2CPacket()
            );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return PACKET;
    }
}