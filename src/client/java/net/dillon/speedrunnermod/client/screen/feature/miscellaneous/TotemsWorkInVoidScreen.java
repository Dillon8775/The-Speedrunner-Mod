package net.dillon.speedrunnermod.client.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class TotemsWorkInVoidScreen extends AbstractFeatureScreen {

    public TotemsWorkInVoidScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_TOTEMS_WORK_IN_VOID);
    }

    @Override
    public @NotNull String linesKey() {
        return "totems_work_in_void";
    }

    @Override
    public int getPageNumber() {
        return 29;
    }

    @Override
    public @NotNull ScreenCategory getScreenCategory() {
        return ScreenCategory.MISCELLANEOUS;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}