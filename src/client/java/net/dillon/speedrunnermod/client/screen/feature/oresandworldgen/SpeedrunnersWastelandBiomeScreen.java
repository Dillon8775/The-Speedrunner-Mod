package net.dillon.speedrunnermod.client.screen.feature.oresandworldgen;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Environment(EnvType.CLIENT)
public class SpeedrunnersWastelandBiomeScreen extends AbstractFeatureScreen {

    public SpeedrunnersWastelandBiomeScreen(Screen parent) {
        super(parent, Text.translatable("speedrunnermod.title.features.ores_and_worldgen.speedrunners_wasteland_biome"));
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(ButtonWidget.builder(options().main.customBiomesAndCustomBiomeFeatures ? ModTexts.STOP_SPEEDRUNNERS_WASTELAND_BIOME_FROM_GENERATING : ModTexts.ALLOW_SPEEDRUNNERS_WASTELAND_BIOME_TO_GENERATE, button -> {
            options().main.customBiomesAndCustomBiomeFeatures = !options().main.customBiomesAndCustomBiomeFeatures;
            this.refreshNonRestartableFeature();
        }).build());
    }

    @Override
    @NotNull
    public String linesKey() {
        return "speedrunners_wasteland_biome";
    }

    @Override
    public int getPageNumber() {
        return 1;
    }

    @Override
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.ORES_AND_WORLDGEN;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.STARTER;
    }
}