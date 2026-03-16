package net.dillon.speedrunnermod.client.screen.feature.secretdoommode;

import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;


public class ExpectTheUnexpectedScreen extends AbstractSecretDoomModeScreen {

    public ExpectTheUnexpectedScreen(Screen parent) {
        super(parent);
    }

    @Override
    protected void getButtonFunction() {
        this.onClose();
        doomModeButtonAlreadyClicked = 1;
    }

    @Override
    protected Component getButtonText() {
        return ModTexts.OK;
    }

    @Override
    public @NotNull String linesKey() {
        return "expect_the_unexpected";
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}