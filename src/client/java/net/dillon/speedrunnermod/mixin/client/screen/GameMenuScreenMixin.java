package net.dillon.speedrunnermod.mixin.client.screen;

import net.dillon.speedrunnermod.client.screen.base.MainScreen;
import net.dillon.speedrunnermod.client.screen.feature.FeaturesScreen;
import net.dillon.speedrunnermod.util.ClientModUtil;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.MessageScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;

@Environment(EnvType.CLIENT)
@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin extends Screen {
    @Shadow @Final
    private boolean showMenu;
    @Unique
    private ButtonWidget featuresButton, optionsButton, createWorldButton;

    public GameMenuScreenMixin(Text title, ButtonWidget createWorldButton) {
        super(title);
    }

    /**
     * Adds additional buttons to the game menu screen (discord, options button, etc.)
     */
    @Inject(method = "initWidgets", at = @At("TAIL"))
    private void addSpeedrunnerModButtons(CallbackInfo ci) {
        if (this.showMenu) {
            this.optionsButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (buttonWidget) -> {
                this.client.setScreen(new MainScreen(this));
            }).dimensions(this.width / 2 - 4 - 120 - 2, this.height / 4 + 96 - 16, 20, 20).build());

            this.featuresButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (buttonWidget) -> {
                this.client.setScreen(new FeaturesScreen(this));
            }).dimensions(this.optionsButton.getX(), this.optionsButton.getY() - 48, 20, 20).build());

            if (clientOptions().client.showResetButton.getCurrentValue()) {
                this.createWorldButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (buttonWidget) -> {
                    if (this.client.inGameHud != null) {
                        this.client.inGameHud.getChatHud().clear(false);
                    }
                    this.client.world.disconnect(Text.translatable("menu.savingLevel"));
                    this.client.disconnect(new MessageScreen(Text.translatable("speedrunnermod.menu.generating_new_world")), false, false);
                    CreateWorldScreen.show(this.client, null);
                }).dimensions(this.optionsButton.getX(), this.optionsButton.getY() - 24, 20, 20).build());
                this.createWorldButton.active = clientOptions().client.fastWorldCreation.getCurrentValue();
            }
        }
    }

    /**
     * Renders additional textures on the game menu screen.
     */
    @Inject(method = "render", at = @At("TAIL"))
    private void renderSpeedrunnerModButtonTextures(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (this.showMenu) {
            ClientModUtil.renderSpeedrunnerSmithingTemplate(context, this.featuresButton);

            context.drawTexture(RenderPipelines.GUI_TEXTURED, Identifier.of("speedrunnermod:textures/gui/speedrunner_mod.png"), this.width / 2 - 4 - 58 - 2, this.height / 4 - 26 + 2, 0.0F, 0.0F, 129, 16, 129, 16);

            if (clientOptions().client.showResetButton.getCurrentValue()) {
                context.drawTexture(RenderPipelines.GUI_TEXTURED, Identifier.of("speedrunnermod:textures/item/speedrunner_boots.png"), createWorldButton.getX() + 2, createWorldButton.getY() + 2, 0.0F, 0.0F, 16, 16, 16, 16);
            }

            ClientModUtil.renderModIcon(context, this.optionsButton);

            this.renderTooltips(context, mouseX, mouseY);
        }
    }

    /**
     * Renders tooltips on certain buttons.
     */
    @Unique
    private void renderTooltips(DrawContext context, int mouseX, int mouseY) {
        if (clientOptions().client.showResetButton.getCurrentValue() && createWorldButton.isHovered()) {
            if (this.client.isInSingleplayer() && this.client.isIntegratedServerRunning() && !this.client.getServer().isRemote()) {
                context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(clientOptions().client.fastWorldCreation.getCurrentValue() ? ModTexts.CREATE_WORLD_BUTTON_TOOLTIP : ModTexts.CREATE_WORLD_BUTTON_DISABLED_TOOLTIP, 200), mouseX, mouseY);
            } else {
                context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(Text.literal("You must be in singleplayer to create new worlds."), 200), mouseX, mouseY);
            }
        }

        if (this.optionsButton.isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(ModTexts.OPTIONS_TOOLTIP, 200), mouseX, mouseY);
        }
        if (this.featuresButton.isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(ModTexts.FEATURES_TOOLTIP, 200), mouseX, mouseY);
        }
    }
}