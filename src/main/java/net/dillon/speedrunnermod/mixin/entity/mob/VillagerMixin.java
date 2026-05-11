package net.dillon.speedrunnermod.mixin.entity.mob;

import net.dillon.speedrunnermod.util.VillagerGlowCountdown;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Villager.class)
public class VillagerMixin implements VillagerGlowCountdown {
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
                ((Villager)(Object)this).setGlowingTag(false);
            }
        }
    }

    /**
     * Writes the glow ticks remaining to NBT.
     */
    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void writeGlowTicks(ValueOutput view, CallbackInfo ci) {
        view.putInt("GlowTicksRemaining", this.glowTicksRemaining);
    }

    /**
     * Reads the glow ticks remaining to NBT.
     */
    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void readGlowTicks(ValueInput view, CallbackInfo ci) {
        this.glowTicksRemaining = view.getIntOr("GlowTicksRemaining", -1);
    }
}