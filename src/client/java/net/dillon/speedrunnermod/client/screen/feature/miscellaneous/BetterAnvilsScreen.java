package net.dillon.speedrunnermod.client.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.client.screen.options.MainOptionsScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.jetbrains.annotations.NotNull;

public class BetterAnvilsScreen extends AbstractFeatureScreen {

    public BetterAnvilsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_BETTER_ANVILS);
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(ButtonWidget.builder(ModTexts.CONFIGURE_OPTION, button -> {
            this.client.setScreen(new MainOptionsScreen(this));
            ((MainOptionsScreen) this.client.currentScreen).buttonList.setScrollY(220);
            ((MainOptionsScreen) this.client.currentScreen).searchField.setText("an");
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