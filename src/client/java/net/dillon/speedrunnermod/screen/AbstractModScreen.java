package net.dillon.speedrunnermod.screen;

import net.dillon.dillonlib.task.ClientTasks;
import net.dillon.dillonlib.util.Texts;
import net.dillon.speedrunnermod.helper.ModConstants;
import net.dillon.speedrunnermod.platform.SpeedrunnerModPlatforms;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.dillon.speedrunnermod.screen.feature.FeatureScreenCategory;
import net.dillon.speedrunnermod.util.ClientModUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

import static net.dillon.dillonlib.task.ClientTasks.openScreen;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Used to create any {@code Speedrunner Mod} screens.
 */
public abstract class AbstractModScreen extends OptionsSubScreen {
    public Button doneButton;
    public Component realTitle;

    public AbstractModScreen(Screen parent, Component title) {
        super(parent, Minecraft.getInstance().options, Texts.BLANK);
        this.realTitle = title;
    }

    @Override
    protected void addFooter() {
        this.doneButton = this.layout.addToFooter(Button.builder(CommonComponents.GUI_DONE, button -> this.onClose()).width(200).build());
    }

    @Override
    protected void addTitle() {
        if (!this.shouldRenderTitleText()) {
            return;
        }

        Component realTitle = this.realTitle;
        if (this instanceof FeatureScreen abstractFeatureScreen) {
            FeatureScreenCategory category = abstractFeatureScreen.featurePage.getCategory();
            String key = abstractFeatureScreen.featurePage.getKey().toLowerCase();
            realTitle = FeatureScreen.featureTitleText(category, key);
        }

        this.layout.addTitleHeader(realTitle, this.font);
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);

        this.renderCustomText(graphics);

        if (this.shouldRenderVersionText()) {
            ClientTasks.drawModInfo(
                    graphics,
                    this,
                    ModConstants.MOD_VERSION,
                    SpeedrunnerModPlatforms.getPlatform().logoWidth().getWidthModifier(),
                    ofSpeedrunnerMod("hud/logo_smithing_template"),
                    ModConstants.HAS_UPDATE
            );
        }

        if (!this.shouldRenderTitleText()) {
            renderSpeedrunnerModTitleText(graphics, this.width);
        }
    }

    /**
     * Iterate through all {@link FeatureScreen}s to add to the main feature screen lists.
     */
    protected void addButtonsIteratively(FeatureScreenCategory screenCategory) {
        List<AbstractWidget> featureButtons = new ArrayList<>();
        for (FeaturePage page : FeaturePage.values()) {
            if (page.getCategory() == screenCategory) {
                FeatureScreen screen = this.createFeatureScreen(page);

                featureButtons.add(Button.builder(
                        FeatureScreen.featureTitleText(screenCategory, page.getKey()), b -> openScreen(screen)
                ).build());
            }
        }

        this.list.addSmall(featureButtons);
    }

    /**
     * Creates every feature screen based on its category.
     */
    private FeatureScreen createFeatureScreen(FeaturePage page) {
        return page.createScreen(this);
    }

    /**
     * Renders the speedrunner mod title text.
     */
    protected static void renderSpeedrunnerModTitleText(GuiGraphicsExtractor graphics, int width) {
        int middle = width / 2 - 65;
        int height = 10;
        ClientModUtil.renderSpeedrunnerModLogo(graphics, middle, height, false);
    }

    /**
     * Render custom text on a mod screen.
     * <p><b>Never</b> {@link Override} the {@code basic render method,} use this method instead.</p>
     */
    protected void renderCustomText(GuiGraphicsExtractor graphics) {
    }

    /**
     * Determines if the screen should render the "Version: v#.#" text.
     */
    protected boolean shouldRenderVersionText() {
        return true;
    }

    /**
     * Determines if the screen should render the title text.
     */
    protected boolean shouldRenderTitleText() {
        return false;
    }

    /**
     * Needed because this method is abstract in {@link OptionsSubScreen}.
     */
    @Override
    protected void addOptions() {
    }
}