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

import static net.dillon.speedrunnermod.option.ModOptions.isSafeToPlay;

@AI
public class ClientConditionalMixinPlugin implements IMixinConfigPlugin {

    /**
     * Disables certain client-side mixins from loading if the {@code apply fog mixin} advanced option is disabled.
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
     * Returns client-side mixins that should not apply based on certain conditions.
     */
    private boolean shouldNotApply(String mixinClassName) {
        return !readOptionAsBoolean("background_renderer_mixin") && mixinClassName.equals("net.dillon.speedrunnermod.mixin.client.Fog") ||
                !readOptionAsBoolean("simple_option_mixin") && mixinClassName.equals("net.dillon.speedrunnermod.mixin.client.IncreasedBrightness") ||
                !readOptionAsBoolean("logo_drawer_mixin") && mixinClassName.equals("net.dillon.speedrunnermod.mixin.client.screen.LogoDrawerMixin") ||
                !readOptionAsBoolean("render_layers_mixin") && mixinClassName.equals("net.dillon.speedrunnermod.mixin.client.fix.RenderLayersMixin");
    }

    /**
     * Reads a boolean from the options file.
     * <p>Used with conditional mixin plugins to prevent crashing.</p>
     */
    @AI
    private static boolean readOptionAsBoolean(String option) {
        File configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), ModUtil.CLIENT_CONFIG_FILE_NAME);

        if (!configFile.exists()) {
            // Default to true if the config file doesn't exist
            return true;
        }

        try (FileReader reader = new FileReader(configFile)) {
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
            if (json.has("mixins")) {
                JsonObject mixins = json.getAsJsonObject("mixins");
                if (mixins.has(option)) {
                    JsonObject optionValue = mixins.getAsJsonObject(option);
                    if (optionValue.has("value")) {
                        return optionValue.get("value").getAsBoolean();
                    }
                }
            }
        }
        catch (Exception e) {
            SpeedrunnerMod.error("Failed to read config for mixin plugin: " + e.getMessage());
            isSafeToPlay(false);
        }

        return true;
    }
}