package net.dillon.speedrunnermod.mixin.client;

import net.dillon.speedrunnermod.client.keybind.ModKeybindings;
import net.dillon.speedrunnermod.main.SpeedrunnerModClient;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.loader.api.FabricLoader;
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

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.saveClientChanges;

/**
 * Implements all keybindings functions into the game.
 */
@Mixin(Minecraft.class)
public abstract class Keybindings {
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
        while (ModKeybindings.RESET.consumeClick()) {
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

        while (ModKeybindings.TOGGLE_FOG.consumeClick()) {
            if (clientOptions().mixins.fogMixins.getCurrentValue()) {
                clientOptions().client.fog.set(!clientOptions().client.fog.getCurrentValue());
                saveClientChanges();
                Minecraft.getInstance().levelRenderer.allChanged();
            } else {
                debugWarn("speedrunnermod.fog.mixin_disabled");
            }
        }

        if (!FabricLoader.getInstance().isModLoaded("simplekeybinds")) {
            while (ModKeybindings.TOGGLE_FULLBRIGHT.consumeClick()) {
                if (clientOptions().mixins.simpleOptionMixin.getCurrentValue()) {
                    clientOptions().client.fullBright.set(!clientOptions().client.fullBright.getCurrentValue());
                    saveClientChanges();
                    Minecraft.getInstance().options.gamma().set(clientOptions().client.fullBright.getCurrentValue() ? SpeedrunnerModClient.getMaxBrightness() : 1.0D);
                    Minecraft.getInstance().options.save();
                } else {
                    debugWarn("\"Simple Option Mixin\" is disabled, cannot change brightness.");
                }
            }

            while (ModKeybindings.TOGGLE_HITBOXES.consumeClick()) {
                boolean bl = Minecraft.getInstance().debugEntries.toggleStatus(DebugScreenEntries.ENTITY_HITBOXES);
                debugWarn(bl ? "debug.show_hitboxes.on" : "debug.show_hitboxes.off");
            }

            while (ModKeybindings.TOGGLE_CHUNK_BORDERS.consumeClick()) {
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
        this.gui.getChat().addMessage((ModTexts.BLANK).copy().append((Component.translatable("debug.prefix")).withStyle(ChatFormatting.YELLOW, ChatFormatting.BOLD)).append(" ").append(Component.translatable(string, objects)));
    }
}