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
public class BlazeSpawnersInBastionsScreen extends AbstractFeatureScreen {

    public BlazeSpawnersInBastionsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_BLAZE_SPAWNERS_IN_BASTIONS);
    }

    @Override
    public @NotNull String linesKey() {
        return "blaze_spawners_in_bastions";
    }

    @Override
    public int getPageNumber() {
        return 14;
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