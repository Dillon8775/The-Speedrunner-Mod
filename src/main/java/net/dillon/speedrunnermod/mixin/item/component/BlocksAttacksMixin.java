package net.dillon.speedrunnermod.mixin.item.component;

import net.dillon.speedrunnermod.helper.ModComponentHelper;
import net.dillon.speedrunnermod.item.equipment.ModShieldItem;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlocksAttacks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BlocksAttacks.class)
public class BlocksAttacksMixin {

    /**
     * Applies the correct {@code shield cooldown}, with the cooldown enchantment.
     */
    @ModifyVariable(method = "disable", at = @At("STORE"), name = "cooldownTicks")
    private int modifyCooldown(int cooldownTicks, ServerLevel level, LivingEntity user, float baseSeconds, ItemStack blockingWith) {
        if (!(user instanceof Player player)) {
            return cooldownTicks;
        }

        int i = blockingWith.getItem() instanceof ModShieldItem modShieldItem ? (int)(ModComponentHelper.getItemCooldown(blockingWith, player) / modShieldItem.getCooldownDivider()) :
                blockingWith.getItem() instanceof ModShieldItem modShieldItem ? (int)(((float) ModComponentHelper.getItemCooldown(blockingWith, player) / 2.5) / modShieldItem.getCooldownDivider()) :
                        ModComponentHelper.getItemCooldown(blockingWith, player);

        return i * (int) baseSeconds;
    }
}