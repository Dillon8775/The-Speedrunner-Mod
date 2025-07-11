package net.dillon.speedrunnermod.command;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

/**
 * All Speedrunner Mod commands.
 */
public class ModCommands {

    public static void registerCommands() {
        CommandRegistrationCallback.EVENT.register((commandDispatcher, commandRegistryAccess, registrationEnvironment) -> {
            SyncOptionsCommand.register(commandDispatcher);
        });
        CommandRegistrationCallback.EVENT.register((commandDispatcher, commandRegistryAccess, registrationEnvironment) -> {
            SyncOptionsAuthorizeCommand.register(commandDispatcher);
        });
    }
}