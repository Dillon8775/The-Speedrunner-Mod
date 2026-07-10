package net.dillon.speedrunnermod.item;

/**
 * Represents a {@code fireproof boat.}
 */
public interface FireproofBoat {
    void setFireproof(boolean fireproof);
    boolean isFireproof();
    void setBoatSpeed(float boatSpeed);
    float getBoatSpeed();
}