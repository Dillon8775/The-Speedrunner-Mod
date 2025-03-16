package net.dillon.speedrunnermod.client.screen;

import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.io.File;

@Environment(EnvType.CLIENT)
public class TransferOptionsToPlayerScreen extends AbstractModScreen {

    public TransferOptionsToPlayerScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_TRANSFER_OPTIONS_TO_PLAYER);
    }

    @Override
    protected void init() {
        TextFieldWidget playerName = this.addDrawableChild(new TextFieldWidget(this.textRenderer, this.getButtonsMiddle(), this.height / 2 - 12, 100, 20, Text.literal("Enter player name")));
        ButtonWidget findAndSend = this.addDrawableChild(ButtonWidget.builder(ModTexts.FIND_AND_SEND, (button) -> {
            for (ServerPlayerEntity receiver : this.client.getServer().getPlayerManager().getPlayerList()) {
                if (receiver.getGameProfile().getName().equals(playerName.getText())) {
                    File senderConfigFile = ModOptions.getConfigFile();
                } else {
                }
            }
        }).dimensions(this.getButtonsMiddle(), this.height / 2 + 24, 100, 20).build());

        super.init();
    }

    @Override
    protected int columns() {
        return 1;
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return false;
    }

    @Override
    protected boolean isOptionsScreen() {
        return false;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }
}