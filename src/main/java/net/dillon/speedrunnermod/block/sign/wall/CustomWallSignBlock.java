package net.dillon.speedrunnermod.block.sign.wall;

import net.dillon.speedrunnermod.block.sign.TerraformSignBlockHelper;
import net.minecraft.world.level.block.WallSignBlock;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Creates a {@code wall sign block.}
 */
public class CustomWallSignBlock extends WallSignBlock {

	public CustomWallSignBlock(String id, Properties settings) {
		super(TerraformSignBlockHelper.registerDefaultWoodType(ofSpeedrunnerMod(id)), settings);
	}
}