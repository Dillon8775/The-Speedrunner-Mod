package net.dillon.speedrunnermod.client.screen.feature.blocksanditems;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.client.screen.feature.doommode.BasicsScreen;
import net.dillon.speedrunnermod.client.screen.feature.oresandworldgen.SpeedrunnersWastelandBiomeScreen;
import net.dillon.speedrunnermod.client.screen.feature.toolsandarmor.SpeedrunnerArmorScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
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
    public int getPageNumber() {
        return this.getMaxPages();
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