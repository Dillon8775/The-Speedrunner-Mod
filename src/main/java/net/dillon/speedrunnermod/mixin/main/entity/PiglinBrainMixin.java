package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.tag.ModItemTags;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PiglinBrain.class)
public abstract class PiglinBrainMixin {

    /**
     * Protects the player entirely if they are wearing golden speedrunner armor.
     */
    @Inject(method = "onGuardedBlockInteracted", at = @At("HEAD"), cancellable = true)
    private static void playerSafeGoldenSpeedrunner(ServerWorld world, PlayerEntity player, boolean blockOpen, CallbackInfo ci) {
        if (isWearingGoldenSpeedrunnerArmor(player)) {
            ci.cancel();
        }
    }

    /**
     * Stop being angry at the player if they are wearing golden speedrunner armor.
     */
    @Inject(method = "tickActivities", at = @At("HEAD"))
    private static void forgetThePlayer(PiglinEntity piglin, CallbackInfo ci) {
        for (PlayerEntity player : piglin.getEntityWorld().getPlayers()) {
            if (isWearingGoldenSpeedrunnerArmor(player) && !hasBeenHitByPlayer(piglin)) {
                piglin.getBrain().forget(MemoryModuleType.ANGRY_AT);
            }
        }
    }

    /**
     * Copied over from {@link PiglinBrain}.
     */
    private static boolean hasBeenHitByPlayer(PiglinEntity piglin) {
        return piglin.getBrain().hasMemoryModule(MemoryModuleType.ADMIRING_DISABLED);
    }

    /**
     * @return if the player is wearing golden speedrunner armor.
     */
    @Unique
    private static boolean isWearingGoldenSpeedrunnerArmor(PlayerEntity player) {
        EquipmentSlot[] slots = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
        for (EquipmentSlot s : slots) {
            if (player.getEquippedStack(s).isIn(ModItemTags.GOLDEN_SPEEDRUNNER_ARMOR)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @author Dillon8775
     * @reason Removes the first argument of the original method, and makes piglins {@code willing to trade}, even if they were previously hit by the player.
     */
    @Overwrite
    public static boolean isWillingToTrade(PiglinEntity piglin, ItemStack nearbyItems) {
        return !PiglinBrain.isAdmiringItem(piglin) && piglin.isAdult() && PiglinBrain.acceptsForBarter(nearbyItems);
    }

    /**
     * Lowers the distance piglins have to be in order for them to run away from the nearest zombified piglin.
     */
    @ModifyArg(method = "getNearestZombifiedPiglin", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/PiglinEntity;isInRange(Lnet/minecraft/entity/Entity;D)Z"), index = 1)
    private static double changeNearestPiglinDistance(double radius) {
        return ModUtil.getZombifiedPiglinRunawayDistance();
    }
}