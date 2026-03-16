package net.dillon.speedrunnermod.client.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.client.screen.options.MainOptionsScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

public class LessFallDamageScreen extends AbstractFeatureScreen {

    public LessFallDamageScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_LESS_FALL_DAMAGE);
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(Button.builder(ModTexts.CONFIGURE_OPTION, button -> {
            this.refreshRestartableFeature();
            ((MainOptionsScreen)this.minecraft.screen).searchField.setValue("damage");
            ((MainOptionsScreen)this.minecraft.screen).buttonList.setScrollAmount(80);
        }).build());
    }

    @Override
    public @NotNull String linesKey() {
        return "less_fall_damage";
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