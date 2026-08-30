package net.dillon.speedrunnermod.option;

import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;

import java.util.Arrays;
import java.util.Comparator;

/**
 * All the different {@code Structure Spawn Rate} options, from extremely common to extremely rare.
 */
@Deprecated(forRemoval = true) // Soon to be fixed boolean
public enum StructureSpawnRate implements StringRepresentable {
    EVERYWHERE(0, "everywhere", "speedrunnermod.options.structure_spawn_rates.everywhere"),
    VERY_COMMON(1, "very_common", "speedrunnermod.options.structure_spawn_rates.very_common"),
    COMMON(2, "common", "speedrunnermod.options.structure_spawn_rates.common"),
    NORMAL(3, "normal", "speedrunnermod.options.structure_spawn_rates.normal"),
    DEFAULT(4, "default", "speedrunnermod.options.structure_spawn_rates.default"),
    RARE(5, "rare", "speedrunnermod.options.structure_spawn_rates.rare"),
    VERY_RARE(6, "very_rare", "speedrunnermod.options.structure_spawn_rates.very_rare"),
    CUSTOM(7, "custom", "speedrunnermod.options.structure_spawn_rates.custom");

    private static final StructureSpawnRate[] VALUES = Arrays.stream(StructureSpawnRate.values()).sorted(Comparator.comparingInt(StructureSpawnRate::getId)).toArray(StructureSpawnRate[]::new);
    private final int id;
    private final String name;
    private final Component translateKey;

    StructureSpawnRate(int id, final String name, String translationKey) {
        this.id = id;
        this.name = name;
        this.translateKey = Component.translatable(translationKey);
    }

    /**
     * Returns the {@code id value} of the {@code Structure Spawn Rate} option.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns the {@code translation key} of the {@code Mode} option.
     */
    public Component getText() {
        return this.translateKey;
    }

    public String getSerializedName() {
        return this.name;
    }

    /**
     * Not sure what this does to be honest, but it's used in ModListOptions.
     */
    public static StructureSpawnRate byId(int id) {
        return VALUES[Mth.positiveModulo(id, VALUES.length)];
    }
}