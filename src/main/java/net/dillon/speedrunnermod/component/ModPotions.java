package net.dillon.speedrunnermod.component;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.util.TickCalculator;
import net.fabricmc.fabric.api.registry.FabricPotionBrewingBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;

/**
 * Stores all speedrunner mod potions.
 */
public class ModPotions {
    public static final Holder<Potion> DRAGONS_AURA = registerModPotion(ModPotionIds.DRAGONS_AURA, new Potion("dragons_aura",
            new MobEffectInstance(ModMobEffects.DRAGONS_AURA, TickCalculator.minutes(2), 0)));
    public static final Holder<Potion> LONG_DRAGONS_AURA = registerModPotion(ModPotionIds.LONG_DRAGONS_AURA, new Potion("long_dragons_aura",
            new MobEffectInstance(ModMobEffects.DRAGONS_AURA, TickCalculator.minutes(6), 0)));
    public static final Holder<Potion> WITHERED = registerModPotion(ModPotionIds.WITHERED, new Potion("withered",
            new MobEffectInstance(ModMobEffects.WITHERED, TickCalculator.minutes(3), 0)));
    public static final Holder<Potion> LONG_WITHERED = registerModPotion(ModPotionIds.LONG_WITHERED, new Potion("long_withered",
            new MobEffectInstance(ModMobEffects.WITHERED, TickCalculator.minutes(8), 0)));
    public static final Holder<Potion> STRONG_WITHERED = registerModPotion(ModPotionIds.STRONG_WITHERED, new Potion("strong_withered",
            new MobEffectInstance(ModMobEffects.WITHERED, 1800, 1)));
    public static final Holder<Potion> STRONG_LUCK = registerModPotion(ModPotionIds.STRONG_LUCK, new Potion("strong_luck",
            new MobEffectInstance(MobEffects.LUCK, TickCalculator.minutes(2), 1)));

    /**
     * Registers a potion.
     */
    private static Holder<Potion> registerModPotion(final ResourceKey<Potion> key, final Potion potion) {
        return Registry.registerForHolder(BuiltInRegistries.POTION, key, potion);
    }

    /**
     * Registers potion and all potion recipes.
     */
    public static void registerPotions() {
        FabricPotionBrewingBuilder.BUILD.register(builder -> {
            builder.addMix(Potions.WATER, ModItems.ENDER_MATTER, DRAGONS_AURA);
            builder.addMix(DRAGONS_AURA, Items.REDSTONE, LONG_DRAGONS_AURA);

            builder.addMix(Potions.AWKWARD, Items.WITHER_SKELETON_SKULL, WITHERED);
            builder.addMix(Potions.AWKWARD, Items.WITHER_ROSE, WITHERED);
            builder.addMix(WITHERED, Items.REDSTONE, LONG_WITHERED);
            builder.addMix(WITHERED, Items.GLOWSTONE_DUST, STRONG_WITHERED);

            builder.addMix(Potions.WATER, Items.LILY_PAD, Potions.LUCK);
            builder.addMix(Potions.LUCK, Items.GLOWSTONE_DUST, STRONG_LUCK);
        });

        SpeedrunnerMod.debug("Registered potions.");
    }
}