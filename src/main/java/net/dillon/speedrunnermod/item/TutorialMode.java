package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public interface TutorialMode {

    /**
     * Pulled from Keybindings class.
     */
    default void send(String string, PlayerEntity player) {
        player.sendMessage((ModTexts.BLANK).copy().append((Text.translatable("speedrunnermod.tutorial_mode.prefix"))).append("").append(Text.translatable(string)), false);
    }
}