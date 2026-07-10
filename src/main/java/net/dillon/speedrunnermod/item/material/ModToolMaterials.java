package net.dillon.speedrunnermod.item.material;

import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;

import java.util.List;

/**
 * All Speedrunner Mod {@code tool materials} (for pickaxes, axes, swords, etc.)
 */
public class ModToolMaterials {
    public static final ToolMaterial SPEEDRUNNER_SHOVEL_AXE_HOE = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 500, 11.0F, 0.0F, 17, ModItemTags.SPEEDRUNNER_TOOL_MATERIALS);
    public static final ToolMaterial SPEEDRUNNER_SWORD_PICKAXE = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 500, 11.0F, 0.5F, 17, ModItemTags.SPEEDRUNNER_TOOL_MATERIALS);
    public static final ToolMaterial SPEEDRUNNER_SPEAR = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 500, 11.0F, 2.0F, 17, ModItemTags.SPEEDRUNNER_TOOL_MATERIALS);
    public static final ToolMaterial GOLDEN_SPEEDRUNNER = new ToolMaterial(BlockTags.INCORRECT_FOR_GOLD_TOOL, 72, 13.0F, 0.0F, 25, ModItemTags.GOLDEN_SPEEDRUNNER_TOOL_MATERIALS);
    public static final ToolMaterial GOLDEN_SPEEDRUNNER_SPEAR = new ToolMaterial(BlockTags.INCORRECT_FOR_GOLD_TOOL, 72, 13.0F, 0.0F, 25, ModItemTags.GOLDEN_SPEEDRUNNER_TOOL_MATERIALS);
    public static final ToolMaterial DRAGONS_SWORD = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2036, 14.0F, 2.0F, 36, ModItemTags.DRAGON_TOOL_MATERIALS);

    /**
     * @return if a tool material is valid to modify.
     */
    public static boolean isValidToolMaterialToModify(ToolMaterial toolMaterial) {
        for (ToolMaterial material : speedrunnerToolMaterials()) {
            if (material.equals(toolMaterial)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return a {@link List} of all speedrunner tool materials.
     */
    private static List<ToolMaterial> speedrunnerToolMaterials() {
        return List.of(
                SPEEDRUNNER_SHOVEL_AXE_HOE,
                SPEEDRUNNER_SWORD_PICKAXE,
                SPEEDRUNNER_SPEAR,
                GOLDEN_SPEEDRUNNER,
                GOLDEN_SPEEDRUNNER_SPEAR,
                DRAGONS_SWORD
        );
    }
}