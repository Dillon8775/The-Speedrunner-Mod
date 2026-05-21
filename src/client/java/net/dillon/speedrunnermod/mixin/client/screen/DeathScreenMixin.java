package net.dillon.speedrunnermod.mixin.client.screen;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.client.gui.screens.GenericMessageScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.CommonColors;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;

@Mixin(DeathScreen.class)
public class DeathScreenMixin extends Screen {
    @Shadow @Final
    private List<Button> exitButtons;

    private DeathScreenMixin(Component title) {
        super(title);
    }

    /**
     * Adds a {@code reset button} to the death screen.
     */
    @Inject(method = "init", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"))
    private void addResetButton(CallbackInfo ci) {
        if (clientOptions().client.fastWorldCreation.getCurrentValue() &&
                clientOptions().client.showResetButton.getCurrentValue() &&
                this.minecraft.isLocalServer() && this.minecraft.getCurrentServer() == null) {
            this.exitButtons.add(this.addRenderableWidget(Button.builder(Component.translatable("speedrunnermod.new_run"), button -> {
                if (this.minecraft.gui != null) {
                    this.minecraft.gui.getChat().clearMessages(false);
                }
                this.minecraft.level.disconnect(Component.translatable("menu.savingLevel"));
                this.minecraft.disconnect(new GenericMessageScreen(Component.translatable("speedrunnermod.menu.generating_new_world")), false, false);
                CreateWorldScreen.openFresh(this.minecraft, null);
            }).bounds(this.width / 2 - 100, this.height / 4 + 120, 200, 20).build()));
        }
    }

    /**
     * Displays the players death coordinates on the death screen.
     */
    @Inject(method = "extractRenderState", at = @At("TAIL"))
    private void displayDeathCords(GuiGraphicsExtractor context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (options().main.showDeathCords.getCurrentValue()) {
            context.centeredText(this.font, ModUtil.deathCords(ModUtil.latestDeathCords[0], ModUtil.latestDeathCords[1], ModUtil.latestDeathCords[2]), this.width / 2, 115, CommonColors.WHITE);
        }
    }
}