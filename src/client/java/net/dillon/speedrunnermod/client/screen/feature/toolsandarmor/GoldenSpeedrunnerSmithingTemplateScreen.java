package net.dillon.speedrunnermod.client.screen.feature.toolsandarmor;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class GoldenSpeedrunnerSmithingTemplateScreen extends AbstractFeatureScreen {

    public GoldenSpeedrunnerSmithingTemplateScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_GOLDEN_SPEEDRUNNER_SMITHING_TEMPLATE);
    }

    @Override
    public @NotNull String linesKey() {
        return "golden_speedrunner_smithing_template";
    }

    @Override
    public @NotNull ScreenCategory getScreenCategory() {
        return ScreenCategory.TOOLS_AND_ARMOR;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}