package net.dillon.speedrunnermod.option;

/**
 * An {@code option value} holding an {@code integer}, which stores the {@code default value} and {@code current value,} as well as a {@code minimum} and {@code maximum} value.
 */
public class IntegerOptionValue extends OptionValue<Integer> {
    private final int minValue; // The minimum value allowed (if a number)
    private final int maxValue; // The maximum value allowed (if a number)

    /**
     * Constructs an integer option with required minValue and maxValue values.
     */
    public IntegerOptionValue(int defaultValue, boolean requiresRestart, int minValue, int maxValue) {
        super(defaultValue, requiresRestart);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    /**
     * @return the {@code minimum} value allowed for the option.
     */
    public int getMinValue() {
        return this.minValue;
    }

    /**
     * @return the {@code maximum} value allowed for the option.
     */
    public int getMaxValue() {
        return this.maxValue;
    }
}