package net.dillon.speedrunnermod.util;

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
                        "\"item_stack_mixin\" is disabled."
                ),
                new PredicateEntry(
                        new String[]{"world.TheEndGatewayBlockEntityMixin"},
                        !ModCommonOptions.INSTANCE.getInstance().mixins().theEndGatewayBlockEntityMixin,
                        "\"the_end_gateway_block_entity_mixin\" is disabled."
                )
        );
    }
}