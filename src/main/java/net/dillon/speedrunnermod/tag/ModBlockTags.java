package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code block tags.}
 */
public class ModBlockTags {

    /**
     * Registers a {@code block tag.}
     */
    protected static TagKey<Block> createBlockTag(String path) {
        return TagKey.create(Registries.BLOCK, ofSpeedrunnerMod(path));
    }

    /**
     * Initializes all Speedrunner Mod {@code block tags.}
     */
    public static void initializeBlockTags() {
        SpeedrunnerMod.debug("Initialized block tags.");
    }
}