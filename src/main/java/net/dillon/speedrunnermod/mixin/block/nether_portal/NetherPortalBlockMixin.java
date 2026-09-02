package net.dillon.speedrunnermod.mixin.block.nether_portal;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.NetherPortalBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;

@Mixin(NetherPortalBlock.class)
public class NetherPortalBlockMixin {

    /**
     * Returns the {@code custom nether portal delay}, according to the speedrunner mod option.
     */
    @Inject(method = "getPortalTransitionTime", at = @At("RETURN"), cancellable = true)
    private void applyNetherPortalDelay(ServerLevel world, Entity entity, CallbackInfoReturnable<Integer> cir) {
        if (entity instanceof Player playerEntity) {
            if (common().worldgen().netherPortalDelay >= 0) {
                cir.setReturnValue(playerEntity.getAbilities().invulnerable ? 1 : common().worldgen().netherPortalDelay * 20);
            }
        }
    }
}