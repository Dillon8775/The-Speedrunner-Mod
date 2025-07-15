package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.tag.ModStructureTags;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.StructureTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.gen.structure.Structure;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.option.ModOptions.isEasyMode;

/**
 * For all speedrunner mod "eye" items which are used to locate exact distances of structures and print them.
 */
public interface EyeItem {

    /**
     * Decrements an item from the player's inventory.
     */
    default void decrementItem(PlayerEntity player, Item item) {
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack stack = player.getInventory().getStack(i);
            if (stack.isOf(item)) {
                stack.decrement(1);
                break;
            }
        }
    }

    /**
     * The text to send when the eye has located a structure.
     */
    default Text locationText(int structureDistance, Text structureTexts) {
        Text distance = Text.translatable("item.speedrunnermod.eye.distance", structureDistance).formatted(this.distanceFormatting(structureDistance));
        return Text.translatable("item.speedrunnermod.eye.blocks_away", structureTexts, distance);
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
     * Adds the tooltips for {@code State-Of-The-Art} items.
     */
    default void addStateOfTheArtItemTooltip(Consumer<Text> textConsumer) {
        if (!isEasyMode()) {
            textConsumer.accept(ModTexts.STATE_OF_THE_ART_ITEM_DISABLED);
        }
    }
}