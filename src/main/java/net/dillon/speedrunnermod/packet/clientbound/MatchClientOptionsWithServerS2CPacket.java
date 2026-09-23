package net.dillon.speedrunnermod.packet.clientbound;

import com.google.gson.Gson;
import net.dillon.speedrunnermod.option.ModCommonOptions;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

import static net.dillon.speedrunnermod.main.CommonMain.ofSpeedrunnerMod;

public record MatchClientOptionsWithServerS2CPacket(String jsonOptions) implements CustomPacketPayload {
    public static final Identifier ID = ofSpeedrunnerMod("match_client_options_with_server_s2c");
    public static final CustomPacketPayload.Type<MatchClientOptionsWithServerS2CPacket> PACKET_TYPE = new CustomPacketPayload.Type<>(ID);

    public static final Gson GSON = new Gson();

    public static final StreamCodec<RegistryFriendlyByteBuf, MatchClientOptionsWithServerS2CPacket> CODEC = StreamCodec.ofMember(
        (buf, packet) -> packet.writeUtf(buf.jsonOptions()),
        buf -> new MatchClientOptionsWithServerS2CPacket(buf.readUtf())
    );

    public static MatchClientOptionsWithServerS2CPacket from(ModCommonOptions options) {
        return new MatchClientOptionsWithServerS2CPacket(GSON.toJson(options));
    }

    public ModCommonOptions toOptions() {
        return GSON.fromJson(this.jsonOptions, ModCommonOptions.class);
    }

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return PACKET_TYPE;
    }
}