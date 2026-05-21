package net.dillon.speedrunnermod.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.AttackRange;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

/**
 * The speedrunner spear, with more block reach and increased momentum from the lunge enchantment.
 */
public class SpeedrunnerSpearItem extends Item {
    private final float increasedMomentumMultiplier;

    public SpeedrunnerSpearItem(Properties properties, ToolMaterial toolMaterial,
                                float maxReach,
                                float maxCreativeReach,
                                float increasedMomentumMultiplier,
                                float attackDuration,
                                float damageMultiplier,
                                float delay,
                                float dismountTime,
                                float dismountThreshold,
                                float knockbackTime,
                                float damageTime) {
        super(properties
                .spear(
                        toolMaterial,
                        attackDuration,
                        damageMultiplier,
                        delay,
                        dismountTime,
                        dismountThreshold,
                        knockbackTime,
                        5.1F,
                        damageTime,
                        4.6F
                )
                .component(DataComponents.ATTACK_RANGE,
                        new AttackRange(3.0F, maxReach, 3.0F, maxCreativeReach, 0.125F, 0.5F)));
        this.increasedMomentumMultiplier = increasedMomentumMultiplier;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.speedrunner_spear.tooltip"));
    }

    /**
     * @return the increased momentum multiplier for a speedrunner spear.
     */
    public float getIncreasedMomentumMultiplier() {
        return this.increasedMomentumMultiplier;
    }
}