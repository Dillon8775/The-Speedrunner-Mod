package net.dillon.speedrunnermod.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;

public interface TutorialMode {

    /**
     * Pulled from Keybindings class.
     */
    default void send(String string, PlayerEntity player) {
        player.sendMessage((ModTexts.BLANK).copy().append((Text.translatable("speedrunnermod.tutorial_mode.prefix"))).append("").append(Text.translatable(string)), false);
    }

    /**
     * Plays a ding sound when completing a step in tutorial mode.
     */
    default void playDing(PlayerEntity player) {
        player.playSoundToPlayer(SoundEvents.ENTITY_ARROW_HIT_PLAYER, SoundCategory.NEUTRAL, 1.0F, 1.0F);
    }
}