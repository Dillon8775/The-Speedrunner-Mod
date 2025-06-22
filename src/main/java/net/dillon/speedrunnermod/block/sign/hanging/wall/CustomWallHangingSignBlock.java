package net.dillon.speedrunnermod.block.sign.hanging.wall;

import net.dillon.speedrunnermod.block.sign.hanging.CustomHangingSign;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.minecraft.block.WallHangingSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.util.Identifier;

/**
 * Creates a {@code wall hanging sign block.}
 */
@Author(Authors.TERRAFORMERSMC)
public class CustomWallHangingSignBlock extends WallHangingSignBlock implements CustomHangingSign {
	private final Identifier texture;
	private final Identifier guiTexture;

	public CustomWallHangingSignBlock(Identifier texture, Identifier guiTexture, WoodType woodType, Settings settings) {
		super(woodType, settings);
		this.texture = texture;
		this.guiTexture = guiTexture;
	}

	public CustomWallHangingSignBlock(Identifier texture, Identifier guiTexture, Settings settings) {
		this(texture, guiTexture, WoodType.OAK, settings);
	}

	@Override
	public Identifier getTexture() {
		return texture;
	}

	@Override
	public Identifier getGuiTexture() {
		return guiTexture;
	}
}