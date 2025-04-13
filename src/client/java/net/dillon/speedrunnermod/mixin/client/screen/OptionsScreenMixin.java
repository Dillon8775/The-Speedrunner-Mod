package net.dillon.speedrunnermod.mixin.client.screen;

import net.dillon.speedrunnermod.client.screen.base.MainScreen;
import net.dillon.speedrunnermod.client.util.ModIcons;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Priority set to {@code 1001} for {@code SpeedrunIGT} compatibility, as this mod also {@link Override}s the default render method.
 */
@Environment(EnvType.CLIENT)
@Mixin(value = OptionsScreen.class, priority = 1001)
public class OptionsScreenMixin extends Screen {
    @Shadow @Final
    private GameOptions settings;
    @Unique
    private ButtonWidget optionsButton, dillon8775YouTubeButton;

    public OptionsScreenMixin(Text title) {
        super(title);
    }

    /**
     * Adds additional buttons to the vanilla options screen.
     */
    @Inject(method = "init", at = @At("TAIL"))
    private void addButtons(CallbackInfo ci) {
        this.optionsButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (button) -> {
            this.client.setScreen(new MainScreen(this, this.settings));
        }).dimensions(this.width / 2 - 179, this.height / 6 + 128 - 6, 20, 20).build());
    }

    /**
     * Renders the textures for the additional buttons on the vanilla options screen.
     */
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.drawTexture(RenderLayer::getGuiTextured, ModIcons.SPEEDRUNNER_MOD_ICON, optionsButton.getX() + 1, optionsButton.getY() + 1, 0.0F, 0.0F, 18, 18, 18, 18);
    }
}