package net.dillon.speedrunnermod.platform;

import net.dillon.dillonlib.platform.Platforms;
import net.dillon.dillonlib.platform.info.ModReference;

public class ModReferences {
    public static final ModReference QUALITY_OF_QUESO = new ModReference("qualityofqueso");
    public static final ModReference SIMPLE_KEYBINDS = new ModReference("simplekeybinds");

    public static boolean isModLoaded(ModReference mod) {
        return Platforms.getDillonLibMixinPlatform().isModLoaded(mod);
    }
}