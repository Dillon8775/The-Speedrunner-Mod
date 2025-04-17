package net.dillon.speedrunnermod.client.screen.feature.toolsandarmor;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class SpeedrunnerArmorScreen extends AbstractFeatureScreen {

    public SpeedrunnerArmorScreen(Screen parent) {
        super(parent, Text.translatable("speedrunnermod.title.features.tools_and_armor.speedrunner_armor"));
    }

    @Override
    @NotNull
    public String linesKey() {
        return "speedrunner_armor";
    }

    @Override
    public int getPageNumber() {
        return 1;
    }

    @Override
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.TOOLS_AND_ARMOR;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.STARTER;
    }
}