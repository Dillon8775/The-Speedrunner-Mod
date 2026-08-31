package net.dillon.speedrunnermod.option;

/**
 * A special config for strongholds.
 */
public class StrongholdConfig {
    public int totalStrongholdsPerWorld;
    public int distance;
    public int spread;
    public int totalPortalRoomsPerWorld;
    public int totalLibrariesPerWorld;

    public StrongholdConfig(int totalStrongholdsPerWorld, int distance, int spread, int totalPortalRoomsPerStronghold, int totalLibrariesPerStronghold) {
        this.totalStrongholdsPerWorld = totalStrongholdsPerWorld;
        this.distance = distance;
        this.spread = spread;
        this.totalPortalRoomsPerWorld = totalPortalRoomsPerStronghold;
        this.totalLibrariesPerWorld = totalLibrariesPerStronghold;
    }
}