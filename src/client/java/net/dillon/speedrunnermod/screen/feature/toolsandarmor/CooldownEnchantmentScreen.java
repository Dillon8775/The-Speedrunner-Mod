package net.dillon.speedrunnermod.screen.feature.toolsandarmor;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

public class CooldownEnchantmentScreen extends AbstractFeatureScreen {

    public CooldownEnchantmentScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_COOLDOWN_ENCHANTMENT);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "cooldown_enchantment";
    }

    @Override
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.TOOLS_AND_ARMOR;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}