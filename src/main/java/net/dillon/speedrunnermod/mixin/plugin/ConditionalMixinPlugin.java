package net.dillon.speedrunnermod.mixin.plugin;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.util.AI;
import net.dillon.speedrunnermod.util.ModUtil;
import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Set;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.isEnvironmentTypeServer;
import static net.dillon.speedrunnermod.option.ModOptions.isSafe;

@AI
public class ConditionalMixinPlugin implements IMixinConfigPlugin {

    /**
     * Disables certain mixins from loading if the {@code apply fog mixin} advanced option is disabled.
     */
    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return !shouldNotApply(mixinClassName);
    }

    // Other methods...
    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    /**
     * Returns mixins that should not apply based on certain conditions.
     */
    private boolean shouldNotApply(String mixinClassName) {
        return !readOptionAsBoolean() && mixinClassName.equals("net.dillon.speedrunnermod.mixin.main.world.TBSurfaceRuleDataMixin");
    }

    /**
     * Reads a boolean from the options file.
     * <p>Used with conditional mixin plugins to prevent crashing.</p>
     */
    @AI
    private static boolean readOptionAsBoolean() {
        File configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), ModUtil.CONFIG_FILE_NAME);

        if (!configFile.exists()) {
            // Default to true if the config file doesn't exist
            return true;
        }

        try (FileReader reader = new FileReader(configFile)) {
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
            if (json.has("mixins")) {
                JsonObject mixins = json.getAsJsonObject("mixins");
                if (mixins.has("terra_blender_surface_rule_data_mixin")) {
                    JsonObject optionValue = mixins.getAsJsonObject("terra_blender_surface_rule_data_mixin");
                    if (optionValue.has("value")) {
                        return optionValue.get("value").getAsBoolean();
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