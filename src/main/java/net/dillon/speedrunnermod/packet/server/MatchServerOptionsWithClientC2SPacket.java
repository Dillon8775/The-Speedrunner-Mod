package net.dillon.speedrunnermod.packet.server;

import com.google.gson.Gson;
import net.dillon.speedrunnermod.option.ModOptions;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * A packet which sends client-side options to the server to match the server's settings with the client.
 */
public record MatchServerOptionsWithClientC2SPacket(String jsonOptions, String playerName) implements CustomPacketPayload {
    public static final Identifier ID = ofSpeedrunnerMod("match_server_options_with_client_c2s");
    public static final CustomPacketPayload.Type<MatchServerOptionsWithClientC2SPacket> PACKET = new CustomPacketPayload.Type<>(ID);

    public static final Gson GSON = new Gson();

    public static final StreamCodec<FriendlyByteBuf, MatchServerOptionsWithClientC2SPacket> CODEC = StreamCodec.ofMember(
            (buf, packet) -> {
                packet.writeUtf(buf.jsonOptions());
                packet.writeUtf(buf.playerName());
            },
            buf -> new MatchServerOptionsWithClientC2SPacket(
                    buf.readUtf(),
                    buf.readUtf())
    );

    public static MatchServerOptionsWithClientC2SPacket from(ModOptions options, String playerName) {
        return new MatchServerOptionsWithClientC2SPacket(GSON.toJson(options), playerName);
    }

    public ModOptions toOptions() {
        return GSON.fromJson(this.jsonOptions, ModOptions.class);
    }

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return PACKET;
    }
}