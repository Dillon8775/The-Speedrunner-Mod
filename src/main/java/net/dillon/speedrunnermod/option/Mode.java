package net.dillon.speedrunnermod.option;

import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;

import java.util.Arrays;
import java.util.Comparator;

public enum Mode implements StringRepresentable {
    EASY(0, "easy", "speedrunnermod.options.mode.easy"),
    BALANCED(1, "balanced", "speedrunnermod.options.mode.balanced"),
    DOOM(2, "doom", "speedrunnermod.options.mode.doom");

    private static final Mode[] VALUES = Arrays.stream(Mode.values()).sorted(Comparator.comparingInt(Mode::getId)).toArray(Mode[]::new);
    private final int id;
    private final String name;
    private final Component translateKey;

    Mode(int id, final String name, String translationKey) {
        this.id = id;
        this.name = name;
        this.translateKey = Component.translatable(translationKey);
    }

    /**
     * Returns the {@code id value} of the {@code Mode} option.
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
    public static Mode byId(int id) {
        return VALUES[Mth.positiveModulo(id, VALUES.length)];
    }
}