package net.dillon.speedrunnermod.option;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * Any client-side speedrunner mod option that is {@code broken,} see {@link ModOptions} for more on this.
 */
@Environment(EnvType.CLIENT)
public class ClientBrokenModOptions {
    public static boolean itemMessages = false;
    public static boolean gameMode = false;
    public static boolean difficulty = false;
}