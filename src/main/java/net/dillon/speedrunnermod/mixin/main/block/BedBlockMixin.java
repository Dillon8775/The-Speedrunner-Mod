package net.dillon.speedrunnermod.mixin.main.block;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BedBlock.class)
public class BedBlockMixin {

    /**
     * Cancels the original explosion method, and creates a new one, increasing the explosion power with beds in the end.
     */
    @Inject(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/damage/DamageSource;Lnet/minecraft/world/explosion/ExplosionBehavior;Lnet/minecraft/util/math/Vec3d;FZLnet/minecraft/world/World$ExplosionSourceType;)V"), cancellable = true)
    private void changeBedExplosionPower(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        cir.cancel();
        Vec3d vec3d = pos.toCenterPos();
        world.createExplosion(null, world.getDamageSources().badRespawnPoint(vec3d), null, vec3d, ModUtil.getBedBlockExplosionPower(world), true, World.ExplosionSourceType.BLOCK);
        cir.setReturnValue(ActionResult.SUCCESS);
    }
}