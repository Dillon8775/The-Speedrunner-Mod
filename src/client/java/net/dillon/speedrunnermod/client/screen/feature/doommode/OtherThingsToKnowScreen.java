package net.dillon.speedrunnermod.client.screen.feature.doommode;

import net.dillon.speedrunnermod.client.screen.base.option.RestartRequiredScreen;
import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.jetbrains.annotations.NotNull;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Environment(EnvType.CLIENT)
public class OtherThingsToKnowScreen extends AbstractFeatureScreen {

    public OtherThingsToKnowScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_DOOM_MODE_OTHER_THINGS_TO_KNOW);
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(ButtonWidget.builder(ModTexts.OK, button -> this.close()).build());
        ButtonWidget enableDoomMode = this.addButtonObject(ButtonWidget.builder(ModTexts.ENABLE_DOOM_MODE, button -> {
            RestartRequiredScreen.getCurrentOptions();
            if (!isDoomMode()) {
                this.client.setScreen(new RestartRequiredScreen(this));
            }
            options().main.mode.set(ModOptions.Mode.DOOM);
        }).build());
        enableDoomMode.active = !isDoomMode();
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
        return ScreenType.DEFAULT;
    }
}