package net.dillon.speedrunnermod.option;

import net.dillon.speedrunnermod.util.ModUtil;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.configHandler;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientConfigHandler;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.option.ModOptions.isIntegerOptionValid;

/**
 * All {@code Client-side Speedrunner Mod options.}
 */
public class ClientModOptions {
    public static final ModClientOptionsHandler CLIENT_INSTANCE = new ModClientOptionsHandler();
    public final Client client = new Client();
    public final Mixins mixins = new Mixins();
    public final StoredValues storedValues = new StoredValues();

    public static class Client {

        /**
         * Enable/disable Minecraft's default fog.
         */
        public OptionValue<Boolean> fog = new OptionValue<>(true, false);

        /**
         * Enables/disables fullbright.
         */
        public OptionValue<Boolean> fullBright = new OptionValue<>(false, false);

        /**
         * Determines whether certain player messages should be sent to the player's chat or actionbar (the area above the hotbar).
         */
        public OptionValue<ItemMessages> itemMessages = new OptionValue<>(ItemMessages.ACTIONBAR, false);

        /**
         * Create a new world with just one click.
         */
        public OptionValue<Boolean> instantWorldCreation = new OptionValue<>(true, false);

        /**
         * Determines the gamemode they every new world should generate with.
         */
        public OptionValue<GameMode> gameMode = new OptionValue<>(GameMode.SURVIVAL, false);

        /**
         * Determines the difficulty that every new world should generate with.
         */
        public OptionValue<Difficulty> difficulty = new OptionValue<>(Difficulty.EASY, false);

        /**
         * Allows cheats when a new world is created.
         */
        public OptionValue<Boolean> allowCommands = new OptionValue<>(false, false);

        /**
         * The seed when a new world is created. Leave blank for random seed.
         */
        public OptionValue<String> seed = new OptionValue<>("", false);

        /**
         * Display the reset button on the title screen, game menu screen and pause screen.
         */
        public OptionValue<Boolean> showResetButton = new OptionValue<>(true, false);

        /**
         * The amount that the brightness should be set to when fullbright is turned on.
         */
        public IntegerOptionValue fullbrightAmount = new IntegerOptionValue(1200, false, 300, 1200);

        /**
         * Sets the inventory slot that the flight duration 3 firework rockets should be given to when iCarus Mode is enabled.
         */
        public IntegerOptionValue iCarusFireworksInventorySlot = new IntegerOptionValue(1, false, 1, 36);

        /**
         * Sets the inventory slot that the InfiniPearl item should be given to when InfiniPearl mode is enabled.
         * <p>This value is incremented by one if iCarus Mode is already enabled.</p>
         */
        public IntegerOptionValue infiniPearlInventorySlot = new IntegerOptionValue(1, false, 1, 36);

        /**
         * Increases lava vision distance when submerged in lava with the fire resistance effect.
         */
        public OptionValue<Boolean> increasedLavaVision = new OptionValue<>(true, false);

        /**
         * The minimum brightness amount for the Speedrunner Mod.
         */
        public OptionValue<Double> minimumBrightness = new OptionValue<>(0.0D, false);

        /**
         * To show people who to turn to.
         */
        public OptionValue<Boolean> canCloseEndCredits = new OptionValue<>(false, false);
    }

    public static class Mixins {

        /**
         * Applies the fog option into the game.
         * <p>Disable this if you are experiencing compatibility issues with other mods that may also mess with fog settings.</p>
         */
        public OptionValue<Boolean> fogMixins = new OptionValue<>(true, true);

        /**
         * Applies the simple option mixin into the game, which controls the brightness option slider.
         * <p>Disable this if you are experiencing compatibility issues with other mods, or if you don't want the new brightness slider.</p>
         */
        public OptionValue<Boolean> optionInstanceMixin = new OptionValue<>(true, true);

        /**
         * Applies the logo drawer mixin into the game, which adds the custom speedrunner edition logo to the title screen.
         * <p>Disable this if you do not want the custom logo, or are making a custom texture pack that uses a different logo, or are experiencing compatibility issues with other mods.</p>
         */
        public OptionValue<Boolean> logoRendererMixin = new OptionValue<>(true, true);
    }

    /**
     * Unconfigurable options; just used for storage and reference.
     */
    public static class StoredValues {

        /**
         * Determines if the first-time playing screens should load.
         */
        public OptionValue<Boolean> firstTimePlaying = new OptionValue<>(true, false);
    }

    /**
     * Returns true if the {@code Item Messages} option is set to actionbar.
     */
    public static boolean isActionbar() {
        return clientOptions().client.itemMessages.getCurrentValue().equals(ItemMessages.ACTIONBAR);
    }

    /**
     * Resets all of the {@code speedrunner mod options} back to factory default.
     */
    public static void resetAllOptions() {
        configHandler().resetToDefault();
    }

    /**
     * Resets all of the {@code speedrunner mod client-side options} back to factory default.
     */
    public static void resetAllClientOptions() {
        clientConfigHandler().resetToDefault();
    }

    /**
     * A handler class for handling the client-side options file.
     */
    public static class ModClientOptionsHandler extends BaseOptions<ClientModOptions> {

        protected ModClientOptionsHandler() {
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
            if (clientOptions().client.itemMessages.getCurrentValue() == null) {
                this.setBroken(clientOptions().client.itemMessages, "itemMessages");
            }

            if (clientOptions().client.gameMode.getCurrentValue() == null) {
                this.setBroken(clientOptions().client.gameMode, "gameMode");
            }

            if (clientOptions().client.difficulty.getCurrentValue() == null) {
                this.setBroken(clientOptions().client.difficulty, "difficulty");
            }

            if (!isIntegerOptionValid(clientOptions().client.iCarusFireworksInventorySlot)) {
                this.setBroken(clientOptions().client.iCarusFireworksInventorySlot, "iCarusFireworksInventorySlot");
            }

            if (!isIntegerOptionValid(clientOptions().client.infiniPearlInventorySlot)) {
                this.setBroken(clientOptions().client.infiniPearlInventorySlot, "infiniPearlInventorySlot");
            }
        }
    }
}
