package net.dillon.speedrunnermod.component;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.util.TickCalculator;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.DeathProtection;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.ClearAllStatusEffectsConsumeEffect;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;
import static net.minecraft.world.item.component.Consumables.defaultFood;

/**
 * Different types of components for mod items.
 */
public class ModDataComponentTypes {
    public static final DataComponentType<TagKey<Structure>> LOCATING_STRUCTURE = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE, ofSpeedrunnerMod("locating_structure"), DataComponentType.<TagKey<Structure>>builder().persistent(TagKey.hashedCodec(Registries.STRUCTURE)).build());

    public static final DataComponentType<ItemStack> STORED_ITEMSTACK = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE, ofSpeedrunnerMod("stored_itemstack"), DataComponentType.<ItemStack>builder().persistent(ItemStack.OPTIONAL_CODEC).networkSynchronized(ItemStack.OPTIONAL_STREAM_CODEC).build());

    public static final DeathProtection SPEEDRUNNERS_TOTEM_EFFECTS = new DeathProtection(
            List.of(
                    new ClearAllStatusEffectsConsumeEffect(),
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.REGENERATION, 1800, 1),
                                    new MobEffectInstance(MobEffects.ABSORPTION, TickCalculator.minutes(1), 1),
                                    new MobEffectInstance(MobEffects.RESISTANCE, TickCalculator.seconds(30)),
                                    new MobEffectInstance(MobEffects.FIRE_RESISTANCE, TickCalculator.minutes(2)),
                                    new MobEffectInstance(MobEffects.STRENGTH, TickCalculator.seconds(15)),
                                    new MobEffectInstance(MobEffects.SPEED, TickCalculator.seconds(30)),
                                    new MobEffectInstance(MobEffects.SLOW_FALLING, TickCalculator.seconds(10))
                            )
                    )
            ));

    public static final Consumable SPEEDRUNNER_BULK = defaultFood()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0),
                                    new MobEffectInstance(MobEffects.WATER_BREATHING, 1200, 0),
                                    new MobEffectInstance(MobEffects.SPEED, 600, 0)
                            )
                    )
            )
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            new MobEffectInstance(MobEffects.HASTE, 500, 1), 0.5F
                    )
            )
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            new MobEffectInstance(MobEffects.REGENERATION, 200), 0.25F
                    )
            )
            .build();

    public static final Consumable ROTTEN_SPEEDRUNNER_BULK = defaultFood()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            new MobEffectInstance(MobEffects.HUNGER, 400, 0), 0.5F
                    )
            )
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            new MobEffectInstance(MobEffects.SLOWNESS, 300, 0), 0.1F
                    )
            )
            .build();

    /**
     * Initializes all speedrunner mod data components.
     */
    public static void initializeDataComponents() {
        SpeedrunnerMod.debug("Initialized data components.");
    }
}