package net.dillon.speedrunnermod.client.screen.base;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.SpeedrunnerModClient;
import net.dillon.speedrunnermod.client.screen.base.text.AbstractChangelogScreen;
import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.firsttimeplaying.FirstTimePlayingScreen;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ConfirmLinkScreen;
import net.minecraft.client.gui.screen.MessageScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.function.BiFunction;

/**
 * The base screen for any {@code Speedrunner Mod} screen.
 */
@Environment(EnvType.CLIENT)
public class BaseModScreen extends GameOptionsScreen {
    public ButtonWidget refreshButton;
    protected final GameOptions options = MinecraftClient.getInstance().options;

    public BaseModScreen(Screen parent, GameOptions options, Text title) {
        super(parent, options, title);
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
     * An easier way to open a link.
     */
    protected void openLink(String link, boolean trusted) {
        this.client.setScreen(new ConfirmLinkScreen(openInBrowser -> {
            if (openInBrowser) {
                Util.getOperatingSystem().open(link);
            }
            this.client.setScreen(this);
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
        ModOptions.saveConfig();
        this.client.setScreen(new RefreshingScreen(parent, options));
        this.client.setScreen(this.determineRefreshedScreen(id));
    }

    /**
     * Refreshes a feature screen.
     */
    public void refreshFeatureScreen(int pageNumber, ScreenCategory screenCategory) {
        this.client.setScreen(new RefreshingScreen(parent, options));
        this.client.setScreen(this.determineRefreshedFeatureScreen(pageNumber, screenCategory));
    }

    /**
     * Refreshes a changelog screen.
     */
    public void refreshChangelogScreen(String id) {
        ModOptions.saveConfig();
        this.client.setScreen(new RefreshingScreen(parent, options));
        this.client.setScreen(this.determineRefreshedChangelogScreen(id));
    }

    /**
     * Determines the refreshed screen for base screens.
     */
    public Screen determineRefreshedScreen(String pageId) {
        for (BiFunction<Screen, GameOptions, AbstractModScreen> modScreenConstructor : SpeedrunnerModClient.ALL_MOD_SCREENS) {
            AbstractModScreen screen = modScreenConstructor.apply(this.parent, this.options);
            if (screen.pageId().equals(pageId)) {
                return screen;
            }
        }
        return new MainScreen(this.parent, this.options);
    }

    /**
     * Determines the refreshed screen for feature screens.
     */
    public Screen determineRefreshedFeatureScreen(int pageNumber, ScreenCategory screenCategory) {
        for (BiFunction<Screen, GameOptions, AbstractFeatureScreen> featureScreenConstructor : SpeedrunnerModClient.ALL_FEATURE_SCREENS) {
            AbstractFeatureScreen screen = featureScreenConstructor.apply(this.parent, this.options);
            if (screen.getPageNumber() == pageNumber && screen.getScreenCategory() == screenCategory) {
                return screen;
            }
        }
        return new FirstTimePlayingScreen(this.parent, this.options);
    }

    /**
     * Determines the refreshed screen for changelog screens.
     */
    public Screen determineRefreshedChangelogScreen(String pageId) {
        for (BiFunction<Screen, GameOptions, AbstractChangelogScreen> modScreenConstructor : SpeedrunnerModClient.ALL_CHANGELOG_SCREENS) {
            AbstractChangelogScreen screen = modScreenConstructor.apply(this.parent, this.options);
            if (screen.pageId().equals(pageId)) {
                return screen;
            }
        }
        return new MainScreen(this.parent, this.options);
    }

    /**
     * Needed because this method is abstract in {@link GameOptionsScreen}.
     */
    @Override
    protected void addOptions() {
    }
}