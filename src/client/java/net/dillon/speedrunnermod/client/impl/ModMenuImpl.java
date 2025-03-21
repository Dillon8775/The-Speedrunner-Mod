package net.dillon.speedrunnermod.client.impl;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.dillon.speedrunnermod.client.screen.base.MainScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

/**
 * Allows the options screen to be opened with the {@code Mod Menu} mod.
 */
@Environment(EnvType.CLIENT)
public class ModMenuImpl implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return screen -> new MainScreen(null, MinecraftClient.getInstance().options);
    }
}