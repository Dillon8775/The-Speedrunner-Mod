package net.dillon.speedrunnermod.screen.option;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.option.ListOptions;
import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;
import static net.dillon.speedrunnermod.option.CommonModOptions.isBalancedMode;
import static net.dillon.speedrunnermod.option.CommonModOptions.isDoomMode;
import static net.dillon.speedrunnermod.option.ListOptions.ofRestartable;

/**
 * A screen for some of the {@code advanced speedrunner mod options.}
 */
public class AdvancedOptionsScreen extends AbstractModScreen {
    private AbstractWidget modifiedStrongholdGeneration, modifiedStrongholdYGeneration, modifiedNetherFortressGeneration, piglinAwakenerPiglinCount, icarusFireworksInventorySlot, infiniPearlInventorySlot, dragonImmunityFromGoliathAndWither, annulEyeSearchRadius, piglinAwakenerSearchRadius, blazeSpotterSearchRadius, raidEradicatorSearchRadius, dragonsPearlSearchRadius, dragonImmunityDetectionRadiusForGoliath, dragonImmunityDetectionRadiusForWither;

    public AdvancedOptionsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_ADVANCED_OPTIONS);
    }

    @Override
    protected void init() {
        this.initializeModButtonListWidget();

        this.modifiedStrongholdGeneration = createOption(ListOptions.modifiedStrongholdGeneration());
        this.modifiedStrongholdYGeneration = createOption(ListOptions.modifiedStrongholdYGeneration());
        this.modifiedNetherFortressGeneration = createOption(ListOptions.modifiedNetherFortressGeneration());
        this.piglinAwakenerPiglinCount = createOption(ListOptions.piglinAwakenerPiglinCount());
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
        this.buttonList.addSingleOptionEntry(createOption(ListOptions.enderEyeBreakingCooldown()));
        this.buttonList.addSingleOptionEntry(this.piglinAwakenerPiglinCount);
        this.buttonList.addSingleOptionEntry(createOption(ListOptions.shiftToThrowFireball()));
        this.buttonList.addSingleOptionEntry(createOption(ListOptions.longerDragonPerchStayTime()));
        this.buttonList.addSingleOptionEntry(createOption(ListOptions.decreasedZombifiedPiglinScareDistance()));
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
        this.buttonList.addSingleOptionEntry(createOption(ListOptions.goliathAndZombieEntityDetectionRadius(this.hasXDown(), this.hasYDown(), this.hasZDown())));

        super.init();
    }

    @Override
    protected void lockOptionsAndRenderTooltips(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        boolean balancedMode = !isBalancedMode();

        this.lockOptionWithTooltip(this.modifiedStrongholdGeneration, balancedMode,
                ofRestartable(Component.translatable("speedrunnermod.options.modified_stronghold_generation.tooltip")),
                Component.translatable("speedrunnermod.options.balanced_mode_conflicting.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.modifiedStrongholdYGeneration, balancedMode,
                ofRestartable(Component.translatable("speedrunnermod.options.modified_stronghold_y_generation.tooltip")),
                Component.translatable("speedrunnermod.options.balanced_mode_conflicting.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.modifiedNetherFortressGeneration, balancedMode,
                ofRestartable(Component.translatable("speedrunnermod.options.modified_nether_fortress_generation.tooltip")),
                Component.translatable("speedrunnermod.options.balanced_mode_conflicting.tooltip"),
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

        this.lockOptionWithTooltip(this.icarusFireworksInventorySlot, common().general.iCarusMode.getCurrentValue(),
                Component.translatable("speedrunnermod.options.icarus_fireworks_inventory_slot.tooltip"),
                Component.translatable("speedrunnermod.options.icarus_mode_must_be_enabled.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.infiniPearlInventorySlot, common().general.infiniPearlMode.getCurrentValue(),
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