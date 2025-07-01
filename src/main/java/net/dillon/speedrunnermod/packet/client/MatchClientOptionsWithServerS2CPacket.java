package net.dillon.speedrunnermod.packet.client;

import com.google.gson.Gson;
import net.dillon.speedrunnermod.option.ModOptions;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

public record MatchClientOptionsWithServerS2CPacket(String jsonOptions) implements CustomPayload {
    public static final Identifier ID = ofSpeedrunnerMod("match_client_options_with_server_s2c");
    public static final CustomPayload.Id<MatchClientOptionsWithServerS2CPacket> PACKET = new CustomPayload.Id<>(ID);

    public static final Gson GSON = new Gson();

    public static final PacketCodec<PacketByteBuf, MatchClientOptionsWithServerS2CPacket> CODEC = PacketCodec.of(
        (buf, packet) -> packet.writeString(buf.jsonOptions()),
        buf -> new MatchClientOptionsWithServerS2CPacket(buf.readString())
    );

    public static MatchClientOptionsWithServerS2CPacket from(ModOptions options) {
        return new MatchClientOptionsWithServerS2CPacket(GSON.toJson(options));
    }

    public ModOptions toOptions() {
        return GSON.fromJson(this.jsonOptions, ModOptions.class);
    }

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return PACKET;
    }
}