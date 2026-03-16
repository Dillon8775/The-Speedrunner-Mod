package net.dillon.speedrunnermod.block.sign;

import net.minecraft.world.level.block.StandingSignBlock;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Creates a {@code sign block.}
 */
public class CustomSignBlock extends StandingSignBlock {

    public CustomSignBlock(String id, Properties settings) {
        super(TerraformSignBlockHelper.registerDefaultWoodType(ofSpeedrunnerMod(id)), settings);
    }
}