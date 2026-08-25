package net.dillon.speedrunnermod.screen;

import com.mojang.blaze3d.platform.InputConstants;
import net.dillon.dillonlib.util.CommonSprites;
import net.dillon.speedrunnermod.menu.WorkbenchMenu;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.CyclingSlotBackground;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.Optional;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;
import static net.minecraft.resources.Identifier.withDefaultNamespace;

/**
 * The base screen for the {@code Speedrunner's Workbench.}
 */
public class WorkbenchScreen extends ItemCombinerScreen<WorkbenchMenu> {
    private static final Identifier ERROR_TEXTURE = withDefaultNamespace("container/anvil/error");
    private static final Identifier TEXTURE = ofSpeedrunnerMod("textures/gui/container/workbench.png");
    private static final Identifier SMITHING_TEMPLATE = withDefaultNamespace("container/slot/smithing_template_netherite_upgrade");
    private final Player player;
    private static final List<Identifier> SLOT_TEXTURES = List.of(
            slotTexture("axe"),
            slotTexture("boots"),
            slotTexture("chestplate"),
            slotTexture("sword"),
            slotTexture("hoe"),
            slotTexture("pickaxe"),
            slotTexture("leggings"),
            slotTexture("shovel"),
            slotTexture("spear")
    );
    private static final List<Identifier> TRANSFER_SLOT_TEXTURES = List.of(
            CommonSprites.BOOK,
            slotTexture("axe"),
            slotTexture("boots"),
            slotTexture("sword"),
            slotTexture("pickaxe")
    );
    private static final List<Identifier> GOLD_UPGRADE_SLOT_TEXTURES = List.of(
            slotTexture("ingot"),
            CommonSprites.BOOK,
            slotTexture("axe")
    );
    private static final List<Identifier> OUTPUT_SLOT_TEXTURES = List.of(
            CommonSprites.ENCHANTED_BOOK
    );
    private final CyclingSlotBackground inputSlotIcon = new CyclingSlotBackground(this.menu.getInputSlot().index);
    private final CyclingSlotBackground transferToSlotIcon = new CyclingSlotBackground(this.menu.getTransferToSlot().index);
    private final CyclingSlotBackground outputSlotTextures = new CyclingSlotBackground(this.menu.getOutputSlot().index);

    public WorkbenchScreen(WorkbenchMenu handler, Inventory inventory, Component title) {
        super(handler, inventory, title, TEXTURE);
        this.titleLabelX = 40;
        this.titleLabelY = 15;
        this.player = inventory.player;
    }

    /**
     * @return the Identifier pointing to a slot texture.
     */
    private static Identifier slotTexture(String name) {
        return withDefaultNamespace("container/slot/" + name);
    }

    /**
     * Handles animations on slots.
     */
    @Override
    public void containerTick() {
        this.inputSlotIcon.tick(SLOT_TEXTURES);
        this.transferToSlotIcon.tick(
                this.menu.getInputSlot().getItem().is(ModItemTags.UPGRADEABLE_GOLD)
                        ? GOLD_UPGRADE_SLOT_TEXTURES
                        : TRANSFER_SLOT_TEXTURES
        );
        this.outputSlotTextures.tick(OUTPUT_SLOT_TEXTURES);
        super.containerTick();
    }

    /**
     * Renders helpful tooltips on enchantment transferrer slots.
     */
    private void renderSlotTooltip(GuiGraphicsExtractor context, int mouseX, int mouseY) {
        Optional<Component> optional = Optional.empty();

        if (this.hoveredSlot != null) {
            ItemStack focusedSlotStack = this.hoveredSlot.getItem();
            if (focusedSlotStack.isEmpty()) {
                switch (this.hoveredSlot.index) {
                    case 0 -> optional = Optional.of(Component.translatable("block.speedrunnermod.speedrunners_workbench.enchanted_tool").withStyle(ChatFormatting.AQUA));
                    case 1 -> optional = Optional.of(Component.translatable("block.speedrunnermod.speedrunners_workbench.unenchanted_tool_or_book").withStyle(ChatFormatting.LIGHT_PURPLE));
                    case 2 -> optional = Optional.of(Component.translatable("block.speedrunnermod.speedrunners_workbench.smithing_template"));
                }
            }
        }

        optional.ifPresent(text -> context.setTooltipForNextFrame(this.font, this.font.split(text, 115), mouseX, mouseY));
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor context, int mouseX, int mouseY, float delta) {
        this.inputSlotIcon.extractRenderState(this.menu, context, delta, this.leftPos, this.topPos);
        this.outputSlotTextures.extractRenderState(this.menu, context, delta, this.leftPos, this.topPos);
        if (this.menu.getInputSlot().hasItem()) {
            this.transferToSlotIcon.extractRenderState(this.menu, context, delta, this.leftPos, this.topPos);
        }
        this.renderSlotTooltip(context, mouseX, mouseY);
        super.extractRenderState(context, mouseX, mouseY, delta);
    }

    /**
     * Close the handled screen when pressing escape on keyboard.
     */
    @Override
    public boolean keyPressed(KeyEvent input) {
        if (input.key() == InputConstants.KEY_ESCAPE) {
            this.minecraft.player.closeContainer();
        }

        return super.keyPressed(input);
    }

    /**
     * Copied over from {@link AnvilMenu}.
     * <p>Draws the foreground.</p>
     */
    @Override
    protected void extractLabels(GuiGraphicsExtractor context, int mouseX, int mouseY) {
        super.extractLabels(context, mouseX, mouseY);
        context.blitSprite(RenderPipelines.GUI_TEXTURED, SMITHING_TEMPLATE, this.menu.getSmithingTemplateSlot().x, this.menu.getSmithingTemplateSlot().y, 16, 16);
        if (this.menu.getTransferToSlot().getItem().is(Items.BOOK)) {
            int color = -1275068416;
            Slot slot = this.menu.getSmithingTemplateSlot();
            context.fillGradient(slot.x, slot.y, slot.x + 16, slot.y + 16, color, color);
        }
        int i = this.menu.getLevelCost();
        if (i > 0) {
            int j = -8323296; // green
            Component text;
            if (!this.menu.getSlot(this.menu.getOutputSlot().index).hasItem()) {
                text = null;
            } else {
                text = Component.translatable("block.speedrunnermod.speedrunners_workbench.cost", i);
                if (!this.menu.getSlot(this.menu.getOutputSlot().index).mayPickup(this.player)) {
                    j = -40864; // red
                }
            }

            if (text != null) {
                int k = this.imageWidth - 8 - this.font.width(text) - 2;
                context.fill(k - 2, 67, this.imageWidth - 8, 79, 1325400064);
                context.text(this.font, text, k, 69, j);
            }
        }
    }

    /**
     * Draws the red arrow texture when something doesn't go righ.t
     */
    @Override
    protected void extractErrorIcon(GuiGraphicsExtractor context, int x, int y) {
        if ((this.menu.getSlot(this.menu.getInputSlot().index).hasItem() && this.menu.getSlot(this.menu.getTransferToSlot().index).hasItem()) && !this.menu.getSlot(this.menu.getResultSlot()).hasItem()) {
            context.blitSprite(RenderPipelines.GUI_TEXTURED, ERROR_TEXTURE, x + 99, y + 35, 28, 21);
        }
    }

    @Override
    public void resize(int width, int height) {
        this.init(width, height);
    }
}