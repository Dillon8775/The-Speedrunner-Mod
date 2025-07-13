package net.dillon.speedrunnermod.block.sign.hanging;

import net.dillon.speedrunnermod.block.sign.TerraformSignBlockHelper;
import net.minecraft.block.HangingSignBlock;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Creates a {@code hanging sign block.}
 */
public class CustomHangingSignBlock extends HangingSignBlock {

    public CustomHangingSignBlock(String id, Settings settings) {
        super(TerraformSignBlockHelper.registerDefaultWoodType(ofSpeedrunnerMod(id)), settings);
    }
}