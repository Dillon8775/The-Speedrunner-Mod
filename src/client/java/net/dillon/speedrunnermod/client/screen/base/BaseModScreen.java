package net.dillon.speedrunnermod.client.screen.base;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.firsttimeplaying.FirstTimePlayingScreen;
import net.dillon.speedrunnermod.main.SpeedrunnerModClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ConfirmLinkScreen;
import net.minecraft.client.gui.screen.MessageScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

import java.util.function.Function;

/**
 * The base screen for any {@code Speedrunner Mod} screen.
 */
@Environment(EnvType.CLIENT)
public class BaseModScreen extends GameOptionsScreen {

    public BaseModScreen(Screen parent, Text title) {
        super(parent, MinecraftClient.getInstance().options, title);
    }

    /**
     * Quits a world.
     */
    protected void quitWorld() {
        if (this.client.isInSingleplayer()) {
            this.client.world.disconnect();
            this.client.disconnect(new MessageScreen(Text.translatable("menu.savingLevel")));
        } else {
            this.client.disconnect();
        }
    }

    /**
     * Fixes resizing issues.
     */
    @Override
    public void resize(MinecraftClient client, int width, int height) {
        super.resize(client, width, height);
        this.clearAndInit();
    }

    /**
     * An easier way to open a link.
     */
    protected void openLink(String link, boolean trusted) {
        this.client.setScreen(new ConfirmLinkScreen(openInBrowser -> {
            if (openInBrowser) {
                Util.getOperatingSystem().open(link);
            }
            this.client.setScreen(this);
            this.resize(this.client, this.width, this.height);
        }, link, trusted));
    }

    /**
     * A simplified way to render a tooltip.
     */
    protected void renderBasicTooltip(Text text, DrawContext context, int mouseX, int mouseY) {
        context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(text, 200), mouseX, mouseY);
    }

    /**
     * Refreshes a base mod screen.
     */
    public void refreshScreen(String id) {
        this.client.setScreen(new RefreshingScreen(parent));
        this.client.setScreen(this.determineRefreshedScreen(id));
    }

    /**
     * Refreshes a feature screen.
     */
    public void refreshFeatureScreen(int pageNumber, ScreenCategory screenCategory) {
        this.client.setScreen(new RefreshingScreen(parent));
        this.client.setScreen(this.determineRefreshedFeatureScreen(pageNumber, screenCategory));
    }

    /**
     * Determines the refreshed screen for base screens.
     */
    private Screen determineRefreshedScreen(String pageId) {
        for (Function<Screen, AbstractModScreen> modScreenConstructor : SpeedrunnerModClient.ALL_MOD_SCREENS) {
            AbstractModScreen screen = modScreenConstructor.apply(this.parent);
            if (screen.pageId().equals(pageId)) {
                return screen;
            }
        }
        return new MainScreen(this.parent);
    }

    /**
     * Determines the refreshed screen for feature screens.
     */
    private Screen determineRefreshedFeatureScreen(int pageNumber, ScreenCategory screenCategory) {
        for (Function<Screen, AbstractFeatureScreen> featureScreenConstructor : SpeedrunnerModClient.ALL_FEATURE_SCREENS) {
            AbstractFeatureScreen screen = featureScreenConstructor.apply(this.parent);
            if (screen.getPageNumber() == pageNumber && screen.getScreenCategory() == screenCategory) {
                return screen;
            }
        }
        return new FirstTimePlayingScreen(this.parent);
    }

    /**
     * Needed because this method is abstract in {@link GameOptionsScreen}.
     */
    @Override
    protected void addOptions() {
    }
}