package net.dillon.speedrunnermod.packet.server;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

public record ClientPreferencesC2SPacket(boolean actionbar, int iCarusFireworksInventorySlot, int infiniPearlInventorySlot) implements CustomPayload {
    public static final Identifier ID = ofSpeedrunnerMod("update_actionbar_preferences_c2s");

    public static final CustomPayload.Id<ClientPreferencesC2SPacket> PACKET = new CustomPayload.Id<>(ID);
    public static final PacketCodec<RegistryByteBuf, ClientPreferencesC2SPacket> CODEC =
            PacketCodec.of((buf, packet) -> {
                packet.writeBoolean(buf.actionbar());
                packet.writeInt(buf.iCarusFireworksInventorySlot());
                packet.writeInt(buf.infiniPearlInventorySlot());
                },
                    buf -> new ClientPreferencesC2SPacket
                            (
                                    buf.readBoolean(),
                                    buf.readInt(),
                                    buf.readInt()
                            )
            );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return PACKET;
    }
}