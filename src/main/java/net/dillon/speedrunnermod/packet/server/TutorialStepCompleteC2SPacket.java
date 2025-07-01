package net.dillon.speedrunnermod.packet.server;

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

@ChatGPT(Credit.MOST_CREDIT)
public record TutorialStepCompleteC2SPacket(TutorialStep step, List<String> messageKeys) implements CustomPayload {
    public static final Identifier ID = ofSpeedrunnerMod("tutorial_step_complete_c2s");

    public static final CustomPayload.Id<TutorialStepCompleteC2SPacket> PACKET = new CustomPayload.Id<>(ID);
    public static final PacketCodec<RegistryByteBuf, TutorialStepCompleteC2SPacket> CODEC =
            PacketCodec.of(
                    (buf, packet) -> {
                        packet.writeEnumConstant(buf.step());
                        packet.writeCollection(buf.messageKeys(), PacketByteBuf::writeString);
                    },
                    buf -> new TutorialStepCompleteC2SPacket(
                            buf.readEnumConstant(TutorialStep.class),
                            buf.readList(PacketByteBuf::readString)
                    )
            );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return PACKET;
    }
}