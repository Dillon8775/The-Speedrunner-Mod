package net.dillon.speedrunnermod.helper;

import net.dillon.speedrunnermod.option.eum.Mode;
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

    // Titles and menus
    public static final Component TITLE = Component.translatable("speedrunnermod.title");
    public static final Component MENU_FEATURES = Component.translatable("speedrunnermod.menu.features").withStyle(ChatFormatting.GOLD);
    public static final Component TITLE_FEATURES = Component.translatable("speedrunnermod.title.features").withStyle(ChatFormatting.AQUA);
    public static final Component MENU_MODS = Component.translatable("speedrunnermod.menu.resources.mods").withStyle(ChatFormatting.AQUA);
    public static final Component MENU_TUTORIALS = Component.translatable("speedrunnermod.menu.resources.tutorials").withStyle(ChatFormatting.GREEN);
    public static final Component ENABLE_DOOM_MODE = Component.translatable("speedrunnermod.doom_mode.enable").withStyle(ChatFormatting.RED);
    public static final Component DOOM_MODE_ALREADY_ENABLED = Component.translatable("speedrunnermod.doom_mode.already_enabled").withStyle(ChatFormatting.RED);

    // Feature screens enable/disable features
    public static final Component STOP_SPEEDRUNNERS_WASTELAND_BIOME_FROM_GENERATING = Component.translatable("speedrunnermod.options.stop_speedrunners_wasteland_biome_from_generating").withStyle(ChatFormatting.RED);
    public static final Component ALLOW_SPEEDRUNNERS_WASTELAND_BIOME_TO_GENERATE = Component.translatable("speedrunnermod.options.allow_speedrunners_wasteland_biome_to_generate").withStyle(ChatFormatting.AQUA);
    public static final Component DISABLE_THIS_FEATURE = Component.translatable("speedrunnermod.disable_this_feature").withStyle(ChatFormatting.RED);
    public static final Component ENABLE_THIS_FEATURE = Component.translatable("speedrunnermod.enable_this_feature").withStyle(ChatFormatting.GREEN);
    public static final Component DISABLE_ICARUS_MODE = Component.translatable("speedrunnermod.disable_icarus_mode").withStyle(ChatFormatting.GRAY);
    public static final Component ENABLE_ICARUS_MODE = Component.translatable("speedrunnermod.enable_icarus_mode").withStyle(ChatFormatting.GRAY);
    public static final Component DISABLE_INFINI_PEARL_MODE = Component.translatable("speedrunnermod.disable_infini_pearl_mode").withStyle(ChatFormatting.BLUE);
    public static final Component ENABLE_INFINI_PEARL_MODE = Component.translatable("speedrunnermod.enable_infini_pearl_mode").withStyle(ChatFormatting.BLUE);

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

    // Links
    public static final Component MOD_SHOWCASE_VIDEO = Component.translatable("speedrunnermod.menu.links.mod_showcase_video").withStyle(ChatFormatting.LIGHT_PURPLE);
    public static final Component MOD_RELEASE_TRAILER = Component.translatable("speedrunnermod.menu.links.mod_release_trailer").withStyle(ChatFormatting.AQUA);

    // Option files
    public static final Component MENU_OPEN_OPTIONS_FILE = Component.translatable("speedrunnermod.menu.open_options_file");
    public static final Component OPEN_OPTIONS_FILE_TOOLTIP = Component.translatable("speedrunnermod.menu.open_options_file.tooltip");
    public static final Component OPEN_CLIENT_OPTIONS_FILE_TOOLTIP = Component.translatable("speedrunnermod.menu.open_client_options_file.tooltip");

    // Mods
    public static final Component SODIUM = Component.translatable("speedrunnermod.title.resources.mods.sodium").withStyle(ChatFormatting.GREEN);
    public static final Component LITHIUM = Component.translatable("speedrunnermod.title.resources.mods.lithium").withStyle(ChatFormatting.AQUA);
    public static final Component SPEEDRUN_IGT = Component.translatable("speedrunnermod.title.resources.mods.speedrunigt").withStyle(ChatFormatting.GREEN);
    public static final Component KRYPTON = Component.translatable("speedrunnermod.title.resources.mods.krypton").withStyle(ChatFormatting.GRAY);
    public static final Component SIMPLE_KEYBINDS = Component.translatable("speedrunnermod.title.resources.mods.simple_keybinds").withStyle(ChatFormatting.GREEN);
    public static final Component QUALITY_OF_QUESO = Component.translatable("speedrunnermod.title.resources.mods.qoq").withStyle(ChatFormatting.YELLOW);

    // Configuration buttons
    public static final Component NOT_NOW = Component.translatable("speedrunnermod.not_now");
    public static final Component RESTART_NOW = Component.translatable("speedrunnermod.restart_now");
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