package net.dillon.speedrunnermod.mixin.main.block;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BedBlock.class)
public class BedBlockMixin {

    /**
     * Cancels the original explosion method, and creates a new one, increasing the explosion power with beds in the end.
     */
    @Inject(method = "useWithoutItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;explode(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;Lnet/minecraft/world/phys/Vec3;FZLnet/minecraft/world/level/Level$ExplosionInteraction;)V"), cancellable = true)
    private void changeBedExplosionPower(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit, CallbackInfoReturnable<InteractionResult> cir) {
        cir.cancel();
        Vec3 vec3d = pos.getCenter();
        world.explode(null, world.damageSources().badRespawnPointExplosion(vec3d), null, vec3d, ModUtil.getBedBlockExplosionPower(world), true, Level.ExplosionInteraction.BLOCK);
        cir.setReturnValue(InteractionResult.SUCCESS);
    }
}