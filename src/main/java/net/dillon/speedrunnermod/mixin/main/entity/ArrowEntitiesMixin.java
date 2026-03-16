package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.entity.projectile.arrow.SpectralArrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(value = {Arrow.class, SpectralArrow.class})
public abstract class ArrowEntitiesMixin extends AbstractArrow {

    public ArrowEntitiesMixin(EntityType<? extends AbstractArrow> entityType, Level world) {
        super(entityType, world);
    }

    /**
     * Makes beds explode when hit with an arrow.
     */
    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        if (options().main.arrowsDestroyBeds.getCurrentValue() && !(this.level().dimension() == Level.OVERWORLD) && blockHitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos blockPos = blockHitResult.getBlockPos();
            BlockState blockState = this.level().getBlockState(blockPos);

            if (blockState.getBlock().defaultBlockState().is(BlockTags.BEDS)) {
                this.discard();
                this.level().removeBlock(blockPos, false);
                this.level().explode(this, getX(), getY(), getZ(), ModUtil.getBedBlockExplosionPower(this.level()), true, Level.ExplosionInteraction.BLOCK);
            }
        }
        super.onHitBlock(blockHitResult);
    }
}