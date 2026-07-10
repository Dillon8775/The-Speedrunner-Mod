package net.dillon.speedrunnermod.mixin.item.enchantment;

import net.dillon.speedrunnermod.component.ModAttributes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.effects.ApplyExhaustion;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ApplyExhaustion.class)
public class ApplyExhaustionMixin {

    /**
     * Prevents Lunge from draining lots of hunger when used on a speedrunner spear.
     */
    @Inject(method = "apply", at = @At("HEAD"), cancellable = true)
    private void cancelExhaustionForSpeedrunnerSpear(ServerLevel serverLevel, int enchantmentLevel, EnchantedItemInUse enchantedItem, Entity entity, Vec3 position, CallbackInfo ci) {
        if (!(entity instanceof Player player)) {
            return;
        }

        int level = EnchantmentHelper.getItemEnchantmentLevel(serverLevel.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LUNGE), enchantedItem.itemStack());
        if (level <= 0) {
            return;
        }

        float additionalExhaustion = (float)player.getAttributeValue(ModAttributes.BONUS_SPEAR_LUNGE_EXHAUSTION);
        if (additionalExhaustion == 0.0F) {
            return;
        }
        float finalExhaustion = (Math.abs(additionalExhaustion / 3.5F)) * 10;
        player.causeFoodExhaustion(finalExhaustion + ((float) level / 10));
        ci.cancel();
    }
}