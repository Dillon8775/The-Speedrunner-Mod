package net.dillon.speedrunnermod.mixin.main.component;

import net.minecraft.component.type.DeathProtectionComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(DeathProtectionComponent.class)
public class DeathProtectionComponentMixin {
    @Unique
    private final List<StatusEffectInstance> effectsToAdd = new ArrayList<>();

    /**
     * Gets the players {@code current status effects,} and stores them to re-add after using totem.
     */
    @Inject(method = "applyDeathEffects", at = @At("HEAD"))
    private void dontRemovePositiveEffects(ItemStack stack, LivingEntity entity, CallbackInfo ci) {
        for (StatusEffectInstance statusEffectInstance : entity.getStatusEffects()) {
            StatusEffect effect = statusEffectInstance.getEffectType().value();
            if (effect.isBeneficial()) {
                this.effectsToAdd.add(statusEffectInstance);
            }
        }
    }

    /**
     * Re-applies all positive status effects to the player after using a totem.
     */
    @Inject(method = "applyDeathEffects", at = @At("TAIL"))
    private void applyPositiveEffects(ItemStack stack, LivingEntity entity, CallbackInfo ci) {
        for (StatusEffectInstance effect : this.effectsToAdd) {
            entity.addStatusEffect(effect);
        }
    }
}