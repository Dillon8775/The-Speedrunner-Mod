package net.dillon.speedrunnermod.screen.feature.oresandworldgen;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.screen.option.WorldGenOptionsScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

public class BetterBiomesScreen extends AbstractFeatureScreen {

    public BetterBiomesScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_BETTER_BIOMES);
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(Button.builder(options().worldGen.betterBiomes.getCurrentValue() ? ModTexts.DISABLE_THIS_FEATURE : ModTexts.ENABLE_THIS_FEATURE, button -> {
            this.refreshRestartableFeature(new WorldGenOptionsScreen(this));
            options().worldGen.betterBiomes.set(!options().worldGen.betterBiomes.getCurrentValue());
            ((WorldGenOptionsScreen)this.minecraft.gui.screen()).searchField.setValue("better biomes");
        }).build());
    }

    @Override
    public @NotNull String linesKey() {
        return "better_biomes";
    }

    @Override
    public @NotNull ScreenCategory getScreenCategory() {
        return ScreenCategory.ORES_AND_WORLDGEN;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}