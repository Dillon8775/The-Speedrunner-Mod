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
    public int getPageNumber() {
        return 5;
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