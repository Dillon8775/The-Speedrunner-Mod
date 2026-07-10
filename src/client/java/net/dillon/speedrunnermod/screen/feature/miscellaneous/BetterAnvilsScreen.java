package net.dillon.speedrunnermod.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.dillon.speedrunnermod.screen.option.GeneralOptionsScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;

public class BetterAnvilsScreen extends DefaultMiscellaneousFeatureFactory {

    public BetterAnvilsScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(Button.builder(ModTexts.CONFIGURE_OPTION, button -> {
            this.minecraft.gui.setScreen(new GeneralOptionsScreen(this));
            ((GeneralOptionsScreen) this.minecraft.gui.screen()).buttonList.setScrollAmount(220);
            ((GeneralOptionsScreen) this.minecraft.gui.screen()).searchField.setValue("an");
        }).build());
    }
}