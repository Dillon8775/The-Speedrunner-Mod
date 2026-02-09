package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.VillagerGlowCountdown;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VillagerEntity.class)
public class VillagerEntityMixin implements VillagerGlowCountdown {
    @Unique
    private int glowTicksRemaining = -1;

    /**
     * Sets how long a villager should glow for.
     */
    @Unique
    public void setGlowingFor(int ticks) {
        this.glowTicksRemaining = ticks;
    }

    /**
     * Counts down the ticks in order to remove glow from villagers.
     */
    @Inject(method = "tick", at = @At("TAIL"))
    private void tickCountdown(CallbackInfo ci) {
        if (this.glowTicksRemaining > 0) {
            this.glowTicksRemaining--;

            if (this.glowTicksRemaining == 0) {
                ((VillagerEntity)(Object)this).setGlowing(false);
            }
        }
    }

    /**
     * Writes the glow ticks remaining to NBT.
     */
    @Inject(method = "writeCustomData", at = @At("TAIL"))
    private void writeGlowTicks(WriteView view, CallbackInfo ci) {
        view.putInt("GlowTicksRemaining", this.glowTicksRemaining);
    }

    /**
     * Reads the glow ticks remaining to NBT.
     */
    @Inject(method = "readCustomData", at = @At("TAIL"))
    private void readGlowTicks(ReadView view, CallbackInfo ci) {
        this.glowTicksRemaining = view.getInt("GlowTicksRemaining", -1);
    }
}