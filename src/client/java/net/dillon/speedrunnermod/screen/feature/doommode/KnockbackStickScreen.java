package net.dillon.speedrunnermod.screen.feature.doommode;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

public class KnockbackStickScreen extends AbstractFeatureScreen {

    public KnockbackStickScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_DOOM_MODE_KNOCKBACK_STICK);
    }

    @Override
    public @NotNull String linesKey() {
        return "knockback_stick";
    }

    @Override
    public @NotNull ScreenCategory getScreenCategory() {
        return ScreenCategory.DOOM_MODE;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}