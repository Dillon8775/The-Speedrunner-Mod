package net.dillon.speedrunnermod.block.sign.hanging;

import net.dillon.speedrunnermod.block.sign.CustomSign;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.minecraft.util.Identifier;

/**
 * Specifies a hanging sign created with the speedrunner mod.
 */
@Author(Authors.TERRAFORMERSMC)
public interface CustomHangingSign extends CustomSign {
    Identifier getGuiTexture();
}