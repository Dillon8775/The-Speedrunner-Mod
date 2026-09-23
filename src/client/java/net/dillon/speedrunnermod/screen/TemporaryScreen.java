package net.dillon.speedrunnermod.screen;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class TemporaryScreen extends AbstractModScreen {

    public TemporaryScreen(Screen parent) {
        super(parent, Component.empty());
    }

    @Override
    public void onClose() {
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return false;
    }
}