package net.dillon.speedrunnermod.screen.feature.firsttimeplaying;

import net.dillon.dillonlib.util.Texts;
import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.option.eum.Mode;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import static net.dillon.dillonlib.task.ClientTasks.openScreen;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.commonConfigHandler;

public class ModeOptionScreen extends FTPFeatureScreen {

    public ModeOptionScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void init() {
        super.init();
        this.addButtonObject(Button.builder(ModTexts.EASY_MODE, button -> {
            commonConfigHandler().update(o -> o.general().mode = Mode.EASY);

            restartRequired = false;
            openScreen(this.getNextScreen());
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.mode.easy.tooltip"))
        ).build());

        this.addButtonObject(Button.builder(ModTexts.BALANCED_MODE, button -> {
            commonConfigHandler().update(o -> o.general().mode = Mode.BALANCED);

            restartRequired = true;
            openScreen(this.getNextScreen());
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.mode.balanced.tooltip"))
        ).build());

        this.addButtonObject(Button.builder(ModTexts.DOOM_MODE, button -> {
            commonConfigHandler().update(o -> o.general().mode = Mode.DOOM);

            restartRequired = true;
            openScreen(this.getNextScreen());
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.mode.doom.tooltip"))
        ).build());

        this.addButtonObject(Button.builder(Texts.BACK, button -> {
            openScreen(this.getPreviousScreen());
        }).build());
    }

    @Override
    public int getPageNumber() {
        return 3;
    }
}