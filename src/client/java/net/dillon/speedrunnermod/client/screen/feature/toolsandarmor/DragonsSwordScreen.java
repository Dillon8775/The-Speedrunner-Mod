package net.dillon.speedrunnermod.client.screen.feature.toolsandarmor;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.client.screen.feature.blocksanditems.SpeedrunnerIngotsScreen;
import net.dillon.speedrunnermod.client.screen.feature.miscellaneous.BetterHotkeysScreen;
import net.dillon.speedrunnermod.client.screen.feature.oresandworldgen.SpeedrunnersWastelandBiomeScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class DragonsSwordScreen extends AbstractFeatureScreen {

    public DragonsSwordScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_DRAGONS_SWORD,
                new SpeedrunnersWastelandBiomeScreen(parent), ModTexts.MENU_ORES_AND_WORLDGEN,
                new BetterHotkeysScreen(parent), ModTexts.MENU_MISCELLANEOUS,
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