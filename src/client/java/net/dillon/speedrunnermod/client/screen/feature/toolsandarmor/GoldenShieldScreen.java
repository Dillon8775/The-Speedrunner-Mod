package net.dillon.speedrunnermod.client.screen.feature.toolsandarmor;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public class GoldenShieldScreen extends AbstractFeatureScreen {

    public GoldenShieldScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_SPEEDRUNNER_SHIELD);
    }

    @Override
    public @NotNull String linesKey() {
        return "golden_shield";
    }

    @Override
    public @NotNull ScreenCategory getScreenCategory() {
        return ScreenCategory.TOOLS_AND_ARMOR;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}