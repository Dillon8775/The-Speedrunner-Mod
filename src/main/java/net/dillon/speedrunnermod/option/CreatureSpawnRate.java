package net.dillon.speedrunnermod.option;

import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;

import java.util.Arrays;
import java.util.Comparator;

/**
 * All the different {@code Mob Spawning Rate} options.
 */
public enum CreatureSpawnRate implements StringRepresentable {
    LOW(0, "low", "speedrunnermod.options.creature_spawn_rate.low"),
    NORMAL(1, "normal", "speedrunnermod.options.creature_spawn_rate.normal"),
    HIGH(2, "high", "speedrunnermod.options.creature_spawn_rate.high");

    private static final CreatureSpawnRate[] VALUES = Arrays.stream(CreatureSpawnRate.values()).sorted(Comparator.comparingInt(CreatureSpawnRate::getId)).toArray(CreatureSpawnRate[]::new);
    private final int id;
    private final String name;
    private final Component translateKey;

    CreatureSpawnRate(int id, final String name, String translationKey) {
        this.id = id;
        this.name = name;
        this.translateKey = Component.translatable(translationKey);
    }

    /**
     * Returns the {@code id value} of the {@code Creature Spawning Rate} option.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns the {@code translation key} of the {@code Creature Spawning Rate} option.
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
    public static CreatureSpawnRate byId(int id) {
        return VALUES[Mth.positiveModulo(id, VALUES.length)];
    }
}