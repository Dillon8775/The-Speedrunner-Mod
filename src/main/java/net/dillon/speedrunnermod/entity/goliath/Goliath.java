package net.dillon.speedrunnermod.entity.goliath;

import net.dillon.speedrunnermod.mixin.entity.goliath.GoliathEntity;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;

/**
 * See {@link GoliathEntity} for more.
 */
public interface Goliath {

    static boolean tryAttack(ServerLevel world, LivingEntity attacker, LivingEntity target) {
        float f = (float)attacker.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float g;
        if (!attacker.isBaby() && (int)f > 0) {
            g = f / 2.0F + (float)world.getRandom().nextInt((int)f);
        } else {
            g = f;
        }

        DamageSource damageSource = attacker.damageSources().mobAttack(attacker);
        boolean bl = target.hurtServer(world, damageSource, g);
        if (bl) {
            EnchantmentHelper.doPostAttackEffects(world, target, damageSource);
            if (!(attacker.getHealth() < attacker.getMaxHealth() / 3)) {
                target.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, ModUtil.secondsAsTicks(3)));
                target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, ModUtil.secondsAsTicks(3)));
                target.addEffect(new MobEffectInstance(MobEffects.NAUSEA, ModUtil.secondsAsTicks(7)));
            }
            if (!attacker.isBaby()) {
                knockback(attacker, target);
            }
        }

        return bl;
    }

    static void knockback(LivingEntity attacker, LivingEntity target) {
        double d = attacker.getAttributeValue(Attributes.ATTACK_KNOCKBACK);
        double e = target.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);
        double f = d - e;
        if (!(f <= (double)0.0F)) {
            double g = target.getX() - attacker.getX();
            double h = target.getZ() - attacker.getZ();
            float i = (float)(attacker.level().getRandom().nextInt(21) - 10);
            double j = f * (double)(attacker.level().getRandom().nextFloat() * 0.5F + 0.2F);
            Vec3 vec3d = (new Vec3(g, (double)0.0F, h)).normalize().scale(j).yRot(i);
            double k = f * (double)attacker.level().getRandom().nextFloat() * (double)0.5F;
            target.push(vec3d.x, k, vec3d.z);
            target.hurtMarked = true;
        }
    }
}