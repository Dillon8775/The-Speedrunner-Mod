package net.dillon.speedrunnermod.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code enchantments.}
 */
public class ModEnchantments {
    // For boots, grants the player the speed effect, amplifier increased for each level
    public static final RegistryKey<Enchantment> DASH = register("dash");
    // For items that have a "cooldown" (shields, ender pearls, chorus fruit, etc.), this lowers the cooldown on those items
    public static final RegistryKey<Enchantment> COOLDOWN = register("cooldown");

    /**
     * Registers a {@code enchantment}
     */
    private static RegistryKey<Enchantment> register(String path) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, ofSpeedrunnerMod(path));
    }

    /**
     * Initializes all Speedrunner Mod {@code enchantments.}
     */
    public static void initializeEnchantments() {}
}