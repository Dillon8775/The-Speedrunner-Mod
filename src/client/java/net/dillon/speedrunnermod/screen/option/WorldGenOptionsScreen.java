package net.dillon.speedrunnermod.screen.option;

import net.dillon.speedrunnermod.option.ListOptions;
import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isBalancedMode;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

/**
 * Options for WorldGen-related features.
 */
public class WorldGenOptionsScreen extends AbstractModScreen {
    private AbstractWidget structureSpawnRate, strongholdCount, strongholdDistance, strongholdSpread, strongholdPortalRoomCount, strongholdLibraryCount, betterBiomes, customBiomesAndCustomBiomeFeatures;

    public WorldGenOptionsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_OPTIONS_WORLDGEN);
    }

    /**
     * All of the {@code worldgen options.}
     * <p>These are displayed in order.</p>
     */
    private List<AbstractWidget> worldGenOptions() {
        this.structureSpawnRate = createOption(ListOptions.structureSpawnRate());
        this.strongholdCount = createOption(ListOptions.strongholdCount());
        this.strongholdDistance = createOption(ListOptions.strongholdDistance());
        this.strongholdSpread = createOption(ListOptions.strongholdSpread());
        this.strongholdPortalRoomCount = createOption(ListOptions.strongholdPortalRoomCount());
        this.strongholdLibraryCount = createOption(ListOptions.strongholdLibraryCount());
        this.betterBiomes = createOption(ListOptions.betterBiomes());
        this.customBiomesAndCustomBiomeFeatures = createOption(ListOptions.customBiomesAndCustomBiomeFeatures());

        return List.of(
                this.structureSpawnRate,
                createOption(ListOptions.creatureSpawningRate()),

                createOption(ListOptions.commonOres()),
                this.betterBiomes,

                createOption(ListOptions.globalNetherPortals()),
                createOption(ListOptions.netherWater()),

                createOption(ListOptions.netherPortalDelay()),
                createOption(ListOptions.arrowsDestroyBeds()),

                createOption(ListOptions.customDataGeneration()),
                this.strongholdCount,

                this.strongholdDistance,
                this.strongholdSpread,

                this.strongholdPortalRoomCount,
                this.strongholdLibraryCount
        );
    }

    @Override
    protected void init() {
        this.initializeCustomButtonListWidget();

        this.customBiomesAndCustomBiomeFeatures = createOption(ListOptions.customBiomesAndCustomBiomeFeatures());

        this.buttonList.addAll(this.worldGenOptions());
        this.buttonList.addSingleOptionEntry(createOption(ListOptions.rightClickToRemoveSilkTouch()));
        this.buttonList.addSingleOptionEntry(this.customBiomesAndCustomBiomeFeatures);

        super.init();
    }

    @Override
    protected void lockOptionsAndRenderTooltips(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        this.lockOptionWithTooltip(this.structureSpawnRate, options().worldGen.customDataGeneration.getCurrentValue(),
                ListOptions.structureSpawnRateTooltip(),
                Component.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        boolean customDataGen = options().worldGen.customDataGeneration.getCurrentValue();
        boolean customDataGenAndBalancedMode = customDataGen && !isBalancedMode();
        boolean customDataGenAndBalancedModeAndModifiedStrongholdGeneration = customDataGenAndBalancedMode && options().advanced.modifiedStrongholdGeneration.getCurrentValue();

        this.lockOptionWithTooltip(this.strongholdCount, customDataGenAndBalancedModeAndModifiedStrongholdGeneration,
                Component.translatable("speedrunnermod.options.stronghold_count.tooltip"),
                !options().worldGen.customDataGeneration.getCurrentValue() ?
                        Component.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip") :
                        !isBalancedMode() ?
                                Component.translatable("speedrunnermod.options.modified_stronghold_generation_must_be_enabled.tooltip") :
                                Component.translatable("speedrunnermod.options.balanced_mode_conflicting.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.strongholdDistance, customDataGenAndBalancedModeAndModifiedStrongholdGeneration && !isDoomMode(),
                Component.translatable("speedrunnermod.options.stronghold_distance.tooltip"),
                isDoomMode() ?
                        Component.translatable("speedrunnermod.options.mode_easy_required.tooltip") :
                        !options().worldGen.customDataGeneration.getCurrentValue() ?
                                Component.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip") :
                                    !isBalancedMode() ?
                                            Component.translatable("speedrunnermod.options.modified_stronghold_generation_must_be_enabled.tooltip") :
                                            Component.translatable("speedrunnermod.options.balanced_mode_conflicting.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.strongholdSpread, customDataGenAndBalancedModeAndModifiedStrongholdGeneration && !isDoomMode(),
                Component.translatable("speedrunnermod.options.stronghold_spread.tooltip"),
                isDoomMode() ?
                        Component.translatable("speedrunnermod.options.mode_easy_required.tooltip") :
                            !options().worldGen.customDataGeneration.getCurrentValue() ?
                                    Component.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip") :
                                        !isBalancedMode() ?
                                                Component.translatable("speedrunnermod.options.modified_stronghold_generation_must_be_enabled.tooltip") :
                                                Component.translatable("speedrunnermod.options.balanced_mode_conflicting.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.strongholdPortalRoomCount, customDataGenAndBalancedModeAndModifiedStrongholdGeneration,
                Component.translatable("speedrunnermod.options.stronghold_portal_room_count.tooltip"),
                !options().worldGen.customDataGeneration.getCurrentValue() ?
                        Component.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip") :
                        !isBalancedMode() ?
                                Component.translatable("speedrunnermod.options.modified_stronghold_generation_must_be_enabled.tooltip") :
                                Component.translatable("speedrunnermod.options.balanced_mode_conflicting.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.strongholdLibraryCount, customDataGenAndBalancedModeAndModifiedStrongholdGeneration,
                Component.translatable("speedrunnermod.options.stronghold_library_count.tooltip"),
                !options().worldGen.customDataGeneration.getCurrentValue() ?
                        Component.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip") :
                        !isBalancedMode() ?
                                Component.translatable("speedrunnermod.options.modified_stronghold_generation_must_be_enabled.tooltip") :
                                Component.translatable("speedrunnermod.options.balanced_mode_conflicting.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.betterBiomes, options().worldGen.customDataGeneration.getCurrentValue() && options().general.customBiomesAndCustomBiomeFeatures.getCurrentValue(),
                Component.translatable("speedrunnermod.options.better_biomes.tooltip"),
                !options().worldGen.customDataGeneration.getCurrentValue() ?
                        Component.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip") :
                        Component.translatable("speedrunnermod.options.custom_biomes_and_custom_biome_features_must_be_enabled.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.customBiomesAndCustomBiomeFeatures, options().worldGen.customDataGeneration.getCurrentValue(),
                Component.translatable("speedrunnermod.options.custom_biomes_and_custom_biome_features.tooltip"),
                Component.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip"),
                graphics,
                mouseX,
                mouseY
        );
    }

    @Override
    public String pageId() {
        return "gfhtgfrt";
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