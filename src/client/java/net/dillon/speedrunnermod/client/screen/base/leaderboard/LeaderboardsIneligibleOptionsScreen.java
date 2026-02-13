package net.dillon.speedrunnermod.client.screen.base.leaderboard;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.option.Leaderboards;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.Colors;

@Deprecated

public class LeaderboardsIneligibleOptionsScreen extends AbstractModScreen {
    private final Screen parent;
    public static boolean fromInitialBoot = true;

    public LeaderboardsIneligibleOptionsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_INELIGIBLE_OPTIONS);
        this.parent = parent;
    }

    @Override
    public void close() {
        if (fromInitialBoot) {
            this.client.setScreen(new LeaderboardsSafeScreen(this.parent));
        } else {
            this.client.setScreen(new LeaderboardsIneligibleScreen(this.parent));
        }
    }

    @Override
    public void renderCustomText(DrawContext context) {
        boolean longList = Leaderboards.ineligibleOptions.size() > 12;
        int textHeight = longList ? 35 : 50;
        for (int i = 0; i < Leaderboards.ineligibleOptions.size(); i++) {
            context.drawCenteredTextWithShadow(this.textRenderer, Leaderboards.ineligibleOptions.get(i), this.width / 2, textHeight, Colors.WHITE);
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