package net.dillon.speedrunnermod.util;

import net.dillon.speedrunnermod.helper.ModHelper;

public class ClientMixinPluginUtil extends AbstractMixinPluginUtil {
    private static final String FOG_RENDERER_MIXIN = "net.dillon.speedrunnermod.mixin.client.render.FogRendererMixin";
    private static final String OPTION_INSTANCE_MIXIN = "net.dillon.speedrunnermod.mixin.client.OptionInstanceMixin";

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
        if (!this.readOptionAsBoolean("mixins", "abstract_client_player_mixin")
                && mixinClassName.equals("net.dillon.speedrunnermod.mixin.client.fix.AbstractClientPlayerMixin")) {
            this.setReason("\"abstract_client_player_mixin\" is disabled.");
            return false;
        }

        if (!this.readOptionAsBoolean("mixins", "fog_mixins")
                && (mixinClassName.equals(FOG_RENDERER_MIXIN) || mixinClassName.equals("net.dillon.speedrunnermod.mixin.client.render.LavaFogEnvironmentMixin"))) {
            this.setReason("\"fog_mixins\" is disabled.");
            return false;
        }
        if (!this.readOptionAsBoolean("mixins", "option_instance_mixin")
                && mixinClassName.equals(OPTION_INSTANCE_MIXIN)) {
            this.setReason("\"option_instance_mixin\" is disabled.");
            return false;
        }
        if (!this.readOptionAsBoolean("mixins", "logo_renderer_mixin")
                && mixinClassName.equals("net.dillon.speedrunnermod.mixin.client.screen.LogoRendererMixin")) {
            this.setReason("\"logo_renderer_mixin\" is disabled.");
            return false;
        }
        return true;
    }

    @Override
    public String configFileName() {
        return ModHelper.CLIENT_CONFIG_FILE_NAME;
    }
}