package net.dillon.speedrunnermod.util;

import net.dillon.speedrunnermod.option.ModOptions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

public interface TutorialMode {
    List<TutorialStep> EXCLUDED_EASY_MODE_STEPS = List.of(
            TutorialStep.OBTAINED_SPEEDRUNNER_ARMOR,
            TutorialStep.OBTAINED_SPEEDRUNNER_SHIELD,
            TutorialStep.OBTAINED_TOTEM,
            TutorialStep.FREE_FALLED_INTO_VOID,
            TutorialStep.OBTAINED_SPEEDRUNNERS_TOTEM,
            TutorialStep.KILLED_GOLIATH,
            TutorialStep.KILLED_WITHER
    );
    List<TutorialStep> EXCLUDED_NORMAL_MODE_STEPS = List.of(
            TutorialStep.OBTAINED_PIGLIN_AWAKENER,
            TutorialStep.USED_PIGLIN_AWAKENER,
            TutorialStep.OBTAINED_BLAZE_SPOTTER,
            TutorialStep.USED_BLAZE_SPOTTER,
            TutorialStep.OBTAINED_DRAGONS_PEARL,
            TutorialStep.OBTAINED_ANNUL_EYE,
            TutorialStep.USED_ANNUL_EYE,
            TutorialStep.USED_DRAGONS_PEARL,
            TutorialStep.OBTAINED_ENTER_THRUSTER,
            TutorialStep.USED_ENTER_THRUSTER,
            TutorialStep.OBTAINED_DRAGONS_SWORD
    );
    List<TutorialStep> DOOM_MODE_STEPS = List.of(
            TutorialStep.ENTERED_WORLD,
            TutorialStep.OBTAINED_SPEEDRUNNER_PICKAXE,
            TutorialStep.OBTAINED_SPEEDRUNNER_BOAT,
            TutorialStep.OBTAINED_SPEEDRUNNER_ARMOR,
            TutorialStep.OBTAINED_SPEEDRUNNER_SHIELD,
            TutorialStep.OBTAINED_SPEEDRUNNERS_EYE,
            TutorialStep.OBTAINED_DRAGONS_PEARL,
            TutorialStep.OBTAINED_ANNUL_EYE,
            TutorialStep.OBTAINED_SPEEDRUNNERS_WORKBENCH,
            TutorialStep.USED_ANNUL_EYE,
            TutorialStep.BROKEN_EXPERIENCE_ORE,
            TutorialStep.TRANSFERRED_ENCHANTMENTS,
            TutorialStep.INTERACTED_WITH_RETIRED_SPEEDRUNNER,
            TutorialStep.ENTERED_END,
            TutorialStep.OBTAINED_TOTEM,
            TutorialStep.FREE_FALLED_INTO_VOID,
            TutorialStep.OBTAINED_SPEEDRUNNERS_TOTEM,
            TutorialStep.KILLED_GOLIATH,
            TutorialStep.KILLED_WITHER,
            TutorialStep.USED_DRAGONS_PEARL,
            TutorialStep.KILLED_DRAGON
    );

    /**
     * Gets a step in tutorial mode.
     */
    @ChatGPT(Credit.FULL_CREDIT)
    boolean getStep(TutorialStep step);

    /**
     * Sets a step in tutorial mode.
     */
    @ChatGPT(Credit.FULL_CREDIT)
    void setStep(TutorialStep step, boolean value);

    /**
     * Returns true if a tutorial step can complete.
     */
    @ChatGPT(Credit.PARTIAL_CREDIT)
    default boolean canComplete(TutorialStep step) {
        TutorialStep[] steps;
        List<TutorialStep> stepsList = new ArrayList<>(Arrays.stream(TutorialStep.values()).toList());
        if (options().main.playingMode.normal()) {
            for (TutorialStep excludedStep : EXCLUDED_NORMAL_MODE_STEPS) {
                stepsList.remove(excludedStep);
            }
        } else if (options().main.playingMode.doom()) {
            stepsList = DOOM_MODE_STEPS;
        } else {
            for (TutorialStep excludedStep : EXCLUDED_EASY_MODE_STEPS) {
                stepsList.remove(excludedStep);
            }
        }
        steps = stepsList.toArray(new TutorialStep[0]);
        for (TutorialStep s : steps) {
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
    @ChatGPT(Credit.MOST_CREDIT)
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