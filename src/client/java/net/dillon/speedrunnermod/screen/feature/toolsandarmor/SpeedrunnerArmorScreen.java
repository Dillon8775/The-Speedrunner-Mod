package net.dillon.speedrunnermod.screen.feature.toolsandarmor;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

public class SpeedrunnerArmorScreen extends AbstractFeatureScreen {

    public SpeedrunnerArmorScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_SPEEDRUNNER_ARMOR);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "speedrunner_armor";
    }

    @Override
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.TOOLS_AND_ARMOR;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.FIRST_PAGE;
    }
}