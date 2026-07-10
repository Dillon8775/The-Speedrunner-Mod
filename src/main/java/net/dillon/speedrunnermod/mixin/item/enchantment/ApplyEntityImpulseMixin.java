package net.dillon.speedrunnermod.mixin.item.enchantment;

import net.dillon.speedrunnermod.component.ModAttributes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.effects.ApplyEntityImpulse;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ApplyEntityImpulse.class)
public class ApplyEntityImpulseMixin {

    /**
     * Gives the player an increased boost w/ the lunge enchantment with speedrunner spears.
     */
    @Inject(method = "apply", at = @At("TAIL"))
    private void addSlightlyMoreLungeBoost(ServerLevel serverLevel, int enchantmentLevel, EnchantedItemInUse enchantedItem, Entity entity, Vec3 position, CallbackInfo ci) {
        if (!(entity instanceof LivingEntity living)) {
            return;
        }

        if (EnchantmentHelper.getItemEnchantmentLevel(serverLevel.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LUNGE), enchantedItem.itemStack()) <= 0) {
            return;
        }

        float additionalLunge = (float)living.getAttributeValue(ModAttributes.BONUS_SPEAR_LUNGE_MOMENTUM);
        if (!(additionalLunge > 0.0F)) {
            return;
        }

        ApplyEntityImpulse self = (ApplyEntityImpulse) (Object) this;
        Vec3 extraImpulse = entity.getLookAngle()
                .addLocalCoordinates(self.direction())
                .multiply(self.coordinateScale())
                .scale(additionalLunge * enchantmentLevel);
        entity.addDeltaMovement(extraImpulse);
        entity.hurtMarked = true;
        entity.needsSync = true;
    }
}