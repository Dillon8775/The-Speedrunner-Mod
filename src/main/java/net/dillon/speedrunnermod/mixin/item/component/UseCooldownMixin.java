package net.dillon.speedrunnermod.mixin.item.component;

import net.dillon.speedrunnermod.helper.ModComponentHelper;
import net.dillon.speedrunnermod.item.core.ModItems;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.UseCooldown;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(UseCooldown.class)
public class UseCooldownMixin {

    /**
     * Applies the {@code cooldown enchantment} to certain items.
     */
    @Inject(method = "apply", at = @At("TAIL"))
    private void implementCooldownEnchantmentChorusFruit(ItemStack stack, LivingEntity user, CallbackInfo ci) {
        if (!(user instanceof Player player) || !stack.is(ModItemTags.COOLDOWN_ENCHANTMENT_ITEMS) || stack.is(ModItems.INFINI_PEARL)) {
            return;
        }

        ModComponentHelper.applyNewItemCooldown(player, stack);
    }
}