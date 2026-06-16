package net.dillon.speedrunnermod.screen.feature.toolsandarmor;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.screen.feature.blocksanditems.SpeedrunnerIngotsScreen;
import net.dillon.speedrunnermod.screen.feature.miscellaneous.ICarusModeScreen;
import net.dillon.speedrunnermod.screen.feature.oresandworldgen.SpeedrunnersWastelandBiomeScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

public class DragonsSwordScreen extends AbstractFeatureScreen {

    public DragonsSwordScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_DRAGONS_SWORD,
                new SpeedrunnersWastelandBiomeScreen(parent), ModTexts.MENU_ORES_AND_WORLDGEN,
                new ICarusModeScreen(parent), ModTexts.MENU_MISCELLANEOUS,
                new SpeedrunnerIngotsScreen(parent), ModTexts.MENU_BLOCKS_AND_ITEMS, false, null, null);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "dragons_sword";
    }

    @Override
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.TOOLS_AND_ARMOR;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.LAST_PAGE;
    }
}