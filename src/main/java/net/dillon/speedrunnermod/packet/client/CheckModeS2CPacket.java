package net.dillon.speedrunnermod.packet.client;

import net.dillon.speedrunnermod.option.ModOptions;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Checks the mode from server, and makes sure that it matches on client-side.
 */
public record CheckModeS2CPacket(ModOptions.Mode serverSideMode) implements CustomPayload {
    public static final Identifier ID = ofSpeedrunnerMod("check_mode_s2c");

    public static final CustomPayload.Id<CheckModeS2CPacket> PACKET = new CustomPayload.Id<>(ID);
    public static final PacketCodec<RegistryByteBuf, CheckModeS2CPacket> CODEC =
            PacketCodec.of(
                    (buf, packet) -> {
                        packet.writeEnumConstant(buf.serverSideMode());
                    },
                    buf -> new CheckModeS2CPacket(
                            buf.readEnumConstant(ModOptions.Mode.class)
                    )
            );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return PACKET;
    }
}