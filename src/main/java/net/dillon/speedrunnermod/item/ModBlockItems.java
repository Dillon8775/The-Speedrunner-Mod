package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * All Speedrunner Mod {@code block items.}
 */
public class ModBlockItems {
    public static final Item SPEEDRUNNER_LOG = Items.register(ModBlocks.SPEEDRUNNER_LOG);

    public static final Item STRIPPED_SPEEDRUNNER_LOG = Items.register(ModBlocks.STRIPPED_SPEEDRUNNER_LOG);

    public static final Item SPEEDRUNNER_WOOD = Items.register(ModBlocks.SPEEDRUNNER_WOOD);

    public static final Item STRIPPED_SPEEDRUNNER_WOOD = Items.register(ModBlocks.STRIPPED_SPEEDRUNNER_WOOD);

    public static final Item SPEEDRUNNER_LEAVES = Items.register(ModBlocks.SPEEDRUNNER_LEAVES);

    public static final Item SPEEDRUNNER_SAPLING = Items.register(ModBlocks.SPEEDRUNNER_SAPLING);

    public static final Item SPEEDRUNNER_PLANKS = Items.register(ModBlocks.SPEEDRUNNER_PLANKS);

    public static final Item SPEEDRUNNER_SLAB = Items.register(ModBlocks.SPEEDRUNNER_SLAB);

    public static final Item SPEEDRUNNER_STAIRS = Items.register(ModBlocks.SPEEDRUNNER_STAIRS);

    public static final Item SPEEDRUNNER_FENCE = Items.register(ModBlocks.SPEEDRUNNER_FENCE);

    public static final Item SPEEDRUNNER_FENCE_GATE = Items.register(ModBlocks.SPEEDRUNNER_FENCE_GATE);

    public static final Item WOODEN_SPEEDRUNNER_TRAPDOOR = Items.register(ModBlocks.WOODEN_SPEEDRUNNER_TRAPDOOR);

    public static final Item WOODEN_SPEEDRUNNER_BUTTON = Items.register(ModBlocks.WOODEN_SPEEDRUNNER_BUTTON);

    public static final Item WOODEN_SPEEDRUNNER_PRESSURE_PLATE = Items.register(ModBlocks.WOODEN_SPEEDRUNNER_PRESSURE_PLATE);

    public static final Item WOODEN_SPEEDRUNNER_DOOR = Items.register(ModBlocks.WOODEN_SPEEDRUNNER_DOOR, TallBlockItem::new);

    public static final Item SPEEDRUNNER_SIGN = Items.register(ModBlocks.SPEEDRUNNER_SIGN, (block, settings) -> new SignItem(
            ModBlocks.SPEEDRUNNER_SIGN, ModBlocks.SPEEDRUNNER_WALL_SIGN, settings), new Item.Settings().maxCount(16));

    public static final Item SPEEDRUNNER_HANGING_SIGN = Items.register(ModBlocks.SPEEDRUNNER_HANGING_SIGN, (block, settings) -> new HangingSignItem(
            ModBlocks.SPEEDRUNNER_HANGING_SIGN, ModBlocks.SPEEDRUNNER_HANGING_WALL_SIGN, settings), new Item.Settings().maxCount(16));

    public static final Item DEAD_SPEEDRUNNER_LOG = Items.register(ModBlocks.DEAD_SPEEDRUNNER_LOG);

    public static final Item DEAD_STRIPPED_SPEEDRUNNER_LOG = Items.register(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_LOG);

    public static final Item DEAD_SPEEDRUNNER_WOOD = Items.register(ModBlocks.DEAD_SPEEDRUNNER_WOOD);

    public static final Item DEAD_STRIPPED_SPEEDRUNNER_WOOD = Items.register(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_WOOD);

    public static final Item DEAD_SPEEDRUNNER_LEAVES = Items.register(ModBlocks.DEAD_SPEEDRUNNER_LEAVES);

    public static final Item DEAD_SPEEDRUNNER_SAPLING = Items.register(ModBlocks.DEAD_SPEEDRUNNER_SAPLING);

    public static final Item DEAD_SPEEDRUNNER_PLANKS = Items.register(ModBlocks.DEAD_SPEEDRUNNER_PLANKS);

    public static final Item DEAD_SPEEDRUNNER_SLAB = Items.register(ModBlocks.DEAD_SPEEDRUNNER_SLAB);

    public static final Item DEAD_SPEEDRUNNER_STAIRS = Items.register(ModBlocks.DEAD_SPEEDRUNNER_STAIRS);

    public static final Item DEAD_SPEEDRUNNER_FENCE = Items.register(ModBlocks.DEAD_SPEEDRUNNER_FENCE);

    public static final Item DEAD_SPEEDRUNNER_FENCE_GATE = Items.register(ModBlocks.DEAD_SPEEDRUNNER_FENCE_GATE);

    public static final Item DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR = Items.register(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR);

    public static final Item DEAD_WOODEN_SPEEDRUNNER_BUTTON = Items.register(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_BUTTON);

    public static final Item DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE = Items.register(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE);

    public static final Item DEAD_WOODEN_SPEEDRUNNER_DOOR = Items.register(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_DOOR, TallBlockItem::new);

    public static final Item DEAD_SPEEDRUNNER_SIGN = Items.register(ModBlocks.DEAD_SPEEDRUNNER_SIGN, (block, settings) -> new SignItem(
            ModBlocks.DEAD_SPEEDRUNNER_SIGN, ModBlocks.DEAD_SPEEDRUNNER_WALL_SIGN, settings), new Item.Settings().maxCount(16));

    public static final Item DEAD_SPEEDRUNNER_HANGING_SIGN = Items.register(ModBlocks.DEAD_SPEEDRUNNER_HANGING_SIGN, (block, settings) -> new HangingSignItem(
            ModBlocks.DEAD_SPEEDRUNNER_HANGING_SIGN, ModBlocks.DEAD_SPEEDRUNNER_HANGING_WALL_SIGN, settings), new Item.Settings().maxCount(16));

    public static final Item SPEEDRUNNER_TRAPDOOR = Items.register(ModBlocks.SPEEDRUNNER_TRAPDOOR);

    public static final Item SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE = Items.register(ModBlocks.SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE);

    public static final Item SPEEDRUNNER_DOOR = Items.register(ModBlocks.SPEEDRUNNER_DOOR);

    public static final Item DEAD_SPEEDRUNNER_BUSH = Items.register(ModBlocks.DEAD_SPEEDRUNNER_BUSH);

    public static final Item SPEEDRUNNERS_WORKBENCH = Items.register(ModBlocks.SPEEDRUNNERS_WORKBENCH, (block, settings) -> new BlockItem(ModBlocks.SPEEDRUNNERS_WORKBENCH,
            settings) {

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            if (options().client.itemTooltips) {
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_workbench.tooltip.line1").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_workbench.tooltip.line2").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_workbench.tooltip.line3").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_workbench.tooltip.line4").formatted(Formatting.GRAY));
            }
        }
    });

    public static final Item SPEEDRUNNER_BLOCK = Items.register(ModBlocks.SPEEDRUNNER_BLOCK);

    public static final Item RAW_SPEEDRUNNER_BLOCK = Items.register(ModBlocks.RAW_SPEEDRUNNER_BLOCK);

    public static final Item THRUSTED_BLOCK = Items.register(ModBlocks.THRUSTED_BLOCK);

    public static final Item SPEEDRUNNER_ORE = Items.register(ModBlocks.SPEEDRUNNER_ORE);

    public static final Item DEEPSLATE_SPEEDRUNNER_ORE = Items.register(ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE);

    public static final Item NETHER_SPEEDRUNNER_ORE = Items.register(ModBlocks.NETHER_SPEEDRUNNER_ORE);

    public static final Item IGNEOUS_ORE = Items.register(ModBlocks.IGNEOUS_ORE);

    public static final Item DEEPSLATE_IGNEOUS_ORE = Items.register(ModBlocks.DEEPSLATE_IGNEOUS_ORE);

    public static final Item NETHER_IGNEOUS_ORE = Items.register(ModBlocks.NETHER_IGNEOUS_ORE);

    public static final Item EXPERIENCE_ORE = Items.register(ModBlocks.EXPERIENCE_ORE, ExperienceOreItem::new);
    public static final Item DEEPSLATE_EXPERIENCE_ORE = Items.register(ModBlocks.DEEPSLATE_EXPERIENCE_ORE, ExperienceOreItem::new);
    public static final Item NETHER_EXPERIENCE_ORE = Items.register(ModBlocks.NETHER_EXPERIENCE_ORE, ExperienceOreItem::new);

    public static final Item DOOM_STONE = Items.register(ModBlocks.DOOM_STONE);

    public static final Item DOOM_LOG = Items.register(ModBlocks.DOOM_LOG);

    public static final Item STRIPPED_DOOM_LOG = Items.register(ModBlocks.STRIPPED_DOOM_LOG);

    public static final Item DOOM_LEAVES = Items.register(ModBlocks.DOOM_LEAVES);

    /**
     * Initializes all Speedrunner Mod {@code block items.}
     */
    public static void initializeBlockItems() {
    }
}