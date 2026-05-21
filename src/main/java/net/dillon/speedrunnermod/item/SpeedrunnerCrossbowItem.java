package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.mixin.accessor.CrossbowItemAccessor;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

/**
 * A crossbow which charges faster, shoots farther, and has more durability.
 */
public class SpeedrunnerCrossbowItem extends CrossbowItem {

    public SpeedrunnerCrossbowItem(Properties settings) {
        super(settings.stacksTo(1).durability(652));
    }

    @Override
    public InteractionResult use(Level world, Player user, InteractionHand hand) {
        ItemStack itemStack = user.getItemInHand(hand);
        ChargedProjectiles chargedProjectilesComponent = itemStack.get(DataComponents.CHARGED_PROJECTILES);
        if (chargedProjectilesComponent != null && !chargedProjectilesComponent.isEmpty()) {
            this.performShooting(world, user, hand, itemStack, getSpeed(chargedProjectilesComponent), 1.0F, null);
            return InteractionResult.CONSUME;
        }
        if (!user.getProjectile(itemStack).isEmpty()) {
            ((CrossbowItemAccessor)(this)).setStartSoundPlayed(false);
            ((CrossbowItemAccessor)(this)).setMidLoadSoundPlayed(false);
            user.startUsingItem(hand);
            return InteractionResult.CONSUME;
        }
        return InteractionResult.FAIL;
    }

    /**
     * Increases damage with speedrunner crossbow.
     */
    @Override
    protected Projectile createProjectile(Level world, LivingEntity shooter, ItemStack weaponStack, ItemStack projectileStack, boolean critical) {
        ArrowItem arrowItem2 = projectileStack.getItem() instanceof ArrowItem arrowItem ? arrowItem : (ArrowItem) Items.ARROW;
        AbstractArrow persistentProjectileEntity = arrowItem2.createArrow(world, projectileStack, shooter, weaponStack);
        persistentProjectileEntity.setBaseDamageFromMob(1.1F); // Added to increase the power of the crossbow slightly
        if (critical) {
            persistentProjectileEntity.setCritArrow(true);
        }

        return persistentProjectileEntity;
    }

    /**
     * The maximum use time for the crossbow.
     */
    @Override
    public int getUseDuration(ItemStack stack, LivingEntity user) {
        return getPullTime(stack, user) + 3;
    }

    /**
     * Faster charging speed.
     */
    private static float getSpeed(ChargedProjectiles stack) {
        return stack.contains(Items.FIREWORK_ROCKET) ? 2.1F : 3.65F;
    }

    /**
     * Lowered pull time.
     */
    public static int getPullTime(ItemStack stack, LivingEntity user) {
        float f = EnchantmentHelper.modifyCrossbowChargingTime(stack, user, 1.00F);
        return Mth.floor(f * 20.0F);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.speedrunner_crossbow.tooltip"));
    }
}