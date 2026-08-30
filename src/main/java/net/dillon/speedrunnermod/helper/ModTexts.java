package net.dillon.speedrunnermod.helper;

import net.dillon.speedrunnermod.option.Mode;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

/**
 * Stores all {@code translation keys} for the Speedrunner Mod.
 */
public class ModTexts {
    // Main
    public static final Component SAVE = Component.translatable("speedrunnermod.save");
    public static final Component SAVE_TOOLTIP = Component.translatable("speedrunnermod.save.tooltip");
    public static final Component NEXT_ARROW = Component.literal(">").withStyle(ChatFormatting.BOLD);
    public static final Component NEXT_ARROW_TOOLTIP = Component.translatable("speedrunnermod.next.tooltip");
    public static final Component PREVIOUS = Component.literal("<").withStyle(ChatFormatting.BOLD);
    public static final Component PREVIOUS_TOOLTIP = Component.translatable("speedrunnermod.previous.tooltip");
    public static final Component HELP_TOOLTIP = Component.translatable("speedrunnermod.help_button.tooltip");
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
    public static final Component TITLE_FEATURES = Component.translatable("speedrunnermod.title.features").withStyle(ChatFormatting.AQUA);
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
    public static final Component MENU_BLOCKS_AND_ITEMS = Component.translatable("speedrunnermod.menu.features.blocks_and_items").withStyle(ChatFormatting.GREEN);
    public static final Component TITLE_BLOCKS_AND_ITEMS = Component.translatable("speedrunnermod.title.features.blocks_and_items");
    public static final Component MENU_TOOLS_AND_ARMOR = Component.translatable("speedrunnermod.menu.features.tools_and_armor").withStyle(ChatFormatting.AQUA);
    public static final Component TITLE_TOOLS_AND_ARMOR = Component.translatable("speedrunnermod.title.features.tools_and_armor");
    public static final Component MENU_POTIONS_AND_ENCHANTMENTS = Component.translatable("speedrunnermod.menu.features.potions_and_enchantments").withStyle(ChatFormatting.LIGHT_PURPLE);
    public static final Component TITLE_POTIONS_AND_ENCHANTMENTS = Component.translatable("speedrunnermod.title.features.potions_and_enchantments");
    public static final Component MENU_ORES_AND_WORLDGEN = Component.translatable("speedrunnermod.menu.features.ores_and_worldgen").withStyle(ChatFormatting.GREEN);
    public static final Component TITLE_ORES_AND_WORLDGEN = Component.translatable("speedrunnermod.title.features.ores_and_worldgen");
    public static final Component MENU_FEATURE_DOOM_MODE = Component.translatable("speedrunnermod.menu.features.doom_mode").withStyle(ChatFormatting.RED);
    public static final Component TITLE_FEATURE_DOOM_MODE = Component.translatable("speedrunnermod.title.features.doom_mode");
    public static final Component MENU_MISCELLANEOUS = Component.translatable("speedrunnermod.menu.features.miscellaneous");
    public static final Component TITLE_MISCELLANEOUS = Component.translatable("speedrunnermod.title.features.miscellaneous");

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
    public static final Component OPTIONS_UPDATE_TOOLTIP = Component.translatable("speedrunnermod.title.update_available");
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