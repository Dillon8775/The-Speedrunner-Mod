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
            TutorialStep.CRAFT_SPEEDRUNNER_ARMOR,
            TutorialStep.CRAFT_SPEEDRUNNER_SHIELD,
            TutorialStep.CRAFT_ENDER_EYE,
            TutorialStep.USE_ENDER_EYE,
            TutorialStep.OBTAIN_TOTEM_OF_UNDYING,
            TutorialStep.FREE_FALL_INTO_VOID,
            TutorialStep.OBTAIN_SPEEDRUNNERS_TOTEM,
            TutorialStep.KILL_GOLIATH,
            TutorialStep.KILL_WITHER
    );
    List<TutorialStep> EXCLUDED_BALANCED_MODE_STEPS = List.of(
            TutorialStep.CRAFT_SPEEDRUNNER_ARMOR,
            TutorialStep.CRAFT_SPEEDRUNNER_SHIELD,
            TutorialStep.CRAFT_PIGLIN_AWAKENER,
            TutorialStep.USE_PIGLIN_AWAKENER,
            TutorialStep.CRAFT_BLAZE_SPOTTER,
            TutorialStep.USE_BLAZE_SPOTTER,
            TutorialStep.CRAFT_DRAGONS_PEARL,
            TutorialStep.CRAFT_ANNUL_EYE,
            TutorialStep.USE_ANNUL_EYE,
            TutorialStep.OBTAIN_TOTEM_OF_UNDYING,
            TutorialStep.FREE_FALL_INTO_VOID,
            TutorialStep.OBTAIN_SPEEDRUNNERS_TOTEM,
            TutorialStep.KILL_GOLIATH,
            TutorialStep.KILL_WITHER,
            TutorialStep.USE_DRAGONS_PEARL,
            TutorialStep.OBTAIN_ENDER_THRUSTER,
            TutorialStep.USE_ENTER_THRUSTER,
            TutorialStep.OBTAIN_DRAGONS_SWORD
    );
    List<TutorialStep> DOOM_MODE_STEPS = List.of(
            TutorialStep.ENTER_WORLD,
            TutorialStep.CRAFT_SPEEDRUNNER_PICKAXE,
            TutorialStep.CRAFT_SPEEDRUNNER_BOAT,
            TutorialStep.CRAFT_SPEEDRUNNER_ARMOR,
            TutorialStep.CRAFT_SPEEDRUNNER_SHIELD,
            TutorialStep.CRAFT_SPEEDRUNNERS_EYE,
            TutorialStep.CHANGE_SPEEDRUNNERS_EYE_LOCATOR,
            TutorialStep.USE_SPEEDRUNNERS_EYE,
            TutorialStep.CRAFT_DRAGONS_PEARL,
            TutorialStep.CRAFT_ANNUL_EYE,
            TutorialStep.CRAFT_SPEEDRUNNERS_WORKBENCH,
            TutorialStep.MINE_EXPERIENCE_ORE,
            TutorialStep.TRANSFER_ENCHANTMENTS,
            TutorialStep.INTERACT_WITH_RETIRED_SPEEDRUNNER,
            TutorialStep.USE_ANNUL_EYE,
            TutorialStep.ENTER_END,
            TutorialStep.OBTAIN_TOTEM_OF_UNDYING,
            TutorialStep.FREE_FALL_INTO_VOID,
            TutorialStep.OBTAIN_SPEEDRUNNERS_TOTEM,
            TutorialStep.KILL_GOLIATH,
            TutorialStep.KILL_WITHER,
            TutorialStep.USE_DRAGONS_PEARL,
            TutorialStep.KILL_DRAGON
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
        if (!options().main.tutorialMode) {
            return false;
        }
        TutorialStep[] steps;
        List<TutorialStep> stepsList = new ArrayList<>(Arrays.stream(TutorialStep.values()).toList());
        if (options().main.playingMode.balanced()) {
            for (TutorialStep excludedStep : EXCLUDED_BALANCED_MODE_STEPS) {
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