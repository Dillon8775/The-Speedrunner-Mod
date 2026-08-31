package net.dillon.speedrunnermod.screen.feature;

import net.dillon.speedrunnermod.screen.FeatureScreen;
import net.dillon.speedrunnermod.screen.feature.blocksanditems.DefaultBlocksAndItemsFeatureFactory;
import net.dillon.speedrunnermod.screen.feature.blocksanditems.SpeedrunnersWorkbenchScreen;
import net.dillon.speedrunnermod.screen.feature.doommode.DoomModeFeatureScreen;
import net.dillon.speedrunnermod.screen.feature.doommode.OtherThingsToKnowScreen;
import net.dillon.speedrunnermod.screen.feature.firsttimeplaying.*;
import net.dillon.speedrunnermod.screen.feature.miscellaneous.*;
import net.dillon.speedrunnermod.screen.feature.oresandworldgen.CommonOresScreen;
import net.dillon.speedrunnermod.screen.feature.oresandworldgen.DefaultOresAndWorldGenFeatureFactory;
import net.dillon.speedrunnermod.screen.feature.oresandworldgen.FortressesBastionsAndStrongholdsScreen;
import net.dillon.speedrunnermod.screen.feature.oresandworldgen.SpeedrunnersWastelandBiomeScreen;
import net.dillon.speedrunnermod.screen.feature.potionsandenchantments.CooldownEnchantmentScreen;
import net.dillon.speedrunnermod.screen.feature.potionsandenchantments.DefaultPotionsAndEnchantmentsFeatureFactory;
import net.dillon.speedrunnermod.screen.feature.secretdoommode.*;
import net.dillon.speedrunnermod.screen.feature.toolsandarmor.DefaultToolsAndArmorFeatureFactory;
import net.dillon.speedrunnermod.screen.feature.toolsandarmor.DragonsSwordScreen;
import net.minecraft.client.gui.screens.Screen;

/**
 * All feature pages for every feature screen, categorized by their {@link FeatureScreenCategory}, {@code in order.}
 */
public enum FeaturePage {
    FIRST_TIME_PLAYING("first_time_playing", FeatureScreenCategory.FIRST_TIME_PLAYING, FeatureScreenPage.FTP, FirstTimePlayingScreen::new),
    KEY_FEATURES("key_features", FeatureScreenCategory.FIRST_TIME_PLAYING, FeatureScreenPage.FTP, KeyFeaturesScreen::new),
    MODE_OPTION("mode_option", FeatureScreenCategory.FIRST_TIME_PLAYING, FeatureScreenPage.FTP, ModeOptionScreen::new),
    READY_TO_PLAY("ready_to_play", FeatureScreenCategory.FIRST_TIME_PLAYING, FeatureScreenPage.FTP, ReadyToPlayScreen::new),
    FTP_RESTART_REQUIRED("ftp_restart_required", FeatureScreenCategory.FIRST_TIME_PLAYING, FeatureScreenPage.FTP, FTPRestartRequiredScreen::new),

    SPEEDRUNNER_INGOTS("speedrunner_ingots", FeatureScreenCategory.BLOCKS_AND_ITEMS, FeatureScreenPage.FIRST, DefaultBlocksAndItemsFeatureFactory::new),
    SPEEDRUNNER_NUGGETS("speedrunner_nuggets", FeatureScreenCategory.BLOCKS_AND_ITEMS, DefaultBlocksAndItemsFeatureFactory::new),
    SPEEDRUNNER_BLOCKS("speedrunner_blocks", FeatureScreenCategory.BLOCKS_AND_ITEMS, DefaultBlocksAndItemsFeatureFactory::new),
    RAW_SPEEDRUNNER("raw_speedrunner", FeatureScreenCategory.BLOCKS_AND_ITEMS, DefaultBlocksAndItemsFeatureFactory::new),
    SPEEDRUNNER_WOOD("speedrunner_wood", FeatureScreenCategory.BLOCKS_AND_ITEMS, DefaultBlocksAndItemsFeatureFactory::new),
    DEAD_SPEEDRUNNER_WOOD("dead_speedrunner_wood", FeatureScreenCategory.BLOCKS_AND_ITEMS, DefaultBlocksAndItemsFeatureFactory::new),
    SPEEDRUNNER_PADDLE("speedrunner_paddle", FeatureScreenCategory.BLOCKS_AND_ITEMS, DefaultBlocksAndItemsFeatureFactory::new),
    FIREPROOF_BOATS("fireproof_boats", FeatureScreenCategory.BLOCKS_AND_ITEMS, DefaultBlocksAndItemsFeatureFactory::new),
    IGNEOUS_ROCKS("igneous_rocks", FeatureScreenCategory.BLOCKS_AND_ITEMS, DefaultBlocksAndItemsFeatureFactory::new),
    SPEEDRUNNERS_EYE("speedrunners_eye", FeatureScreenCategory.BLOCKS_AND_ITEMS, DefaultBlocksAndItemsFeatureFactory::new),
    EYE_OF_INFERNO("eye_of_inferno", FeatureScreenCategory.BLOCKS_AND_ITEMS, DefaultBlocksAndItemsFeatureFactory::new),
    ENDER_THRUSTER("ender_thruster", FeatureScreenCategory.BLOCKS_AND_ITEMS, DefaultBlocksAndItemsFeatureFactory::new),
    ENDER_MATTER("ender_matter", FeatureScreenCategory.BLOCKS_AND_ITEMS, DefaultBlocksAndItemsFeatureFactory::new),
    INFINI_PEARL("infini_pearl", FeatureScreenCategory.BLOCKS_AND_ITEMS, DefaultBlocksAndItemsFeatureFactory::new),
    SPEEDRUNNERS_TOTEM("speedrunners_totem", FeatureScreenCategory.BLOCKS_AND_ITEMS, DefaultBlocksAndItemsFeatureFactory::new),
    EYE_OF_ANNUL("eye_of_annul", FeatureScreenCategory.BLOCKS_AND_ITEMS, DefaultBlocksAndItemsFeatureFactory::new),
    DRAGONS_PEARL("dragons_pearl", FeatureScreenCategory.BLOCKS_AND_ITEMS, DefaultBlocksAndItemsFeatureFactory::new),
    DRAGONS_FIREBALL("dragons_fireball", FeatureScreenCategory.BLOCKS_AND_ITEMS, DefaultBlocksAndItemsFeatureFactory::new),
    PIGLIN_AWAKENER("piglin_awakener", FeatureScreenCategory.BLOCKS_AND_ITEMS, DefaultBlocksAndItemsFeatureFactory::new),
    BLAZE_SPOTTER("blaze_spotter", FeatureScreenCategory.BLOCKS_AND_ITEMS, DefaultBlocksAndItemsFeatureFactory::new),
    RAID_ERADICATOR("raid_eradicator", FeatureScreenCategory.BLOCKS_AND_ITEMS, DefaultBlocksAndItemsFeatureFactory::new),
    INVENTORY_PRESERVER("inventory_preserver", FeatureScreenCategory.BLOCKS_AND_ITEMS, DefaultBlocksAndItemsFeatureFactory::new),
    SPEEDRUNNER_BULK("speedrunner_bulk", FeatureScreenCategory.BLOCKS_AND_ITEMS, DefaultBlocksAndItemsFeatureFactory::new),
    SPEEDRUNNERS_WORKBENCH("speedrunners_workbench", FeatureScreenCategory.BLOCKS_AND_ITEMS, FeatureScreenPage.LAST, SpeedrunnersWorkbenchScreen::new),

    SPEEDRUNNER_ARMOR("speedrunner_armor", FeatureScreenCategory.TOOLS_AND_ARMOR, FeatureScreenPage.FIRST, DefaultToolsAndArmorFeatureFactory::new),
    GOLDEN_SPEEDRUNNER_ARMOR("golden_speedrunner_armor", FeatureScreenCategory.TOOLS_AND_ARMOR, DefaultToolsAndArmorFeatureFactory::new),
    GOLDEN_SMITHING_TEMPLATE("golden_smithing_template", FeatureScreenCategory.TOOLS_AND_ARMOR, DefaultToolsAndArmorFeatureFactory::new),
    SPEEDRUNNER_SHIELD("speedrunner_shield", FeatureScreenCategory.TOOLS_AND_ARMOR, DefaultToolsAndArmorFeatureFactory::new),
    SPEEDRUNNER_NAUTILUS_ARMOR("speedrunner_nautilus_armor", FeatureScreenCategory.TOOLS_AND_ARMOR, DefaultToolsAndArmorFeatureFactory::new),
    SPEEDRUNNER_HARNESS("speedrunner_harness", FeatureScreenCategory.TOOLS_AND_ARMOR, DefaultToolsAndArmorFeatureFactory::new),
    SPEEDRUNNER_SPEAR("speedrunner_spear", FeatureScreenCategory.TOOLS_AND_ARMOR, DefaultToolsAndArmorFeatureFactory::new),
    SPEEDRUNNER_BOW_AND_CROSSBOW("speedrunner_bow_and_crossbow", FeatureScreenCategory.TOOLS_AND_ARMOR, DefaultToolsAndArmorFeatureFactory::new),
    SPEEDRUNNER_FLINT_AND_STEEL("speedrunner_flint_and_steel", FeatureScreenCategory.TOOLS_AND_ARMOR, DefaultToolsAndArmorFeatureFactory::new),
    SPEEDRUNNER_SHEARS("speedrunner_shears", FeatureScreenCategory.TOOLS_AND_ARMOR, DefaultToolsAndArmorFeatureFactory::new),
    DRAGONS_SWORD("dragons_sword", FeatureScreenCategory.TOOLS_AND_ARMOR, FeatureScreenPage.LAST, DragonsSwordScreen::new),

    DRAGONS_AURA("dragons_aura", FeatureScreenCategory.POTIONS_AND_ENCHANTMENTS, FeatureScreenPage.FIRST, DefaultPotionsAndEnchantmentsFeatureFactory::new),
    LUCK_POTION("luck_potion", FeatureScreenCategory.POTIONS_AND_ENCHANTMENTS, DefaultPotionsAndEnchantmentsFeatureFactory::new),
    WITHERED_EFFECT("withered_effect", FeatureScreenCategory.POTIONS_AND_ENCHANTMENTS, DefaultPotionsAndEnchantmentsFeatureFactory::new),
    DASH_ENCHANTMENT("dash_enchantment", FeatureScreenCategory.POTIONS_AND_ENCHANTMENTS, DefaultPotionsAndEnchantmentsFeatureFactory::new),
    COOLDOWN_ENCHANTMENT("cooldown_enchantment", FeatureScreenCategory.POTIONS_AND_ENCHANTMENTS, FeatureScreenPage.LAST, CooldownEnchantmentScreen::new),

    SPEEDRUNNERS_WASTELAND("speedrunners_wasteland", FeatureScreenCategory.ORES_AND_WORLDGEN, FeatureScreenPage.FIRST, SpeedrunnersWastelandBiomeScreen::new),
    SPEEDRUNNERS_ORES("speedrunner_ores", FeatureScreenCategory.ORES_AND_WORLDGEN, DefaultOresAndWorldGenFeatureFactory::new),
    EXPERIENCE_ORES("experience_ores", FeatureScreenCategory.ORES_AND_WORLDGEN, DefaultOresAndWorldGenFeatureFactory::new),
    EXPERIENCE_FRAGMENT("experience_fragment", FeatureScreenCategory.ORES_AND_WORLDGEN, DefaultOresAndWorldGenFeatureFactory::new),
    IGNEOUS_ORES("igneous_ores", FeatureScreenCategory.ORES_AND_WORLDGEN, DefaultOresAndWorldGenFeatureFactory::new),
    COMMON_ORES("common_ores", FeatureScreenCategory.ORES_AND_WORLDGEN, CommonOresScreen::new),
    BETTER_BIOMES("better_biomes", FeatureScreenCategory.ORES_AND_WORLDGEN, DefaultOresAndWorldGenFeatureFactory::new),
    STRUCTURES("structures", FeatureScreenCategory.ORES_AND_WORLDGEN, DefaultOresAndWorldGenFeatureFactory::new),
    FORTRESSES_BASTIONS_AND_STRONGHOLDS("fortresses_bastions_and_strongholds", FeatureScreenCategory.ORES_AND_WORLDGEN, FeatureScreenPage.LAST, FortressesBastionsAndStrongholdsScreen::new),

    ICARUS_MODE("icarus_mode", FeatureScreenCategory.MISCELLANEOUS, FeatureScreenPage.FIRST, ICarusModeScreen::new),
    INFINI_PEARL_MODE("infini_pearl_mode", FeatureScreenCategory.MISCELLANEOUS, InfiniPearlModeScreen::new),
    FASTER_BLOCK_BREAKING("faster_block_breaking", FeatureScreenCategory.MISCELLANEOUS, DefaultMiscellaneousFeatureFactory::new),
    BETTER_PIGLIN_BARTERING("better_piglin_bartering", FeatureScreenCategory.MISCELLANEOUS, DefaultMiscellaneousFeatureFactory::new),
    PIGLIN_PORK("piglin_pork", FeatureScreenCategory.MISCELLANEOUS, DefaultMiscellaneousFeatureFactory::new),
    NO_MORE_PIGLIN_BRUTES("no_more_piglin_brutes", FeatureScreenCategory.MISCELLANEOUS, DefaultMiscellaneousFeatureFactory::new),
    TRIPLED_DROPS("tripled_drops", FeatureScreenCategory.MISCELLANEOUS, DefaultMiscellaneousFeatureFactory::new),
    MORE_EXPERIENCE("more_experience", FeatureScreenCategory.MISCELLANEOUS, DefaultMiscellaneousFeatureFactory::new),
    BLAZE_SPAWNERS_IN_BASTIONS("blaze_spawners_in_bastions", FeatureScreenCategory.MISCELLANEOUS, DefaultMiscellaneousFeatureFactory::new),
    BLAZES_DROP_GOLD("blazes_drop_gold", FeatureScreenCategory.MISCELLANEOUS, DefaultMiscellaneousFeatureFactory::new),
    BETTER_NETHER_PORTALS("better_nether_portals", FeatureScreenCategory.MISCELLANEOUS, DefaultMiscellaneousFeatureFactory::new),
    WATER_IN_NETHER("water_in_nether", FeatureScreenCategory.MISCELLANEOUS, WaterInNetherScreen::new),
    FIREPROOF_ITEMS("fireproof_items", FeatureScreenCategory.MISCELLANEOUS, DefaultMiscellaneousFeatureFactory::new),
    THROWABLE_FIREBALLS("throwable_fireballs", FeatureScreenCategory.MISCELLANEOUS, DefaultMiscellaneousFeatureFactory::new),
    BETTER_HOTKEYS("better_hotkeys", FeatureScreenCategory.MISCELLANEOUS, DefaultMiscellaneousFeatureFactory::new),
    RESET_KEY("reset_key", FeatureScreenCategory.MISCELLANEOUS, DefaultMiscellaneousFeatureFactory::new),
    FOG_KEY("fog_key", FeatureScreenCategory.MISCELLANEOUS, DefaultMiscellaneousFeatureFactory::new),
    FULLBRIGHT_KEY("fullbright_key", FeatureScreenCategory.MISCELLANEOUS, DefaultMiscellaneousFeatureFactory::new),
    RETIRED_SPEEDRUNNER("retired_speedrunner", FeatureScreenCategory.MISCELLANEOUS, DefaultMiscellaneousFeatureFactory::new),
    BETTER_LOOT_TABLES("better_loot_tables", FeatureScreenCategory.MISCELLANEOUS, DefaultMiscellaneousFeatureFactory::new),
    REVERSE_CRAFTING("reverse_crafting", FeatureScreenCategory.MISCELLANEOUS, DefaultMiscellaneousFeatureFactory::new),
    FASTER_SMELTING("faster_smelting", FeatureScreenCategory.MISCELLANEOUS, DefaultMiscellaneousFeatureFactory::new),
    LESS_FALL_DAMAGE("less_fall_damage", FeatureScreenCategory.MISCELLANEOUS, DefaultMiscellaneousFeatureFactory::new),
    COOKED_FLESH("cooked_flesh", FeatureScreenCategory.MISCELLANEOUS, DefaultMiscellaneousFeatureFactory::new),
    BETTER_FOODS("better_foods", FeatureScreenCategory.MISCELLANEOUS, BetterFoodsScreen::new),
    BETTER_ANVILS("better_anvils", FeatureScreenCategory.MISCELLANEOUS, DefaultMiscellaneousFeatureFactory::new),
    BETTER_DEATH_SCREEN("better_death_screen", FeatureScreenCategory.MISCELLANEOUS, BetterDeathScreen::new),
    CRAFTABLE_GOD_APPLES("craftable_god_apples", FeatureScreenCategory.MISCELLANEOUS, DefaultMiscellaneousFeatureFactory::new),
    RIGHT_CLICK_TO_REMOVE_SILK_TOUCH("right_click_to_remove_silk_touch", FeatureScreenCategory.MISCELLANEOUS, RightClickToRemoveSilkTouchScreen::new),
    CRAFTABLE_TOTEMS("craftable_totems", FeatureScreenCategory.MISCELLANEOUS, DefaultMiscellaneousFeatureFactory::new),
    ENDER_EYES_NEVER_BREAK("ender_eyes_never_break", FeatureScreenCategory.MISCELLANEOUS, DefaultMiscellaneousFeatureFactory::new),
    ARROWS_EXPLODE_BEDS("arrows_explode_beds", FeatureScreenCategory.MISCELLANEOUS, ArrowsExplodeBedsScreen::new),
    SPEEDRUNNER_EDITION("speedrunner_edition", FeatureScreenCategory.MISCELLANEOUS, DefaultMiscellaneousFeatureFactory::new),
    CUSTOM_PANORAMA("custom_panorama", FeatureScreenCategory.MISCELLANEOUS, DefaultMiscellaneousFeatureFactory::new),
    AND_MORE("and_more", FeatureScreenCategory.MISCELLANEOUS, FeatureScreenPage.LAST, AndMoreScreen::new),

    BASICS("basics", FeatureScreenCategory.DOOM_MODE, FeatureScreenPage.FIRST, DoomModeFeatureScreen::new),
    A_PLEASANT_GREETING("a_pleasant_greeting", FeatureScreenCategory.DOOM_MODE, DoomModeFeatureScreen::new),
    CANT_SLEEP("cant_sleep", FeatureScreenCategory.DOOM_MODE, DoomModeFeatureScreen::new),
    BOSSES("bosses", FeatureScreenCategory.DOOM_MODE, DoomModeFeatureScreen::new),
    GOLIATH("goliath", FeatureScreenCategory.DOOM_MODE, DoomModeFeatureScreen::new),
    MINIONS("minions", FeatureScreenCategory.DOOM_MODE, DoomModeFeatureScreen::new),
    DOOM_BLOCKS("doom_blocks", FeatureScreenCategory.DOOM_MODE, DoomModeFeatureScreen::new),
    KNOCKBACK_STICK("knockback_stick", FeatureScreenCategory.DOOM_MODE, DoomModeFeatureScreen::new),
    OTHER_THINGS_TO_KNOW("other_things_to_know", FeatureScreenCategory.DOOM_MODE, FeatureScreenPage.LAST, OtherThingsToKnowScreen::new),

    YOU_ARENT_READY_FOR_THIS("you_arent_ready_for_this", FeatureScreenCategory.SECRET_DOOM_MODE, FeatureScreenPage.FIRST, YouArentReadyForThisScreen::new),
    DO_YOU_UNDERSTAND("do_you_understand", FeatureScreenCategory.SECRET_DOOM_MODE, DoYouUnderstandScreen::new),
    IM_READY("im_ready", FeatureScreenCategory.SECRET_DOOM_MODE, ImReadyScreen::new),
    EXPECT_THE_UNEXPECTED("expect_the_unexpected", FeatureScreenCategory.SECRET_DOOM_MODE, ExpectTheUnexpectedScreen::new),
    UM("um", FeatureScreenCategory.SECRET_DOOM_MODE, UmScreen::new),
    DOT_DOT_DOT("dot_dot_dot", FeatureScreenCategory.SECRET_DOOM_MODE, DotDotDotScreen::new),
    DOT_DOT_DOT_DOT("dot_dot_dot_dot", FeatureScreenCategory.SECRET_DOOM_MODE, DotDotDotDotScreen::new),
    ALL_SECRETS("all_secrets", FeatureScreenCategory.SECRET_DOOM_MODE, AllSecretsScreen::new),
    EYE_FEATURES("eye_features", FeatureScreenCategory.SECRET_DOOM_MODE, FeatureScreenPage.LAST, EyeFeaturesScreen::new);

    private final String key;
    private final FeatureScreenCategory category;
    private final FeatureScreenPage pageType;
    private final FeatureScreenFactory factory;

    /**
     * Creates a {@link FeatureScreen}.
     */
    FeaturePage(final String key, final FeatureScreenCategory category, FeatureScreenFactory factory) {
        this.key = key;
        this.category = category;
        this.pageType = FeatureScreenPage.DEFAULT;
        this.factory = factory;
    }

    /**
     * Creates a {@link FeatureScreen}, with a {@code key, category, and set page type}.
     */
    FeaturePage(final String key, final FeatureScreenCategory category, final FeatureScreenPage pageType, FeatureScreenFactory factory) {
        this.key = key;
        this.category = category;
        this.pageType = pageType;
        this.factory = factory;
    }

    /**
     * @return a newly nstantiated feature screen.
     */
    public FeatureScreen createScreen(Screen parent) {
        return factory.create(parent, this);
    }

    /**
     * @return the {@code key} for this feature screen.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @return the {@link FeatureScreenCategory} for this feature screen.
     */
    public FeatureScreenCategory getCategory() {
        return this.category;
    }

    /**
     * @return the {@link FeatureScreenPage} for this feature screen.
     */
    public FeatureScreenPage getPageType() {
        return this.pageType;
    }

    /**
     * @return the text file location for a feature screen.
     */
    public String getTextFileLocation() {
        return this.category.getPath() + "/" + this.key + ".txt";
    }

    /**
     * @return the first page for a screen category.
     */
    public static FeaturePage getFirstPage(FeatureScreenCategory category) {
        for (FeaturePage page : values()) {
            if (page.getCategory() == category) {
                return page;
            }
        }

        throw new IllegalStateException("No feature pages found for category: " + category);
    }
}