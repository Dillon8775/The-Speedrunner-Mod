package net.dillon.speedrunnermod.mixin.client.screen;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.DifficultyButtons;
import net.minecraft.client.gui.screens.options.WorldOptionsScreen;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(WorldOptionsScreen.class)
public class WorldOptionsScreenMixin extends Screen {
    @Shadow
    private @Nullable DifficultyButtons difficultyButtons;

    public WorldOptionsScreenMixin(Component title) {
        super(title);
    }

    /**
     * Locks the difficulty button on doom mode. You can't change it >:).
     */
    @Override
    public void extractRenderState(final GuiGraphicsExtractor graphics, final int mouseX, final int mouseY, final float a) {
        super.extractRenderState(graphics, mouseX, mouseY, a);
        if (isDoomMode() && this.difficultyButtons != null) {
            this.difficultyButtons.lockButton().setLocked(true);
            this.difficultyButtons.lockButton().active = false;
            this.difficultyButtons.difficultyButton().active = false;
        }
    }
}