package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.function.Consumer;

/**
 * A sword with very low durability, but can one-shot wither skeletons, and guarantees a wither skeleton skull.
 */
public class WitherSwordItem extends Item  {

    public WitherSwordItem(Settings settings) {
        super(settings.sword(ModToolMaterials.WITHER_SWORD, 9, -2.4F));
    }

    /**
     * Kills wither skeletons instantly, and makes them drop a wither skull.
     */
    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target instanceof WitherSkeletonEntity witherSkeleton) {
            if (ModUtil.percentChance(witherSkeleton.getRandom(), 65)) {
                witherSkeleton.dropItem(witherSkeleton.getServer().getWorld(witherSkeleton.getWorld().getRegistryKey()), Items.WITHER_SKELETON_SKULL);
                stack.damage(ModToolMaterials.WITHER_SWORD.durability(), attacker, EquipmentSlot.MAINHAND);
            }
            witherSkeleton.kill(witherSkeleton.getServer().getWorld(witherSkeleton.getWorld().getRegistryKey()));
        }
        super.postHit(stack, target, attacker);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.speedrunnermod.wither_sword.tooltip.line1").formatted(Formatting.GRAY));
        textConsumer.accept(Text.translatable("item.speedrunnermod.wither_sword.tooltip.line2").formatted(Formatting.GRAY));
    }
}