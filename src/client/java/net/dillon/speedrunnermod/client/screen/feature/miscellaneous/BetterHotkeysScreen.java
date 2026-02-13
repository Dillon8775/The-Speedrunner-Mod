package net.dillon.speedrunnermod.client.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.NotNull;


public class BetterHotkeysScreen extends AbstractFeatureScreen {

    public BetterHotkeysScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_BETTER_HOTKEYS);
    }

    @Override
    public @NotNull String linesKey() {
        return "better_hotkeys";
    }

    @Override
    public @NotNull ScreenCategory getScreenCategory() {
        return ScreenCategory.MISCELLANEOUS;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}