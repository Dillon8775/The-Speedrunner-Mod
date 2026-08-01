package net.dillon.speedrunnermod.util;

import net.dillon.dillonlib.mixinplugin.PredicateEntry;

import java.util.List;

public class SpeedrunnerModMixinPlugin extends AbstractMixinPluginUtil {

    @Override
    public String configFileName() {
        return "speedrunnermod.json";
    }

    @Override
    public List<PredicateEntry> entries() {
        return List.of(
                new PredicateEntry(
                        new String[]{"attribute.ItemStackMixin"},
                        !this.readOptionAsBoolean("mixins", "item_stack_mixin"),
                        "\"item_stack_mixin\" is disabled."
                ),
                new PredicateEntry(
                        new String[]{"world.TheEndGatewayBlockEntityMixin"},
                        !this.readOptionAsBoolean("mixins", "the_end_gateway_block_entity_mixin"),
                        "\"the_end_gateway_block_entity_mixin\" is disabled."
                )
        );
    }
}