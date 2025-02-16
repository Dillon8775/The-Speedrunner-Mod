package net.dillon.speedrunnermod.mixin.main.item;

import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.util.ItemUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.component.type.UseCooldownComponent.class)
public abstract class UseCooldownComponent {

    @Shadow public abstract int getCooldownTicks();

    @Inject(method = "set", at = @At("TAIL"))
    private void set(ItemStack stack, LivingEntity user, CallbackInfo ci) {
        if (user instanceof PlayerEntity player && stack.isOf(Items.CHORUS_FRUIT)) {
            int coolEnchantment = EnchantmentHelper.getEquipmentLevel(ItemUtil.entityEnchantment(user, ModEnchantments.COOLDOWN), user);
            int cooldown = coolEnchantment > 3 ? 0 : coolEnchantment == 3 ? 5 : coolEnchantment == 2 ? 10 : coolEnchantment == 1 ? 15 : 20;
            player.getItemCooldownManager().set(stack, this.getCooldownTicks() * cooldown);
        }
    }
}