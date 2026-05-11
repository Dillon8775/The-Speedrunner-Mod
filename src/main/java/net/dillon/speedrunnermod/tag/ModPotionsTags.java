package net.dillon.speedrunnermod.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.alchemy.Potion;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod potion tags.
 */
public class ModPotionsTags {
    public static final TagKey<Potion> RETIRED_SPEEDRUNNER_POTIONS = create("retired_speedrunner_potions");

    private static TagKey<Potion> create(String id) {
        return TagKey.create(Registries.POTION, ofSpeedrunnerMod(id));
    }
}