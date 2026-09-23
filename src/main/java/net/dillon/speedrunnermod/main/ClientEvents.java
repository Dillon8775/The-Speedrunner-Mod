package net.dillon.speedrunnermod.main;

import net.blay09.mods.balm.Balm;
import net.dillon.dillonlib.task.CommonTasks;
import net.dillon.speedrunnermod.helper.ModConstants;
import net.dillon.speedrunnermod.helper.ModHelper;
import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.option.ModClientOptions;
import net.dillon.speedrunnermod.option.ModCommonOptions;
import net.dillon.speedrunnermod.packet.clientbound.CheckModeS2CPacket;
import net.dillon.speedrunnermod.packet.clientbound.MatchClientOptionsWithServerS2CPacket;
import net.dillon.speedrunnermod.packet.serverbound.ClientPreferencesC2SPacket;
import net.dillon.speedrunnermod.packet.serverbound.MatchServerOptionsWithClientC2SPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.server.IntegratedServer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.permissions.PermissionSet;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.component.SwingAnimation;

import java.util.Timer;
import java.util.TimerTask;

import static net.dillon.dillonlib.task.ClientTasks.executeIfClientPlayer;
import static net.dillon.speedrunnermod.main.ClientMain.client;
import static net.dillon.speedrunnermod.main.CommonMain.common;
import static net.dillon.speedrunnermod.main.CommonMain.commonConfigHandler;
import static net.dillon.speedrunnermod.option.ModClientOptions.isOverlay;

/**
 * Client-side events for The Speedrunner Mod.
 */
public class ClientEvents {

    /**
     * Handles checking the player's mode.
     */
    public static void handleCheckMode(CheckModeS2CPacket packet) {
        if (common().general().mode != packet.serverSideMode()) {
            net.minecraft.client.Minecraft.getInstance().getConnection().getConnection().disconnect(Component.translatable("speedrunnermod.mode.doesnt_match_server"));
            net.minecraft.client.Minecraft.getInstance().disconnect(new net.dillon.speedrunnermod.screen.synced.ModeDoesntMatchScreen(packet.serverSideMode()), false, false);
        }
    }

    /**
     * Handles matching the user's (client) options with the server-side options, client-side safe.
     */
    public static void handleMatchUserOptionsWithServer(MatchClientOptionsWithServerS2CPacket packet) {
        ModCommonOptions serverOptions = packet.toOptions();
        commonConfigHandler().match(serverOptions);
        net.minecraft.client.Minecraft.getInstance().getConnection().getConnection().disconnect(ModTexts.MATCHED_SETTINGS_WITH_SERVER);
        net.minecraft.client.Minecraft.getInstance().disconnect(new net.dillon.speedrunnermod.screen.synced.TimedScreen(null, 5, true), false, false);
    }

    /**
     * Handles opening feature screen, client-side safe.

     */
    public static void handleOpenFeaturesScreen(Player player) {
        player.swing(InteractionHand.MAIN_HAND, SwingAnimation.DEFAULT, true);
        net.minecraft.client.Minecraft.getInstance().gui.setScreen(new net.dillon.speedrunnermod.screen.feature.FeaturesScreen(null));
    }

    /**
     * Handles matching and requesting the client-side options with the server options, client-side safe.
     */
    public static void handleMatchClientOptionsWithServer(Player player) {
        Balm.networking().sendToServer(MatchServerOptionsWithClientC2SPacket.from(common(), player.getName().getString()));
        player.sendSystemMessage(Component.translatable("speedrunnermod.client_options_sent"));
    }

    /**
     * Registers {@code client-side join and disconnect} events.
     */
    public static void registerClientJoinAndDisconnectEvents() {
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            ModHelper.errorMessagesSent = 0;
            sendNewC2SOptions();

            executeIfClientPlayer(player -> {
                int delayInTicks = 150;
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        syncFwc(client, delayInTicks);
                    }
                }, delayInTicks);

                if (ModConstants.HAS_UPDATE) {
                    CommonTasks.sendUpdateMessage(player, ModTexts.TITLE.copy().withStyle(ChatFormatting.AQUA), "https://modrinth.com/mod/speedrunner-mod/versions", TextColor.AQUA.getValue());
                }
            });
        });
    }

    /**
     * Syncs the {@code fast world creation} options with the world.
     */
    public static void syncFwc(Minecraft client, int delayTicks) {
        IntegratedServer integratedServer = client.getSingleplayerServer();
        if (integratedServer != null) {
            integratedServer.setWorldAllowCommands(client().worldCreation().allowCommands);
            PermissionSet permissionPredicate = integratedServer.getProfilePermissions(client.player.nameAndId());
            client.player.setPermissions(permissionPredicate);

            for (ServerPlayer serverPlayerEntity : integratedServer.getPlayerList().getPlayers()) {
                integratedServer.getCommands().sendCommands(serverPlayerEntity);
            }

            CommonMain.LOGGER.info("Synced fast world creation settings with world in {} ticks.", delayTicks);
        }
    }

    /**
     * Updates client-sided options and sends to server-side.
     */
    public static void sendNewC2SOptions() {
        ModClientOptions.General options = client().general();
        Balm.networking().sendToServer(new ClientPreferencesC2SPacket(
                isOverlay(), options.warningMessages, options.iCarusFireworksInventorySlot, options.infiniPearlInventorySlot
        ));
    }
}