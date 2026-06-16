package net.dillon.speedrunnermod.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

public class FireproofItemsScreen extends AbstractFeatureScreen {

    public FireproofItemsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_FIREPROOF_ITEMS);
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(Button.builder(options().general.fireproofItems.getCurrentValue() ? ModTexts.DISABLE_THIS_FEATURE : ModTexts.ENABLE_THIS_FEATURE, button -> {
            options().general.fireproofItems.set(!options().general.fireproofItems.getCurrentValue());
            this.refreshNonRestartableFeature();
        }).build());
    }

    @Override
    public @NotNull String linesKey() {
        return "fireproof_items";
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