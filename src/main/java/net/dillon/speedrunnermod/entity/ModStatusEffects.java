package net.dillon.speedrunnermod.entity;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Stores all speedrunner mod status effects.
 */
public class ModStatusEffects {
    public static final RegistryEntry<StatusEffect> DRAGONS_AURA = registerStatusEffect("dragons_aura",
            new DragonsAuraEffect(StatusEffectCategory.BENEFICIAL, 5965444));

    /**
     * Registers a status effect.
     */
    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, ofSpeedrunnerMod(name), statusEffect);
    }

    public static void registerStatusEffects() {
        SpeedrunnerMod.debug("Registered potions.");
    }
}