package net.dillon.speedrunnermod.client.screen.feature.secretdoommode;

import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class PageSix extends AbstractSecretDoomModeScreen {

    public PageSix(Screen parent) {
        super(parent);
    }

    @Override
    protected Text getButtonText() {
        return Text.translatable("speedrunnermod.doom_mode_screen.line8.reply");
    }

    @Override
    public int getPageNumber() {
        return 6;
    }

    @Override
    public @NotNull String linesKey() {
        return "page_six";
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}