package net.dillon.speedrunnermod.mixin.client.screen;

import net.dillon.speedrunnermod.util.ClientModUtil;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.dillonlib.task.ClientTasks.drawSprite;
import static net.dillon.speedrunnermod.main.CommonMain.ofSpeedrunnerMod;

@Mixin(PauseScreen.class)
public class PauseScreenMixin extends Screen {
    @Shadow @Final
    private boolean showPauseMenu;
    @Unique
    private Button featuresButton;

    public PauseScreenMixin(Component title, Button createWorldButton) {
        super(title);
    }

    /**
     * Adds additional buttons to the game menu screen.
     */
    @Inject(method = "createPauseMenu", at = @At("TAIL"))
    private void addSpeedrunnerModButtons(CallbackInfo ci) {
        if (!this.showPauseMenu) {
            return;
        }

        Button optionsButton = this.addRenderableWidget(
                ClientModUtil.createMenuButton(this)
        );
        optionsButton.setX(this.width / 2 - 4 - 120 - 2);
        int height = this.height / 4 + 96 - 16;
        optionsButton.setY(height);
        optionsButton.setWidth(20);
        height -= 48;

        this.featuresButton = this.addRenderableWidget(
                ClientModUtil.createFeaturesButton(this, optionsButton.getX(), height)
        );
    }

    /**
     * Renders additional textures on the game menu screen.
     */
    @Inject(method = "extractRenderState", at = @At("TAIL"))
    private void renderSpeedrunnerModButtonTextures(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (!this.showPauseMenu) {
            return;
        }

        drawSprite(
                graphics,
                ofSpeedrunnerMod("hud/speedrunner_mod"),
                this.width / 2 - 4 - 58 - 2,
                this.height / 4 - 26 + 2,
                129,
                16
        );

        ClientModUtil.renderSpeedrunnerSmithingTemplate(graphics, this.featuresButton);
    }
}