package net.dillon.speedrunnermod.client.screen.base.leaderboard;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.option.Leaderboards;
import net.dillon.speedrunnermod.util.ModLinks;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Deprecated

public class LeaderboardsScreen extends AbstractModScreen {
    protected ButtonWidget submitSpeedrunButton;

    public LeaderboardsScreen(Screen parent) {
        super(parent, Text.translatable("speedrunnermod.title.leaderboards"));
    }

    @Override
    protected void init() {
        int height = this.getButtonsHeight();

        this.submitSpeedrunButton = this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.leaderboards.submit").formatted(getSubmitSpeedrunColor()), (button) -> {
            this.openLink(ModLinks.LEADERBOARDS_SUBMISSION, true);
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());
        this.submitSpeedrunButton.active = options().main.leaderboardsMode.getCurrentValue() && Leaderboards.isEligibleForLeaderboardRuns();
        this.addDrawableChild(ButtonWidget.builder(ModTexts.MENU_LEADERBOARDS_VIEW, (button) -> {
            this.openLink(ModLinks.LEADERBOARDS, true);
        }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());

        height += 24;
        this.addDrawableChild(ButtonWidget.builder(ModTexts.MENU_LEADERBOARDS_SPREADSHEET, (button) -> {
            this.openLink(ModLinks.LEADERBOARDS_SPREADSHEET, true);
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());

        super.init();
    }

    @Override
    protected void renderTooltips(DrawContext context, int mouseX, int mouseY) {
        if (this.submitSpeedrunButton.isHovered()) {
            if (!options().main.leaderboardsMode.getCurrentValue()) {
                this.renderBasicTooltip(Text.translatable("speedrunnermod.leaderboards_mode_disabled.tooltip"), context, mouseX, mouseY);
            } else if (!Leaderboards.isEligibleForLeaderboardRuns()) {
                this.renderBasicTooltip(Text.translatable("speedrunnermod.cannot_submit_speedrun.tooltip"), context, mouseX, mouseY);
            } else {
                this.renderBasicTooltip(Text.translatable("speedrunnermod.submit_speedrun.tooltip"), context, mouseX, mouseY);
            }
        }
        super.renderTooltips(context, mouseX, mouseY);
    }

    @Override
    public String pageId() {
        return "gripefs";
    }

    /**
     * Gets submit a speedrun button text color.
     */
    private static Formatting getSubmitSpeedrunColor() {
        if (options().main.leaderboardsMode.getCurrentValue()) {
            if (!Leaderboards.isEligibleForLeaderboardRuns()) {
                return Formatting.RED;
            } else {
                return Formatting.GREEN;
            }
        } else {
            return Formatting.RED;
        }
    }

    @Override
    protected int columns() {
        return 2;
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