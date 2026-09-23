package net.dillon.speedrunnermod.screen.misc;

import net.dillon.speedrunnermod.helper.ModConstants;
import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import static net.dillon.dillonlib.task.ClientTasks.openLink;

public class OtherModsScreen extends AbstractModScreen {

    public OtherModsScreen(Screen parent) {
        super(parent, Component.translatable("speedrunnermod.title.resources.mods"));
    }

    @Override
    public void widgets() {
        Button sodiumButton = Button.builder(ModTexts.SODIUM, (buttonWidget) -> {
            openLink(this, link("https://modrinth.com/mod/sodium"), false);
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.title.resources.mods.sodium.tooltip"))
        ).build();

        Button lithiumButton = Button.builder(ModTexts.LITHIUM, (buttonWidget) -> {
            openLink(this, link("https://modrinth.com/mod/lithium"), false);
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.title.resources.mods.lithium.tooltip"))
        ).build();

        Button speedrunIGTButton = Button.builder(ModTexts.SPEEDRUN_IGT, (buttonWidget) -> {
            openLink(this, link("https://modrinth.com/mod/speedrunigt"), false);
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.title.resources.mods.speedrunigt.tooltip"))
        ).build();

        Button kryptonButton = Button.builder(ModTexts.KRYPTON, (buttonWidget) -> {
            openLink(this, link("https://modrinth.com/mod/krypton"), false);
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.title.resources.mods.krypton.tooltip"))
        ).build();

        Button simpleKeybindsButton = Button.builder(ModTexts.SIMPLE_KEYBINDS, (buttonWidget) -> {
            openLink(this, link("https://modrinth.com/mod/simple-keybinds"), false);
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.title.resources.mods.simple_keybinds.tooltip"))
        ).build();

        Button qualityOfQuesoButton = Button.builder(ModTexts.QUALITY_OF_QUESO, (buttonWidget) -> {
            openLink(this, link("https://modrinth.com/mod/quality-of-queso"), false);
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.title.resources.mods.qoq.tooltip"))
        ).build();

        this.createHeader(
                Component.translatable("speedrunnermod.header.qol_mods"),
                speedrunIGTButton,
                qualityOfQuesoButton,
                simpleKeybindsButton
        );

        this.createHeader(
                Component.translatable("speedrunnermod.header.performance_mods"),
                sodiumButton,
                lithiumButton,
                kryptonButton
        );
    }

    /**
     * Returns a {@code link} with correct version filtering.
     */
    private static String link(String url) {
        return url + "/versions?g=" + ModConstants.MC_VERSION + "&l=fabric";
    }
}