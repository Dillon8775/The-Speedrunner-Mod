package net.dillon.speedrunnermod.impl;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.dillon.speedrunnermod.screen.MainScreen;

/**
 * Allows the options screen to be opened with the {@code Mod Menu} mod.
 */
public class ModMenuImpl implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return MainScreen::new;
    }
}