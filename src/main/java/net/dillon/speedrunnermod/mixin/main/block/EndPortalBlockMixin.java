package net.dillon.speedrunnermod.mixin.main.block;

import net.dillon.speedrunnermod.packet.UpdateClientPreferencesS2CPacket;
import net.dillon.speedrunnermod.util.TutorialStep;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.EndPortalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.util.ModUtil.sendWithPrefix;

@Mixin(EndPortalBlock.class)
public class EndPortalBlockMixin {

    @Inject(method = "onEntityCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;detachForDimensionChange()V"))
    private void exitEndTutorialMode(BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler handler, CallbackInfo ci) {
        if (options().main.tutorialMode && options().tutorialMode.getStep(TutorialStep.KILL_DRAGON) && entity instanceof ServerPlayerEntity player) {
            List<String> translations = new ArrayList<>();
            String s = options().main.playingMode.doom() ? "speedrunnermod.tutorial_mode.exit_end.doom" : "speedrunnermod.tutorial_mode.find_experience_ore";
            translations.add(s);
            sendWithPrefix(s, player);
            ServerPlayNetworking.send(player, new UpdateClientPreferencesS2CPacket(translations));
        }
    }
}