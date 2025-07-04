package net.dillon.speedrunnermod.client.screen.feature.secretdoommode;

import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class PageThree extends AbstractSecretDoomModeScreen {

    public PageThree(Screen parent) {
        super(parent);
    }

    @Override
    protected Text getButtonText() {
        return Text.translatable("speedrunnermod.doom_mode_screen.line3.reply");
    }

    @Override
    public int getPageNumber() {
        return 3;
    }

    @Override
    public @NotNull String linesKey() {
        return "page_three";
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}