package net.dillon.speedrunnermod.screen.option;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.option.ListOptions;
import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;
import static net.dillon.speedrunnermod.option.CommonModOptions.isBalancedMode;
import static net.dillon.speedrunnermod.option.CommonModOptions.isDoomMode;
import static net.dillon.speedrunnermod.option.ListOptions.ofWorldReload;

/**
 * Options for WorldGen-related features.
 */
public class WorldGenOptionsScreen extends AbstractModScreen {
    private AbstractWidget structureSpawnRate, totalStrongholds, strongholdDistance, strongholdSpread, totalPortalRooms, totalLibraries;

    public WorldGenOptionsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_OPTIONS_WORLDGEN);
    }

    /**
     * All of the {@code worldgen options.}
     * <p>These are displayed in order.</p>
     */
    private List<AbstractWidget> worldGenOptions() {
        this.structureSpawnRate = createOption(ListOptions.structureSpawnRate());
        this.totalStrongholds = createOption(ListOptions.totalStrongholds());
        this.strongholdDistance = createOption(ListOptions.strongholdDistance());
        this.strongholdSpread = createOption(ListOptions.strongholdSpread());
        this.totalPortalRooms = createOption(ListOptions.totalPortalRooms());
        this.totalLibraries = createOption(ListOptions.totalLibraries());

        return List.of(
                this.structureSpawnRate,
                createOption(ListOptions.creatureSpawningRate()),

                createOption(ListOptions.netherPortalDelay()),
                createOption(ListOptions.arrowsDestroyBeds()),

                createOption(ListOptions.globalNetherPortals()),
                createOption(ListOptions.netherWater()),

                createOption(ListOptions.betterBiomes()),
                createOption(ListOptions.generateSpeedrunnersWasteland()),

                createOption(ListOptions.commonOres()),
                createOption(ListOptions.generateSpeedrunnerWood()),

                createOption(ListOptions.commonPlainTrees()),
                this.totalStrongholds,

                this.strongholdDistance,
                this.strongholdSpread,

                this.totalPortalRooms,
                this.totalLibraries
        );
    }

    @Override
    protected void init() {
        this.initializeModButtonListWidget();

        this.buttonList.addAll(this.worldGenOptions());
        this.buttonList.addSingleOptionEntry(createOption(ListOptions.rightClickToRemoveSilkTouch()));

        super.init();
    }

    @Override
    protected void lockOptionsAndRenderTooltips(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        boolean balancedMode = !isBalancedMode();
        boolean balancedModeAndModifiedStrongholdGeneration = balancedMode && common().advanced.modifiedStrongholdGeneration.getCurrentValue();

        if (this.structureSpawnRate.isHovered()) {
            graphics.setTooltipForNextFrame(this.font, this.font.split(ListOptions.structureSpawnRateTooltip(), 200), mouseX, mouseY);
        }

        this.lockOptionWithTooltip(this.totalStrongholds, balancedModeAndModifiedStrongholdGeneration,
                ofWorldReload(Component.translatable("speedrunnermod.options.total_strongholds.tooltip")),
                !isBalancedMode() ?
                        Component.translatable("speedrunnermod.options.modified_stronghold_generation_must_be_enabled.tooltip") :
                        Component.translatable("speedrunnermod.options.balanced_mode_conflicting.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.strongholdDistance, balancedModeAndModifiedStrongholdGeneration && !isDoomMode(),
                ofWorldReload(Component.translatable("speedrunnermod.options.stronghold_distance.tooltip")),
                isDoomMode() ?
                        Component.translatable("speedrunnermod.options.mode_easy_required.tooltip") :
                        !isBalancedMode() ?
                                Component.translatable("speedrunnermod.options.modified_stronghold_generation_must_be_enabled.tooltip") :
                                Component.translatable("speedrunnermod.options.balanced_mode_conflicting.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.strongholdSpread, balancedModeAndModifiedStrongholdGeneration && !isDoomMode(),
                ofWorldReload(Component.translatable("speedrunnermod.options.stronghold_spread.tooltip")),
                isDoomMode() ?
                        Component.translatable("speedrunnermod.options.mode_easy_required.tooltip") :
                            !isBalancedMode() ?
                                    Component.translatable("speedrunnermod.options.modified_stronghold_generation_must_be_enabled.tooltip") :
                                    Component.translatable("speedrunnermod.options.balanced_mode_conflicting.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.totalPortalRooms, balancedModeAndModifiedStrongholdGeneration,
                ofWorldReload(Component.translatable("speedrunnermod.options.total_portals_rooms.tooltip")),
                !isBalancedMode() ?
                        Component.translatable("speedrunnermod.options.modified_stronghold_generation_must_be_enabled.tooltip") :
                        Component.translatable("speedrunnermod.options.balanced_mode_conflicting.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.totalLibraries, balancedModeAndModifiedStrongholdGeneration,
                ofWorldReload(Component.translatable("speedrunnermod.options.total_libraries.tooltip")),
                !isBalancedMode() ?
                        Component.translatable("speedrunnermod.options.modified_stronghold_generation_must_be_enabled.tooltip") :
                        Component.translatable("speedrunnermod.options.balanced_mode_conflicting.tooltip"),
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