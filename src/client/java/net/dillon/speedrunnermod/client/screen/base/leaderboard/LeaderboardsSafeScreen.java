package net.dillon.speedrunnermod.client.screen.base.leaderboard;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.option.Leaderboards;
import net.dillon.speedrunnermod.util.ModLinks;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ConfirmLinkScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Util;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.info;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.warn;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.saveAllChanges;

@Deprecated
@Environment(EnvType.CLIENT)
public class LeaderboardsSafeScreen extends AbstractModScreen {
    protected ButtonWidget leftButton, middleButton, rightButton, viewIneligibleOptionsButton, viewSubmissionPageButton;

    public LeaderboardsSafeScreen(Screen parent) {
        super(parent, ModTexts.TITLE_LEADERBOARDS);
    }

    @Override
    protected void init() {
        int height = this.height / 6 + 102;
        this.leftButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.FIX_AND_RESTART, (buttonWidget) -> {
            info("Fixing options! Re-launch to apply changes.");
            Leaderboards.fixOptions();
            saveAllChanges();
            this.client.scheduleStop();
        }).dimensions(this.getButtonsLeftSide(), height, 100, 20).build());
        this.middleButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.DISABLE_LEADERBOARDS_MODE_AND_RESTART, (buttonWidget) -> {
            Leaderboards.disableLeaderboardsMode();
            this.client.scheduleStop();
        }).dimensions(this.getButtonsMiddle(), height, 100, 20).build());
        this.rightButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.IGNORE, (buttonWidget) -> {
            Leaderboards.sendIgnoreWarning();
            this.client.setScreen(new TitleScreen(false));
        }).dimensions(this.getButtonsRightSide(), height, 100, 20).build());

        height += 36;
        this.viewIneligibleOptionsButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.VIEW_INELIGIBLE_OPTIONS, (buttonWidget) -> {
            Leaderboards.checkForIneligibleOptions();
            this.client.setScreen(new LeaderboardsIneligibleOptionsScreen(null));
        }).dimensions(this.width / 2 - 100, height, 200, 20).build());

        this.viewSubmissionPageButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.VISIT_SUBMISSION_PAGE, (button) -> {
            this.client.setScreen(new ConfirmLinkScreen(openInBrowser -> {
                if (openInBrowser) {
                    Util.getOperatingSystem().open(ModLinks.LEADERBOARDS_SUBMISSION);
                }
                this.client.setScreen(this);
            }, ModLinks.LEADERBOARDS_SUBMISSION, true));
        }).dimensions(this.width / 2 - 100, this.height - 29, 200, 20).build());
    }

    @Override
    public void close() {
        warn("Cannot close screen! Please select an option.");
    }

    @Override
    public void renderCustomText(DrawContext context) {
        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("speedrunnermod.leaderboards.ineligible_options.line1"), this.width / 2, 80, Colors.WHITE);
        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("speedrunnermod.leaderboards.ineligible_options.line2"), this.width / 2, 100, Colors.WHITE);
        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("speedrunnermod.leaderboards.ineligible_options.line3"), this.width / 2, 120, Colors.WHITE);
    }

    @Override
    public String pageId() {
        return "309grio";
    }

    @Override
    protected void renderTooltips(DrawContext context, int mouseX, int mouseY) {
        if (this.leftButton.isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.fix_and_restart.tooltip"), context, mouseX, mouseY);
        }
        if (this.middleButton.isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.disable_leaderboards_mode_and_restart.tooltip"), context, mouseX, mouseY);
        }
        if (this.rightButton.isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.ignore.tooltip"), context, mouseX, mouseY);
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