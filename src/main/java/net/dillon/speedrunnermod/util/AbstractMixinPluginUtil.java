package net.dillon.speedrunnermod.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.option.BaseOptions;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.isEnvironmentTypeServer;
import static net.dillon.speedrunnermod.option.ModOptions.isSafe;

/**
 * Abstract representation of a mixin plugin utility class.
 */
public abstract class AbstractMixinPluginUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger("Speedrunner Mod/Mixin");

    /**
     * @return the config.
     */
    public abstract String configFileName();

    /**
     * @return the list of mapped mixin entries to be disabled.
     */
    public abstract List<PredicateEntry> entries();

    /**
     * @return {@code false} if mixin should not apply.
     */
    public final boolean shouldNotApply(String targetClassName, String mixinClassName) {
        for (PredicateEntry entry : entries()) {
            if (entry.condition()) {
                for (String s : entry.mixins()) {
                    String name = "net.dillon.speedrunnermod.mixin." + s;
                    if (name.equals(mixinClassName)) {
                        LOGGER.warn("Skipping mixin {} for class {}: {}",
                                mixinClassName,
                                targetClassName,
                                entry.reason()
                        );
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * @return if the quality of queso mod is loaded.
     */
    public boolean isQualityOfQuesoLoaded() {
        return FabricLoader.getInstance().isModLoaded("qualityofqueso");
    }

    /**
     * @return if the simple keybinds is loaded.
     */
    public boolean isSimpleKeybindsLoaded() {
        return FabricLoader.getInstance().isModLoaded("simplekeybinds");
    }

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
                    if (option.has(BaseOptions.CURRENT_VALUE)) {
                        return option.get(BaseOptions.CURRENT_VALUE).getAsBoolean();
                    }
                }
            }
        } catch (Exception e) {
            if (isEnvironmentTypeServer()) {
                throw new IllegalStateException("Failed to read config for mixin plugin: " + e.getMessage() + ". This is likely caused to updating to the newest version of the speedrunner mod, please relaunch the server and everything should work.");
            } else {
                SpeedrunnerMod.error("Failed to read config for mixin plugin: " + e.getMessage());
                isSafe(false);
            }
        }

        return true;
    }
}