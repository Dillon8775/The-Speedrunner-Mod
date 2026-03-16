package net.dillon.speedrunnermod.block.sign.hanging;

import net.dillon.speedrunnermod.block.sign.TerraformSignBlockHelper;
import net.minecraft.world.level.block.CeilingHangingSignBlock;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Creates a {@code hanging sign block.}
 */
public class CustomHangingSignBlock extends CeilingHangingSignBlock {

    public CustomHangingSignBlock(String id, Properties settings) {
        super(TerraformSignBlockHelper.registerDefaultWoodType(ofSpeedrunnerMod(id)), settings);
    }
}