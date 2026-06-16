package net.dillon.speedrunnermod.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

public class ICarusModeScreen extends AbstractFeatureScreen {

    public ICarusModeScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_ICARUS_MODE);
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(Button.builder(options().general.iCarusMode.getCurrentValue() ? ModTexts.DISABLE_ICARUS_MODE : ModTexts.ENABLE_ICARUS_MODE, button -> {
            options().general.iCarusMode.set(!options().general.iCarusMode.getCurrentValue());
            this.refreshNonRestartableFeature();
        }).build());
    }

    @Override
    @NotNull
    public String linesKey() {
        return "icarus_mode";
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