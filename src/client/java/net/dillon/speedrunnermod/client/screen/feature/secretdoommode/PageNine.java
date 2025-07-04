package net.dillon.speedrunnermod.client.screen.feature.secretdoommode;

import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class PageNine extends AbstractSecretDoomModeScreen {

    public PageNine(Screen parent) {
        super(parent);
    }

    @Override
    protected void getButtonFunction() {
        this.close();
        doomModeButtonAlreadyClicked = 0;
    }

    @Override
    protected Text getButtonText() {
        return ModTexts.OK;
    }

    @Override
    public int getPageNumber() {
        return 9;
    }

    @Override
    public @NotNull String linesKey() {
        return "page_nine";
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.LAST_PAGE;
    }
}