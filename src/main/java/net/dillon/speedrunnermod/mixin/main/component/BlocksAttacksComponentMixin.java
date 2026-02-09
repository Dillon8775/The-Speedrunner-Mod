package net.dillon.speedrunnermod.mixin.main.component;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.item.SpeedrunnerShieldItem;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.component.type.BlocksAttacksComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BlocksAttacksComponent.class)
public class BlocksAttacksComponentMixin {

    /**
     * Applies the correct {@code shield cooldown,} with the cooldown enchantment.
     */
    @ModifyVariable(method = "applyShieldCooldown", at = @At("STORE"), ordinal = 0)
    private int modifyCooldown(int originalCooldown, ServerWorld world, LivingEntity affectedEntity, float cooldownSeconds, ItemStack stack) {
        if (affectedEntity instanceof PlayerEntity player) {
            int i = stack.isOf(ModItems.SPEEDRUNNER_SHIELD) ? (int)(ModUtil.getItemCooldown(player) / SpeedrunnerShieldItem.COOLDOWN_DIVIDER) : ModUtil.getItemCooldown(player);
            return i * (int)cooldownSeconds;
        }

        return originalCooldown;
    }
}