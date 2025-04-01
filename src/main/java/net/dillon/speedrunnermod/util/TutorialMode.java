package net.dillon.speedrunnermod.util;

import net.dillon.speedrunnermod.option.ModOptions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;

public interface TutorialMode {

    /**
     * Gets a step in tutorial mode.
     */
    boolean getStep(TutorialStep step);

    /**
     * Sets a step in tutorial mode.
     */
    void setStep(TutorialStep step, boolean value);

    /**
     * Returns true if a tutorial step can complete.
     */
    default boolean canComplete(TutorialStep step) {
        for (TutorialStep s : TutorialStep.values()) {
            if (s == step) {
                break;
            }
            if (!getStep(s)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Completes a tutorial step.
     */
    default void completeStep(TutorialStep step, PlayerEntity player, String... messageKey) {
        if (canComplete(step) && !getStep(step)) {
            setStep(step, true);
            for (String s : messageKey) {
                send(s, player);
            }
            player.playSoundToPlayer(SoundEvents.ENTITY_ARROW_HIT_PLAYER, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            ModOptions.saveConfig();
        }
    }

    /**
     * Pulled from Keybindings class.
     */
    default void send(String string, PlayerEntity player) {
        player.sendMessage((ModTexts.BLANK).copy().append((Text.translatable("speedrunnermod.tutorial_mode.prefix"))).append("").append(Text.translatable(string)), false);
    }
}