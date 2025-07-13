package net.dillon.speedrunnermod.block.sign;

import net.minecraft.block.SignBlock;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Creates a {@code sign block.}
 */
public class CustomSignBlock extends SignBlock {

    public CustomSignBlock(String id, Settings settings) {
        super(TerraformSignBlockHelper.registerDefaultWoodType(ofSpeedrunnerMod(id)), settings);
    }
}