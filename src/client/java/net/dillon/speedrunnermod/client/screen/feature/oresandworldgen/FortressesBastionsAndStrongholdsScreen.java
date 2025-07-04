package net.dillon.speedrunnermod.client.screen.feature.oresandworldgen;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.client.screen.feature.blocksanditems.SpeedrunnerIngotsScreen;
import net.dillon.speedrunnermod.client.screen.feature.miscellaneous.BetterHotkeysScreen;
import net.dillon.speedrunnermod.client.screen.feature.toolsandarmor.SpeedrunnerArmorScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class FortressesBastionsAndStrongholdsScreen extends AbstractFeatureScreen {

    public FortressesBastionsAndStrongholdsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_FORTRESSES_BASTIONS_AND_STRONGHOLDS,
                new BetterHotkeysScreen(parent), ModTexts.MENU_MISCELLANEOUS,
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
    public int getPageNumber() {
        return this.getMaxPages();
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