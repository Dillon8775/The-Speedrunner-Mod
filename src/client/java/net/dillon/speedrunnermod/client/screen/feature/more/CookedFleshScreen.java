package net.dillon.speedrunnermod.client.screen.feature.more;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class CookedFleshScreen extends AbstractFeatureScreen {

    public CookedFleshScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.more.cooked_flesh"));
    }

    @Override
    public @NotNull String linesKey() {
        return "cooked_flesh";
    }

    @Override
    public int getPageNumber() {
        return 12;
    }

    @Override
    public @NotNull ScreenCategory getScreenCategory() {
        return ScreenCategory.MORE;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.NORMAL;
    }
}