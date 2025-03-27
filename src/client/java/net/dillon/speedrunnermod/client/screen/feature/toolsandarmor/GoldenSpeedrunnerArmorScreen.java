package net.dillon.speedrunnermod.client.screen.feature.toolsandarmor;

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
public class GoldenSpeedrunnerArmorScreen extends AbstractFeatureScreen {

    public GoldenSpeedrunnerArmorScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.tools_and_armor.golden_speedrunner_armor"));
    }

    @Override
    @NotNull
    public String linesKey() {
        return "golden_speedrunner_armor";
    }

    @Override
    public int getPageNumber() {
        return 2;
    }

    @Override
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.TOOLS_AND_ARMOR;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.NORMAL;
    }
}