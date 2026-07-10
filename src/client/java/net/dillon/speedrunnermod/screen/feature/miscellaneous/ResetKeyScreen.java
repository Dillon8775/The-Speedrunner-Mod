package net.dillon.speedrunnermod.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.dillon.speedrunnermod.screen.option.WorldCreationOptionsScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;

public class ResetKeyScreen extends DefaultMiscellaneousFeatureFactory {

    public ResetKeyScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(Button.builder(ModTexts.CONFIGURE_OPTION, button -> {
            this.minecraft.gui.setScreen(new WorldCreationOptionsScreen(this));
        }).build());
    }
}