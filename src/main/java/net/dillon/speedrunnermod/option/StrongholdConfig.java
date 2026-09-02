package net.dillon.speedrunnermod.option;

/**
 * A special config for strongholds.
 */
public record StrongholdConfig(int totalStrongholdsPerWorld, int distance, int spread, int totalPortalRoomsPerWorld, int totalLibrariesPerWorld) {
}