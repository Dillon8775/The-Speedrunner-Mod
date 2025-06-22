package net.dillon.speedrunnermod.mixin.client.screen;

import net.dillon.speedrunnermod.client.screen.base.MainScreen;
import net.dillon.speedrunnermod.client.screen.feature.FeaturesScreen;
import net.dillon.speedrunnermod.client.util.ModIcons;
import net.dillon.speedrunnermod.client.util.ModLinks;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ConfirmLinkScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;

@Environment(EnvType.CLIENT)
@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
    @Shadow @Final
    private boolean doBackgroundFade;
    @Shadow
    private long backgroundFadeStart;
    @Shadow
    private float backgroundAlpha;
    @Unique
    private ButtonWidget featuresButton, createWorldButton, optionsButton, wikiButton;

    public TitleScreenMixin(Text title) {
        super(title);
    }

    /**
     * Adds additional buttons to the title screen.
     */
    @Inject(method = "init", at = @At("TAIL"))
    private void addButtons(CallbackInfo ci) {
        this.featuresButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (buttonWidget) -> {
            this.client.setScreen(new FeaturesScreen(this));
        }).dimensions(this.width / 2 - 124, this.height / 4 + 48, 20, 20).build());

        if (clientOptions().client.showResetButton) {
            this.createWorldButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (buttonWidget) -> {
                CreateWorldScreen.show(this.client, this);
            }).dimensions(this.width / 2 - 124, this.height / 4 + 72, 20, 20).build());
            this.createWorldButton.active = clientOptions().client.fastWorldCreation;
        }

        this.optionsButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (buttonWidget) -> {
            this.client.setScreen(new MainScreen(this));
        }).dimensions(this.width / 2 - 124, this.height / 4 + 96, 20, 20).build());

        this.wikiButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (buttonWidget) -> {
            this.client.setScreen(new ConfirmLinkScreen(openInBrowser -> {
                if (openInBrowser) {
                    Util.getOperatingSystem().open(ModLinks.WIKI);
                }
                this.client.setScreen(this);
            }, ModLinks.WIKI, true));
        }).dimensions(this.width / 2 + 104, this.height / 4 + 96, 20, 20).build());
    }

    /**
     * Adds additional textures to the title screen.
     */
    @Inject(method = "render", at = @At("TAIL"))
    private void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        context.drawTexture(RenderLayer::getGuiTextured, ofSpeedrunnerMod("textures/item/golden_speedrunner_upgrade_smithing_template.png"), featuresButton.getX() + 2, this.featuresButton.getY() + 2, 0.0F, 0.0F, 16, 16, 16, 16);

        if (clientOptions().client.showResetButton) {
            context.drawTexture(RenderLayer::getGuiTextured, ofSpeedrunnerMod("textures/item/speedrunner_boots.png"), createWorldButton.getX() + 2, createWorldButton.getY() + 2, 0.0F, 0.0F, 16, 16, 16, 16);
        }

        context.drawTexture(RenderLayer::getGuiTextured, ModIcons.SPEEDRUNNER_MOD_ICON, optionsButton.getX() + 1, optionsButton.getY() + 1, 0.0F, 0.0F, 18, 18, 18, 18);

        context.drawTexture(RenderLayer::getGuiTextured, ModIcons.WIKI_ICON, wikiButton.getX() + 2, wikiButton.getY() + 2, 0.0F, 0.0F, 16, 16, 16, 16);

        this.renderTooltips(context, mouseX, mouseY);

        float f = this.doBackgroundFade ? (float)(Util.getMeasuringTimeMs() - this.backgroundFadeStart) / 1000.0F : 1.0F;
        float g = this.doBackgroundFade ? MathHelper.clamp(f - 1.0F, 0.0F, 1.0F) : 1.0F;
        int l = MathHelper.ceil(g * 255.0F) << 24;
        context.drawTextWithShadow(this.textRenderer, SpeedrunnerMod.THE_SPEEDRUNNER_MOD_STRING + " " + SpeedrunnerMod.MOD_VERSION, 2, this.height - 20, 16777215 | l);
    }

    /**
     * Renders the tooltips on the title screen buttons.
     */
    @Unique
    private void renderTooltips(DrawContext context, int mouseX, int mouseY) {
        if (this.featuresButton.isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(ModTexts.FEATURES_TOOLTIP, 200), mouseX, mouseY);
        }

        if (clientOptions().client.showResetButton && this.createWorldButton.isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(clientOptions().client.fastWorldCreation ? ModTexts.CREATE_WORLD_BUTTON_TOOLTIP : ModTexts.CREATE_WORLD_BUTTON_DISABLED_TOOLTIP, 200), mouseX, mouseY);
        }

        if (this.optionsButton.isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(ModTexts.OPTIONS_TOOLTIP, 200), mouseX, mouseY);
        }

        if (this.wikiButton.isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(ModTexts.WIKI_TOOLTIP, 200), mouseX, mouseY);
        }
    }
}