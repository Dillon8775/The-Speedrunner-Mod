package net.dillon.speedrunnermod.screen.feature.oresandworldgen;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.screen.feature.blocksanditems.SpeedrunnerIngotsScreen;
import net.dillon.speedrunnermod.screen.feature.miscellaneous.ICarusModeScreen;
import net.dillon.speedrunnermod.screen.feature.toolsandarmor.SpeedrunnerArmorScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

public class FortressesBastionsAndStrongholdsScreen extends AbstractFeatureScreen {

    public FortressesBastionsAndStrongholdsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_FORTRESSES_BASTIONS_AND_STRONGHOLDS,
                new ICarusModeScreen(parent), ModTexts.MENU_MISCELLANEOUS,
                new SpeedrunnerIngotsScreen(parent), ModTexts.MENU_BLOCKS_AND_ITEMS,
                new SpeedrunnerArmorScreen(parent), ModTexts.MENU_TOOLS_AND_ARMOR,
                false, null, null);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "fortresses_bastions_and_strongholds";
    }

    @Override
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.ORES_AND_WORLDGEN;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.LAST_PAGE;
    }
}