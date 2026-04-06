package net.dillon.speedrunnermod.mixin.client.render;

import net.dillon.speedrunnermod.entity.ModStatusEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

@Mixin(Gui.HeartType.class)
public class HeartTypeMixin {
    @Unique
    private static final Identifier AURA_HALF_BLINKING = ofSpeedrunnerMod("hud/heart/aura_half_blinking");
    @Unique
    private static final Identifier AURA_HALF = ofSpeedrunnerMod("hud/heart/aura_half");
    @Unique
    private static final Identifier AURA_FULL_BLINKING = ofSpeedrunnerMod("hud/heart/aura_full_blinking");
    @Unique
    private static final Identifier AURA_FULL = ofSpeedrunnerMod("hud/heart/aura_full");
    @Unique
    private static final Identifier HARDCORE_AURA_HALF_BLINKING = ofSpeedrunnerMod("hud/heart/aura_hardcore_half_blinking");
    @Unique
    private static final Identifier HARDCORE_AURA_HALF = ofSpeedrunnerMod("hud/heart/aura_hardcore_half");
    @Unique
    private static final Identifier HARDCORE_AURA_FULL_BLINKING = ofSpeedrunnerMod("hud/heart/aura_hardcore_full_blinking");
    @Unique
    private static final Identifier HARDCORE_AURA_FULL = ofSpeedrunnerMod("hud/heart/aura_hardcore_full");

    /**
     * Renders the dragon's hearts on the player when they have the {@code Dragon's Aura} effect.
     */
    @Inject(method = "getSprite", at = @At("HEAD"), cancellable = true)
    private void redirectTexture(boolean hardcore, boolean half, boolean blinking, CallbackInfoReturnable<Identifier> cir) {
        Player player = Minecraft.getInstance().player;

        if ((Object)this != Gui.HeartType.NORMAL) {
            return;
        }

        if (player != null && player.hasEffect(ModStatusEffects.DRAGONS_AURA)) {
            if (!hardcore) {
                if (half) {
                    cir.setReturnValue(blinking ? AURA_HALF_BLINKING : AURA_HALF);
                } else {
                    cir.setReturnValue(blinking ? AURA_FULL_BLINKING : AURA_FULL);
                }
            } else if (half) {
                cir.setReturnValue(blinking ? HARDCORE_AURA_HALF_BLINKING : HARDCORE_AURA_HALF);
            } else {
                cir.setReturnValue(blinking ? HARDCORE_AURA_FULL_BLINKING : HARDCORE_AURA_FULL);
            }
        }
    }
}