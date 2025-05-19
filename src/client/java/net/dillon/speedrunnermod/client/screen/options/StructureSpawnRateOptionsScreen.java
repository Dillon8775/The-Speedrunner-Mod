package net.dillon.speedrunnermod.client.screen.options;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.client.util.TranslationStringKeys;
import net.dillon.speedrunnermod.option.ModListOptions;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.SimpleOption;

import java.io.File;

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
                ModListOptions.structureSpawnRate(TranslationStringKeys.VILLAGE),
                ModListOptions.structureSpawnRate(TranslationStringKeys.ANCIENT_CITY),
                ModListOptions.structureSpawnRate(TranslationStringKeys.DESERT_PYRAMID),
                ModListOptions.structureSpawnRate(TranslationStringKeys.JUNGLE_PYRAMID),
                ModListOptions.structureSpawnRate(TranslationStringKeys.PILLAGER_OUTPOST),
                ModListOptions.structureSpawnRate(TranslationStringKeys.END_CITY),
                ModListOptions.structureSpawnRate(TranslationStringKeys.WOODLAND_MANSION),
                ModListOptions.structureSpawnRate(TranslationStringKeys.RUINED_PORTAL),
                ModListOptions.structureSpawnRate(TranslationStringKeys.SHIPWRECK),
                ModListOptions.structureSpawnRate(TranslationStringKeys.TRIAL_CHAMBER)
        };
    }

    @Override
    protected void init() {
        this.initializeOptionListWidget();

        this.optionList.addAll(structures());
        this.optionList.addSingleOptionEntry(ModListOptions.structureSpawnRate(TranslationStringKeys.NETHER_COMPLEXES));

        this.addSelectableChild(this.optionList);
        this.configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), ModOptions.CONFIG);

        super.init();
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