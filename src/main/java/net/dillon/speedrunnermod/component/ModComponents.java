package net.dillon.speedrunnermod.component;

import com.mojang.serialization.Codec;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
import net.dillon.speedrunnermod.util.TickCalculator;
import net.minecraft.component.ComponentType;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.DeathProtectionComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.item.consume.ClearAllEffectsConsumeEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.ofSpeedrunnerMod;
import static net.minecraft.component.type.ConsumableComponents.food;

/**
 * Different types of components for mod items.
 */
public class ModComponents {
    @ChatGPT(Credit.FULL_CREDIT)
    public static final ComponentType<Boolean> DAMAGEABLE = Registry.register(
            Registries.DATA_COMPONENT_TYPE, ofSpeedrunnerMod("damageable"), ComponentType.<Boolean>builder().codec(Codec.BOOL).build());

    public static final DeathProtectionComponent TOTEM_SPEEDRUNNERS = new DeathProtectionComponent(
            List.of(
                    new ClearAllEffectsConsumeEffect(),
                    new ApplyEffectsConsumeEffect(
                            List.of(
                                    new StatusEffectInstance(StatusEffects.REGENERATION, 1800, 1),
                                    new StatusEffectInstance(StatusEffects.ABSORPTION, TickCalculator.minutes(1), 1),
                                    new StatusEffectInstance(StatusEffects.RESISTANCE, TickCalculator.seconds(30)),
                                    new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, TickCalculator.minutes(2)),
                                    new StatusEffectInstance(StatusEffects.STRENGTH, TickCalculator.seconds(15)),
                                    new StatusEffectInstance(StatusEffects.SPEED, TickCalculator.seconds(30)),
                                    new StatusEffectInstance(StatusEffects.SLOW_FALLING, TickCalculator.seconds(10))
                            )
                    )
            ));

    public static final ConsumableComponent SPEEDRUNNER_BULK = food()
            .consumeEffect(
                    new ApplyEffectsConsumeEffect(
                            List.of(
                                    new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 1200, 0),
                                    new StatusEffectInstance(StatusEffects.WATER_BREATHING, 1200, 0),
                                    new StatusEffectInstance(StatusEffects.SPEED, 600, 0)
                            )
                    )
            )
            .consumeEffect(
                    new ApplyEffectsConsumeEffect(
                            new StatusEffectInstance(StatusEffects.HASTE, 500, 1), 0.5F
                    )
            )
            .consumeEffect(
                    new ApplyEffectsConsumeEffect(
                            new StatusEffectInstance(StatusEffects.REGENERATION, 200), 0.25F
                    )
            )
            .build();

    public static final ConsumableComponent ROTTEN_SPEEDRUNNER_BULK = food()
            .consumeEffect(
                    new ApplyEffectsConsumeEffect(
                            new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F
                    )
            )
            .consumeEffect(
                    new ApplyEffectsConsumeEffect(
                            new StatusEffectInstance(StatusEffects.SLOWNESS, 300, 0), 0.1F
                    )
            )
            .build();

    public static void init() {
    }
}