package net.dillon.speedrunnermod.screen.secretdoommode;

import net.dillon.speedrunnermod.screen.ScreenType;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class UmScreen extends AbstractSecretDoomModeScreen {

    public UmScreen(Screen parent) {
        super(parent);
    }

    @Override
    protected Component getButtonText() {
        return Component.translatable("speedrunnermod.doom_mode_screen.line7.reply");
    }

    @Override
    public @NotNull String linesKey() {
        return "um";
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}