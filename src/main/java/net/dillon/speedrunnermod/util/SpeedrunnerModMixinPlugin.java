package net.dillon.speedrunnermod.util;

import net.dillon.dillonlib.core.DillonLibModReferences;
import net.dillon.dillonlib.mixinplugin.PredicateEntry;
import net.dillon.speedrunnermod.option.ModClientOptions;
import net.dillon.speedrunnermod.option.ModCommonOptions;

import java.util.List;

public class SpeedrunnerModMixinPlugin extends AbstractMixinPluginUtil {
    private static final String FOG_RENDERER_MIXIN = "client.render.FogRendererMixin";

    @Override
    public List<PredicateEntry> entries() {
        return List.of(
                PredicateEntry.ofDebug(
                        PredicateEntry.single("attribute.ItemStackMixin"),
                        !ModCommonOptions.INSTANCE.getInstance().mixins().itemStackMixin,
                        "ItemStackMixin is disabled via config."
                ),
                PredicateEntry.ofDebug(
                        PredicateEntry.single("world.TheEndGatewayBlockEntityMixin"),
                        !ModCommonOptions.INSTANCE.getInstance().mixins().theEndGatewayBlockEntityMixin,
                        "TheEndGatewayBlockEntityMixin is disabled via config."
                ),
                PredicateEntry.ofError(
                        PredicateEntry.single("registry.RegistryLoadTaskPendingRegistrationMixin"),
                        !Overrides.applyRegistryLoaderMixin(),
                        "Apply Registry Loader Mixin is disabled via an override."
                ),
                PredicateEntry.ofDebug(
                        PredicateEntry.single(FOG_RENDERER_MIXIN),
                        DillonLibModReferences.isModLoaded(DillonLibModReferences.QUALITY_OF_QUESO),
                        "Quality of Queso mod is loaded, disabling because this mod adds more versatility and configuration."
                ),
                PredicateEntry.ofDebug(
                        PredicateEntry.ddouble(
                                FOG_RENDERER_MIXIN,
                                "client.render.LavaFogEnvironmentMixin"
                        ),
                        !ModClientOptions.INSTANCE.getInstance().mixins().fogMixins,
                        "Fog Mixins are disabled via config."
                ),
                PredicateEntry.ofDebug(
                        PredicateEntry.single("fix.AbstractClientPlayerMixin"),
                        !ModClientOptions.INSTANCE.getInstance().mixins().abstractClientPlayerMixin,
                        "AbstractClientPlayerMixin is disabled via config."
                ),
                PredicateEntry.ofDebug(
                        PredicateEntry.single("client.screen.LogoRendererMixin"),
                        !ModClientOptions.INSTANCE.getInstance().mixins().logoRendererMixin,
                        "LogoRendererMixin is disabled via config."
                )
        );
    }
}