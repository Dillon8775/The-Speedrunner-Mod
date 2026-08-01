package net.dillon.speedrunnermod.platform.mixinsafe;

import net.dillon.dillonlib.platform.mixinsafe.MixinModPlatform;
import net.dillon.speedrunnermod.helper.ModConstants;

public class MixinSpeedrunnerModPlatform extends MixinModPlatform {

    @Override
    public String modId() {
        return ModConstants.MOD_ID;
    }

    @Override
    public boolean shouldApplyFactories() {
        return true;
    }

    @Override
    public boolean shouldApplyFullBright() {
        return true;
    }
}