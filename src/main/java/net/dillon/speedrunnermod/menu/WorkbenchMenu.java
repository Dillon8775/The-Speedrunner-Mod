package net.dillon.speedrunnermod.menu;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.dillon.speedrunnermod.advancement.ModPredicates;
import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;

/**
 * Screen and enchantment transferring handling for the {@code Speedrunner's Workbench.}
 */
public class WorkbenchMenu extends ItemCombinerMenu {
    private final DataSlot levelCost = DataSlot.standalone(); // Level cost variable
    private final Map<Holder, Integer> enchantmentsToRemove = new HashMap<>(); // List of enchantments to remove from the item, with their respective level
    private final Map<Object2IntMap.Entry<Holder<Enchantment>>, Integer> enchantmentsToTransfer = new HashMap<>(); // List of enchantments to transfer over, with their respective level (mapped)

    /**
     * Constructor for registering this screen handler.
     */
    public WorkbenchMenu(int syncId, Inventory inventory) {
        this(syncId, inventory, ContainerLevelAccess.NULL);
    }

    /**
     * Base constructor.
     */
    public WorkbenchMenu(int syncId, Inventory inventory, ContainerLevelAccess context) {
        super(ModMenus.WORKBENCH, syncId, inventory, context, getForgingSlotsManager());
        this.addDataSlot(this.levelCost);
    }

    /**
     * Copied over from {@link AnvilMenu}. Sets the slot's position on the screen.
     */
    private static ItemCombinerMenuSlotDefinition getForgingSlotsManager() {
        return ItemCombinerMenuSlotDefinition.create()
                .withSlot(0, 27, 37, stack -> stack.is(ModItemTags.SPEEDRUNNERS_WORKBENCH_UPGRADEABLE))
                .withSlot(1, 76, 37, stack -> stack.is(ModItemTags.SPEEDRUNNERS_WORKBENCH_CONVERTABLE))
                .withSlot(2, 76, 60, stack -> stack.is(ModItems.GOLDEN_UPGRADE_SMITHING_TEMPLATE))
                .withResultSlot(3, 134, 37).build();
    }

    /**
     * This screen can only be opened with a {@code Speedrunner's Workbench block.}
     */
    @Override
    protected boolean isValidBlock(BlockState state) {
        return state.is(ModBlocks.SPEEDRUNNERS_WORKBENCH);
    }

    /**
     * Determines if the player can take the outputted item.
     * <p>In this case, if the player is in creative mode or if they have enough levels.</p>
     */
    @Override
    protected boolean mayPickup(Player player, boolean present) {
        return (player.hasInfiniteMaterials() || player.experienceLevel >= this.levelCost.get());
    }

    /**
     * Refresh the slots and give the player the item.
     */
    @Override
    public void onTake(Player player, ItemStack stack) {
        if (!player.getAbilities().instabuild) {
            player.giveExperienceLevels(-this.levelCost.get());
        }

        ItemStack newSlot1 = this.inputSlots.getItem(this.getInputSlot().index);
        // Remove the enchantment from the main hand item if it was transferred/upgraded to the offhand
        for (Holder registryEntry : this.enchantmentsToRemove.keySet()) {
            EnchantmentHelper.updateEnchantments(newSlot1, builder -> builder.removeIf(enchantmentRegistryEntry -> enchantmentRegistryEntry.equals(registryEntry)));
        }
        this.inputSlots.setItem(this.getInputSlot().index, newSlot1);
        boolean ingot = this.inputSlots.getItem(this.getTransferToSlot().index).is(ModItems.SPEEDRUNNER_INGOT) && this.inputSlots.getItem(this.getInputSlot().index).is(ModItemTags.UPGRADEABLE_GOLD);
        boolean upgraded = false;
        if (ingot) {
            this.inputSlots.setItem(this.getInputSlot().index, ItemStack.EMPTY);
            upgraded = true;
        }
        if (this.inputSlots.getItem(this.getTransferToSlot().index).is(Items.BOOK) || ingot) {
            this.inputSlots.setItem(this.getTransferToSlot().index, this.decrementedStack(this.inputSlots.getItem(this.getTransferToSlot().index).copy()));
            if (ingot) {
                this.inputSlots.setItem(this.getSmithingTemplateSlot().index, this.decrementedStack(this.inputSlots.getItem(this.getSmithingTemplateSlot().index).copy()));
            }
        } else {
            this.inputSlots.setItem(this.getTransferToSlot().index, ItemStack.EMPTY);
            this.inputSlots.setItem(this.getSmithingTemplateSlot().index, this.decrementedStack(this.inputSlots.getItem(this.getSmithingTemplateSlot().index).copy()));
        }
        this.success(player, upgraded);
    }

    /**
     * Handles transferring enchantments.
     * <p>See additional comments inside of this method for more documentation.</p>
     */
    @Override
    public void createResult() {
        ItemStack firstSlot = this.inputSlots.getItem(this.getInputSlot().index); // Get the stack in the first slot
        ItemStack secondSlot = this.inputSlots.getItem(this.getTransferToSlot().index); // Get the stack in the second slot
        ItemEnchantments slot1Enchantments = EnchantmentHelper.getEnchantmentsForCrafting(firstSlot); // Enchantments on first slot stack
        ItemEnchantments slot2Enchantments = EnchantmentHelper.getEnchantmentsForCrafting(secondSlot); // Enchantments on second slot stack
        ItemEnchantments.Mutable firstSlotBuilder = new ItemEnchantments.Mutable(slot1Enchantments); // Build enchantments component on first slot
        ItemEnchantments.Mutable secondSlotBuilder = new ItemEnchantments.Mutable(slot2Enchantments); // Build enchantments component on second slot

        this.resultSlots.setItem(0, ItemStack.EMPTY); // Reset the output initially to nothing
        this.levelCost.set(0); // Reset the level cost
        this.enchantmentsToTransfer.clear(); // Reset enchantments to transfer
        this.enchantmentsToRemove.clear(); // Reset enchantments to remove

        // If slot 1 or 2 is empty, make sure nothing is returned
        if (firstSlot.isEmpty() || secondSlot.isEmpty()) {
            return;
        }

        // Run through all enchantments in the first slot
        for (Object2IntMap.Entry<Holder<Enchantment>> entry : slot1Enchantments.entrySet()) {
            Holder registryEntry = entry.getKey();
            Enchantment enchantment = (Enchantment)registryEntry.value();

            // If second slot has no enchantments, and the enchantment wanting to be transferred is acceptable, transfer the enchantment
            if (!secondSlot.isEnchanted() && enchantment.canEnchant(secondSlot) || secondSlot.is(Items.BOOK)) {
                enchantmentsToTransfer.put(entry, firstSlotBuilder.getLevel(entry.getKey()));
                enchantmentsToRemove.put(entry.getKey(), firstSlotBuilder.getLevel(entry.getKey()));
                this.broadcastChanges();
            } else { // Otherwise, start running through all second slot enchantments to determine acceptability
                boolean allIsCompatible = true; // All enchantments are compatible
                for (Holder<Enchantment> registryEntry2 : secondSlotBuilder.keySet()) {

                    // Compare first second and second slot enchantments and determine if they are compatible with each other
                    for (Holder<Enchantment> existingEnchantment : secondSlotBuilder.keySet()) {
                        if (!Enchantment.areCompatible(existingEnchantment, registryEntry) && !registryEntry2.equals(registryEntry)) {
                            allIsCompatible = false; // If not, not all enchantments are compatible
                            break; // Break out of the loop, no further action needed here
                        }
                    }

                    // Determines if an enchantment in second slot can be upgraded to a higher level
                    boolean alreadyPresentButUpgradable = registryEntry2.equals(registryEntry) && secondSlotBuilder.getLevel(registryEntry2) <= firstSlotBuilder.getLevel(registryEntry);

                    // If all enchantments are compatible with each other and can be combined, OR can be upgraded
                    // Try to transfer enchantments
                    if ((allIsCompatible && enchantment.canEnchant(secondSlot)) || alreadyPresentButUpgradable) {
                        int slotBuilder = firstSlotBuilder.getLevel(entry.getKey());
                        if (!(slotBuilder + 1 > 10) && secondSlotBuilder.getLevel(entry.getKey()) <= firstSlotBuilder.getLevel(entry.getKey())) {
                            enchantmentsToTransfer.put(entry, secondSlotBuilder.getLevel(entry.getKey()) == slotBuilder
                                    ? slotBuilder + 1 : slotBuilder);
                            enchantmentsToRemove.put(entry.getKey(), firstSlotBuilder.getLevel(entry.getKey()));
                            this.broadcastChanges();
                        }
                    }
                }
            }
        }

        // Applies the transferred enchantments to the output item.
        ItemStack output = secondSlot.copy(); // Copy second slot stack
        if (secondSlot.is(Items.BOOK)) { // Make output enchanted book if transferring enchantments to a book
            output = new ItemStack(Items.ENCHANTED_BOOK);
        }
        // Run through all enchantments to transfer
        for (Object2IntMap.Entry<Holder<Enchantment>> entry : enchantmentsToTransfer.keySet()) {
            int firstSlotLevel = firstSlotBuilder.getLevel(entry.getKey());
            int secondSlotLevel = secondSlotBuilder.getLevel(entry.getKey());

            // Check if second slot already has the enchantment
            if (secondSlotLevel > 0) {
                // If second slot has a lower level, upgrade it
                if (secondSlotLevel <= firstSlotLevel) {
                    EnchantmentHelper.updateEnchantments(output, builder -> builder.upgrade(entry.getKey(),
                            secondSlotLevel == firstSlotLevel ? firstSlotLevel + 1 : firstSlotLevel));
                }
                // No further action needed if the levels are equal or second slot has a higher level
            } else {
                // If second slot does not have the enchantment, transfer it
                EnchantmentHelper.updateEnchantments(output, builder -> builder.upgrade(entry.getKey(), firstSlotLevel));
            }
        }
        // Total transferred enchantments equals the number of enchantments to transfer (map cannot contain duplicates, so the size is correct)
        int totalTransferredEnchantments = enchantmentsToTransfer.size();
        int cost = 0; // Cost variable (initially set to 0).
        double outputDurability = output.getMaxDamage() - output.getDamageValue(); // New outputDurability amount
        if (totalTransferredEnchantments > 0) { // as long as at least one enchantment is transferred...
            cost += totalTransferredEnchantments; // set cost to total transferred enchantments
            // For each enchantment, get the enchantment level, and add it to cost
            // Additionally, divide output durability by (1.0 + (each enchantment level * 0.1))
            for (Map.Entry<Object2IntMap.Entry<Holder<Enchantment>>, Integer> entry : enchantmentsToTransfer.entrySet()) {
                cost += entry.getValue(); // cost = (totalTransferredEnchantments + (eachEnchantmentsLevel))
                outputDurability /= 1.0 + (entry.getValue() * 0.1); // outputDurability = (1.0 + (eachEnchantmentLevel * 0.1)) (ex. efficiency 5 would do -> outputDurability / 1.5, fortune 3 would do -> outputDurability / 1.3)
            }
            // Set damage to output durability
            int newOutputDamage = output.getMaxDamage() - (int)outputDurability;
            if (!this.getSmithingTemplateSlot().hasItem()) {
                output.setDamageValue(Mth.clamp(newOutputDamage, 0, output.getMaxDamage()));
            } else if (!this.getTransferToSlot().getItem().is(Items.BOOK)) {
                cost += totalTransferredEnchantments * 2;
            }
            this.resultSlots.setItem(0, output); // Set the output
            this.levelCost.set(cost); // Set the cost
        } else if (firstSlot.is(ModItemTags.UPGRADEABLE_GOLD) && secondSlot.is(ModItems.SPEEDRUNNER_INGOT) && this.getSmithingTemplateSlot().hasItem()) {
            this.resultSlots.setItem(0, firstSlot.transmuteCopy(this.toGoldenSpeedrunner(firstSlot), 1));
            this.levelCost.set(0);
        }
    }

    /**
     * A successful enchantment transfer.
     */
    private void success(Player player, boolean upgraded) {
        player.playSound(SoundEvents.SMITHING_TABLE_USE, 1.0F, this.player.getRandom().nextFloat() * 0.1F + 0.9F);
        player.giveExperienceLevels(this.levelCost.get());
        if (!upgraded && player instanceof ServerPlayer serverPlayer) {
            ModPredicates.TRIGGERED_BY_ITEMLIKE.trigger(serverPlayer, new ItemStack(ModItems.SPEEDRUNNERS_WORKBENCH));
        }
    }

    /**
     * @return the {@link ItemStack} decremented by 1.
     */
    private ItemStack decrementedStack(ItemStack s) {
        ItemStack decrementedStack = s.copy();
        decrementedStack.shrink(1);
        return decrementedStack;
    }

    /**
     * @return the golden speedrunner converted item.
     */
    private Item toGoldenSpeedrunner(ItemStack s) {
        if (s.is(Items.GOLDEN_SWORD)) {
            return ModItems.GOLDEN_SPEEDRUNNER_SWORD;
        } else if (s.is(Items.GOLDEN_PICKAXE)) {
            return ModItems.GOLDEN_SPEEDRUNNER_PICKAXE;
        } else if (s.is(Items.GOLDEN_SHOVEL)) {
            return ModItems.GOLDEN_SPEEDRUNNER_SHOVEL;
        } else if (s.is(Items.GOLDEN_AXE)) {
            return ModItems.GOLDEN_SPEEDRUNNER_AXE;
        } else if (s.is(Items.GOLDEN_HOE)) {
            return ModItems.GOLDEN_SPEEDRUNNER_HOE;
        } else if (s.is(Items.GOLDEN_HELMET)) {
            return ModItems.GOLDEN_SPEEDRUNNER_HELMET;
        } else if (s.is(Items.GOLDEN_CHESTPLATE)) {
            return ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE;
        } else if (s.is(Items.GOLDEN_LEGGINGS)) {
            return ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS;
        } else if (s.is(Items.GOLDEN_BOOTS)) {
            return ModItems.GOLDEN_SPEEDRUNNER_BOOTS;
        } else if (s.is(Items.GOLDEN_SPEAR)) {
            return ModItems.GOLDEN_SPEEDRUNNER_SPEAR;
        } else if (s.is(ModItems.SPEEDRUNNER_HARNESS)) {
            return ModItems.GOLDEN_SPEEDRUNNER_HARNESS;
        } else if (s.is(Items.GOLDEN_NAUTILUS_ARMOR)) {
            return ModItems.GOLDEN_SPEEDRUNNER_NAUTILUS_ARMOR;
        }
        return null;
    }

    /**
     * @return the input slot.
     */
    public Slot getInputSlot() {
        return this.getSlot(0);
    }

    /**
     * @return the slot with the item that you want to transfer enchantments to.
     */
    public Slot getTransferToSlot() {
        return this.getSlot(1);
    }

    /**
     * @return the smithing template slot.
     */
    public Slot getSmithingTemplateSlot() {
        return this.getSlot(2);
    }

    /**
     * @return the output slot.
     */
    public Slot getOutputSlot() {
        return this.getSlot(3);
    }

    /**
     * @return the current level cost.
     */
    public int getLevelCost() {
        return this.levelCost.get();
    }
}