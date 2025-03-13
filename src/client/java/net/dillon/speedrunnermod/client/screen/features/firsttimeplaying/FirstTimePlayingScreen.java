package net.dillon.speedrunnermod.client.screen.features.firsttimeplaying;

import net.dillon.speedrunnermod.client.screen.features.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.features.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.features.ScreenType;
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
public class FirstTimePlayingScreen extends AbstractFeatureScreen {

    public FirstTimePlayingScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.BLANK, false, false);
    }

    @Override
    protected void addButtons() {
        this.buttons.add(ButtonWidget.builder(Text.translatable("speedrunnermod.lets_go"),button -> {
            this.client.setScreen(this.getNextScreen());
        }).build());
    }

    @Override
    public @NotNull String linesKey() {
        return "first_time_playing.welcome";
    }

    @Override
    public int getPageNumber() {
        return 1;
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

    @Override
    protected boolean shouldRenderTitleText() {
        return false;
    }
}