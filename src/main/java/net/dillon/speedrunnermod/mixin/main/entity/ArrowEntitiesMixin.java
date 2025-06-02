package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModConstants;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(value = {ArrowEntity.class, SpectralArrowEntity.class})
public abstract class ArrowEntitiesMixin extends PersistentProjectileEntity {

    public ArrowEntitiesMixin(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Makes beds explode when hit with an arrow.
     */
    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        if (options().main.arrowsDestroyBeds && !(this.getWorld().getRegistryKey() == World.OVERWORLD) && blockHitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos blockPos = blockHitResult.getBlockPos();
            BlockState blockState = this.getWorld().getBlockState(blockPos);

            if (blockState.getBlock().getDefaultState().isIn(BlockTags.BEDS)) {
                this.discard();
                this.getWorld().removeBlock(blockPos, false);
                this.getWorld().createExplosion(this, getX(), getY(), getZ(), ModConstants.bedBlockExplosionPower(this.getWorld()), true, World.ExplosionSourceType.BLOCK);
            }
        }
        super.onBlockHit(blockHitResult);
    }
}