package net.dillon.speedrunnermod.client.screen.base.misc;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

import java.util.List;

public class TutorialsScreen extends AbstractModScreen {
    private ButtonWidget bastionRoutesButton,
            netherFortressesButton,
            microlensingButton,
            blindTravelButton,
            oneCyclingButton,
            pieChartButton,
            f3MenuButton,
            buriedTreasuresButton,
            comprehensiveGuideButton,
            thirtyMinRun,
            otherUsefulTricksButton;

    public TutorialsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_TUTORIALS);
    }

    @Override
    protected List<ClickableWidget> buttons() {
        return List.of(
                this.bastionRoutesButton,
                this.netherFortressesButton,
                this.microlensingButton,
                this.blindTravelButton,
                this.oneCyclingButton,
                this.pieChartButton,
                this.f3MenuButton,
                this.buriedTreasuresButton,
                this.comprehensiveGuideButton,
                this.thirtyMinRun,
                this.otherUsefulTricksButton
        );
    }

    @Override
    protected void init() {
        this.bastionRoutesButton = ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.bastion_routes"), (button) -> {
            this.client.setScreen(new BastionRoutesScreen(this.parent));
        }).build();

        this.netherFortressesButton = ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.nether_fortresses"), (button) -> {
            this.openLink("https://www.youtube.com/watch?v=pmx9LyUvLTk", false);
        }).build();

        this.microlensingButton = ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.microlensing"), (button) -> {
            this.client.setScreen(new MicrolensingScreen(this.parent));
        }).build();

        this.blindTravelButton = ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.blind_travel"), (button) -> {
            this.openLink("https://www.youtube.com/watch?v=Ou58P7e-ZY0", false);
        }).build();

        this.oneCyclingButton = ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.one_cycling"), (button) -> {
            this.openLink("https://www.youtube.com/watch?v=JaVyuTyDxxs", false);
        }).build();

        this.pieChartButton = ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.pie_chart"), (button) -> {
            this.openLink("https://www.youtube.com/watch?v=ENgEBHIifm8", false);
        }).build();

        this.f3MenuButton = ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.f3_menu"), (button) -> {
            this.openLink("https://www.youtube.com/watch?v=-fSr7P5LQJY", false);
        }).build();

        this.buriedTreasuresButton = ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.buried_treasure"), (button) -> {
            this.openLink("https://www.youtube.com/watch?v=_dyD8ZwagDg", false);
        }).build();

        this.comprehensiveGuideButton = ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.comprehensive_guide"), (button) -> {
            this.openLink("https://youtu.be/iaUF0oaegns?si=L47hwqlAerCAZ-S4", false);
        }).build();

        this.thirtyMinRun = ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.thirty_min_run"), (button) -> {
            this.openLink("https://youtu.be/olakF9Xbisc?si=1pquTzHph5n5S44o", false);
        }).build();

        this.otherUsefulTricksButton = ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.other_useful_tricks"), (button) -> {
            this.openLink("https://www.youtube.com/watch?v=TvvApbI6fis", false);
        }).build();

        super.init();
    }

    @Override
    protected void renderTooltips(DrawContext context, int mouseX, int mouseY) {
        if (this.bastionRoutesButton.isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.menu.resources.tutorials.bastion_routes.tooltip"), context, mouseX, mouseY);
        }
        if (this.netherFortressesButton.isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.menu.resources.tutorials.nether_fortresses.tooltip"), context, mouseX, mouseY);
        }
        if (this.microlensingButton.isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.menu.resources.tutorials.microlensing.tooltip"), context, mouseX, mouseY);
        }
        if (this.blindTravelButton.isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.menu.resources.tutorials.blind_travel.tooltip"), context, mouseX, mouseY);
        }
        if (this.oneCyclingButton.isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.menu.resources.tutorials.one_cycling.tooltip"), context, mouseX, mouseY);
        }
        if (this.pieChartButton.isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.menu.resources.tutorials.pie_chart.tooltip"), context, mouseX, mouseY);
        }
        if (this.buriedTreasuresButton.isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.menu.resources.tutorials.buried_treasure.tooltip"), context, mouseX, mouseY);
        }
        if (this.comprehensiveGuideButton.isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.menu.resources.tutorials.comprehensive_guide.tooltip"), context, mouseX, mouseY);
        }
        if (this.thirtyMinRun.isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.menu.resources.tutorials.thirty_min_run.tooltip"), context, mouseX, mouseY);
        }
        if (this.otherUsefulTricksButton.isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.menu.resources.tutorials.other_useful_tricks.tooltip"), context, mouseX, mouseY);
        }
        super.renderTooltips(context, mouseX, mouseY);
    }

    @Override
    public String pageId() {
        return "gnipfi";
    }

    @Override
    public void close() {
        this.client.setScreen(new ResourcesScreen(this.parent));
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
    public boolean isOptionsScreen() {
        return false;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }

    protected static class BastionRoutesScreen extends AbstractModScreen {
        private ButtonWidget treasureBastionButton, bridgeBastionButton, stablesBastionButton, housingBastionButton;

        public BastionRoutesScreen(Screen parent) {
            super(parent, Text.translatable("speedrunnermod.title.resources.tutorials.bastion_routes"));
        }

        @Override
        protected List<ClickableWidget> buttons() {
            return List.of(
                    this.treasureBastionButton,
                    this.bridgeBastionButton,
                    this.stablesBastionButton,
                    this.housingBastionButton
            );
        }

        @Override
        protected void init() {
            this.treasureBastionButton = ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.bastion_routes.treasure"), (button) -> {
                this.openLink("https://www.youtube.com/watch?v=np6fc_z9LUY", false);
            }).build();

            this.bridgeBastionButton = ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.bastion_routes.bridge"), (button) -> {
                this.openLink("https://www.youtube.com/watch?v=Dts81nEnzuQ", false);
            }).build();

            this.stablesBastionButton = ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.bastion_routes.stables"), (button) -> {
                this.openLink("https://www.youtube.com/watch?v=WIN-ZtUJfIc", false);
            }).build();

            this.housingBastionButton = ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.bastion_routes.housing"), (button) -> {
                this.openLink("https://www.youtube.com/watch?v=S0G5asEjrgk", false);
            }).build();

            super.init();
        }

        @Override
        public void close() {
            this.client.setScreen(new TutorialsScreen(this.parent));
        }

        @Override
        public String pageId() {
            return "ipei0ew";
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
        public boolean isOptionsScreen() {
            return false;
        }

        @Override
        protected boolean shouldRenderTitleText() {
            return true;
        }
    }

    public static class MicrolensingScreen extends AbstractModScreen {
        private ButtonWidget bastionsButton, fortressesButton;

        public MicrolensingScreen(Screen parent) {
            super(parent, Text.translatable("speedrunnermod.title.resources.tutorials.microlensing"));
        }

        @Override
        protected List<ClickableWidget> buttons() {
            return List.of(
                   this.bastionsButton,
                   this.fortressesButton
            );
        }

        @Override
        protected void init() {
            this.bastionsButton = ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.microlensing.bastion"), (button) -> {
                this.openLink("https://www.youtube.com/watch?v=jvTfMLPnMSw", false);
            }).build();

            this.fortressesButton = ButtonWidget.builder(Text.translatable("speedrunnermod.menu.resources.tutorials.microlensing.fortress"), (button) -> {
                this.openLink("https://www.youtube.com/watch?v=Kl_-L9XbJko", false);
            }).build();

            super.init();
        }

        @Override
        public void close() {
            this.client.setScreen(new TutorialsScreen(this.parent));
        }

        @Override
        public String pageId() {
            return "gfiioads";
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
        public boolean isOptionsScreen() {
            return false;
        }

        @Override
        protected boolean shouldRenderTitleText() {
            return true;
        }
    }
}