package net.dillon.speedrunnermod.util;

import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

/**
 * All {@code translation keys} for the Speedrunner Mod.
 */
public class ModTexts {
    public static final Text BLANK = Text.literal("");
    public static final Text OK = Text.translatable("speedrunnermod.ok");
    public static final Text SAVE = Text.translatable("speedrunnermod.save");
    public static final Text SAVE_TOOLTIP = Text.translatable("speedrunnermod.save.tooltip");
    public static final Text NEXT = Text.literal(">").formatted(Formatting.BOLD);
    public static final Text NEXT_TOOLTIP = Text.translatable("speedrunnermod.next.tooltip");
    public static final Text PREVIOUS = Text.literal("<").formatted(Formatting.BOLD);
    public static final Text PREVIOUS_TOOLTIP = Text.translatable("speedrunnermod.previous.tooltip");
    public static final Text BACK = Text.translatable("speedrunnermod.back");
    public static final Text HELP_TOOLTIP = Text.translatable("speedrunnermod.help_button.tooltip");
    public static final Text DIRECTORY_TOOLTIP = Text.translatable("speedrunnermod.directory_button.tooltip");
    public static final Text ON = Text.literal("ON").formatted(Formatting.GREEN);
    public static final Text OFF = Text.literal("OFF").formatted(Formatting.RED);
    public static final Text DASH = Text.literal("---").formatted(Formatting.DARK_RED);
    public static final Text YES = Text.literal("YES").formatted(Formatting.GREEN);
    public static final Text NO = Text.literal("NO").formatted(Formatting.RED);
    public static final Text REFRESHING = Text.literal("Refreshing...");
    public static final Text ENABLED = Text.literal("Enabled").formatted(Formatting.GREEN);
    public static final Text DISABLED = Text.literal("DISABLED").formatted(Formatting.BOLD).formatted(Formatting.RED);
    public static final Text FEATURE_DISABLED = Text.literal("Feature Disabled");

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
    public static final Text TITLE_CREDITS = Text.translatable("speedrunnermod.title.credits");
    public static final Text MENU_EXTERNAL = Text.translatable("speedrunnermod.menu.external").formatted(Formatting.RED);
    public static final Text TITLE_EXTERNAL = Text.translatable("speedrunnermod.external");
    public static final Text MENU_FEATURES_TOOLTIP = Text.translatable("speedrunnermod.menu.features.tooltip");
    public static final Text MENU_FEATURES = Text.translatable("speedrunnermod.menu.features").formatted(Formatting.AQUA);
    public static final Text TITLE_FEATURES = Text.translatable("speedrunnermod.title.features");
    public static final Text MENU_ALL_FEATURES = Text.translatable("speedrunnermod.menu.features.all_features").formatted(Formatting.BOLD);
    public static final Text TITLE_ALL_FEATURES = Text.translatable("speedrunnermod.title.features.all_features").formatted(Formatting.AQUA);
    public static final Text MENU_WIKI = Text.translatable("speedrunnermod.menu.resources.wiki").formatted(Formatting.LIGHT_PURPLE);
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
    public static final Text MENU_DOOM_MODE = Text.translatable("speedrunnermod.menu.doom_mode");
    public static final Text TITLE_DOOM_MODE = Text.translatable("speedrunnermod.title.doom_mode");
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

    public static final Text CURSEFORGE = Text.translatable("speedrunnermod.menu.external.curseforge").formatted(Formatting.GOLD);
    public static final Text MODRINTH = Text.translatable("speedrunnermod.menu.external.modrinth").formatted(Formatting.GREEN);
    public static final Text GITHUB = Text.translatable("speedrunnermod.menu.external.github").formatted(Formatting.GRAY);
    public static final Text WEBPAGE = Text.translatable("speedrunnermod.menu.external.wiki").formatted(Formatting.LIGHT_PURPLE);
    public static final Text MOD_SHOWCASE_VIDEO = Text.translatable("speedrunnermod.menu.external.mod_showcase_video").formatted(Formatting.AQUA);
    public static final Text MENU_LEADERBOARDS = Text.translatable("speedrunnermod.menu.external.leaderboards").formatted(Formatting.GREEN);
    public static final Text MENU_LEADERBOARDS_DISABLED = Text.translatable("speedrunnermod.menu.leaderboards.disabled");
    public static final Text MENU_LEADERBOARDS_VIEW = Text.translatable("speedrunnermod.menu.leaderboards.view");
    public static final Text MENU_LEADERBOARDS_SPREADSHEET = Text.translatable("speedrunnermod.menu.leaderboards.spreadsheet");
    public static final Text TITLE_LEADERBOARDS = Text.translatable("speedrunnermod.title.leaderboards").formatted(Formatting.GREEN);
    public static final Text TITLE_INELIGIBLE_OPTIONS = Text.translatable("speedrunnermod.title.ineligible_options");

    public static final Text MENU_OPEN_OPTIONS_FILE = Text.translatable("speedrunnermod.menu.open_options_file");
    public static final Text OPEN_OPTIONS_FILE_TOOLTIP = Text.translatable("speedrunnermod.menu.open_options_file.tooltip");

    public static final Text SODIUM = Text.translatable("speedrunnermod.title.resources.mods.sodium").formatted(Formatting.GREEN);
    public static final Text SODIUM_TOOLTIP = Text.translatable("speedrunnermod.title.resources.mods.sodium.tooltip");
    public static final Text LITHIUM = Text.translatable("speedrunnermod.title.resources.mods.lithium").formatted(Formatting.AQUA);
    public static final Text LITHIUM_TOOLTIP = Text.translatable("speedrunnermod.title.resources.mods.lithium.tooltip");
    public static final Text PHOSPHOR = Text.translatable("speedrunnermod.title.resources.mods.phosphor").formatted(Formatting.YELLOW);
    public static final Text PHOSPHOR_TOOLTIP = Text.translatable("speedrunnermod.title.resources.mods.phosphor.tooltip");
    public static final Text SPEEDRUN_IGT = Text.translatable("speedrunnermod.title.resources.mods.speedrunigt").formatted(Formatting.GREEN);
    public static final Text SPEEDRUN_IGT_TOOLTIP = Text.translatable("speedrunnermod.title.resources.mods.speedrunigt.tooltip");
    public static final Text LAZYDFU = Text.translatable("speedrunnermod.title.resources.mods.lazydfu").formatted(Formatting.BLUE);
    public static final Text LAZYDFU_TOOLTIP = Text.translatable("speedrunnermod.title.resources.mods.lazydfu.tooltip");
    public static final Text KRYPTON = Text.translatable("speedrunnermod.title.resources.mods.krypton").formatted(Formatting.GRAY);
    public static final Text SIMPLE_KEYBINDS = Text.translatable("speedrunnermod.title.resources.mods.simple_keybinds").formatted(Formatting.GREEN);
    public static final Text SIMPLE_KEYBINDS_TOOLTIP = Text.translatable("speedrunnermod.title.resources.mods.simple_keybinds.tooltip");
    public static final Text KRYPTON_TOOLTIP = Text.translatable("speedrunnermod.title.resources.mods.krypton.tooltip");
    public static final Text OPTIFINE = Text.translatable("speedrunnermod.title.resources.mods.optifine").formatted(Formatting.RED);
    public static final Text OPTIFINE_TOOLTIP = Text.translatable("speedrunnermod.title.resources.mods.optifine.tooltip");

    public static final Text RESET = Text.translatable("speedrunnermod.reset");
    public static final Text RESET_CONFIRM = Text.translatable("speedrunnermod.reset_confirm");
    public static final Text NOT_NOW = Text.translatable("speedrunnermod.not_now");
    public static final Text REFRESH_SCREEN_TOOLTIP = Text.translatable("speedrunnermod.refresh_screen");

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

    public static final Text QUESTIONS_AND_ISSUES = Text.translatable("speedrunnermod.questions_and_issues").formatted(Formatting.BLUE);
    public static final Text QUESTIONS_AND_ISSUES_TOOLTIP = Text.translatable("speedrunnermod.questions_and_issues.tooltip");
    public static final Text SUGGESTIONS_AND_FEEDBACK = Text.translatable("speedrunnermod.suggestions_and_feedback").formatted(Formatting.GOLD);
    public static final Text SUGGESTIONS_AND_FEEDBACK_TOOLTIP = Text.translatable("speedrunnermod.suggestions_and_feedback.tooltip");

    public static final Text FEATURES_TOOLTIP = Text.translatable("speedrunnermod.features.tooltip");
    public static final Text CREATE_WORLD_BUTTON_TOOLTIP = Text.translatable("speedrunnermod.create_world_button.desc");
    public static final Text CREATE_WORLD_BUTTON_DISABLED_TOOLTIP = Text.translatable("speedrunnermod.create_world_button.disabled");
    public static final Text OPTIONS_TOOLTIP = Text.translatable("speedrunnermod.title.options.tooltip");
    public static final Text WIKI_TOOLTIP = Text.translatable("speedrunnermod.menu.title_screen.external.wiki.tooltip");
}