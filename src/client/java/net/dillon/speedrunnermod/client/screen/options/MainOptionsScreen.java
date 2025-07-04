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

/**
 * The Speedrunner Mod's {@code main options.}
 */
@Environment(EnvType.CLIENT)
public class MainOptionsScreen extends AbstractModScreen {

    public MainOptionsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_OPTIONS_MAIN);
    }

    /**
     * All of the {@code main options.}
     * <p>These are displayed in order.</p>
     */
    private SimpleOption<?>[] mainOptions() {
        return new SimpleOption[]{
                ModListOptions.tutorialMode(),
                ModListOptions.fasterBlockBreaking(),
                ModListOptions.blockBreakingMultiplier(),
                ModListOptions.dragonPerchTime(),
                ModListOptions.icarusMode(),
                ModListOptions.infiniPearlMode(),
                ModListOptions.betterVillagerTrades(),
                ModListOptions.betterFoods(),
                ModListOptions.fireproofItems(),
                ModListOptions.throwableFireballs(),
                ModListOptions.fallDamage(),
                ModListOptions.kineticDamage(),
                ModListOptions.strongholdCount(),
                ModListOptions.strongholdDistance(),
                ModListOptions.strongholdSpread(),
                ModListOptions.strongholdPortalRoomCount(),
                ModListOptions.strongholdLibraryCount(),
                ModListOptions.globalNetherPortals(),
                ModListOptions.lavaBoats(),
                ModListOptions.netherWater(),
                ModListOptions.commonOres(),
                ModListOptions.betterAnvil(),
                ModListOptions.anvilCostLimit(),
                ModListOptions.higherEnchantmentLevels(),
                ModListOptions.arrowsDestroyBeds(),
                ModListOptions.mobSpawningRate(),
                ModListOptions.fasterSpawners(),
                ModListOptions.killGhastOnFireball(),
                ModListOptions.showDeathCords(),
                ModListOptions.customDataGeneration()
        };
    }

    @Override
    protected void init() {
        super.init();
        this.optionList.addSingleOptionEntry(ModListOptions.mode());
        this.optionList.addSingleOptionEntry(ModListOptions.structureSpawnRate());
        this.optionList.addAll(mainOptions());
        this.optionList.addSingleOptionEntry(ModListOptions.netherPortalDelay());
        this.optionList.addSingleOptionEntry(ModListOptions.rightClickToRemoveSilkTouch());
        this.optionList.addSingleOptionEntry(ModListOptions.customBiomesAndCustomBiomeFeatures());

        this.addSelectableChild(this.optionList);
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

        this.lockOptionWithTooltip(ModListOptions.strongholdCount(), options().main.customDataGeneration.getCurrentValue(),
                Text.translatable("speedrunnermod.options.stronghold_count.tooltip"),
                Text.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.strongholdDistance(), options().main.customDataGeneration.getCurrentValue(),
                Text.translatable("speedrunnermod.options.stronghold_distance.tooltip"),
                Text.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.strongholdSpread(), options().main.customDataGeneration.getCurrentValue(),
                Text.translatable("speedrunnermod.options.stronghold_spread.tooltip"),
                Text.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.strongholdPortalRoomCount(), options().main.customDataGeneration.getCurrentValue(),
                Text.translatable("speedrunnermod.options.stronghold_portal_room_count.tooltip"),
                Text.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.strongholdLibraryCount(), options().main.customDataGeneration.getCurrentValue(),
                Text.translatable("speedrunnermod.options.stronghold_library_count.tooltip"),
                Text.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip")
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