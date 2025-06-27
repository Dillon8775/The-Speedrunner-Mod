package net.dillon.speedrunnermod.option;

import net.dillon.speedrunnermod.util.ModUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.TranslatableOption;
import net.minecraft.util.math.MathHelper;

import java.util.*;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.OPTIONS_ERROR_MESSAGE;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.error;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.option.ModOptions.isSafe;

/**
 * All {@code Client-side Speedrunner Mod options.}
 */
@Environment(EnvType.CLIENT)
public class ClientModOptions {
    public final Client client = new Client();
    public final Mixins mixins = new Mixins();
    public final StoredValues storedValues = new StoredValues();

    public static final ClientModOptions.Handler CLIENT_OPTIONS = new Handler();

    /**
     * A handler class for handling the client-side options file.
     */
    public static class Handler extends BaseOptions<ClientModOptions> {

        protected Handler() {
            super(ModUtil.CLIENT_CONFIG_FILE_NAME);
        }

        @Override
        protected ClientModOptions createDefault() {
            return new ClientModOptions();
        }

        @Override
        protected Class<ClientModOptions> getConfigClass() {
            return ClientModOptions.class;
        }

        @Override
        protected void safeCheck() {
            if (clientOptions().client.itemMessages == null) {
                error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.itemMessages");
                isSafe(false);
                ClientBrokenModOptions.itemMessages = true;
            }

            if (clientOptions().client.gameMode == null) {
                error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.gameMode");
                isSafe(false);
                ClientBrokenModOptions.gameMode = true;
            }

            if (clientOptions().client.difficulty == null) {
                error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.difficulty");
                isSafe(false);
                ClientBrokenModOptions.difficulty = true;
            }
        }
    }

    public static class Client {

        /**
         * Determines if the first-time playing screens should load.
         */
        public boolean firstTimePlaying = true;

        /**
         * Enable/disable Minecraft's default fog.
         */
        public boolean fog = true;

        /**
         * Enables/disables fullbright.
         */
        public boolean fullBright = false;

        /**
         * Determines whether certain player messages should be sent to the player's chat or actionbar (the area above the hotbar).
         */
        public ClientModOptions.ItemMessages itemMessages = ClientModOptions.ItemMessages.ACTIONBAR;

        /**
         * Create a new world with just one click.
         */
        public boolean fastWorldCreation = true;

        /**
         * Determines the gamemode they every new world should generate with.
         */
        public ClientModOptions.GameMode gameMode =  ClientModOptions.GameMode.SURVIVAL;

        /**
         * Determines the difficulty that every new world should generate with.
         */
        public ClientModOptions.Difficulty difficulty =  ClientModOptions.Difficulty.EASY;

        /**
         * Allows cheats when a new world is created.
         */
        public boolean allowCheats = false;

        /**
         * Display the reset button on the title screen, game menu screen and pause screen.
         */
        public boolean showResetButton = true;

        /**
         * The minimum brightness amount for the Speedrunner Mod.
         */
        public double minimumBrightness = 0.0D;

        /**
         * The maximum brightness amount for the Speedrunner Mod.
         */
        public double maximumBrightness = 12.0D;
    }

    public static class Mixins {

        /**
         * Applies the fog option into the game.
         * <p>Disable this if you are experiencing compatibility issues with other mods that may also mess with fog settings.</p>
         */
        @RequiresRestart
        public boolean backgroundRendererMixin = true;

        /**
         * Applies the simple option mixin into the game, which controls the brightness option slider.
         * <p>Disable this if you are experiencing compatibility issues with other mods, or if you don't want the new brightness slider.</p>
         */
        @RequiresRestart
        public boolean simpleOptionMixin = true;

        /**
         * Applies the logo drawer mixin into the game, which adds the custom speedrunner edition logo to the title screen.
         * <p>Disable this if you do not want the custom logo, or are making a custom texture pack that uses a different logo, or are experiencing compatibility issues with other mods.</p>
         */
        @RequiresRestart
        public boolean logoDrawerMixin = true;

        /**
         * Applies the render layers mixin into the game, which registers a render layer for lava boats.
         * <p>I would only disable this if you absolutely have to, or if you are experiencing noticeable issues with lava boats, or aren't using them.</p>
         */
        @RequiresRestart
        public boolean renderLayersMixin = true;
    }

    /**
     * Unconfigurable options; just used for storage and reference.
     */
    public static class StoredValues {

        /**
         * Returns the last completed tutorial step message translation key(s). These messages are sent when the player rejoins the world.
         */
        public List<String> lastCompletedTutorialStepTranslations = new ArrayList<>();
    }

    /**
     * All the different {@code GameMode} options.
     */
    public enum GameMode implements TranslatableOption {
        SURVIVAL(0, "speedrunnermod.options.gamemode.survival"),
        CREATIVE(1, "speedrunnermod.options.gamemode.creative"),
        HARDCORE(2, "speedrunnermod.options.gamemode.hardcore"),
        SPECTATOR(3, "speedrunnermod.options.gamemode.spectator");

        private static final GameMode[] VALUES = Arrays.stream(GameMode.values()).sorted(Comparator.comparingInt(GameMode::getId)).toArray(GameMode[]::new);
        private final int id;
        private final String translateKey;

        GameMode(int id, String translationKey) {
            this.id = id;
            this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
        }

        /**
         * Returns the {@code id value} of the fast world creation {@code GameMode} option.
         */
        @Override
        public int getId() {
            return this.id;
        }

        /**
         * Returns the {@code translation key} of the {@code GameMode} option.
         */
        @Override
        public String getTranslationKey() {
            return this.translateKey;
        }

        /**
         * Not sure what this does to be honest, but it's used in ModListOptions.
         */
        public static GameMode byId(int id) {
            return VALUES[MathHelper.floorMod(id, VALUES.length)];
        }
    }

    /**
     * All the different {@code Difficulty} options.
     */
    public enum Difficulty implements TranslatableOption {
        PEACEFUL(0, "speedrunnermod.options.difficulty.peaceful"),
        EASY(1, "speedrunnermod.options.difficulty.easy"),
        NORMAL(2, "speedrunnermod.options.difficulty.normal"),
        HARD(3, "speedrunnermod.options.difficulty.hard");

        private static final Difficulty[] VALUES = Arrays.stream(Difficulty.values()).sorted(Comparator.comparingInt(Difficulty::getId)).toArray(Difficulty[]::new);
        private final int id;
        private final String translateKey;

        Difficulty(int id, String translationKey) {
            this.id = id;
            this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
        }

        /**
         * Returns the {@code id value} of the fast world creation {@code Difficulty} option.
         */
        @Override
        public int getId() {
            return this.id;
        }

        /**
         * Returns the {@code translation key} of the {@code Difficulty} option.
         */
        @Override
        public String getTranslationKey() {
            return this.translateKey;
        }

        /**
         * Not sure what this does to be honest, but it's used in ModListOptions.
         */
        public static Difficulty byId(int id) {
            return VALUES[MathHelper.floorMod(id, VALUES.length)];
        }
    }

    public enum ItemMessages implements TranslatableOption {
        CHAT(0, "speedrunnermod.options.item_messages.chat"),
        ACTIONBAR(1, "speedrunnermod.options.item_messages.actionbar");

        private static final ItemMessages[] VALUES = Arrays.stream(ItemMessages.values()).sorted(Comparator.comparingInt(ItemMessages::getId)).toArray(ItemMessages[]::new);
        private final int id;
        private final String translateKey;

        ItemMessages(int id, String translationKey) {
            this.id = id;
            this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
        }

        /**
         * Returns the {@code id value} of the {@code Item Messages} option.
         */
        @Override
        public int getId() {
            return this.id;
        }

        /**
         * Returns the {@code translation key} of the {@code Item Messages} option.
         */
        @Override
        public String getTranslationKey() {
            return this.translateKey;
        }

        /**
         * Not sure what this does to be honest, but it's used in ModListOptions.
         */
        public static ItemMessages byId(int id) {
            return VALUES[MathHelper.floorMod(id, VALUES.length)];
        }

        /**
         * Returns true if the {@code Item Messages} option is set to actionbar.
         */
        public boolean isActionbar() {
            return clientOptions().client.itemMessages.equals(ACTIONBAR);
        }
    }

    /**
     * Resets all of the {@code speedrunner mod client-side options} back to factory default.
     */
    public static void resetAllClientOptions() {
        clientOptions().client.firstTimePlaying = true;
        clientOptions().client.fog = true;
        clientOptions().client.itemMessages = ClientModOptions.ItemMessages.ACTIONBAR;
        clientOptions().client.fastWorldCreation = true;
        clientOptions().client.gameMode = ClientModOptions.GameMode.SURVIVAL;
        clientOptions().client.difficulty = ClientModOptions.Difficulty.EASY;
        clientOptions().client.allowCheats = false;
        clientOptions().client.showResetButton = true;
        clientOptions().client.minimumBrightness = 0.0D;
        clientOptions().client.maximumBrightness = 12.0D;

        clientOptions().mixins.backgroundRendererMixin = true;
        clientOptions().mixins.simpleOptionMixin = true;
        clientOptions().mixins.logoDrawerMixin = true;
        clientOptions().mixins.renderLayersMixin = true;
    }
}
