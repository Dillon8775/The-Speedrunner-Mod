package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;

import static net.dillon.dillonlib.factory.Factories.createEnchantmentTag;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

public class ModEnchantmentTags {
    public static final TagKey<Enchantment> SPEEDRUNNER_ENCHANTMENTS = createEnchantmentTag(ofSpeedrunnerMod("speedrunner_enchantments"));
    public static final TagKey<Enchantment> ON_RANDOM_SPEEDRUNNER_LOOT = createEnchantmentTag(ofSpeedrunnerMod("on_random_speedrunner_loot"));
    public static final TagKey<Enchantment> RETIRED_SPEEDRUNNER_TRADES = createEnchantmentTag(ofSpeedrunnerMod("retired_speedrunner_trades"));
    public static final TagKey<Enchantment> WITHERED_ENCHANTMENTS = createEnchantmentTag(ofSpeedrunnerMod("withered_enchantments"));

    /**
     * Initializes all Speedrunner Mod {@code enchantment tags.}
     */
    public static void initializeEnchantmentTags() {
        SpeedrunnerMod.LOGGER.debug("Initialized enchantment tags.");
    }
}