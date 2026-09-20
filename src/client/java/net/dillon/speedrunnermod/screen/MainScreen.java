package net.dillon.speedrunnermod.screen;

import net.dillon.dillonlib.util.Texts;
import net.dillon.speedrunnermod.config.ConfigurationScreen;
import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.dillon.speedrunnermod.screen.feature.FeaturesScreen;
import net.dillon.speedrunnermod.screen.feature.secretdoommode.SecretDoomModeFeatureScreen;
import net.dillon.speedrunnermod.screen.misc.ResourcesScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.WinScreen;
import net.minecraft.network.chat.Component;

import static net.dillon.dillonlib.task.ClientTasks.openScreen;
import static net.dillon.dillonlib.task.ClientTasks.tryOpenYaclScreen;
import static net.dillon.speedrunnermod.option.ModCommonOptions.isDoomMode;

/**
 * The {@code main screen} for the Speedrunner Mod, consisting of all the basic resources, such as options, other mods, and more.
 */
public class MainScreen extends AbstractModScreen {

    public MainScreen(Screen parent) {
        super(parent, Texts.BLANK);
    }

    @Override
    protected void widgets() {
        Button featuresButton = Button.builder(ModTexts.MENU_FEATURES, (button) -> {
            openScreen(new FeaturesScreen(this));
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.menu.features.tooltip"))
        ).build();

        Button configurateButton = Button.builder(Component.translatable("speedrunnermod.configure"), (button) ->
                tryOpenYaclScreen(() -> ConfigurationScreen.configScreen().generateScreen(this), ModTexts.TITLE)
        ).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.options.tooltip"))
        ).build();

        Button resourcesButton = Button.builder(Component.translatable("speedrunnermod.menu.resources"), (button) -> {
            openScreen(new ResourcesScreen(this));
        }).build();

        Button creditsButton = Button.builder(Component.translatable("speedrunnermod.menu.credits"), (button) -> {
            openScreen(new WinScreen(false, () -> openScreen(this)));
        }).build();

        Button doomModeButton = Button.builder(Component.translatable("speedrunnermod.menu.doom_mode"), (button) -> {
            if (SecretDoomModeFeatureScreen.doomModeButtonAlreadyClicked > 0) {
                openScreen(FeaturePage.UM.createScreen(this));
            } else {
                openScreen(FeaturePage.YOU_ARENT_READY_FOR_THIS.createScreen(this));
            }
        }).build();
        doomModeButton.visible = isDoomMode();

        this.createHeader(
                Component.translatable("speedrunnermod.header.features_and_settings"),
                featuresButton,
                configurateButton
        );

        this.createHeader(
                Component.translatable("speedrunnermod.header.utilities"),
                resourcesButton,
                creditsButton
        );

        if (doomModeButton.visible) {
            this.createHeader(
                    Component.translatable("speedrunnermod.header.what_is_this"),
                    doomModeButton
            );
        }
    }
}