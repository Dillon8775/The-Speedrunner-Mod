package net.dillon.speedrunnermod.mixin.client.screen;

import net.dillon.speedrunnermod.helper.ModHelper;
import net.dillon.speedrunnermod.util.ClientModUtil;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.CommonColors;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static net.dillon.dillonlib.task.ClientTasks.isOnServer;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.client;

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
        if (client().worldCreation().instantWorldCreation &&
                client().general().showResetButton &&
                !isOnServer()) {
            this.exitButtons.add(this.addRenderableWidget(Button.builder(Component.translatable("speedrunnermod.new_run"), button ->  ClientModUtil.createNewWorld(this.minecraft))
                    .bounds(this.width / 2 - 100, this.height / 4 + 120, 200, 20)
                    .build()));
        }
    }

    /**
     * Displays the players death coordinates on the death screen.
     */
    @Inject(method = "extractRenderState", at = @At("TAIL"))
    private void displayDeathCords(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (!common().general().showDeathCords) {
            return;
        }

        graphics.centeredText(
                this.font,
                ModHelper.deathCords(
                        ModHelper.latestDeathCords[0],
                        ModHelper.latestDeathCords[1],
                        ModHelper.latestDeathCords[2]
                ),
                this.width / 2,
                115,
                CommonColors.WHITE
        );
    }
}