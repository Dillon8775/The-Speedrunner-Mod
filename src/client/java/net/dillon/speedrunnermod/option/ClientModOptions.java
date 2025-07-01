package net.dillon.speedrunnermod.option;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.tutorial.TutorialStep;
import net.dillon.speedrunnermod.util.AI;
import net.dillon.speedrunnermod.util.ModUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.TranslatableOption;
import net.minecraft.util.math.MathHelper;

import java.util.*;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.*;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientConfigHandler;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.option.ModOptions.isInBounds;
import static net.dillon.speedrunnermod.option.ModOptions.isSafeToPlay;

/**
 * All {@code Client-side Speedrunner Mod options.}
 */
@Environment(EnvType.CLIENT)
public class ClientModOptions {
    public final Client client = new Client();
    public final Mixins mixins = new Mixins();
    public final StoredValues storedValues = new StoredValues();
    public final TutorialMode tutorialMode = new TutorialMode();

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
            if (clientOptions().client.itemMessages.getCurrentValue() == null) {
                error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.itemMessages");
                isSafeToPlay(false);
                ClientBrokenModOptions.itemMessages = true;
            }

            if (clientOptions().client.gameMode.getCurrentValue() == null) {
                error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.gameMode");
                isSafeToPlay(false);
                ClientBrokenModOptions.gameMode = true;
            }

            if (clientOptions().client.difficulty.getCurrentValue() == null) {
                error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.difficulty");
                isSafeToPlay(false);
                ClientBrokenModOptions.difficulty = true;
            }

            if (!clientOptions().isIcarusFireworksInventorySlotValid()) {
                error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.icarusFireworksInventorySlot");
                isSafeToPlay(false);
                BrokenModOptions.iCarusFireworksInventorySlot = true;
            }

            if (!clientOptions().isInfiniPearlInventorySlotValid()) {
                error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.infiniPearlInventorySlot");
                isSafeToPlay(false);
                BrokenModOptions.infiniPearlInventorySlot = true;
            }
        }
    }

    public static class Client {

        /**
         * Takes the player through various different features in the mod.
         */
        public OptionValue<Boolean> tutorialMode = new OptionValue<>(false, true);

        /**
         * Determines if the first-time playing screens should load.
         */
        public OptionValue<Boolean> firstTimePlaying = new OptionValue<>(true, false);

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
        public OptionValue<Boolean> fastWorldCreation = new OptionValue<>(true, false);

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
        public OptionValue<Boolean> allowCheats = new OptionValue<>(false, false);

        /**
         * Display the reset button on the title screen, game menu screen and pause screen.
         */
        public OptionValue<Boolean> showResetButton = new OptionValue<>(true, false);

        /**
         * Sets the inventory slot that the flight duration 3 firework rockets should be given to when iCarus Mode is enabled.
         */
        public OptionValue<Integer> iCarusFireworksInventorySlot = new OptionValue<>(1, false);

        /**
         * Sets the inventory slot that the InfiniPearl item should be given to when InfiniPearl mode is enabled.
         * <p>This value is incremented by one if iCarus Mode is already enabled.</p>
         */
        public OptionValue<Integer> infiniPearlInventorySlot = new OptionValue<>(1, false);

        /**
         * The minimum brightness amount for the Speedrunner Mod.
         */
        public OptionValue<Double> minimumBrightness = new OptionValue<>(0.0D, false);

        /**
         * The maximum brightness amount for the Speedrunner Mod.
         */
        public OptionValue<Double> maximumBrightness = new OptionValue<>(12.0D, false);
    }

    public static class Mixins {

        /**
         * Applies the fog option into the game.
         * <p>Disable this if you are experiencing compatibility issues with other mods that may also mess with fog settings.</p>
         */
        public OptionValue<Boolean> backgroundRendererMixin = new OptionValue<>(true, true);

        /**
         * Applies the simple option mixin into the game, which controls the brightness option slider.
         * <p>Disable this if you are experiencing compatibility issues with other mods, or if you don't want the new brightness slider.</p>
         */
        public OptionValue<Boolean> simpleOptionMixin = new OptionValue<>(true, true);

        /**
         * Applies the logo drawer mixin into the game, which adds the custom speedrunner edition logo to the title screen.
         * <p>Disable this if you do not want the custom logo, or are making a custom texture pack that uses a different logo, or are experiencing compatibility issues with other mods.</p>
         */
        public OptionValue<Boolean> logoDrawerMixin = new OptionValue<>(true, true);

        /**
         * Applies the render layers mixin into the game, which registers a render layer for lava boats.
         * <p>I would only disable this if you absolutely have to, or if you are experiencing noticeable issues with lava boats, or aren't using them.</p>
         */
        public OptionValue<Boolean> renderLayersMixin = new OptionValue<>(true, true);
    }

    /**
     * Unconfigurable options; just used for storage and reference.
     */
    public static class StoredValues {

        /**
         * Returns the last completed tutorial step message translation key(s). These messages are sent when the player rejoins the world.
         */
        public OptionValue<List<String>> lastCompletedTutorialStepTranslations = new OptionValue<>(new ArrayList<>(), false);
    }

    /**
     * All booleans for doing certain things in the tutorial mode.
     */
    @AI
    public static class TutorialMode implements net.dillon.speedrunnermod.tutorial.TutorialMode {
        public OptionValue<Boolean> enterWorld = new OptionValue<>(false, false);
        public OptionValue<Boolean> obtainedSpeedrunnerPickaxe = new OptionValue<>(false, false);
        public OptionValue<Boolean> obtainedSpeedrunnerPaddle = new OptionValue<>(false, false);
        public OptionValue<Boolean> obtainedSpeedrunnerBoat = new OptionValue<>(false, false);
        public OptionValue<Boolean> obtainedSpeedrunnerArmorSet = new OptionValue<>(false, false);
        public OptionValue<Boolean> obtainedSpeedrunnerShield = new OptionValue<>(false, false);
        public OptionValue<Boolean> obtainedInfernoEye = new OptionValue<>(false, false);
        public OptionValue<Boolean> usedInfernoEye = new OptionValue<>(false, false);
        public OptionValue<Boolean> obtainedPiglinAwakener = new OptionValue<>(false, false);
        public OptionValue<Boolean> usedPiglinAwakener = new OptionValue<>(false, false);
        public OptionValue<Boolean> obtainedBlazeSpotter = new OptionValue<>(false, false);
        public OptionValue<Boolean> usedBlazeSpotter = new OptionValue<>(false, false);
        public OptionValue<Boolean> obtainedSpeedrunnersEye = new OptionValue<>(false, false);
        public OptionValue<Boolean> changedSpeedrunnersEyeLocator = new OptionValue<>(false, false);
        public OptionValue<Boolean> usedSpeedrunnersEye = new OptionValue<>(false, false);
        public OptionValue<Boolean> obtainedEnderEye = new OptionValue<>(false, false);
        public OptionValue<Boolean> usedEnderEye = new OptionValue<>(false, false);
        public OptionValue<Boolean> obtainedDragonsPearl = new OptionValue<>(false, false);
        public OptionValue<Boolean> obtainedAnnulEye = new OptionValue<>(false, false);
        public OptionValue<Boolean> usedAnnulEye = new OptionValue<>(false, false);
        public OptionValue<Boolean> enteredEnd = new OptionValue<>(false, false);
        public OptionValue<Boolean> obtainedTotem = new OptionValue<>(false, false);
        public OptionValue<Boolean> freeFalledIntoVoid = new OptionValue<>(false, false);
        public OptionValue<Boolean> obtainedSpeedrunnersTotem = new OptionValue<>(false, false);
        public OptionValue<Boolean> brokenDoomBlock = new OptionValue<>(false, false);
        public OptionValue<Boolean> killedGoliath = new OptionValue<>(false, false);
        public OptionValue<Boolean> killedWither = new OptionValue<>(false, false);
        public OptionValue<Boolean> usedDragonsPearl = new OptionValue<>(false, false);
        public OptionValue<Boolean> killedDragon = new OptionValue<>(false, false);
        public OptionValue<Boolean> exitEnd = new OptionValue<>(false, false);
        public OptionValue<Boolean> brokenExperienceOre = new OptionValue<>(false, false);
        public OptionValue<Boolean> obtainedSpeedrunnersWorkbench = new OptionValue<>(false, false);
        public OptionValue<Boolean> transferedEnchantments = new OptionValue<>(false, false);
        public OptionValue<Boolean> interactedWithRetiredSpeedrunner = new OptionValue<>(false, false);
        public OptionValue<Boolean> obtainedEnderThruster = new OptionValue<>(false, false);
        public OptionValue<Boolean> usedEnderThruster = new OptionValue<>(false, false);
        public OptionValue<Boolean> obtainedDragonsSword = new OptionValue<>(false, false);
        public OptionValue<Boolean> obtainedWitherBone = new OptionValue<>(false, false);
        public OptionValue<Boolean> obtainedWitherSword = new OptionValue<>(false, false);
        public OptionValue<Boolean> obtainedEnderMatter = new OptionValue<>(false, false);
        public OptionValue<Boolean> obtainedInfiniPearl = new OptionValue<>(false, false);

        @Override
        public boolean getStep(TutorialStep step) {
            return switch (step) {
                case ENTER_WORLD -> enterWorld.getCurrentValue();
                case CRAFT_SPEEDRUNNER_PICKAXE -> obtainedSpeedrunnerPickaxe.getCurrentValue();
                case CRAFT_SPEEDRUNNER_PADDLE -> obtainedSpeedrunnerPaddle.getCurrentValue();
                case CRAFT_SPEEDRUNNER_BOAT -> obtainedSpeedrunnerBoat.getCurrentValue();
                case CRAFT_SPEEDRUNNER_ARMOR -> obtainedSpeedrunnerArmorSet.getCurrentValue();
                case CRAFT_SPEEDRUNNER_SHIELD -> obtainedSpeedrunnerShield.getCurrentValue();
                case CRAFT_INFERNO_EYE -> obtainedInfernoEye.getCurrentValue();
                case USE_INFERNO_EYE -> usedInfernoEye.getCurrentValue();
                case CRAFT_PIGLIN_AWAKENER -> obtainedPiglinAwakener.getCurrentValue();
                case USE_PIGLIN_AWAKENER -> usedPiglinAwakener.getCurrentValue();
                case CRAFT_BLAZE_SPOTTER -> obtainedBlazeSpotter.getCurrentValue();
                case USE_BLAZE_SPOTTER -> usedBlazeSpotter.getCurrentValue();
                case CRAFT_SPEEDRUNNERS_EYE -> obtainedSpeedrunnersEye.getCurrentValue();
                case CHANGE_SPEEDRUNNERS_EYE_LOCATOR -> changedSpeedrunnersEyeLocator.getCurrentValue();
                case USE_SPEEDRUNNERS_EYE -> usedSpeedrunnersEye.getCurrentValue();
                case USE_ENDER_EYE -> usedEnderEye.getCurrentValue();
                case CRAFT_ANNUL_EYE -> obtainedAnnulEye.getCurrentValue();
                case CRAFT_DRAGONS_PEARL -> obtainedDragonsPearl.getCurrentValue();
                case CRAFT_ENDER_EYE -> obtainedEnderEye.getCurrentValue();
                case USE_ANNUL_EYE -> usedAnnulEye.getCurrentValue();
                case ENTER_END -> enteredEnd.getCurrentValue();
                case OBTAIN_TOTEM_OF_UNDYING -> obtainedTotem.getCurrentValue();
                case FREE_FALL_INTO_VOID -> freeFalledIntoVoid.getCurrentValue();
                case OBTAIN_SPEEDRUNNERS_TOTEM -> obtainedSpeedrunnersTotem.getCurrentValue();
                case BREAK_DOOM_BLOCK -> brokenDoomBlock.getCurrentValue();
                case KILL_GOLIATH -> killedGoliath.getCurrentValue();
                case KILL_WITHER -> killedWither.getCurrentValue();
                case USE_DRAGONS_PEARL -> usedDragonsPearl.getCurrentValue();
                case KILL_DRAGON -> killedDragon.getCurrentValue();
                case EXIT_END -> exitEnd.getCurrentValue();
                case MINE_EXPERIENCE_ORE -> brokenExperienceOre.getCurrentValue();
                case CRAFT_SPEEDRUNNERS_WORKBENCH -> obtainedSpeedrunnersWorkbench.getCurrentValue();
                case TRANSFER_ENCHANTMENTS -> transferedEnchantments.getCurrentValue();
                case INTERACT_WITH_RETIRED_SPEEDRUNNER -> interactedWithRetiredSpeedrunner.getCurrentValue();
                case OBTAIN_ENDER_THRUSTER -> obtainedEnderThruster.getCurrentValue();
                case USE_ENTER_THRUSTER -> usedEnderThruster.getCurrentValue();
                case OBTAIN_DRAGONS_SWORD -> obtainedDragonsSword.getCurrentValue();
                case OBTAIN_WITHER_BONE -> obtainedWitherBone.getCurrentValue();
                case OBTAIN_WITHER_SWORD -> obtainedWitherSword.getCurrentValue();
                case OBTAIN_ENDER_MATTER -> obtainedEnderMatter.getCurrentValue();
                case OBTAIN_INFINI_PEARL -> obtainedInfiniPearl.getCurrentValue();
            };
        }

        @Override
        public void setStep(TutorialStep step, boolean value) {
            switch (step) {
                case ENTER_WORLD -> enterWorld.set(value);
                case CRAFT_SPEEDRUNNER_PICKAXE -> obtainedSpeedrunnerPickaxe.set(value);
                case CRAFT_SPEEDRUNNER_PADDLE -> obtainedSpeedrunnerPaddle.set(value);
                case CRAFT_SPEEDRUNNER_BOAT -> obtainedSpeedrunnerBoat.set(value);
                case CRAFT_SPEEDRUNNER_ARMOR -> obtainedSpeedrunnerArmorSet.set(value);
                case CRAFT_SPEEDRUNNER_SHIELD -> obtainedSpeedrunnerShield.set(value);
                case CRAFT_INFERNO_EYE -> obtainedInfernoEye.set(value);
                case USE_INFERNO_EYE -> usedInfernoEye.set(value);
                case CRAFT_PIGLIN_AWAKENER -> obtainedPiglinAwakener.set(value);
                case USE_PIGLIN_AWAKENER -> usedPiglinAwakener.set(value);
                case CRAFT_BLAZE_SPOTTER -> obtainedBlazeSpotter.set(value);
                case USE_BLAZE_SPOTTER -> usedBlazeSpotter.set(value);
                case CRAFT_SPEEDRUNNERS_EYE -> obtainedSpeedrunnersEye.set(value);
                case CHANGE_SPEEDRUNNERS_EYE_LOCATOR -> changedSpeedrunnersEyeLocator.set(value);
                case USE_SPEEDRUNNERS_EYE -> usedSpeedrunnersEye.set(value);
                case CRAFT_ENDER_EYE -> obtainedEnderEye.set(value);
                case USE_ENDER_EYE -> usedEnderEye.set(value);
                case CRAFT_DRAGONS_PEARL -> obtainedDragonsPearl.set(value);
                case CRAFT_ANNUL_EYE -> obtainedAnnulEye.set(value);
                case USE_ANNUL_EYE -> usedAnnulEye.set(value);
                case ENTER_END -> enteredEnd.set(value);
                case OBTAIN_TOTEM_OF_UNDYING -> obtainedTotem.set(value);
                case FREE_FALL_INTO_VOID -> freeFalledIntoVoid.set(value);
                case OBTAIN_SPEEDRUNNERS_TOTEM -> obtainedSpeedrunnersTotem.set(value);
                case BREAK_DOOM_BLOCK -> brokenDoomBlock.set(value);
                case KILL_GOLIATH -> killedGoliath.set(value);
                case KILL_WITHER -> killedWither.set(value);
                case USE_DRAGONS_PEARL -> usedDragonsPearl.set(value);
                case KILL_DRAGON -> killedDragon.set(value);
                case EXIT_END -> exitEnd.set(value);
                case MINE_EXPERIENCE_ORE -> brokenExperienceOre.set(value);
                case CRAFT_SPEEDRUNNERS_WORKBENCH -> obtainedSpeedrunnersWorkbench.set(value);
                case TRANSFER_ENCHANTMENTS -> transferedEnchantments.set(value);
                case INTERACT_WITH_RETIRED_SPEEDRUNNER -> interactedWithRetiredSpeedrunner.set(value);
                case OBTAIN_ENDER_THRUSTER -> obtainedEnderThruster.set(value);
                case USE_ENTER_THRUSTER -> usedEnderThruster.set(value);
                case OBTAIN_DRAGONS_SWORD -> obtainedDragonsSword.set(value);
                case OBTAIN_WITHER_BONE -> obtainedWitherBone.set(value);
                case OBTAIN_WITHER_SWORD -> obtainedWitherSword.set(value);
                case OBTAIN_ENDER_MATTER -> obtainedEnderMatter.set(value);
                case OBTAIN_INFINI_PEARL -> obtainedInfiniPearl.set(value);
            }
        }
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
    }

    /**
     * Returns true if the {@code Item Messages} option is set to actionbar.
     */
    public static boolean isActionbar() {
        return clientOptions().client.itemMessages.getCurrentValue().equals(ItemMessages.ACTIONBAR);
    }

    /**
     * @return {@code true} if the {@code Icarus Fireworks Inventory Slot} advanced option is valid.
     */
    public boolean isIcarusFireworksInventorySlotValid() {
        return isInBounds(client.iCarusFireworksInventorySlot.getCurrentValue(), 1, 36);
    }

    /**
     * @return {@code true} if the {@code InfiniPearl Inventory Slot} advanced option is valid.
     */
    public boolean isInfiniPearlInventorySlotValid() {
        return isInBounds(client.infiniPearlInventorySlot.getCurrentValue(), 1, 36);
    }

    /**
     * Resets all of the {@code speedrunner mod options} back to factory default.
     */
    @AI
    public static void resetAllOptions() {
        configHandler().resetToDefault();
        resetTutorialModeProgression();
    }

    /**
     * Resets all of the {@code speedrunner mod client-side options} back to factory default.
     */
    @AI
    public static void resetAllClientOptions() {
        clientConfigHandler().resetToDefault();
    }

    /**
     * Resets all tutorial mode options.
     */
    public static void resetTutorialModeProgression() {
        for (TutorialStep step : TutorialStep.values()) {
            try {
                clientOptions().tutorialMode.setStep(step, false);
            } catch (Exception e) {
                SpeedrunnerMod.error("Error resetting tutorial mode option: " + step.name());
                e.printStackTrace();
            }
        }
    }
}
