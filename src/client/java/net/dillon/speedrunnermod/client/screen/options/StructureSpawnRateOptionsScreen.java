package net.dillon.speedrunnermod.client.screen.options;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.client.util.TranslationStringKeys;
import net.dillon.speedrunnermod.option.ModListOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.SimpleOption;

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
    private SimpleOption<?>[] structures() {
        return new SimpleOption[]{
                ModListOptions.createStructureSpawnRateOption(TranslationStringKeys.VILLAGE),
                ModListOptions.createStructureSpawnRateOption(TranslationStringKeys.ANCIENT_CITY),
                ModListOptions.createStructureSpawnRateOption(TranslationStringKeys.DESERT_PYRAMID),
                ModListOptions.createStructureSpawnRateOption(TranslationStringKeys.JUNGLE_PYRAMID),
                ModListOptions.createStructureSpawnRateOption(TranslationStringKeys.PILLAGER_OUTPOST),
                ModListOptions.createStructureSpawnRateOption(TranslationStringKeys.END_CITY),
                ModListOptions.createStructureSpawnRateOption(TranslationStringKeys.WOODLAND_MANSION),
                ModListOptions.createStructureSpawnRateOption(TranslationStringKeys.RUINED_PORTAL),
                ModListOptions.createStructureSpawnRateOption(TranslationStringKeys.SHIPWRECK),
                ModListOptions.createStructureSpawnRateOption(TranslationStringKeys.TRIAL_CHAMBER)
        };
    }

    @Override
    protected void init() {
        super.init();
        this.optionList.addAll(structures());
        this.optionList.addSingleOptionEntry(ModListOptions.createStructureSpawnRateOption(TranslationStringKeys.NETHER_COMPLEXES));

        this.addSelectableChild(this.optionList);
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