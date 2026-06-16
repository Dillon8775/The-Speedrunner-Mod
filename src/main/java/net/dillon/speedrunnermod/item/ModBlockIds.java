package net.dillon.speedrunnermod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod block ids.
 */
public class ModBlockIds {
    public static final ResourceKey<Block> POTTED_SPEEDRUNNER_SAPLING = create("potted_speedrunner_sapling");
    public static final ResourceKey<Block> DEAD_POTTED_SPEEDRUNNER_SAPLING = create("dead_potted_speedrunner_sapling");
    public static final ResourceKey<Block> DEAD_POTTED_SPEEDRUNNER_BUSH = create("dead_potted_speedrunner_bush");

    /**
     * Creates an {@code block id.}
     */
    private static ResourceKey<Block> create(final String name) {
        return ResourceKey.create(Registries.BLOCK, ofSpeedrunnerMod(name));
    }
}