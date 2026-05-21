package net.dillon.speedrunnermod.potion;

import net.dillon.speedrunnermod.effect.ModMobEffects;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.util.ModUtil;
import net.fabricmc.fabric.api.registry.FabricPotionBrewingBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Stores all speedrunner mod potions.
 */
public class ModPotions {
    public static final Holder<Potion> DRAGONS_AURA = registerPotion("dragons_aura", ModMobEffects.DRAGONS_AURA, ModUtil.minutesAsTicks(2), 0);
    public static final Holder<Potion> LONG_DRAGONS_AURA = registerPotion("long_dragons_aura", ModMobEffects.DRAGONS_AURA, ModUtil.minutesAsTicks(6), 0);

    /**
     * Registers a potion.
     */
    private static Holder<Potion> registerPotion(String name, Holder<MobEffect> effect, int duration, int amplifier) {
        return Registry.registerForHolder(BuiltInRegistries.POTION, ofSpeedrunnerMod(name),
                new Potion(name, new MobEffectInstance(effect, duration, amplifier)));
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