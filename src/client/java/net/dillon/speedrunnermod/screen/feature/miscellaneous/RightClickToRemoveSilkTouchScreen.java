package net.dillon.speedrunnermod.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

public class RightClickToRemoveSilkTouchScreen extends AbstractFeatureScreen {

    public RightClickToRemoveSilkTouchScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_RIGHT_CLICK_TO_REMOVE_SILK_TOUCH);
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(Button.builder(options().general.rightClickToRemoveSilkTouch.getCurrentValue() ? ModTexts.DISABLE_THIS_FEATURE : ModTexts.ENABLE_THIS_FEATURE, button -> {
            options().general.rightClickToRemoveSilkTouch.set(!options().general.rightClickToRemoveSilkTouch.getCurrentValue());
            this.refreshNonRestartableFeature();
        }).build());
    }

    @Override
    public @NotNull String linesKey() {
        return "right_click_to_remove_silk_touch";
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