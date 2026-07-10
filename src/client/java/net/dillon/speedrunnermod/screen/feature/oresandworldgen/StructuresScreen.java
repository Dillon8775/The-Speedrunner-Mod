package net.dillon.speedrunnermod.screen.feature.oresandworldgen;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.dillon.speedrunnermod.screen.option.WorldGenOptionsScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;

public class StructuresScreen extends DefaultOresAndWorldGenFeatureFactory {

    public StructuresScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(Button.builder(ModTexts.CONFIGURE_OPTION, button -> {
            this.refreshRestartableFeature(new WorldGenOptionsScreen(this));
            ((WorldGenOptionsScreen)this.minecraft.gui.screen()).searchField.setValue("structures");
        }).build());
    }
}