package net.dillon.speedrunnermod.util;

import net.dillon.dillonlib.mixinplugin.PredicateEntry;
import net.dillon.speedrunnermod.option.ModClientOptions;
import net.dillon.speedrunnermod.platform.ModReferences;

import java.util.List;

public class ClientSpeedrunnerModMixinPlugin extends AbstractMixinPluginUtil {
    private static final String FOG_RENDERER_MIXIN = "client.render.FogRendererMixin";
    private static final String OPTION_INSTANCE_MIXIN = "client.OptionInstanceMixin";

    @Override
    public List<PredicateEntry> entries() {
        return List.of(
                new PredicateEntry(
                        new String[]{OPTION_INSTANCE_MIXIN},
                        ModReferences.isModLoaded(ModReferences.SIMPLE_KEYBINDS) || !ModClientOptions.INSTANCE.getInstance().mixins().optionInstanceMixin,
                        "either Simple Keybinds mod is loaded, and already modifies what this mod does, or OptionInstanceMixin  is disabled via config."
                ),
                new PredicateEntry(
                        new String[]{FOG_RENDERER_MIXIN},
                        ModReferences.isModLoaded(ModReferences.QUALITY_OF_QUESO),
                        "Quality of Queso mod is loaded, disabling because this mod adds more versatility and configuration."
                ),
                new PredicateEntry(
                        new String[]{FOG_RENDERER_MIXIN, "client.render.LavaFogEnvironmentMixin"},
                        !ModClientOptions.INSTANCE.getInstance().mixins().fogMixins,
                        "Fog Mixins are disabled via config."
                ),
                new PredicateEntry(
                        new String[]{"fix.AbstractClientPlayerMixin"},
                        !ModClientOptions.INSTANCE.getInstance().mixins().abstractClientPlayerMixin,
                        "AbstractClientPlayerMixin is disabled via config."
                ),
                new PredicateEntry(
                        new String[]{"client.screen.LogoRendererMixin"},
                        !ModClientOptions.INSTANCE.getInstance().mixins().logoRendererMixin,
                        "LogoRendererMixin is disabled via config."
                )
        );
    }
}