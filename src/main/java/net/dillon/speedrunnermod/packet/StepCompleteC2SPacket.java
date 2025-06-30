package net.dillon.speedrunnermod.packet;

import net.dillon.speedrunnermod.tutorial.TutorialStep;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

@ChatGPT(Credit.FULL_CREDIT)
public record StepCompleteC2SPacket(TutorialStep step, List<String> messageKeys) implements CustomPayload {
    public static final Identifier ID = ofSpeedrunnerMod("step_complete");
    public static final CustomPayload.Id<StepCompleteC2SPacket> PACKET_ID = new CustomPayload.Id<>(ID);

    public static final PacketCodec<RegistryByteBuf, StepCompleteC2SPacket> CODEC =
            PacketCodec.of(
                    (buf, payload) -> {
                        payload.writeEnumConstant(buf.step());
                        payload.writeCollection(buf.messageKeys(), PacketByteBuf::writeString);
                    },
                    buf -> new StepCompleteC2SPacket(
                            buf.readEnumConstant(TutorialStep.class),
                            buf.readList(PacketByteBuf::readString)
                    )
            );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }
}