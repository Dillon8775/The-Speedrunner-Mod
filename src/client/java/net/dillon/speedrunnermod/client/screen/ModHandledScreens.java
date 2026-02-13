package net.dillon.speedrunnermod.client.screen;

import net.dillon.speedrunnermod.screen.ModScreenHandlerTypes;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

/**
 * All handled screens for the speedrunner mod.
 */
public class ModHandledScreens {

    public static void registerScreens() {
        HandledScreens.register(ModScreenHandlerTypes.WORKBENCH, WorkbenchScreen::new);
    }
}