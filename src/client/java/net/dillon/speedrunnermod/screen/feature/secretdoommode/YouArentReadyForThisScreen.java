package net.dillon.speedrunnermod.screen.feature.secretdoommode;

import net.dillon.speedrunnermod.screen.ScreenType;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class YouArentReadyForThisScreen extends AbstractSecretDoomModeScreen {

    public YouArentReadyForThisScreen(Screen parent) {
        super(parent);
    }

    @Override
    protected Component getButtonText() {
        return Component.translatable("speedrunnermod.doom_mode_screen.line1.reply");
    }

    @Override
    public @NotNull String linesKey() {
        return "you_arent_ready_for_this";
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.FIRST_PAGE;
    }
}