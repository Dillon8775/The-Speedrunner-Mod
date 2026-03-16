package net.dillon.speedrunnermod.enchantment;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code enchantments.}
 */
public class ModEnchantments {
    // For boots, grants the player the speed effect, amplifier increased for each level
    public static final ResourceKey<Enchantment> DASH = register("dash");
    // For items that have a "cooldown" (shields, ender pearls, chorus fruit, etc.), this lowers the cooldown on those items
    public static final ResourceKey<Enchantment> COOLDOWN = register("cooldown");
    // Inflicts target with wither effect for 3-7 seconds (based on level), and gives increased chance of wither skeleton skulls
    public static final ResourceKey<Enchantment> WITHERED = register("withered");

    /**
     * Registers a {@code enchantment}
     */
    private static ResourceKey<Enchantment> register(String path) {
        return ResourceKey.create(Registries.ENCHANTMENT, ofSpeedrunnerMod(path));
    }

    /**
     * Initializes all Speedrunner Mod {@code enchantments.}
     */
    public static void initializeEnchantments() {
        SpeedrunnerMod.debug("Initialized enchantments.");
    }
}