package net.dillon.speedrunnermod.screen.feature.blocksanditems;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

public class FireproofBoatsScreen extends AbstractFeatureScreen {

    public FireproofBoatsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_FIREPROOF_BOATS);
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(Button.builder(options().general.lavaBoats.getCurrentValue() ? ModTexts.DISABLE_LAVA_BOATS : ModTexts.ENABLE_LAVA_BOATS, button -> {
            options().general.lavaBoats.set(!options().general.lavaBoats.getCurrentValue());
            this.refreshNonRestartableFeature();
        }).build());
    }

    @Override
    @NotNull
    public String linesKey() {
        return "fireproof_boats";
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