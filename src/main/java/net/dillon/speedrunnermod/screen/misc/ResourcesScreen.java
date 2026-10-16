package net.dillon.speedrunnermod.screen.misc;

import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import static net.dillon.dillonlib.task.ClientTasks.openLink;
import static net.dillon.dillonlib.task.ClientTasks.openScreen;

public class ResourcesScreen extends AbstractModScreen {

    public ResourcesScreen(Screen parent) {
        super(parent, Component.translatable("speedrunnermod.title.resources"));
    }

    @Override
    public void widgets() {
        Button otherModsButton = Button.builder(Component.translatable("speedrunnermod.menu.resources.other_mods").withStyle(ChatFormatting.AQUA), (button) -> {
            openScreen(new OtherModsScreen(this));
        }).build();

        Button resourcePacks = Button.builder(Component.translatable("speedrunnermod.menu.resources.resource_packs"), (button) -> {
            openLink(this, "https://1drv.ms/f/c/dde4bd527f59679e/IgA2xkc_Nf8aR5PFjlG-0dgQAancHE7CokUkzyRyzlDDNeE?e=FJgVIt", false);
        }).build();

        Button tutorialsButton = Button.builder(Component.translatable("speedrunnermod.menu.resources.tutorials").withStyle(ChatFormatting.GREEN), (button) -> {
            openScreen(new TutorialsScreen(this));
        }).build();

        this.createHeader(
                Component.translatable("speedrunnermod.header.mod_resources"),
                otherModsButton,
                resourcePacks,
                tutorialsButton
        );
    }
}