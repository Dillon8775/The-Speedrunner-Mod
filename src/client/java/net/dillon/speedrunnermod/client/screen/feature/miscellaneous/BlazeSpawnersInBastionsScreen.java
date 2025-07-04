package net.dillon.speedrunnermod.client.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class BlazeSpawnersInBastionsScreen extends AbstractFeatureScreen {

    public BlazeSpawnersInBastionsScreen(Screen parent) {
        super(parent, Text.translatable("speedrunnermod.title.features.miscellaneous.blaze_spawners_in_bastions"));
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