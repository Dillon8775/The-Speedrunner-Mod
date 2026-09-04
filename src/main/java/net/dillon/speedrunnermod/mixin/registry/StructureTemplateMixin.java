package net.dillon.speedrunnermod.mixin.registry;

import net.dillon.speedrunnermod.data.VanillaLootLoader;
import net.minecraft.core.HolderGetter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StructureTemplate.class)
public class StructureTemplateMixin {

    /**
     * Directly manipulates structure loot data, and points certain structures to a different path to make generating data easier.
     */
    @Inject(method = "load", at = @At("HEAD"))
    private void modifyStructure(HolderGetter<Block> blockLookup, CompoundTag tag, CallbackInfo ci) {
        VanillaLootLoader.modifyVanillaLootPaths(tag);
    }
}