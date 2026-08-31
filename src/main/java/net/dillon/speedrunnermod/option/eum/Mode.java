package net.dillon.speedrunnermod.option.eum;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

public enum Mode implements StringRepresentable {
    EASY("§b§lEASY"),
    BALANCED("§e§lBALANCED"),
    DOOM("§c§lDOOM");

    private static final Codec<Mode> CODEC = StringRepresentable.fromEnum(Mode::values);
    private final String name;

    Mode(final String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}