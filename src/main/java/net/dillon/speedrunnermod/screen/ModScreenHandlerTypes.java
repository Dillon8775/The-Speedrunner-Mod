package net.dillon.speedrunnermod.screen;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.screen.ScreenHandlerType;

/**
 * All mod screen handler types.
 */
public class ModScreenHandlerTypes {
    public static final ScreenHandlerType<WorkbenchScreenHandler> WORKBENCH = ScreenHandlerType.register("workbench", WorkbenchScreenHandler::new);

    /**
     * Initializes all screen speedrunner mod handlers.
     */
    public static void initializeScreenHandlers() {
        SpeedrunnerMod.debug("Initialized screen handlers.");
    }
}