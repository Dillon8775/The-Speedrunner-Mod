package net.dillon.speedrunnermod.helper;

import net.dillon.dillonlib.util.UpdateChecker;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.platform.SpeedrunnerModPlatforms;
import net.dillon.speedrunnermod.util.RandomChance;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

import static net.dillon.speedrunnermod.option.ModCommonOptions.doomOrDefault;
import static net.dillon.speedrunnermod.option.ModCommonOptions.isDoomMode;

/**
 * Constant variables and values for the Speedrunner Mod.
 */
public class ModConstants {
    public static final String MOD_ID = "speedrunnermod";
    public static final Component MOD_VERSION = Component.literal(SpeedrunnerModPlatforms.getPlatform().modVersion()).withStyle(ChatFormatting.AQUA);
    public static boolean HAS_UPDATE = UpdateChecker.hasUpdate(UpdateChecker.checkForUpdate(
            "speedrunner-mod",
            MOD_VERSION.getString())
    );
    public static final String MC_VERSION = FabricLoader.getInstance().getRawGameVersion();

    public static final int DEFAULT_DOOM_ZOMBIE_FIREBALL_CHARGE_SPEED = 40;
    public static final int DEFAULT_ZOMBIE_FIREBALL_CHARGE_SPEED = 100;
    public static final int DEFAULT_MINION_FIREBALL_CHARGE_SPEED = 120;

    /**
     * Initializes all constant variables.
     */
    public static void initConstants() {
        SpeedrunnerMod.LOGGER.debug("Initialized mod constants.");
    }

    /**
     * @return the bed block explosion power based.
     */
    public static float getBedBlockExplosionPower(Level level) {
        if (isDoomMode()) {
            return level.dimension() == Level.END ? 15.0F : 5.0F;
        } else {
            return 5.0F;
        }
    }

    /**
     * @return how much damage a fireball does when hitting an entity (each 0.5 = half a heart).
     */
    public static float getSmallFireballDamageValue() {
        return doomOrDefault(RandomChance.intInclusive(3, 5), 1.0F);
    }
}