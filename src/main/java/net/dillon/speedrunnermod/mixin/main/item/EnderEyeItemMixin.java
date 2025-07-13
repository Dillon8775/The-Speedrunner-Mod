package net.dillon.speedrunnermod.mixin.main.item;

import net.dillon.speedrunnermod.item.EyeItem;
import net.dillon.speedrunnermod.tag.ModStructureTags;
import net.dillon.speedrunnermod.tutorial.TutorialStep;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnderEyeItem;
import net.minecraft.registry.tag.StructureTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.option.ModOptions.isBalancedMode;

@Mixin(EnderEyeItem.class)
public class EnderEyeItemMixin implements EyeItem {

    /**
     * Locates the exact distance of the nearest stronghold and sends it to the player.
     */
    @Inject(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EyeOfEnderEntity;initTargetPos(Lnet/minecraft/util/math/Vec3d;)V"))
    private void locateExactDistance(World world, PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        BlockPos blockPos = ((ServerWorld)world).locateStructure(StructureTags.EYE_OF_ENDER_LOCATED, player.getBlockPos(), 100, false);
        BlockPos playerpos = player.getBlockPos();
        int structureDistance = MathHelper.floor(ModUtil.getDistance(playerpos.getX(), playerpos.getZ(), blockPos.getX(), blockPos.getZ()));
        ModUtil.sendMessageWithActionbarPref(player, this.locationText(structureDistance, this.structureTexts(ModStructureTags.STRONGHOLDS)));
        if (isBalancedMode()) {
            ModUtil.completeStepS2C(TutorialStep.USE_ENDER_EYE, player, "speedrunnermod.tutorial_mode.enter_end.balanced");
        }
    }
}