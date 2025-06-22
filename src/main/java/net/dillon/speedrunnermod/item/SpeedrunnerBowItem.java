package net.dillon.speedrunnermod.item;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Consumer;

/**
 * A bow that charges faster, shoots farther, does more damage, and has more durability.
 */
public class SpeedrunnerBowItem extends BowItem  {

    public SpeedrunnerBowItem(Settings settings) {
        super(settings.maxCount(1).maxDamage(768));
    }

    /**
     * See comments inside method for changes.
     */
    @Override
    public boolean onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity playerEntity)) {
            return false;
        } else {
            ItemStack itemStack = playerEntity.getProjectileType(stack);
            if (itemStack.isEmpty()) {
                return false;
            } else {
                int i = this.getMaxUseTime(stack, user) - remainingUseTicks;
                float f = getPullProgress(i);
                if ((double)f < 0.1) {
                    return false;
                } else {
                    List<ItemStack> list = load(stack, itemStack, playerEntity);
                    if (world instanceof ServerWorld serverWorld && !list.isEmpty()) {
                        this.shootAll(serverWorld, playerEntity, playerEntity.getActiveHand(), stack, list, f * 3.5F /* In the BowItem class, this value is set to 3.0. Now it's 3.5, which increases the speed */, 1.0F, f == 1.0F, null);
                    }

                    world.playSound(
                            null,
                            playerEntity.getX(),
                            playerEntity.getY(),
                            playerEntity.getZ(),
                            SoundEvents.ENTITY_ARROW_SHOOT,
                            SoundCategory.PLAYERS,
                            1.0F,
                            1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F
                    );
                    playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                    return true;
                }
            }
        }
    }

    @Override
    protected ProjectileEntity createArrowEntity(World world, LivingEntity shooter, ItemStack weaponStack, ItemStack projectileStack, boolean critical) {
        Item item = projectileStack.getItem();
        ArrowItem arrowItem2 = item instanceof ArrowItem ? (ArrowItem)item : (ArrowItem) Items.ARROW;
        PersistentProjectileEntity persistentProjectileEntity = arrowItem2.createArrow(world, projectileStack, shooter, weaponStack);
        persistentProjectileEntity.applyDamageModifier(1.1F); // Added to increase the power of the bow slightly
        if (critical) {
            persistentProjectileEntity.setCritical(true);
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
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 54000;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.speedrunnermod.speedrunner_bow.tooltip.line1").formatted(Formatting.GRAY));
        textConsumer.accept(Text.translatable("item.speedrunnermod.speedrunner_bow.tooltip.line2").formatted(Formatting.GRAY));
    }
}