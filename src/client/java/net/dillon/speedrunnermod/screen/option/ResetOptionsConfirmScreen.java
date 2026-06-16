package net.dillon.speedrunnermod.screen.option;

import net.dillon.speedrunnermod.option.ClientModOptions;
import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.CommonColors;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.info;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.saveAllChanges;

public class ResetOptionsConfirmScreen extends AbstractModScreen {

    public ResetOptionsConfirmScreen(Screen parent) {
        super(parent, ModTexts.TITLE_OPTIONS_RESET);
    }

    @Override
    protected void init() {
        int height = this.height / 6 + 126;
        this.addRenderableWidget(Button.builder(ModTexts.RESET_CONFIRM, (buttonWidget) -> {
            ClientModOptions.resetAllOptions();
            ClientModOptions.resetAllClientOptions();
            saveAllChanges();
            info("Successfully reset all options. Restart the game to take full effect.");
            this.minecraft.gui.setScreen(new ResetOptionsScreen(this.parent));
        }).bounds(this.getButtonsLeftSide(), height, 150, 20).build());
        this.addRenderableWidget(Button.builder(ModTexts.NOT_NOW, (buttonWidget) -> {
            this.onClose();
        }).bounds(this.getButtonsRightSide(), height, 150, 20).build());
    }

    @Override
    public void onClose() {
        this.minecraft.gui.setScreen(new ModOptionsScreen(this.parent));
    }

    @Override
    public void renderCustomText(GuiGraphicsExtractor context) {
        context.centeredText(this.font, Component.translatable("speedrunnermod.reset_options_confirm"), this.width / 2, 110, CommonColors.WHITE);
    }

    @Override
    public String pageId() {
        return "gfiefsi";
    }

    @Override
    protected int columns() {
        return 2;
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return false;
    }

    @Override
    public boolean isOptionsScreen() {
        return false;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }
}