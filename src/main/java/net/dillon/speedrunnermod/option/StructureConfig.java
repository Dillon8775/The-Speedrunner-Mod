package net.dillon.speedrunnermod.option;

import net.minecraft.data.worldgen.StructureSets;

/**
 * Gives a structure a fixed spacing and separation value.
 * <p>See {@link StructureSets} for more.</p>
 */
public record StructureConfig(int spacing, int separation) {
}