package net.dillon.speedrunnermod.screen;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
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

import java.util.HashMap;
import java.util.Map;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * Screen and enchantment transferring handling for the {@code Speedrunner's Workbench.}
 */
public class WorkbenchScreenHandler extends ForgingScreenHandler implements TutorialMode {
    private final Property levelCost = Property.create(); // Level cost variable
    private final Map<RegistryEntry, Integer> enchantmentsToRemove = new HashMap<>(); // List of enchantments to remove from the item, with their respective level
    private final Map<Object2IntMap.Entry<RegistryEntry<Enchantment>>, Integer> enchantmentsToTransfer = new HashMap<>(); // List of enchantments to transfer over, with their respective level (mapped)

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
        for (RegistryEntry registryEntry : enchantmentsToRemove.keySet()) {
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
        ItemStack firstSlot = this.input.getStack(0); // Get the stack in the first slot
        ItemStack secondSlot = this.input.getStack(1); // Get the stack in the second slot
        ItemEnchantmentsComponent slot1Enchantments = EnchantmentHelper.getEnchantments(firstSlot); // Enchantments on first slot stack
        ItemEnchantmentsComponent slot2Enchantments = EnchantmentHelper.getEnchantments(secondSlot); // Enchantments on second slot stack
        ItemEnchantmentsComponent.Builder firstSlotBuilder = new ItemEnchantmentsComponent.Builder(slot1Enchantments); // Build enchantments component on first slot
        ItemEnchantmentsComponent.Builder secondSlotBuilder = new ItemEnchantmentsComponent.Builder(slot2Enchantments); // Build enchantments component on second slot

        this.output.setStack(0, ItemStack.EMPTY); // Reset the output initially to nothing
        this.levelCost.set(0); // Reset the level cost
        this.enchantmentsToTransfer.clear(); // Reset enchantments to transfer
        this.enchantmentsToRemove.clear(); // Reset enchantments to remove

        // If slot 1 or 2 is empty make sure nothing is returned
        if (firstSlot.isEmpty() || secondSlot.isEmpty()) {
            return;
        }

        // Run through all enchantments in the first slot
        for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : slot1Enchantments.getEnchantmentEntries()) {
            RegistryEntry registryEntry = entry.getKey();
            Enchantment enchantment = (Enchantment)registryEntry.value();

            // If second slot has no enchantments, and the enchantment wanting to be transferred is acceptable, transfer the enchantment
            if (!secondSlot.hasEnchantments() && enchantment.isAcceptableItem(secondSlot)) {
                enchantmentsToTransfer.put(entry, firstSlotBuilder.getLevel(entry.getKey()));
                enchantmentsToRemove.put(entry.getKey(), firstSlotBuilder.getLevel(entry.getKey()));
                this.sendContentUpdates();
            } else { // Otherwise, start running through all second slot enchantments to determine acceptability
                boolean allIsCompatible = true; // All enchantments are compatible
                for (RegistryEntry<Enchantment> registryEntry2 : secondSlotBuilder.getEnchantments()) {

                    // Compare first second and second slot enchantments and determine if they are compatible with each other
                    for (RegistryEntry<Enchantment> existingEnchantment : secondSlotBuilder.getEnchantments()) {
                        if (!Enchantment.canBeCombined(existingEnchantment, registryEntry) && !registryEntry2.equals(registryEntry)) {
                            allIsCompatible = false; // If not, not all enchantments are compatible
                            break; // Break out of the loop, no further action needed here
                        }
                    }

                    // Determines if an enchantment in second slot can be upgraded to a higher level
                    boolean alreadyPresentButUpgradable = registryEntry2.equals(registryEntry) && secondSlotBuilder.getLevel(registryEntry2) < firstSlotBuilder.getLevel(registryEntry);

                    // If all enchantments are compatible with each other and can be combined, OR can be upgraded
                    // Try to transfer enchantments
                    if ((allIsCompatible && enchantment.isAcceptableItem(secondSlot)) || alreadyPresentButUpgradable) {
                        // However, if second slot enchantment is already at max level, don't transfer enchantment, we cannot transfer enchantment over max level
                        if (secondSlotBuilder.getLevel(entry.getKey()) != enchantment.getMaxLevel()) {
                            enchantmentsToTransfer.put(entry, firstSlotBuilder.getLevel(entry.getKey()));
                            enchantmentsToRemove.put(entry.getKey(), firstSlotBuilder.getLevel(entry.getKey()));
                            this.sendContentUpdates();
                        }
                    }
                }
            }
        }

        // Applies the transferred enchantments to the output item.
        ItemStack output = secondSlot.copy(); // Copy second slot stack
        // Run through all enchantments to transfer
        for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : enchantmentsToTransfer.keySet()) {
            int firstSlotLevel = firstSlotBuilder.getLevel(entry.getKey());
            int secondSlotLevel = secondSlotBuilder.getLevel(entry.getKey());

            // Check if second slot already has the enchantment
            if (secondSlotLevel > 0) {
                // If second slot has a lower level, upgrade it
                if (secondSlotLevel < firstSlotLevel) {
                    EnchantmentHelper.apply(output, builder -> builder.add(entry.getKey(), firstSlotLevel));
                }
                // No further action needed if the levels are equal or second slot has a higher level
            } else {
                // If second slot does not have the enchantment, transfer it
                EnchantmentHelper.apply(output, builder -> builder.add(entry.getKey(), firstSlotLevel));
            }
        }
        // Total transferred enchantments equals the number of enchantments to transfer (map cannot contain duplicates, so the size is correct)
        int totalTransferredEnchantments = enchantmentsToTransfer.size();
        int cost = 0; // Cost variable (initially set to 0).
        if (totalTransferredEnchantments > 0) { // as long as at least one enchantment is transferred...
            cost += totalTransferredEnchantments; // set cost to total transferred enchantments
            // For each enchantment, get the enchantment level, and add it to cost
            for (Map.Entry<Object2IntMap.Entry<RegistryEntry<Enchantment>>, Integer> entry : enchantmentsToTransfer.entrySet()) {
                cost += entry.getValue(); // cost = (totalTransferredEnchantments + (eachEnchantmentsLevel))
            }
            this.output.setStack(0, output); // Set the output
            this.levelCost.set(cost); // Set the cost
        }
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