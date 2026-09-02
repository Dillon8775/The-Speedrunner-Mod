package net.dillon.speedrunnermod.mixin.entity.mob.piglin;

import net.dillon.speedrunnermod.component.ModAttributes;
import net.dillon.speedrunnermod.mixin.accessor.PiglinAiInvoker;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;

@Mixin(PiglinAi.class)
public abstract class PiglinAiMixin {

    /**
     * Protects the player entirely if they are wearing golden speedrunner armor.
     */
    @Inject(method = "angerNearbyPiglins", at = @At("HEAD"), cancellable = true)
    private static void playerSafeGoldenSpeedrunner(ServerLevel world, Player player, boolean blockOpen, CallbackInfo ci) {
        if (hasPiglinSafeAttribute(player, false)) {
            ci.cancel();
        }
    }

    /**
     * Stop being angry at the player if they are wearing golden speedrunner armor.
     */
    @Inject(method = "updateActivity", at = @At("HEAD"))
    private static void forgetThePlayer(Piglin piglin, CallbackInfo ci) {
        for (Player player : piglin.level().players()) {
            if (hasPiglinSafeAttribute(player, true) && !hasBeenHitByPlayer(piglin)) {
                piglin.getBrain().eraseMemory(MemoryModuleType.ANGRY_AT);
            }
        }
    }

    /**
     * Copied over from {@link PiglinAi}.
     */
    private static boolean hasBeenHitByPlayer(Piglin piglin) {
        return piglin.getBrain().hasMemoryValue(MemoryModuleType.ADMIRING_DISABLED);
    }

    /**
     * @return if the player has the piglin safe attribute.
     */
    @Unique
    private static boolean hasPiglinSafeAttribute(Player player, boolean mustBeFull) {
        float piglinSafety = (float)player.getAttributeValue(ModAttributes.PIGLIN_STEALTH);
        if (mustBeFull) {
            return piglinSafety >= 2.0F;
        }
        return piglinSafety > 1.0F && player.getRandom().nextFloat() < (piglinSafety - 1.0F);
    }

    /**
     * @author Dillon8775
     * @reason Removes the first argument of the original method, and makes piglins {@code willing to trade}, even if they were previously hit by the player.
     */
    @Overwrite
    public static boolean canAdmire(Piglin piglin, ItemStack nearbyItems) {
        return !PiglinAiInvoker.invokeIsAdmiringItem(piglin) && piglin.isAdult() && PiglinAiInvoker.invokeIsBarterCurrency(nearbyItems);
    }

    /**
     * Lowers the distance piglins have to be in order for them to run away from the nearest zombified piglin.
     */
    @ModifyArg(method = "isNearZombified", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/piglin/Piglin;closerThan(Lnet/minecraft/world/entity/Entity;D)Z"), index = 1)
    private static double changeNearestPiglinDistance(double radius) {
        return common().accessibility().decreasedZombifiedPiglinScareDistance ? 2.0D : 6.0D;
    }
}