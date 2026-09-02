package net.dillon.speedrunnermod.helper;

import net.dillon.speedrunnermod.option.eum.Mode;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

/**
 * Stores all {@code translation keys} for the Speedrunner Mod.
 */
public class ModTexts {
    // Titles and menus
    public static final Component TITLE = Component.translatable("speedrunnermod.title");
    public static final Component MENU_FEATURES = Component.translatable("speedrunnermod.menu.features").withStyle(ChatFormatting.AQUA);
    public static final Component TITLE_FEATURES = Component.translatable("speedrunnermod.title.features").withStyle(ChatFormatting.AQUA);
    public static final Component ENABLE_DOOM_MODE = Component.translatable("speedrunnermod.doom_mode.enable").withStyle(ChatFormatting.RED);

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

    // Mods
    public static final Component SODIUM = Component.translatable("speedrunnermod.title.resources.mods.sodium").withStyle(ChatFormatting.GREEN);
    public static final Component LITHIUM = Component.translatable("speedrunnermod.title.resources.mods.lithium").withStyle(ChatFormatting.AQUA);
    public static final Component SPEEDRUN_IGT = Component.translatable("speedrunnermod.title.resources.mods.speedrunigt").withStyle(ChatFormatting.GREEN);
    public static final Component KRYPTON = Component.translatable("speedrunnermod.title.resources.mods.krypton").withStyle(ChatFormatting.GRAY);
    public static final Component SIMPLE_KEYBINDS = Component.translatable("speedrunnermod.title.resources.mods.simple_keybinds").withStyle(ChatFormatting.GREEN);
    public static final Component QUALITY_OF_QUESO = Component.translatable("speedrunnermod.title.resources.mods.qoq").withStyle(ChatFormatting.YELLOW);

    // Modes
    public static final Component EASY_MODE = Component.translatable("speedrunnermod.options.mode.easy");
    public static final Component BALANCED_MODE = Component.translatable("speedrunnermod.options.mode.balanced");
    public static final Component DOOM_MODE = Component.translatable("speedrunnermod.options.mode.doom");

    // State-of-the-art item tooltips/messages
    public static final Component CALCULATING = Component.translatable("item.speedrunnermod.eye.calculating").withStyle(ChatFormatting.RED);

    // Match client-settings to server
    public static final Component MATCHED_SETTINGS_WITH_SERVER = Component.translatable("speedrunnermod.matched_settings_with_server");

    // Title and pause screen
    public static final Component CREATE_WORLD_BUTTON_TOOLTIP = Component.translatable("speedrunnermod.create_world_button.desc");
    public static final Component CREATE_WORLD_BUTTON_DISABLED_TOOLTIP = Component.translatable("speedrunnermod.create_world_button.disabled");
    public static final Component OPTIONS_TOOLTIP = Component.translatable("speedrunnermod.title.options.tooltip");
    public static final Component OPTIONS_UPDATE_TOOLTIP = Component.translatable("speedrunnermod.title.update_available");

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