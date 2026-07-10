package net.dillon.speedrunnermod.item.tool;

import net.dillon.speedrunnermod.component.ModAttributes;
import net.dillon.speedrunnermod.mixin.accessor.CrossbowItemAccessor;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * A crossbow which charges faster, shoots farther, and has more durability.
 */
public class SpeedrunnerCrossbowItem extends CrossbowItem {

    public SpeedrunnerCrossbowItem(Properties settings) {
        super(settings
                .stacksTo(1)
                .durability(652)
                .attributes(
                        ItemAttributeModifiers.builder()
                                .add(
                                        ModAttributes.BONUS_BOW_CHARGE_SPEED,
                                        new AttributeModifier(ofSpeedrunnerMod("charge_speed_speedrunner_crossbow"), 0.25F /*0.25*/, AttributeModifier.Operation.ADD_VALUE),
                                        EquipmentSlotGroup.MAINHAND
                                )
                                .add(
                                        ModAttributes.BONUS_RANGE,
                                        new AttributeModifier(ofSpeedrunnerMod("range_speedrunner_crossbow"), 0.5F, AttributeModifier.Operation.ADD_VALUE),
                                        EquipmentSlotGroup.MAINHAND
                                )
                                .add(
                                        ModAttributes.BONUS_BOW_POWER,
                                        new AttributeModifier(ofSpeedrunnerMod("power_speedrunner_crossbow"), 0.1F, AttributeModifier.Operation.ADD_VALUE),
                                        EquipmentSlotGroup.MAINHAND
                                )
                                .build()
                )
        );
    }

    @Override
    public InteractionResult use(Level world, Player user, InteractionHand hand) {
        ItemStack itemStack = user.getItemInHand(hand);
        ChargedProjectiles chargedProjectilesComponent = itemStack.get(DataComponents.CHARGED_PROJECTILES);
        if (chargedProjectilesComponent != null && !chargedProjectilesComponent.isEmpty()) {
            this.performShooting(world, user, hand, itemStack, getRange(user, chargedProjectilesComponent), 1.0F, null);
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
     * Increases charge speed, and removes the time held argument.
     */
    @Override
    public boolean releaseUsing(final ItemStack itemStack, final Level level, final LivingEntity entity, final int remainingTime) {
        return getChargeSpeed(itemStack, entity) >= 1.0F && isCharged(itemStack);
    }

    /**
     * The maximum use time for the crossbow.
     */
    @Override
    public int getUseDuration(ItemStack stack, LivingEntity user) {
        return getChargeSpeed(stack, user) + 3;
    }

    /**
     * Increases projectile range.
     */
    @Override
    public int getDefaultProjectileRange() {
        return 10;
    }

    /**
     * @return the {@code charge speed} for the speedrunner crossbow.
     */
    public static int getChargeSpeed(ItemStack stack, LivingEntity user) {
        float chargeSpeed = (float)user.getAttributeValue(ModAttributes.BONUS_BOW_CHARGE_SPEED);
        float f = EnchantmentHelper.modifyCrossbowChargingTime(stack, user, 1.25F - chargeSpeed);
        return Mth.floor(f * 20.0F);
    }

    /**
     * @return the {@code range} for the speedrunner crossbow.
     */
    public static float getRange(LivingEntity user, ChargedProjectiles stack) {
        float range = (float)user.getAttributeValue(ModAttributes.BONUS_RANGE);
        return stack.contains(Items.FIREWORK_ROCKET) ? 1.6F + range : 3.15F + range;
    }

    /**
     * @return the {@code power} for the speedrunner crossbow.
     */
    public static float getPower(LivingEntity user) {
        float power = (float)user.getAttributeValue(ModAttributes.BONUS_BOW_POWER);
        return 1.0F + power;
    }
}