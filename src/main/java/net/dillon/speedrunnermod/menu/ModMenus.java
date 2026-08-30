package net.dillon.speedrunnermod.menu;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

/**
 * All mod screen handler types.
 */
public class ModMenus {
    public static final MenuType<WorkbenchMenu> WORKBENCH = Registry.register(
            BuiltInRegistries.MENU, "workbench", new MenuType<>(WorkbenchMenu::new, FeatureFlags.VANILLA_SET));

    /**
     * Initializes all screen speedrunner mod handlers.
     */
    public static void initializeMenus() {
        SpeedrunnerMod.LOGGER.debug("Initialized screen handlers.");
    }
}