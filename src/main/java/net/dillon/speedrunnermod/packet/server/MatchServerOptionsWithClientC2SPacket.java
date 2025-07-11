package net.dillon.speedrunnermod.packet.server;

import com.google.gson.Gson;
import net.dillon.speedrunnermod.option.ModOptions;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * A packet which sends client-side options to the server to match the server's settings with the client.
 */
public record MatchServerOptionsWithClientC2SPacket(String jsonOptions, String playerName) implements CustomPayload {
    public static final Identifier ID = ofSpeedrunnerMod("match_server_options_with_client_c2s");
    public static final CustomPayload.Id<MatchServerOptionsWithClientC2SPacket> PACKET = new CustomPayload.Id<>(ID);

    public static final Gson GSON = new Gson();

    public static final PacketCodec<PacketByteBuf, MatchServerOptionsWithClientC2SPacket> CODEC = PacketCodec.of(
            (buf, packet) -> {
                packet.writeString(buf.jsonOptions());
                packet.writeString(buf.playerName());
            },
            buf -> new MatchServerOptionsWithClientC2SPacket(
                    buf.readString(),
                    buf.readString())
    );

    public static MatchServerOptionsWithClientC2SPacket from(ModOptions options, String playerName) {
        return new MatchServerOptionsWithClientC2SPacket(GSON.toJson(options), playerName);
    }

    public ModOptions toOptions() {
        return GSON.fromJson(this.jsonOptions, ModOptions.class);
    }

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return PACKET;
    }
}