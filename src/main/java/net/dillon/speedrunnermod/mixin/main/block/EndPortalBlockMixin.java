package net.dillon.speedrunnermod.mixin.main.block;

import net.dillon.speedrunnermod.packet.client.UpdateLastCompletedTutorialStepTranslationsS2CPacket;
import net.dillon.speedrunnermod.server.ServerStorage;
import net.dillon.speedrunnermod.tutorial.TutorialStep;
import net.dillon.speedrunnermod.util.ModUtil;
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

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;
import static net.dillon.speedrunnermod.util.ModUtil.sendWithPrefix;

@Mixin(EndPortalBlock.class)
public class EndPortalBlockMixin {

    @Inject(method = "onEntityCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;detachForDimensionChange()V"))
    private void exitEndTutorialMode(BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler handler, CallbackInfo ci) {
        if (entity instanceof ServerPlayerEntity player && ServerStorage.hasCompletedStep(player, TutorialStep.KILL_DRAGON)) {
            if (isDoomMode()) {
                ModUtil.completeStepS2C(TutorialStep.EXIT_END, player, "speedrunnermod.tutorial_mode.exit_end.doom");
            } else {
                List<String> translations = new ArrayList<>();
                String s = "speedrunnermod.tutorial_mode.find_experience_ore";
                translations.add(s);
                sendWithPrefix(s, player);
                ServerPlayNetworking.send(player, new UpdateLastCompletedTutorialStepTranslationsS2CPacket(translations));
            }
        }
    }
}