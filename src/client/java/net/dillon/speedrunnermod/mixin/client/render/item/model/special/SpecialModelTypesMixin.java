package net.dillon.speedrunnermod.mixin.client.render.item.model.special;

import com.mojang.serialization.MapCodec;
import net.dillon.speedrunnermod.client.render.GoldenShieldModelRenderer;
import net.dillon.speedrunnermod.client.render.SpeedrunnerShieldModelRenderer;
import net.minecraft.client.render.item.model.special.SpecialModelRenderer;
import net.minecraft.client.render.item.model.special.SpecialModelTypes;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

@Mixin(SpecialModelTypes.class)
public class SpecialModelTypesMixin {
    @Shadow @Final
    public static Codecs.IdMapper<Identifier, MapCodec<? extends SpecialModelRenderer.Unbaked>> ID_MAPPER;

    @Inject(method = "bootstrap", at = @At("TAIL"))
    private static void registerSpeedrunnerModModelRenderers(CallbackInfo ci) {
        ID_MAPPER.put(ofSpeedrunnerMod("speedrunner_shield"), SpeedrunnerShieldModelRenderer.Unbaked.CODEC);
        ID_MAPPER.put(ofSpeedrunnerMod("golden_shield"), GoldenShieldModelRenderer.Unbaked.CODEC);
    }
}