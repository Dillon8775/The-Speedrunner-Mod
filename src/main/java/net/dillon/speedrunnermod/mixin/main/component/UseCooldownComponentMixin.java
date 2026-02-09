package net.dillon.speedrunnermod.mixin.main.component;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.component.type.UseCooldownComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(UseCooldownComponent.class)
public class UseCooldownComponentMixin {

    /**
     * Applies the {@code cooldown enchantment} to certain items.
     */
    @Inject(method = "set", at = @At("TAIL"))
    private void implementCooldownEnchantmentChorusFruit(ItemStack stack, LivingEntity user, CallbackInfo ci) {
        if (user instanceof PlayerEntity player && stack.isIn(ModItemTags.COOLDOWN_ENCHANTMENT_ITEMS) && !stack.isOf(ModItems.INFINI_PEARL)) {
            ModUtil.applyItemCooldown(player, stack);
        }
    }
}