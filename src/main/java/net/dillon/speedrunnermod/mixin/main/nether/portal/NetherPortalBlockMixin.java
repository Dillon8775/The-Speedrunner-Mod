package net.dillon.speedrunnermod.mixin.main.nether.portal;

import net.minecraft.block.NetherPortalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(NetherPortalBlock.class)
public class NetherPortalBlockMixin {

    /**
     * Returns the {@code custom nether portal delay}, according to the speedrunner mod option.
     */
    @Inject(method = "getPortalDelay", at = @At("RETURN"), cancellable = true)
    private void portalDelay(ServerWorld world, Entity entity, CallbackInfoReturnable<Integer> cir) {
        if (entity instanceof PlayerEntity playerEntity) {
            if (options().main.netherPortalDelay >= 0) {
                cir.setReturnValue(playerEntity.getAbilities().invulnerable ? 1 : options().main.netherPortalDelay * 20);
            }
        }
    }
}