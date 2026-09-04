package net.dillon.speedrunnermod.util;

import net.dillon.dillonlib.mixinplugin.MessageType;
import net.dillon.dillonlib.mixinplugin.PredicateEntry;
import net.dillon.speedrunnermod.option.ModCommonOptions;

import java.util.List;

public class SpeedrunnerModMixinPlugin extends AbstractMixinPluginUtil {

    @Override
    public List<PredicateEntry> entries() {
        return List.of(
                new PredicateEntry(
                        new String[]{"attribute.ItemStackMixin"},
                        !ModCommonOptions.INSTANCE.getInstance().mixins().itemStackMixin,
                        "ItemStackMixin is disabled via config."
                ),
                new PredicateEntry(
                        new String[]{"world.TheEndGatewayBlockEntityMixin"},
                        !ModCommonOptions.INSTANCE.getInstance().mixins().theEndGatewayBlockEntityMixin,
                        "TheEndGatewayBlockEntityMixin is disabled via config."
                ),
                new PredicateEntry(
                        new String[]{"registry.RegistryLoadTaskPendingRegistrationMixin"},
                        !Overrides.applyRegistryLoaderMixin(),
                        "Apply Registry Loader Mixin is disabled via an override.",
                        MessageType.ERROR
                )
        );
    }
}