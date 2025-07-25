package net.dillon.speedrunnermod.client.screen.options;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.option.ModListOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.*;

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
                ModListOptions.showResetButton(),
                ModListOptions.higherBreathTime(),
                ModListOptions.fireballExplosionPower(),
                ModListOptions.shiftToThrowFireball()
        };
    }

    @Override
    protected void init() {
        super.init();
        this.optionList.addSingleOptionEntry(ModListOptions.modifiedStrongholdGeneration());
        this.optionList.addSingleOptionEntry(ModListOptions.modifiedStrongholdYGeneration());
        this.optionList.addSingleOptionEntry(ModListOptions.modifiedNetherFortressGeneration());
        this.optionList.addSingleOptionEntry(ModListOptions.generateSpeedrunnerWood());
        this.optionList.addSingleOptionEntry(ModListOptions.enderEyeBreakingCooldown());
        this.optionList.addSingleOptionEntry(ModListOptions.longerDragonPerchStayTime());
        this.optionList.addSingleOptionEntry(ModListOptions.decreasedZombifiedPiglinScareDistance());
        this.optionList.addSingleOptionEntry(ModListOptions.piglinAwakenerPiglinCount());
        this.optionList.addSingleOptionEntry(ModListOptions.icarusFireworksInventorySlot());
        this.optionList.addSingleOptionEntry(ModListOptions.infiniPearlInventorySlot());
        this.optionList.addSingleOptionEntry(ModListOptions.dragonKillsNearbyHostileEntities());
        this.optionList.addSingleOptionEntry(ModListOptions.dragonImmunityFromGoliathAndWither());
        this.optionList.addAll(advancedOptions());
        this.optionList.addSingleOptionEntry(ModListOptions.annulEyeSearchRadius(this.hasXDown(), this.hasYDown(), this.hasZDown()));
        this.optionList.addSingleOptionEntry(ModListOptions.piglinAwakenerSearchRadius(this.hasXDown(), this.hasYDown(), this.hasZDown()));
        this.optionList.addSingleOptionEntry(ModListOptions.blazeSpotterSearchRadius(this.hasXDown(), this.hasYDown(), this.hasZDown()));
        this.optionList.addSingleOptionEntry(ModListOptions.raidEradicatorSearchRadius(this.hasXDown(), this.hasYDown(), this.hasZDown()));
        this.optionList.addSingleOptionEntry(ModListOptions.dragonsPearlSearchRadius(this.hasXDown(), this.hasYDown(), this.hasZDown()));
        this.optionList.addSingleOptionEntry(ModListOptions.dragonMassKillRadius(this.hasXDown(), this.hasYDown(), this.hasZDown()));
        this.optionList.addSingleOptionEntry(ModListOptions.dragonImmunityDetectionRadiusForGoliath(this.hasXDown(), this.hasYDown(), this.hasZDown()));
        this.optionList.addSingleOptionEntry(ModListOptions.dragonImmunityDetectionRadiusForWither(this.hasXDown(), this.hasYDown(), this.hasZDown()));

        this.addSelectableChild(this.optionList);
    }

    @Override
    protected void lockOptionsAndRenderTooltips(DrawContext context, int mouseX, int mouseY) {
        boolean customDataGen = options().main.customDataGeneration.getCurrentValue();
        boolean customDataGenAndBalancedMode = customDataGen && !isBalancedMode();
        boolean customDataGenAndCustomBiomesAndCustomBiomeFeatures = customDataGen && options().main.customBiomesAndCustomBiomeFeatures.getCurrentValue();

        this.lockOptionWithTooltip(ModListOptions.modifiedStrongholdGeneration(), customDataGenAndBalancedMode,
                Text.translatable("speedrunnermod.options.modified_stronghold_generation.tooltip"),
                !options().main.customDataGeneration.getCurrentValue() ?
                Text.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip") :
                        Text.translatable("speedrunnermod.options.balanced_mode_conflicting.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.modifiedStrongholdYGeneration(), customDataGenAndBalancedMode,
                Text.translatable("speedrunnermod.options.modified_stronghold_y_generation.tooltip"),
                !options().main.customDataGeneration.getCurrentValue() ?
                Text.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip") :
                        Text.translatable("speedrunnermod.options.balanced_mode_conflicting.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.modifiedNetherFortressGeneration(), customDataGenAndBalancedMode,
                Text.translatable("speedrunnermod.options.modified_nether_fortress_generation.tooltip"),
                !options().main.customDataGeneration.getCurrentValue() ?
                Text.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip") :
                        Text.translatable("speedrunnermod.options.balanced_mode_conflicting.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.generateSpeedrunnerWood(), customDataGenAndCustomBiomesAndCustomBiomeFeatures,
                Text.translatable("speedrunnermod.options.generate_speedrunner_wood.tooltip"),
                !options().main.customDataGeneration.getCurrentValue() ?
                        Text.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip") :
                        Text.translatable("speedrunnermod.options.custom_biomes_and_custom_biome_features_must_be_enabled.tooltip")
                );

        this.lockOptionWithTooltip(ModListOptions.piglinAwakenerPiglinCount(), isEasyMode(),
                Text.translatable("speedrunnermod.options.piglin_awakener_piglin_count.tooltip"),
                Text.translatable("speedrunnermod.options.mode_easy_required.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.icarusFireworksInventorySlot(), options().main.iCarusMode.getCurrentValue(),
                Text.translatable("speedrunnermod.options.icarus_fireworks_inventory_slot.tooltip"),
                Text.translatable("speedrunnermod.options.icarus_mode_must_be_enabled.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.infiniPearlInventorySlot(), options().main.infiniPearlMode.getCurrentValue(),
                Text.translatable("speedrunnermod.options.infini_pearl_inventory_slot.tooltip"),
                Text.translatable("speedrunnermod.options.infini_pearl_mode_must_be_enabled.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.dragonImmunityFromGoliathAndWither(), isDoomMode(),
                Text.translatable("speedrunnermod.options.dragon_immunity_from_goliath_and_wither.tooltip"),
                Text.translatable("speedrunnermod.options.mode_doom_required.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.fireballExplosionPower(), options().main.throwableFireballs.getCurrentValue(),
                Text.translatable("speedrunnermod.options.fireball_explosion_power.tooltip"),
                Text.translatable("speedrunnermod.options.throwable_fireballs_must_be_enabled.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.annulEyeSearchRadius(this.hasXDown(), this.hasYDown(), this.hasZDown()), !isBalancedMode(),
                ModListOptions.listIntegerTooltip(Text.translatable("speedrunnermod.options.annul_eye_search_radius.tooltip")),
                Text.translatable("speedrunnermod.options.mode_easy_or_doom_required.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.piglinAwakenerSearchRadius(this.hasXDown(), this.hasYDown(), this.hasZDown()), isEasyMode(),
                ModListOptions.listIntegerTooltip(Text.translatable("speedrunnermod.options.piglin_awakener_search_radius.tooltip")),
                Text.translatable("speedrunnermod.options.mode_easy_required.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.blazeSpotterSearchRadius(this.hasXDown(), this.hasYDown(), this.hasZDown()), isEasyMode(),
                ModListOptions.listIntegerTooltip(Text.translatable("speedrunnermod.options.blaze_spotter_search_radius.tooltip")),
                Text.translatable("speedrunnermod.options.mode_easy_required.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.raidEradicatorSearchRadius(this.hasXDown(), this.hasYDown(), this.hasZDown()), !isBalancedMode(),
                ModListOptions.listIntegerTooltip(Text.translatable("speedrunnermod.options.raid_eradicator_search_radius.tooltip")),
                Text.translatable("speedrunnermod.options.mode_easy_or_doom_required.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.dragonsPearlSearchRadius(this.hasXDown(), this.hasYDown(), this.hasZDown()), !isBalancedMode(),
                ModListOptions.listIntegerTooltip(Text.translatable("speedrunnermod.options.dragons_pearl_search_radius.tooltip")),
                Text.translatable("speedrunnermod.options.mode_easy_or_doom_required.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.dragonImmunityDetectionRadiusForGoliath(this.hasXDown(), this.hasYDown(), this.hasZDown()), isDoomMode(),
                ModListOptions.listIntegerTooltip(Text.translatable("speedrunnermod.options.dragon_immunity_detection_radius_for_goliath.tooltip")),
                Text.translatable("speedrunnermod.options.mode_doom_required.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.dragonImmunityDetectionRadiusForWither(this.hasXDown(), this.hasYDown(), this.hasZDown()), isDoomMode(),
                ModListOptions.listIntegerTooltip(Text.translatable("speedrunnermod.options.dragon_immunity_detection_radius_for_wither.tooltip")),
                Text.translatable("speedrunnermod.options.mode_doom_required.tooltip")
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