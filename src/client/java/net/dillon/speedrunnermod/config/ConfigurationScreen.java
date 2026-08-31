package net.dillon.speedrunnermod.config;

import dev.isxander.yacl3.api.YetAnotherConfigLib;
import net.dillon.speedrunnermod.network.ClientModPackets;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

import static net.dillon.dillonlib.task.ClientTasks.getMinecraft;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.commonConfigHandler;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientConfigHandler;

/**
 * The main configuration screen for the Speedrunner Mod.
 */
public class ConfigurationScreen {

    public static YetAnotherConfigLib configScreen() {
        return YetAnotherConfigLib.createBuilder()
                .title(Component.translatable("speedrunnermod.title"))
                .category(
                        GeneralCategory.create()
                )
                .category(
                        WorldGenCategory.create()
                )
                .category(
                        ClientCategory.create()
                )
                .category(
                        AccessibilityCategory.create()
                )
                .save(() -> {
                    clientConfigHandler().save();
                    commonConfigHandler().save();

                    Minecraft mc = getMinecraft();
                    boolean bl = mc.getSingleplayerServer() != null;
                    boolean bl2 = mc.level != null;
                    if (bl || bl2) {
                        ClientModPackets.sendNewC2SOptions();
                        if (bl2) {
                            ClientModPackets.syncFwc(getMinecraft(), 0);
                        }
                    }
                })
                .build();
    }
}