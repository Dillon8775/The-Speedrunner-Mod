package net.dillon.speedrunnermod.client.screen.feature.oresandworldgen;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
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

        this.addButtonObject(Button.builder(options().main.betterBiomes.getCurrentValue() ? ModTexts.DISABLE_THIS_FEATURE : ModTexts.ENABLE_THIS_FEATURE, button -> {
            this.refreshRestartableFeature();
            options().main.betterBiomes.set(!options().main.betterBiomes.getCurrentValue());
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