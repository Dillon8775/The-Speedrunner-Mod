package net.dillon.speedrunnermod.client.screen.options;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.client.util.ButtonSide;
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
                ModListOptions.TUTORIAL_MODE,
                ModListOptions.FASTER_BLOCK_BREAKING,
                ModListOptions.BLOCK_BREAKING_MULTIPLIER,
                ModListOptions.DRAGON_PERCH_TIME,
                ModListOptions.ICARUS_MODE,
                ModListOptions.INFINI_PEARL_MODE,
                ModListOptions.BETTER_VILLAGER_TRADES,
                ModListOptions.BETTER_FOODS,
                ModListOptions.FIREPROOF_ITEMS,
                ModListOptions.THROWABLE_FIREBALLS,
                ModListOptions.FALL_DAMAGE,
                ModListOptions.KINETIC_DAMAGE,
                ModListOptions.STRONGHOLD_COUNT,
                ModListOptions.STRONGHOLD_DISTANCE,
                ModListOptions.STRONGHOLD_SPREAD,
                ModListOptions.STRONGHOLD_PORTAL_ROOM_COUNT,
                ModListOptions.STRONGHOLD_LIBRARY_COUNT,
                ModListOptions.GLOBAL_NETHER_PORTALS,
                ModListOptions.LAVA_BOATS,
                ModListOptions.NETHER_WATER,
                ModListOptions.COMMON_ORES,
                ModListOptions.BETTER_ANVIL,
                ModListOptions.ANVIL_COST_LIMIT,
                ModListOptions.HIGHER_ENCHANTMENT_LEVELS,
                ModListOptions.ARROWS_DESTROY_BEDS,
                ModListOptions.MOB_SPAWNING_RATE,
                ModListOptions.FASTER_SPAWNERS,
                ModListOptions.KILL_GHAST_ON_FIREBALL,
                ModListOptions.SHOW_DEATH_CORDS,
                ModListOptions.CUSTOM_DATA_GENERATION
        };
    }

    @Override
    protected void init() {
        super.init();
        this.optionList.addSingleOptionEntry(ModListOptions.PLAYING_MODE);
        this.optionList.addSingleOptionEntry(ModListOptions.STRUCTURE_SPAWN_RATE);
        this.optionList.addAll(mainOptions());
        this.optionList.addSingleOptionEntry(ModListOptions.NETHER_PORTAL_DELAY);
        this.optionList.addSingleOptionEntry(ModListOptions.RIGHT_CLICK_TO_REMOVE_SILK_TOUCH);
        this.optionList.addSingleOptionEntry(ModListOptions.CUSTOM_BIOMES_AND_CUSTOM_BIOME_FEATURES);

        this.deactivateOptionIf(3, ButtonSide.LEFT, options().main.fasterBlockBreaking);
        this.deactivateOptionIf(8, ButtonSide.LEFT, options().main.customDataGeneration);
        this.deactivateOptionIf(8, ButtonSide.RIGHT, options().main.customDataGeneration);
        this.deactivateOptionIf(9, ButtonSide.LEFT, options().main.customDataGeneration);
        this.deactivateOptionIf(9, ButtonSide.RIGHT, options().main.customDataGeneration);
        this.deactivateOptionIf(10, ButtonSide.LEFT, options().main.customDataGeneration);

        this.addSelectableChild(this.optionList);
    }

    @Override
    protected void renderOptionTooltips(DrawContext context, int mouseX, int mouseY) {
        this.renderOptionTooltip(
                3,
                ButtonSide.LEFT,
                options().main.fasterBlockBreaking,
                Text.translatable("speedrunnermod.options.block_breaking_multiplier.tooltip"),
                Text.translatable("speedrunnermod.options.block_breaking_multiplier.inactive"),
                context,
                mouseX,
                mouseY
        );
        this.renderOptionTooltip(
                8,
                ButtonSide.LEFT,
                options().main.customDataGeneration,
                Text.translatable("speedrunnermod.options.stronghold_count.tooltip"),
                Text.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip"),
                context,
                mouseX,
                mouseY
        );
        this.renderOptionTooltip(
                8,
                ButtonSide.RIGHT,
                options().main.customDataGeneration,
                Text.translatable("speedrunnermod.options.stronghold_distance.tooltip"),
                Text.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip"),
                context,
                mouseX,
                mouseY
        );
        this.renderOptionTooltip(
                9,
                ButtonSide.LEFT,
                options().main.customDataGeneration,
                Text.translatable("speedrunnermod.options.stronghold_spread.tooltip"),
                Text.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip"),
                context,
                mouseX,
                mouseY
        );
        this.renderOptionTooltip(
                9,
                ButtonSide.RIGHT,
                options().main.customDataGeneration,
                Text.translatable("speedrunnermod.options.stronghold_portal_room_count.tooltip"),
                Text.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip"),
                context,
                mouseX,
                mouseY
        );
        this.renderOptionTooltip(
                10,
                ButtonSide.LEFT,
                options().main.customDataGeneration,
                Text.translatable("speedrunnermod.options.stronghold_library_count.tooltip"),
                Text.translatable("speedrunnermod.options.custom_data_generation_must_be_enabled.tooltip"),
                context,
                mouseX,
                mouseY
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