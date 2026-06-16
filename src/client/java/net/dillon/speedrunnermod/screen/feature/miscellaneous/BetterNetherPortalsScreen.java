package net.dillon.speedrunnermod.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

public class BetterNetherPortalsScreen extends AbstractFeatureScreen {

    public BetterNetherPortalsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_BETTER_NETHER_PORTALS);
    }

    @Override
    public @NotNull String linesKey() {
        return "better_nether_portals";
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