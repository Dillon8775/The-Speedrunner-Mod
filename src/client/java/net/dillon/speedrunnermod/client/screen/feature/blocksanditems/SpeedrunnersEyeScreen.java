package net.dillon.speedrunnermod.client.screen.feature.blocksanditems;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class SpeedrunnersEyeScreen extends AbstractFeatureScreen {

    public SpeedrunnersEyeScreen(Screen parent) {
        super(parent, Text.translatable("speedrunnermod.title.features.blocks_and_items.speedrunners_eye"));
    }

    @Override
    @NotNull
    public String linesKey() {
        return "speedrunners_eye";
    }

    @Override
    public int getPageNumber() {
        return 11;
    }

    @Override
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.BLOCKS_AND_ITEMS;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.NORMAL;
    }
}