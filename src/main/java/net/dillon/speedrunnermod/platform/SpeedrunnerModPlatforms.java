package net.dillon.speedrunnermod.platform;

import net.dillon.dillonlib.platform.ModPlatform;
import net.dillon.dillonlib.platform.PlatformLoader;
import net.dillon.speedrunnermod.helper.ModConstants;

public class SpeedrunnerModPlatforms {
    private static final ModPlatform PLATFORM = PlatformLoader.load(ModPlatform.class, ModConstants.MOD_ID);

    public static ModPlatform getPlatform() {
        return PLATFORM;
    }
}