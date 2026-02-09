package net.dillon.speedrunnermod.util;

import net.dillon.speedrunnermod.option.ModOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

/**
 * Stores all {@code translation keys} for the Speedrunner Mod.
 */
public class ModTexts {
    // Main
    public static final Text BLANK = Text.literal("");
    public static final Text OK = Text.translatable("speedrunnermod.ok");
    public static final Text SAVE = Text.translatable("speedrunnermod.save");
    public static final Text SAVE_TOOLTIP = Text.translatable("speedrunnermod.save.tooltip");
    public static final Text NEXT = Text.translatable("speedrunnermod.next");
    public static final Text NEXT_ARROW = Text.literal(">").formatted(Formatting.BOLD);
    public static final Text NEXT_ARROW_TOOLTIP = Text.translatable("speedrunnermod.next.tooltip");
    public static final Text PREVIOUS = Text.literal("<").formatted(Formatting.BOLD);
    public static final Text PREVIOUS_TOOLTIP = Text.translatable("speedrunnermod.previous.tooltip");
    public static final Text BACK = Text.translatable("speedrunnermod.back");
    public static final Text HELP_TOOLTIP = Text.translatable("speedrunnermod.help_button.tooltip");
    public static final Text ON = Text.literal("ON").formatted(Formatting.GREEN);
    public static final Text OFF = Text.literal("OFF").formatted(Formatting.RED);
    public static final Text YES = Text.literal("YES").formatted(Formatting.GREEN);
    public static final Text NO = Text.literal("NO").formatted(Formatting.RED);
    public static final Text LETS_GO = Text.translatable("speedrunnermod.lets_go");
    public static final Text REFRESHING = Text.literal("Refreshing...");
    public static final Text ENABLED = Text.literal("Enabled").formatted(Formatting.GREEN);
    public static final Text DISABLED = Text.literal("DISABLED").formatted(Formatting.BOLD).formatted(Formatting.RED);
    public static final Text FEATURE_DISABLED = Text.literal("Feature Disabled");

    // Titles and menus
    public static final Text TITLE = Text.translatable("speedrunnermod.title");
    public static final Text MENU_OPTIONS_ACTION_NEEDED = Text.translatable("speedrunnermod.leaderboards.action_needed");
    public static final Text MENU_OPTIONS_SAFE = Text.translatable("speedrunnermod.leaderboards.safe");
    public static final Text TITLE_OPTIONS_MAIN = Text.translatable("speedrunnermod.title.options.main");
    public static final Text TITLE_OPTIONS_CLIENT = Text.translatable("speedrunnermod.title.options.client");
    public static final Text MENU_OPTIONS_RESET = Text.translatable("speedrunnermod.menu.options.reset");
    public static final Text TITLE_OPTIONS_RESET = Text.translatable("speedrunnermod.title.options.reset");
    public static final Text MENU_TUTORIAL_MODE_OPTIONS_RESET = Text.translatable("speedrunnermod.menu.options.tutorial_mode.reset");
    public static final Text TITLE_TUTORIAL_MODE_OPTIONS_RESET = Text.translatable("speedrunnermod.title.options.tutorial_mode.reset");
    public static final Text MENU_TUTORIAL_MODE_OPTIONS_RESET_TOOLTIP = Text.translatable("speedrunnermod.menu.options.tutorial_mode.reset.tooltip");
    public static final Text MENU_OPTIONS_TOOLTIP = Text.translatable("speedrunnermod.menu.options.tooltip");
    public static final Text MENU_OPTIONS_MAIN = Text.translatable("speedrunnermod.menu.options.main");
    public static final Text MENU_OPTIONS_MAIN_TOOLTIP = Text.translatable("speedrunnermod.menu.options.main.tooltip");
    public static final Text MENU_FAST_WORLD_CREATION = Text.translatable("speedrunnermod.menu.options.fast_world_creation");
    public static final Text MENU_FAST_WORLD_CREATION_TOOLTIP = Text.translatable("speedrunnermod.menu.options.fast_world_creation.tooltip");
    public static final Text TITLE_FAST_WORLD_CREATION = Text.translatable("speedrunnermod.title.options.fast_world_creation");
    public static final Text MENU_OPTIONS_CLIENT = Text.translatable("speedrunnermod.menu.options.client");
    public static final Text MENU_OPTIONS_CLIENT_TOOLTIP = Text.translatable("speedrunnermod.menu.options.client.tooltip");
    public static final Text MENU_OPTIONS_RESET_TOOLTIP = Text.translatable("speedrunnermod.menu.options.reset.tooltip");
    public static final Text MENU_STRUCTURE_SPAWN_RATE_OPTIONS = Text.translatable("speedrunnermod.menu.options.structure_spawn_rates");
    public static final Text MENU_STRUCTURE_SPAWN_RATE_OPTIONS_TOOLTIP = Text.translatable("speedrunnermod.menu.options.structure_spawn_rates.tooltip");
    public static final Text MENU_STRUCTURE_SPAWN_RATE_OPTIONS_NEEDS_CUSTOM_TOOLTIP = Text.translatable("speedrunnermod.menu.options.structure_spawn_rates_needs_custom.tooltip");
    public static final Text TITLE_STRUCTURE_SPAWN_RATE_OPTIONS = Text.translatable("speedrunnermod.title.options.structure_spawn_rates");
    public static final Text MENU_MIXIN_OPTIONS = Text.translatable("speedrunnermod.menu.options.mixins");
    public static final Text MENU_MIXIN_OPTIONS_TOOLTIP = Text.translatable("speedrunnermod.menu.options.mixins.tooltip");
    public static final Text TITLE_MIXIN_OPTIONS = Text.translatable("speedrunnermod.title.options.mixins");
    public static final Text MENU_ADVANCED_OPTIONS = Text.translatable("speedrunnermod.menu.options.advanced");
    public static final Text MENU_ADVANCED_OPTIONS_TOOLTIP = Text.translatable("speedrunnermod.menu.options.advanced.tooltip");
    public static final Text TITLE_ADVANCED_OPTIONS = Text.translatable("speedrunnermod.title.options.advanced");
    public static final Text MENU_CREDITS = Text.translatable("speedrunnermod.menu.credits");
    public static final Text MENU_CREDITS_TOOLIP = Text.translatable("speedrunnermod.menu.credits.tooltip");
    public static final Text MENU_EXTERNAL = Text.translatable("speedrunnermod.menu.external").formatted(Formatting.RED);
    public static final Text TITLE_EXTERNAL = Text.translatable("speedrunnermod.external");
    public static final Text MENU_FEATURES_TOOLTIP = Text.translatable("speedrunnermod.menu.features.tooltip");
    public static final Text MENU_FEATURES = Text.translatable("speedrunnermod.menu.features").formatted(Formatting.AQUA);
    public static final Text TITLE_FEATURES = Text.translatable("speedrunnermod.title.features");
    public static final Text MENU_RESOURCES = Text.translatable("speedrunnermod.menu.resources");
    public static final Text MENU_RESOURCES_TOOLTIP = Text.translatable("speedrunnermod.menu.resources.tooltip");
    public static final Text TITLE_RESOURCES = Text.translatable("speedrunnermod.title.resources");
    public static final Text MENU_MODS = Text.translatable("speedrunnermod.menu.resources.mods").formatted(Formatting.AQUA);
    public static final Text TITLE_MODS = Text.translatable("speedrunnermod.title.resources.mods");
    public static final Text MENU_TUTORIALS = Text.translatable("speedrunnermod.menu.resources.tutorials").formatted(Formatting.DARK_AQUA);
    public static final Text TITLE_TUTORIALS = Text.translatable("speedrunnermod.title.resources.tutorials");
    public static final Text TITLE_RESTART_REQUIRED = Text.translatable("speedrunnermod.title.restart_required");
    public static final Text TITLE_SAFE_BOOT = Text.translatable("speedrunnermod.title.safe_mode");
    public static final Text TITLE_SPEEDRUN_IGT_MISSING = Text.translatable("speedrunnermod.title.speedrun_igt_missing");
    public static final Text ENABLE_DOOM_MODE = Text.translatable("speedrunnermod.doom_mode.enable").formatted(Formatting.RED);
    public static final Text DOOM_MODE_ALREADY_ENABLED = Text.translatable("speedrunnermod.doom_mode.already_enabled").formatted(Formatting.RED);
    public static final Text MENU_DOOM_MODE = Text.translatable("speedrunnermod.menu.doom_mode");

    // Feature screens enable/disable features
    public static final Text DISABLE_LAVA_BOATS = Text.translatable("speedrunnermod.options.lava_boats.disable").formatted(Formatting.GOLD);
    public static final Text ENABLE_LAVA_BOATS = Text.translatable("speedrunnermod.options.lava_boats.enable").formatted(Formatting.GREEN);
    public static final Text STOP_SPEEDRUNNERS_WASTELAND_BIOME_FROM_GENERATING = Text.translatable("speedrunnermod.options.stop_speedrunners_wasteland_biome_from_generating").formatted(Formatting.RED);
    public static final Text ALLOW_SPEEDRUNNERS_WASTELAND_BIOME_TO_GENERATE = Text.translatable("speedrunnermod.options.allow_speedrunners_wasteland_biome_to_generate").formatted(Formatting.AQUA);
    public static final Text DISABLE_THIS_FEATURE = Text.translatable("speedrunnermod.disable_this_feature").formatted(Formatting.RED);
    public static final Text ENABLE_THIS_FEATURE = Text.translatable("speedrunnermod.enable_this_feature").formatted(Formatting.GREEN);
    public static final Text DISABLE_ICARUS_MODE = Text.translatable("speedrunnermod.disable_icarus_mode").formatted(Formatting.GRAY);
    public static final Text ENABLE_ICARUS_MODE = Text.translatable("speedrunnermod.enable_icarus_mode").formatted(Formatting.GRAY);
    public static final Text DISABLE_INFINI_PEARL_MODE = Text.translatable("speedrunnermod.disable_infini_pearl_mode").formatted(Formatting.BLUE);
    public static final Text ENABLE_INFINI_PEARL_MODE = Text.translatable("speedrunnermod.enable_infini_pearl_mode").formatted(Formatting.BLUE);
    public static final Text DISABLE_HIGHER_ENCHANT_LEVELS = Text.translatable("speedrunnermod.disable_higher_enchant_levels").formatted(Formatting.RED);
    public static final Text ENABLE_HIGHER_ENCHANT_LEVELS = Text.translatable("speedrunnermod.enable_higher_enchant_levels").formatted(Formatting.LIGHT_PURPLE);
    public static final Text CONFIGURE_OPTION = Text.translatable("speedrunnermod.configure_option");

    // Feature screen categories
    public static final Text MENU_BLOCKS_AND_ITEMS = Text.translatable("speedrunnermod.menu.features.blocks_and_items");
    public static final Text TITLE_BLOCKS_AND_ITEMS = Text.translatable("speedrunnermod.title.features.blocks_and_items");
    public static final Text MENU_TOOLS_AND_ARMOR = Text.translatable("speedrunnermod.menu.features.tools_and_armor");
    public static final Text TITLE_TOOLS_AND_ARMOR = Text.translatable("speedrunnermod.title.features.tools_and_armor");
    public static final Text MENU_ORES_AND_WORLDGEN = Text.translatable("speedrunnermod.menu.features.ores_and_worldgen");
    public static final Text TITLE_ORES_AND_WORLDGEN = Text.translatable("speedrunnermod.title.features.ores_and_worldgen");
    public static final Text MENU_MISCELLANEOUS = Text.translatable("speedrunnermod.menu.features.miscellaneous");
    public static final Text TITLE_MISCELLANEOUS = Text.translatable("speedrunnermod.title.features.miscellaneous");
    public static final Text MENU_FEATURE_DOOM_MODE = Text.translatable("speedrunnermod.menu.features.doom_mode");
    public static final Text TITLE_FEATURE_DOOM_MODE = Text.translatable("speedrunnermod.title.features.doom_mode");

    // Blocks and items feature screens
    public static final Text TITLE_FEATURE_BLAZE_SPOTTER = Text.translatable("speedrunnermod.title.features.blocks_and_items.blaze_spotter");
    public static final Text TITLE_FEATURE_DEAD_SPEEDRUNNER_WOOD = Text.translatable("speedrunnermod.title.features.blocks_and_items.dead_speedrunner_wood");
    public static final Text TITLE_FEATURE_DRAGONS_PEARL = Text.translatable("speedrunnermod.title.features.blocks_and_items.dragons_pearl");
    public static final Text TITLE_FEATURE_DRAGONS_AURA = Text.translatable("speedrunnermod.title.features.blocks_and_items.dragons_aura");
    public static final Text TITLE_FEATURE_INVENTORY_PRESERVER = Text.translatable("speedrunnermod.title.features.blocks_and_items.inventory_preserver");
    public static final Text TITLE_FEATURE_DRAGONS_FIREBALL = Text.translatable("speedrunnermod.title.features.blocks_and_items.dragons_fireball");
    public static final Text TITLE_FEATURE_ENDER_MATTER = Text.translatable("speedrunnermod.title.features.blocks_and_items.ender_matter");
    public static final Text TITLE_FEATURE_ENDER_THRUSTER = Text.translatable("speedrunnermod.title.features.blocks_and_items.ender_thruster");
    public static final Text TITLE_FEATURE_EYE_OF_ANNUL = Text.translatable("speedrunnermod.title.features.blocks_and_items.eye_of_annul");
    public static final Text TITLE_FEATURE_EYE_OF_INFERNO = Text.translatable("speedrunnermod.title.features.blocks_and_items.eye_of_inferno");
    public static final Text TITLE_FEATURE_IGNEOUS_ROCKS = Text.translatable("speedrunnermod.title.features.blocks_and_items.igneous_rocks");
    public static final Text TITLE_FEATURE_INFINI_PEARL = Text.translatable("speedrunnermod.title.features.blocks_and_items.infini_pearl");
    public static final Text TITLE_FEATURE_MORE_BOATS = Text.translatable("speedrunnermod.title.features.blocks_and_items.more_boats");
    public static final Text TITLE_FEATURE_PIGLIN_AWAKENER = Text.translatable("speedrunnermod.title.features.blocks_and_items.piglin_awakener");
    public static final Text TITLE_FEATURE_RAID_ERADICATOR = Text.translatable("speedrunnermod.title.features.blocks_and_items.raid_eradicator");
    public static final Text TITLE_FEATURE_SPEEDRUNNER_BLOCKS = Text.translatable("speedrunnermod.title.features.blocks_and_items.speedrunner_blocks");
    public static final Text TITLE_FEATURE_SPEEDRUNNER_BULK = Text.translatable("speedrunnermod.title.features.blocks_and_items.speedrunner_bulk");
    public static final Text TITLE_FEATURE_SPEEDRUNNER_INGOTS = Text.translatable("speedrunnermod.title.features.blocks_and_items.speedrunner_ingots");
    public static final Text TITLE_FEATURE_SPEEDRUNNER_NUGGETS = Text.translatable("speedrunnermod.title.features.blocks_and_items.speedrunner_nuggets");
    public static final Text TITLE_FEATURE_SPEEDRUNNER_PADDLE = Text.translatable("speedrunnermod.title.features.blocks_and_items.speedrunner_paddle");
    public static final Text TITLE_FEATURE_SPEEDRUNNERS_EYE = Text.translatable("speedrunnermod.title.features.blocks_and_items.speedrunners_eye");
    public static final Text TITLE_FEATURE_SPEEDRUNNERS_TOTEM = Text.translatable("speedrunnermod.title.features.blocks_and_items.speedrunners_totem");
    public static final Text TITLE_FEATURE_SPEEDRUNNERS_WORKBENCH = Text.translatable("speedrunnermod.title.features.blocks_and_items.speedrunners_workbench");
    public static final Text TITLE_FEATURE_SPEEDRUNNER_WOOD = Text.translatable("speedrunnermod.title.features.blocks_and_items.speedrunner_wood");
    public static final Text TITLE_FEATURE_SPEEDRUNNER_BOW_AND_CROSSBOW = Text.translatable("speedrunnermod.title.features.tools_and_armor.speedrunner_bow_and_crossbow");
    public static final Text TITLE_FEATURE_SPEEDRUNNER_FLINT_AND_STEEL = Text.translatable("speedrunnermod.title.features.tools_and_armor.speedrunner_flint_and_steel");
    public static final Text TITLE_FEATURE_SPEEDRUNNER_SHEARS = Text.translatable("speedrunnermod.title.features.tools_and_armor.speedrunner_shears");
    public static final Text TITLE_FEATURE_SPEEDRUNNER_SHIELD = Text.translatable("speedrunnermod.title.features.tools_and_armor.speedrunner_shield");
    public static final Text TITLE_FEATURE_RAW_SPEEDRUNNER = Text.translatable("speedrunnermod.title.features.blocks_and_items.raw_speedrunner");

    // Tools and armor feature screens
    public static final Text TITLE_FEATURE_COOLDOWN_ENCHANTMENT = Text.translatable("speedrunnermod.title.features.tools_and_armor.cooldown_enchantment");
    public static final Text TITLE_FEATURE_DASH_ENCHANTMENT = Text.translatable("speedrunnermod.title.features.tools_and_armor.dash_enchantment");
    public static final Text TITLE_FEATURE_WITHERED_ENCHANTMENT = Text.translatable("speedrunnermod.title.features.tools_and_armor.withered_enchantment");
    public static final Text TITLE_FEATURE_DRAGONS_SWORD = Text.translatable("speedrunnermod.title.features.tools_and_armor.dragons_sword");
    public static final Text TITLE_FEATURE_GOLDEN_SPEEDRUNNER_ARMOR = Text.translatable("speedrunnermod.title.features.tools_and_armor.golden_speedrunner_armor");
    public static final Text TITLE_FEATURE_GOLDEN_SMITHING_TEMPLATE = Text.translatable("speedrunnermod.title.features.tools_and_armor.golden_smithing_template");
    public static final Text TITLE_FEATURE_SPEEDRUNNER_ARMOR = Text.translatable("speedrunnermod.title.features.tools_and_armor.speedrunner_armor");
    public static final Text TITLE_FEATURE_SPEEDRUNNER_SAFE_BOOTS = Text.translatable("speedrunnermod.title.features.tools_and_armor.speedrunner_safe_boots");

    // Ores and worldgen feature screens
    public static final Text TITLE_FEATURE_COMMON_ORES = Text.translatable("speedrunnermod.title.features.ores_and_worldgen.common_ores");
    public static final Text TITLE_FEATURE_EXPERIENCE_ORES = Text.translatable("speedrunnermod.title.features.ores_and_worldgen.experience_ores");
    public static final Text TITLE_FEATURE_EXPERIENCE_FRAGMENT = Text.translatable("speedrunnermod.title.features.ores_and_worldgen.experience_fragment");
    public static final Text TITLE_FEATURE_FORTRESSES_BASTIONS_AND_STRONGHOLDS = Text.translatable("speedrunnermod.title.features.ores_and_worldgen.fortresses_bastions_and_strongholds");
    public static final Text TITLE_FEATURE_IGNEOUS_ORES = Text.translatable("speedrunnermod.title.features.ores_and_worldgen.igneous_ores");
    public static final Text TITLE_FEATURE_SPEEDRUNNER_ORES = Text.translatable("speedrunnermod.title.features.ores_and_worldgen.speedrunner_ores");
    public static final Text TITLE_FEATURE_SPEEDRUNNERS_WASTELAND = Text.translatable("speedrunnermod.title.features.ores_and_worldgen.speedrunners_wasteland_biome");
    public static final Text TITLE_FEATURE_BETTER_BIOMES = Text.translatable("speedrunnermod.title.features.ores_and_worldgen.better_biomes");
    public static final Text TITLE_FEATURE_STRUCTURES = Text.translatable("speedrunnermod.title.features.ores_and_worldgen.structures");

    // Miscellaneous feature screens
    public static final Text TITLE_FEATURE_TUTORIAL_MODE = Text.translatable("speedrunnermod.title.features.miscellaneous.tutorial_mode");
    public static final Text TITLE_FEATURE_AND_MORE = Text.translatable("speedrunnermod.title.features.miscellaneous.and_more");
    public static final Text TITLE_FEATURE_ARROWS_EXPLODE_BEDS = Text.translatable("speedrunnermod.title.features.miscellaneous.arrows_explode_beds");
    public static final Text TITLE_FEATURE_BETTER_ANVILS = Text.translatable("speedrunnermod.title.features.miscellaneous.better_anvils");
    public static final Text TITLE_FEATURE_BETTER_DEATH_SCREEN = Text.translatable("speedrunnermod.title.features.miscellaneous.better_death_screen");
    public static final Text TITLE_FEATURE_BETTER_FOODS = Text.translatable("speedrunnermod.title.features.miscellaneous.better_foods");
    public static final Text TITLE_FEATURE_BETTER_HOTKEYS = Text.translatable("speedrunnermod.title.features.miscellaneous.better_hotkeys");
    public static final Text TITLE_FEATURE_BETTER_LOOT_TABLES = Text.translatable("speedrunnermod.title.features.miscellaneous.better_loot_tables");
    public static final Text TITLE_FEATURE_BETTER_NETHER_PORTALS = Text.translatable("speedrunnermod.title.features.miscellaneous.better_nether_portals");
    public static final Text TITLE_FEATURE_BETTER_PIGLIN_BARTERING = Text.translatable("speedrunnermod.title.features.miscellaneous.piglin_bartering");
    public static final Text TITLE_FEATURE_BETTER_VILLAGER_TRADES = Text.translatable("speedrunnermod.title.features.miscellaneous.better_villager_trades");
    public static final Text TITLE_FEATURE_BLAZES_DROP_GOLD = Text.translatable("speedrunnermod.title.features.miscellaneous.blazes_drop_gold");
    public static final Text TITLE_FEATURE_BLAZE_SPAWNERS_IN_BASTIONS = Text.translatable("speedrunnermod.title.features.miscellaneous.blaze_spawners_in_bastions");
    public static final Text TITLE_FEATURE_COOKED_FLESH = Text.translatable("speedrunnermod.title.features.miscellaneous.cooked_flesh");
    public static final Text TITLE_FEATURE_CUSTOM_PANORAMA = Text.translatable("speedrunnermod.title.features.miscellaneous.custom_panorama");
    public static final Text TITLE_FEATURE_ENDER_EYES_NEVER_BREAK = Text.translatable("speedrunnermod.title.features.miscellaneous.ender_eyes_never_break");
    public static final Text TITLE_FEATURE_FASTER_BLOCK_BREAKING = Text.translatable("speedrunnermod.title.features.miscellaneous.faster_block_breaking");
    public static final Text TITLE_FEATURE_FIREPROOF_ITEMS = Text.translatable("speedrunnermod.title.features.miscellaneous.fireproof_items");
    public static final Text TITLE_FEATURE_FOG_KEY = Text.translatable("speedrunnermod.title.features.miscellaneous.fog_key");
    public static final Text TITLE_FEATURE_FULLBRIGHT_KEY = Text.translatable("speedrunnermod.title.features.miscellaneous.fullbright_key");
    public static final Text TITLE_FEATURE_ICARUS_MODE = Text.translatable("speedrunnermod.title.features.miscellaneous.icarus_mode");
    public static final Text TITLE_FEATURE_INFINI_PEARL_MODE = Text.translatable("speedrunnermod.title.features.miscellaneous.infini_pearl_mode");
    public static final Text TITLE_FEATURE_LESS_FALL_DAMAGE = Text.translatable("speedrunnermod.title.features.miscellaneous.less_fall_damage");
    public static final Text TITLE_FEATURE_MORE_EXPERIENCE = Text.translatable("speedrunnermod.title.features.miscellaneous.more_experience");
    public static final Text TITLE_FEATURE_NO_MORE_PIGLIN_BRUTES = Text.translatable("speedrunnermod.title.features.miscellaneous.no_more_piglin_brutes");
    public static final Text TITLE_FEATURE_PIGLIN_PORK = Text.translatable("speedrunnermod.title.features.miscellaneous.piglin_pork");
    public static final Text TITLE_FEATURE_RESET_KEY = Text.translatable("speedrunnermod.title.features.miscellaneous.reset_key");
    public static final Text TITLE_FEATURE_RETIRED_SPEEDRUNNER = Text.translatable("speedrunnermod.title.features.miscellaneous.retired_speedrunner");
    public static final Text TITLE_FEATURE_REVERSE_CRAFTING = Text.translatable("speedrunnermod.title.features.miscellaneous.reverse_crafting");
    public static final Text TITLE_FEATURE_FASTER_SMELTING = Text.translatable("speedrunnermod.title.features.miscellaneous.faster_smelting");
    public static final Text TITLE_FEATURE_RIGHT_CLICK_TO_REMOVE_SILK_TOUCH = Text.translatable("speedrunnermod.title.features.miscellaneous.right_click_to_remove_silk_touch");
    public static final Text TITLE_FEATURE_SPEEDRUNNER_EDITION = Text.translatable("speedrunnermod.title.features.miscellaneous.speedrunner_edition");
    public static final Text TITLE_FEATURE_THROWABLE_FIREBALLS = Text.translatable("speedrunnermod.title.features.miscellaneous.throwable_fireballs");
    public static final Text TITLE_FEATURE_CRAFTABLE_TOTEMS = Text.translatable("speedrunnermod.title.features.miscellaneous.craftable_totems");
    public static final Text TITLE_FEATURE_CRAFTABLE_GOD_APPLES = Text.translatable("speedrunnermod.title.features.miscellaneous.craftable_god_apples");
    public static final Text TITLE_FEATURE_TRIPLED_DROPS = Text.translatable("speedrunnermod.title.features.miscellaneous.tripled_drops");
    public static final Text TITLE_FEATURE_WATER_IN_NETHER = Text.translatable("speedrunnermod.title.features.miscellaneous.water_in_nether");

    // Doom mode feature screens
    public static final Text TITLE_FEATURE_DOOM_MODE_BASICS = Text.translatable("speedrunnermod.title.features.doom_mode.basics");
    public static final Text TITLE_FEATURE_DOOM_MODE_BOSSES = Text.translatable("speedrunnermod.title.features.doom_mode.bosses");
    public static final Text TITLE_FEATURE_DOOM_MODE_DOOM_BLOCKS = Text.translatable("speedrunnermod.title.features.doom_mode.doom_blocks");
    public static final Text TITLE_FEATURE_DOOM_MODE_KNOCKBACK_STICK = Text.translatable("speedrunnermod.title.features.doom_mode.knockback_stick");
    public static final Text TITLE_FEATURE_DOOM_MODE_GOLIATH = Text.translatable("speedrunnermod.title.features.doom_mode.goliath");
    public static final Text TITLE_FEATURE_DOOM_MODE_OTHER_THINGS_TO_KNOW = Text.translatable("speedrunnermod.title.features.doom_mode.other_things_to_know");

    // Socials
    public static final Text CURSEFORGE = Text.translatable("speedrunnermod.menu.external.curseforge").formatted(Formatting.GOLD);
    public static final Text MODRINTH = Text.translatable("speedrunnermod.menu.external.modrinth").formatted(Formatting.GREEN);
    public static final Text GITHUB = Text.translatable("speedrunnermod.menu.external.github").formatted(Formatting.GRAY);
    public static final Text MOD_SHOWCASE_VIDEO = Text.translatable("speedrunnermod.menu.external.mod_showcase_video").formatted(Formatting.AQUA);
    public static final Text MENU_LEADERBOARDS = Text.translatable("speedrunnermod.menu.external.leaderboards").formatted(Formatting.GREEN);
    public static final Text MENU_LEADERBOARDS_DISABLED = Text.translatable("speedrunnermod.menu.leaderboards.disabled");
    public static final Text MENU_LEADERBOARDS_VIEW = Text.translatable("speedrunnermod.menu.leaderboards.view");
    public static final Text MENU_LEADERBOARDS_SPREADSHEET = Text.translatable("speedrunnermod.menu.leaderboards.spreadsheet");
    public static final Text TITLE_LEADERBOARDS = Text.translatable("speedrunnermod.title.leaderboards").formatted(Formatting.GREEN);
    public static final Text TITLE_INELIGIBLE_OPTIONS = Text.translatable("speedrunnermod.title.ineligible_options");

    // Option files
    public static final Text MENU_OPEN_OPTIONS_FILE = Text.translatable("speedrunnermod.menu.open_options_file");
    public static final Text OPEN_OPTIONS_FILE_TOOLTIP = Text.translatable("speedrunnermod.menu.open_options_file.tooltip");
    public static final Text OPEN_CLIENT_OPTIONS_FILE_TOOLTIP = Text.translatable("speedrunnermod.menu.open_client_options_file.tooltip");

    // Mods
    public static final Text SODIUM = Text.translatable("speedrunnermod.title.resources.mods.sodium").formatted(Formatting.GREEN);
    public static final Text SODIUM_TOOLTIP = Text.translatable("speedrunnermod.title.resources.mods.sodium.tooltip");
    public static final Text LITHIUM = Text.translatable("speedrunnermod.title.resources.mods.lithium").formatted(Formatting.AQUA);
    public static final Text LITHIUM_TOOLTIP = Text.translatable("speedrunnermod.title.resources.mods.lithium.tooltip");
    public static final Text SPEEDRUN_IGT = Text.translatable("speedrunnermod.title.resources.mods.speedrunigt").formatted(Formatting.GREEN);
    public static final Text SPEEDRUN_IGT_TOOLTIP = Text.translatable("speedrunnermod.title.resources.mods.speedrunigt.tooltip");
    public static final Text KRYPTON = Text.translatable("speedrunnermod.title.resources.mods.krypton").formatted(Formatting.GRAY);
    public static final Text SIMPLE_KEYBINDS = Text.translatable("speedrunnermod.title.resources.mods.simple_keybinds").formatted(Formatting.GREEN);
    public static final Text SIMPLE_KEYBINDS_TOOLTIP = Text.translatable("speedrunnermod.title.resources.mods.simple_keybinds.tooltip");
    public static final Text QUALITY_OF_QUESO = Text.translatable("speedrunnermod.title.resources.mods.qoq").formatted(Formatting.YELLOW);
    public static final Text QUALITY_OF_QUESO_TOOLTIP = Text.translatable("speedrunnermod.title.resources.mods.qoq.tooltip");
    public static final Text KRYPTON_TOOLTIP = Text.translatable("speedrunnermod.title.resources.mods.krypton.tooltip");
    public static final Text OPTIFINE = Text.translatable("speedrunnermod.title.resources.mods.optifine").formatted(Formatting.RED);
    public static final Text OPTIFINE_TOOLTIP = Text.translatable("speedrunnermod.title.resources.mods.optifine.tooltip");

    // Configuration buttons
    public static final Text RESET = Text.translatable("speedrunnermod.reset");
    public static final Text RESET_CONFIRM = Text.translatable("speedrunnermod.reset_confirm");
    public static final Text NOT_NOW = Text.translatable("speedrunnermod.not_now");
    public static final Text RESTART_NOW = Text.translatable("speedrunnermod.restart_now");
    public static final Text RESTART_LATER = Text.translatable("speedrunnermod.restart_later");
    public static final Text REVERT_CHANGES = Text.translatable("speedrunnermod.revert_changes");
    public static final Text FIX_AND_RESTART = Text.translatable("speedrunnermod.fix_and_restart");
    public static final Text DOWNLOAD_AND_INSTALL = Text.translatable("speedrunnermod.download_and_install");
    public static final Text CLOSE_GAME = Text.translatable("speedrunnermod.close_game");
    public static final Text PROCEED_ANYWAY = Text.translatable("speedrunnermod.proceed_anyway");
    public static final Text DISABLE_LEADERBOARDS_MODE_AND_RESTART = Text.translatable("speedrunnermod.disable_leaderboards_mode_and_restart");
    public static final Text IGNORE = Text.translatable("speedrunnermod.ignore").formatted(Formatting.RED);
    public static final Text VIEW_INELIGIBLE_OPTIONS = Text.translatable("speedrunnermod.view_ineligible_options");
    public static final Text VISIT_SUBMISSION_PAGE = Text.translatable("speedrunnermod.visit_submission_page");
    public static final Text ENTER_TUTORIAL_MODE = Text.translatable("speedrunnermod.enter_tutorial_mode");
    public static final Text BEGIN_PLAYING = Text.translatable("speedrunnermod.begin_playing");
    public static final Text BEGIN_PLAYING_TOOLTIP = Text.translatable("speedrunnermod.begin_playing.tooltip");

    // Modes
    public static final Text EASY_MODE = Text.translatable("speedrunnermod.options.mode.easy");
    public static final Text EASY_MODE_TOOLTIP = Text.translatable("speedrunnermod.mode.easy.tooltip");
    public static final Text BALANCED_MODE = Text.translatable("speedrunnermod.options.mode.balanced");
    public static final Text BALANCED_MODE_TOOLTIP = Text.translatable("speedrunnermod.mode.balanced.tooltip");
    public static final Text DOOM_MODE = Text.translatable("speedrunnermod.options.mode.doom");
    public static final Text DOOM_MODE_TOOLTIP = Text.translatable("speedrunnermod.mode.doom.tooltip");

    // State-of-the-art item tooltips/messages
    public static final Text CALCULATING = Text.translatable("item.speedrunnermod.eye.calculating").formatted(Formatting.RED);

    // Match client-settings to server
    public static final Text TITLE_MODE_DOESNT_MATCH_SERVER_SETTING = Text.translatable("speedrunnermod.title.mode_doesnt_match_server_setting");
    public static final Text MODE_DOESNT_MATCH_SERVER_SETTING = Text.translatable("speedrunnermod.mode.doesnt_match_server");
    public static final Text MATCH_MODE_TO_SERVER = Text.translatable("speedrunnermod.match_mode_to_server");
    public static final Text TITLE_MATCH_SETTINGS_WITH_SERVER = Text.translatable("speedrunnermod.title.match_settings_with_server");
    public static final Text MATCH_AND_RESTART = Text.translatable("speedrunnermod.match_and_restart");
    public static final Text MATCH_AND_RESTART_TOOLTIP = Text.translatable("speedrunnermod.match_and_restart.tooltip");
    public static final Text MATCHED_SETTINGS_WITH_SERVER = Text.translatable("speedrunnermod.matched_settings_with_server");
    public static final Text ABORT = Text.translatable("speedrunnermod.abort");
    public static final Text MATCH_SETTINGS_WITH_SERVER_LINE1 = Text.translatable("speedrunnermod.match_settings_with_server.line1");
    public static final Text MATCH_SETTINGS_WITH_SERVER_LINE2 = Text.translatable("speedrunnermod.match_settings_with_server.line2");
    public static final Text MATCH_SETTINGS_WITH_SERVER_SYNC_FAILED = Text.translatable("speedrunnermod.match_settings_with_server_sync_failed");
    public static final Text MATCH_SETTINGS_WITH_SERVER_SYNC_FAILED_LINE2 = Text.translatable("speedrunnermod.match_settings_with_server_sync_failed.line2");
    public static final Text MATCH_SETTINGS_WITH_SERVER_FAILED = MATCH_SETTINGS_WITH_SERVER_SYNC_FAILED.copy().append(" ").append(MATCH_SETTINGS_WITH_SERVER_SYNC_FAILED_LINE2);

    // Title screen
    public static final Text FEATURES_TOOLTIP = Text.translatable("speedrunnermod.features.tooltip");
    public static final Text CREATE_WORLD_BUTTON_TOOLTIP = Text.translatable("speedrunnermod.create_world_button.desc");
    public static final Text CREATE_WORLD_BUTTON_DISABLED_TOOLTIP = Text.translatable("speedrunnermod.create_world_button.disabled");
    public static final Text OPTIONS_TOOLTIP = Text.translatable("speedrunnermod.title.options.tooltip");

    // Miscellaneous
    public static final Text QUESTIONS_AND_ISSUES = Text.translatable("speedrunnermod.questions_and_issues").formatted(Formatting.BLUE);
    public static final Text QUESTIONS_AND_ISSUES_TOOLTIP = Text.translatable("speedrunnermod.questions_and_issues.tooltip");
    public static final Text SUGGESTIONS_AND_FEEDBACK = Text.translatable("speedrunnermod.suggestions_and_feedback").formatted(Formatting.GOLD);
    public static final Text SUGGESTIONS_AND_FEEDBACK_TOOLTIP = Text.translatable("speedrunnermod.suggestions_and_feedback.tooltip");

    /**
     * @return the text for disabled items.
     */
    public static Text stateOfTheArtItemDisabledTooltip(ModOptions.Mode mode) {
        Text modeText;
        switch (mode) {
            case DOOM -> modeText = Text.literal("doom").formatted(Formatting.RED);
            case BALANCED -> modeText = Text.literal("balanced").formatted(Formatting.YELLOW);
            default -> modeText = Text.literal("easy").formatted(Formatting.AQUA);
        }
        return Text.translatable("item.speedrunnermod.state_of_the_art_item.disabled", modeText).formatted(Formatting.RED).formatted(Formatting.BOLD).formatted(Formatting.ITALIC);
    }
}