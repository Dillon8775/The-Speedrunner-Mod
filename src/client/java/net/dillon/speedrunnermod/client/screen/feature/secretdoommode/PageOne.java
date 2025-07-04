package net.dillon.speedrunnermod.client.screen.feature.secretdoommode;

import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class PageOne extends AbstractSecretDoomModeScreen {

    public PageOne(Screen parent) {
        super(parent);
    }

    @Override
    protected Text getButtonText() {
        return Text.translatable("speedrunnermod.doom_mode_screen.line1.reply");
    }

    @Override
    public int getPageNumber() {
        return 1;
    }

    @Override
    public @NotNull String linesKey() {
        return "page_one";
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.FIRST_PAGE;
    }
}