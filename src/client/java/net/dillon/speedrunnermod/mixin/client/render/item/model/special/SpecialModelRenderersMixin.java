package net.dillon.speedrunnermod.mixin.client.render.item.model.special;

import com.mojang.serialization.MapCodec;
import net.dillon.speedrunnermod.client.render.GoldenShieldModelRenderer;
import net.dillon.speedrunnermod.client.render.SpeedrunnerShieldModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderers;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

@Mixin(SpecialModelRenderers.class)
public class SpecialModelRenderersMixin {
    @Shadow @Final
    public static ExtraCodecs.LateBoundIdMapper<Identifier, MapCodec<? extends SpecialModelRenderer.Unbaked>> ID_MAPPER;

    @Inject(method = "bootstrap", at = @At("TAIL"))
    private static void registerSpeedrunnerModModelRenderers(CallbackInfo ci) {
        ID_MAPPER.put(ofSpeedrunnerMod("speedrunner_shield"), SpeedrunnerShieldModelRenderer.Unbaked.MAP_CODEC);
        ID_MAPPER.put(ofSpeedrunnerMod("golden_shield"), GoldenShieldModelRenderer.Unbaked.MAP_CODEC);
    }
}