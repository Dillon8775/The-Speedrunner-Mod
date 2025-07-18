package net.dillon.speedrunnermod.client.screen.feature.secretdoommode;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class DotDotDotDotScreen extends DotDotDotScreen {

    public DotDotDotDotScreen(Screen parent) {
        super(parent);
    }

    @Override
    public @NotNull String linesKey() {
        return "dot_dot_dot_dot";
    }
}