package net.dillon.speedrunnermod.util;

import net.dillon.speedrunnermod.option.Mode;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

/**
 * Stores all {@code translation keys} for the Speedrunner Mod.
 */
public class ModTexts {
    // Main
    public static final Component BLANK = Component.literal("");
    public static final Component OK = Component.translatable("speedrunnermod.ok");
    public static final Component SAVE = Component.translatable("speedrunnermod.save");
    public static final Component SAVE_TOOLTIP = Component.translatable("speedrunnermod.save.tooltip");
    public static final Component NEXT = Component.translatable("speedrunnermod.next");
    public static final Component NEXT_ARROW = Component.literal(">").withStyle(ChatFormatting.BOLD);
    public static final Component NEXT_ARROW_TOOLTIP = Component.translatable("speedrunnermod.next.tooltip");
    public static final Component PREVIOUS = Component.literal("<").withStyle(ChatFormatting.BOLD);
    public static final Component PREVIOUS_TOOLTIP = Component.translatable("speedrunnermod.previous.tooltip");
    public static final Component BACK = Component.translatable("speedrunnermod.back");
    public static final Component HELP_TOOLTIP = Component.translatable("speedrunnermod.help_button.tooltip");
    public static final Component ON = Component.literal("ON").withStyle(ChatFormatting.GREEN);
    public static final Component OFF = Component.literal("OFF").withStyle(ChatFormatting.RED);
    public static final Component YES = Component.literal("YES").withStyle(ChatFormatting.GREEN);
    public static final Component NO = Component.literal("NO").withStyle(ChatFormatting.RED);
    public static final Component LETS_GO = Component.translatable("speedrunnermod.lets_go");
    public static final Component REFRESHING = Component.literal("Refreshing...");
    public static final Component ENABLED = Component.literal("Enabled").withStyle(ChatFormatting.GREEN);
    public static final Component DISABLED = Component.literal("DISABLED").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.RED);
    public static final Component FEATURE_DISABLED = Component.literal("Feature Disabled");

    // Titles and menus
    public static final Component TITLE = Component.translatable("speedrunnermod.title");
    public static final Component MENU_OPTIONS_ACTION_NEEDED = Component.translatable("speedrunnermod.leaderboards.action_needed");
    public static final Component MENU_OPTIONS_SAFE = Component.translatable("speedrunnermod.leaderboards.safe");
    public static final Component TITLE_OPTIONS_GENERAL = Component.translatable("speedrunnermod.title.options.general");
    public static final Component TITLE_OPTIONS_WORLDGEN = Component.translatable("speedrunnermod.title.options.worldgen");
    public static final Component TITLE_OPTIONS_CLIENT = Component.translatable("speedrunnermod.title.options.client");
    public static final Component MENU_OPTIONS_RESET = Component.translatable("speedrunnermod.menu.options.reset");
    public static final Component TITLE_OPTIONS_RESET = Component.translatable("speedrunnermod.title.options.reset");
    public static final Component MENU_OPTIONS_TOOLTIP = Component.translatable("speedrunnermod.menu.options.tooltip");
    public static final Component MENU_OPTIONS_MAIN = Component.translatable("speedrunnermod.menu.options.general");
    public static final Component MENU_OPTIONS_MAIN_TOOLTIP = Component.translatable("speedrunnermod.menu.options.general.tooltip");
    public static final Component MENU_OPTIONS_WORLDGEN = Component.translatable("speedrunnermod.menu.options.worldgen");
    public static final Component MENU_OPTIONS_WORLDGEN_TOOLTIP = Component.translatable("speedrunnermod.menu.options.worldgen.tooltip");
    public static final Component MENU_FAST_WORLD_CREATION = Component.translatable("speedrunnermod.menu.options.fast_world_creation");
    public static final Component MENU_FAST_WORLD_CREATION_TOOLTIP = Component.translatable("speedrunnermod.menu.options.fast_world_creation.tooltip");
    public static final Component TITLE_FAST_WORLD_CREATION = Component.translatable("speedrunnermod.title.options.fast_world_creation");
    public static final Component MENU_OPTIONS_CLIENT = Component.translatable("speedrunnermod.menu.options.client");
    public static final Component MENU_OPTIONS_CLIENT_TOOLTIP = Component.translatable("speedrunnermod.menu.options.client.tooltip");
    public static final Component MENU_OPTIONS_RESET_TOOLTIP = Component.translatable("speedrunnermod.menu.options.reset.tooltip");
    public static final Component MENU_STRUCTURE_SPAWN_RATE_OPTIONS = Component.translatable("speedrunnermod.menu.options.structure_spawn_rates");
    public static final Component MENU_STRUCTURE_SPAWN_RATE_OPTIONS_TOOLTIP = Component.translatable("speedrunnermod.menu.options.structure_spawn_rates.tooltip");
    public static final Component MENU_STRUCTURE_SPAWN_RATE_OPTIONS_NEEDS_CUSTOM_TOOLTIP = Component.translatable("speedrunnermod.menu.options.structure_spawn_rates_needs_custom.tooltip");
    public static final Component TITLE_STRUCTURE_SPAWN_RATE_OPTIONS = Component.translatable("speedrunnermod.title.options.structure_spawn_rates");
    public static final Component MENU_MIXIN_OPTIONS = Component.translatable("speedrunnermod.menu.options.mixins");
    public static final Component MENU_MIXIN_OPTIONS_TOOLTIP = Component.translatable("speedrunnermod.menu.options.mixins.tooltip");
    public static final Component TITLE_MIXIN_OPTIONS = Component.translatable("speedrunnermod.title.options.mixins");
    public static final Component MENU_ADVANCED_OPTIONS = Component.translatable("speedrunnermod.menu.options.advanced");
    public static final Component MENU_ADVANCED_OPTIONS_TOOLTIP = Component.translatable("speedrunnermod.menu.options.advanced.tooltip");
    public static final Component TITLE_ADVANCED_OPTIONS = Component.translatable("speedrunnermod.title.options.advanced");
    public static final Component MENU_CREDITS = Component.translatable("speedrunnermod.menu.credits");
    public static final Component MENU_CREDITS_TOOLIP = Component.translatable("speedrunnermod.menu.credits.tooltip");
    public static final Component MENU_LINKS = Component.translatable("speedrunnermod.menu.links").withStyle(ChatFormatting.GOLD);
    public static final Component TITLE_LINKS = Component.translatable("speedrunnermod.links");
    public static final Component MENU_FEATURES_TOOLTIP = Component.translatable("speedrunnermod.menu.features.tooltip");
    public static final Component MENU_FEATURES = Component.translatable("speedrunnermod.menu.features").withStyle(ChatFormatting.AQUA);
    public static final Component TITLE_FEATURES = Component.translatable("speedrunnermod.title.features");
    public static final Component MENU_RESOURCES = Component.translatable("speedrunnermod.menu.resources");
    public static final Component MENU_RESOURCES_TOOLTIP = Component.translatable("speedrunnermod.menu.resources.tooltip");
    public static final Component TITLE_RESOURCES = Component.translatable("speedrunnermod.title.resources");
    public static final Component MENU_MODS = Component.translatable("speedrunnermod.menu.resources.mods").withStyle(ChatFormatting.AQUA);
    public static final Component TITLE_MODS = Component.translatable("speedrunnermod.title.resources.mods");
    public static final Component MENU_TUTORIALS = Component.translatable("speedrunnermod.menu.resources.tutorials").withStyle(ChatFormatting.GREEN);
    public static final Component TITLE_TUTORIALS = Component.translatable("speedrunnermod.title.resources.tutorials");
    public static final Component TITLE_RESTART_REQUIRED = Component.translatable("speedrunnermod.title.restart_required");
    public static final Component TITLE_SAFE_BOOT = Component.translatable("speedrunnermod.title.safe_mode");
    public static final Component TITLE_SPEEDRUN_IGT_MISSING = Component.translatable("speedrunnermod.title.speedrun_igt_missing");
    public static final Component ENABLE_DOOM_MODE = Component.translatable("speedrunnermod.doom_mode.enable").withStyle(ChatFormatting.RED);
    public static final Component DOOM_MODE_ALREADY_ENABLED = Component.translatable("speedrunnermod.doom_mode.already_enabled").withStyle(ChatFormatting.RED);
    public static final Component MENU_DOOM_MODE = Component.translatable("speedrunnermod.menu.doom_mode");

    // Feature screens enable/disable features
    public static final Component DISABLE_LAVA_BOATS = Component.translatable("speedrunnermod.options.lava_boats.disable").withStyle(ChatFormatting.GOLD);
    public static final Component ENABLE_LAVA_BOATS = Component.translatable("speedrunnermod.options.lava_boats.enable").withStyle(ChatFormatting.GREEN);
    public static final Component STOP_SPEEDRUNNERS_WASTELAND_BIOME_FROM_GENERATING = Component.translatable("speedrunnermod.options.stop_speedrunners_wasteland_biome_from_generating").withStyle(ChatFormatting.RED);
    public static final Component ALLOW_SPEEDRUNNERS_WASTELAND_BIOME_TO_GENERATE = Component.translatable("speedrunnermod.options.allow_speedrunners_wasteland_biome_to_generate").withStyle(ChatFormatting.AQUA);
    public static final Component DISABLE_THIS_FEATURE = Component.translatable("speedrunnermod.disable_this_feature").withStyle(ChatFormatting.RED);
    public static final Component ENABLE_THIS_FEATURE = Component.translatable("speedrunnermod.enable_this_feature").withStyle(ChatFormatting.GREEN);
    public static final Component DISABLE_ICARUS_MODE = Component.translatable("speedrunnermod.disable_icarus_mode").withStyle(ChatFormatting.GRAY);
    public static final Component ENABLE_ICARUS_MODE = Component.translatable("speedrunnermod.enable_icarus_mode").withStyle(ChatFormatting.GRAY);
    public static final Component DISABLE_INFINI_PEARL_MODE = Component.translatable("speedrunnermod.disable_infini_pearl_mode").withStyle(ChatFormatting.BLUE);
    public static final Component ENABLE_INFINI_PEARL_MODE = Component.translatable("speedrunnermod.enable_infini_pearl_mode").withStyle(ChatFormatting.BLUE);
    public static final Component CONFIGURE_OPTION = Component.translatable("speedrunnermod.configure_option");

    // Feature screen categories
    public static final Component MENU_BLOCKS_AND_ITEMS = Component.translatable("speedrunnermod.menu.features.blocks_and_items");
    public static final Component TITLE_BLOCKS_AND_ITEMS = Component.translatable("speedrunnermod.title.features.blocks_and_items");
    public static final Component MENU_TOOLS_AND_ARMOR = Component.translatable("speedrunnermod.menu.features.tools_and_armor");
    public static final Component TITLE_TOOLS_AND_ARMOR = Component.translatable("speedrunnermod.title.features.tools_and_armor");
    public static final Component MENU_ORES_AND_WORLDGEN = Component.translatable("speedrunnermod.menu.features.ores_and_worldgen");
    public static final Component TITLE_ORES_AND_WORLDGEN = Component.translatable("speedrunnermod.title.features.ores_and_worldgen");
    public static final Component MENU_MISCELLANEOUS = Component.translatable("speedrunnermod.menu.features.miscellaneous");
    public static final Component TITLE_MISCELLANEOUS = Component.translatable("speedrunnermod.title.features.miscellaneous");
    public static final Component MENU_FEATURE_DOOM_MODE = Component.translatable("speedrunnermod.menu.features.doom_mode");
    public static final Component TITLE_FEATURE_DOOM_MODE = Component.translatable("speedrunnermod.title.features.doom_mode");

    // Blocks and items feature screens
    public static final Component TITLE_FEATURE_BLAZE_SPOTTER = Component.translatable("speedrunnermod.title.features.blocks_and_items.blaze_spotter");
    public static final Component TITLE_FEATURE_DEAD_SPEEDRUNNER_WOOD = Component.translatable("speedrunnermod.title.features.blocks_and_items.dead_speedrunner_wood");
    public static final Component TITLE_FEATURE_DRAGONS_PEARL = Component.translatable("speedrunnermod.title.features.blocks_and_items.dragons_pearl");
    public static final Component TITLE_FEATURE_DRAGONS_AURA = Component.translatable("speedrunnermod.title.features.blocks_and_items.dragons_aura");
    public static final Component TITLE_FEATURE_INVENTORY_PRESERVER = Component.translatable("speedrunnermod.title.features.blocks_and_items.inventory_preserver");
    public static final Component TITLE_FEATURE_DRAGONS_FIREBALL = Component.translatable("speedrunnermod.title.features.blocks_and_items.dragons_fireball");
    public static final Component TITLE_FEATURE_ENDER_MATTER = Component.translatable("speedrunnermod.title.features.blocks_and_items.ender_matter");
    public static final Component TITLE_FEATURE_ENDER_THRUSTER = Component.translatable("speedrunnermod.title.features.blocks_and_items.ender_thruster");
    public static final Component TITLE_FEATURE_EYE_OF_ANNUL = Component.translatable("speedrunnermod.title.features.blocks_and_items.eye_of_annul");
    public static final Component TITLE_FEATURE_EYE_OF_INFERNO = Component.translatable("speedrunnermod.title.features.blocks_and_items.eye_of_inferno");
    public static final Component TITLE_FEATURE_IGNEOUS_ROCKS = Component.translatable("speedrunnermod.title.features.blocks_and_items.igneous_rocks");
    public static final Component TITLE_FEATURE_INFINI_PEARL = Component.translatable("speedrunnermod.title.features.blocks_and_items.infini_pearl");
    public static final Component TITLE_FEATURE_FIREPROOF_BOATS = Component.translatable("speedrunnermod.title.features.blocks_and_items.fireproof_boats");
    public static final Component TITLE_FEATURE_PIGLIN_AWAKENER = Component.translatable("speedrunnermod.title.features.blocks_and_items.piglin_awakener");
    public static final Component TITLE_FEATURE_RAID_ERADICATOR = Component.translatable("speedrunnermod.title.features.blocks_and_items.raid_eradicator");
    public static final Component TITLE_FEATURE_SPEEDRUNNER_BLOCKS = Component.translatable("speedrunnermod.title.features.blocks_and_items.speedrunner_blocks");
    public static final Component TITLE_FEATURE_SPEEDRUNNER_BULK = Component.translatable("speedrunnermod.title.features.blocks_and_items.speedrunner_bulk");
    public static final Component TITLE_FEATURE_SPEEDRUNNER_INGOTS = Component.translatable("speedrunnermod.title.features.blocks_and_items.speedrunner_ingots");
    public static final Component TITLE_FEATURE_SPEEDRUNNER_NUGGETS = Component.translatable("speedrunnermod.title.features.blocks_and_items.speedrunner_nuggets");
    public static final Component TITLE_FEATURE_SPEEDRUNNER_PADDLE = Component.translatable("speedrunnermod.title.features.blocks_and_items.speedrunner_paddle");
    public static final Component TITLE_FEATURE_SPEEDRUNNERS_EYE = Component.translatable("speedrunnermod.title.features.blocks_and_items.speedrunners_eye");
    public static final Component TITLE_FEATURE_SPEEDRUNNERS_TOTEM = Component.translatable("speedrunnermod.title.features.blocks_and_items.speedrunners_totem");
    public static final Component TITLE_FEATURE_SPEEDRUNNERS_WORKBENCH = Component.translatable("speedrunnermod.title.features.blocks_and_items.speedrunners_workbench");
    public static final Component TITLE_FEATURE_SPEEDRUNNER_WOOD = Component.translatable("speedrunnermod.title.features.blocks_and_items.speedrunner_wood");
    public static final Component TITLE_FEATURE_SPEEDRUNNER_BOW_AND_CROSSBOW = Component.translatable("speedrunnermod.title.features.tools_and_armor.speedrunner_bow_and_crossbow");
    public static final Component TITLE_FEATURE_SPEEDRUNNER_FLINT_AND_STEEL = Component.translatable("speedrunnermod.title.features.tools_and_armor.speedrunner_flint_and_steel");
    public static final Component TITLE_FEATURE_SPEEDRUNNER_SHEARS = Component.translatable("speedrunnermod.title.features.tools_and_armor.speedrunner_shears");
    public static final Component TITLE_FEATURE_SPEEDRUNNER_SHIELD = Component.translatable("speedrunnermod.title.features.tools_and_armor.speedrunner_shield");
    public static final Component TITLE_FEATURE_GOLDEN_SHIELD = Component.translatable("speedrunnermod.title.features.tools_and_armor.golden_shield");
    public static final Component TITLE_FEATURE_RAW_SPEEDRUNNER = Component.translatable("speedrunnermod.title.features.blocks_and_items.raw_speedrunner");

    // Tools and armor feature screens
    public static final Component TITLE_FEATURE_COOLDOWN_ENCHANTMENT = Component.translatable("speedrunnermod.title.features.tools_and_armor.cooldown_enchantment");
    public static final Component TITLE_FEATURE_DASH_ENCHANTMENT = Component.translatable("speedrunnermod.title.features.tools_and_armor.dash_enchantment");
    public static final Component TITLE_FEATURE_WITHERED_ENCHANTMENT = Component.translatable("speedrunnermod.title.features.tools_and_armor.withered_enchantment");
    public static final Component TITLE_FEATURE_DRAGONS_SWORD = Component.translatable("speedrunnermod.title.features.tools_and_armor.dragons_sword");
    public static final Component TITLE_FEATURE_SPEEDRUNNER_SPEAR = Component.translatable("speedrunnermod.title.features.tools_and_armor.speedrunner_spear");
    public static final Component TITLE_FEATURE_GOLDEN_SPEEDRUNNER_ARMOR = Component.translatable("speedrunnermod.title.features.tools_and_armor.golden_speedrunner_armor");
    public static final Component TITLE_FEATURE_GOLDEN_SMITHING_TEMPLATE = Component.translatable("speedrunnermod.title.features.tools_and_armor.golden_smithing_template");
    public static final Component TITLE_FEATURE_SPEEDRUNNER_ARMOR = Component.translatable("speedrunnermod.title.features.tools_and_armor.speedrunner_armor");
    public static final Component TITLE_FEATURE_SPEEDRUNNER_SAFE_BOOTS = Component.translatable("speedrunnermod.title.features.tools_and_armor.speedrunner_safe_boots");
    public static final Component TITLE_FEATURE_SPEEDRUNNER_NAUTILUS_ARMOR = Component.translatable("speedrunnermod.title.features.tools_and_armor.speedrunner_nautilus_armor");
    public static final Component TITLE_FEATURE_SPEEDRUNNER_HARNESS = Component.translatable("speedrunnermod.title.features.tools_and_armor.speedrunner_harness");

    // Ores and worldgen feature screens
    public static final Component TITLE_FEATURE_COMMON_ORES = Component.translatable("speedrunnermod.title.features.ores_and_worldgen.common_ores");
    public static final Component TITLE_FEATURE_EXPERIENCE_ORES = Component.translatable("speedrunnermod.title.features.ores_and_worldgen.experience_ores");
    public static final Component TITLE_FEATURE_EXPERIENCE_FRAGMENT = Component.translatable("speedrunnermod.title.features.ores_and_worldgen.experience_fragment");
    public static final Component TITLE_FEATURE_FORTRESSES_BASTIONS_AND_STRONGHOLDS = Component.translatable("speedrunnermod.title.features.ores_and_worldgen.fortresses_bastions_and_strongholds");
    public static final Component TITLE_FEATURE_IGNEOUS_ORES = Component.translatable("speedrunnermod.title.features.ores_and_worldgen.igneous_ores");
    public static final Component TITLE_FEATURE_SPEEDRUNNER_ORES = Component.translatable("speedrunnermod.title.features.ores_and_worldgen.speedrunner_ores");
    public static final Component TITLE_FEATURE_SPEEDRUNNERS_WASTELAND = Component.translatable("speedrunnermod.title.features.ores_and_worldgen.speedrunners_wasteland_biome");
    public static final Component TITLE_FEATURE_BETTER_BIOMES = Component.translatable("speedrunnermod.title.features.ores_and_worldgen.better_biomes");
    public static final Component TITLE_FEATURE_STRUCTURES = Component.translatable("speedrunnermod.title.features.ores_and_worldgen.structures");

    // Miscellaneous feature screens
    public static final Component TITLE_FEATURE_AND_MORE = Component.translatable("speedrunnermod.title.features.miscellaneous.and_more");
    public static final Component TITLE_FEATURE_ARROWS_EXPLODE_BEDS = Component.translatable("speedrunnermod.title.features.miscellaneous.arrows_explode_beds");
    public static final Component TITLE_FEATURE_BETTER_ANVILS = Component.translatable("speedrunnermod.title.features.miscellaneous.better_anvils");
    public static final Component TITLE_FEATURE_BETTER_DEATH_SCREEN = Component.translatable("speedrunnermod.title.features.miscellaneous.better_death_screen");
    public static final Component TITLE_FEATURE_BETTER_FOODS = Component.translatable("speedrunnermod.title.features.miscellaneous.better_foods");
    public static final Component TITLE_FEATURE_BETTER_HOTKEYS = Component.translatable("speedrunnermod.title.features.miscellaneous.better_hotkeys");
    public static final Component TITLE_FEATURE_BETTER_LOOT_TABLES = Component.translatable("speedrunnermod.title.features.miscellaneous.better_loot_tables");
    public static final Component TITLE_FEATURE_BETTER_NETHER_PORTALS = Component.translatable("speedrunnermod.title.features.miscellaneous.better_nether_portals");
    public static final Component TITLE_FEATURE_BETTER_PIGLIN_BARTERING = Component.translatable("speedrunnermod.title.features.miscellaneous.piglin_bartering");
    public static final Component TITLE_FEATURE_BETTER_VILLAGER_TRADES = Component.translatable("speedrunnermod.title.features.miscellaneous.better_villager_trades");
    public static final Component TITLE_FEATURE_BLAZES_DROP_GOLD = Component.translatable("speedrunnermod.title.features.miscellaneous.blazes_drop_gold");
    public static final Component TITLE_FEATURE_BLAZE_SPAWNERS_IN_BASTIONS = Component.translatable("speedrunnermod.title.features.miscellaneous.blaze_spawners_in_bastions");
    public static final Component TITLE_FEATURE_COOKED_FLESH = Component.translatable("speedrunnermod.title.features.miscellaneous.cooked_flesh");
    public static final Component TITLE_FEATURE_CUSTOM_PANORAMA = Component.translatable("speedrunnermod.title.features.miscellaneous.custom_panorama");
    public static final Component TITLE_FEATURE_ENDER_EYES_NEVER_BREAK = Component.translatable("speedrunnermod.title.features.miscellaneous.ender_eyes_never_break");
    public static final Component TITLE_FEATURE_FASTER_BLOCK_BREAKING = Component.translatable("speedrunnermod.title.features.miscellaneous.faster_block_breaking");
    public static final Component TITLE_FEATURE_FIREPROOF_ITEMS = Component.translatable("speedrunnermod.title.features.miscellaneous.fireproof_items");
    public static final Component TITLE_FEATURE_FOG_KEY = Component.translatable("speedrunnermod.title.features.miscellaneous.fog_key");
    public static final Component TITLE_FEATURE_FULLBRIGHT_KEY = Component.translatable("speedrunnermod.title.features.miscellaneous.fullbright_key");
    public static final Component TITLE_FEATURE_ICARUS_MODE = Component.translatable("speedrunnermod.title.features.miscellaneous.icarus_mode");
    public static final Component TITLE_FEATURE_INFINI_PEARL_MODE = Component.translatable("speedrunnermod.title.features.miscellaneous.infini_pearl_mode");
    public static final Component TITLE_FEATURE_LESS_FALL_DAMAGE = Component.translatable("speedrunnermod.title.features.miscellaneous.less_fall_damage");
    public static final Component TITLE_FEATURE_MORE_EXPERIENCE = Component.translatable("speedrunnermod.title.features.miscellaneous.more_experience");
    public static final Component TITLE_FEATURE_NO_MORE_PIGLIN_BRUTES = Component.translatable("speedrunnermod.title.features.miscellaneous.no_more_piglin_brutes");
    public static final Component TITLE_FEATURE_PIGLIN_PORK = Component.translatable("speedrunnermod.title.features.miscellaneous.piglin_pork");
    public static final Component TITLE_FEATURE_RESET_KEY = Component.translatable("speedrunnermod.title.features.miscellaneous.reset_key");
    public static final Component TITLE_FEATURE_RETIRED_SPEEDRUNNER = Component.translatable("speedrunnermod.title.features.miscellaneous.retired_speedrunner");
    public static final Component TITLE_FEATURE_REVERSE_CRAFTING = Component.translatable("speedrunnermod.title.features.miscellaneous.reverse_crafting");
    public static final Component TITLE_FEATURE_FASTER_SMELTING = Component.translatable("speedrunnermod.title.features.miscellaneous.faster_smelting");
    public static final Component TITLE_FEATURE_RIGHT_CLICK_TO_REMOVE_SILK_TOUCH = Component.translatable("speedrunnermod.title.features.miscellaneous.right_click_to_remove_silk_touch");
    public static final Component TITLE_FEATURE_SPEEDRUNNER_EDITION = Component.translatable("speedrunnermod.title.features.miscellaneous.speedrunner_edition");
    public static final Component TITLE_FEATURE_THROWABLE_FIREBALLS = Component.translatable("speedrunnermod.title.features.miscellaneous.throwable_fireballs");
    public static final Component TITLE_FEATURE_CRAFTABLE_TOTEMS = Component.translatable("speedrunnermod.title.features.miscellaneous.craftable_totems");
    public static final Component TITLE_FEATURE_CRAFTABLE_GOD_APPLES = Component.translatable("speedrunnermod.title.features.miscellaneous.craftable_god_apples");
    public static final Component TITLE_FEATURE_TRIPLED_DROPS = Component.translatable("speedrunnermod.title.features.miscellaneous.tripled_drops");
    public static final Component TITLE_FEATURE_WATER_IN_NETHER = Component.translatable("speedrunnermod.title.features.miscellaneous.water_in_nether");

    // Doom mode feature screens
    public static final Component TITLE_FEATURE_DOOM_MODE_BASICS = Component.translatable("speedrunnermod.title.features.doom_mode.basics");
    public static final Component TITLE_FEATURE_DOOM_MODE_BOSSES = Component.translatable("speedrunnermod.title.features.doom_mode.bosses");
    public static final Component TITLE_FEATURE_DOOM_MODE_DOOM_BLOCKS = Component.translatable("speedrunnermod.title.features.doom_mode.doom_blocks");
    public static final Component TITLE_FEATURE_DOOM_MODE_KNOCKBACK_STICK = Component.translatable("speedrunnermod.title.features.doom_mode.knockback_stick");
    public static final Component TITLE_FEATURE_DOOM_MODE_GOLIATH = Component.translatable("speedrunnermod.title.features.doom_mode.goliath");
    public static final Component TITLE_FEATURE_DOOM_MODE_OTHER_THINGS_TO_KNOW = Component.translatable("speedrunnermod.title.features.doom_mode.other_things_to_know");

    // Socials
    public static final Component CURSEFORGE = Component.translatable("speedrunnermod.menu.links.curseforge").withStyle(ChatFormatting.GOLD);
    public static final Component MODRINTH = Component.translatable("speedrunnermod.menu.links.modrinth").withStyle(ChatFormatting.GREEN);
    public static final Component GITHUB = Component.translatable("speedrunnermod.menu.links.github").withStyle(ChatFormatting.GRAY);
    public static final Component MOD_SHOWCASE_VIDEO = Component.translatable("speedrunnermod.menu.links.mod_showcase_video").withStyle(ChatFormatting.LIGHT_PURPLE);
    public static final Component MOD_RELEASE_TRAILER = Component.translatable("speedrunnermod.menu.links.mod_release_trailer").withStyle(ChatFormatting.AQUA);
    public static final Component MENU_LEADERBOARDS = Component.translatable("speedrunnermod.menu.links.leaderboards").withStyle(ChatFormatting.GREEN);
    public static final Component MENU_LEADERBOARDS_DISABLED = Component.translatable("speedrunnermod.menu.leaderboards.disabled");
    public static final Component MENU_LEADERBOARDS_VIEW = Component.translatable("speedrunnermod.menu.leaderboards.view");
    public static final Component MENU_LEADERBOARDS_SPREADSHEET = Component.translatable("speedrunnermod.menu.leaderboards.spreadsheet");
    public static final Component TITLE_LEADERBOARDS = Component.translatable("speedrunnermod.title.leaderboards").withStyle(ChatFormatting.GREEN);
    public static final Component TITLE_INELIGIBLE_OPTIONS = Component.translatable("speedrunnermod.title.ineligible_options");

    // Option files
    public static final Component MENU_OPEN_OPTIONS_FILE = Component.translatable("speedrunnermod.menu.open_options_file");
    public static final Component OPEN_OPTIONS_FILE_TOOLTIP = Component.translatable("speedrunnermod.menu.open_options_file.tooltip");
    public static final Component OPEN_CLIENT_OPTIONS_FILE_TOOLTIP = Component.translatable("speedrunnermod.menu.open_client_options_file.tooltip");

    // Mods
    public static final Component SODIUM = Component.translatable("speedrunnermod.title.resources.mods.sodium").withStyle(ChatFormatting.GREEN);
    public static final Component SODIUM_TOOLTIP = Component.translatable("speedrunnermod.title.resources.mods.sodium.tooltip");
    public static final Component LITHIUM = Component.translatable("speedrunnermod.title.resources.mods.lithium").withStyle(ChatFormatting.AQUA);
    public static final Component LITHIUM_TOOLTIP = Component.translatable("speedrunnermod.title.resources.mods.lithium.tooltip");
    public static final Component SPEEDRUN_IGT = Component.translatable("speedrunnermod.title.resources.mods.speedrunigt").withStyle(ChatFormatting.GREEN);
    public static final Component SPEEDRUN_IGT_TOOLTIP = Component.translatable("speedrunnermod.title.resources.mods.speedrunigt.tooltip");
    public static final Component KRYPTON = Component.translatable("speedrunnermod.title.resources.mods.krypton").withStyle(ChatFormatting.GRAY);
    public static final Component SIMPLE_KEYBINDS = Component.translatable("speedrunnermod.title.resources.mods.simple_keybinds").withStyle(ChatFormatting.GREEN);
    public static final Component SIMPLE_KEYBINDS_TOOLTIP = Component.translatable("speedrunnermod.title.resources.mods.simple_keybinds.tooltip");
    public static final Component QUALITY_OF_QUESO = Component.translatable("speedrunnermod.title.resources.mods.qoq").withStyle(ChatFormatting.YELLOW);
    public static final Component QUALITY_OF_QUESO_TOOLTIP = Component.translatable("speedrunnermod.title.resources.mods.qoq.tooltip");
    public static final Component KRYPTON_TOOLTIP = Component.translatable("speedrunnermod.title.resources.mods.krypton.tooltip");
    public static final Component OPTIFINE = Component.translatable("speedrunnermod.title.resources.mods.optifine").withStyle(ChatFormatting.RED);
    public static final Component OPTIFINE_TOOLTIP = Component.translatable("speedrunnermod.title.resources.mods.optifine.tooltip");

    // Configuration buttons
    public static final Component RESET = Component.translatable("speedrunnermod.reset");
    public static final Component RESET_CONFIRM = Component.translatable("speedrunnermod.reset_confirm");
    public static final Component NOT_NOW = Component.translatable("speedrunnermod.not_now");
    public static final Component RESTART_NOW = Component.translatable("speedrunnermod.restart_now");
    public static final Component RESTART_LATER = Component.translatable("speedrunnermod.restart_later");
    public static final Component REVERT_CHANGES = Component.translatable("speedrunnermod.revert_changes");
    public static final Component FIX_AND_RESTART = Component.translatable("speedrunnermod.fix_and_restart");
    public static final Component DOWNLOAD_AND_INSTALL = Component.translatable("speedrunnermod.download_and_install");
    public static final Component CLOSE_GAME = Component.translatable("speedrunnermod.close_game");
    public static final Component PROCEED_ANYWAY = Component.translatable("speedrunnermod.proceed_anyway");
    public static final Component DISABLE_LEADERBOARDS_MODE_AND_RESTART = Component.translatable("speedrunnermod.disable_leaderboards_mode_and_restart");
    public static final Component IGNORE = Component.translatable("speedrunnermod.ignore").withStyle(ChatFormatting.RED);
    public static final Component VIEW_INELIGIBLE_OPTIONS = Component.translatable("speedrunnermod.view_ineligible_options");
    public static final Component VISIT_SUBMISSION_PAGE = Component.translatable("speedrunnermod.visit_submission_page");
    public static final Component BEGIN_PLAYING = Component.translatable("speedrunnermod.begin_playing");

    // Modes
    public static final Component EASY_MODE = Component.translatable("speedrunnermod.options.mode.easy");
    public static final Component EASY_MODE_TOOLTIP = Component.translatable("speedrunnermod.mode.easy.tooltip");
    public static final Component BALANCED_MODE = Component.translatable("speedrunnermod.options.mode.balanced");
    public static final Component BALANCED_MODE_TOOLTIP = Component.translatable("speedrunnermod.mode.balanced.tooltip");
    public static final Component DOOM_MODE = Component.translatable("speedrunnermod.options.mode.doom");
    public static final Component DOOM_MODE_TOOLTIP = Component.translatable("speedrunnermod.mode.doom.tooltip");

    // State-of-the-art item tooltips/messages
    public static final Component CALCULATING = Component.translatable("item.speedrunnermod.eye.calculating").withStyle(ChatFormatting.RED);

    // Match client-settings to server
    public static final Component TITLE_MODE_DOESNT_MATCH_SERVER_SETTING = Component.translatable("speedrunnermod.title.mode_doesnt_match_server_setting");
    public static final Component MODE_DOESNT_MATCH_SERVER_SETTING = Component.translatable("speedrunnermod.mode.doesnt_match_server");
    public static final Component MATCH_MODE_TO_SERVER = Component.translatable("speedrunnermod.match_mode_to_server");
    public static final Component TITLE_MATCH_SETTINGS_WITH_SERVER = Component.translatable("speedrunnermod.title.match_settings_with_server");
    public static final Component MATCH_AND_RESTART = Component.translatable("speedrunnermod.match_and_restart");
    public static final Component MATCH_AND_RESTART_TOOLTIP = Component.translatable("speedrunnermod.match_and_restart.tooltip");
    public static final Component MATCHED_SETTINGS_WITH_SERVER = Component.translatable("speedrunnermod.matched_settings_with_server");
    public static final Component ABORT = Component.translatable("speedrunnermod.abort");
    public static final Component MATCH_SETTINGS_WITH_SERVER_LINE1 = Component.translatable("speedrunnermod.match_settings_with_server.line1");
    public static final Component MATCH_SETTINGS_WITH_SERVER_LINE2 = Component.translatable("speedrunnermod.match_settings_with_server.line2");
    public static final Component MATCH_SETTINGS_WITH_SERVER_SYNC_FAILED = Component.translatable("speedrunnermod.match_settings_with_server_sync_failed");
    public static final Component MATCH_SETTINGS_WITH_SERVER_SYNC_FAILED_LINE2 = Component.translatable("speedrunnermod.match_settings_with_server_sync_failed.line2");
    public static final Component MATCH_SETTINGS_WITH_SERVER_FAILED = MATCH_SETTINGS_WITH_SERVER_SYNC_FAILED.copy().append(" ").append(MATCH_SETTINGS_WITH_SERVER_SYNC_FAILED_LINE2);

    // Title screen
    public static final Component FEATURES_TOOLTIP = Component.translatable("speedrunnermod.features.tooltip");
    public static final Component CREATE_WORLD_BUTTON_TOOLTIP = Component.translatable("speedrunnermod.create_world_button.desc");
    public static final Component CREATE_WORLD_BUTTON_DISABLED_TOOLTIP = Component.translatable("speedrunnermod.create_world_button.disabled");
    public static final Component OPTIONS_TOOLTIP = Component.translatable("speedrunnermod.title.options.tooltip");
    public static final Component RESTART_REQUIRED_TOOLTIP = Component.translatable("speedrunnermod.options.restart_required.tooltip");

    // Miscellaneous
    public static final Component QUESTIONS_AND_ISSUES = Component.translatable("speedrunnermod.questions_and_issues").withStyle(ChatFormatting.BLUE);

    /**
     * @return the text for disabled items.
     */
    public static Component stateOfTheArtItemDisabledTooltip(Mode mode) {
        Component modeText;
        switch (mode) {
            case DOOM -> modeText = Component.literal("doom").withStyle(ChatFormatting.RED);
            case BALANCED -> modeText = Component.literal("balanced").withStyle(ChatFormatting.YELLOW);
            default -> modeText = Component.literal("easy").withStyle(ChatFormatting.AQUA);
        }
        return Component.translatable("item.speedrunnermod.state_of_the_art_item.disabled", modeText).withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC);
    }
}