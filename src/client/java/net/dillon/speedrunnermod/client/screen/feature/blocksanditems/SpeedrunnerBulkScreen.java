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
public class SpeedrunnerBulkScreen extends AbstractFeatureScreen {

    public SpeedrunnerBulkScreen(Screen parent) {
        super(parent, Text.translatable("speedrunnermod.title.features.blocks_and_items.speedrunner_bulk"));
    }

    @Override
    @NotNull
    public String linesKey() {
        return "speedrunner_bulk";
    }

    @Override
    public int getPageNumber() {
        return 19;
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