package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

public class ModEnchantmentTags {
    public static TagKey<Enchantment> ON_RANDOM_SPEEDRUNNER_LOOT = of("on_random_speedrunner_loot");
    public static TagKey<Enchantment> RETIRED_SPEEDRUNNER_TRADES = of("retired_speedrunner_trades");
    public static TagKey<Enchantment> WITHERED_ENCHANTMENTS = of("withered_enchantments");

    /**
     * Registers an {@code enchantment tag.}
     */
    private static TagKey<Enchantment> of(String path) {
        return TagKey.create(Registries.ENCHANTMENT, ofSpeedrunnerMod(path));
    }

    /**
     * Initializes all Speedrunner Mod {@code enchantment tags.}
     */
    public static void initializeEnchantmentTags() {
        SpeedrunnerMod.debug("Initialized enchantment tags.");
    }
}