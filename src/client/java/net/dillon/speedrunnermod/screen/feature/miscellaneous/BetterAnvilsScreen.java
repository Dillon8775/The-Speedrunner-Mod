package net.dillon.speedrunnermod.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.screen.option.GeneralOptionsScreen;
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
            this.minecraft.gui.setScreen(new GeneralOptionsScreen(this));
            ((GeneralOptionsScreen) this.minecraft.gui.screen()).buttonList.setScrollAmount(220);
            ((GeneralOptionsScreen) this.minecraft.gui.screen()).searchField.setValue("an");
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