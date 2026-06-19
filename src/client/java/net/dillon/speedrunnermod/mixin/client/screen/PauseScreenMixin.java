package net.dillon.speedrunnermod.mixin.client.screen;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.sugar.Local;
import net.dillon.speedrunnermod.screen.MainScreen;
import net.dillon.speedrunnermod.screen.feature.FeaturesScreen;
import net.dillon.speedrunnermod.screen.option.RestartRequiredScreen;
import net.dillon.speedrunnermod.screen.synced.TimedScreen;
import net.dillon.speedrunnermod.util.ClientModUtil;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.GenericMessageScreen;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.server.IntegratedServer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;

@Mixin(PauseScreen.class)
public class PauseScreenMixin extends Screen {
    @Shadow @Final
    private boolean showPauseMenu;
    @Unique
    private Button featuresButton, optionsButton, createWorldButton, restartButton;

    public PauseScreenMixin(Component title, Button createWorldButton) {
        super(title);
    }

    /**
     * Adds the reset button to the pause screen.
     */
    @Definition(id = "integratedServer", local = @Local(type = IntegratedServer.class, name = "integratedServer"))
    @Expression("integratedServer = ?")
    @Inject(method = "createPauseMenu", at = @At("MIXINEXTRAS:EXPRESSION"))
    private void addResetWorldButton(CallbackInfo ci, @Local(name = "iconButtonRow") LinearLayout iconButtonRow) {
        if (!this.showPauseMenu || !clientOptions().client.showResetButton.getCurrentValue()) {
            return;
        }

        this.createWorldButton = Button.builder(ModTexts.BLANK, (buttonWidget) -> {
            if (this.minecraft.gui != null) {
                this.minecraft.gui.hud.getChat().clearMessages(false);
            }
            this.minecraft.level.disconnect(Component.translatable("menu.savingLevel"));
            this.minecraft.disconnect(new GenericMessageScreen(Component.translatable("speedrunnermod.menu.generating_new_world")), false, false);
            CreateWorldScreen.openFresh(this.minecraft, null);
        }).width(20).build();
        this.createWorldButton.active = clientOptions().client.instantWorldCreation.getCurrentValue();
        iconButtonRow.addChild(this.createWorldButton);
    }

    /**
     * Adds additional buttons to the game menu screen (discord, options button, etc.)
     */
    @Inject(method = "createPauseMenu", at = @At("TAIL"))
    private void addSpeedrunnerModButtons(CallbackInfo ci) {
        if (!this.showPauseMenu) {
            return;
        }

        this.optionsButton = this.addRenderableWidget(Button.builder(ModTexts.BLANK, (buttonWidget) -> {
            this.minecraft.gui.setScreen(new MainScreen(this));
        }).bounds(this.width / 2 - 4 - 120 - 2, this.height / 4 + 96 - 16, 20, 20).build());

        if (RestartRequiredScreen.restartRequired) {
            this.restartButton = this.addRenderableWidget(Button.builder(ModTexts.BLANK, (buttonWidget) -> {
                this.minecraft.gui.setScreen(new TimedScreen(this, 5, false));
            }).bounds(this.optionsButton.getX() - 24, this.optionsButton.getY(), 20, 20).build());
        }

        this.featuresButton = this.addRenderableWidget(Button.builder(ModTexts.BLANK, (buttonWidget) -> {
            this.minecraft.gui.setScreen(new FeaturesScreen(this));
        }).bounds(this.optionsButton.getX(), this.optionsButton.getY() - 48, 20, 20).build());
    }

    /**
     * Renders additional textures on the game menu screen.
     */
    @Inject(method = "extractRenderState", at = @At("TAIL"))
    private void renderSpeedrunnerModButtonTextures(GuiGraphicsExtractor context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (!this.showPauseMenu) {
            return;
        }

        ClientModUtil.renderSpeedrunnerSmithingTemplate(context, this.featuresButton);

        context.blit(RenderPipelines.GUI_TEXTURED, Identifier.parse("speedrunnermod:textures/gui/speedrunner_mod.png"), this.width / 2 - 4 - 58 - 2, this.height / 4 - 26 + 2, 0.0F, 0.0F, 129, 16, 129, 16);

        if (clientOptions().client.showResetButton.getCurrentValue()) {
            context.blit(RenderPipelines.GUI_TEXTURED, Identifier.parse("speedrunnermod:textures/item/speedrunner_boots.png"), createWorldButton.getX() + 2, createWorldButton.getY() + 2, 0.0F, 0.0F, 16, 16, 16, 16);
        }

        ClientModUtil.renderModIcon(context, this.optionsButton);
        if (this.restartButton != null) {
            ClientModUtil.renderSyncIcon(context, this.restartButton);
        }

        this.renderTooltips(context, mouseX, mouseY);
    }

    /**
     * Renders tooltips on certain buttons.
     */
    @Unique
    private void renderTooltips(GuiGraphicsExtractor context, int mouseX, int mouseY) {
        if (clientOptions().client.showResetButton.getCurrentValue() && createWorldButton.isHovered()) {
            context.setTooltipForNextFrame(this.font, this.font.split(clientOptions().client.instantWorldCreation.getCurrentValue() ? ModTexts.CREATE_WORLD_BUTTON_TOOLTIP : ModTexts.CREATE_WORLD_BUTTON_DISABLED_TOOLTIP, 200), mouseX, mouseY);
        }

        if (this.optionsButton.isHovered()) {
            context.setTooltipForNextFrame(this.font, this.font.split(ModTexts.OPTIONS_TOOLTIP, 200), mouseX, mouseY);
        }
        if (this.featuresButton.isHovered()) {
            context.setTooltipForNextFrame(this.font, this.font.split(ModTexts.FEATURES_TOOLTIP, 200), mouseX, mouseY);
        }
        if (this.restartButton != null && this.restartButton.isHovered()) {
            context.setTooltipForNextFrame(this.font, this.font.split(ModTexts.RESTART_REQUIRED_TOOLTIP, 200), mouseX, mouseY);
        }
    }
}