package net.dillon.speedrunnermod.client.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

public class BetterFoodsScreen extends AbstractFeatureScreen {

    public BetterFoodsScreen(Screen parent) {
        super(parent, Text.translatable("speedrunnermod.title.features.miscellaneous.better_foods"));
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(ButtonWidget.builder(options().main.betterFoods.getCurrentValue() ? ModTexts.DISABLE_THIS_FEATURE : ModTexts.ENABLE_THIS_FEATURE, button -> {
            options().main.betterFoods.set(!options().main.betterFoods.getCurrentValue());
            this.refreshNonRestartableFeature();
        }).build());
    }

    @Override
    public int getPageNumber() {
        return 25;
    }

    @Override
    public @NotNull String linesKey() {
        return "better_foods";
    }

    @Override
    public @NotNull ScreenCategory getScreenCategory() {
        return ScreenCategory.MISCELLANEOUS;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}