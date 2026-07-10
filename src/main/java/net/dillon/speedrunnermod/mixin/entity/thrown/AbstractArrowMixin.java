package net.dillon.speedrunnermod.mixin.entity.thrown;

import net.dillon.speedrunnermod.helper.ModConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin extends Projectile {

    public AbstractArrowMixin(EntityType<? extends Projectile> type, Level level) {
        super(type, level);
    }

    /**
     * Makes beds explode in any dimension other than the overworld.
     */
    @Inject(method = "onHitBlock", at = @At("TAIL"))
    private void arrowExplodeBed(BlockHitResult hitResult, CallbackInfo ci) {
        if (!options().worldGen.arrowsDestroyBeds.getCurrentValue()) {
            return;
        }

        if (!(this.level().dimension() == Level.OVERWORLD) && hitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos blockPos = hitResult.getBlockPos();
            BlockState blockState = this.level().getBlockState(blockPos);

            if (blockState.getBlock().defaultBlockState().is(BlockTags.BEDS)) {
                this.discard();
                this.level().removeBlock(blockPos, false);
                this.level().explode(this, getX(), getY(), getZ(), ModConstants.getBedBlockExplosionPower(this.level()), true, Level.ExplosionInteraction.BLOCK);
            }
        }
    }
}