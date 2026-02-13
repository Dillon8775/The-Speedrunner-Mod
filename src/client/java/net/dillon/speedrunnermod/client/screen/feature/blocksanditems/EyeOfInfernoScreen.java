package net.dillon.speedrunnermod.client.screen.feature.blocksanditems;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.NotNull;


public class EyeOfInfernoScreen extends AbstractFeatureScreen {

    public EyeOfInfernoScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_EYE_OF_INFERNO);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "eye_of_inferno";
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