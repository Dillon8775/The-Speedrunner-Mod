package net.dillon.speedrunnermod.screen.feature.oresandworldgen;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

public class SpeedrunnersWastelandBiomeScreen extends AbstractFeatureScreen {

    public SpeedrunnersWastelandBiomeScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_SPEEDRUNNERS_WASTELAND);
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(Button.builder(options().general.customBiomesAndCustomBiomeFeatures.getCurrentValue() ? ModTexts.STOP_SPEEDRUNNERS_WASTELAND_BIOME_FROM_GENERATING : ModTexts.ALLOW_SPEEDRUNNERS_WASTELAND_BIOME_TO_GENERATE, button -> {
            options().general.customBiomesAndCustomBiomeFeatures.set(!options().general.customBiomesAndCustomBiomeFeatures.getCurrentValue());
            this.refreshNonRestartableFeature();
        }).build());
    }

    @Override
    @NotNull
    public String linesKey() {
        return "speedrunners_wasteland_biome";
    }

    @Override
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.ORES_AND_WORLDGEN;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.FIRST_PAGE;
    }
}