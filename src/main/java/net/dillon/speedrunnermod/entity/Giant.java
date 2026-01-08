package net.dillon.speedrunnermod.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

/**
 * See {@link net.dillon.speedrunnermod.mixin.main.entity.giant.GiantEntityMixin} for more.
 */
public interface Giant {

    static boolean tryAttack(ServerWorld world, LivingEntity attacker, LivingEntity target) {
        float f = (float)attacker.getAttributeValue(EntityAttributes.ATTACK_DAMAGE);
        float g;
        if (!attacker.isBaby() && (int)f > 0) {
            g = f / 2.0F + (float)world.random.nextInt((int)f);
        } else {
            g = f;
        }

        DamageSource damageSource = attacker.getDamageSources().mobAttack(attacker);
        boolean bl = target.damage(world, damageSource, g);
        if (bl) {
            EnchantmentHelper.onTargetDamaged(world, target, damageSource);
            if (!attacker.isBaby()) {
                knockback(attacker, target);
            }
        }

        return bl;
    }

    static void knockback(LivingEntity attacker, LivingEntity target) {
        double d = attacker.getAttributeValue(EntityAttributes.ATTACK_KNOCKBACK);
        double e = target.getAttributeValue(EntityAttributes.KNOCKBACK_RESISTANCE);
        double f = d - e;
        if (!(f <= (double)0.0F)) {
            double g = target.getX() - attacker.getX();
            double h = target.getZ() - attacker.getZ();
            float i = (float)(attacker.getEntityWorld().random.nextInt(21) - 10);
            double j = f * (double)(attacker.getEntityWorld().random.nextFloat() * 0.5F + 0.2F);
            Vec3d vec3d = (new Vec3d(g, (double)0.0F, h)).normalize().multiply(j).rotateY(i);
            double k = f * (double)attacker.getEntityWorld().random.nextFloat() * (double)0.5F;
            target.addVelocity(vec3d.x, k, vec3d.z);
            target.knockedBack = true;
        }
    }
}