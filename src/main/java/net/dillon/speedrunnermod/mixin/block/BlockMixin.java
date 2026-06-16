package net.dillon.speedrunnermod.mixin.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(Block.class)
public class BlockMixin {

    /**
     * @author Dillon8775
     * @reason Lowers fall damage. This applies to all entities.
     * <p>If the entity is {@code sneaking}, then the damage can be reduced by {@code ~1.25%.}</p>
     */
    @Overwrite
    public void fallOn(Level world, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
        float fallDamage;
        if (!options().general.fallDamage.getCurrentValue()) {
            fallDamage = 0.0F;
        } else {
            fallDamage = isDoomMode() ? 1.0F : 0.7F;
            if (entity.isShiftKeyDown()) {
                fallDamage = fallDamage / 1.25F;
            }
        }
        entity.causeFallDamage(fallDistance, fallDamage, entity.damageSources().fall());
    }
}