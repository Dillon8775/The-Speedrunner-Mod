package net.dillon.speedrunnermod.client.screen.base.leaderboard;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.client.screen.base.MainScreen;
import net.dillon.speedrunnermod.client.screen.base.option.RestartRequiredScreen;
import net.dillon.speedrunnermod.option.Leaderboards;
import net.dillon.speedrunnermod.util.ModLinks;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ConfirmLinkScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Util;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.info;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.warn;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.saveAllChanges;

@Deprecated
@Environment(EnvType.CLIENT)
public class LeaderboardsIneligibleScreen extends AbstractModScreen {
    public static boolean needsRestart = false;
    public static boolean needsRestartFromEnablingLeaderboardsMode = false;
    protected ButtonWidget leftButton, middleButton, rightButton, viewIneligibleOptionsButton, visitSubmissionPageButton;

    public LeaderboardsIneligibleScreen(Screen parent) {
        super(parent, ModTexts.TITLE_LEADERBOARDS);
    }

    @Override
    protected void init() {
        int height = this.getButtonsHeight();
        this.leftButton = this.addDrawableChild(ButtonWidget.builder(needsRestartFromEnablingLeaderboardsMode ? ModTexts.RESTART_NOW : Leaderboards.noOptionsWereChanged() ? ModTexts.FIX_AND_RESTART : ModTexts.REVERT_CHANGES, (buttonWidget) -> {
            if (needsRestartFromEnablingLeaderboardsMode) {
                this.quitWorld();
                this.client.scheduleStop();
            } else if (Leaderboards.noOptionsWereChanged()) {
                this.quitWorld();
                info("Fixing options! Re-launch to apply changes.");
                Leaderboards.fixOptions();
                saveAllChanges();
                this.client.scheduleStop();
            } else {
                this.revertChanges();
            }
        }).dimensions(this.getButtonsLeftSide(), this.getButtonsHeight(), 100, 20).build());
        this.middleButton = this.addDrawableChild(ButtonWidget.builder(needsRestartFromEnablingLeaderboardsMode ? ModTexts.REVERT_CHANGES : ModTexts.DISABLE_LEADERBOARDS_MODE_AND_RESTART, (buttonWidget) -> {
            if (needsRestartFromEnablingLeaderboardsMode) {
                this.revertChanges();
            } else {
                this.quitWorld();
                Leaderboards.disableLeaderboardsMode();
                this.client.scheduleStop();
            }
        }).dimensions(this.getButtonsMiddle(), this.getButtonsHeight(), 100, 20

        ).build());
        this.rightButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.IGNORE, (buttonWidget) -> {
            Leaderboards.sendIgnoreWarning();
            if (needsRestart && !needsRestartFromEnablingLeaderboardsMode) {
                this.client.setScreen(new RestartRequiredScreen(this.parent));
            } else {
                this.client.setScreen(new MainScreen(this.parent));
            }
        }).dimensions(this.getButtonsRightSide(), this.getButtonsHeight(), 100, 20).build());

        if (!needsRestartFromEnablingLeaderboardsMode) {
            height += 36;
            this.viewIneligibleOptionsButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.VIEW_INELIGIBLE_OPTIONS, (buttonWidget) -> {
                LeaderboardsIneligibleOptionsScreen.fromInitialBoot = false;
                Leaderboards.checkForIneligibleOptions();
                this.client.setScreen(new LeaderboardsIneligibleOptionsScreen(this.parent));
            }).dimensions(this.width / 2 - 100, height, 200, 20).build());

            this.visitSubmissionPageButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.VISIT_SUBMISSION_PAGE, (button) -> {
                this.client.setScreen(new ConfirmLinkScreen(openInBrowser -> {
                    if (openInBrowser) {
                        Util.getOperatingSystem().open(ModLinks.LEADERBOARDS_SUBMISSION);
                    }
                    this.client.setScreen(this);
                }, ModLinks.LEADERBOARDS_SUBMISSION, true));
            }).dimensions(this.width / 2 - 100, this.height - 29, 200, 20).build());
        }
    }

    @Override
    public void renderCustomText(DrawContext context) {
        if (needsRestartFromEnablingLeaderboardsMode) {
            context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("speedrunnermod.leaderboards.restart_required.line1"), this.width / 2, 110, Colors.WHITE);
            context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("speedrunnermod.leaderboards.restart_required.line2"), this.width / 2, 130, Colors.WHITE);
        } else if (Leaderboards.noOptionsWereChanged()) {
            context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("speedrunnermod.leaderboards.restart.line1"), this.width / 2, 80, Colors.WHITE);
            context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("speedrunnermod.leaderboards.restart.line2"), this.width / 2, 100, Colors.WHITE);
            context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("speedrunnermod.leaderboards.restart.line3"), this.width / 2, 120, Colors.WHITE);
        } else {
            context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("speedrunnermod.leaderboards.ineligible.line1"), this.width / 2, 80, Colors.WHITE);
            context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("speedrunnermod.leaderboards.ineligible.line2"), this.width / 2, 100, Colors.WHITE);
            context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("speedrunnermod.leaderboards.ineligible_options.line3"), this.width / 2, 120, Colors.WHITE);
        }
    }

    @Override
    public String pageId() {
        return "q39grkd";
    }

    @Override
    protected void renderTooltips(DrawContext context, int mouseX, int mouseY) {
        if (this.leftButton.isHovered()) {
            if (needsRestartFromEnablingLeaderboardsMode) {
                this.renderBasicTooltip(Text.translatable("speedrunnermod.restart_now.tooltip"), context, mouseX, mouseY);
            } else if (Leaderboards.noOptionsWereChanged()) {
                this.renderBasicTooltip(Text.translatable("speedrunnermod.fix_and_restart.tooltip"), context, mouseX, mouseY);
            } else {
                this.renderBasicTooltip(Text.translatable("speedrunnermod.revert_changes.tooltip"), context, mouseX, mouseY);
            }
        }
        if (this.middleButton.isHovered()) {
            if (!needsRestartFromEnablingLeaderboardsMode) {
                this.renderBasicTooltip(Text.translatable("speedrunnermod.disable_leaderboards_mode_and_restart.tooltip"), context, mouseX, mouseY);
            }
        }
        if (this.rightButton.isHovered()) {
            if (!needsRestartFromEnablingLeaderboardsMode) {
                this.renderBasicTooltip(Text.translatable("speedrunnermod.ignore.tooltip"), context, mouseX, mouseY);
            } else {
                this.renderBasicTooltip(Text.translatable("speedrunnermod.ignore_restart.tooltip"), context, mouseX, mouseY);
            }
        }
        super.renderTooltips(context, mouseX, mouseY);
    }

    private void revertChanges() {
        Leaderboards.revertChanges();
        saveAllChanges();
        info("Changes reverted.");
        this.client.setScreen(this.parent);
    }

    @Override
    protected int getButtonsHeight() {
        return needsRestartFromEnablingLeaderboardsMode ? this.height / 6 + 126 : this.height / 6 + 106;
    }

    @Override
    public void close() {
        warn("Cannot close screen! Please choose an option.");
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