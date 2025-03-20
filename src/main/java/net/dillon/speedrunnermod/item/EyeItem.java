package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.tag.ModStructureTags;
import net.minecraft.registry.tag.StructureTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.gen.structure.Structure;

/**
 * For all speedrunner mod "eye" items, which are used to locate exact distances of structures and print them.
 */
public interface EyeItem {

    /**
     * The text to send when the eye has located a structure.
     */
    default Text locationText(int structureDistance, Text structureTexts) {
        Text distance = Text.translatable("item.speedrunnermod.eye.distance", structureDistance).formatted(this.distanceFormatting(structureDistance));
        return Text.translatable("item.speedrunnermod.speedrunners_eye.blocks_away", structureTexts, distance);
    }

    /**
     * Determines the color of the structure distance text based on how far away it is. The closer, the better.
     */
    default Formatting distanceFormatting(int structureDistance) {
        return structureDistance < 200 ? Formatting.GREEN : structureDistance < 600 ? Formatting.GOLD : structureDistance < 1999 ? Formatting.RED : Formatting.DARK_RED;
    }

    /**
     * The structure texts to display.
     */
    default Text structureTexts(TagKey<Structure> structureType) {
        Text structure;

        if (structureType.equals(StructureTags.RUINED_PORTAL)) {
            structure = Text.literal("§dRuined Portal");
        } else if (structureType.equals(StructureTags.SHIPWRECK)) {
            structure = Text.literal("§bShipwreck");
        } else if (structureType.equals(StructureTags.ON_OCEAN_EXPLORER_MAPS)) {
            structure = Text.literal("§9Ocean Monument");
        } else if (structureType.equals(StructureTags.ON_WOODLAND_EXPLORER_MAPS)) {
            structure = Text.literal("§7Woodland Mansion");
        } else if (structureType.equals(ModStructureTags.DESERT_PYRAMIDS)) {
            structure = Text.literal("§eDesert Pyramid");
        } else if (structureType.equals(ModStructureTags.ANCIENT_CITIES)) {
            structure = Text.literal("§9Ancient City");
        } else if (structureType.equals(StructureTags.ON_TRIAL_CHAMBERS_MAPS)) {
            structure = Text.literal("§aTrial Chamber");
        } else if (structureType.equals(ModStructureTags.FORTRESSES)) {
            structure = Text.literal("§cNether Fortress");
        } else if (structureType.equals(ModStructureTags.BASTIONS)) {
            structure = Text.literal("§6Bastion");
        } else if (structureType.equals(ModStructureTags.STRONGHOLDS)) {
            structure = Text.literal("§aStronghold");
        } else {
            structure = Text.literal("§6Village");
        }

        return structure;
    }

    /**
     * The calculating text for when the item begins calcuating the distance of the structure.
     */
    default Text calculatingText() {
        return Text.translatable("item.speedrunnermod.eye.calculating").formatted(Formatting.RED);
    }
}