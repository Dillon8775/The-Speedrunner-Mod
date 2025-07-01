package net.dillon.speedrunnermod.packet.client;

import net.dillon.speedrunnermod.tutorial.TutorialStep;
import net.dillon.speedrunnermod.util.AI;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Packet for sending tutorial steps over to the client-side.
 */
@AI
public record CompleteTutorialStepS2CPacket(TutorialStep step, List<String> messageKeys) implements CustomPayload {
    public static final Identifier ID = ofSpeedrunnerMod("complete_tutorial_step_s2c");

    public static final CustomPayload.Id<CompleteTutorialStepS2CPacket> PACKET = new CustomPayload.Id<>(ID);
    public static final PacketCodec<RegistryByteBuf, CompleteTutorialStepS2CPacket> CODEC =
            PacketCodec.of(
                    (buf, packet) -> {
                        packet.writeEnumConstant(buf.step());
                        packet.writeCollection(buf.messageKeys(), PacketByteBuf::writeString);
                    },
                    buf -> new CompleteTutorialStepS2CPacket(
                            buf.readEnumConstant(TutorialStep.class),
                            buf.readList(PacketByteBuf::readString)
                    )
            );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return PACKET;
    }
}