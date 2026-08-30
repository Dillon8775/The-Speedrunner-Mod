package net.dillon.speedrunnermod.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.dillon.dillonlib.mixinplugin.MixinPluginUtil;
import net.dillon.dillonlib.mixinplugin.PredicateEntry;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.option.ModBaseOptionsHandler;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.isEnvironmentTypeServer;
import static net.dillon.speedrunnermod.option.CommonModOptions.isSafe;

/**
 * Abstract representation of a mixin plugin utility class.
 */
public abstract class AbstractMixinPluginUtil extends MixinPluginUtil {

    /**
     * @return the config file name used for this plugin.
     */
    public abstract String configFileName();

    @Override
    public Logger logger() {
        return LoggerFactory.getLogger("SpeedrunnerMod/Mixin");
    }

    @Override
    public String mixinDirectory() {
        return "net.dillon.speedrunnermod.mixin.";
    }

    @Override
    public abstract List<PredicateEntry> entries();

    /**
     * Reads a boolean from the options file.
     * <p>Used with conditional mixin plugins to prevent crashing.</p>
     */
    public boolean readOptionAsBoolean(String optionCategory, String optionValue) {
        File configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), this.configFileName());

        if (!configFile.exists()) {
            // Default to true if the config file doesn't exist
            return true;
        }

        try (FileReader reader = new FileReader(configFile)) {
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
            if (json.has(optionCategory)) {
                JsonObject mixins = json.getAsJsonObject(optionCategory);
                if (mixins.has(optionValue)) {
                    JsonObject option = mixins.getAsJsonObject(optionValue);
                    if (option.has(ModBaseOptionsHandler.CURRENT_VALUE)) {
                        return option.get(ModBaseOptionsHandler.CURRENT_VALUE).getAsBoolean();
                    }
                }
            }
        } catch (Exception e) {
            if (isEnvironmentTypeServer()) {
                throw new IllegalStateException("Failed to read config for mixin plugin: " + e.getMessage() + ". This is likely caused to updating to the newest version of the speedrunner mod, please relaunch the server and everything should work.");
            } else {
                SpeedrunnerMod.LOGGER.error("Failed to read config for mixin plugin: {}", e.getMessage());
                isSafe(false);
            }
        }

        return true;
    }
}