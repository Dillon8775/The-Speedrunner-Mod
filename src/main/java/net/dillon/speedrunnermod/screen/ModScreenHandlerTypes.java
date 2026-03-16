package net.dillon.speedrunnermod.screen;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.world.inventory.MenuType;

/**
 * All mod screen handler types.
 */
public class ModScreenHandlerTypes {
    public static final MenuType<WorkbenchScreenHandler> WORKBENCH = MenuType.register("workbench", WorkbenchScreenHandler::new);

    /**
     * Initializes all screen speedrunner mod handlers.
     */
    public static void initializeScreenHandlers() {
        SpeedrunnerMod.debug("Initialized screen handlers.");
    }
}