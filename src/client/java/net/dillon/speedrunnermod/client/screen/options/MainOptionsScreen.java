package net.dillon.speedrunnermod.client.screen.options;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.option.ModListOptions;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.SimpleOption;

import java.io.File;

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
                ModListOptions.BETTER_BIOMES,
                ModListOptions.CUSTOM_BIOMES_AND_CUSTOM_BIOME_FEATURES,
                ModListOptions.FIREPROOF_ITEMS,
                ModListOptions.THROWABLE_FIREBALLS,
                ModListOptions.FALL_DAMAGE,
                ModListOptions.KINETIC_DAMAGE,
                ModListOptions.STRONGHOLD_COUNT,
                ModListOptions.STRONGHOLD_DISTANCE,
                ModListOptions.STRONGHOLD_SPREAD,
                ModListOptions.STRONGHOLD_PORTAL_ROOM_COUNT,
                ModListOptions.STRONGHOLD_LIBRARY_COUNT,
                ModListOptions.NETHER_PORTAL_DELAY,
                ModListOptions.GLOBAL_NETHER_PORTALS,
                ModListOptions.LAVA_BOATS,
                ModListOptions.NETHER_WATER,
                ModListOptions.COMMON_ORES,
                ModListOptions.BETTER_ANVIL,
                ModListOptions.ANVIL_COST_LIMIT,
                ModListOptions.HIGHER_ENCHANTMENT_LEVELS,
                ModListOptions.RIGHT_CLICK_TO_REMOVE_SILK_TOUCH,
                ModListOptions.ARROWS_DESTROY_BEDS,
                ModListOptions.MOB_SPAWNING_RATE,
                ModListOptions.FASTER_SPAWNERS,
                ModListOptions.KILL_GHAST_ON_FIREBALL,
                ModListOptions.CUSTOM_DATA_GENERATION
        };
    }

    @Override
    protected void init() {
        this.initializeOptionListWidget();
        this.optionList.addSingleOptionEntry(ModListOptions.PLAYING_MODE);
        this.optionList.addSingleOptionEntry(ModListOptions.STRUCTURE_SPAWN_RATE);
        this.optionList.addAll(mainOptions());
        this.addSelectableChild(this.optionList);
        this.configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), ModOptions.CONFIG);

        super.init();
    }

    @Override
    protected String pageId() {
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
    protected boolean isOptionsScreen() {
        return true;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }
}