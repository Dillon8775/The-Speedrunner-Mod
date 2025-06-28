package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;

/**
 * All Speedrunner Mod {@code fuels} (objects that can be used as a fuel source in a furnace).
 */
public class ModFuels {

    /**
     * Registers all Speedrunner Mod {@code fuel objects.}
     */
    public static void registerFuels() {
        FuelRegistryEvents.BUILD.register((builder, context) -> {
            builder.add(ModBlocks.SPEEDRUNNER_LOG, 400);
            builder.add(ModBlocks.DEAD_SPEEDRUNNER_LOG, 500);
            builder.add(ModBlocks.STRIPPED_SPEEDRUNNER_LOG, 400);
            builder.add(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_LOG, 500);
            builder.add(ModBlocks.SPEEDRUNNER_WOOD, 400);
            builder.add(ModBlocks.DEAD_SPEEDRUNNER_WOOD, 500);
            builder.add(ModBlocks.STRIPPED_SPEEDRUNNER_WOOD, 400);
            builder.add(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_WOOD, 500);
            builder.add(ModBlocks.SPEEDRUNNER_SAPLING, 200);
            builder.add(ModBlocks.DEAD_SPEEDRUNNER_SAPLING, 300);
            builder.add(ModBlocks.SPEEDRUNNER_PLANKS, 400);
            builder.add(ModBlocks.DEAD_SPEEDRUNNER_PLANKS, 500);
            builder.add(ModItems.SPEEDRUNNER_STICK, 200);
            builder.add(ModItems.SPEEDRUNNER_BOAT, 300);
            builder.add(ModItems.SPEEDRUNNER_CHEST_BOAT, 300);
            builder.add(ModItems.DEAD_SPEEDRUNNER_BOAT, 400);
            builder.add(ModItems.DEAD_SPEEDRUNNER_CHEST_BOAT, 400);
            builder.add(ModBlocks.SPEEDRUNNER_SLAB, 300);
            builder.add(ModBlocks.DEAD_SPEEDRUNNER_SLAB, 400);
            builder.add(ModBlocks.SPEEDRUNNER_STAIRS, 400);
            builder.add(ModBlocks.DEAD_SPEEDRUNNER_STAIRS, 500);
            builder.add(ModBlocks.WOODEN_SPEEDRUNNER_TRAPDOOR, 400);
            builder.add(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR, 500);
            builder.add(ModBlocks.WOODEN_SPEEDRUNNER_PRESSURE_PLATE, 400);
            builder.add(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE, 500);
            builder.add(ModBlocks.SPEEDRUNNER_FENCE, 400);
            builder.add(ModBlocks.DEAD_SPEEDRUNNER_FENCE, 500);
            builder.add(ModBlocks.SPEEDRUNNER_FENCE_GATE, 400);
            builder.add(ModBlocks.DEAD_SPEEDRUNNER_FENCE_GATE, 500);
            builder.add(ModBlocks.WOODEN_SPEEDRUNNER_DOOR, 300);
            builder.add(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_DOOR, 400);
            builder.add(ModBlocks.WOODEN_SPEEDRUNNER_BUTTON, 200);
            builder.add(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_BUTTON, 300);
            builder.add(ModBlocks.DEAD_SPEEDRUNNER_BUSH, 200);
        });

        SpeedrunnerMod.debug("Registered fuels.");
    }
}