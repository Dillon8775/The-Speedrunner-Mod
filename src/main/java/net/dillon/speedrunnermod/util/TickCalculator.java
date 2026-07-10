package net.dillon.speedrunnermod.util;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;

/**
 * A tick calculator class, which helps with calculating tick-based arguments.
 */
public class TickCalculator {

    /**
     * Converts seconds to ticks.
     */
    public static int seconds(int secondsAsTicks) {
        try {
            int testSeconds = 0;
            while (testSeconds < 525600) {
                if (secondsAsTicks == testSeconds) {
                    throw new NumberFormatException();
                }
                testSeconds += 60;
            }
            return secondsAsTicks * 20;
        } catch (NumberFormatException o) {
            SpeedrunnerMod.error("Use method minutesInTicks(int) if you're inputting an exact minute.");
            o.printStackTrace();
            return minutes(secondsAsTicks / 60);
        }
    }

    /**
     * Converts minutes to ticks.
     */
    public static int minutes(int minutesAsTicks) {
        return (minutesAsTicks * 60) * 20;
    }
}