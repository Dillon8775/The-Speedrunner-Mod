package net.dillon.speedrunnermod.client;

import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.saveClientChanges;

/**
 * Stores client-synced server options.
 */
@ChatGPT(Credit.FULL_CREDIT)
@Environment(EnvType.CLIENT)
public class ClientSyncedServerOptions {
    private static final Map<UUID, List<String>> TRANSLATIONS = new HashMap<>();

    /**
     * Sets the last sent {@code tutorial mode message translations.}
     */
    public static void setLastSentTutorialModeMessageTranslations(UUID playerUuid, List<String> translations) {
        TRANSLATIONS.put(playerUuid, translations);
    }

    /**
     * @return the last sent {@code tutorial mode message translations.}
     */
    public static List<String> getLastSentTutorialModeMessageTranslations(UUID playerUuid) {
        return TRANSLATIONS.getOrDefault(playerUuid, clientOptions().storedValues.lastCompletedTutorialStepTranslations);
    }

    /**
     * @return {@code true} if the player {@link UUID} is found; used to send the message again when re-joining the world.
     */
    public static boolean tutorialModeMessageTranslationsContainsPlayerUuid(UUID playerUuid) {
        return TRANSLATIONS.containsKey(playerUuid);
    }

    /**
     * Writes the last sent {@code tutorial mode message translations,} but clears them from memory to save heap space.
     */
    public static void writeAndClearTutorialModeMessageTranslations(UUID playerUuid) {
        clientOptions().storedValues.lastCompletedTutorialStepTranslations = getLastSentTutorialModeMessageTranslations(playerUuid);
        saveClientChanges();
        TRANSLATIONS.remove(playerUuid);
    }
}