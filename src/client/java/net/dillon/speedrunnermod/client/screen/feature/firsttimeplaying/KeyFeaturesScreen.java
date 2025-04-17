package net.dillon.speedrunnermod.client.screen.feature.firsttimeplaying;

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

@Environment(EnvType.CLIENT)
public class KeyFeaturesScreen extends AbstractFeatureScreen {

    public KeyFeaturesScreen(Screen parent) {
        super(parent, ModTexts.BLANK);
    }

    @Override
    protected void init() {
        super.init();
        this.addButtonObject(ButtonWidget.builder(Text.translatable("speedrunnermod.next"), button -> {
            this.client.setScreen(this.getNextScreen());
        }).build());
        this.addButtonObject(ButtonWidget.builder(Text.translatable("speedrunnermod.back"), button -> {
            this.client.setScreen(this.getPreviousScreen());
        }).build());
    }

    @Override
    public @NotNull String linesKey() {
        return "key_points";
    }

    @Override
    public int getPageNumber() {
        return 2;
    }

    @Override
    public @NotNull ScreenCategory getScreenCategory() {
        return ScreenCategory.FIRST_TIME_PLAYING;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.FIRST_TIME_PLAYING;
    }
}