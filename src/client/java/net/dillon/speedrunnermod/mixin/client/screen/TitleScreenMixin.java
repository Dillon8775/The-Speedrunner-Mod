package net.dillon.speedrunnermod.mixin.client.screen;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.screen.MainScreen;
import net.dillon.speedrunnermod.screen.feature.FeaturesScreen;
import net.dillon.speedrunnermod.screen.option.RestartRequiredScreen;
import net.dillon.speedrunnermod.screen.synced.TimedScreen;
import net.dillon.speedrunnermod.util.ClientModUtil;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;
import net.minecraft.util.CommonColors;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
    @Unique
    private Button featuresButton, createWorldButton, optionsButton, restartButton;

    public TitleScreenMixin(Component title) {
        super(title);
    }

    /**
     * Adds additional buttons to the title screen.
     */
    @Inject(method = "init", at = @At("TAIL"))
    private void addSpeedrunnerModButtons(CallbackInfo ci) {
        this.optionsButton = this.addRenderableWidget(Button.builder(ModTexts.BLANK, (buttonWidget) -> {
            this.minecraft.gui.setScreen(new MainScreen(this));
        }).bounds(this.width / 2 - 124, this.height / 4 + 96, 20, 20).build());

        if (RestartRequiredScreen.restartRequired) {
            this.restartButton = this.addRenderableWidget(Button.builder(ModTexts.BLANK, (buttonWidget) -> {
                this.minecraft.gui.setScreen(new TimedScreen(this, 5, false));
            }).bounds(this.optionsButton.getX() - 24, this.optionsButton.getY(), 20, 20).build());
        }

        this.featuresButton = this.addRenderableWidget(Button.builder(ModTexts.BLANK, (buttonWidget) -> {
            this.minecraft.gui.setScreen(new FeaturesScreen(this));
        }).bounds(this.optionsButton.getX(), this.optionsButton.getY() - 48, 20, 20).build());

        if (clientOptions().client.showResetButton.getCurrentValue()) {
            this.createWorldButton = this.addRenderableWidget(Button.builder(ModTexts.BLANK, (buttonWidget) -> {
                CreateWorldScreen.openFresh(this.minecraft, null);
            }).bounds(this.optionsButton.getX(), this.optionsButton.getY() - 24, 20, 20).build());
            this.createWorldButton.active = clientOptions().client.instantWorldCreation.getCurrentValue();
        }
    }

    /**
     * Adds additional textures to the title screen.
     */
    @Inject(method = "extractRenderState", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Ljava/lang/String;III)V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void renderSpeedrunnerModButtonTexturesAndText(GuiGraphicsExtractor context, int mouseX, int mouseY, float delta, CallbackInfo ci, float f) {
        ClientModUtil.renderSpeedrunnerSmithingTemplate(context, this.featuresButton, f);

        if (clientOptions().client.showResetButton.getCurrentValue()) {
            context.blit(RenderPipelines.GUI_TEXTURED, ofSpeedrunnerMod("textures/item/speedrunner_boots.png"), createWorldButton.getX() + 2, createWorldButton.getY() + 2, 0.0F, 0.0F, 16, 16, 16, 16, ARGB.color(f, CommonColors.WHITE));
        }

        ClientModUtil.renderModIcon(context, this.optionsButton, f);
        if (this.restartButton != null) {
            ClientModUtil.renderSyncIcon(context, this.restartButton, f);
        }

        this.renderTooltips(context, mouseX, mouseY);
        context.text(this.font, SpeedrunnerMod.THE_SPEEDRUNNER_MOD_STRING + " " + SpeedrunnerMod.MOD_VERSION, 2, this.height - 20, ARGB.color(f, 0x55FFFF));
    }

    /**
     * Renders the tooltips on the title screen buttons.
     */
    @Unique
    private void renderTooltips(GuiGraphicsExtractor context, int mouseX, int mouseY) {
        if (this.featuresButton.isHovered()) {
            context.setTooltipForNextFrame(this.font, this.font.split(ModTexts.FEATURES_TOOLTIP, 200), mouseX, mouseY);
        }

        if (clientOptions().client.showResetButton.getCurrentValue() && this.createWorldButton.isHovered()) {
            context.setTooltipForNextFrame(this.font, this.font.split(clientOptions().client.instantWorldCreation.getCurrentValue() ? ModTexts.CREATE_WORLD_BUTTON_TOOLTIP : ModTexts.CREATE_WORLD_BUTTON_DISABLED_TOOLTIP, 200), mouseX, mouseY);
        }

        if (this.optionsButton.isHovered()) {
            context.setTooltipForNextFrame(this.font, this.font.split(ModTexts.OPTIONS_TOOLTIP, 200), mouseX, mouseY);
        }

        if (this.restartButton != null && this.restartButton.isHovered()) {
            context.setTooltipForNextFrame(this.font, this.font.split(ModTexts.RESTART_REQUIRED_TOOLTIP, 200), mouseX, mouseY);
        }
    }

    /**
     * Allows the user to refresh the title screen.
     */
    @Override
    public boolean keyPressed(KeyEvent input) {
        if (input.key() == GLFW.GLFW_KEY_R) {
            this.minecraft.gui.setScreen(new TitleScreen());
        }
        return super.keyPressed(input);
    }
}