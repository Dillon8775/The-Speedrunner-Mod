package net.dillon.speedrunnermod.client.screen;

import net.dillon.speedrunnermod.menu.ModMenus;
import net.minecraft.client.gui.screens.MenuScreens;

/**
 * All handled screens for the speedrunner mod.
 */
public class ModHandledScreens {

    public static void registerScreens() {
        MenuScreens.register(ModMenus.WORKBENCH, WorkbenchScreen::new);
    }
}