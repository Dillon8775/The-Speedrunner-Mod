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

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.isEnvironmentTypeServer;
import static net.dillon.speedrunnermod.option.ModOptions.isSafe;

/**
 * Abstract representation of a mixin plugin utility class.
 */
public abstract class AbstractMixinPluginUtil {
    private final Logger logger = LoggerFactory.getLogger("Speedrunner Mod/Mixin");
    private String reason = "";

    /**
     * @return the current mixin logger.
     */
    public Logger getLogger() {
        return this.logger;
    }

    /**
     * @return the reason a mixin should not be applied.
     */
    public String getReason() {
        return this.reason;
    }

    /**
     * Sets the reason a mixin should not be applied.
     */
    public void setReason(String reason) {
        this.reason = reason;
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
     * @return {@code true} if a mixin should be applied to the game.
     */
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        boolean bl = shouldApply(mixinClassName);
        if (!bl) {
            this.getLogger().warn("Skipping mixin {} for target {} because it should not be applied. Reason: {}",
                    mixinClassName,
                    targetClassName,
                    this.getReason().isEmpty() ? "null" : this.getReason());
        }
        return bl;
    }

    /**
     * Returns mixins that should not apply based on certain conditions.
     */
    public abstract boolean shouldApply(String mixinClassName);

    /**
     * @return the config.
     */
    public abstract String configFileName();

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