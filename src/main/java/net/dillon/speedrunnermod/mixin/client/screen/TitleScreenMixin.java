package net.dillon.speedrunnermod.mixin.client.screen;

import com.mojang.blaze3d.platform.InputConstants;
import net.dillon.dillonlib.platform.info.UpdatableSpriteButton;
import net.dillon.speedrunnermod.helper.ModConstants;
import net.dillon.speedrunnermod.util.ClientModUtil;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static net.dillon.dillonlib.task.ClientTasks.openScreen;
import static net.dillon.speedrunnermod.main.ClientMain.client;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
    @Unique
    private Button featuresButton;

    public TitleScreenMixin(Component title) {
        super(title);
    }

    /**
     * Adds additional buttons to the title screen.
     */
    @Inject(method = "init", at = @At("TAIL"))
    private void addSpeedrunnerModButtons(CallbackInfo ci) {
        UpdatableSpriteButton optionsButton = this.addRenderableWidget(
                ClientModUtil.createMenuButton(this)
        );
        optionsButton.setX(this.width / 2 - 124);
        int height = this.height / 4 + 96;
        optionsButton.setY(height);
        optionsButton.setWidth(20);
        height -= 24;

        Button createNewWorldButton = this.addRenderableWidget(
                ClientModUtil.createNewWorldButton()
        );
        createNewWorldButton.setX(optionsButton.getX());
        createNewWorldButton.setY(height);
        createNewWorldButton.active = client().worldCreation().instantWorldCreation;
        height -= 24;

        this.featuresButton = this.addRenderableWidget(
                ClientModUtil.createFeaturesButton(this, optionsButton.getX(), height)
        );
    }

    /**
     * Adds additional textures to the title screen.
     */
    @Inject(method = "extractRenderState", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Ljava/lang/String;III)V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void renderSpeedrunnerModButtonTexturesAndText(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta, CallbackInfo ci, float f) {
        graphics.text(this.font, "The Speedrunner Mod " + ModConstants.MOD_VERSION.getString(), 2, this.height - 20, ARGB.color(f, 0x55FFFF));
        ClientModUtil.renderSpeedrunnerSmithingTemplate(graphics, this.featuresButton, f);
    }

    /**
     * Allows the user to refresh the title screen.
     */
    @Override
    public boolean keyPressed(KeyEvent input) {
        if (input.key() == InputConstants.KEY_R) {
            openScreen(new TitleScreen());
        }
        return super.keyPressed(input);
    }
}