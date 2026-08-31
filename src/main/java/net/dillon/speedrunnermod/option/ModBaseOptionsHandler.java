package net.dillon.speedrunnermod.option;

import net.dillon.dillonlib.util.BaseOptions;

/**
 * The base class for registering options on different environment sides.
 */
public abstract class ModBaseOptionsHandler<T> extends BaseOptions<T> {
    public static final String CURRENT_VALUE = "current_value";
    protected T instance;

    private final String space = " ";
    private final String pertaining = "Pertaining to: ";
    protected final String related = space + pertaining;

    public ModBaseOptionsHandler(String fileName) {
        super(fileName);
    }

    /**
     * Matches client-side options with server-side options.
     */
    public void match(T sentOptions) {
        this.instance = sentOptions;
        save();
    }
}