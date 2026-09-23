package net.dillon.speedrunnermod.screen;

import net.minecraft.client.gui.screens.MenuScreens;

/**
 * All handled screens for the speedrunner mod.
 */
public class ModMenus {

    public static void registerScreens() {
        MenuScreens.register(net.dillon.speedrunnermod.menu.ModMenus.WORKBENCH, WorkbenchScreen::new);
    }
}