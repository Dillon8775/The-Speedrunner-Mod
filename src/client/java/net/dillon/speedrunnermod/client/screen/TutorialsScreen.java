package net.dillon.speedrunnermod.client.screen;

import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class TutorialsScreen extends AbstractModScreen {

    public TutorialsScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_TUTORIALS);
    }

    @Override
    protected void init() {
        this.initializeCustomButtonListWidget();

        this.buttons.add(0, ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.bastion_routes"), (button) -> {
            this.client.setScreen(new BastionRoutesScreen(this.parent, MinecraftClient.getInstance().options));
        }).build());

        this.buttons.add(1, ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.nether_fortresses"), (button) -> {
            this.openLink("https://www.youtube.com/watch?v=pmx9LyUvLTk", false);
        }).build());

        this.buttons.add(2, ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.microlensing"), (button) -> {
            this.client.setScreen(new MicrolensingScreen(this.parent, MinecraftClient.getInstance().options));
        }).build());

        this.buttons.add(3, ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.blind_travel"), (button) -> {
            this.openLink("https://www.youtube.com/watch?v=Ou58P7e-ZY0", false);
        }).build());

        this.buttons.add(4, ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.one_cycling"), (button) -> {
            this.openLink("https://www.youtube.com/watch?v=JaVyuTyDxxs", false);
        }).build());

        this.buttons.add(5, ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.pie_chart"), (button) -> {
            this.openLink("https://www.youtube.com/watch?v=ENgEBHIifm8", false);
        }).build());

        this.buttons.add(6, ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.f3_menu"), (button) -> {
            this.openLink("https://www.youtube.com/watch?v=-fSr7P5LQJY", false);
        }).build());

        this.buttons.add(7, ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.buried_treasure"), (button) -> {
            this.openLink("https://www.youtube.com/watch?v=_dyD8ZwagDg", false);
        }).build());

        this.buttons.add(8, ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.other_useful_tricks"), (button) -> {
            this.openLink("https://www.youtube.com/watch?v=TvvApbI6fis", false);
        }).build());

        super.init();
    }

    @Override
    protected void renderTooltips(DrawContext context, int mouseX, int mouseY) {
        if (this.buttons.get(0).isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.menu.resources.tutorials.bastion_routes.tooltip"), context, mouseX, mouseY);
        }
        if (this.buttons.get(1).isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.menu.resources.tutorials.nether_fortresses.tooltip"), context, mouseX, mouseY);
        }
        if (this.buttons.get(2).isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.menu.resources.tutorials.microlensing.tooltip"), context, mouseX, mouseY);
        }
        if (this.buttons.get(3).isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.menu.resources.tutorials.blind_travel.tooltip"), context, mouseX, mouseY);
        }
        if (this.buttons.get(4).isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.menu.resources.tutorials.one_cycling.tooltip"), context, mouseX, mouseY);
        }
        if (this.buttons.get(5).isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.menu.resources.tutorials.pie_chart.tooltip"), context, mouseX, mouseY);
        }
        if (this.buttons.get(7).isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.menu.resources.tutorials.buried_treasure.tooltip"), context, mouseX, mouseY);
        }
        if (this.buttons.get(8).isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.menu.resources.tutorials.other_useful_tricks.tooltip"), context, mouseX, mouseY);
        }
        super.renderTooltips(context, mouseX, mouseY);
    }

    @Override
    public void close() {
        this.client.setScreen(new ResourcesScreen(this.parent, MinecraftClient.getInstance().options));
    }

    @Override
    protected int columns() {
        return 2;
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return false;
    }

    @Override
    protected boolean isOptionsScreen() {
        return false;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }

    private static class BastionRoutesScreen extends AbstractModScreen {

        public BastionRoutesScreen(Screen parent, GameOptions options) {
            super(parent, options, Text.translatable("speedrunnermod.title.resources.tutorials.bastion_routes"));
        }

        @Override
        protected void init() {
            int height = this.getButtonsHeight();

            this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.bastion_routes.treasure"), (button) -> {
                this.openLink("https://www.youtube.com/watch?v=np6fc_z9LUY", false);
            }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());
            this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.bastion_routes.bridge"), (button) -> {
                this.openLink("https://www.youtube.com/watch?v=Dts81nEnzuQ", false);
            }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());

            height += 24;
            this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.bastion_routes.stables"), (button) -> {
                this.openLink("https://www.youtube.com/watch?v=WIN-ZtUJfIc", false);
            }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());
            this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.bastion_routes.housing"), (button) -> {
                this.openLink("https://www.youtube.com/watch?v=S0G5asEjrgk", false);
            }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());

            this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, (button) -> {
                this.close();
            }).dimensions(this.width / 2 - 100, this.height - 29, 200, 20).build());
        }

        @Override
        public void close() {
            this.client.setScreen(new TutorialsScreen(this.parent, MinecraftClient.getInstance().options));
        }

        @Override
        protected int columns() {
            return 2;
        }

        @Override
        protected boolean shouldRenderVersionText() {
            return false;
        }

        @Override
        protected boolean isOptionsScreen() {
            return false;
        }

        @Override
        protected boolean shouldRenderTitleText() {
            return true;
        }
    }

    private static class MicrolensingScreen extends AbstractModScreen {

        public MicrolensingScreen(Screen parent, GameOptions options) {
            super(parent, options, Text.translatable("speedrunnermod.title.resources.tutorials.microlensing"));
        }

        @Override
        protected void init() {
            int height = this.getButtonsHeight();

            this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.microlensing.bastion"), (button) -> {
                this.openLink("https://www.youtube.com/watch?v=jvTfMLPnMSw", false);
            }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());
            this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.microlensing.fortress"), (button) -> {
                this.openLink("https://www.youtube.com/watch?v=Kl_-L9XbJko", false);
            }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());

            this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, (button) -> {
                this.close();
            }).dimensions(this.width / 2 - 100, this.height - 29, 200, 20).build());
        }

        @Override
        public void close() {
            this.client.setScreen(new TutorialsScreen(this.parent, MinecraftClient.getInstance().options));
        }

        @Override
        protected int columns() {
            return 2;
        }

        @Override
        protected boolean shouldRenderVersionText() {
            return false;
        }

        @Override
        protected boolean isOptionsScreen() {
            return false;
        }

        @Override
        protected boolean shouldRenderTitleText() {
            return true;
        }
    }
}