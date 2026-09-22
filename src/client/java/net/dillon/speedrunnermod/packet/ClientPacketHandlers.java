package net.dillon.speedrunnermod.packet;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.option.ModCommonOptions;
import net.dillon.speedrunnermod.packet.clientbound.CheckModeS2CPacket;
import net.dillon.speedrunnermod.packet.clientbound.MatchClientOptionsWithServerS2CPacket;
import net.dillon.speedrunnermod.packet.clientbound.OpenFeaturesScreenS2CPacket;
import net.dillon.speedrunnermod.packet.clientbound.RequestClientSideOptionsS2CPacket;
import net.dillon.speedrunnermod.packet.serverbound.MatchServerOptionsWithClientC2SPacket;
import net.dillon.speedrunnermod.screen.feature.FeaturesScreen;
import net.dillon.speedrunnermod.screen.synced.ModeDoesntMatchScreen;
import net.dillon.speedrunnermod.screen.synced.TimedScreen;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.component.SwingAnimation;

import static net.dillon.dillonlib.task.ClientTasks.getMinecraft;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.commonConfigHandler;

/**
 * Handles client-bound packets.
 */
public class ClientPacketHandlers {

    /**
     * Handles checking the player's mode.
     */
    public static void handleCheckMode(Player player, CheckModeS2CPacket packet) {
        if (common().general().mode != packet.serverSideMode()) {
            getMinecraft().getConnection().getConnection().disconnect(Component.translatable("speedrunnermod.mode.doesnt_match_server"));
            getMinecraft().disconnect(new ModeDoesntMatchScreen(packet.serverSideMode()), false, false);
        }
    }

    /**
     * Handles matching the user's (client) options with the server-side options.
     */
    public static void handleMatchUserOptionsWithServer(Player player, MatchClientOptionsWithServerS2CPacket packet) {
        ModCommonOptions serverOptions = packet.toOptions();
        commonConfigHandler().match(serverOptions);
        getMinecraft().getConnection().getConnection().disconnect(ModTexts.MATCHED_SETTINGS_WITH_SERVER);
        getMinecraft().disconnect(new TimedScreen(null, 5, true), false, false);
    }

    /**
     * Handles opening the features screen.
     */
    public static void handleOpenFeaturesScreen(Player player, OpenFeaturesScreenS2CPacket packet) {
        player.swing(InteractionHand.MAIN_HAND, SwingAnimation.DEFAULT, true);
        getMinecraft().gui.setScreen(new FeaturesScreen(null));
    }

    /**
     * Handles matching and requesting the client-side options with the server options.
     */
    public static void handleMatchClientOptionsWithServer(Player player, RequestClientSideOptionsS2CPacket packet) {
        ClientPlayNetworking.send(MatchServerOptionsWithClientC2SPacket.from(common(), player.getName().getString()));
        player.sendSystemMessage(Component.translatable("speedrunnermod.client_options_sent"));
    }
}