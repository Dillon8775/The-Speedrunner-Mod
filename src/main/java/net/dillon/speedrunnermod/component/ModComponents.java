package net.dillon.speedrunnermod.component;

import net.dillon.speedrunnermod.util.TickCalculator;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.DeathProtectionComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.item.consume.ClearAllEffectsConsumeEffect;

import java.util.List;

import static net.minecraft.component.type.ConsumableComponents.food;

/**
 * Different types of components for mod items.
 */
public class ModComponents {
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
                                    new StatusEffectInstance(StatusEffects.SLOW_FALLING, TickCalculator.seconds(30))
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
}