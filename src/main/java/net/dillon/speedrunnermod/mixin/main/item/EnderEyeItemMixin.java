package net.dillon.speedrunnermod.mixin.main.item;

import net.dillon.speedrunnermod.item.EyeItem;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.tag.ModStructureTags;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.StructureTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnderEyeItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderEyeItem.class)
public class EnderEyeItemMixin implements EyeItem {

    /**
     * Locates the exact distance of the nearest stronghold and sends it to the player.
     */
    @Inject(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/EyeOfEnder;signalTo(Lnet/minecraft/world/phys/Vec3;)V"))
    private void locateExactDistance(Level world, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        BlockPos blockPos = ((ServerLevel)world).findNearestMapStructure(StructureTags.EYE_OF_ENDER_LOCATED, player.blockPosition(), 100, false);
        BlockPos playerpos = player.blockPosition();
        int structureDistance = Mth.floor(ModUtil.getDistance(playerpos.getX(), playerpos.getZ(), blockPos.getX(), blockPos.getZ()));
        ModUtil.sendMessageWithActionbarPref(player, this.locationText(structureDistance, this.structureTexts(ModStructureTags.STRONGHOLDS)));
    }

    @Override
    public ModOptions.Mode[] disabledModes() {
        return new ModOptions.Mode[]{};
    }
}