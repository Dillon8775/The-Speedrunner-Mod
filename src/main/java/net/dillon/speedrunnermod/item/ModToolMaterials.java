package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;

/**
 * All Speedrunner Mod {@code tool materials} (for pickaxes, axes, swords, etc.)
 */
public class ModToolMaterials {
    public static final ToolMaterial SPEEDRUNNER_SHOVEL_AXE_HOE = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 500, 11.0F, 0.0F, 17, ModItemTags.SPEEDRUNNER_TOOL_MATERIALS);
    public static final ToolMaterial SPEEDRUNNER_SWORD_PICKAXE = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 500, 11.0F, 0.5F, 17, ModItemTags.SPEEDRUNNER_TOOL_MATERIALS);
    public static final ToolMaterial GOLDEN_SPEEDRUNNER = new ToolMaterial(BlockTags.INCORRECT_FOR_GOLD_TOOL, 72, 13.0F, 0.0F, 25, ModItemTags.GOLDEN_SPEEDRUNNER_TOOL_MATERIALS);
    public static final ToolMaterial WITHER_SWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 17, 4.0F, 1.0F, 7, ModItemTags.WITHER_TOOL_MATERIALS);
    public static final ToolMaterial DRAGONS_SWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2036, 14.0F, 2.0F, 30, ModItemTags.DRAGON_TOOL_MATERIALS);
}