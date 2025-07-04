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
public class EnderMatterScreen extends AbstractFeatureScreen {

    public EnderMatterScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_ENDER_MATTER);
    }

    @Override
    public @NotNull String linesKey() {
        return "ender_matter";
    }

    @Override
    public int getPageNumber() {
        return 13;
    }

    @Override
    public @NotNull ScreenCategory getScreenCategory() {
        return ScreenCategory.BLOCKS_AND_ITEMS;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}