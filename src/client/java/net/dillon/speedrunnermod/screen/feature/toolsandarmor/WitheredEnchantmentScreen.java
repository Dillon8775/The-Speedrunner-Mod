package net.dillon.speedrunnermod.screen.feature.toolsandarmor;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

public class WitheredEnchantmentScreen extends AbstractFeatureScreen {

    public WitheredEnchantmentScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_WITHERED_ENCHANTMENT);
    }

    @Override
    public @NotNull String linesKey() {
        return "withered_enchantment";
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