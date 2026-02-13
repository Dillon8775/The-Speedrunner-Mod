package net.dillon.speedrunnermod.client.screen.options;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.option.ModListOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

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
    private List<ClickableWidget> mainOptions() {
        return List.of(
                ModListOptions.fasterBlockBreaking().createWidget(this.gameOptions),
                ModListOptions.blockBreakingMultiplier().createWidget(this.gameOptions),

                ModListOptions.icarusMode().createWidget(this.gameOptions),
                ModListOptions.infiniPearlMode().createWidget(this.gameOptions),

                ModListOptions.betterVillagerTrades().createWidget(this.gameOptions),
                ModListOptions.betterFoods().createWidget(this.gameOptions),

                ModListOptions.betterBiomes().createWidget(this.gameOptions),
                ModListOptions.dragonPerchTime().createWidget(this.gameOptions),

                ModListOptions.fireproofItems().createWidget(this.gameOptions),
                ModListOptions.throwableFireballs().createWidget(this.gameOptions),

                ModListOptions.fallDamage().createWidget(this.gameOptions),
                ModListOptions.kineticDamage().createWidget(this.gameOptions),

                ModListOptions.strongholdCount().createWidget(this.gameOptions),
                ModListOptions.strongholdDistance().createWidget(this.gameOptions),

                ModListOptions.strongholdSpread().createWidget(this.gameOptions),
                ModListOptions.strongholdPortalRoomCount().createWidget(this.gameOptions),

                ModListOptions.strongholdLibraryCount().createWidget(this.gameOptions),
                ModListOptions.globalNetherPortals().createWidget(this.gameOptions),

                ModListOptions.lavaBoats().createWidget(this.gameOptions),
                ModListOptions.netherWater().createWidget(this.gameOptions),

                ModListOptions.commonOres().createWidget(this.gameOptions),
                ModListOptions.betterAnvil().createWidget(this.gameOptions),

                ModListOptions.anvilCostLimit().createWidget(this.gameOptions),
                ModListOptions.higherEnchantmentLevels().createWidget(this.gameOptions),

                ModListOptions.arrowsDestroyBeds().createWidget(this.gameOptions),
                ModListOptions.creatureSpawningRate().createWidget(this.gameOptions),

                ModListOptions.fasterSpawners().createWidget(this.gameOptions),
                ModListOptions.fasterBrewing().createWidget(this.gameOptions),

                ModListOptions.fasterSmelting().createWidget(this.gameOptions),
                ModListOptions.netherPortalDelay().createWidget(this.gameOptions),

                ModListOptions.showDeathCords().createWidget(this.gameOptions),
                ModListOptions.killGhastOnFireball().createWidget(this.gameOptions)
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
    protected void lockOptionsAndRenderTooltips(DrawContext context, int mouseX, int mouseY) {
        this.lockOptionWithTooltip(ModListOptions.mode(), !this.isOnServer(),
                Text.translatable("speedrunnermod.options.mode.tooltip"),
                Text.translatable("speedrunnermod.options.mode.server.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.structureSpawnRate(), options().main.customDataGeneration.getCurrentValue(),
                ModListOptions.structureSpawnRateTooltip(),
                Text.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.blockBreakingMultiplier(), options().main.fasterBlockBreaking.getCurrentValue(),
                Text.translatable("speedrunnermod.options.block_breaking_multiplier.tooltip"),
                Text.translatable("speedrunnermod.options.block_breaking_multiplier.inactive")
        );

        boolean customDataGen = options().main.customDataGeneration.getCurrentValue();
        boolean customDataGenAndBalancedMode = customDataGen && !isBalancedMode();
        boolean customDataGenAndBalancedModeAndModifiedStrongholdGeneration = customDataGenAndBalancedMode && options().advanced.modifiedStrongholdGeneration.getCurrentValue();

        this.lockOptionWithTooltip(ModListOptions.strongholdCount(), customDataGenAndBalancedModeAndModifiedStrongholdGeneration,
                Text.translatable("speedrunnermod.options.stronghold_count.tooltip"),
                !options().main.customDataGeneration.getCurrentValue() ?
                Text.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip") :
                        !isBalancedMode() ?
                                Text.translatable("speedrunnermod.options.modified_stronghold_generation_must_be_enabled.tooltip") :
                                Text.translatable("speedrunnermod.options.balanced_mode_conflicting.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.strongholdDistance(), customDataGenAndBalancedModeAndModifiedStrongholdGeneration,
                Text.translatable("speedrunnermod.options.stronghold_distance.tooltip"),
                !options().main.customDataGeneration.getCurrentValue() ?
                Text.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip") :
                        !isBalancedMode() ?
                                Text.translatable("speedrunnermod.options.modified_stronghold_generation_must_be_enabled.tooltip") :
                                Text.translatable("speedrunnermod.options.balanced_mode_conflicting.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.strongholdSpread(), customDataGenAndBalancedModeAndModifiedStrongholdGeneration,
                Text.translatable("speedrunnermod.options.stronghold_spread.tooltip"),
                !options().main.customDataGeneration.getCurrentValue() ?
                Text.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip") :
                        !isBalancedMode() ?
                                Text.translatable("speedrunnermod.options.modified_stronghold_generation_must_be_enabled.tooltip") :
                                Text.translatable("speedrunnermod.options.balanced_mode_conflicting.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.strongholdPortalRoomCount(), customDataGenAndBalancedModeAndModifiedStrongholdGeneration,
                Text.translatable("speedrunnermod.options.stronghold_portal_room_count.tooltip"),
                !options().main.customDataGeneration.getCurrentValue() ?
                Text.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip") :
                        !isBalancedMode() ?
                                Text.translatable("speedrunnermod.options.modified_stronghold_generation_must_be_enabled.tooltip") :
                                Text.translatable("speedrunnermod.options.balanced_mode_conflicting.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.strongholdLibraryCount(), customDataGenAndBalancedModeAndModifiedStrongholdGeneration,
                Text.translatable("speedrunnermod.options.stronghold_library_count.tooltip"),
                !options().main.customDataGeneration.getCurrentValue() ?
                Text.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip") :
                        !isBalancedMode() ?
                                Text.translatable("speedrunnermod.options.modified_stronghold_generation_must_be_enabled.tooltip") :
                                Text.translatable("speedrunnermod.options.balanced_mode_conflicting.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.betterBiomes(), options().main.customDataGeneration.getCurrentValue() && options().main.customBiomesAndCustomBiomeFeatures.getCurrentValue(),
                Text.translatable("speedrunnermod.options.better_biomes.tooltip"),
                !options().main.customDataGeneration.getCurrentValue() ?
                        Text.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip") :
                        Text.translatable("speedrunnermod.options.custom_biomes_and_custom_biome_features_must_be_enabled.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.customBiomesAndCustomBiomeFeatures(), options().main.customDataGeneration.getCurrentValue(),
                Text.translatable("speedrunnermod.options.custom_biomes_and_custom_biome_features.tooltip"),
                Text.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip")
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