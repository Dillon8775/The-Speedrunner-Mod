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
public class SpeedrunnerSafeBootsScreen extends AbstractFeatureScreen {

    public SpeedrunnerSafeBootsScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.tools_and_armor.speedrunner_safe_boots"));
    }

    @Override
    @NotNull
    public String linesKey() {
        return "speedrunner_safe_boots";
    }

    @Override
    public int getPageNumber() {
        return 3;
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