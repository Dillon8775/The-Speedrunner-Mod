package net.dillon.speedrunnermod.option;

import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;

import java.util.Arrays;
import java.util.Comparator;

public enum ItemMessages implements StringRepresentable {
    CHAT(0, "chat", "speedrunnermod.options.item_messages.chat"),
    ACTIONBAR(1, "actionbar", "speedrunnermod.options.item_messages.actionbar");

    private static final ItemMessages[] VALUES = Arrays.stream(ItemMessages.values()).sorted(Comparator.comparingInt(ItemMessages::getId)).toArray(ItemMessages[]::new);
    private final int id;
    private final String name;
    private final Component translateKey;

    ItemMessages(int id, final String name, String translationKey) {
        this.id = id;
        this.name = name;
        this.translateKey = Component.translatable(translationKey);
    }

    /**
     * Returns the {@code id value} of the {@code Item Messages} option.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns the {@code translation key} of the {@code Item Messages} option.
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
    public static ItemMessages byId(int id) {
        return VALUES[Mth.positiveModulo(id, VALUES.length)];
    }
}