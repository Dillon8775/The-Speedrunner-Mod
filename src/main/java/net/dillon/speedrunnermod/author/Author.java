package net.dillon.speedrunnermod.author;

/**
 * Indicates that the annotated method, field, or class/object was created by the person equal to the {@code String value.}
 */
public @interface Author {
    Authors value();
}