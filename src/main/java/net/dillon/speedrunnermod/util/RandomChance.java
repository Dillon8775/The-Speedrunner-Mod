package net.dillon.speedrunnermod.util;

import java.util.Random;

/**
 * A random chance calculator, which helps with creating random things.
 */
public class RandomChance {

    /**
     * Returns a random float, with a minimum and maximum value.
     */
    public static float floatInclusive(float min, float max) {
        Random random = new Random();
        return min + random.nextFloat() * (max - min);
    }

    /**
     * @return a random int, with a minimum and maximum value.
     */
    public static int intInclusive(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}