package net.dillon.speedrunnermod.screen.feature.firsttimeplaying;

import net.dillon.dillonlib.util.Texts;
import net.dillon.speedrunnermod.option.ModClientOptions;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.dillon.speedrunnermod.screen.feature.FeaturesScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;

import static net.dillon.dillonlib.client.ModernWidgetOptions.createOption;
import static net.dillon.dillonlib.client.ModernWidgetOptions.createSimpleBooleanOption;
import static net.dillon.dillonlib.task.ClientTasks.openScreen;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.client;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientConfigHandler;

public class ReadyToPlayScreen extends FTPFeatureScreen {

    public ReadyToPlayScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void init() {
        super.init();
        this.addButtonObject(Button.builder(Component.translatable("speedrunnermod.begin_playing"), button -> {
            if (restartRequired) {
                openScreen(this.getNextScreen());
            } else {
                clientConfigHandler().update(c -> c.storedValues().firstTimePlaying = false);
                if (client().storedValues().viewFeatures) {
                    openScreen(new FeaturesScreen(null));
                    clientConfigHandler().update(c -> c.storedValues().viewFeatures = false);
                } else {
                    openScreen(new TitleScreen());
                }
            }
        }).build());
        this.addButtonObject(createOption(
                createSimpleBooleanOption(
                        "speedrunnermod.view_features",
                        false,
                        client().storedValues().viewFeatures,
                        ModClientOptions.INSTANCE,
                        (clientModOptions, v) -> clientModOptions.storedValues().viewFeatures = v
                )
        ));
        this.addButtonObject(Button.builder(Texts.BACK, button -> {
            openScreen(this.getPreviousScreen());
            restartRequired = false;
        }).build());
    }
}