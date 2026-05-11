package net.dillon.speedrunnermod.mixin.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.TeleportRandomlyConsumeEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(Consumables.class)
public abstract class ConsumablesMixin {
    @Shadow
    public static final Consumable CHORUS_FRUIT, ENCHANTED_GOLDEN_APPLE, GOLDEN_APPLE;

    static {
        CHORUS_FRUIT = Consumables.defaultFood()
                .onConsume(new TeleportRandomlyConsumeEffect()
                )
                .onConsume(
                        new ApplyStatusEffectsConsumeEffect(
                                new MobEffectInstance(MobEffects.REGENERATION, 100, 0)
                        )
                )

                .build();
        ENCHANTED_GOLDEN_APPLE = Consumables.defaultFood()
                .onConsume(
                        new ApplyStatusEffectsConsumeEffect(
                                List.of(
                                        new MobEffectInstance(MobEffects.REGENERATION, 600, 2),
                                        new MobEffectInstance(MobEffects.RESISTANCE, 6000, 0),
                                        new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0),
                                        new MobEffectInstance(MobEffects.ABSORPTION, 2400, 3)
                                )
                        )
                )
                .build();
        GOLDEN_APPLE = Consumables.defaultFood()
                .onConsume(
                        new ApplyStatusEffectsConsumeEffect(
                                List.of(
                                        new MobEffectInstance(MobEffects.REGENERATION, 200, 1),
                                        new MobEffectInstance(MobEffects.ABSORPTION, 2400, 0)
                                )
                        )
                )
                .build();
    }
}