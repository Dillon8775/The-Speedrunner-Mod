package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.tag.ModStructureTags;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.StructureTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

/**
 * For all speedrunner mod "eye" items which are used to locate exact distances of structures and print them.
 */
public interface EyeItem {

    /**
     * Decrements a stack from the player's inventory.
     */
    default void decrementIfPossible(Player player, ItemStack stack) {
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }
    }

    /**
     * Decrements an item from the player's inventory.
     */
    @Deprecated(forRemoval = true)
    default void decrementIfPossible(Player player, Item item) {
        if (!player.getAbilities().instabuild) {
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                ItemStack stack = player.getInventory().getItem(i);
                if (stack.is(item)) {
                    stack.shrink(1);
                    break;
                }
            }
        }
    }

    /**
     * The text to send when the eye has located a structure.
     */
    default Component locationText(int structureDistance, Component structureTexts) {
        Component distance = Component.translatable("item.speedrunnermod.eye.distance", structureDistance).withStyle(this.distanceFormatting(structureDistance));
        return Component.translatable("item.speedrunnermod.eye.blocks_away", structureTexts, distance);
    }

    /**
     * Determines the color of the structure distance text based on how far away it is. The closer, the better.
     */
    default ChatFormatting distanceFormatting(int structureDistance) {
        return structureDistance < 200 ? ChatFormatting.GREEN : structureDistance < 600 ? ChatFormatting.GOLD : structureDistance < 1999 ? ChatFormatting.RED : ChatFormatting.DARK_RED;
    }

    /**
     * The structure texts to display.
     */
    default Component structureTexts(TagKey<Structure> structureType) {
        Component structure;

        if (structureType.equals(StructureTags.RUINED_PORTAL)) {
            structure = Component.literal("§dRuined Portal");
        } else if (structureType.equals(StructureTags.SHIPWRECK)) {
            structure = Component.literal("§bShipwreck");
        } else if (structureType.equals(StructureTags.ON_OCEAN_EXPLORER_MAPS)) {
            structure = Component.literal("§9Ocean Monument");
        } else if (structureType.equals(StructureTags.ON_WOODLAND_EXPLORER_MAPS)) {
            structure = Component.literal("§7Woodland Mansion");
        } else if (structureType.equals(ModStructureTags.DESERT_PYRAMIDS)) {
            structure = Component.literal("§eDesert Pyramid");
        } else if (structureType.equals(ModStructureTags.ANCIENT_CITIES)) {
            structure = Component.literal("§9Ancient City");
        } else if (structureType.equals(StructureTags.ON_TRIAL_CHAMBERS_MAPS)) {
            structure = Component.literal("§aTrial Chamber");
        } else if (structureType.equals(ModStructureTags.FORTRESSES)) {
            structure = Component.literal("§cNether Fortress");
        } else if (structureType.equals(ModStructureTags.BASTIONS)) {
            structure = Component.literal("§6Bastion");
        } else if (structureType.equals(ModStructureTags.STRONGHOLDS)) {
            structure = Component.literal("§aStronghold");
        } else {
            structure = Component.literal("§6Village");
        }

        return structure;
    }

    /**
     * Removes obstruction blocks, preventing the player from teleporting.
     */
    default void removeObstructions(Level world, BlockPos pos) {
        boolean isAir = world.getBlockState(pos.above()).isAir() && world.getBlockState(pos.above(1)).isAir();
        if (!isAir) {
            for (int i = 1; i < 3; i++) {
                world.setBlock(pos.above(i), Blocks.AIR.defaultBlockState(), 3);
            }
        }
    }

    /**
     * Loads the chunk before teleporting, to ensure no prevent teleportation bugs, and prevents chunk loading errors.
     */
    default void correctlyTeleport(Level world, BlockPos pos, Player player, float additionalY) {
        if (!(world instanceof ServerLevel serverWorld) || !(player instanceof ServerPlayer serverPlayer)) {
            return;
        }

        double x = pos.getX() + 0.5D;
        double y = pos.getY() + additionalY;
        double z = pos.getZ() + 0.5D;

        ChunkPos chunkPos = new ChunkPos(BlockPos.containing(x, y, z).getX() >> 4, BlockPos.containing(x, y, z).getZ() >> 4);
        // Ensure destination chunk exists before moving the player there.
        serverWorld.getChunk(chunkPos.x(), chunkPos.z());
        serverPlayer.connection.teleport(x, y, z, serverPlayer.getYRot(), serverPlayer.getXRot());
    }

    /**
     * Plays the throwing eye item at the players position for the item with a custom pitch.
     */
    default void playThrowSound(Level world, Player player) {
        this.playWorldSound(SoundEvents.ENDER_PEARL_THROW, 1.0F, 0.4F, world, player);
    }

    /**
     * Plays the teleporting sound at the players position for the item with a custom pitch.
     */
    default void playTeleportSound(Level world, Player player) {
        this.playWorldSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F, world, player);
    }

    /**
     * Plays the eye of ender launch sound at a high pitch at the players position.
     */
    default void playPitchedLaunchSound(float pitch, Level world, Player player) {
        this.playWorldSound(SoundEvents.ENDER_EYE_LAUNCH, 1.0F, pitch, world, player);
    }

    /**
     * Plays a sound at the players position for the item.
     */
    default void playWorldSound(SoundEvent event, Level world, Player player) {
        this.playWorldSound(event, 1.0F, 1.0F, world, player);
    }

    /**
     * Plays a sound at the players position for the item with a custom pitch.
     */
    default void playWorldSound(SoundEvent event, float pitch, Level world, Player player) {
        this.playWorldSound(event, 1.0F, pitch, world, player);
    }

    /**
     * Plays a sound at the players position for the item with a custom pitch and volume.
     */
    default void playWorldSound(SoundEvent event, float volume, float pitch, Level world, Player player) {
        world.playSound(null, player.getX(), player.getY(), player.getZ(), event, SoundSource.PLAYERS, volume, pitch);
    }

    /**
     * Adds the tooltips for {@code State-Of-The-Art} items.
     */
    default void addStateOfTheArtItemTooltip(Consumer<Component> textConsumer) {
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
