package net.dillon.speedrunnermod.screen.synced;

import net.dillon.speedrunnermod.option.Mode;
import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.CommonColors;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.saveDedicatedServerChanges;

public class ModeDoesntMatchScreen extends AbstractModScreen {
    private Button matchModeToServerButton;
    private final Mode serverSideMode;

    public ModeDoesntMatchScreen(Mode serverSideMode) {
        super(null, ModTexts.TITLE_MODE_DOESNT_MATCH_SERVER_SETTING);
        this.serverSideMode = serverSideMode;
    }

    @Override
    protected void init() {
        this.matchModeToServerButton = this.addRenderableWidget(Button.builder(ModTexts.MATCH_MODE_TO_SERVER, (buttonWidget) -> {
            options().general.mode.set(this.serverSideMode);
            saveDedicatedServerChanges();
            this.minecraft.gui.setScreen(new TimedScreen(null, 5, true));
        }).bounds(this.getButtonsLeftSide(), this.getCustomButtonsHeight(), 150, 20).build());
        this.addRenderableWidget(Button.builder(Component.translatable("gui.toMenu"), (buttonWidget) -> {
            this.onClose();
        }).bounds(this.getButtonsRightSide(), this.getCustomButtonsHeight(), 150, 20).build());
    }

    @Override
    public void renderCustomText(GuiGraphicsExtractor context) {
        context.centeredText(this.font, Component.translatable("speedrunnermod.mode.doesnt_match_server.line1"), this.width / 2, 110, CommonColors.WHITE);
        context.centeredText(this.font, Component.translatable("speedrunnermod.mode.doesnt_match_server.line2"), this.width / 2, 130, CommonColors.WHITE);
    }

    @Override
    public void renderTooltips(GuiGraphicsExtractor context, int mouseX, int mouseY) {
        if (this.matchModeToServerButton.isHovered()) {
            this.renderBasicTooltip(Component.translatable("speedrunnermod.match_mode_to_server.tooltip"), context, mouseX, mouseY);
        }
    }

    @Override
    public void onClose() {
        this.minecraft.gui.setScreen(new JoinMultiplayerScreen(null));
    }

    @Override
    public boolean shouldRenderTooltips() {
        return true;
    }

    @Override
    public String pageId() {
        return "cdxgfiojkdfoj";
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