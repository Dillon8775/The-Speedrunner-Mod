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
public class NoMorePiglinBrutesScreen extends AbstractFeatureScreen {

    public NoMorePiglinBrutesScreen(Screen parent) {
        super(parent, Text.translatable("speedrunnermod.title.features.miscellaneous.no_more_piglin_brutes"));
    }

    @Override
    @NotNull
    public String linesKey() {
        return "no_more_piglin_brutes";
    }

    @Override
    public int getPageNumber() {
        return 10;
    }

    @Override
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.MISCELLANEOUS;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.NORMAL;
    }
}