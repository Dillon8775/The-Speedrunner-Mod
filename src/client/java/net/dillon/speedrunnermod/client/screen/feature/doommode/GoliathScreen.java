package net.dillon.speedrunnermod.client.screen.feature.doommode;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class GoliathScreen extends AbstractFeatureScreen {

    public GoliathScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_DOOM_MODE_GOLIATH);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "goliath";
    }

    @Override
    public int getPageNumber() {
        return 3;
    }

    @Override
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.DOOM_MODE;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}