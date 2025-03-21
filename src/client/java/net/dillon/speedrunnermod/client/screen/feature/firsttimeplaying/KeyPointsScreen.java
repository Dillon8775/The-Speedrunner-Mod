package net.dillon.speedrunnermod.client.screen.feature.firsttimeplaying;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class KeyPointsScreen extends AbstractFeatureScreen {

    public KeyPointsScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.BLANK, false, false);
    }

    @Override
    protected void addButtons() {
        this.buttons.add(ButtonWidget.builder(Text.translatable("speedrunnermod.next"), button -> {
            this.client.setScreen(this.getNextScreen());
        }).build());
        this.buttons.add(ButtonWidget.builder(Text.translatable("speedrunnermod.back"), button -> {
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
    protected Identifier getImage() {
        return null;
    }

    @Override
    protected Identifier getCraftingRecipeImage() {
        return null;
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