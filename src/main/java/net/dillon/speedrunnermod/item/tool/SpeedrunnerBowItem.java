package net.dillon.speedrunnermod.item.tool;

import net.dillon.dillonlib.factory.item.BowFactory;
import net.dillon.speedrunnermod.component.ModAttributes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * A bow that charges faster, shoots farther, does more damage, and has more durability.
 */
public class SpeedrunnerBowItem extends BowFactory {
    public static final float MAX_DRAW_DURATION = 15.0F;

    public SpeedrunnerBowItem(Properties settings) {
        super(settings
                .stacksTo(1)
                .durability(768)
                .attributes(
                        ItemAttributeModifiers.builder()
                                .add(
                                        ModAttributes.BONUS_BOW_CHARGE_SPEED,
                                        new AttributeModifier(ofSpeedrunnerMod("charge_speed_speedrunner_bow"), 0.5F, AttributeModifier.Operation.ADD_VALUE),
                                        EquipmentSlotGroup.MAINHAND
                                )
                                .add(
                                        ModAttributes.BONUS_RANGE,
                                        new AttributeModifier(ofSpeedrunnerMod("range_speedrunner_bow"), 0.5F, AttributeModifier.Operation.ADD_VALUE),
                                        EquipmentSlotGroup.MAINHAND
                                )
                                .add(
                                        ModAttributes.BONUS_BOW_POWER,
                                        new AttributeModifier(ofSpeedrunnerMod("power_speedrunner_bow"), 0.1F, AttributeModifier.Operation.ADD_VALUE),
                                        EquipmentSlotGroup.MAINHAND
                                )
                                .build()
                )
        );
    }

    /**
     * Increases the bow power and range.
     */
    @Override
    public boolean releaseUsing(final ItemStack itemStack, final Level level, final LivingEntity entity, final int remainingTime) {
        if (entity instanceof Player player) {
            ItemStack projectile = player.getProjectile(itemStack);
            if (projectile.isEmpty()) {
                return false;
            } else {
                int timeHeld = this.getUseDuration(itemStack, entity) - remainingTime;
                float pow = getChargeSpeed(player, timeHeld);
                if (pow < 0.1) {
                    return false;
                } else {
                    List<ItemStack> firedProjectiles = draw(itemStack, projectile, player);
                    if (level instanceof ServerLevel serverLevel && !firedProjectiles.isEmpty()) {
                        this.shoot(serverLevel, player, player.getUsedItemHand(), itemStack, firedProjectiles, pow * getRange(player) /* In the BowItem class, this value is set to 3.0. Now it's 3.5, which increases the speed */, 1.0F, pow == 1.0F, null);
                    }

                    level.playSound(
                            null,
                            player.getX(),
                            player.getY(),
                            player.getZ(),
                            SoundEvents.ARROW_SHOOT,
                            SoundSource.PLAYERS,
                            1.0F,
                            1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + pow * 0.5F
                    );
                    player.awardStat(Stats.ITEM_USED.get(this));
                    return true;
                }
            }
        } else {
            return false;
        }
    }

    /**
     * @return the charge speed for the speedrunner bow.
     */
    public static float getChargeSpeed(LivingEntity user, int useTicks) {
        float chargeSpeed = (float)user.getAttributeValue(ModAttributes.BONUS_BOW_CHARGE_SPEED); // Increases bow charging speed
        float f = (float)useTicks / (MAX_DRAW_DURATION - (chargeSpeed * 5));
        if ((f = (f * f + f * 1.5F) / 2.5F) > 1.0F) {
            f = 1.0F;
        }
        return f;
    }

    /**
     * @return the {@code range} for the speedrunner bow.
     */
    private static float getRange(LivingEntity user) {
        float range = (float)user.getAttributeValue(ModAttributes.BONUS_RANGE);
        return 3.0F + range;
    }

    /**
     * @return the {@code power} for the speedrunner bow.
     */
    public static float getPower(LivingEntity user) {
        float power = (float)user.getAttributeValue(ModAttributes.BONUS_BOW_POWER);
        return 1.0F + power;
    }

    /**
     * Increases projectile range.
     */
    @Override
    public int getDefaultProjectileRange() {
        return 17;
    }

    /**
     * I honestly don't know what this does, but I just lowered it from the parent method.
     */
    @Override
    public int getUseDuration(ItemStack stack, LivingEntity user) {
        return 54000;
    }
}