package net.dillon.speedrunnermod.mixin.client.render;

import net.dillon.speedrunnermod.component.ModMobEffects;
import net.dillon.speedrunnermod.component.effect.WitheredEffect;
import net.minecraft.client.gui.Hud;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Hud.HeartType.class)
public class HeartTypeMixin {

    /**
     * Makes the players heart appear withered if they have the {@link WitheredEffect}.
     */
    @Redirect(method = "forPlayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;hasEffect(Lnet/minecraft/core/Holder;)Z", ordinal = 1))
    private static boolean witheredHeartsForWitheredEffect(Player player, Holder<MobEffect> holder) {
        return player.hasEffect(MobEffects.WITHER) || player.hasEffect(ModMobEffects.WITHERED);
    }
}