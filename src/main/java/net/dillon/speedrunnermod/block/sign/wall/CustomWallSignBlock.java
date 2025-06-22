package net.dillon.speedrunnermod.block.sign.wall;

import net.dillon.speedrunnermod.block.sign.CustomSign;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.util.Identifier;

/**
 * Creates a {@code wall sign block.}
 */
@Author(Authors.TERRAFORMERSMC)
public class CustomWallSignBlock extends WallSignBlock implements CustomSign {
	private final Identifier texture;

	public CustomWallSignBlock(Identifier texture, WoodType woodType, Settings settings) {
		super(woodType, settings);
		this.texture = texture;
	}

	public CustomWallSignBlock(Identifier texture, Settings settings) {
		this(texture, WoodType.OAK, settings);
	}

	@Override
	public Identifier getTexture() {
		return texture;
	}
}