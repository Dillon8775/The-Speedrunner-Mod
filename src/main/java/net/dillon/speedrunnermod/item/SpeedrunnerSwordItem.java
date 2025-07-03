package net.dillon.speedrunnermod.item;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

/**
 * Better than iron, worse than diamond, deals more damage to withers and giants.
 */
public class SpeedrunnerSwordItem extends Item  {
    private static int attackDamage;

    public SpeedrunnerSwordItem(int attackDamage, boolean golden, Settings settings) {
        super(settings.sword(!golden ? ModToolMaterials.SPEEDRUNNER_SWORD_PICKAXE : ModToolMaterials.GOLDEN_SPEEDRUNNER, attackDamage, -2.4F));
        SpeedrunnerSwordItem.attackDamage = attackDamage;
    }

    /**
     * Deals more damage to withers, and giants under certain conditions.
     */
    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity) {
            if (target instanceof WitherEntity) {
                target.serverDamage(attacker.getDamageSources().playerAttack((PlayerEntity)attacker), getAttackDamage() * 4.45F);
            } else if (target instanceof GiantEntity) {
                target.serverDamage(attacker.getDamageSources().playerAttack((PlayerEntity)attacker), getAttackDamage() * 3.25F);
            }
        }
        super.postHit(stack, target, attacker);
    }

    /**
     * Returns the attack damage for the certain {@code "speedrunner sword.}
     */
    private int getAttackDamage() {
        return SpeedrunnerSwordItem.attackDamage;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.speedrunnermod.speedrunner_sword.tooltip.line1").formatted(Formatting.GRAY));
        if (isDoomMode()) {
            textConsumer.accept(Text.translatable("item.speedrunnermod.speedrunner_sword.tooltip.line2"));
        }
    }
}