package net.dillon.speedrunnermod.tutorial;

import net.dillon.speedrunnermod.packet.server.TutorialStepCompleteC2SPacket;
import net.dillon.speedrunnermod.util.AI;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.sound.SoundEvents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.saveClientChanges;
import static net.dillon.speedrunnermod.option.ModOptions.isBalancedMode;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;
import static net.dillon.speedrunnermod.util.ModUtil.sendWithPrefix;

@Environment(EnvType.CLIENT)
public interface TutorialMode {
    List<TutorialStep> EXCLUDED_EASY_MODE_STEPS = List.of(
            TutorialStep.CRAFT_SPEEDRUNNER_ARMOR,
            TutorialStep.CRAFT_SPEEDRUNNER_SHIELD,
            TutorialStep.CRAFT_ENDER_EYE,
            TutorialStep.USE_ENDER_EYE,
            TutorialStep.OBTAIN_TOTEM_OF_UNDYING,
            TutorialStep.FREE_FALL_INTO_VOID,
            TutorialStep.OBTAIN_SPEEDRUNNERS_TOTEM,
            TutorialStep.BREAK_DOOM_BLOCK,
            TutorialStep.KILL_GOLIATH,
            TutorialStep.KILL_WITHER,
            TutorialStep.EXIT_END
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
            TutorialStep.BREAK_DOOM_BLOCK,
            TutorialStep.KILL_GOLIATH,
            TutorialStep.KILL_WITHER,
            TutorialStep.USE_DRAGONS_PEARL,
            TutorialStep.EXIT_END,
            TutorialStep.OBTAIN_ENDER_THRUSTER,
            TutorialStep.USE_ENTER_THRUSTER,
            TutorialStep.OBTAIN_DRAGONS_SWORD
    );
    List<TutorialStep> DOOM_MODE_STEPS = List.of(
            TutorialStep.ENTER_WORLD,
            TutorialStep.CRAFT_SPEEDRUNNER_PICKAXE,
            TutorialStep.CRAFT_SPEEDRUNNER_PADDLE,
            TutorialStep.CRAFT_SPEEDRUNNER_BOAT,
            TutorialStep.CRAFT_SPEEDRUNNER_ARMOR,
            TutorialStep.CRAFT_SPEEDRUNNER_SHIELD,
            TutorialStep.CRAFT_SPEEDRUNNERS_EYE,
            TutorialStep.CHANGE_SPEEDRUNNERS_EYE_LOCATOR,
            TutorialStep.USE_SPEEDRUNNERS_EYE,
            TutorialStep.CRAFT_DRAGONS_PEARL,
            TutorialStep.CRAFT_ANNUL_EYE,
            TutorialStep.MINE_EXPERIENCE_ORE,
            TutorialStep.CRAFT_SPEEDRUNNERS_WORKBENCH,
            TutorialStep.TRANSFER_ENCHANTMENTS,
            TutorialStep.INTERACT_WITH_RETIRED_SPEEDRUNNER,
            TutorialStep.USE_ANNUL_EYE,
            TutorialStep.ENTER_END,
            TutorialStep.OBTAIN_TOTEM_OF_UNDYING,
            TutorialStep.FREE_FALL_INTO_VOID,
            TutorialStep.OBTAIN_SPEEDRUNNERS_TOTEM,
            TutorialStep.BREAK_DOOM_BLOCK,
            TutorialStep.KILL_GOLIATH,
            TutorialStep.KILL_WITHER,
            TutorialStep.USE_DRAGONS_PEARL,
            TutorialStep.KILL_DRAGON,
            TutorialStep.EXIT_END
    );

    /**
     * Gets a step in tutorial mode.
     */
    @AI
    boolean getStep(TutorialStep step);

    /**
     * Sets a step in tutorial mode.
     */
    @AI
    void setStep(TutorialStep step, boolean value);

    /**
     * @return {@code true} if tutorial mode is completed.
     */
    default boolean completed() {
        return (!isDoomMode() && this.getStep(TutorialStep.OBTAIN_INFINI_PEARL)) ||
                (isDoomMode() && this.getStep(TutorialStep.EXIT_END));
    }

    /**
     * Returns true if a tutorial step can complete.
     */
    @AI
    default boolean canComplete(TutorialStep step) {
        if (!clientOptions().client.tutorialMode.getCurrentValue()) {
            return false;
        }
        TutorialStep[] steps;
        List<TutorialStep> stepsList = new ArrayList<>(Arrays.stream(TutorialStep.values()).toList());
        if (isBalancedMode()) {
            for (TutorialStep excludedStep : EXCLUDED_BALANCED_MODE_STEPS) {
                stepsList.remove(excludedStep);
            }
        } else if (isDoomMode()) {
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
    @AI
    default void completeStep(TutorialStep step, ClientPlayerEntity player, String... messageKey) {
        if (this.canComplete(step) && !this.getStep(step)) {
            setStep(step, true);
            List<String> translations = new ArrayList<>();
            for (String s : messageKey) {
                sendWithPrefix(s, player);
                translations.add(s);
            }
            if (completed()) {
                translations = List.of(); // blank list if tutorial mode is completed
            }
            ClientPlayNetworking.send(new TutorialStepCompleteC2SPacket(step, translations));
            player.playSound(SoundEvents.ENTITY_ARROW_HIT_PLAYER, 1.0F, 1.0F);
            saveClientChanges();
        }
    }
}