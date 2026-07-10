package net.dillon.speedrunnermod.helper;

/**
 * An interface which tells the player if they had an inventory preserver.
 */
public interface InventoryPreserver {
    void removeInventoryPreserver();
    boolean hadInventoryPreserver();
}