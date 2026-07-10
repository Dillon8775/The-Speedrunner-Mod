package net.dillon.speedrunnermod.util;

import com.google.common.annotations.VisibleForTesting;

/**
 * Manual overrides for testing purposes.
 */
@VisibleForTesting
public class Overrides {

    /**
     * @return if we should always be considered a first time player.
     */
    public static boolean firstTimePlaying() {
        return false;
    }
}