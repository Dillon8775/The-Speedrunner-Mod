package net.dillon.speedrunnermod.option.eum;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

/**
 * All the different {@code GameMode} options.
 */
public enum WorldGameMode implements StringRepresentable {
    SURVIVAL("§cSurvival"),
    CREATIVE("§aCreative"),
    HARDCORE("§4Hardcore"),
    SPECTATOR("§7Spectator");

    public static final Codec<WorldGameMode> CODEC = StringRepresentable.fromEnum(WorldGameMode::values);
    private final String name;

    WorldGameMode(final String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}