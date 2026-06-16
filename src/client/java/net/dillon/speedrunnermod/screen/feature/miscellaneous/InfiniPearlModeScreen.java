package net.dillon.speedrunnermod.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

public class InfiniPearlModeScreen extends AbstractFeatureScreen {

    public InfiniPearlModeScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_INFINI_PEARL_MODE);
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(Button.builder(options().general.infiniPearlMode.getCurrentValue() ? ModTexts.DISABLE_INFINI_PEARL_MODE : ModTexts.ENABLE_INFINI_PEARL_MODE, button -> {
            options().general.infiniPearlMode.set(!options().general.infiniPearlMode.getCurrentValue());
            this.refreshNonRestartableFeature();
        }).build());
    }

    @Override
    @NotNull
    public String linesKey() {
        return "infini_pearl_mode";
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