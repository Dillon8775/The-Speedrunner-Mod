package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.alchemy.Potion;

import static net.dillon.dillonlib.factory.Factories.createPotionTag;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod potion tags.
 */
public class ModPotionsTags {
    public static final TagKey<Potion> RETIRED_SPEEDRUNNER_ARROW_EFFECTS = createPotionTag(ofSpeedrunnerMod("retired_speedrunner_arrow_effects"));
    public static final TagKey<Potion> RETIRED_SPEEDRUNNER_POTION_EFFECTS = createPotionTag(ofSpeedrunnerMod("retired_speedrunner_potion_effects"));
    public static final TagKey<Potion> DOOM_BLOCK_POTIONS = createPotionTag(ofSpeedrunnerMod("doom_block_potions"));

    /**
     * Initializes all Speedrunner Mod {@code potion tags.}
     */
    public static void initializePotionTags() {
        SpeedrunnerMod.LOGGER.debug("Initialized potion tags.");
    }
}