package net.dillon.speedrunnermod.packet;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

public record UpdateClientPreferencesS2CPacket(List<String> lastCompletedTutorialStepTranslations) implements CustomPayload {
    public static final Identifier ID = ofSpeedrunnerMod("update_client_prefs_s2c");
    public static final CustomPayload.Id<UpdateClientPreferencesS2CPacket> PAYLOAD_ID = new CustomPayload.Id<>(ID);

    public static final PacketCodec<RegistryByteBuf, UpdateClientPreferencesS2CPacket> CODEC =
            PacketCodec.of(
                    (buf, packet) -> {
                        packet.writeVarInt(buf.lastCompletedTutorialStepTranslations().size());
                        for (String str : buf.lastCompletedTutorialStepTranslations()) {
                            packet.writeString(str);
                        }
                    },
                    buf -> {
                        int size = buf.readVarInt();
                        List<String> list = new ArrayList<>(size);
                        for (int i = 0; i < size; i++) {
                            list.add(buf.readString());
                        }
                        return new UpdateClientPreferencesS2CPacket(list);
                    }
            );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return PAYLOAD_ID;
    }
}