package net.dillon.speedrunnermod.mixin.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;

@Mixin(FoodProperties.class)
public class FoodPropertiesMixin {

    @Inject(method = "onConsume", at = @At("TAIL"))
    private void addFoodEffects(Level world, LivingEntity user, ItemStack stack, Consumable consumable, CallbackInfo ci) {
        if (common().general().betterFoods && user instanceof Player player && stack.is(Items.GOLDEN_CARROT)) {
             player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 0));
        }
    }
}