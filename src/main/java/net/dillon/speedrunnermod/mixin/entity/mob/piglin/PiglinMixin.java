package net.dillon.speedrunnermod.mixin.entity.mob.piglin;

import net.dillon.speedrunnermod.entity.Awakened;
import net.dillon.speedrunnermod.helper.ModAttributeHelper;
import net.dillon.speedrunnermod.helper.ModHelper;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.option.ModCommonOptions.doomOrDefault;

@Mixin(Piglin.class)
public abstract class PiglinMixin extends AbstractPiglin implements Awakened {
    @Unique
    private static final EntityDataAccessor<Boolean> AWAKENED = SynchedEntityData.defineId(Piglin.class, EntityDataSerializers.BOOLEAN);

    public PiglinMixin(EntityType<? extends AbstractPiglin> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public void setAwakened(boolean awakened) {
        this.entityData.set(AWAKENED, awakened);
    }

    @Override
    public boolean isAwakened() {
        return this.entityData.get(AWAKENED);
    }

    /**
     * Creates the {@code awakened tracker.}
     */
    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    private void writeAwakenedTracker(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(AWAKENED, false);
    }

    /**
     * Writes the {@code awakened} to NBT.
     */
    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void writeFireproofToNbt(ValueOutput view, CallbackInfo ci) {
        view.putBoolean("Awakened", this.isAwakened());
    }

    /**
     * Reads the {@code awakened tracker} by NBT and writes it back.
     */
    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void readFireproofFromNbt(ValueInput view, CallbackInfo ci) {
        this.setAwakened(view.getBooleanOr("Awakened", false));
    }

    /**
     * Modifies {@code piglin} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changePiglinAttributes(EntityType<? extends Piglin> entityType, Level world, CallbackInfo ci) {
        ModAttributeHelper.modifyMaxHealth(this, doomOrDefault(24.0D, 16.0D));
        ModAttributeHelper.modifyAttackDamage(this, doomOrDefault(6.0D, 2.0D));
    }

    /**
     * Modifies the experience to drop for the piglin.
     */
    @Inject(method = "getBaseExperienceReward", at = @At("HEAD"))
    private void modifyExperienceToDrop(ServerLevel world, CallbackInfoReturnable<Integer> cir) {
        if (this.getLastHurtByMob() != null) {
            this.xpReward = ModHelper.modifyDroppedExperiencePoints(this, this.getLastHurtByMob(), 5, 32);
        }
    }
}