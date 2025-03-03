package net.dillon.speedrunnermod.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * Better than iron, worse than diamond, deals more damage to withers and giants.
 */
public class SpeedrunnerSwordItem extends SwordItem {
    private static int attackDamage;

    public SpeedrunnerSwordItem(int attackDamage, boolean golden, Settings settings) {
        super(!golden ? ModToolMaterials.SPEEDRUNNER_SWORD_PICKAXE : ModToolMaterials.GOLDEN_SPEEDRUNNER, attackDamage, -2.4F, settings);
        SpeedrunnerSwordItem.attackDamage = attackDamage;
    }

    /**
     * Deals more damage to withers, and giants under certain conditions.
     */
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity) {
            if (target instanceof WitherEntity) {
                target.serverDamage(attacker.getDamageSources().playerAttack((PlayerEntity)attacker), getAttackDamage() * 4.45F);
            } else if (target instanceof GiantEntity) {
                target.serverDamage(attacker.getDamageSources().playerAttack((PlayerEntity)attacker), getAttackDamage() * 3.25F);
            }
        }
        return super.postHit(stack, target, attacker);
    }

    /**
     * Returns the attack damage for the certain {@code "speedrunner sword.}
     */
    private int getAttackDamage() {
        return SpeedrunnerSwordItem.attackDamage;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (options().client.itemTooltips) {
            tooltip.add(Text.translatable("item.speedrunnermod.speedrunner_sword.tooltip.line1").formatted(Formatting.GRAY));
            if (options().main.playingMode.doom()) {
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunner_sword.tooltip.line2"));
            }
        }
    }
}