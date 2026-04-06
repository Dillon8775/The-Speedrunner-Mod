package net.dillon.speedrunnermod.client.screen.base;

import net.dillon.speedrunnermod.main.SpeedrunnerModClient;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.CommonColors;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.info;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.warn;

public class SafeBootScreen extends AbstractModScreen {
    private Button proceedAnywayButton;

    public SafeBootScreen(Screen parent) {
        super(parent, ModTexts.TITLE_SAFE_BOOT);
    }

    @Override
    protected void init() {
        this.addRenderableWidget(Button.builder(ModTexts.FIX_AND_RESTART, (buttonWidget) -> {
            SpeedrunnerModClient.fixAllBrokenOptions();
            info("Fixing options! Re-launch to apply changes.");
            this.minecraft.stop();
        }).bounds(this.getButtonsLeftSide(), this.getCustomButtonsHeight(), 100, 20).build());
        this.addRenderableWidget(Button.builder(ModTexts.CLOSE_GAME, (buttonWidget) -> {
            info("Closing game! No changes were made.");
            this.minecraft.stop();
        }).bounds(this.getButtonsMiddle(), this.getCustomButtonsHeight(), 100, 20).build());
        this.proceedAnywayButton = this.addRenderableWidget(Button.builder(ModTexts.PROCEED_ANYWAY, (buttonWidget) -> {
            warn("Proceeding. Due to corrupt options, you may experience issues. Re-launch the game to fix options.");
            this.minecraft.setScreen(new TitleScreen(false));
        }).bounds(this.getButtonsRightSide(), this.getCustomButtonsHeight(), 100, 20).build());
    }

    @Override
    public void onClose() {
        warn("Cannot close screen! Please select an option.");
    }

    @Override
    public void renderCustomText(GuiGraphicsExtractor context) {
        context.centeredText(this.font, Component.translatable("speedrunnermod.options.error.line1"), this.width / 2, 100, CommonColors.WHITE);
        context.centeredText(this.font, Component.translatable("speedrunnermod.options.error.line2"), this.width / 2, 120, CommonColors.WHITE);
    }

    @Override
    protected void renderTooltips(GuiGraphicsExtractor context, int mouseX, int mouseY) {
        if (this.proceedAnywayButton.isHovered()) {
            context.setTooltipForNextFrame(this.font, this.font.split(Component.translatable("speedrunnermod.proceed_anyway.tooltip"), 200), mouseX, mouseY);
        }
    }

    @Override
    public boolean shouldRenderTooltips() {
        return true;
    }

    @Override
    public String pageId() {
        return "bfpogr09wi0w";
    }

    @Override
    protected int columns() {
        return 3;
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return true;
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