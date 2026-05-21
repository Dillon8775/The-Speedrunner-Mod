package net.dillon.speedrunnermod.effect;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Stores all speedrunner mod status effects.
 */
public class ModMobEffects {
    public static final Holder<MobEffect> DRAGONS_AURA = registerStatusEffect("dragons_aura",
            new DragonsAuraEffect(MobEffectCategory.BENEFICIAL, 5965444));

    /**
     * Registers a status effect.
     */
    private static Holder<MobEffect> registerStatusEffect(String name, MobEffect statusEffect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, ofSpeedrunnerMod(name), statusEffect);
    }

    public static void registerStatusEffects() {
        SpeedrunnerMod.debug("Registered potions.");
    }
}