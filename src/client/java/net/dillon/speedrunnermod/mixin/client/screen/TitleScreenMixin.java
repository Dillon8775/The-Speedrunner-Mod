package net.dillon.speedrunnermod.mixin.client.screen;

import net.dillon.speedrunnermod.client.screen.base.MainScreen;
import net.dillon.speedrunnermod.client.screen.feature.FeaturesScreen;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.util.ClientModUtil;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.input.KeyInput;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.ColorHelper;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;

@Environment(EnvType.CLIENT)
@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
    @Shadow @Final
    private boolean doBackgroundFade;
    @Shadow
    private long backgroundFadeStart;
    @Unique
    private ButtonWidget featuresButton, createWorldButton, optionsButton;

    public TitleScreenMixin(Text title) {
        super(title);
    }

    /**
     * Adds additional buttons to the title screen.
     */
    @Inject(method = "init", at = @At("TAIL"))
    private void addSpeedrunnerModButtons(CallbackInfo ci) {
        this.optionsButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (buttonWidget) -> {
            this.client.setScreen(new MainScreen(this));
        }).dimensions(this.width / 2 - 124, this.height / 4 + 96, 20, 20).build());

        this.featuresButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (buttonWidget) -> {
            this.client.setScreen(new FeaturesScreen(this));
        }).dimensions(this.optionsButton.getX(), this.optionsButton.getY() - 48, 20, 20).build());

        if (clientOptions().client.showResetButton.getCurrentValue()) {
            this.createWorldButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (buttonWidget) -> {
                CreateWorldScreen.show(this.client, null);
            }).dimensions(this.optionsButton.getX(), this.optionsButton.getY() - 24, 20, 20).build());
            this.createWorldButton.active = clientOptions().client.fastWorldCreation.getCurrentValue();
        }
    }

    /**
     * Adds additional textures to the title screen.
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;III)V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void renderSpeedrunnerModButtonTexturesAndText(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci, float f) {
        ClientModUtil.renderSpeedrunnerSmithingTemplate(context, this.featuresButton, f);

        if (clientOptions().client.showResetButton.getCurrentValue()) {
            context.drawTexture(RenderPipelines.GUI_TEXTURED, ofSpeedrunnerMod("textures/item/speedrunner_boots.png"), createWorldButton.getX() + 2, createWorldButton.getY() + 2, 0.0F, 0.0F, 16, 16, 16, 16, ColorHelper.withAlpha(f, Colors.WHITE));
        }

        ClientModUtil.renderModIcon(context, this.optionsButton, f);

        this.renderTooltips(context, mouseX, mouseY);
        context.drawTextWithShadow(this.textRenderer, SpeedrunnerMod.THE_SPEEDRUNNER_MOD_STRING + " " + SpeedrunnerMod.MOD_VERSION, 2, this.height - 20, ColorHelper.withAlpha(f, Formatting.AQUA.getColorValue()));
    }

    /**
     * Renders the tooltips on the title screen buttons.
     */
    @Unique
    private void renderTooltips(DrawContext context, int mouseX, int mouseY) {
        if (this.featuresButton.isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(ModTexts.FEATURES_TOOLTIP, 200), mouseX, mouseY);
        }

        if (clientOptions().client.showResetButton.getCurrentValue() && this.createWorldButton.isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(clientOptions().client.fastWorldCreation.getCurrentValue() ? ModTexts.CREATE_WORLD_BUTTON_TOOLTIP : ModTexts.CREATE_WORLD_BUTTON_DISABLED_TOOLTIP, 200), mouseX, mouseY);
        }

        if (this.optionsButton.isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(ModTexts.OPTIONS_TOOLTIP, 200), mouseX, mouseY);
        }
    }

    /**
     * Allows the user to refresh the title screen.
     */
    @Override
    public boolean keyPressed(KeyInput input) {
        if (input.key() == GLFW.GLFW_KEY_R) {
            this.client.setScreen(new TitleScreen());
        }
        return super.keyPressed(input);
    }
}