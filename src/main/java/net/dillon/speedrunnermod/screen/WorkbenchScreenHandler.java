package net.dillon.speedrunnermod.screen;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
import net.dillon.speedrunnermod.util.ModUtil;
import net.dillon.speedrunnermod.util.TutorialMode;
import net.minecraft.block.BlockState;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.Property;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.ForgingSlotsManager;
import net.minecraft.sound.SoundEvents;

import java.util.ArrayList;
import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * Screen and enchantment transferring handling for the {@code Speedrunner's Workbench.}
 */
public class WorkbenchScreenHandler extends ForgingScreenHandler implements TutorialMode {
    private final Property levelCost = Property.create();
    private List<RegistryEntry> enchantmentsToRemove = new ArrayList<>();
    private List<Object2IntMap.Entry<RegistryEntry<Enchantment>>> enchantmentsToTransfer = new ArrayList<>();

    /**
     * Constructor for registering this screen handler.
     */
    public WorkbenchScreenHandler(int syncId, PlayerInventory inventory) {
        this(syncId, inventory, ScreenHandlerContext.EMPTY);
    }

    /**
     * Base constructor.
     */
    public WorkbenchScreenHandler(int syncId, PlayerInventory inventory, ScreenHandlerContext context) {
        super(ModScreenHandlerTypes.WORKBENCH, syncId, inventory, context, getForgingSlotsManager());
        this.addProperty(this.levelCost);
    }

    /**
     * Copied over from {@link AnvilScreenHandler}. Sets the slot's position on the screen.
     */
    private static ForgingSlotsManager getForgingSlotsManager() {
        return ForgingSlotsManager.builder().input(0, 27, 47, stack -> true).input(1, 76, 47, stack -> true).output(2, 134, 47).build();
    }

    /**
     * This screen can only be opened with a {@code Speedrunner's Workbench block.}
     */
    @Override
    protected boolean canUse(BlockState state) {
        return state.isOf(ModBlocks.SPEEDRUNNERS_WORKBENCH);
    }

    /**
     * Determines if the player can take the outputted item.
     * <p>In this case, if the player is in creative mode or if they have enough levels.</p>
     */
    @Override
    protected boolean canTakeOutput(PlayerEntity player, boolean present) {
        return (player.isInCreativeMode() || player.experienceLevel >= this.levelCost.get()) && this.levelCost.get() > 0;
    }

    /**
     * Refresh the slots and give the player the item.
     */
    @ChatGPT(Credit.PARTIAL_CREDIT)
    @Override
    public void onTakeOutput(PlayerEntity player, ItemStack stack) {
        if (!player.getAbilities().creativeMode) {
            player.addExperienceLevels(-this.levelCost.get());
        }

        ItemStack newSlot1 = this.input.getStack(0);
        // Remove the enchantment from the main hand item if it was transferred/upgraded to the offhand
        for (RegistryEntry registryEntry : this.enchantmentsToRemove) {
            EnchantmentHelper.apply(newSlot1, builder -> builder.remove(enchantmentRegistryEntry -> enchantmentRegistryEntry.equals(registryEntry)));
        }
        this.input.setStack(0, newSlot1);
        this.input.setStack(1, ItemStack.EMPTY);
        this.success(player);
    }

    /**
     * Handles transferring enchantments.
     * <p>See additional comments inside of this method for more documentation.</p>
     */
    @ChatGPT(Credit.PARTIAL_CREDIT)
    @Override
    public void updateResult() {
        ItemStack slot1 = this.input.getStack(0); // Get the players main hand stack
        ItemStack slot2 = this.input.getStack(1); // Get the players offhand stack
        ItemEnchantmentsComponent slot1Enchantments = EnchantmentHelper.getEnchantments(slot1); // Get the enchantments on the players main hand item, using an item enchantments component
        ItemEnchantmentsComponent slot2Enchantments = EnchantmentHelper.getEnchantments(slot2); // Get the enchantments on the players offhand item, using an item enchantments component
        ItemEnchantmentsComponent.Builder slot1Builder = new ItemEnchantmentsComponent.Builder(slot1Enchantments); // An item enchantments component builder for the players main hand enchantments
        ItemEnchantmentsComponent.Builder slot2Builder = new ItemEnchantmentsComponent.Builder(slot2Enchantments); // An item enchantments component builder for the players offhand enchantments

        this.output.setStack(0, ItemStack.EMPTY); // Set the output initially to nothing
        this.levelCost.set(0); // Reset level cost

        if (slot1.isEmpty() || slot2.isEmpty()) { // if slot 1 or 2 is empty make sure nothing is returned
            return;
        }

        ItemEnchantmentsComponent.Builder outputBuilder = new ItemEnchantmentsComponent.Builder(slot2Enchantments);
        int totalTransferred = 0; // The total amount of enchantments successfully transferred

        // Run through all main hand enchantments
        for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : slot1Enchantments.getEnchantmentEntries()) {
            RegistryEntry registryEntry = entry.getKey();
            Enchantment enchantment = (Enchantment)registryEntry.value();

            if (!slot2.hasEnchantments()) { // If the players offhand item has no enchantments, "successWithNoEnchantments" returns true, and enchantments are transferred
                totalTransferred++;
                this.levelCost.set( this.initializedCost(player, slot1Builder, entry, totalTransferred));
                if (totalTransferred != 0 && enchantment.isAcceptableItem(slot2)) {
                    this.enchantmentsToTransfer.add(entry);
                    this.enchantmentsToRemove.add(registryEntry);
                    this.sendContentUpdates();
                }
            } else { // Otherwise, start running through all offhand enchantments
                boolean allIsCompatible = true;
                for (RegistryEntry<Enchantment> registryEntry2 : slot2Builder.getEnchantments()) {

                    // Compare main hand and offhand enchantments and determine if they are compatible
                    for (RegistryEntry<Enchantment> existingEnchantment : slot2Builder.getEnchantments()) {
                        if (!Enchantment.canBeCombined(existingEnchantment, registryEntry) && !registryEntry2.equals(registryEntry)) {
                            allIsCompatible = false;
                            break;
                        }
                    }

                    // Determines if an enchantment on the offhand can be upgraded to a higher level
                    boolean canUpgrade = registryEntry2.equals(registryEntry) && slot2Builder.getLevel(registryEntry2) < slot1Builder.getLevel(registryEntry);

                    // If all enchantments are compatible with each other and can be combined,
                    // "successWithEnchantments" returns true, and enchantments are transferred
                    if (allIsCompatible && Enchantment.canBeCombined(registryEntry, registryEntry2) && enchantment.isAcceptableItem(slot2) || canUpgrade) {

                        totalTransferred++;
                        this.levelCost.set(this.initializedCost(this.player, slot1Builder, entry, totalTransferred));

                        if (totalTransferred != 0) {
                            this.enchantmentsToTransfer.add(entry);
                            this.enchantmentsToRemove.add(entry.getKey());
                            this.sendContentUpdates();
                        }
                    }
                }
            }
        }

        // Applies the transferred enchantments to the output item.
        if (totalTransferred > 0) {
            ItemStack output = slot2.copy();
            for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : this.enchantmentsToTransfer) {
                int slot1Level = slot1Builder.getLevel(entry.getKey());
                int slot2Level = slot2Builder.getLevel(entry.getKey());

                // Check if the offhand already has the enchantment
                if (slot2Level > 0) {
                    // If offhand has a lower level, upgrade it
                    if (slot2Level < slot1Level) {
                        EnchantmentHelper.apply(output, builder -> builder.add(entry.getKey(), slot1Level));
                    }
                    // No further action needed if the levels are equal or offhand has a higher level
                } else {
                    // If the offhand does not have the enchantment, transfer it
                    EnchantmentHelper.apply(output, builder -> builder.add(entry.getKey(), slot1Level));
                }
            }
            this.output.setStack(0, output);
        }
    }

    /**
     * Corrects the {@code cost} variable to equal the total amount of enchantments transferred multiplied by itself.
     */
    private int initializedCost(PlayerEntity player, ItemEnchantmentsComponent.Builder enchantmentLevel, Object2IntMap.Entry<RegistryEntry<Enchantment>> entry, int totalTransferred) {
        int cost = ModUtil.multiplyEnchantments(enchantmentLevel, entry, totalTransferred);

        if (cost > options().main.anvilCostLimit && options().main.anvilCostLimit != 50) {
            cost = options().main.anvilCostLimit;
        }

        if (player.getAbilities().creativeMode) {
            cost = 0;
        }

        return cost;
    }

    /**
     * A successful enchantment transfer.
     */
    private void success(PlayerEntity player) {
        player.playSound(SoundEvents.BLOCK_SMITHING_TABLE_USE, 1.0F, this.player.getRandom().nextFloat() * 0.1F + 0.9F);
        player.addExperienceLevels(this.levelCost.get());
        if (options().main.tutorialMode && options().tutorialMode.killedDragon && options().tutorialMode.brokenExperienceOre && options().tutorialMode.obtainedSpeedrunnersWorkbench && !options().tutorialMode.transferedEnchantments) {
            this.send("speedrunnermod.tutorial_mode.transferred_enchantments", player);
            this.playDing(player);
            options().tutorialMode.transferedEnchantments = true;
            ModOptions.saveConfig();
        }
    }

    /**
     * Returns the current level cost.
     */
    public int getLevelCost() {
        return this.levelCost.get();
    }
}