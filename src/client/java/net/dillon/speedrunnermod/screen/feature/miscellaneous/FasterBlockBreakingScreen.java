package net.dillon.speedrunnermod.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.screen.option.GeneralOptionsScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

public class FasterBlockBreakingScreen extends AbstractFeatureScreen {

    public FasterBlockBreakingScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_FASTER_BLOCK_BREAKING);
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(Button.builder(ModTexts.CONFIGURE_OPTION, button -> {
            this.refreshRestartableFeature(new GeneralOptionsScreen(this));
            ((GeneralOptionsScreen)this.minecraft.gui.screen()).searchField.setValue("block breaking");
        }).build());
    }

    @Override
    @NotNull
    public String linesKey() {
        return "faster_block_breaking";
    }

    @Override
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.MISCELLANEOUS;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}