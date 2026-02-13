package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.tag.ModStructureTags;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.StructureTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ChunkTicket;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.Structure;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

/**
 * For all speedrunner mod "eye" items which are used to locate exact distances of structures and print them.
 */
public interface EyeItem {

    /**
     * Decrements a stack from the player's inventory.
     */
    default void decrementIfPossible(PlayerEntity player, ItemStack stack) {
        if (!player.getAbilities().creativeMode) {
            stack.decrement(1);
        }
    }

    /**
     * Decrements an item from the player's inventory.
     */
    default void decrementIfPossible(PlayerEntity player, Item item) {
        if (!player.getAbilities().creativeMode) {
            for (int i = 0; i < player.getInventory().size(); i++) {
                ItemStack stack = player.getInventory().getStack(i);
                if (stack.isOf(item)) {
                    stack.decrement(1);
                    break;
                }
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
     * Removes obstruction blocks, preventing the player from teleporting.
     */
    default void removeObstructions(World world, BlockPos pos) {
        boolean isAir = world.getBlockState(pos.up()).isAir() && world.getBlockState(pos.up(1)).isAir();
        if (!isAir) {
            for (int i = 1; i < 3; i++) {
                world.setBlockState(pos.up(i), Blocks.AIR.getDefaultState(), 3);
            }
        }
    }

    /**
     * Loads the chunk before teleporting, to ensure no prevent teleportation bugs.
     */
    default void correctlyTeleport(World world, BlockPos pos, PlayerEntity player, float additionalY) {
        if (!(world instanceof ServerWorld serverWorld)) {
            return;
        }

        ChunkPos chunkPos = new ChunkPos(pos);
        serverWorld.getChunkManager().addTicket(new ChunkTicket(ChunkTicketType.PLAYER_LOADING, 1), chunkPos);
        serverWorld.getChunk(chunkPos.x, chunkPos.z);

        player.teleport(pos.getX() + 0.5F, pos.getY() + additionalY, pos.getZ() + 0.5F, false);
    }

    /**
     * Plays the throwing eye item at the players position for the item with a custom pitch.
     */
    default void playThrowSound(World world, PlayerEntity player) {
        this.playWorldSound(SoundEvents.ENTITY_ENDER_PEARL_THROW, 1.0F, 0.4F, world, player);
    }

    /**
     * Plays the teleporting sound at the players position for the item with a custom pitch.
     */
    default void playTeleportSound(World world, PlayerEntity player) {
        this.playWorldSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F, world, player);
    }

    /**
     * Plays the eye of ender launch sound at a high pitch at the players position.
     */
    default void playPitchedLaunchSound(float pitch, World world, PlayerEntity player) {
        this.playWorldSound(SoundEvents.ENTITY_ENDER_EYE_LAUNCH, 1.0F, pitch, world, player);
    }

    /**
     * Plays a sound at the players position for the item.
     */
    default void playWorldSound(SoundEvent event, World world, PlayerEntity player) {
        this.playWorldSound(event, 1.0F, 1.0F, world, player);
    }

    /**
     * Plays a sound at the players position for the item with a custom pitch.
     */
    default void playWorldSound(SoundEvent event, float pitch, World world, PlayerEntity player) {
        this.playWorldSound(event, 1.0F, pitch, world, player);
    }

    /**
     * Plays a sound at the players position for the item with a custom pitch and volume.
     */
    default void playWorldSound(SoundEvent event, float volume, float pitch, World world, PlayerEntity player) {
        world.playSound(null, player.getX(), player.getY(), player.getZ(), event, SoundCategory.PLAYERS, volume, pitch);
    }

    /**
     * Adds the tooltips for {@code State-Of-The-Art} items.
     */
    default void addStateOfTheArtItemTooltip(Consumer<Text> textConsumer) {
        for (ModOptions.Mode mode : this.disabledModes()) {
            if (options().main.mode.getCurrentValue() == mode) {
                textConsumer.accept(ModTexts.stateOfTheArtItemDisabledTooltip(mode));
            }
        }
    }

    /**
     * @return if the item is disabled.
     */
    default boolean isDisabled() {
        for (ModOptions.Mode mode : this.disabledModes()) {
            if (options().main.mode.getCurrentValue() == mode) {
                return true;
            }
        }
        return false;
    }

    /**
     * All disabled modes.<p>
     * Return {@code ModOptions.Mode[]{}} if there are no disabled modes.
     */
    ModOptions.Mode[] disabledModes();
}