package net.dillon.speedrunnermod.screen.feature.blocksanditems;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

public class DragonsAuraScreen extends AbstractFeatureScreen {

    public DragonsAuraScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_DRAGONS_AURA);
    }

    @Override
    public @NotNull String linesKey() {
        return "dragons_aura";
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