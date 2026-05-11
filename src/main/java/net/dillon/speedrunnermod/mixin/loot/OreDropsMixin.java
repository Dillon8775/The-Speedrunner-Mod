package net.dillon.speedrunnermod.mixin.loot;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ApplyBonusCount.OreDrops.class)
public class OreDropsMixin {

    /**
     * @author Dillon8775
     * @reason Applies better loot drops from ores.
     */
    @Overwrite
    public int calculateNewCount(RandomSource random, int initialCount, int enchantmentLevel) {
        return enchantmentLevel > 0 ? initialCount * (enchantmentLevel + 1) : initialCount;
    }
}