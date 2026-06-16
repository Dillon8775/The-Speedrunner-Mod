package net.dillon.speedrunnermod.option;

import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;

import java.util.Arrays;
import java.util.Comparator;

/**
 * All the different {@code Difficulty} options.
 */
public enum Difficulty implements StringRepresentable {
    PEACEFUL(0, "peaceful", "speedrunnermod.options.difficulty.peaceful"),
    EASY(1, "easy", "speedrunnermod.options.difficulty.easy"),
    NORMAL(2, "normal", "speedrunnermod.options.difficulty.normal"),
    HARD(3, "hard", "speedrunnermod.options.difficulty.hard");

    private static final Difficulty[] VALUES = Arrays.stream(Difficulty.values()).sorted(Comparator.comparingInt(Difficulty::getId)).toArray(Difficulty[]::new);
    private final int id;
    private final String name;
    private final Component translateKey;

    Difficulty(int id, final String name, String translationKey) {
        this.id = id;
        this.name = name;
        this.translateKey = Component.translatable(translationKey);
    }

    /**
     * Returns the {@code id value} of the {@code Difficulty} option.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns the {@code translation key} of the {@code Difficulty} option.
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
    public static Difficulty byId(int id) {
        return VALUES[Mth.positiveModulo(id, VALUES.length)];
    }
}