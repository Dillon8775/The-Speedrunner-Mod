package net.dillon.speedrunnermod.client.screen.feature.oresandworldgen;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.client.screen.options.MainOptionsScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;


public class StructuresScreen extends AbstractFeatureScreen {

    public StructuresScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_STRUCTURES);
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(Button.builder(ModTexts.CONFIGURE_OPTION, button -> {
            this.refreshRestartableFeature();
            ((MainOptionsScreen)this.minecraft.screen).searchField.setValue("structures");
        }).build());
    }

    @Override
    @NotNull
    public String linesKey() {
        return "structures";
    }

    @Override
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.ORES_AND_WORLDGEN;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}