package net.dillon.speedrunnermod.potion;

import net.dillon.speedrunnermod.effect.ModMobEffects;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.util.ModUtil;
import net.fabricmc.fabric.api.registry.FabricPotionBrewingBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;

/**
 * Stores all speedrunner mod potions.
 */
public class ModPotions {
    public static final Holder<Potion> DRAGONS_AURA = registerModPotion(ModPotionIds.DRAGONS_AURA, new Potion("dragons_aura",
            new MobEffectInstance(ModMobEffects.DRAGONS_AURA, ModUtil.minutesAsTicks(2), 0)));
    public static final Holder<Potion> LONG_DRAGONS_AURA = registerModPotion(ModPotionIds.LONG_DRAGONS_AURA, new Potion("long_dragons_aura",
            new MobEffectInstance(ModMobEffects.DRAGONS_AURA, ModUtil.minutesAsTicks(6), 0)));

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
        });

        SpeedrunnerMod.debug("Registered potions.");
    }
}