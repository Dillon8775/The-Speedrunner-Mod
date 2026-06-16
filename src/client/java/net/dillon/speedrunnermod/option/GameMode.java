package net.dillon.speedrunnermod.option;

import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;

import java.util.Arrays;
import java.util.Comparator;

/**
 * All the different {@code GameMode} options.
 */
public enum GameMode implements StringRepresentable {
    SURVIVAL(0, "survival", "speedrunnermod.options.gamemode.survival"),
    CREATIVE(1, "creative", "speedrunnermod.options.gamemode.creative"),
    HARDCORE(2, "hardcore", "speedrunnermod.options.gamemode.hardcore"),
    SPECTATOR(3, "spectator", "speedrunnermod.options.gamemode.spectator");

    private static final GameMode[] VALUES = Arrays.stream(GameMode.values()).sorted(Comparator.comparingInt(GameMode::getId)).toArray(GameMode[]::new);
    private final int id;
    private final String name;
    private final Component translateKey;

    GameMode(int id, final String name, String translationKey) {
        this.id = id;
        this.name = name;
        this.translateKey = Component.translatable(translationKey);
    }

    public boolean hardcore() {
        return this == HARDCORE;
    }

    /**
     * Returns the {@code id value} of the {@code GameMode} option.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns the {@code translation key} of the {@code GameMode} option.
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
    public static GameMode byId(int id) {
        return VALUES[Mth.positiveModulo(id, VALUES.length)];
    }
}