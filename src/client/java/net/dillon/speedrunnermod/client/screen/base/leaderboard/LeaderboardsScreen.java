package net.dillon.speedrunnermod.client.screen.base.leaderboard;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.option.Leaderboards;
import net.dillon.speedrunnermod.util.ModLinks;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Deprecated

public class LeaderboardsScreen extends AbstractModScreen {
    protected Button submitSpeedrunButton;

    public LeaderboardsScreen(Screen parent) {
        super(parent, Component.translatable("speedrunnermod.title.leaderboards"));
    }

    @Override
    protected void init() {
        int height = this.getButtonsHeight();

        this.submitSpeedrunButton = this.addRenderableWidget(Button.builder(Component.translatable("speedrunnermod.menu.leaderboards.submit").withStyle(getSubmitSpeedrunColor()), (button) -> {
            this.openLink(ModLinks.LEADERBOARDS_SUBMISSION, true);
        }).bounds(this.getButtonsLeftSide(), height, 150, 20).build());
        this.submitSpeedrunButton.active = options().main.leaderboardsMode.getCurrentValue() && Leaderboards.isEligibleForLeaderboardRuns();
        this.addRenderableWidget(Button.builder(ModTexts.MENU_LEADERBOARDS_VIEW, (button) -> {
            this.openLink(ModLinks.LEADERBOARDS, true);
        }).bounds(this.getButtonsRightSide(), height, 150, 20).build());

        height += 24;
        this.addRenderableWidget(Button.builder(ModTexts.MENU_LEADERBOARDS_SPREADSHEET, (button) -> {
            this.openLink(ModLinks.LEADERBOARDS_SPREADSHEET, true);
        }).bounds(this.getButtonsLeftSide(), height, 150, 20).build());

        super.init();
    }

    @Override
    protected void renderTooltips(GuiGraphicsExtractor context, int mouseX, int mouseY) {
        if (this.submitSpeedrunButton.isHovered()) {
            if (!options().main.leaderboardsMode.getCurrentValue()) {
                this.renderBasicTooltip(Component.translatable("speedrunnermod.leaderboards_mode_disabled.tooltip"), context, mouseX, mouseY);
            } else if (!Leaderboards.isEligibleForLeaderboardRuns()) {
                this.renderBasicTooltip(Component.translatable("speedrunnermod.cannot_submit_speedrun.tooltip"), context, mouseX, mouseY);
            } else {
                this.renderBasicTooltip(Component.translatable("speedrunnermod.submit_speedrun.tooltip"), context, mouseX, mouseY);
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
    private static ChatFormatting getSubmitSpeedrunColor() {
        if (options().main.leaderboardsMode.getCurrentValue()) {
            if (!Leaderboards.isEligibleForLeaderboardRuns()) {
                return ChatFormatting.RED;
            } else {
                return ChatFormatting.GREEN;
            }
        } else {
            return ChatFormatting.RED;
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