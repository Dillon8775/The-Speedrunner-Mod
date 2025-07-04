package net.dillon.speedrunnermod.client.screen.feature.blocksanditems;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class EnderThrusterScreen extends AbstractFeatureScreen {

    public EnderThrusterScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_ENDER_THRUSTER);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "ender_thruster";
    }

    @Override
    public int getPageNumber() {
        return 12;
    }

    @Override
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.BLOCKS_AND_ITEMS;
    }


    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}