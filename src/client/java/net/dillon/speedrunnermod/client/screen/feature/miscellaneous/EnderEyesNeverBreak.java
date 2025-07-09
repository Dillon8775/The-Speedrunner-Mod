package net.dillon.speedrunnermod.client.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class EnderEyesNeverBreak extends AbstractFeatureScreen {

    public EnderEyesNeverBreak(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_ENDER_EYES_NEVER_BREAK);
    }

    @Override
    public @NotNull String linesKey() {
        return "ender_eyes_never_break";
    }

    @Override
    public int getPageNumber() {
        return 30;
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