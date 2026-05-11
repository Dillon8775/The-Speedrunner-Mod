package net.dillon.speedrunnermod.util;

public class MixinPluginUtil extends AbstractMixinPluginUtil {

    @Override
    public boolean shouldApply(String mixinClassName) {
        if (!this.readOptionAsBoolean("mixins", "the_end_gateway_block_entity_mixin")
                && mixinClassName.equals("net.dillon.speedrunnermod.mixin.world.TheEndGatewayBlockEntityMixin")) {
            this.setReason("\"the_end_gateway_block_entity_mixin\" function is disabled.");
            return false;
        }
        return true;
    }

    @Override
    public String configFileName() {
        return ModUtil.CONFIG_FILE_NAME;
    }
}