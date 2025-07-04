package net.dillon.speedrunnermod.client.screen.feature.secretdoommode;

import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class PageTwo extends AbstractSecretDoomModeScreen {

    public PageTwo(Screen parent) {
        super(parent);
    }

    @Override
    protected Text getButtonText() {
        return Text.translatable("speedrunnermod.doom_mode_screen.line2.reply");
    }

    @Override
    public int getPageNumber() {
        return 2;
    }

    @Override
    public @NotNull String linesKey() {
        return "page_two";
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}