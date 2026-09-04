package net.dillon.speedrunnermod.mixin.client.screen;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.sugar.Local;
import net.dillon.dillonlib.util.Texts;
import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.util.ClientModUtil;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.server.IntegratedServer;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.dillonlib.task.ClientTasks.drawSprite;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.client;

@Mixin(PauseScreen.class)
public class PauseScreenMixin extends Screen {
    @Shadow @Final
    private boolean showPauseMenu;
    @Unique
    private Button featuresButton, createWorldButton;

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
        if (!this.showPauseMenu || !client().general().showResetButton) {
            return;
        }

        this.createWorldButton = Button.builder(Texts.BLANK, (buttonWidget) -> ClientModUtil.createNewWorld(this.minecraft))
                .width(20)
                .tooltip(
                        Tooltip.create(
                                client().worldCreation().instantWorldCreation ? ModTexts.CREATE_WORLD_BUTTON_TOOLTIP : ModTexts.CREATE_WORLD_BUTTON_DISABLED_TOOLTIP
                        )
                )
                .build();
        this.createWorldButton.active = client().worldCreation().instantWorldCreation;
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

        Button optionsButton = this.addRenderableWidget(
                ClientModUtil.createMenuButton(this)
        );
        optionsButton.setX(this.width / 2 - 4 - 120 - 2);
        optionsButton.setY(this.height / 4 + 96 - 16);
        optionsButton.setWidth(20);

        this.featuresButton = this.addRenderableWidget(ClientModUtil.createFeaturesButton(this, optionsButton.getX(), optionsButton.getY() - 48));
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

        if (client().general().showResetButton) {
            ClientModUtil.renderSpeedrunnerBoots(graphics, this.createWorldButton);
        }
        ClientModUtil.renderSpeedrunnerSmithingTemplate(graphics, this.featuresButton);
    }
}