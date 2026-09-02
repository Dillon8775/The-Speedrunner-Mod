package net.dillon.speedrunnermod.component;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;

import static net.dillon.dillonlib.util.Arithmetics.M_asTick;

/**
 * Stores all speedrunner mod potions.
 */
public class ModPotions {
    public static final Holder<Potion> DRAGONS_AURA = registerModPotion(ModPotionIds.DRAGONS_AURA, new Potion("dragons_aura",
            new MobEffectInstance(ModMobEffects.DRAGONS_AURA, M_asTick(2), 0)));
    public static final Holder<Potion> LONG_DRAGONS_AURA = registerModPotion(ModPotionIds.LONG_DRAGONS_AURA, new Potion("long_dragons_aura",
            new MobEffectInstance(ModMobEffects.DRAGONS_AURA, M_asTick(6), 0)));
    public static final Holder<Potion> WITHERED = registerModPotion(ModPotionIds.WITHERED, new Potion("withered",
            new MobEffectInstance(ModMobEffects.WITHERED, M_asTick(3), 0)));
    public static final Holder<Potion> LONG_WITHERED = registerModPotion(ModPotionIds.LONG_WITHERED, new Potion("long_withered",
            new MobEffectInstance(ModMobEffects.WITHERED, M_asTick(8), 0)));
    public static final Holder<Potion> STRONG_WITHERED = registerModPotion(ModPotionIds.STRONG_WITHERED, new Potion("strong_withered",
            new MobEffectInstance(ModMobEffects.WITHERED, 1800, 1)));
    public static final Holder<Potion> STRONG_LUCK = registerModPotion(ModPotionIds.STRONG_LUCK, new Potion("strong_luck",
            new MobEffectInstance(MobEffects.LUCK, M_asTick(2), 1)));

    /**
     * Registers a potion.
     */
    private static Holder<Potion> registerModPotion(final ResourceKey<Potion> key, final Potion potion) {
        return Registry.registerForHolder(BuiltInRegistries.POTION, key, potion);
    }

    /**
     * Initializes all potions.
     */
    public static void initializePotions() {
        SpeedrunnerMod.LOGGER.debug("Initialized potions.");
    }
}