package net.dillon.speedrunnermod.mixin.main.entity.player;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(PlayerEntity.class)
public interface InventoryAccessor {
    @Accessor
    PlayerInventory getInventory();
}