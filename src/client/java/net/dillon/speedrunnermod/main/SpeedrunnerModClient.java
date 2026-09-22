package net.dillon.speedrunnermod.main;

import net.dillon.speedrunnermod.keybind.ModKeyMappings;
import net.dillon.speedrunnermod.option.ModClientOptions;
import net.dillon.speedrunnermod.packet.ClientModPackets;
import net.dillon.speedrunnermod.particle.ModParticleManager;
import net.dillon.speedrunnermod.screen.ModMenus;
import net.fabricmc.api.ClientModInitializer;

/**
 * The home initializer for the client-side of the Speedrunner Mod.
 */
public class SpeedrunnerModClient implements ClientModInitializer {

    /**
     * Initializes all the client-side {@code speedrunner mod} renderers, configurations, etc.
     */
    @Override
    public void onInitializeClient() {
        ClientModPackets.registerClientBoundPackets();

        ModParticleManager.registerParticleTypes();

        ModMenus.registerScreens();

        try {
            ModKeyMappings.initializeKeybinds();
        } catch (ExceptionInInitializerError o) {
            SpeedrunnerMod.LOGGER.error("Error initializing keybinds. Skipping!");
            o.printStackTrace();
        }

        clientConfigHandler().load();
    }

    /**
     * Returns the {@code client} Speedrunner Mod {@code options.}
     * <p>This should <b>ONLY</b> be called in {@code EnvType.CLIENT} classes and methods.</p>
     */

    public static ModClientOptions client() {
        return ModClientOptions.INSTANCE.getInstance();
    }

    /**
     * Returns the {@code client} Speedrunner Mod {@code options handler} (for saving/loading config).
     * <p>This should <b>ONLY</b> be called in {@code EnvType.CLIENT} classes and methods.</p>
     */
    public static ModClientOptions.ModClientOptionsHandler clientConfigHandler() {
        return ModClientOptions.INSTANCE;
    }

    /**
     * Returns the {@code maximum brightness} value for the speedrunner mod.
     */
    public static double getMaxBrightness() {
        return (double) client().general().fullBrightAmount / 100;
    }
}