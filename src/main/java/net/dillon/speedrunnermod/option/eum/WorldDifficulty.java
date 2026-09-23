package net.dillon.speedrunnermod.option.eum;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

/**
 * All the different {@code Difficulty} options.
 */
public enum WorldDifficulty implements StringRepresentable {
    PEACEFUL("§7Peaceful"),
    EASY("§bEasy"),
    NORMAL("§eNormal"),
    HARD("§cHard");

    public static final Codec<WorldDifficulty> CODEC = StringRepresentable.fromEnum(WorldDifficulty::values);
    private final String name;

    WorldDifficulty(final String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}