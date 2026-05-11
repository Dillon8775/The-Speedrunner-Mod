package net.dillon.speedrunnermod.network.server;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

public record ClientPreferencesC2SPacket(boolean actionbar, int iCarusFireworksInventorySlot, int infiniPearlInventorySlot) implements CustomPacketPayload {
    public static final Identifier ID = ofSpeedrunnerMod("client_preferences_c2s");

    public static final CustomPacketPayload.Type<ClientPreferencesC2SPacket> PACKET = new CustomPacketPayload.Type<>(ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, ClientPreferencesC2SPacket> CODEC =
            StreamCodec.ofMember((buf, packet) -> {
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
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return PACKET;
    }
}