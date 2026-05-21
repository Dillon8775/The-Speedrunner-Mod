package net.dillon.speedrunnermod.mixin.client;

import net.dillon.speedrunnermod.keybind.ModKeyMappings;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.main.SpeedrunnerModClient;
import net.dillon.speedrunnermod.option.Leaderboards;
import net.dillon.speedrunnermod.screen.SafeBootScreen;
import net.dillon.speedrunnermod.screen.firsttimeplaying.FirstTimePlayingScreen;
import net.dillon.speedrunnermod.screen.leaderboard.LeaderboardsSafeScreen;
import net.dillon.speedrunnermod.screen.misc.SpeedrunIGTMissingScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.components.debug.DebugScreenEntries;
import net.minecraft.client.gui.screens.GenericMessageScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.main.GameConfig;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.function.Function;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.warn;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.saveClientChanges;

/**
 * Implements all keybindings functions into the game.
 */
@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    @Shadow @Final
    public Gui gui;
    @Shadow
    public ClientLevel level;
    @Shadow
    public abstract void disconnect(Screen disconnectionScreen, boolean transferring, boolean bl);
    @Shadow
    public abstract void setScreen(@Nullable Screen screen);
    @Shadow
    public abstract boolean isLocalServer();
    @Shadow
    public abstract @Nullable ServerData getCurrentServer();
    @Shadow @Final
    public Options options;
    @Shadow
    protected abstract boolean addInitialScreens(List<Function<Runnable, Screen>> list);

    /**
     * Ensures that the {@code fullbright} option is correctly initialized when launching the game.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void setGammaUponStart(GameConfig args, CallbackInfo ci) {
        clientOptions().client.fullBright.set(Minecraft.getInstance().options.gamma().get() >= 10.0D);
        saveClientChanges();
    }

    /**
     * All speedrunner mod {@code keybinding} functions.
     */
    @Inject(method = "handleKeybinds", at = @At("TAIL"))
    private void implementSpeedrunnerModKeybindFunctions(CallbackInfo info) {
        while (ModKeyMappings.RESET.consumeClick()) {
            if (this.isLocalServer() && this.getCurrentServer() == null) {
                if (clientOptions().client.fastWorldCreation.getCurrentValue()) {
                    if (this.gui != null) {
                        this.gui.getChat().clearMessages(false);
                    }
                    assert this.level != null;
                    this.level.disconnect(Component.translatable("menu.savingLevel"));
                    this.disconnect(new GenericMessageScreen(Component.translatable("speedrunnermod.menu.generating_new_world")), false, false);
                    CreateWorldScreen.openFresh(Minecraft.getInstance(), null);
                } else {
                    debugWarn("\"Fast World Creation\" is OFF, please enable to use this feature.");
                }
            } else {
                debugWarn("You must be in singleplayer to create new worlds.");
            }
        }

        while (ModKeyMappings.TOGGLE_FOG.consumeClick()) {
            if (ModUtil.isQualityOfQuesoLoaded()) {
                debugWarn("speedrunnermod.fog.quality_of_queso_loaded");
            } else if (ModUtil.isSimpleKeybindsLoaded()) {
                debugWarn("speedrunnermod.keybind.simple_keybinds_loaded");
            } else if (!clientOptions().mixins.fogMixins.getCurrentValue()) {
                debugWarn("speedrunnermod.fog.mixin_disabled");
            } else {
                clientOptions().client.fog.set(!clientOptions().client.fog.getCurrentValue());
                saveClientChanges();
                Minecraft.getInstance().levelRenderer.allChanged();
            }
        }

        while (ModKeyMappings.TOGGLE_FULLBRIGHT.consumeClick()) {
            if (ModUtil.isSimpleKeybindsLoaded()) {
                debugWarn("speedrunnermod.keybind.simple_keybinds_loaded");
            } else if (!clientOptions().mixins.optionInstanceMixin.getCurrentValue()) {
                debugWarn("\"Simple Option Mixin\" is disabled, cannot change brightness.");
            } else {
                clientOptions().client.fullBright.set(!clientOptions().client.fullBright.getCurrentValue());
                saveClientChanges();
                Minecraft.getInstance().options.gamma().set(clientOptions().client.fullBright.getCurrentValue() ? SpeedrunnerModClient.getMaxBrightness() : 1.0D);
                Minecraft.getInstance().options.save();
            }
        }

        while (ModKeyMappings.TOGGLE_HITBOXES.consumeClick()) {
            if (ModUtil.isSimpleKeybindsLoaded()) {
                debugWarn("speedrunnermod.keybind.simple_keybinds_loaded");
            } else {
                boolean bl = Minecraft.getInstance().debugEntries.toggleStatus(DebugScreenEntries.ENTITY_HITBOXES);
                debugWarn(bl ? "debug.show_hitboxes.on" : "debug.show_hitboxes.off");
            }
        }

        while (ModKeyMappings.TOGGLE_CHUNK_BORDERS.consumeClick()) {
            if (ModUtil.isSimpleKeybindsLoaded()) {
                debugWarn("speedrunnermod.keybind.simple_keybinds_loaded");
            } else {
                boolean bl = Minecraft.getInstance().debugEntries.toggleStatus(DebugScreenEntries.ENTITY_HITBOXES);
                debugWarn(bl ? "debug.chunk_boundaries.on" : "debug.chunk_boundaries.off");
            }
        }
    }

    /**
     * Displays the {@code [Debug Warn]:} prefix when sending a debug message.
     */
    @Unique
    private void debugWarn(String string, Object... objects) {
        this.gui.getChat().addClientSystemMessage((ModTexts.BLANK).copy().append((Component.translatable("debug.prefix")).withStyle(ChatFormatting.YELLOW, ChatFormatting.BOLD)).append(" ").append(Component.translatable(string, objects)));
    }

    /**
     * Adds the {@code Safe Mode} feature.
     * <p>If the speedrunner mod detects broken options, then the game will load into the {@link SafeBootScreen}.</p>
     */
    @Inject(method = "buildInitialScreens", at = @At("RETURN"), cancellable = true)
    private void openSpeedrunnerModScreens(Minecraft.GameLoadCookie cookie, CallbackInfoReturnable<Runnable> cir) {
        Runnable vanillaFlow = cir.getReturnValue();
        cir.setReturnValue(() -> {
            if (SpeedrunnerMod.safeBoot) {
                this.setScreen(new SafeBootScreen(null));
                warn("Booted into safe mode, due to corrupt options. It is recommended that you fix these options before proceeding.");
            } else if (clientOptions().storedValues.firstTimePlaying.getCurrentValue()) {
                this.setScreen(new FirstTimePlayingScreen(null));
            } else if (!Leaderboards.isEligibleForLeaderboardRuns() && options().main.leaderboardsMode.getCurrentValue()) {
                this.setScreen(new LeaderboardsSafeScreen(null));
                warn("You have invalid options set for the leaderboards, you must fix these if you want to submit a speedrun to the leaderboards.");
            } else if (options().main.leaderboardsMode.getCurrentValue() && SpeedrunnerModClient.speedrunIGTMissing) {
                this.setScreen(new SpeedrunIGTMissingScreen(null));
                warn("SpeedrunIGT mod is missing, please download to submit speedruns.");
            } else {
                vanillaFlow.run();
            }
        });
    }
}