package net.dillon.speedrunnermod.client.screen.feature.doommode;

import net.dillon.speedrunnermod.client.screen.base.RestartRequiredScreen;
import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Environment(EnvType.CLIENT)
public class OtherThingsToKnowScreen extends AbstractFeatureScreen {

    public OtherThingsToKnowScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.doom_mode.other_things_to_know"));
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(ButtonWidget.builder(ModTexts.OK, button -> this.close()).build());
        ButtonWidget enableDoomMode = this.addButtonObject(ButtonWidget.builder(ModTexts.ENABLE_DOOM_MODE, button -> {
            if (!options().main.playingMode.doom()) {
                this.client.setScreen(new RestartRequiredScreen(this.parent, MinecraftClient.getInstance().options));
            }
            options().main.playingMode = ModOptions.PlayingMode.DOOM;
        }).build());
        enableDoomMode.active = !options().main.playingMode.doom();
    }

    @Override
    @NotNull
    public String linesKey() {
        return "other_things_to_know";
    }

    @Override
    public int getPageNumber() {
        return this.getMaxPages();
    }

    @Override
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.DOOM_MODE;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.NORMAL;
    }
}