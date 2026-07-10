package net.dillon.speedrunnermod.helper;

/**
 * Gives ender pearls a inertia value.
 */
public interface InfiniPearl {
    void setInertia(float value);
    float getInertia();
    void setDamage(float value);
    float getTargetDamage();
}