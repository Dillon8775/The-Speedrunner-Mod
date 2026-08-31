package net.dillon.speedrunnermod.network.client;

import net.dillon.speedrunnermod.option.eum.Mode;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Checks the mode from server, and makes sure that it matches on client-side.
 */
public record CheckModeS2CPacket(Mode serverSideMode) implements CustomPacketPayload {
    public static final Identifier ID = ofSpeedrunnerMod("check_mode_s2c");

    public static final CustomPacketPayload.Type<CheckModeS2CPacket> PACKET = new CustomPacketPayload.Type<>(ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, CheckModeS2CPacket> CODEC =
            StreamCodec.ofMember(
                    (buf, packet) -> {
                        packet.writeEnum(buf.serverSideMode());
                    },
                    buf -> new CheckModeS2CPacket(
                            buf.readEnum(Mode.class)
                    )
            );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return PACKET;
    }
}