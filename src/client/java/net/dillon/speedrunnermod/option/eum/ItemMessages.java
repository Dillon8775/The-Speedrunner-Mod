package net.dillon.speedrunnermod.option.eum;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

public enum ItemMessages implements StringRepresentable {
    CHAT("§aChat"),
    OVERLAY("§bOverlay");

    public static final Codec<ItemMessages> CODEC = StringRepresentable.fromEnum(ItemMessages::values);
    private final String name;

    ItemMessages(final String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}