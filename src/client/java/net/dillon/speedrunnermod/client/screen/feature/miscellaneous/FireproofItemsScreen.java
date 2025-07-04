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
public class FireproofItemsScreen extends AbstractFeatureScreen {

    public FireproofItemsScreen(Screen parent) {
        super(parent, Text.translatable("speedrunnermod.title.features.miscellaneous.fireproof_items"));
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(ButtonWidget.builder(options().main.fireproofItems.getCurrentValue() ? ModTexts.DISABLE_THIS_FEATURE : ModTexts.ENABLE_THIS_FEATURE, button -> {
            options().main.fireproofItems.set(!options().main.fireproofItems.getCurrentValue());
            this.refreshNonRestartableFeature();
        }).build());
    }

    @Override
    public @NotNull String linesKey() {
        return "fireproof_items";
    }

    @Override
    public int getPageNumber() {
        return 17;
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