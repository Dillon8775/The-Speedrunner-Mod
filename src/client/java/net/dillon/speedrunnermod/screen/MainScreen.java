package net.dillon.speedrunnermod.screen;

import net.dillon.dillonlib.util.Texts;
import net.dillon.speedrunnermod.config.ConfigurationScreen;
import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.dillon.speedrunnermod.screen.feature.FeaturesScreen;
import net.dillon.speedrunnermod.screen.feature.secretdoommode.SecretDoomModeFeatureScreen;
import net.dillon.speedrunnermod.screen.misc.ResourcesScreen;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.WinScreen;
import net.minecraft.network.chat.Component;

import java.util.List;

import static net.dillon.dillonlib.task.ClientTasks.tryOpenYaclScreen;
import static net.dillon.speedrunnermod.option.ModCommonOptions.isDoomMode;

/**
 * The {@code main screen} for the Speedrunner Mod, consisting of all the basic resources, such as options, other mods, and more.
 */
public class MainScreen extends BaseModScreen {

    public MainScreen(Screen parent) {
        super(parent, Texts.BLANK);
    }

    @Override
    protected void init() {
        super.init();

        Button configurateButton = Button.builder(Component.translatable("speedrunnermod.configure")
                .withStyle(ChatFormatting.AQUA)
                .withStyle(ChatFormatting.UNDERLINE), (button) ->
                tryOpenYaclScreen(() -> ConfigurationScreen.configScreen().generateScreen(this), ModTexts.TITLE)
        ).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.menu.options.tooltip"))
        ).build();

        Button featuresButton = Button.builder(ModTexts.MENU_FEATURES, (button) -> {
            this.minecraft.gui.setScreen(new FeaturesScreen(this));
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.menu.features.tooltip"))
        ).build();

        Button resourcesButton = Button.builder(Component.translatable("speedrunnermod.menu.resources"), (button) -> {
            this.minecraft.gui.setScreen(new ResourcesScreen(this));
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.menu.resources.tooltip"))
        ).build();

        Button creditsButton = Button.builder(Component.translatable("speedrunnermod.menu.credits"), (button) -> {
            this.minecraft.gui.setScreen(new WinScreen(Minecraft.getInstance().hasShiftDown(), () -> this.minecraft.gui.setScreen(this)));
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.menu.credits.tooltip"))
        ).build();

        Button doomModeButton = Button.builder(Component.translatable("speedrunnermod.menu.doom_mode"), (button) -> {
            if (SecretDoomModeFeatureScreen.doomModeButtonAlreadyClicked > 0) {
                this.minecraft.gui.setScreen(FeaturePage.UM.createScreen(this));
            } else {
                this.minecraft.gui.setScreen(FeaturePage.YOU_ARENT_READY_FOR_THIS.createScreen(this));
            }
        }).build();
        doomModeButton.visible = isDoomMode();

        this.list.addHeader(Component.translatable("speedrunnermod.menu.features_and_settings"));
        this.list.addBig(
                configurateButton
        );
        this.list.addSmall(
                List.of(
                        featuresButton
                )
        );

        this.list.addHeader(Component.translatable("speedrunnermod.menu.utilities"));
        this.list.addSmall(
                List.of(
                        resourcesButton,
                        creditsButton
                )
        );

        if (doomModeButton.visible) {
            this.list.addHeader(Component.translatable("speedrunnermod.menu.what_is_this"));
            this.list.addSmall(
                    List.of(
                            doomModeButton
                    )
            );
        }
    }
}