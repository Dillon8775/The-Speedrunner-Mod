package net.dillon.speedrunnermod.screen.leaderboard;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.option.Leaderboards;
import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.dillon.speedrunnermod.screen.MainScreen;
import net.dillon.speedrunnermod.screen.option.RestartRequiredScreen;
import net.dillon.speedrunnermod.util.ModLinks;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.CommonColors;
import net.minecraft.util.Util;

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.saveAllChanges;

@Deprecated(forRemoval = true)
public class LeaderboardsIneligibleScreen extends AbstractModScreen {
    public static boolean needsRestart = false;
    public static boolean needsRestartFromEnablingLeaderboardsMode = false;
    protected Button leftButton, middleButton, rightButton, viewIneligibleOptionsButton, visitSubmissionPageButton;

    public LeaderboardsIneligibleScreen(Screen parent) {
        super(parent, ModTexts.TITLE_LEADERBOARDS);
    }

    @Override
    protected void init() {
        int height = this.getButtonsHeight();
        this.leftButton = this.addRenderableWidget(Button.builder(needsRestartFromEnablingLeaderboardsMode ? ModTexts.RESTART_NOW : Leaderboards.noOptionsWereChanged() ? ModTexts.FIX_AND_RESTART : ModTexts.REVERT_CHANGES, (buttonWidget) -> {
            if (needsRestartFromEnablingLeaderboardsMode) {
                this.quitWorld();
                this.minecraft.stop();
            } else if (Leaderboards.noOptionsWereChanged()) {
                this.quitWorld();
                SpeedrunnerMod.LOGGER.info("Fixing options! Re-launch to apply changes.");
                Leaderboards.fixOptions();
                saveAllChanges();
                this.minecraft.stop();
            } else {
                this.revertChanges();
            }
        }).bounds(this.getButtonsLeftSide(), this.getButtonsHeight(), 100, 20).build());
        this.middleButton = this.addRenderableWidget(Button.builder(needsRestartFromEnablingLeaderboardsMode ? ModTexts.REVERT_CHANGES : ModTexts.DISABLE_LEADERBOARDS_MODE_AND_RESTART, (buttonWidget) -> {
            if (needsRestartFromEnablingLeaderboardsMode) {
                this.revertChanges();
            } else {
                this.quitWorld();
                Leaderboards.disableLeaderboardsMode();
                this.minecraft.stop();
            }
        }).bounds(this.getButtonsMiddle(), this.getButtonsHeight(), 100, 20

        ).build());
        this.rightButton = this.addRenderableWidget(Button.builder(ModTexts.IGNORE, (buttonWidget) -> {
            Leaderboards.sendIgnoreWarning();
            if (needsRestart && !needsRestartFromEnablingLeaderboardsMode) {
                this.minecraft.gui.setScreen(new RestartRequiredScreen(this.parent));
            } else {
                this.minecraft.gui.setScreen(new MainScreen(this.parent));
            }
        }).bounds(this.getButtonsRightSide(), this.getButtonsHeight(), 100, 20).build());

        if (!needsRestartFromEnablingLeaderboardsMode) {
            height += 36;
            this.viewIneligibleOptionsButton = this.addRenderableWidget(Button.builder(ModTexts.VIEW_INELIGIBLE_OPTIONS, (buttonWidget) -> {
                LeaderboardsIneligibleOptionsScreen.fromInitialBoot = false;
                Leaderboards.checkForIneligibleOptions();
                this.minecraft.gui.setScreen(new LeaderboardsIneligibleOptionsScreen(this.parent));
            }).bounds(this.width / 2 - 100, height, 200, 20).build());

            this.visitSubmissionPageButton = this.addRenderableWidget(Button.builder(ModTexts.VISIT_SUBMISSION_PAGE, (button) -> {
                this.minecraft.gui.setScreen(new ConfirmLinkScreen(openInBrowser -> {
                    if (openInBrowser) {
                        Util.getPlatform().openUri(ModLinks.LEADERBOARDS_SUBMISSION);
                    }
                    this.minecraft.gui.setScreen(this);
                }, ModLinks.LEADERBOARDS_SUBMISSION, true));
            }).bounds(this.width / 2 - 100, this.height - 29, 200, 20).build());
        }
    }

    @Override
    public void renderCustomText(GuiGraphicsExtractor context) {
        if (needsRestartFromEnablingLeaderboardsMode) {
            context.centeredText(this.font, Component.translatable("speedrunnermod.leaderboards.restart_required.line1"), this.width / 2, 110, CommonColors.WHITE);
            context.centeredText(this.font, Component.translatable("speedrunnermod.leaderboards.restart_required.line2"), this.width / 2, 130, CommonColors.WHITE);
        } else if (Leaderboards.noOptionsWereChanged()) {
            context.centeredText(this.font, Component.translatable("speedrunnermod.leaderboards.restart.line1"), this.width / 2, 80, CommonColors.WHITE);
            context.centeredText(this.font, Component.translatable("speedrunnermod.leaderboards.restart.line2"), this.width / 2, 100, CommonColors.WHITE);
            context.centeredText(this.font, Component.translatable("speedrunnermod.leaderboards.restart.line3"), this.width / 2, 120, CommonColors.WHITE);
        } else {
            context.centeredText(this.font, Component.translatable("speedrunnermod.leaderboards.ineligible.line1"), this.width / 2, 80, CommonColors.WHITE);
            context.centeredText(this.font, Component.translatable("speedrunnermod.leaderboards.ineligible.line2"), this.width / 2, 100, CommonColors.WHITE);
            context.centeredText(this.font, Component.translatable("speedrunnermod.leaderboards.ineligible_options.line3"), this.width / 2, 120, CommonColors.WHITE);
        }
    }

    @Override
    public String pageId() {
        return "q39grkd";
    }

    @Override
    protected void renderTooltips(GuiGraphicsExtractor context, int mouseX, int mouseY) {
        if (this.leftButton.isHovered()) {
            if (needsRestartFromEnablingLeaderboardsMode) {
                this.renderBasicTooltip(Component.translatable("speedrunnermod.restart_now.tooltip"), context, mouseX, mouseY);
            } else if (Leaderboards.noOptionsWereChanged()) {
                this.renderBasicTooltip(Component.translatable("speedrunnermod.fix_and_restart.tooltip"), context, mouseX, mouseY);
            } else {
                this.renderBasicTooltip(Component.translatable("speedrunnermod.revert_changes.tooltip"), context, mouseX, mouseY);
            }
        }
        if (this.middleButton.isHovered()) {
            if (!needsRestartFromEnablingLeaderboardsMode) {
                this.renderBasicTooltip(Component.translatable("speedrunnermod.disable_leaderboards_mode_and_restart.tooltip"), context, mouseX, mouseY);
            }
        }
        if (this.rightButton.isHovered()) {
            if (!needsRestartFromEnablingLeaderboardsMode) {
                this.renderBasicTooltip(Component.translatable("speedrunnermod.ignore.tooltip"), context, mouseX, mouseY);
            } else {
                this.renderBasicTooltip(Component.translatable("speedrunnermod.ignore_restart.tooltip"), context, mouseX, mouseY);
            }
        }
        super.renderTooltips(context, mouseX, mouseY);
    }

    private void revertChanges() {
        Leaderboards.revertChanges();
        saveAllChanges();
        SpeedrunnerMod.LOGGER.info("Changes reverted.");
        this.minecraft.gui.setScreen(this.parent);
    }

    @Override
    protected int getButtonsHeight() {
        return needsRestartFromEnablingLeaderboardsMode ? this.height / 6 + 126 : this.height / 6 + 106;
    }

    @Override
    public void onClose() {
        SpeedrunnerMod.LOGGER.warn("Cannot close screen! Please choose an option.");
    }

    @Override
    protected int columns() {
        return 3;
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