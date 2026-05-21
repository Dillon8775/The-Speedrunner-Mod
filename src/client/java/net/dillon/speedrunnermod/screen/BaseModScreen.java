package net.dillon.speedrunnermod.screen;

import net.dillon.speedrunnermod.main.SpeedrunnerModClient;
import net.dillon.speedrunnermod.screen.firsttimeplaying.*;
import net.dillon.speedrunnermod.screen.secretdoommode.*;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.client.gui.screens.GenericMessageScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Util;

import java.util.List;
import java.util.function.Function;

/**
 * The base screen for any {@code Speedrunner Mod} screen.
 */
public class BaseModScreen extends OptionsSubScreen {
    public EditBox searchField;

    public BaseModScreen(Screen parent, Component title) {
        super(parent, Minecraft.getInstance().options, title);
    }

    /**
     * Quits a world.
     */
    protected void quitWorld() {
        if (this.minecraft.isLocalServer()) {
            this.minecraft.level.disconnect(Component.translatable("menu.savingLevel"));
            this.minecraft.disconnect(new GenericMessageScreen(Component.translatable("menu.savingLevel")), false, false);
        } else {
            this.minecraft.disconnect(new TitleScreen(), false, false);
        }
    }

    /**
     * Fixes resizing issues.
     */
    @Override
    public void resize(int width, int height) {
        String text = "";
        boolean refocus = this.searchField != null && this.searchField.isFocused();
        if (this.searchField != null) {
            text = this.searchField.getValue();
            refocus = this.searchField.isFocused();
        }
        super.resize(width, height);
        this.rebuildWidgets();
        if (this.searchField != null) {
            this.searchField.setValue(text);
            this.searchField.setFocused(refocus);
        }
    }

    /**
     * An easier way to open a link.
     */
    protected void openLink(String link, boolean trusted) {
        this.minecraft.setScreen(new ConfirmLinkScreen(openInBrowser -> {
            if (openInBrowser) {
                Util.getPlatform().openUri(link);
            }
            this.minecraft.setScreen(this);
            this.resize(this.width, this.height);
        }, link, trusted));
    }

    /**
     * A simplified way to render a tooltip.
     */
    protected void renderBasicTooltip(Component text, GuiGraphicsExtractor context, int mouseX, int mouseY) {
        context.setTooltipForNextFrame(this.font, this.font.split(text, 200), mouseX, mouseY);
    }

    /**
     * Refreshes a base mod screen.
     */
    public void refreshScreen(String id) {
        this.minecraft.setScreen(new TemporaryScreen(this.lastScreen, ModTexts.REFRESHING));
        this.minecraft.setScreen(this.determineRefreshedScreen(id));
    }

    /**
     * Refreshes a feature screen.
     */
    public void refreshFeatureScreen(int pageNumber, ScreenCategory screenCategory) {
        this.minecraft.setScreen(new TemporaryScreen(this.lastScreen, ModTexts.REFRESHING));
        this.minecraft.setScreen(this.determineRefreshedFeatureScreen(pageNumber, screenCategory));
    }

    /**
     * Determines the refreshed screen for base screens.
     */
    private Screen determineRefreshedScreen(String pageId) {
        for (Function<Screen, AbstractModScreen> modScreenConstructor : SpeedrunnerModClient.ALL_MOD_SCREENS) {
            AbstractModScreen screen = modScreenConstructor.apply(this.lastScreen);
            if (screen.pageId().equals(pageId)) {
                return screen;
            }
        }
        return new MainScreen(this.lastScreen);
    }

    /**
     * Determines the refreshed screen for feature screens.
     */
    private Screen determineRefreshedFeatureScreen(int pageNumber, ScreenCategory screenCategory) {
        for (Function<Screen, AbstractFeatureScreen> featureScreenConstructor : SpeedrunnerModClient.ALL_FEATURE_SCREENS) {
            AbstractFeatureScreen screen = featureScreenConstructor.apply(this.lastScreen);
            if (screen.getPageNumber() == pageNumber && screen.getScreenCategory() == screenCategory) {
                if (this instanceof AbstractFeatureScreen previous) {
                    screen.targetScrollOffset = previous.targetScrollOffset;
                    screen.scrollOffset = previous.scrollOffset;
                }
                return screen;
            }
        }
        return new FirstTimePlayingScreen(this.lastScreen);
    }

    /**
     * @return all {@code first time playing} feature screens, in order of their {@code page number.}
     */
    public List<Class<? extends AbstractFeatureScreen>> firstTimePlayingScreens() {
        return List.of(
                FirstTimePlayingScreen.class,
                KeyFeaturesScreen.class,
                ModeOptionScreen.class,
                ReadyToPlayScreen.class,
                FTPRestartRequiredScreen.class
        );
    }

    /**
     * @return all {@code secret doom mode} feature screens, in order of their {@code page number.}
     */
    public List<Class<? extends AbstractFeatureScreen>> secretDoomModeScreens() {
        return List.of(
                YouArentReadyForThisScreen.class,
                DoYouUnderstandScreen.class,
                ImReadyScreen.class,
                ExpectTheUnexpectedScreen.class,
                UmScreen.class,
                DotDotDotScreen.class,
                DotDotDotDotScreen.class,
                AllSecretsScreen.class,
                EyeFeaturesScreen.class
        );
    }

    /**
     * Needed because this method is abstract in {@link OptionsSubScreen}.
     */
    @Override
    protected void addOptions() {
    }
}