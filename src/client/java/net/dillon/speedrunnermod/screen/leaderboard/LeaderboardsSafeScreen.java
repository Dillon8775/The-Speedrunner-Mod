package net.dillon.speedrunnermod.screen.leaderboard;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.option.Leaderboards;
import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.dillon.speedrunnermod.util.ModLinks;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.CommonColors;
import net.minecraft.util.Util;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.info;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.warn;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.saveAllChanges;

@Deprecated

public class LeaderboardsSafeScreen extends AbstractModScreen {
    protected Button leftButton, middleButton, rightButton, viewIneligibleOptionsButton, viewSubmissionPageButton;

    public LeaderboardsSafeScreen(Screen parent) {
        super(parent, ModTexts.TITLE_LEADERBOARDS);
    }

    @Override
    protected void init() {
        int height = this.height / 6 + 102;
        this.leftButton = this.addRenderableWidget(Button.builder(ModTexts.FIX_AND_RESTART, (buttonWidget) -> {
            info("Fixing options! Re-launch to apply changes.");
            Leaderboards.fixOptions();
            saveAllChanges();
            this.minecraft.stop();
        }).bounds(this.getButtonsLeftSide(), height, 100, 20).build());
        this.middleButton = this.addRenderableWidget(Button.builder(ModTexts.DISABLE_LEADERBOARDS_MODE_AND_RESTART, (buttonWidget) -> {
            Leaderboards.disableLeaderboardsMode();
            this.minecraft.stop();
        }).bounds(this.getButtonsMiddle(), height, 100, 20).build());
        this.rightButton = this.addRenderableWidget(Button.builder(ModTexts.IGNORE, (buttonWidget) -> {
            Leaderboards.sendIgnoreWarning();
            this.minecraft.gui.setScreen(new TitleScreen(false));
        }).bounds(this.getButtonsRightSide(), height, 100, 20).build());

        height += 36;
        this.viewIneligibleOptionsButton = this.addRenderableWidget(Button.builder(ModTexts.VIEW_INELIGIBLE_OPTIONS, (buttonWidget) -> {
            Leaderboards.checkForIneligibleOptions();
            this.minecraft.gui.setScreen(new LeaderboardsIneligibleOptionsScreen(null));
        }).bounds(this.width / 2 - 100, height, 200, 20).build());

        this.viewSubmissionPageButton = this.addRenderableWidget(Button.builder(ModTexts.VISIT_SUBMISSION_PAGE, (button) -> {
            this.minecraft.gui.setScreen(new ConfirmLinkScreen(openInBrowser -> {
                if (openInBrowser) {
                    Util.getPlatform().openUri(ModLinks.LEADERBOARDS_SUBMISSION);
                }
                this.minecraft.gui.setScreen(this);
            }, ModLinks.LEADERBOARDS_SUBMISSION, true));
        }).bounds(this.width / 2 - 100, this.height - 29, 200, 20).build());
    }

    @Override
    public void onClose() {
        warn("Cannot close screen! Please select an option.");
    }

    @Override
    public void renderCustomText(GuiGraphicsExtractor context) {
        context.centeredText(this.font, Component.translatable("speedrunnermod.leaderboards.ineligible_options.line1"), this.width / 2, 80, CommonColors.WHITE);
        context.centeredText(this.font, Component.translatable("speedrunnermod.leaderboards.ineligible_options.line2"), this.width / 2, 100, CommonColors.WHITE);
        context.centeredText(this.font, Component.translatable("speedrunnermod.leaderboards.ineligible_options.line3"), this.width / 2, 120, CommonColors.WHITE);
    }

    @Override
    public String pageId() {
        return "309grio";
    }

    @Override
    protected void renderTooltips(GuiGraphicsExtractor context, int mouseX, int mouseY) {
        if (this.leftButton.isHovered()) {
            this.renderBasicTooltip(Component.translatable("speedrunnermod.fix_and_restart.tooltip"), context, mouseX, mouseY);
        }
        if (this.middleButton.isHovered()) {
            this.renderBasicTooltip(Component.translatable("speedrunnermod.disable_leaderboards_mode_and_restart.tooltip"), context, mouseX, mouseY);
        }
        if (this.rightButton.isHovered()) {
            this.renderBasicTooltip(Component.translatable("speedrunnermod.ignore.tooltip"), context, mouseX, mouseY);
        }
        super.renderTooltips(context, mouseX, mouseY);
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