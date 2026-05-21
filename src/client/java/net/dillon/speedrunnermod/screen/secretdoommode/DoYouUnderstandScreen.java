package net.dillon.speedrunnermod.screen.secretdoommode;

import net.dillon.speedrunnermod.screen.ScreenType;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class DoYouUnderstandScreen extends AbstractSecretDoomModeScreen {

    public DoYouUnderstandScreen(Screen parent) {
        super(parent);
    }

    @Override
    protected Component getButtonText() {
        return Component.translatable("speedrunnermod.doom_mode_screen.line2.reply");
    }

    @Override
    public @NotNull String linesKey() {
        return "do_you_understand";
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}