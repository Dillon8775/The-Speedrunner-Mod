package net.dillon.speedrunnermod.screen.option;

import net.dillon.speedrunnermod.option.ListOptions;
import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.dillon.speedrunnermod.util.TranslationStringKeys;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.configHandler;

/**
 * Configure structure spawn rates individually by each structure.
 */
public class StructureSpawnRateOptionsScreen extends AbstractModScreen {

    public StructureSpawnRateOptionsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_STRUCTURE_SPAWN_RATE_OPTIONS);
    }

    /**
     * Returns a list of all of the {@code structure spawn rate structure settings.}
     */
    private List<AbstractWidget> structures() {
        return List.of(
                ListOptions.createStructureSpawnRateOption(TranslationStringKeys.VILLAGE).createButton(this.options),
                ListOptions.createStructureSpawnRateOption(TranslationStringKeys.ANCIENT_CITY).createButton(this.options),
                ListOptions.createStructureSpawnRateOption(TranslationStringKeys.DESERT_PYRAMID).createButton(this.options),
                ListOptions.createStructureSpawnRateOption(TranslationStringKeys.JUNGLE_PYRAMID).createButton(this.options),
                ListOptions.createStructureSpawnRateOption(TranslationStringKeys.PILLAGER_OUTPOST).createButton(this.options),
                ListOptions.createStructureSpawnRateOption(TranslationStringKeys.END_CITY).createButton(this.options),
                ListOptions.createStructureSpawnRateOption(TranslationStringKeys.WOODLAND_MANSION).createButton(this.options),
                ListOptions.createStructureSpawnRateOption(TranslationStringKeys.RUINED_PORTAL).createButton(this.options),
                ListOptions.createStructureSpawnRateOption(TranslationStringKeys.SHIPWRECK).createButton(this.options),
                ListOptions.createStructureSpawnRateOption(TranslationStringKeys.TRIAL_CHAMBER).createButton(this.options)
        );
    }

    @Override
    protected void init() {
        this.initializeCustomButtonListWidget();

        this.buttonList.addAll(structures());
        this.buttonList.addSingleOptionEntry(createOption(ListOptions.createStructureSpawnRateOption(TranslationStringKeys.NETHER_COMPLEXES)));

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
        return true;
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