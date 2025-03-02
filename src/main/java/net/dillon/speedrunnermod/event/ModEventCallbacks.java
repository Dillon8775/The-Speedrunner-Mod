package net.dillon.speedrunnermod.event;

import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;

@Author(Authors.YELEEFFF)
public class ModEventCallbacks {

    /**
     * Registers custom events.
     */
    public static void registerEventCallbacks() {
        SpeedrunnersTotemUsedCallback.EVENT.register(((entity, stack, source) -> {}));
    }
}