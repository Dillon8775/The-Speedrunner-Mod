package net.dillon.speedrunnermod.client.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class RetiredSpeedrunnerScreen extends AbstractFeatureScreen {

    public RetiredSpeedrunnerScreen(Screen parent) {
        super(parent, Text.translatable("speedrunnermod.title.features.miscellaneous.retired_speedrunner"));
    }

    @Override
    @NotNull
    public String linesKey() {
        return "retired_speedrunner";
    }

    @Override
    public int getPageNumber() {
        return 20;
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