package net.dillon.speedrunnermod.screen;

/**
 * <p>Used for features screens.</p>
 * <p>STARTER = The first screen (or page) on a set screen category.
 * <p>FINAL = The last screen (or page) on a set screen category.
 * <p>NORMAL = Any screen that is not the first or last screen in a set screen category.</p>
 */
public enum ScreenType {
    FIRST_PAGE,
    DEFAULT,
    LAST_PAGE,
    END,
    FIRST_TIME_PLAYING
}