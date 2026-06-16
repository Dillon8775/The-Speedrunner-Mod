package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code item tags.}
 */
public class ModItemTags {
    public static TagKey<Item> COOLDOWN_ENCHANTMENT_ITEMS = of("cooldown_enchantment_items");
    public static TagKey<Item> DOOM_STONE_SAFE_TOOLS = of("doom_stone_safe_tools");
    public static TagKey<Item> DRAGON_TOOL_MATERIALS = of("dragon_tool_materials");
    public static TagKey<Item> EXPERIENCE_BOTTLE_CRAFTABLES = of("experience_bottle_craftables");
    public static TagKey<Item> FASTER_BOATS = of("faster_boats");
    public static TagKey<Item> FASTER_CHEST_BOATS = of("faster_chest_boats");
    public static TagKey<Item> FLESH = of("flesh");
    public static TagKey<Item> FIREPROOF_BOATS = of("fireproof_boats");
    public static TagKey<Item> FIREPROOF_CHEST_BOATS = of("fireproof_chest_boats");
    public static TagKey<Item> FIREPROOF_ITEMS = of("fireproof_items");
    public static TagKey<Item> GOLDEN_FOOD_ITEMS = of("golden_food_items");
    public static TagKey<Item> GOLDEN_SPEEDRUNNER_ARMOR = of("golden_speedrunner_armor");
    public static TagKey<Item> GOLDEN_SPEEDRUNNER_TOOL_MATERIALS = of("golden_speedrunner_tool_materials");
    public static TagKey<Item> IGNITABLES = of("ignitables");
    public static TagKey<Item> INCREASED_LUNGE_MOMENTUM = of("increased_lunge_momentum");
    public static TagKey<Item> PIGLIN_AWAKENER_CRAFTABLES = of("piglin_awakener_craftables");
    public static TagKey<Item> SCULK_SENSOR_SAFE_BOOTS = of("sculk_sensor_safe_boots");
    public static TagKey<Item> SPEED_BOOTS = of("speed_boots");
    public static TagKey<Item> SPEEDRUNNER_ARMOR = of("speedrunner_armor");
    public static TagKey<Item> SPEEDRUNNER_SHIELD_REPAIRABLE = of("speedrunner_shield_repairable");
    public static TagKey<Item> SPEEDRUNNER_TOOL_MATERIALS = of("speedrunner_tool_materials");
    public static TagKey<Item> SPEEDRUNNER_TOOLS = of("speedrunner_tools");
    public static TagKey<Item> SPEEDRUNNER_SWORDS = of("speedrunner_swords");
    public static TagKey<Item> SPEEDRUNNER_HARNESSES = of("speedrunner_harnesses");
    public static TagKey<Item> SPEEDRUNNER_NAUTILUSES = of("speedrunner_nautiluses");
    public static TagKey<Item> SPEEDRUNNERS_WORKBENCH_CONVERTABLE = of("speedrunners_workbench_convertable");
    public static TagKey<Item> SPEEDRUNNERS_WORKBENCH_UPGRADEABLE = of("speedrunners_workbench_upgradeable");
    public static TagKey<Item> STATE_OF_THE_ART_ITEMS = of("state_of_the_art_items");
    public static TagKey<Item> STICKS = of("sticks");
    public static TagKey<Item> SPEEDRUNNER_STICKS = of("speedrunner_sticks");
    public static TagKey<Item> TOTEMS = of("totems");
    public static TagKey<Item> UPGRADEABLE_GOLD = of("upgradable_gold");

    /**
     * Item tags specifically for advancement criterions.
     */
    public static class AdvancementCriterions {
        public static TagKey<Item> EYE_OF_ANNUL = ofAdvancement("eye_of_annul");
        public static TagKey<Item> BLAZE_SPOTTER = ofAdvancement("blaze_spotter");
        public static TagKey<Item> DRAGONS_PEARL = ofAdvancement("dragons_pearl");
        public static TagKey<Item> DRAGONS_SWORD = ofAdvancement("dragons_sword");
        public static TagKey<Item> ENDER_THRUSTER = ofAdvancement("ender_thruster");
        public static TagKey<Item> INFERNO_EYE = ofAdvancement("inferno_eye");
        public static TagKey<Item> RAID_ERADICATOR = ofAdvancement("raid_eradicator");
        public static TagKey<Item> PIGLIN_AWAKENER = ofAdvancement("piglin_awakener");
        public static TagKey<Item> SPEEDRUNNER_FLINT_AND_STEEL = ofAdvancement("speedrunner_flint_and_steel");
        public static TagKey<Item> SPEEDRUNNERS_WORKBENCH = ofAdvancement("speedrunners_workbench");
    }

    /**
     * Item tags that also have a block tag.
     */
    public static class Block {
        public static TagKey<Item> DEAD_SPEEDRUNNER_LOGS = of("dead_speedrunner_logs");
        public static TagKey<Item> DOOM_LOGS = of("doom_logs");
        public static TagKey<Item> EXPERIENCE_ORES = of("experience_ores");
        public static TagKey<Item> IGNEOUS_ORES = of("igneous_ores");
        public static TagKey<Item> IRON_BLOCKS = of("iron_blocks");
        public static TagKey<Item> NETHER_PORTAL_BASE_BLOCKS = of("nether_portal_base_blocks");
        public static TagKey<Item> SPEEDRUNNER_FUELS = of("speedrunner_fuels");
        public static TagKey<Item> SPEEDRUNNER_LOGS = of("speedrunner_logs");
        public static TagKey<Item> SPEEDRUNNER_ORES = of("speedrunner_ores");
        public static TagKey<Item> SPEEDRUNNER_PLANKS = of("speedrunner_planks");
        public static TagKey<Item> SPEEDRUNNER_SAPLING_PLACEABLES = of("speedrunner_sapling_placeables");
    }

    /**
     * Registers an {@code item tag.}
     */
    private static TagKey<Item> of(String path) {
        return TagKey.create(Registries.ITEM, ofSpeedrunnerMod(path));
    }

    /**
     * Registers an {@code item tag,} specifically for advancement unlocks.
     */
    private static TagKey<Item> ofAdvancement(String path) {
        return TagKey.create(Registries.ITEM, ofSpeedrunnerMod("advancement_criterions/"+path));
    }

    /**
     * Initializes all Speedrunner Mod {@code item tags.}
     */
    public static void initializeItemTags() {
        SpeedrunnerMod.debug("Initialized item tags.");
    }
}