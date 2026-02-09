package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

public class ModEnchantmentTags {
    public static TagKey<Enchantment> RETIRED_SPEEDRUNNER_TRADES = of("retired_speedrunner_trades");
    public static TagKey<Enchantment> WITHERED_ENCHANTMENTS = of("withered_enchantments");

    /**
     * Registers an {@code enchantment tag.}
     */
    private static TagKey<Enchantment> of(String path) {
        return TagKey.of(RegistryKeys.ENCHANTMENT, ofSpeedrunnerMod(path));
    }

    /**
     * Initializes all Speedrunner Mod {@code enchantment tags.}
     */
    public static void initializeEnchantmentTags() {
        SpeedrunnerMod.debug("Initialized enchantment tags.");
    }
}