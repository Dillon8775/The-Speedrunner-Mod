package net.dillon.speedrunnermod.option;

import net.dillon.speedrunnermod.util.AI;

import java.util.ArrayList;
import java.util.List;

/**
 * An {@code option value} for any speedrunner mod option, which stores the {@code default value} and {@code current value.}
 */
@AI
public class OptionValue<T> {
    private final boolean requiresRestart; // Determines if the option requires a restart upon changing
    private final T defaultValue; // The default value for the option (never changed, only used to reset the currentValue)
    private T currentValue; // The value used across the scope of the mod
    private boolean broken = false; // Determines if the option is broken
    private static final List<OptionValue<?>> brokenOptions = new ArrayList<>(); // A list of all "broken" options

    /**
     * Constructs an option.
     */
    public OptionValue(T defaultValue, boolean requiresRestart) {
        this.requiresRestart = requiresRestart;
        this.defaultValue = defaultValue;
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
     * Makes the option {@code "broken"} (meaning action is needed to fix it).
     */
    public void setBroken() {
        this.broken = true;
        brokenOptions.add(this);
    }

    /**
     * Marks an option as fixed.
     */
    public void setFixed() {
        this.broken = false;
    }

    /**
     * @return {@code true} if the option is {@code "broken".}
     */
    public boolean isBroken() {
        return this.broken;
    }

    /**
     * @return A list of all {@code broken} options.
     */
    public static List<OptionValue<?>> getBrokenOptions() {
        return brokenOptions;
    }

    /**
     * Resets the option value back to {@code default.}
     */
    public void reset() {
        this.currentValue = this.defaultValue;
    }
}