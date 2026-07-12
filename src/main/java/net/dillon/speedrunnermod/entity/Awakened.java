package net.dillon.speedrunnermod.entity;

/**
 * Tracks if a piglin was awakened or not.
 */
public interface Awakened {
    void setAwakened(boolean awakened);
    boolean isAwakened();
}