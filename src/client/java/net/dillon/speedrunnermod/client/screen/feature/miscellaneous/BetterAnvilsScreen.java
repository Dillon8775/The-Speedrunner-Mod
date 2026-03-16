package net.dillon.speedrunnermod.client.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.client.screen.options.MainOptionsScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

public class BetterAnvilsScreen extends AbstractFeatureScreen {

    public BetterAnvilsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_BETTER_ANVILS);
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(Button.builder(ModTexts.CONFIGURE_OPTION, button -> {
            this.minecraft.setScreen(new MainOptionsScreen(this));
            ((MainOptionsScreen) this.minecraft.screen).buttonList.setScrollAmount(220);
            ((MainOptionsScreen) this.minecraft.screen).searchField.setValue("an");
        }).build());
    }

    @Override
    public @NotNull String linesKey() {
        return "better_anvils";
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