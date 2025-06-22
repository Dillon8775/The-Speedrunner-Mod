package net.dillon.speedrunnermod.block.sign.hanging;

import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.minecraft.block.HangingSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.util.Identifier;

/**
 * Creates a {@code hanging sign block.}
 */
@Author(Authors.TERRAFORMERSMC)
public class CustomHangingSignBlock extends HangingSignBlock implements CustomHangingSign {
    private final Identifier texture;
    private final Identifier guiTexture;

    public CustomHangingSignBlock(Identifier texture, Identifier guiTexture, WoodType woodType, Settings settings) {
        super(woodType, settings);
        this.texture = texture;
        this.guiTexture = guiTexture;
    }

    public CustomHangingSignBlock(Identifier texture, Identifier guiTexture, Settings settings) {
        this(texture, guiTexture, WoodType.OAK, settings);
    }

    @Override
    public Identifier getGuiTexture() {
        return this.guiTexture;
    }

    @Override
    public Identifier getTexture() {
        return this.texture;
    }
}