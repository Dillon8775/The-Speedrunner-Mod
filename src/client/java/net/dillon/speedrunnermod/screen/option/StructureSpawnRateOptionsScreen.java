package net.dillon.speedrunnermod.screen.option;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.option.ListOptions;
import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.dillon.speedrunnermod.util.TranslationStringKeys;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.commonConfigHandler;

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
                createOption(ListOptions.createStructureSpawnRateOption(TranslationStringKeys.VILLAGE)),
                createOption(ListOptions.createMineshaftFrequencyOption()),

                createOption(ListOptions.createStructureSpawnRateOption(TranslationStringKeys.RUINED_PORTAL)),
                createOption(ListOptions.createStructureSpawnRateOption(TranslationStringKeys.SHIPWRECK)),

                createOption(ListOptions.createStructureSpawnRateOption(TranslationStringKeys.DESERT_PYRAMID)),
                createOption(ListOptions.createStructureSpawnRateOption(TranslationStringKeys.JUNGLE_PYRAMID)),

                createOption(ListOptions.createStructureSpawnRateOption(TranslationStringKeys.PILLAGER_OUTPOST)),
                createOption(ListOptions.createStructureSpawnRateOption(TranslationStringKeys.WOODLAND_MANSION)),

                createOption(ListOptions.createStructureSpawnRateOption(TranslationStringKeys.ANCIENT_CITY)),
                createOption(ListOptions.createStructureSpawnRateOption(TranslationStringKeys.END_CITY)),

                createOption(ListOptions.createStructureSpawnRateOption(TranslationStringKeys.TRIAL_CHAMBER)),
                createOption(ListOptions.createStructureSpawnRateOption(TranslationStringKeys.TRAIL_RUIN)),

                createOption(ListOptions.createStructureSpawnRateOption(TranslationStringKeys.NETHER_COMPLEXES)),
                createOption(ListOptions.createStructureSpawnRateOption(TranslationStringKeys.IGLOO)),

                createOption(ListOptions.createStructureSpawnRateOption(TranslationStringKeys.SWAMP_HUT)),
                createOption(ListOptions.createStructureSpawnRateOption(TranslationStringKeys.OCEAN_RUIN))
        );
    }

    @Override
    protected void init() {
        this.initializeModButtonListWidget();

        this.buttonList.addAll(structures());

        super.init();
        this.configFile = commonConfigHandler().getConfigFile();
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