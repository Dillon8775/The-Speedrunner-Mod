package net.dillon.speedrunnermod.screen.feature.blocksanditems;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.screen.feature.doommode.BasicsScreen;
import net.dillon.speedrunnermod.screen.feature.oresandworldgen.SpeedrunnersWastelandBiomeScreen;
import net.dillon.speedrunnermod.screen.feature.toolsandarmor.SpeedrunnerArmorScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

public class SpeedrunnersWorkbenchScreen extends AbstractFeatureScreen {

    public SpeedrunnersWorkbenchScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_SPEEDRUNNERS_WORKBENCH,
                new SpeedrunnerArmorScreen(parent), ModTexts.MENU_TOOLS_AND_ARMOR,
                new SpeedrunnersWastelandBiomeScreen(parent), ModTexts.MENU_ORES_AND_WORLDGEN,
                new BasicsScreen(parent), ModTexts.MENU_FEATURE_DOOM_MODE,
                false, null, null);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "speedrunners_workbench";
    }

    @Override
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.BLOCKS_AND_ITEMS;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.LAST_PAGE;
    }
}