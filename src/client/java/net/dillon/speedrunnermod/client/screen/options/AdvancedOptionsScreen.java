package net.dillon.speedrunnermod.client.screen.options;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.client.util.ButtonSide;
import net.dillon.speedrunnermod.option.ModListOptions;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.SimpleOption;

import java.io.File;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * A screen for some of the {@code advanced speedrunner mod options.}
 */
@Environment(EnvType.CLIENT)
public class AdvancedOptionsScreen extends AbstractModScreen {

    public AdvancedOptionsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_ADVANCED_OPTIONS);
    }

    /**
     * Some of the {@code advanced options.} The rest are called in the init method.
     */
    private SimpleOption<?>[] advancedOptions() {
        return new SimpleOption[]{
                ModListOptions.SHOW_RESET_BUTTON,
                ModListOptions.HIGHER_BREATH_TIME,
                options().main.throwableFireballs ? ModListOptions.FIREBALL_EXPLOSION_POWER : ModListOptions.Inactiveable.IAO_FIREBALL_EXPLOSION_POWER,
                ModListOptions.SHIFT_TO_THROW_FIREBALL
        };
    }

    @Override
    protected void init() {
        this.initializeOptionListWidget();

        this.optionList.addSingleOptionEntry(ModListOptions.MODIFIED_STRONGHOLD_GENERATION);
        this.optionList.addSingleOptionEntry(ModListOptions.MODIFIED_STRONGHOLD_Y_GENERATION);
        this.optionList.addSingleOptionEntry(ModListOptions.MODIFIED_NETHER_FORTRESS_GENERATION);
        this.optionList.addSingleOptionEntry(ModListOptions.GENERATE_SPEEDRUNNER_WOOD);
        this.optionList.addSingleOptionEntry(options().main.customBiomesAndCustomBiomeFeatures ? ModListOptions.SPEEDRUNNERS_WASTELAND_BIOME_WEIGHT : ModListOptions.Inactiveable.IAO_SPEEDRUNNERS_WASTELAND_BIOME_WEIGHT);
        this.optionList.addSingleOptionEntry(ModListOptions.ENDER_EYE_BREAKING_COOLDOWN);
        this.optionList.addSingleOptionEntry(ModListOptions.LONGER_DRAGON_PERCH_STAY_TIME);
        this.optionList.addSingleOptionEntry(ModListOptions.DECREASED_ZOMBIFIED_PIGLIN_SCARE_DISTANCE);
        this.optionList.addSingleOptionEntry(options().main.playingMode.easy() ? ModListOptions.PIGLIN_AWAKENER_PIGLIN_COUNT : ModListOptions.Inactiveable.IAO_PIGLIN_AWAKENER_PIGLIN_COUNT);
        this.optionList.addSingleOptionEntry(options().main.iCarusMode ? ModListOptions.ICARUS_FIREWORKS_INVENTORY_SLOT : ModListOptions.Inactiveable.IAO_ICARUS_FIREWORKS_INVENTORY_SLOT);
        this.optionList.addSingleOptionEntry(options().main.infiniPearlMode ? ModListOptions.INFINI_PEARL_INVENTORY_SLOT : ModListOptions.Inactiveable.IAO_INFINI_PEARL_INVENTORY_SLOT);
        this.optionList.addSingleOptionEntry(ModListOptions.DRAGON_KILLS_NEARBY_HOSTILE_ENTITIES);
        this.optionList.addSingleOptionEntry(options().main.playingMode.doom() ? ModListOptions.DRAGON_IMMUNITY_FROM_GOLIATH_AND_WITHER : ModListOptions.Inactiveable.IAO_DRAGON_IMMUNITY_FROM_GOLIATH_AND_WITHER);
        this.optionList.addAll(advancedOptions());

        this.deactivateButtonIf(4, ButtonSide.LARGE, options().main.customBiomesAndCustomBiomeFeatures);
        this.deactivateButtonIf(8, ButtonSide.LARGE, options().main.playingMode.easy());
        this.deactivateButtonIf(9, ButtonSide.LARGE, options().main.iCarusMode);
        this.deactivateButtonIf(10, ButtonSide.LARGE, options().main.infiniPearlMode);
        this.deactivateButtonIf(12, ButtonSide.LARGE, options().main.playingMode.doom());
        this.deactivateButtonIf(14, ButtonSide.LEFT, options().main.throwableFireballs);

        this.addSelectableChild(this.optionList);
        this.configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), ModOptions.CONFIG);

        super.init();
    }

    @Override
    protected String pageId() {
        return "gfipdfsip";
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
    protected boolean isOptionsScreen() {
        return true;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }
}