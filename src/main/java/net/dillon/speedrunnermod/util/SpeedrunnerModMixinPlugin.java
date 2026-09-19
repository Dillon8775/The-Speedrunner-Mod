package net.dillon.speedrunnermod.util;

import net.dillon.dillonlib.mixinplugin.PredicateEntry;
import net.dillon.speedrunnermod.option.ModCommonOptions;

import java.util.List;

public class SpeedrunnerModMixinPlugin extends AbstractMixinPluginUtil {

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
                )
        );
    }
}