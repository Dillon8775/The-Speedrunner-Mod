package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.advancement.ModPredicates;
import net.dillon.speedrunnermod.entity.goliath.Minion;
import net.dillon.speedrunnermod.helper.ModHelper;
import net.dillon.speedrunnermod.util.RandomChance;
import net.dillon.speedrunnermod.util.TickCalculator;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.hurtingprojectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.hurtingprojectile.DragonFireball;
import net.minecraft.world.entity.projectile.hurtingprojectile.LargeFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

/**
 * A fireball, which can be thrown.
 */
public interface ThrowableFireball {

    /**
     * Creates a fireball entity.
     */
    static boolean createFireballEntity(boolean dragon, LivingEntity thrower, InteractionHand hand) {
        Level level = thrower.level();
        ItemStack stack = thrower.getItemInHand(hand);

        boolean zombie = thrower instanceof Zombie;

        if (!level.isClientSide()) {
            Vec3 lookVec = thrower.getViewVector(1.0F);

            int explosionPower = getExplosionPower(dragon, thrower);

            AbstractHurtingProjectile fireball = dragon
                    ? new DragonFireball(level, thrower, lookVec.normalize())
                    : new LargeFireball(level, thrower, lookVec.normalize(), explosionPower);

            fireball.absSnapTo(thrower.getX(), thrower.getEyeY() - 0.235, thrower.getZ());
            fireball.setOwner(thrower);
            level.addFreshEntity(fireball);

            level.playSound(null,
                    thrower.getX(), thrower.getY(), thrower.getZ(),
                    SoundEvents.FIRECHARGE_USE,
                    zombie ? SoundSource.HOSTILE : SoundSource.PLAYERS,
                    zombie ? 5.0F : 1.0F,
                    1.0F
            );

            if (thrower instanceof Player player) {
                ModPredicates.TRIGGERED_BY_ITEMLIKE.trigger((ServerPlayer) player, new ItemStack(Items.FIRE_CHARGE));
                player.getCooldowns().addCooldown(stack, TickCalculator.seconds(getFireballCooldown(explosionPower, dragon)));
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
            }
            thrower.swing(hand);

            return true;
        }

        return false;
    }

    /**
     * @return the fireball cooldown when used.
     */
    static int getFireballCooldown(int explosionPower, boolean dragon) {
        int cooldownFromPower = ModHelper.atLeast(explosionPower / 2, 1);
        return dragon ? 5 + (cooldownFromPower - 1) : cooldownFromPower;
    }

    /**
     * @return the explosion power when a fireball impacts the ground.
     */
    static int getExplosionPower(boolean dragon, LivingEntity thrower) {
        boolean isZombie = thrower instanceof Zombie;
        boolean isMinion = thrower instanceof Minion minion && minion.isGoliathMinion();

        final int maxDragonPower = 5;
        int maxPower = 10;
        if (isMinion) {
            maxPower = 4;
        } else if (isZombie) {
            maxPower = 5;
        }

        int power = isDoomMode()
                ? RandomChance.intInclusive(1, maxPower)
                : options().general.fireballExplosionPower.getCurrentValue();

        if (dragon && power > maxDragonPower) {
            power = 5;
        }

        return dragon ? (int)(power * 1.5F) : power;
    }
}