package net.dillon.speedrunnermod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.server.ServerStorage;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.configHandler;

/**
 * The command to authorize a {@code syncoptions} request.
 */
public class SyncOptionsAuthorizeCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("syncoptionsauthorize")
                .requires(source -> !source.isExecutedByPlayer())
                .then(CommandManager.argument("player", StringArgumentType.word())
                        .then(CommandManager.argument("action", StringArgumentType.word())
                                .executes(context -> {
                                    String player = StringArgumentType.getString(context, "player");
                                    String action = StringArgumentType.getString(context, "action");

                                    if (!ServerStorage.hasPendingSyncRequest(player)) {
                                        context.getSource().sendError(Text.literal("No pending sync request from " + player + "."));
                                        return 0;
                                    }

                                    if (action.equalsIgnoreCase("accept")) {
                                        ModOptions clientOptions = ServerStorage.getPendingSyncRequest(player);
                                        configHandler().match(clientOptions);

                                        // Disconnect all players
                                        for (ServerPlayerEntity p : context.getSource().getServer().getPlayerManager().getPlayerList()) {
                                            p.networkHandler.disconnect(Text.translatable("speedrunnermod.server_closed_sync_options", player));
                                        }

                                        context.getSource().sendMessage(Text.translatable("speedrunnermod.closing_server_sync_options", player));
                                        context.getSource().getServer().stop(false);

                                    } else if (action.equalsIgnoreCase("deny")) {
                                        // Notify the requesting player
                                        ServerPlayerEntity requestingPlayer = context.getSource().getServer().getPlayerManager().getPlayer(player);
                                        if (requestingPlayer != null) {
                                            requestingPlayer.sendMessage(Text.translatable("speedrunnermod.sync_options_request_denied", player), false);
                                        }
                                        context.getSource().sendMessage(Text.translatable("speedrunnermod.denied_sync_options_request", player));
                                    }

                                    ServerStorage.removePendingSyncRequest(player);
                                    return 1;
                                }))));
    }
}