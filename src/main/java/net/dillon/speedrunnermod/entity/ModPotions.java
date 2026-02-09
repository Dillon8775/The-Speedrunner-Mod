package net.dillon.speedrunnermod.entity;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.util.ModUtil;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Stores all speedrunner mod potions.
 */
public class ModPotions {
    public static final RegistryEntry<Potion> DRAGONS_AURA = registerPotion("dragons_aura", ModStatusEffects.DRAGONS_AURA, ModUtil.minutesAsTicks(4), 0);
    public static final RegistryEntry<Potion> LONG_DRAGONS_AURA = registerPotion("long_dragons_aura", ModStatusEffects.DRAGONS_AURA, ModUtil.minutesAsTicks(8), 0);

    /**
     * Registers a potion.
     */
    private static RegistryEntry<Potion> registerPotion(String name, RegistryEntry<StatusEffect> effect, int duration, int amplifier) {
        return Registry.registerReference(Registries.POTION, ofSpeedrunnerMod(name),
                new Potion(name, new StatusEffectInstance(effect, duration, amplifier)));
    }

    /**
     * Registers potion and all potion recipes.
     */
    public static void registerPotions() {
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(Potions.WATER, ModItems.ENDER_MATTER, DRAGONS_AURA);
            builder.registerPotionRecipe(DRAGONS_AURA, Items.REDSTONE, LONG_DRAGONS_AURA);
        });

        SpeedrunnerMod.debug("Registered potions.");
    }
}