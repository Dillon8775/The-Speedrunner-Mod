package net.dillon.speedrunnermod.client.screen.feature.miscellaneous;

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

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Environment(EnvType.CLIENT)
public class InfiniPearlModeScreen extends AbstractFeatureScreen {

    public InfiniPearlModeScreen(Screen parent) {
        super(parent, Text.translatable("speedrunnermod.title.features.miscellaneous.infini_pearl_mode"));
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(ButtonWidget.builder(options().main.infiniPearlMode.getCurrentValue() ? ModTexts.DISABLE_INFINI_PEARL_MODE : ModTexts.ENABLE_INFINI_PEARL_MODE, button -> {
            options().main.infiniPearlMode.set(!options().main.infiniPearlMode.getCurrentValue());
            this.refreshNonRestartableFeature();
        }).build());
    }

    @Override
    @NotNull
    public String linesKey() {
        return "infini_pearl_mode";
    }

    @Override
    public int getPageNumber() {
        return 7;
    }

    @Override
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.MISCELLANEOUS;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}