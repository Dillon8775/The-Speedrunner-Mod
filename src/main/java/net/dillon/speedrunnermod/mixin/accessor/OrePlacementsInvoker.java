package net.dillon.speedrunnermod.mixin.accessor;

import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(OrePlacements.class)
public interface OrePlacementsInvoker {
    @Invoker("commonOrePlacement")
    static List<PlacementModifier> invokeCommonOrePlacement(final int count, final PlacementModifier heightRange) {
        throw new AssertionError();
    }
}