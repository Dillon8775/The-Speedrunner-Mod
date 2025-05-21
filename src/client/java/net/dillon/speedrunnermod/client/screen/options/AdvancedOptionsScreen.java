package net.dillon.speedrunnermod.client.screen.options;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.client.util.ButtonSide;
import net.dillon.speedrunnermod.option.ModListOptions;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;

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
                ModListOptions.FIREBALL_EXPLOSION_POWER,
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
        this.optionList.addSingleOptionEntry(ModListOptions.SPEEDRUNNERS_WASTELAND_BIOME_WEIGHT);
        this.optionList.addSingleOptionEntry(ModListOptions.ENDER_EYE_BREAKING_COOLDOWN);
        this.optionList.addSingleOptionEntry(ModListOptions.LONGER_DRAGON_PERCH_STAY_TIME);
        this.optionList.addSingleOptionEntry(ModListOptions.DECREASED_ZOMBIFIED_PIGLIN_SCARE_DISTANCE);
        this.optionList.addSingleOptionEntry(ModListOptions.PIGLIN_AWAKENER_PIGLIN_COUNT);
        this.optionList.addSingleOptionEntry(ModListOptions.ICARUS_FIREWORKS_INVENTORY_SLOT);
        this.optionList.addSingleOptionEntry(ModListOptions.INFINI_PEARL_INVENTORY_SLOT);
        this.optionList.addSingleOptionEntry(ModListOptions.DRAGON_KILLS_NEARBY_HOSTILE_ENTITIES);
        this.optionList.addSingleOptionEntry(ModListOptions.DRAGON_IMMUNITY_FROM_GOLIATH_AND_WITHER);
        this.optionList.addAll(advancedOptions());

        for (int i = 0; i < 3; i++) {
            this.deactivateOptionIf(i, ButtonSide.LARGE, options().main.customDataGeneration);
        }
        this.deactivateOptionIf(4, ButtonSide.LARGE, options().main.customBiomesAndCustomBiomeFeatures);
        this.deactivateOptionIf(8, ButtonSide.LARGE, options().main.playingMode.easy());
        this.deactivateOptionIf(9, ButtonSide.LARGE, options().main.iCarusMode);
        this.deactivateOptionIf(10, ButtonSide.LARGE, options().main.infiniPearlMode);
        this.deactivateOptionIf(12, ButtonSide.LARGE, options().main.playingMode.doom());
        this.deactivateOptionIf(14, ButtonSide.LEFT, options().main.throwableFireballs);

        this.addSelectableChild(this.optionList);
        this.configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), ModOptions.CONFIG);

        super.init();
    }

    @Override
    protected void renderOptionTooltips(DrawContext context, int mouseX, int mouseY) {
        this.renderOptionTooltip(
                0,
                ButtonSide.LARGE,
                options().main.customDataGeneration,
                Text.translatable("speedrunnermod.options.modified_stronghold_generation.tooltip"),
                Text.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip"),
                context,
                mouseX,
                mouseY
        );
        this.renderOptionTooltip(
                1,
                ButtonSide.LARGE,
                options().main.customDataGeneration,
                Text.translatable("speedrunnermod.options.modified_stronghold_y_generation.tooltip"),
                Text.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip"),
                context,
                mouseX,
                mouseY
        );
        this.renderOptionTooltip(
                2,
                ButtonSide.LARGE,
                options().main.customDataGeneration,
                Text.translatable("speedrunnermod.options.modified_nether_fortress_generation.tooltip"),
                Text.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip"),
                context,
                mouseX,
                mouseY
        );
        this.renderOptionTooltip(
                4,
                ButtonSide.LARGE,
                options().main.customBiomesAndCustomBiomeFeatures,
                Text.translatable("speedrunnermod.options.speedrunners_wasteland_biome_weight.tooltip"),
                Text.translatable("speedrunnermod.options.custom_biomes_and_custom_biome_features_must_be_enabled.tooltip"),
                context,
                mouseX,
                mouseY
        );
        this.renderOptionTooltip(
                8,
                ButtonSide.LARGE,
                options().main.playingMode.easy(),
                Text.translatable("speedrunnermod.options.piglin_awakener_piglin_count.tooltip"),
                Text.translatable("speedrunnermod.options.playing_mode_easy_required.tooltip"),
                context,
                mouseX,
                mouseY
        );
        this.renderOptionTooltip(
                9,
                ButtonSide.LARGE,
                options().main.iCarusMode,
                Text.translatable("speedrunnermod.options.icarus_fireworks_inventory_slot.tooltip"),
                Text.translatable("speedrunnermod.options.icarus_mode_must_be_enabled.tooltip"),
                context,
                mouseX,
                mouseY
        );
        this.renderOptionTooltip(
                10,
                ButtonSide.LARGE,
                options().main.infiniPearlMode,
                Text.translatable("speedrunnermod.options.infini_pearl_inventory_slot.tooltip"),
                Text.translatable("speedrunnermod.options.infini_pearl_mode_must_be_enabled.tooltip"),
                context,
                mouseX,
                mouseY
        );
        this.renderOptionTooltip(
                12,
                ButtonSide.LARGE,
                options().main.playingMode.doom(),
                Text.translatable("speedrunnermod.options.dragon_immunity_from_goliath_and_wither.tooltip"),
                Text.translatable("speedrunnermod.options.playing_mode_doom_required.tooltip"),
                context,
                mouseX,
                mouseY
        );
        this.renderOptionTooltip(
                14,
                ButtonSide.LEFT,
                options().main.throwableFireballs,
                Text.translatable("speedrunnermod.options.fireball_explosion_power.tooltip"),
                Text.translatable("speedrunnermod.options.throwable_fireballs_must_be_enabled.tooltip"),
                context,
                mouseX,
                mouseY
        );
    }

    @Override
    public String pageId() {
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
    public boolean isOptionsScreen() {
        return true;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }
}