package net.dillon.speedrunnermod.client.screen.feature.secretdoommode;

import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class PageFive extends AbstractSecretDoomModeScreen {

    public PageFive(Screen parent) {
        super(parent);
    }

    @Override
    protected Text getButtonText() {
        return Text.translatable("speedrunnermod.doom_mode_screen.line7.reply");
    }

    @Override
    public int getPageNumber() {
        return 5;
    }

    @Override
    public @NotNull String linesKey() {
        return "page_five";
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}