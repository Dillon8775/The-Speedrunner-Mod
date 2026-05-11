package net.dillon.speedrunnermod.mixin.accessor;

import net.minecraft.world.item.CrossbowItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CrossbowItem.class)
public interface CrossbowItemAccessor {
    @Accessor("startSoundPlayed")
    void setStartSoundPlayed(boolean startSoundPlayed);
    @Accessor("midLoadSoundPlayed")
    void setMidLoadSoundPlayed(boolean midLoadSoundPlayed);
}