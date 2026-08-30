package net.dillon.speedrunnermod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.dillon.speedrunnermod.network.DedicatedServerStorage;
import net.dillon.speedrunnermod.option.CommonModOptions;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.commonConfigHandler;

/**
 * The command to authorize a {@code syncoptions} request.
 */
public class SyncOptionsAuthorizeCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("syncoptionsauthorize")
                .requires(source -> !source.isPlayer())
                .then(Commands.argument("player", StringArgumentType.word())
                        .then(Commands.argument("action", StringArgumentType.word())
                                .executes(context -> {
                                    String player = StringArgumentType.getString(context, "player");
                                    String action = StringArgumentType.getString(context, "action");

                                    if (!DedicatedServerStorage.hasPendingSyncRequest(player)) {
                                        context.getSource().sendFailure(Component.literal("No pending sync request from " + player + "."));
                                        return 0;
                                    }

                                    if (action.equalsIgnoreCase("accept")) {
                                        CommonModOptions clientOptions = DedicatedServerStorage.getPendingSyncRequest(player);
                                        commonConfigHandler().match(clientOptions);

                                        // Disconnect all players
                                        for (ServerPlayer p : context.getSource().getServer().getPlayerList().getPlayers()) {
                                            p.connection.disconnect(Component.translatable("speedrunnermod.server_closed_sync_options", player));
                                        }

                                        context.getSource().sendSystemMessage(Component.translatable("speedrunnermod.closing_server_sync_options", player));
                                        context.getSource().getServer().halt(false);

                                    } else if (action.equalsIgnoreCase("deny")) {
                                        // Notify the requesting player
                                        ServerPlayer requestingPlayer = context.getSource().getServer().getPlayerList().getPlayerByName(player);
                                        if (requestingPlayer != null) {
                                            requestingPlayer.sendSystemMessage(Component.translatable("speedrunnermod.sync_options_request_denied", player), false);
                                        }
                                        context.getSource().sendSystemMessage(Component.translatable("speedrunnermod.denied_sync_options_request", player));
                                    }

                                    DedicatedServerStorage.removePendingSyncRequest(player);
                                    return 1;
                                }))));
    }
}