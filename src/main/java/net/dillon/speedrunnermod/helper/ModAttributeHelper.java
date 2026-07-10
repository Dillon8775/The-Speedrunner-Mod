package net.dillon.speedrunnermod.helper;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;

/**
 * Helper class for attributes.
 */
public class ModAttributeHelper {

    /**
     * Modifies the {@code maximum health} of an entity.
     */
    public static void modifyMaxHealth(LivingEntity entity, double health) {
        if (entity.getAttribute(Attributes.MAX_HEALTH) != null) {
            entity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(health);
        }
        entity.setHealth((float)entity.getAttribute(Attributes.MAX_HEALTH).getBaseValue());
    }

    /**
     * Modifies the {@code generic movement speed} of an entity.
     */
    public static void modifyMovementSpeed(LivingEntity entity, double speed) {
        if (entity.getAttribute(Attributes.MOVEMENT_SPEED) != null) {
            entity.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(speed);
        }
        entity.setSpeed((float)entity.getAttribute(Attributes.MOVEMENT_SPEED).getBaseValue());
    }

    /**
     * Modifies the {@code follow range} of an entity.
     */
    public static void modifyFollowRange(LivingEntity entity, double range) {
        if (entity.getAttribute(Attributes.FOLLOW_RANGE) != null) {
            entity.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(range);
        }
    }

    /**
     * Modifies the {@code attack damage} of an entity.
     */
    public static void modifyAttackDamage(LivingEntity entity, double attackDamage) {
        if (entity.getAttribute(Attributes.ATTACK_DAMAGE) != null) {
            entity.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(attackDamage);
        }
    }

    /**
     * Modifies the {@code attack knockback} of an entity.
     */
    public static void modifyAttackKnockback(LivingEntity entity, double attackKnockback) {
        if (entity.getAttribute(Attributes.ATTACK_KNOCKBACK) != null) {
            entity.getAttribute(Attributes.ATTACK_KNOCKBACK).setBaseValue(attackKnockback);
        }
    }

    /**
     * Modifies the {@code knockback resistance} of an entity.
     */
    public static void modifyKnockbackResistance(LivingEntity entity, double resistance) {
        if (entity.getAttribute(Attributes.KNOCKBACK_RESISTANCE) != null) {
            entity.getAttribute(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(resistance);
        }
    }

    /**
     * Modifies the {@code flying speed} of an entity.
     */
    @Deprecated
    public static void modifyFlyingSpeed(LivingEntity entity, double flyingSpeed) {
        if (entity.getAttribute(Attributes.FLYING_SPEED) != null) {
            entity.getAttribute(Attributes.FLYING_SPEED).setBaseValue(flyingSpeed);
        }
    }

    /**
     * Modifies the {@code armor attribute} of an entity.
     */
    public static void modifyArmor(LivingEntity entity, double value) {
        if (entity.getAttribute(Attributes.ARMOR) != null) {
            entity.getAttribute(Attributes.ARMOR).setBaseValue(value);
        }
    }
}