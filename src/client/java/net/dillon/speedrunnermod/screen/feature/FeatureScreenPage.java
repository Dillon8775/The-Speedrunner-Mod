package net.dillon.speedrunnermod.screen.feature;

/**
 * <p>Used for features screens, specifying what buttons should be displayed and if the screen should determine a positive or negative page.</p>
 * <p>{@code Default} = Any screen that is not the first or last screen in a set screen category.</p>
 * <p>{@code First} = The first screen (or page) on a set screen category.
 * <p>{@code Last} = The last screen (or page) on a set screen category.
 * <p>{@code FTP (first time playing} = Any screen in the first time playing screen category.
 */
public enum FeatureScreenPage {
    DEFAULT,
    FIRST,
    LAST,
    FTP
}