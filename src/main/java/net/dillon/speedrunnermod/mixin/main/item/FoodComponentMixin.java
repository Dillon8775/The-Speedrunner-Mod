package net.dillon.speedrunnermod.mixin.main.item;

import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(FoodComponent.class)
public class FoodComponentMixin {

    @Inject(method = "onConsume", at = @At("TAIL"))
    private void addFoodEffects(World world, LivingEntity user, ItemStack stack, ConsumableComponent consumable, CallbackInfo ci) {
        if (options().main.betterFoods && user instanceof PlayerEntity player && stack.isOf(Items.GOLDEN_CARROT)) {
             player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 0));
        }
    }
}