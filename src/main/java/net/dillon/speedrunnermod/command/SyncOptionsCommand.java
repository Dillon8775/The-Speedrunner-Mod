package net.dillon.speedrunnermod.command;

import com.mojang.brigadier.CommandDispatcher;
import net.dillon.speedrunnermod.network.client.RequestClientSideOptionsS2CPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

/**
 * The command to request sync server options with request player's options.
 */
public class SyncOptionsCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("syncoptions")
                        .requires(Commands.hasPermission(Commands.LEVEL_OWNERS))
                        .executes(context -> {
                            ServerPlayNetworking.send(context.getSource().getPlayer(), new RequestClientSideOptionsS2CPacket());
                            return 1;
        }));
    }
}