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
public class EyeOfAnnulScreen extends AbstractFeatureScreen {

    public EyeOfAnnulScreen(Screen parent) {
        super(parent, Text.translatable("speedrunnermod.title.features.blocks_and_items.eye_of_annul"));
    }

    @Override
    @NotNull
    public String linesKey() {
        return "eye_of_annul";
    }

    @Override
    public int getPageNumber() {
        return 9;
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