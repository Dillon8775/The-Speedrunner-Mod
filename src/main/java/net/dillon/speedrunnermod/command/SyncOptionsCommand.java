package net.dillon.speedrunnermod.command;

import com.mojang.brigadier.CommandDispatcher;
import net.dillon.speedrunnermod.packet.client.RequestClientSideOptionsS2CPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

/**
 * The command to request sync server options with request player's options.
 */
public class SyncOptionsCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("syncoptions")
                        .requires(source -> source.hasPermissionLevel(4))
                        .executes(context -> {
                            ServerPlayNetworking.send(context.getSource().getPlayer(), new RequestClientSideOptionsS2CPacket());
                            return 1;
        }));
    }
}