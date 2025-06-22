package net.dillon.speedrunnermod.block.sign;

import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.minecraft.util.Identifier;

/**
 * Specifies a sign created with the speedrunner mod.
 */
@Author(Authors.TERRAFORMERSMC)
public interface CustomSign {
    Identifier getTexture();
}