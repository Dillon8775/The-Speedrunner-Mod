package net.dillon.speedrunnermod.client.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.NotNull;

public class CraftableGodApplesScreen extends AbstractFeatureScreen {

    public CraftableGodApplesScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_CRAFTABLE_GOD_APPLES);
    }

    @Override
    public @NotNull String linesKey() {
        return "craftable_god_apples";
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