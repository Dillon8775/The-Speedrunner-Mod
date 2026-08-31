package net.dillon.speedrunnermod.screen;

import com.mojang.blaze3d.platform.InputConstants;
import net.dillon.dillonlib.util.Texts;
import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.dillon.speedrunnermod.screen.feature.FeatureScreenCategory;
import net.dillon.speedrunnermod.screen.feature.FeatureScreenPage;
import net.dillon.speedrunnermod.screen.feature.firsttimeplaying.FirstTimePlayingScreen;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.CommonColors;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

/**
 * Used to create {@code feature screens}, for the soul purpose of displaying some of Speedrunner Mod's features.
 */
public class FeatureScreen extends AbstractScrollableScreen {
    protected final Screen parent;
    public final FeaturePage featurePage;
    private Button nextButton, previousButton;
    protected static boolean restartRequired = false;

    /**
     * Constructs a feature screen.
     */
    public FeatureScreen(Screen parent, FeaturePage featurePage) {
        super(parent, Texts.BLANK);
        this.parent = parent;
        this.featurePage = featurePage;
    }

    /**
     * Used to build title text.
     */
    protected static Component featureTitleText(FeatureScreenCategory category, String lang) {
        return Component.translatable("speedrunnermod.title.features." + category.toString().toLowerCase() + "." + lang);
    }

    /**
     * Determines if the screen should render the title text.
     */
    @Override
    protected boolean shouldRenderTitleText() {
        return featurePage.getCategory() != FeatureScreenCategory.FIRST_TIME_PLAYING;
    }

    /**
     * No page ID's for feature screens, we use page numbers instead.
     */
    @Override
    public String pageId() {
        return null;
    }

    /**
     * Creates the basic buttons for every feature screen.
     * <p>See comments inside of method for more documentation.</p>
     */
    @Override
    protected void init() {
        super.init();

        if (featurePage.getCategory() != FeatureScreenCategory.SECRET_DOOM_MODE) {
            // A starter feature screen (or the first page of a certain category of features)
            // consists of only a "Next" and "Done" button
            if (featurePage.getPageType() != FeatureScreenPage.LAST && featurePage.getPageType() != FeatureScreenPage.FTP) {
                this.nextButton = this.addRenderableWidget(Button.builder(ModTexts.NEXT_ARROW, button -> {
                    this.minecraft.gui.setScreen(this.getNextScreen());
                }).bounds(this.getButtonsRightSide() + 100, this.getDoneButtonHeight(), 20, 20).build());
            }

            // A normal feature screen, which is any page between the first and last page of a certain category of features,
            // consists of a "Next", "Previous" and "Done" button
            if (featurePage.getPageType() != FeatureScreenPage.FIRST && featurePage.getPageType() != FeatureScreenPage.FTP) {
                this.previousButton = this.addRenderableWidget(Button.builder(ModTexts.PREVIOUS, button -> {
                    this.minecraft.gui.setScreen(this.getPreviousScreen());
                }).bounds(this.getButtonsLeftSide() + 30, this.getDoneButtonHeight(), 20, 20).build());
            }
        }
    }

    /**
     * Renders the basic and additional objects on a feature screen.
     */
    @Override
    public void extractRenderState(GuiGraphicsExtractor context, int mouseX, int mouseY, float delta) {
        super.extractRenderState(context, mouseX, mouseY, delta);
        int height = this.height - 21;
        if (featurePage.getCategory() != FeatureScreenCategory.SECRET_DOOM_MODE) {
            context.centeredText(this.font, Component.literal("§lPage:§r " + getPageNumber() + "/" + this.getMaxPages()), featurePage.getCategory() == FeatureScreenCategory.FIRST_TIME_PLAYING ? this.width / 2 : this.width - 39, height, CommonColors.WHITE);
        }

        if (featurePage.getCategory() == FeatureScreenCategory.FIRST_TIME_PLAYING) {
            int middle = this.width / 2 - 128;
            int logoHeight = 10;
            context.blit(RenderPipelines.GUI_TEXTURED, Identifier.parse("speedrunnermod:textures/gui/speedrunner_mod.png"), middle, logoHeight, 0.0F, 0.0F, 258, 32, 258, 32);
        }

        this.renderTooltips(context, mouseX, mouseY);
    }

    /**
     * Render custom tooltips on screen.
     */
    @Override
    protected void renderTooltips(GuiGraphicsExtractor context, int mouseX, int mouseY) {
        if (featurePage.getCategory() != FeatureScreenCategory.SECRET_DOOM_MODE) {
            if (featurePage.getPageType() == FeatureScreenPage.FIRST || featurePage.getPageType() == FeatureScreenPage.DEFAULT) {
                if (this.nextButton.isHovered()) {
                    this.renderBasicTooltip(ModTexts.NEXT_ARROW_TOOLTIP, context, mouseX, mouseY);
                }
                if (featurePage.getPageType() == FeatureScreenPage.DEFAULT && this.previousButton.isHovered()) {
                    this.renderBasicTooltip(ModTexts.PREVIOUS_TOOLTIP, context, mouseX, mouseY);
                }
            } else if (featurePage.getPageType() == FeatureScreenPage.LAST) {
                if (this.previousButton.isHovered()) {
                    this.renderBasicTooltip(ModTexts.PREVIOUS_TOOLTIP, context, mouseX, mouseY);
                }
            }
        }
    }

    /**
     * Allows for navigation between pages by using the left and right arrow keys, and to reload the screen based if the user presses "R" on their keyboard.
     */
    @Override
    public boolean keyPressed(KeyEvent input) {
        if (input.key() == InputConstants.KEY_LEFT || input.key() == InputConstants.KEY_A) {
            if (this.getPageNumber() != 1) {
                this.minecraft.gui.setScreen(this.getPreviousScreen());
            }
            return true;
        } else if (input.key() == InputConstants.KEY_RIGHT || input.key() == InputConstants.KEY_D) {
            if (featurePage.getCategory() == FeatureScreenCategory.FIRST_TIME_PLAYING && !(this.getPageNumber() < 3)) {
                SpeedrunnerMod.LOGGER.warn("Please choose an option!");
            } else if (this.getPageNumber() != this.getMaxPages()) {
                this.minecraft.gui.setScreen(this.getNextScreen());
            }
            return true;
        }
        return super.keyPressed(input);
    }

    /**
     * Get the respective text file based on screen category.
     */
    @Override
    protected String getTextFile() {
        return featurePage.getTextFileLocation();
    }

    /**
     * @return the maximum amount of pages for each category.
     */
    protected int getMaxPages() {
        return this.getFeaturesFromCategory(featurePage.getCategory()).size();
    }

    /**
     * @return the page number of feature screens.
     */
    private Screen page(int pageNumber) {
        return nextScreen(pageNumber, featurePage.getCategory());
    }

    /**
     * @return the list of category screens to go by for each screen category.
     */
    private List<FeaturePage> getFeaturesFromCategory(FeatureScreenCategory category) {
        return Arrays.stream(FeaturePage.values())
                .filter(page -> page.getCategory() == category)
                .toList();
    }

    /**
     * Determine the screen to go to, based on the page number.
     */
    private Screen nextScreen(int pageNumber, FeatureScreenCategory category) {
        List<FeaturePage> pages = getFeaturesFromCategory(category);

        if (pageNumber > 0 && pageNumber <= pages.size()) {
            FeaturePage page = pages.get(pageNumber - 1);
            return page.createScreen(this.parent);
        }

        return new MainScreen(this.parent);
    }

    /**
     * @return the page number for the screen.
     */
    public int getPageNumber() {
        return getFeaturesFromCategory(featurePage.getCategory())
                .indexOf(featurePage) + 1;
    }

    /**
     * Gets the {@code next screen} that should be displayed when clicking the {@code "Next"} button.
     */
    @Nullable
    protected Screen getNextScreen() {
        return this.page(this.getPageNumber() + 1);
    }

    /**
     * <p>Gets the {@code previous screen}, which goes back to the screen displayed before.</p>
     * <p>On {@link FeatureScreenPage#FIRST} pages, there may not be a previous screen.</p>
     */
    @Nullable
    protected Screen getPreviousScreen() {
        return this.page(this.getPageNumber() - 1);
    }

    /**
     * Toggles a feature when pressing the enable/disable button.
     */
    protected void refreshNonRestartableFeature() {
        this.refreshFeatureScreen(this.getPageNumber(), featurePage.getCategory());
    }

    /**
     * Toggles a feature that requires a restart when pressing the enable/disable button.
     */
    protected void refreshRestartableFeature(Screen screen) {
        this.minecraft.gui.setScreen(screen);
    }

    /**
     * Refreshes a feature screen.
     */
    public void refreshFeatureScreen(int pageNumber, FeatureScreenCategory screenCategory) {
        this.minecraft.gui.setScreen(new TemporaryScreen(this.lastScreen, ModTexts.REFRESHING));
        this.minecraft.gui.setScreen(this.determineRefreshedFeatureScreen(pageNumber, screenCategory));
    }

    /**
     * Determines the refreshed screen for feature screens.
     */
    private Screen determineRefreshedFeatureScreen(int pageNumber, FeatureScreenCategory screenCategory) {
        for (FeaturePage page : FeaturePage.values()) {
            if (page.getCategory() == screenCategory && getPageNumber() == pageNumber) {

                FeatureScreen screen = page.createScreen(this.lastScreen);

                if (this instanceof FeatureScreen previous) {
                    screen.targetScrollOffset = previous.targetScrollOffset;
                    screen.scrollOffset = previous.scrollOffset;
                }

                return screen;
            }
        }

        return new FirstTimePlayingScreen(this.lastScreen, featurePage);
    }
}