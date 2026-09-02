package net.dillon.speedrunnermod.option;

import net.dillon.speedrunnermod.main.SpeedrunnerModClient;
import net.dillon.speedrunnermod.option.eum.ItemMessages;
import net.dillon.speedrunnermod.option.eum.WorldDifficulty;
import net.dillon.speedrunnermod.option.eum.WorldGameMode;

/**
 * All {@code Client-side Speedrunner Mod options.}
 */
public class ModClientOptions {
    public static final ModClientOptionsHandler INSTANCE = new ModClientOptionsHandler();
    private final General general = new General();
    private final WorldCreation worldCreation = new WorldCreation();
    private final Mixins mixins = new Mixins();
    private final StoredValues storedValues = new StoredValues();

    public General general() {
        return this.general;
    }

    public WorldCreation worldCreation() {
        return this.worldCreation;
    }

    public Mixins mixins() {
        return this.mixins;
    }

    public StoredValues storedValues() {
        return this.storedValues;
    }

    /**
     * A handler class for handling the client-side options file.
     */
    public static class ModClientOptionsHandler extends ModBaseOptionsHandler<ModClientOptions> {

        protected ModClientOptionsHandler() {
            super("speedrunnermod_client.json");
        }

        @Override
        protected ModClientOptions createDefault() {
            return new ModClientOptions();
        }

        @Override
        protected Class<ModClientOptions> getConfigClass() {
            return ModClientOptions.class;
        }
    }

    public static class General {

        /**
         * Enable/disable Minecraft's default fog.
         */
        public boolean fog = false;

        /**
         * Enables/disables fullbright.
         */
        public boolean fullBright = false;

        /**
         * Determines whether certain player messages should be sent to the player's chat or actionbar (the area above the hotbar).
         */
        public ItemMessages itemMessages = ItemMessages.OVERLAY;

        /**
         * Determines whether warning messages are send to the player's chat.
         */
        public boolean warningMessages = true;

        /**
         * Display the reset button on the title screen, game menu screen and pause screen.
         */
        public boolean showResetButton = true;

        /**
         * The amount that the brightness should be set to when fullbright is turned on.
         */
        public int fullBrightAmount = 1200;

        /**
         * Sets the inventory slot that the flight duration 3 firework rockets should be given to when iCarus Mode is enabled.
         */
        public int iCarusFireworksInventorySlot = 1;

        /**
         * Sets the inventory slot that the InfiniPearl item should be given to when InfiniPearl mode is enabled.
         * <p>This value is incremented by one if iCarus Mode is already enabled.</p>
         */
        public int infiniPearlInventorySlot = 1;

        /**
         * Increases lava vision distance when submerged in lava with the fire resistance effect.
         */
        public boolean increasedLavaVision = true;
    }

    public static class WorldCreation {

        /**
         * Create a new world with just one click.
         */
        public boolean instantWorldCreation = true;

        /**
         * Determines the gamemode they every new world should generate with.
         */
        public WorldGameMode worldGameMode = WorldGameMode.SURVIVAL;

        /**
         * Determines the difficulty that every new world should generate with.
         */
        public WorldDifficulty worldDifficulty = WorldDifficulty.EASY;

        /**
         * Allows cheats when a new world is created.
         */
        public boolean allowCommands = false;

        /**
         * The seed when a new world is created. Leave blank for random seed.
         */
        public String seed = "";
    }

    public static class Mixins {

        /**
         * Applies the fog option into the game.
         * <p>Disable this if you are experiencing compatibility issues with other mods that may also mess with fog settings.</p>
         */
        public boolean fogMixins = true;

        /**
         * Applies fov modifications into the game, for speedrunner bows
         * <p>Disable this if you are experiencing compatibility issues with other mods..</p>
         */
        public boolean abstractClientPlayerMixin = true;

        /**
         * Applies the simple option mixin into the game, which controls the brightness option slider.
         * <p>Disable this if you are experiencing compatibility issues with other mods, or if you don't want the new brightness slider.</p>
         */
        public boolean optionInstanceMixin = true;

        /**
         * Applies the logo drawer mixin into the game, which adds the custom speedrunner edition logo to the title screen.
         * <p>Disable this if you do not want the custom logo, or are making a custom texture pack that uses a different logo, or are experiencing compatibility issues with other mods.</p>
         */
        public boolean logoRendererMixin = true;
    }

    /**
     * Unconfigurable options; just used for storage and reference.
     */
    public static class StoredValues {

        /**
         * Determines if the first-time playing screens should load.
         */
        public boolean firstTimePlaying = true;

        /**
         * Determines if the user should load into feature screens.
         */
        public boolean viewFeatures = true;

        /**
         * To show people who to turn to.
         */
        public boolean canCloseEndCredits = false;
    }

    public static boolean isOverlay() {
        return SpeedrunnerModClient.client().general().itemMessages.equals(ItemMessages.OVERLAY);
    }
}
