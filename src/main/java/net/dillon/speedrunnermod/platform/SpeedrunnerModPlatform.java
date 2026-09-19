package net.dillon.speedrunnermod.platform;

import com.mojang.brigadier.CommandDispatcher;
import net.dillon.dillonlib.platform.ModPlatform;
import net.dillon.dillonlib.platform.Platforms;
import net.dillon.dillonlib.platform.info.Platform;
import net.dillon.dillonlib.platform.info.Release;
import net.dillon.speedrunnermod.command.SyncOptionsAuthorizeCommand;
import net.dillon.speedrunnermod.command.SyncOptionsCommand;
import net.dillon.speedrunnermod.helper.ModConstants;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;

public class SpeedrunnerModPlatform extends ModPlatform {

    @Override
    public void registerCommonCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess) {
        SyncOptionsCommand.register(dispatcher);
    }

    @Override
    public void registerServerCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess) {
        SyncOptionsAuthorizeCommand.register(dispatcher);
    }

    @Override
    public String modId() {
        return ModConstants.MOD_ID;
    }

    @Override
    public String modVersion() {
        return Platforms.getCommonPlatform().commonModVersion(ModConstants.MOD_ID);
    }

    @Override
    public Release release() {
        return Release.STABLE;
    }

    @Override
    public Platform platform() {
        return Platform.FABRIC;
    }
}