package net.dillon.speedrunnermod.option;

import net.dillon.speedrunnermod.util.AI;

/**
 * An {@code option value} for any speedrunner mod option, which stores the {@code default value} and {@code current value.}
 */
@AI
public class OptionValue<T> {
    private final T defaultValue; // The default value for the option (never changed, only used to reset the currentValue)
    private final boolean requiresRestart; // Determines if the option requires a restart upon changing
    private T currentValue; // The value used across the scope of the mod

    /**
     * Constructs an option.
     */
    public OptionValue(T defaultValue, boolean requiresRestart) {
        this.defaultValue = defaultValue;
        this.requiresRestart = requiresRestart;
        this.currentValue = defaultValue;
    }

    /**
     * @return the {@code current value} of the option.

     */
    public T getCurrentValue() {
        return this.currentValue;
    }

    /**
     * @return {@code true} if the option requires a restart.
     */
    public boolean requiresRestart() {
        return this.requiresRestart;
    }

    /**
     * Sets the option value.
     */
    public void set(T value) {
        this.currentValue = value;
    }

    /**
     * Resets the option value back to {@code default.}
     */
    public void reset() {
        this.currentValue = this.defaultValue;
    }
}