package net.dillon.speedrunnermod.screen.option;

import net.dillon.speedrunnermod.option.ListOptions;
import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isBalancedMode;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

/**
 * A screen for some of the {@code advanced speedrunner mod options.}
 */
public class AdvancedOptionsScreen extends AbstractModScreen {
    private AbstractWidget modifiedStrongholdGeneration, modifiedStrongholdYGeneration, modifiedNetherFortressGeneration, generateSpeedrunnerWood, piglinAwakenerPiglinCount, icarusFireworksInventorySlot, infiniPearlInventorySlot, dragonImmunityFromGoliathAndWither, annulEyeSearchRadius, piglinAwakenerSearchRadius, blazeSpotterSearchRadius, raidEradicatorSearchRadius, dragonsPearlSearchRadius, dragonImmunityDetectionRadiusForGoliath, dragonImmunityDetectionRadiusForWither;

    public AdvancedOptionsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_ADVANCED_OPTIONS);
    }

    @Override
    protected void init() {
        this.initializeCustomButtonListWidget();

        this.modifiedStrongholdGeneration = createOption(ListOptions.modifiedStrongholdGeneration());
        this.modifiedStrongholdYGeneration = createOption(ListOptions.modifiedStrongholdYGeneration());
        this.modifiedNetherFortressGeneration = createOption(ListOptions.modifiedNetherFortressGeneration());
        this.generateSpeedrunnerWood = createOption(ListOptions.generateSpeedrunnerWood());
        this.piglinAwakenerPiglinCount = createOption(ListOptions.generateSpeedrunnerWood());
        this.icarusFireworksInventorySlot = createOption(ListOptions.icarusFireworksInventorySlot());
        this.infiniPearlInventorySlot = createOption(ListOptions.infiniPearlInventorySlot());
        this.dragonImmunityFromGoliathAndWither = createOption(ListOptions.dragonImmunityFromGoliathAndWither());
        this.annulEyeSearchRadius = createOption(ListOptions.annulEyeSearchRadius(this.hasXDown(), this.hasYDown(), this.hasZDown()));
        this.piglinAwakenerSearchRadius = createOption(ListOptions.piglinAwakenerSearchRadius(this.hasXDown(), this.hasYDown(), this.hasZDown()));
        this.blazeSpotterSearchRadius = createOption(ListOptions.blazeSpotterSearchRadius(this.hasXDown(), this.hasYDown(), this.hasZDown()));
        this.raidEradicatorSearchRadius = createOption(ListOptions.raidEradicatorSearchRadius(this.hasXDown(), this.hasYDown(), this.hasZDown()));
        this.dragonsPearlSearchRadius = createOption(ListOptions.dragonsPearlSearchRadius(this.hasXDown(), this.hasYDown(), this.hasZDown()));
        this.dragonImmunityDetectionRadiusForGoliath = createOption(ListOptions.dragonImmunityDetectionRadiusForGoliath(this.hasXDown(), this.hasYDown(), this.hasZDown()));
        this.dragonImmunityDetectionRadiusForWither = createOption(ListOptions.dragonImmunityDetectionRadiusForWither(this.hasXDown(), this.hasYDown(), this.hasZDown()));

        this.buttonList.addSingleOptionEntry(this.modifiedStrongholdGeneration);
        this.buttonList.addSingleOptionEntry(this.modifiedStrongholdYGeneration);
        this.buttonList.addSingleOptionEntry(this.modifiedNetherFortressGeneration);
        this.buttonList.addSingleOptionEntry(this.generateSpeedrunnerWood);
        this.buttonList.addSingleOptionEntry(createOption(ListOptions.enderEyeBreakingCooldown()));
        this.buttonList.addSingleOptionEntry(createOption(ListOptions.longerDragonPerchStayTime()));
        this.buttonList.addSingleOptionEntry(createOption(ListOptions.decreasedZombifiedPiglinScareDistance()));
        this.buttonList.addSingleOptionEntry(this.piglinAwakenerPiglinCount);
        this.buttonList.addSingleOptionEntry(this.icarusFireworksInventorySlot);
        this.buttonList.addSingleOptionEntry(this.infiniPearlInventorySlot);
        this.buttonList.addSingleOptionEntry(createOption(ListOptions.dragonKillsNearbyHostileEntities()));
        this.buttonList.addSingleOptionEntry(this.dragonImmunityFromGoliathAndWither);
        this.buttonList.addSingleOptionEntry(this.annulEyeSearchRadius);
        this.buttonList.addSingleOptionEntry(this.piglinAwakenerSearchRadius);
        this.buttonList.addSingleOptionEntry(this.blazeSpotterSearchRadius);
        this.buttonList.addSingleOptionEntry(this.raidEradicatorSearchRadius);
        this.buttonList.addSingleOptionEntry(this.dragonsPearlSearchRadius);
        this.buttonList.addSingleOptionEntry(createOption(ListOptions.dragonMassKillRadius(this.hasXDown(), this.hasYDown(), this.hasZDown())));
        this.buttonList.addSingleOptionEntry(this.dragonImmunityDetectionRadiusForGoliath);
        this.buttonList.addSingleOptionEntry(this.dragonImmunityDetectionRadiusForWither);

        super.init();
    }

    @Override
    protected void lockOptionsAndRenderTooltips(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        boolean customDataGen = options().worldGen.customDataGeneration.getCurrentValue();
        boolean customDataGenAndBalancedMode = customDataGen && !isBalancedMode();
        boolean customDataGenAndCustomBiomesAndCustomBiomeFeatures = customDataGen && options().general.customBiomesAndCustomBiomeFeatures.getCurrentValue();

        this.lockOptionWithTooltip(this.modifiedStrongholdGeneration, customDataGenAndBalancedMode,
                Component.translatable("speedrunnermod.options.modified_stronghold_generation.tooltip"),
                !options().worldGen.customDataGeneration.getCurrentValue() ?
                Component.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip") :
                        Component.translatable("speedrunnermod.options.balanced_mode_conflicting.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.modifiedStrongholdYGeneration, customDataGenAndBalancedMode,
                Component.translatable("speedrunnermod.options.modified_stronghold_y_generation.tooltip"),
                !options().worldGen.customDataGeneration.getCurrentValue() ?
                Component.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip") :
                        Component.translatable("speedrunnermod.options.balanced_mode_conflicting.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.modifiedNetherFortressGeneration, customDataGenAndBalancedMode,
                Component.translatable("speedrunnermod.options.modified_nether_fortress_generation.tooltip"),
                !options().worldGen.customDataGeneration.getCurrentValue() ?
                Component.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip") :
                        Component.translatable("speedrunnermod.options.balanced_mode_conflicting.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.generateSpeedrunnerWood, customDataGenAndCustomBiomesAndCustomBiomeFeatures,
                Component.translatable("speedrunnermod.options.generate_speedrunner_wood.tooltip"),
                !options().worldGen.customDataGeneration.getCurrentValue() ?
                        Component.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip") :
                        Component.translatable("speedrunnermod.options.custom_biomes_and_custom_biome_features_must_be_enabled.tooltip"),
                graphics,
                mouseX,
                mouseY
                );

        this.lockOptionWithTooltip(this.piglinAwakenerPiglinCount, !isBalancedMode(),
                Component.translatable("speedrunnermod.options.piglin_awakener_piglin_count.tooltip"),
                Component.translatable("speedrunnermod.options.mode_easy_or_doom_required.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.icarusFireworksInventorySlot, options().general.iCarusMode.getCurrentValue(),
                Component.translatable("speedrunnermod.options.icarus_fireworks_inventory_slot.tooltip"),
                Component.translatable("speedrunnermod.options.icarus_mode_must_be_enabled.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.infiniPearlInventorySlot, options().general.infiniPearlMode.getCurrentValue(),
                Component.translatable("speedrunnermod.options.infini_pearl_inventory_slot.tooltip"),
                Component.translatable("speedrunnermod.options.infini_pearl_mode_must_be_enabled.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.dragonImmunityFromGoliathAndWither, isDoomMode(),
                Component.translatable("speedrunnermod.options.dragon_immunity_from_goliath_and_wither.tooltip"),
                Component.translatable("speedrunnermod.options.mode_doom_required.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.annulEyeSearchRadius, !isBalancedMode(),
                ListOptions.listIntegerTooltip(Component.translatable("speedrunnermod.options.annul_eye_search_radius.tooltip")),
                Component.translatable("speedrunnermod.options.mode_easy_or_doom_required.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.piglinAwakenerSearchRadius, !isBalancedMode(),
                ListOptions.listIntegerTooltip(Component.translatable("speedrunnermod.options.piglin_awakener_search_radius.tooltip")),
                Component.translatable("speedrunnermod.options.mode_easy_required.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.blazeSpotterSearchRadius, !isBalancedMode(),
                ListOptions.listIntegerTooltip(Component.translatable("speedrunnermod.options.blaze_spotter_search_radius.tooltip")),
                Component.translatable("speedrunnermod.options.mode_easy_required.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.raidEradicatorSearchRadius, !isBalancedMode(),
                ListOptions.listIntegerTooltip(Component.translatable("speedrunnermod.options.raid_eradicator_search_radius.tooltip")),
                Component.translatable("speedrunnermod.options.mode_easy_or_doom_required.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.dragonsPearlSearchRadius, !isBalancedMode(),
                ListOptions.listIntegerTooltip(Component.translatable("speedrunnermod.options.dragons_pearl_search_radius.tooltip")),
                Component.translatable("speedrunnermod.options.mode_easy_or_doom_required.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.dragonImmunityDetectionRadiusForGoliath, isDoomMode(),
                ListOptions.listIntegerTooltip(Component.translatable("speedrunnermod.options.dragon_immunity_detection_radius_for_goliath.tooltip")),
                Component.translatable("speedrunnermod.options.mode_doom_required.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.dragonImmunityDetectionRadiusForWither, isDoomMode(),
                ListOptions.listIntegerTooltip(Component.translatable("speedrunnermod.options.dragon_immunity_detection_radius_for_wither.tooltip")),
                Component.translatable("speedrunnermod.options.mode_doom_required.tooltip"),
                graphics,
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