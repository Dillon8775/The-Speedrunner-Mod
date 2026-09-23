package net.dillon.speedrunnermod.screen.synced;

import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class TemporaryScreen extends AbstractModScreen {

    public TemporaryScreen(Screen parent) {
        super(parent, Component.empty());
    }

    @Override
    public void onClose() {
    }
}