package net.dillon.speedrunnermod.client.util;

import net.dillon.speedrunnermod.util.AbstractMixinPluginUtil;
import net.dillon.speedrunnermod.util.ModUtil;

public class ClientMixinPluginUtil extends AbstractMixinPluginUtil {
    private static final String FOG_RENDERER_MIXIN = "net.dillon.speedrunnermod.client.mixin.render.FogRendererMixin";
    private static final String OPTION_INSTANCE_MIXIN = "net.dillon.speedrunnermod.client.mixin.OptionInstanceMixin";

    @Override
    public boolean shouldApply(String mixinClassName) {
        if (isSimpleKeybindsLoaded()
                && mixinClassName.equals(OPTION_INSTANCE_MIXIN)) {
            this.setReason("Mod \"Simple Keybinds\" is loaded, not applying increased brightness function, as Simple Keybinds adds this already.");
            return false;
        }
        if (isQualityOfQuesoLoaded()
                && mixinClassName.equals(FOG_RENDERER_MIXIN)) {
            this.setReason("Mod \"Quality of Queso\" is loaded, not applying speedrunner mod's fog function, as Quality of Queso adds this already, with more configuration.");
            return false;
        }
        if (isQualityOfQuesoLoaded()
                && mixinClassName.equals("net.dillon.speedrunnermod.client.mixin.fix.AbstractClientPlayerMixin")) {
            this.setReason("Mod \"Quality of Queso\" is loaded, not applying speedrunner mod's FOV effects, as Quality of Queso completely overwrites it.");
            return false;
        }

        if (!this.readOptionAsBoolean("mixins", "fog_mixins")
                && (mixinClassName.equals(FOG_RENDERER_MIXIN) || mixinClassName.equals("net.dillon.speedrunnermod.client.mixin.render.LavaFogEnvironmentMixin"))) {
            this.setReason("\"fog_mixins\" function is disabled.");
            return false;
        }
        if (!this.readOptionAsBoolean("mixins", "option_instance_mixin")
                && mixinClassName.equals(OPTION_INSTANCE_MIXIN)) {
            this.setReason("\"option_instance_mixin\" function is disabled.");
            return false;
        }
        if (!this.readOptionAsBoolean("mixins", "logo_renderer_mixin")
                && mixinClassName.equals("net.dillon.speedrunnermod.client.mixin.screen.LogoRendererMixin")) {
            this.setReason("\"logo_renderer_mixin\" function is disabled.");
            return false;
        }
        return true;
    }

    @Override
    public String configFileName() {
        return ModUtil.CLIENT_CONFIG_FILE_NAME;
    }
}