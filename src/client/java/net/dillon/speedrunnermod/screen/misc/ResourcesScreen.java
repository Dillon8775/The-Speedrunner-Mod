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
    protected void widgets() {
        Button showcaseVideoButton = Button.builder(Component.translatable("speedrunnermod.menu.resources.showcase_video").withStyle(ChatFormatting.LIGHT_PURPLE), (buttonWidget) -> {
            openLink(this, "https://youtu.be/08z9VvnFacw", false);
        }).build();

        Button releaseTrailerButton = Button.builder(Component.translatable("speedrunnermod.menu.resources.release_trailer").withStyle(ChatFormatting.AQUA), (buttonWidget) -> {
            openLink(this, "https://youtu.be/u37ujBiCMCw", false);
        }).build();

        Button otherModsButton = Button.builder(Component.translatable("speedrunnermod.menu.resources.other_mods").withStyle(ChatFormatting.AQUA), (button) -> {
            openScreen(new OtherModsScreen(this));
        }).build();

        Button resourcePacks = Button.builder(Component.translatable("speedrunnermod.menu.resources.resource_packs"), (button) -> {
            openLink(this, "https://1drv.ms/f/c/dde4bd527f59679e/IgA2xkc_Nf8aR5PFjlG-0dgQAancHE7CokUkzyRyzlDDNeE?e=FJgVIt", false);
        }).build();

        Button tutorialsButton = Button.builder(Component.translatable("speedrunnermod.menu.resources.tutorials").withStyle(ChatFormatting.GREEN), (button) -> {
            openScreen(new TutorialsScreen(this));
        }).build();

        Button askQuestionsButton = Button.builder(Component.translatable("speedrunnermod.resources.ask_questions"), (button) -> {
            openLink(this, "https://discord.gg/vfqEAn4YFy", false);
        }).build();

        Button reportBugsButton = Button.builder(Component.translatable("speedrunnermod.resources.report_bugs"), (button) -> {
            openLink(this, "https://github.com/Dillon8775/The-Speedrunner-Mod/issues", false);
        }).build();

        this.createHeader(
                Component.translatable("speedrunnermod.menu.mod_resources"),
                showcaseVideoButton,
                releaseTrailerButton,
                otherModsButton,
                resourcePacks,
                tutorialsButton
        );

        this.createHeader(
                Component.translatable("speedrunnermod.menu.questions_and_bugs"),
                askQuestionsButton,
                reportBugsButton
        );
    }
}