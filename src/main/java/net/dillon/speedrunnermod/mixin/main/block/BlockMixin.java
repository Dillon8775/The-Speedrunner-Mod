package net.dillon.speedrunnermod.mixin.main.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(Block.class)
public class BlockMixin {

    /**
     * @author Dillon8775
     * @reason Lowers fall damage. This applies to all entities.
     * <p>If the entity is {@code sneaking}, then the damage can be reduced by {@code ~1.25%.}</p>
     */
    @Overwrite
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
        float fallDamage;
        if (!options().main.fallDamage) {
            fallDamage = 0.0F;
        } else {
            fallDamage = options().main.playingMode.doom() ? 1.0F : 0.7F;
            if (entity.isSneaking()) {
                fallDamage = fallDamage / 1.25F;
            }
        }
        entity.handleFallDamage(fallDistance, fallDamage, entity.getDamageSources().fall());
    }
}