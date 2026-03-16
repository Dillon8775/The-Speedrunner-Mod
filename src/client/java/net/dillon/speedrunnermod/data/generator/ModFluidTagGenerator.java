package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.tag.ModFluidTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.FluidTags;

import java.util.concurrent.CompletableFuture;

/**
 * Contains the entries of all new or already existing fluid tags.
 */
public class ModFluidTagGenerator extends FabricTagProvider.FluidTagProvider {

    public ModFluidTagGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        valueLookupBuilder(ModFluidTags.BOAT_SAFE_FLUIDS)
                .forceAddTag(FluidTags.WATER)
                .forceAddTag(FluidTags.LAVA);
    }
}