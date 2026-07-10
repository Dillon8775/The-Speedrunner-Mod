package net.dillon.speedrunnermod.util;

import net.dillon.speedrunnermod.helper.ModHelper;

public class MixinPluginUtil extends AbstractMixinPluginUtil {

    @Override
    public boolean shouldApply(String mixinClassName) {
        if (!this.readOptionAsBoolean("mixins", "item_stack_mixin")
                && mixinClassName.equals("net.dillon.speedrunnermod.mixin.attribute.ItemStackMixin")) {
            this.setReason("\"item_stack_mixin\" function is disabled.");
            return false;
        }
        if (!this.readOptionAsBoolean("mixins", "the_end_gateway_block_entity_mixin")
                && mixinClassName.equals("net.dillon.speedrunnermod.mixin.world.TheEndGatewayBlockEntityMixin")) {
            this.setReason("\"the_end_gateway_block_entity_mixin\" function is disabled.");
            return false;
        }
        return true;
    }

    @Override
    public String configFileName() {
        return ModHelper.CONFIG_FILE_NAME;
    }
}