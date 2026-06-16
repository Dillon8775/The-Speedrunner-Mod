package net.dillon.speedrunnermod.screen.feature.blocksanditems;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

public class EyeOfAnnulScreen extends AbstractFeatureScreen {

    public EyeOfAnnulScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_EYE_OF_ANNUL);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "eye_of_annul";
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