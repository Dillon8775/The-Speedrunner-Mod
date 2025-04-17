package net.dillon.speedrunnermod.client.screen.base.text;

import net.dillon.speedrunnermod.client.screen.base.AbstractScrollableScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

/**
 * Just for changelog screens.
 */
@Environment(EnvType.CLIENT)
public abstract class AbstractChangelogScreen extends AbstractScrollableScreen {

    public AbstractChangelogScreen(Screen parent, Text title) {
        super(parent, title);
    }

    /**
     * Refreshes changelog screen correctly.
     */
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_R) {
            this.refreshChangelogScreen(this.pageId());
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean centerAligned() {
        return false;
    }
}