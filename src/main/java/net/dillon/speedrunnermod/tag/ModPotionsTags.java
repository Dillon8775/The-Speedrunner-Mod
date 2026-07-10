package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.alchemy.Potion;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod potion tags.
 */
public class ModPotionsTags {
    public static final TagKey<Potion> RETIRED_SPEEDRUNNER_ARROW_EFFECTS = createPotionTag("retired_speedrunner_arrow_effects");
    public static final TagKey<Potion> RETIRED_SPEEDRUNNER_POTION_EFFECTS = createPotionTag("retired_speedrunner_potion_effects");
    public static final TagKey<Potion> DOOM_BLOCK_POTIONS = createPotionTag("doom_block_potions");

    /**
     * Registers a {@code potion tag.}
     */
    private static TagKey<Potion> createPotionTag(String id) {
        return TagKey.create(Registries.POTION, ofSpeedrunnerMod(id));
    }

    /**
     * Initializes all Speedrunner Mod {@code potion tags.}
     */
    public static void initializePotionTags() {
        SpeedrunnerMod.debug("Initialized potion tags.");
    }
}