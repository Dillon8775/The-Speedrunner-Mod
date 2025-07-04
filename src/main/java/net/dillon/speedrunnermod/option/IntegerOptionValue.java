package net.dillon.speedrunnermod.option;

/**
 * An {@code option value} holding an {@code integer}, which stores the {@code default value} and {@code current value,} as well as a {@code minimum} and {@code maximum} value.
 */
public class IntegerOptionValue extends OptionValue<Integer> {
    private final int min; // The minimum value allowed (if a number)
    private final int max; // The maximum value allowed (if a number)

    /**
     * Constructs an integer option with required min and max values.
     */
    public IntegerOptionValue(int defaultValue, boolean requiresRestart, int min, int max) {
        super(defaultValue, requiresRestart);
        this.min = min;
        this.max = max;
    }

    /**
     * @return the {@code minimum} value allowed for the option.
     */
    public int getMinValue() {
        return this.min;
    }

    /**
     * @return the {@code maximum} value allowed for the option.
     */
    public int getMaxValue() {
        return this.max;
    }
}