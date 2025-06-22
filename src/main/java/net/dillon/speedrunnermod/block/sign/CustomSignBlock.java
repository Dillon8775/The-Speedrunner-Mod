package net.dillon.speedrunnermod.block.sign;

import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.minecraft.block.SignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.util.Identifier;

/**
 * Creates a {@code sign block.}
 */
@Author(Authors.TERRAFORMERSMC)
public class CustomSignBlock extends SignBlock implements CustomSign {
    private final Identifier texture;

    public CustomSignBlock(Identifier texture, WoodType woodType, Settings settings) {
        super(woodType, settings);
        this.texture = texture;
    }

    public CustomSignBlock(Identifier texture, Settings settings) {
        this(texture, WoodType.OAK, settings);
    }

    /**
     * Returns the texture which should be used on custom signs.
     */
    @Override
    public Identifier getTexture() {
        return this.texture;
    }
}