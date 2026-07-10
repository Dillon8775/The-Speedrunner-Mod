package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

public class ModEnchantmentTags {
    public static final TagKey<Enchantment> SPEEDRUNNER_ENCHANTMENTS = createEnchantmentTag("speedrunner_enchantments");
    public static final TagKey<Enchantment> ON_RANDOM_SPEEDRUNNER_LOOT = createEnchantmentTag("on_random_speedrunner_loot");
    public static final TagKey<Enchantment> RETIRED_SPEEDRUNNER_TRADES = createEnchantmentTag("retired_speedrunner_trades");
    public static final TagKey<Enchantment> WITHERED_ENCHANTMENTS = createEnchantmentTag("withered_enchantments");

    /**
     * Registers an {@code enchantment tag.}
     */
    private static TagKey<Enchantment> createEnchantmentTag(String path) {
        return TagKey.create(Registries.ENCHANTMENT, ofSpeedrunnerMod(path));
    }

    /**
     * Initializes all Speedrunner Mod {@code enchantment tags.}
     */
    public static void initializeEnchantmentTags() {
        SpeedrunnerMod.debug("Initialized enchantment tags.");
    }
}