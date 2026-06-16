package net.dillon.speedrunnermod.screen.feature.blocksanditems;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

public class DeadSpeedrunnerWoodScreen extends AbstractFeatureScreen {

    public DeadSpeedrunnerWoodScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_DEAD_SPEEDRUNNER_WOOD);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "dead_speedrunner_wood";
    }

    @Override
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.BLOCKS_AND_ITEMS;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}