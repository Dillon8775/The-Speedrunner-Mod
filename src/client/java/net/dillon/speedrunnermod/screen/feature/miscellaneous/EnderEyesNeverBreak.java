package net.dillon.speedrunnermod.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

public class EnderEyesNeverBreak extends AbstractFeatureScreen {

    public EnderEyesNeverBreak(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_ENDER_EYES_NEVER_BREAK);
    }

    @Override
    public @NotNull String linesKey() {
        return "ender_eyes_never_break";
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