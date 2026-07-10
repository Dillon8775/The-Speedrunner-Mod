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
    public static final TagKey<Item> COOLDOWN_ENCHANTMENT_ITEMS = createItemTag("cooldown_enchantment_items");
    public static final TagKey<Item> DRAGON_TOOL_MATERIALS = createItemTag("dragon_tool_materials");
    public static final TagKey<Item> DRAGON_PARTICLE_ITEMS = createItemTag("dragon_particle_items");
    public static final TagKey<Item> EXPERIENCE_BOTTLE_CRAFTABLES = createItemTag("experience_bottle_craftables");
    public static final TagKey<Item> FIREPROOF_BOATS = createItemTag("fireproof_boats");
    public static final TagKey<Item> FIREPROOF_CHEST_BOATS = createItemTag("fireproof_chest_boats");
    public static final TagKey<Item> FIREPROOF_ITEMS = createItemTag("fireproof_items");
    public static final TagKey<Item> FLESH = createItemTag("flesh");
    public static final TagKey<Item> GOLDEN_SPEEDRUNNER_TOOL_MATERIALS = createItemTag("golden_speedrunner_tool_materials");
    public static final TagKey<Item> SPEEDRUNNER_TOOL_MATERIALS = createItemTag("speedrunner_tool_materials");
    public static final TagKey<Item> GOLDEN_SHIELD_REPAIRABLE = createItemTag("golden_shield_repairable");
    public static final TagKey<Item> SPEEDRUNNER_SHIELD_REPAIRABLE = createItemTag("speedrunner_shield_repairable");
    public static final TagKey<Item> SPEEDRUNNER_ARMOR = createItemTag("speedrunner_armor");
    public static final TagKey<Item> SPEEDRUNNER_HARNESSES = createItemTag("speedrunner_harnesses");
    public static final TagKey<Item> SPEEDRUNNER_NAUTILUSES = createItemTag("speedrunner_nautiluses");
    public static final TagKey<Item> SPEEDRUNNERS_WORKBENCH_CONVERTABLE = createItemTag("speedrunners_workbench_convertable");
    public static final TagKey<Item> SPEEDRUNNERS_WORKBENCH_UPGRADEABLE = createItemTag("speedrunners_workbench_upgradeable");
    public static final TagKey<Item> IGNITABLES = createItemTag("ignitables");
    public static final TagKey<Item> THROWABLE_FIREBALLS = createItemTag("throwable_fireballs");
    public static final TagKey<Item> ENDER_EYE_DEATH_SOUND = createItemTag("ender_eye_death_sound_items");
    public static final TagKey<Item> FIRECHARGE_SOUND = createItemTag("firecharge_sound_items");
    public static final TagKey<Item> PURPLE_EYE_PARTICLES = createItemTag("purple_eye_particles");
    public static final TagKey<Item> SMOKE_EYE_PARTICLES = createItemTag("smoke_eye_particles");
    public static final TagKey<Item> BLUE_EYE_PARTICLES = createItemTag("blue_eye_particles");
    public static final TagKey<Item> PIGLIN_AWAKENER_CRAFTABLES = createItemTag("piglin_awakener_craftables");
    public static final TagKey<Item> SPEED_BOOTS = createItemTag("speed_boots");
    public static final TagKey<Item> STICKS = createItemTag("sticks");
    public static final TagKey<Item> SPEEDRUNNER_STICKS = createItemTag("speedrunner_sticks");
    public static final TagKey<Item> TOTEMS = createItemTag("totems");
    public static final TagKey<Item> UPGRADEABLE_GOLD = createItemTag("upgradable_gold");

    /**
     * Item tags specifically for advancement criterions.
     */
    public static class AdvancementCriterions {
        public static final TagKey<Item> EYE_OF_ANNUL = createAdvancementItemTag("eye_of_annul");
        public static final TagKey<Item> BLAZE_SPOTTER = createAdvancementItemTag("blaze_spotter");
        public static final TagKey<Item> DRAGONS_PEARL = createAdvancementItemTag("dragons_pearl");
        public static final TagKey<Item> DRAGONS_SWORD = createAdvancementItemTag("dragons_sword");
        public static final TagKey<Item> ENDER_THRUSTER = createAdvancementItemTag("ender_thruster");
        public static final TagKey<Item> INFERNO_EYE = createAdvancementItemTag("inferno_eye");
        public static final TagKey<Item> RAID_ERADICATOR = createAdvancementItemTag("raid_eradicator");
        public static final TagKey<Item> PIGLIN_AWAKENER = createAdvancementItemTag("piglin_awakener");
        public static final TagKey<Item> SPEEDRUNNER_FLINT_AND_STEEL = createAdvancementItemTag("speedrunner_flint_and_steel");
        public static final TagKey<Item> SPEEDRUNNERS_WORKBENCH = createAdvancementItemTag("speedrunners_workbench");
    }

    /**
     * Registers an {@code item tag.}
     */
    private static TagKey<Item> createItemTag(String path) {
        return TagKey.create(Registries.ITEM, ofSpeedrunnerMod(path));
    }

    /**
     * Registers an {@code item tag,} specifically for advancement unlocks.
     */
    private static TagKey<Item> createAdvancementItemTag(String path) {
        return TagKey.create(Registries.ITEM, ofSpeedrunnerMod("advancement_criterions/"+path));
    }

    /**
     * Initializes all Speedrunner Mod {@code item tags.}
     */
    public static void initializeItemTags() {
        SpeedrunnerMod.debug("Initialized item tags.");
    }
}