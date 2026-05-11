package net.dillon.speedrunnermod.mixin.item.component;

import net.dillon.speedrunnermod.item.GoldenShieldItem;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.item.SpeedrunnerShieldItem;
import net.dillon.speedrunnermod.util.ModUtil;
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
     * Applies the correct {@code shield cooldown,} with the cooldown enchantment.
     */
    @ModifyVariable(method = "disable", at = @At("STORE"), ordinal = 0)
    private int modifyCooldown(int originalCooldown, ServerLevel world, LivingEntity affectedEntity, float cooldownSeconds, ItemStack stack) {
        if (affectedEntity instanceof Player player) {
            int i = stack.is(ModItems.SPEEDRUNNER_SHIELD) ? (int)(ModUtil.getItemCooldown(player) / SpeedrunnerShieldItem.COOLDOWN_DIVIDER) :
                    stack.is(ModItems.GOLDEN_SHIELD) ? (int)(((float) ModUtil.getItemCooldown(player) / 2.5) / GoldenShieldItem.COOLDOWN_DIVIDER) :
                    ModUtil.getItemCooldown(player);
            return i * (int)cooldownSeconds;
        }

        return originalCooldown;
    }
}