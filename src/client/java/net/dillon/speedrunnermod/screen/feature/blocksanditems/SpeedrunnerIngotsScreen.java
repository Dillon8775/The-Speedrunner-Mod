package net.dillon.speedrunnermod.screen.feature.blocksanditems;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

public class SpeedrunnerIngotsScreen extends AbstractFeatureScreen {

    public SpeedrunnerIngotsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_SPEEDRUNNER_INGOTS);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "speedrunner_ingots";
    }

    @Override
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.BLOCKS_AND_ITEMS;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.FIRST_PAGE;
    }
}