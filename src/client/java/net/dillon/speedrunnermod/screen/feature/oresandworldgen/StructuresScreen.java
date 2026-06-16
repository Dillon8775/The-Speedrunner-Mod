package net.dillon.speedrunnermod.screen.feature.oresandworldgen;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.screen.option.WorldGenOptionsScreen;
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
            this.refreshRestartableFeature(new WorldGenOptionsScreen(this));
            ((WorldGenOptionsScreen)this.minecraft.gui.screen()).searchField.setValue("structures");
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