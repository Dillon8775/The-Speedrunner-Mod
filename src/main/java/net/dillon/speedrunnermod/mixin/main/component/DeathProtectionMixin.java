package net.dillon.speedrunnermod.mixin.main.component;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DeathProtection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(DeathProtection.class)
public class DeathProtectionMixin {
    @Unique
    private final List<MobEffectInstance> effectsToAdd = new ArrayList<>();

    /**
     * Gets the players {@code current status effects,} and stores them to re-add after using totem.
     */
    @Inject(method = "applyEffects", at = @At("HEAD"))
    private void dontRemovePositiveEffects(ItemStack stack, LivingEntity entity, CallbackInfo ci) {
        for (MobEffectInstance statusEffectInstance : entity.getActiveEffects()) {
            MobEffect effect = statusEffectInstance.getEffect().value();
            if (effect.isBeneficial()) {
                this.effectsToAdd.add(statusEffectInstance);
            }
        }
    }

    /**
     * Re-applies all positive status effects to the player after using a totem.
     */
    @Inject(method = "applyEffects", at = @At("TAIL"))
    private void applyPositiveEffects(ItemStack stack, LivingEntity entity, CallbackInfo ci) {
        for (MobEffectInstance effect : this.effectsToAdd) {
            entity.addEffect(effect);
        }
    }
}