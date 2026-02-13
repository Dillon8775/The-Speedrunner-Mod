package net.dillon.speedrunnermod.client.screen;

import net.dillon.speedrunnermod.screen.WorkbenchScreenHandler;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.CyclingSlotIcon;
import net.minecraft.client.gui.screen.ingame.ForgingScreen;
import net.minecraft.client.input.KeyInput;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;
import static net.minecraft.util.Identifier.ofVanilla;

/**
 * The base screen for the {@code Speedrunner's Workbench.}
 */
public class WorkbenchScreen extends ForgingScreen<WorkbenchScreenHandler> {
    private static final Identifier ERROR_TEXTURE = ofVanilla("container/anvil/error");
    private static final Identifier TEXTURE = ofSpeedrunnerMod("textures/gui/container/workbench.png");
    private static final Identifier SMITHING_TEMPLATE = ofVanilla("container/slot/smithing_template_netherite_upgrade");
    private final PlayerEntity player;
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
            ofSpeedrunnerMod("container/slot/book"),
            slotTexture("axe"),
            slotTexture("boots"),
            slotTexture("sword"),
            slotTexture("pickaxe")
    );
    private static final List<Identifier> GOLD_UPGRADE_SLOT_TEXTURES = List.of(
            slotTexture("ingot"),
            ofSpeedrunnerMod("container/slot/book"),
            slotTexture("axe")
    );
    private static final List<Identifier> OUTPUT_SLOT_TEXTURES = List.of(
            ofSpeedrunnerMod("container/slot/enchanted_book")
    );
    private final CyclingSlotIcon inputSlotIcon = new CyclingSlotIcon(this.handler.getInputSlot().id);
    private final CyclingSlotIcon transferToSlotIcon = new CyclingSlotIcon(this.handler.getTransferToSlot().id);
    private final CyclingSlotIcon outputSlotTextures = new CyclingSlotIcon(this.handler.getOutputSlot().id);

    public WorkbenchScreen(WorkbenchScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title, TEXTURE);
        this.titleX = 40;
        this.titleY = 15;
        this.player = inventory.player;
    }

    /**
     * @return the Identifier pointing to a slot texture.
     */
    private static Identifier slotTexture(String name) {
        return ofVanilla("container/slot/" + name);
    }

    /**
     * Handles animations on slots.
     */
    @Override
    public void handledScreenTick() {
        this.inputSlotIcon.updateTexture(SLOT_TEXTURES);
        this.transferToSlotIcon.updateTexture(
                this.handler.getInputSlot().getStack().isIn(ModItemTags.UPGRADEABLE_GOLD)
                        ? GOLD_UPGRADE_SLOT_TEXTURES
                        : TRANSFER_SLOT_TEXTURES
        );
        this.outputSlotTextures.updateTexture(OUTPUT_SLOT_TEXTURES);
        super.handledScreenTick();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.inputSlotIcon.render(this.handler, context, delta, this.x, this.y);
        this.outputSlotTextures.render(this.handler, context, delta, this.x, this.y);
        if (this.handler.getInputSlot().hasStack()) {
            this.transferToSlotIcon.render(this.handler, context, delta, this.x, this.y);
        }
        super.render(context, mouseX, mouseY, delta);
    }

    /**
     * Close the handled screen when pressing escape on keyboard.
     */
    @Override
    public boolean keyPressed(KeyInput input) {
        if (input.key() == GLFW.GLFW_KEY_ESCAPE) {
            this.client.player.closeHandledScreen();
        }

        return super.keyPressed(input);
    }

    /**
     * Copied over from {@link AnvilScreenHandler}.
     * <p>Draws the foreground.</p>
     */
    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        super.drawForeground(context, mouseX, mouseY);
        context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, SMITHING_TEMPLATE, this.handler.getSmithingTemplateSlot().x, this.handler.getSmithingTemplateSlot().y, 16, 16);
        if (this.handler.getTransferToSlot().getStack().isOf(Items.BOOK)) {
            int color = -1275068416;
            Slot slot = this.handler.getSmithingTemplateSlot();
            context.fillGradient(slot.x, slot.y, slot.x + 16, slot.y + 16, color, color);
        }
        int i = this.handler.getLevelCost();
        if (i > 0) {
            int j = -8323296; // green
            Text text;
            if (!this.handler.getSlot(this.handler.getOutputSlot().id).hasStack()) {
                text = null;
            } else {
                text = Text.translatable("block.speedrunnermod.speedrunners_workbench.cost", i);
                if (!this.handler.getSlot(this.handler.getOutputSlot().id).canTakeItems(this.player)) {
                    j = -40864; // red
                }
            }

            if (text != null) {
                int k = this.backgroundWidth - 8 - this.textRenderer.getWidth(text) - 2;
                context.fill(k - 2, 67, this.backgroundWidth - 8, 79, 1325400064);
                context.drawTextWithShadow(this.textRenderer, text, k, 69, j);
            }
        }
    }

    /**
     * Draws the red arrow texture when something doesn't go righ.t
     */
    @Override
    protected void drawInvalidRecipeArrow(DrawContext context, int x, int y) {
        if ((this.handler.getSlot(this.handler.getInputSlot().id).hasStack() && this.handler.getSlot(this.handler.getTransferToSlot().id).hasStack()) && !this.handler.getSlot(this.handler.getResultSlotIndex()).hasStack()) {
            context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, ERROR_TEXTURE, x + 99, y + 35, 28, 21);
        }
    }

    @Override
    public void resize(int width, int height) {
        this.init(width, height);
    }
}