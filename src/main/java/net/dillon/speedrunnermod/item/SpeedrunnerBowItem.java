package net.dillon.speedrunnermod.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Consumer;

/**
 * A bow that charges faster, shoots farther, does more damage, and has more durability.
 */
public class SpeedrunnerBowItem extends BowItem {

    public SpeedrunnerBowItem(Properties settings) {
        super(settings.stacksTo(1).durability(768));
    }

    /**
     * See comments inside method for changes.
     */
    @Override
    public boolean releaseUsing(ItemStack stack, Level world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof Player playerEntity)) {
            return false;
        } else {
            ItemStack itemStack = playerEntity.getProjectile(stack);
            if (itemStack.isEmpty()) {
                return false;
            } else {
                int i = this.getUseDuration(stack, user) - remainingUseTicks;
                float f = getPullProgress(i);
                if ((double)f < 0.1) {
                    return false;
                } else {
                    List<ItemStack> list = draw(stack, itemStack, playerEntity);
                    if (world instanceof ServerLevel serverWorld && !list.isEmpty()) {
                        this.shoot(serverWorld, playerEntity, playerEntity.getUsedItemHand(), stack, list, f * 3.5F /* In the BowItem class, this value is set to 3.0. Now it's 3.5, which increases the speed */, 1.0F, f == 1.0F, null);
                    }

                    world.playSound(
                            null,
                            playerEntity.getX(),
                            playerEntity.getY(),
                            playerEntity.getZ(),
                            SoundEvents.ARROW_SHOOT,
                            SoundSource.PLAYERS,
                            1.0F,
                            1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F
                    );
                    playerEntity.awardStat(Stats.ITEM_USED.get(this));
                    return true;
                }
            }
        }
    }

    @Override
    protected Projectile createProjectile(Level world, LivingEntity shooter, ItemStack weaponStack, ItemStack projectileStack, boolean critical) {
        Item item = projectileStack.getItem();
        ArrowItem arrowItem2 = item instanceof ArrowItem ? (ArrowItem)item : (ArrowItem) Items.ARROW;
        AbstractArrow persistentProjectileEntity = arrowItem2.createArrow(world, projectileStack, shooter, weaponStack);
        persistentProjectileEntity.setBaseDamageFromMob(1.1F); // Added to increase the power of the bow slightly
        if (critical) {
            persistentProjectileEntity.setCritArrow(true);
        }
        return persistentProjectileEntity;
    }

    /**
     * Renderers the pull progress of the speedrunner bow at a faster rate.
     */
    public static float getPullProgress(int useTicks) {
        float f = (float)useTicks / 15.0F;
        if ((f = (f * f + f * 1.5F) / 2.5F) > 1.0F) {
            f = 1.0F;
        }
        return f;
    }

    /**
     * I honestly don't know what this does, but I just lowered it from the parent method.
     */
    @Override
    public int getUseDuration(ItemStack stack, LivingEntity user) {
        return 54000;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        textConsumer.accept(Component.translatable("item.speedrunnermod.speedrunner_bow.tooltip.line1").withStyle(ChatFormatting.GRAY));
        textConsumer.accept(Component.translatable("item.speedrunnermod.speedrunner_bow.tooltip.line2").withStyle(ChatFormatting.GRAY));
    }
}