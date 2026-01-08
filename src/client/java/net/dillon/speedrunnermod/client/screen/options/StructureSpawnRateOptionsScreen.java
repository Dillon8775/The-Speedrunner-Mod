package net.dillon.speedrunnermod.client.screen.options;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.option.ModListOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.dillon.speedrunnermod.util.TranslationStringKeys;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ClickableWidget;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.configHandler;

/**
 * Configure structure spawn rates individually by each structure.
 */
@Environment(EnvType.CLIENT)
public class StructureSpawnRateOptionsScreen extends AbstractModScreen {

    public StructureSpawnRateOptionsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_STRUCTURE_SPAWN_RATE_OPTIONS);
    }

    /**
     * Returns a list of all of the {@code structure spawn rate structure settings.}
     */
    private List<ClickableWidget> structures() {
        return List.of(
                ModListOptions.createStructureSpawnRateOption(TranslationStringKeys.VILLAGE).createWidget(this.gameOptions),
                ModListOptions.createStructureSpawnRateOption(TranslationStringKeys.ANCIENT_CITY).createWidget(this.gameOptions),
                ModListOptions.createStructureSpawnRateOption(TranslationStringKeys.DESERT_PYRAMID).createWidget(this.gameOptions),
                ModListOptions.createStructureSpawnRateOption(TranslationStringKeys.JUNGLE_PYRAMID).createWidget(this.gameOptions),
                ModListOptions.createStructureSpawnRateOption(TranslationStringKeys.PILLAGER_OUTPOST).createWidget(this.gameOptions),
                ModListOptions.createStructureSpawnRateOption(TranslationStringKeys.END_CITY).createWidget(this.gameOptions),
                ModListOptions.createStructureSpawnRateOption(TranslationStringKeys.WOODLAND_MANSION).createWidget(this.gameOptions),
                ModListOptions.createStructureSpawnRateOption(TranslationStringKeys.RUINED_PORTAL).createWidget(this.gameOptions),
                ModListOptions.createStructureSpawnRateOption(TranslationStringKeys.SHIPWRECK).createWidget(this.gameOptions),
                ModListOptions.createStructureSpawnRateOption(TranslationStringKeys.TRIAL_CHAMBER).createWidget(this.gameOptions)
        );
    }

    @Override
    protected void init() {
        this.initializeCustomButtonListWidget();

        this.buttonList.addAll(structures());
        this.buttonList.addSingleOptionEntry(ModListOptions.createStructureSpawnRateOption(TranslationStringKeys.NETHER_COMPLEXES));

        super.init();
        this.configFile = configHandler().getConfigFile();
    }

    @Override
    public String pageId() {
        return "gads09aw09";
    }

    @Override
    protected int columns() {
        return 3;
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return false;
    }

    @Override
    public boolean isOptionsScreen() {
        return true;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }
}