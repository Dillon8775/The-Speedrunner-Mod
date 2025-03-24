package net.dillon.speedrunnermod.client.screen;

import net.dillon.speedrunnermod.screen.ModScreenHandlerTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

/**
 * All handled screens for the speedrunner mod.
 */
@Environment(EnvType.CLIENT)
public class ModHandledScreens {

    public static void registerScreens() {
        HandledScreens.register(ModScreenHandlerTypes.WORKBENCH, WorkbenchScreen::new);
    }
}