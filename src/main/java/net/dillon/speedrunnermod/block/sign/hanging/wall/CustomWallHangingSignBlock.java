package net.dillon.speedrunnermod.block.sign.hanging.wall;

import net.dillon.speedrunnermod.block.sign.TerraformSignBlockHelper;
import net.minecraft.block.WallHangingSignBlock;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Creates a {@code wall hanging sign block.}
 */
public class CustomWallHangingSignBlock extends WallHangingSignBlock {

	public CustomWallHangingSignBlock(String id, Settings settings) {
		super(TerraformSignBlockHelper.registerDefaultWoodType(ofSpeedrunnerMod(id)), settings);
	}
}