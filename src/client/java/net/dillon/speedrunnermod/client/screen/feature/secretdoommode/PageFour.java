package net.dillon.speedrunnermod.client.screen.feature.secretdoommode;

import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class PageFour extends AbstractSecretDoomModeScreen {

    public PageFour(Screen parent) {
        super(parent);
    }

    @Override
    protected void getButtonFunction() {
        this.close();
        doomModeButtonAlreadyClicked = 1;
    }

    @Override
    protected Text getButtonText() {
        return ModTexts.OK;
    }

    @Override
    public int getPageNumber() {
        return 4;
    }

    @Override
    public @NotNull String linesKey() {
        return "page_four";
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}