package net.dillon.speedrunnermod.component;

/**
 * Indicates that an attribute is {@code "fake",} meaning it does not actually do anything, it's just added for visual.
 * <p>The {@code inconsistent} boolean indicates that an attribute is considered fake because of inconsistency when switching hands.</p>
 */
public @interface FakeAttribute {
    boolean inconsistent();
}