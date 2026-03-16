package net.dillon.speedrunnermod.client.screen.options;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.option.ModListOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isBalancedMode;

/**
 * The Speedrunner Mod's {@code main options.}
 */
public class MainOptionsScreen extends AbstractModScreen {

    public MainOptionsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_OPTIONS_MAIN);
    }

    /**
     * All of the {@code main options.}
     * <p>These are displayed in order.</p>
     */
    private List<AbstractWidget> mainOptions() {
        return List.of(
                ModListOptions.fasterBlockBreaking().createButton(this.options),
                ModListOptions.blockBreakingMultiplier().createButton(this.options),

                ModListOptions.icarusMode().createButton(this.options),
                ModListOptions.infiniPearlMode().createButton(this.options),

                ModListOptions.betterVillagerTrades().createButton(this.options),
                ModListOptions.betterFoods().createButton(this.options),

                ModListOptions.betterBiomes().createButton(this.options),
                ModListOptions.dragonPerchTime().createButton(this.options),

                ModListOptions.fireproofItems().createButton(this.options),
                ModListOptions.throwableFireballs().createButton(this.options),

                ModListOptions.fallDamage().createButton(this.options),
                ModListOptions.kineticDamage().createButton(this.options),

                ModListOptions.strongholdCount().createButton(this.options),
                ModListOptions.strongholdDistance().createButton(this.options),

                ModListOptions.strongholdSpread().createButton(this.options),
                ModListOptions.strongholdPortalRoomCount().createButton(this.options),

                ModListOptions.strongholdLibraryCount().createButton(this.options),
                ModListOptions.globalNetherPortals().createButton(this.options),

                ModListOptions.lavaBoats().createButton(this.options),
                ModListOptions.netherWater().createButton(this.options),

                ModListOptions.commonOres().createButton(this.options),
                ModListOptions.betterAnvil().createButton(this.options),

                ModListOptions.anvilCostLimit().createButton(this.options),
                ModListOptions.higherEnchantmentLevels().createButton(this.options),

                ModListOptions.arrowsDestroyBeds().createButton(this.options),
                ModListOptions.creatureSpawningRate().createButton(this.options),

                ModListOptions.fasterSpawners().createButton(this.options),
                ModListOptions.fasterBrewing().createButton(this.options),

                ModListOptions.fasterSmelting().createButton(this.options),
                ModListOptions.netherPortalDelay().createButton(this.options),

                ModListOptions.showDeathCords().createButton(this.options),
                ModListOptions.killGhastOnFireball().createButton(this.options)
        );
    }

    @Override
    protected void init() {
        this.initializeCustomButtonListWidget();

        this.buttonList.addSingleOptionEntry(ModListOptions.mode());
        this.buttonList.addSingleOptionEntry(ModListOptions.structureSpawnRate());
        this.buttonList.addAll(mainOptions());
        this.buttonList.addSingleOptionEntry(ModListOptions.customDataGeneration());
        this.buttonList.addSingleOptionEntry(ModListOptions.rightClickToRemoveSilkTouch());
        this.buttonList.addSingleOptionEntry(ModListOptions.customBiomesAndCustomBiomeFeatures());

        super.init();
    }

    @Override
    protected void lockOptionsAndRenderTooltips(GuiGraphics context, int mouseX, int mouseY) {
        this.lockOptionWithTooltip(ModListOptions.mode(), !this.isOnServer(),
                Component.translatable("speedrunnermod.options.mode.tooltip"),
                Component.translatable("speedrunnermod.options.mode.server.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.structureSpawnRate(), options().main.customDataGeneration.getCurrentValue(),
                ModListOptions.structureSpawnRateTooltip(),
                Component.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.blockBreakingMultiplier(), options().main.fasterBlockBreaking.getCurrentValue(),
                Component.translatable("speedrunnermod.options.block_breaking_multiplier.tooltip"),
                Component.translatable("speedrunnermod.options.block_breaking_multiplier.inactive")
        );

        boolean customDataGen = options().main.customDataGeneration.getCurrentValue();
        boolean customDataGenAndBalancedMode = customDataGen && !isBalancedMode();
        boolean customDataGenAndBalancedModeAndModifiedStrongholdGeneration = customDataGenAndBalancedMode && options().advanced.modifiedStrongholdGeneration.getCurrentValue();

        this.lockOptionWithTooltip(ModListOptions.strongholdCount(), customDataGenAndBalancedModeAndModifiedStrongholdGeneration,
                Component.translatable("speedrunnermod.options.stronghold_count.tooltip"),
                !options().main.customDataGeneration.getCurrentValue() ?
                Component.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip") :
                        !isBalancedMode() ?
                                Component.translatable("speedrunnermod.options.modified_stronghold_generation_must_be_enabled.tooltip") :
                                Component.translatable("speedrunnermod.options.balanced_mode_conflicting.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.strongholdDistance(), customDataGenAndBalancedModeAndModifiedStrongholdGeneration,
                Component.translatable("speedrunnermod.options.stronghold_distance.tooltip"),
                !options().main.customDataGeneration.getCurrentValue() ?
                Component.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip") :
                        !isBalancedMode() ?
                                Component.translatable("speedrunnermod.options.modified_stronghold_generation_must_be_enabled.tooltip") :
                                Component.translatable("speedrunnermod.options.balanced_mode_conflicting.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.strongholdSpread(), customDataGenAndBalancedModeAndModifiedStrongholdGeneration,
                Component.translatable("speedrunnermod.options.stronghold_spread.tooltip"),
                !options().main.customDataGeneration.getCurrentValue() ?
                Component.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip") :
                        !isBalancedMode() ?
                                Component.translatable("speedrunnermod.options.modified_stronghold_generation_must_be_enabled.tooltip") :
                                Component.translatable("speedrunnermod.options.balanced_mode_conflicting.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.strongholdPortalRoomCount(), customDataGenAndBalancedModeAndModifiedStrongholdGeneration,
                Component.translatable("speedrunnermod.options.stronghold_portal_room_count.tooltip"),
                !options().main.customDataGeneration.getCurrentValue() ?
                Component.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip") :
                        !isBalancedMode() ?
                                Component.translatable("speedrunnermod.options.modified_stronghold_generation_must_be_enabled.tooltip") :
                                Component.translatable("speedrunnermod.options.balanced_mode_conflicting.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.strongholdLibraryCount(), customDataGenAndBalancedModeAndModifiedStrongholdGeneration,
                Component.translatable("speedrunnermod.options.stronghold_library_count.tooltip"),
                !options().main.customDataGeneration.getCurrentValue() ?
                Component.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip") :
                        !isBalancedMode() ?
                                Component.translatable("speedrunnermod.options.modified_stronghold_generation_must_be_enabled.tooltip") :
                                Component.translatable("speedrunnermod.options.balanced_mode_conflicting.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.betterBiomes(), options().main.customDataGeneration.getCurrentValue() && options().main.customBiomesAndCustomBiomeFeatures.getCurrentValue(),
                Component.translatable("speedrunnermod.options.better_biomes.tooltip"),
                !options().main.customDataGeneration.getCurrentValue() ?
                        Component.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip") :
                        Component.translatable("speedrunnermod.options.custom_biomes_and_custom_biome_features_must_be_enabled.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.customBiomesAndCustomBiomeFeatures(), options().main.customDataGeneration.getCurrentValue(),
                Component.translatable("speedrunnermod.options.custom_biomes_and_custom_biome_features.tooltip"),
                Component.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip")
        );
    }

    @Override
    public String pageId() {
        return "gf-o909aw";
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