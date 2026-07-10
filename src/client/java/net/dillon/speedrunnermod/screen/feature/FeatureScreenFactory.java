package net.dillon.speedrunnermod.screen.feature;

import net.dillon.speedrunnermod.screen.FeatureScreen;
import net.minecraft.client.gui.screens.Screen;

/**
 * Creates a feature screen with all the required information.
 */
@FunctionalInterface
public interface FeatureScreenFactory {
    FeatureScreen create(Screen parent, FeaturePage page);
}