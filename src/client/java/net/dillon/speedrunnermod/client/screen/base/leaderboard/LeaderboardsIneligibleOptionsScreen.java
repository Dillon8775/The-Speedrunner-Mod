package net.dillon.speedrunnermod.client.screen.base.leaderboard;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.option.Leaderboards;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.util.CommonColors;

@Deprecated

public class LeaderboardsIneligibleOptionsScreen extends AbstractModScreen {
    private final Screen parent;
    public static boolean fromInitialBoot = true;

    public LeaderboardsIneligibleOptionsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_INELIGIBLE_OPTIONS);
        this.parent = parent;
    }

    @Override
    public void onClose() {
        if (fromInitialBoot) {
            this.minecraft.setScreen(new LeaderboardsSafeScreen(this.parent));
        } else {
            this.minecraft.setScreen(new LeaderboardsIneligibleScreen(this.parent));
        }
    }

    @Override
    public void renderCustomText(GuiGraphics context) {
        boolean longList = Leaderboards.ineligibleOptions.size() > 12;
        int textHeight = longList ? 35 : 50;
        for (int i = 0; i < Leaderboards.ineligibleOptions.size(); i++) {
            context.drawCenteredString(this.font, Leaderboards.ineligibleOptions.get(i), this.width / 2, textHeight, CommonColors.WHITE);
            textHeight = longList ? textHeight + 10 : textHeight + 20;
        }
    }

    @Override
    public String pageId() {
        return "awindf";
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