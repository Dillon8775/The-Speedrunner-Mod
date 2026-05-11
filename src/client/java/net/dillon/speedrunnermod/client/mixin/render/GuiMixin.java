package net.dillon.speedrunnermod.client.mixin.render;

import net.dillon.speedrunnermod.effect.ModStatusEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

@Mixin(Gui.class)
public class GuiMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    /**
     * @return the new dragon's aura heart to render.
     */
    @ModifyArg(method = "extractHeart", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"), index = 1)
    private Identifier modifyToDragonsAuraHeart(Identifier original) {
        LocalPlayer player = this.minecraft.player;
        if (player != null && player.hasEffect(ModStatusEffects.DRAGONS_AURA)) {
            return this.dragonsAuraHeart(original);
        }
        return original;
    }

    /**
     * @return the dragon's aura identifier.
     */
    @Unique
    private Identifier dragonsAuraHeart(Identifier original) {
        String path = original.getPath();

        switch (path) {
            case "hud/heart/full" -> {
                return ofSpeedrunnerMod("hud/heart/aura_full");
            }
            case "hud/heart/full_blinking" -> {
                return ofSpeedrunnerMod("hud/heart/aura_full_blinking");
            }
            case "hud/heart/half" -> {
                return ofSpeedrunnerMod("hud/heart/aura_half");
            }
            case "hud/heart/half_blinking" -> {
                return ofSpeedrunnerMod("hud/heart/aura_half_blinking");
            }
            case "hud/heart/hardcore_full" -> {
                return ofSpeedrunnerMod("hud/heart/aura_hardcore_full");
            }
            case "hud/heart/hardcore_full_blinking" -> {
                return ofSpeedrunnerMod("hud/heart/aura_hardcore_full_blinking");
            }
            case "hud/heart/hardcore_half" -> {
                return ofSpeedrunnerMod("hud/heart/aura_hardcore_half");
            }
            case "hud/heart/hardcore_half_blinking" -> {
                return ofSpeedrunnerMod("hud/heart/aura_hardcore_half_blinking");
            }
        }

        return original;
    }
}