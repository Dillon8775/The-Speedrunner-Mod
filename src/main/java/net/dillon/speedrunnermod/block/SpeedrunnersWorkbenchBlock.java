package net.dillon.speedrunnermod.block;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
import net.dillon.speedrunnermod.util.ItemUtil;
import net.dillon.speedrunnermod.util.MathUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.SmithingTableBlock;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * A block that allows transferring of enchantments to other items.
 * <p>Also used as the retired speedrunner's job site block, acts like a smithing table.</p>
 */
public class SpeedrunnersWorkbenchBlock extends SmithingTableBlock {

    public SpeedrunnersWorkbenchBlock(Settings settings) {
        super(settings);
    }

    /**
     * The method for transferring compatible enchantments from the players main hand, to the players offhand.
     * <p>See additional comments inside of this method for more documentation.</p>
     */
    @Override @ChatGPT(Credit.PARTIAL_CREDIT)
    public ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        boolean holdingEnchantedBookItem = player.getMainHandStack().isOf(Items.ENCHANTED_BOOK) || player.getOffHandStack().isOf(Items.ENCHANTED_BOOK);
        if ((options().main.playingMode.easy() || options().main.playingMode.doom()) && !world.isClient && !holdingEnchantedBookItem && player.getMainHandStack().hasEnchantments() && !player.getOffHandStack().isEmpty()) {
            ItemStack mainHandStack = player.getMainHandStack(); // Get the players main hand stack
            ItemStack offHandStack = player.getOffHandStack(); // Get the players offhand stack
            ItemEnchantmentsComponent mainHandEnchantments = EnchantmentHelper.getEnchantments(mainHandStack); // Get the enchantments on the players main hand item, using an item enchantments component
            ItemEnchantmentsComponent offHandEnchantments = EnchantmentHelper.getEnchantments(offHandStack); // Get the enchantments on the players offhand item, using an item enchantments component
            ItemEnchantmentsComponent.Builder mainHandBuilder = new ItemEnchantmentsComponent.Builder(mainHandEnchantments); // An item enchantments component builder for the players main hand enchantments
            ItemEnchantmentsComponent.Builder offHandBuilder = new ItemEnchantmentsComponent.Builder(offHandEnchantments); // An item enchantments component builder for the players offhand enchantments

            int totalTransferred = 0; // The total amount of enchantments successfully transferred
            int cost = 0; // The amount of levels that it costs to transfer all compatible enchantments
            boolean successWithEnchantments = false; // Determines if transferring enchantments were successful, if the players offhand item already had enchantments to begin with
            boolean successWithNoEnchantments = false; // Determines if transferring enchantments were successful, if the players offhand item had no enchantments
            boolean fail = false; // Determines of transferring enchantments failed and/or there were no enchantments to transfer
            boolean wasUpgraded = false; // Determines if a higher enchantment level was transferred to the offhand (ex. offhand item had unbreaking 2, unbreaking 3 was transferred)
            boolean someIncFailed = false; // Determines if only some enchantments failed to transfer
            boolean incompatibleEnchantmentsFailed = false; // Determines if all enchantments failed to transfer due to incompatibility
            boolean offhandHasEnchantments = EnchantmentHelper.hasEnchantments(offHandStack); // Determines if the players offhand stack has any enchantments

            // Run through all main hand enchantments
            for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : mainHandEnchantments.getEnchantmentEntries()) {
                RegistryEntry registryEntry = entry.getKey();
                Enchantment enchantment = (Enchantment)registryEntry.value();

                if (!offhandHasEnchantments) { // If the players offhand item has no enchantments, "successWithNoEnchantments" returns true, and enchantments are transferred
                    totalTransferred++;
                    cost = this.initializeCost(player, mainHandBuilder, entry, totalTransferred);
                    if (totalTransferred != 0 && player.experienceLevel >= cost && enchantment.isAcceptableItem(offHandStack)) {
                        successWithNoEnchantments = true;
                        this.transferEnchantments(mainHandStack, offHandStack, mainHandBuilder, offHandBuilder, entry, registryEntry);
                    } else {
                        if (!enchantment.isAcceptableItem(offHandStack)) {
                            incompatibleEnchantmentsFailed = true;
                        } else {
                            fail = true;
                        }
                    }
                } else { // Otherwise, start running through all offhand enchantments
                    boolean allIsCompatible = true;
                    for (RegistryEntry<Enchantment> registryEntry2 : offHandBuilder.getEnchantments()) {

                        // Compare main hand and offhand enchantments and determine if they are compatible
                        for (RegistryEntry<Enchantment> existingEnchantment : offHandBuilder.getEnchantments()) {
                            if (!Enchantment.canBeCombined(existingEnchantment, registryEntry) && !registryEntry2.equals(registryEntry)) {
                                allIsCompatible = false;
                                break;
                            }
                        }

                        // Determines if an enchantment on the offhand can be upgraded to a higher level
                        boolean canUpgrade = registryEntry2.equals(registryEntry) && offHandBuilder.getLevel(registryEntry2) < mainHandBuilder.getLevel(registryEntry);

                        // If all enchantments are compatible with each other and can be combined,
                        // "successWithEnchantments" returns true, and enchantments are transferred
                        if (allIsCompatible && Enchantment.canBeCombined(registryEntry, registryEntry2) && enchantment.isAcceptableItem(offHandStack) || canUpgrade) {

                            totalTransferred++;
                            cost = this.initializeCost(player, mainHandBuilder, entry, totalTransferred);

                            if (totalTransferred != 0 && player.experienceLevel >= cost) {
                                successWithEnchantments = true;
                                if (canUpgrade) {
                                    wasUpgraded = true;
                                }
                                this.transferEnchantments(mainHandStack, offHandStack, mainHandBuilder, offHandBuilder, entry, registryEntry);
                            } else {
                                fail = true;
                            }
                        } else { // Otherwise, check for the enchantments that are compatible with each other
                            // If total transferred is greater than 0 (or some enchantments are compatible),
                            // "someIncFailed" returns true, and the compatible enchantments are transferred
                            if (!Enchantment.canBeCombined(registryEntry, registryEntry2) || !enchantment.isAcceptableItem(offHandStack)) {
                                if (totalTransferred > 0) {
                                    someIncFailed = true;
                                } else { // Otherwise, no enchantments are transferred, and "incompatibleEnchantmentsFailed" returns true
                                    incompatibleEnchantmentsFailed = true;
                                }
                            }
                        }
                    }
                }
            }

            // Successful enchantment transfer when the offhand item had enchantments before
            if (successWithEnchantments) {
                if (wasUpgraded) {
                    player.sendMessage(Text.translatable("speedrunnermod.enchantment_levels_upgraded"), false);
                }
                this.success(world, pos, player, hand, mainHandStack, cost);
            }

            // Successful enchantment transfer when the offhand item had no enchantments before
            if (successWithNoEnchantments) {
                this.success(world, pos, player, hand, mainHandStack, cost);
            }

            // Some incompatible enchantments were not transferred
            if (someIncFailed) {
                player.sendMessage(Text.translatable("speedrunnermod.some_incompatible_enchantments_failed"), false);
            }

            // No enchantments were transferred, because they were all incompatible
            if (incompatibleEnchantmentsFailed) {
                player.sendMessage(Text.translatable("speedrunnermod.incompatible_enchantments_failed").formatted(ItemUtil.toFormatting(Formatting.AQUA, Formatting.WHITE)), options().client.itemMessages.isActionbar());
            }

            // Tells the player how many levels are needed to transfer enchantments
            if (fail) {
                this.fail(player, cost);
            }

            return ActionResult.SUCCESS;
        } else {
            return super.onUseWithItem(stack, state, world, pos, player, hand,  hit);
        }
    }

    /**
     * Applies the transferred enchantments to the item.
     * <p>See comments inside method for more documentation.</p>
     */
    @ChatGPT(Credit.PARTIAL_CREDIT)
    private void transferEnchantments(ItemStack mainHandStack, ItemStack offHandStack, ItemEnchantmentsComponent.Builder mainHandBuilder, ItemEnchantmentsComponent.Builder offHandBuilder, Object2IntMap.Entry<RegistryEntry<Enchantment>> entry, RegistryEntry registryEntry) {
        int mainHandLevel = mainHandBuilder.getLevel(entry.getKey());
        int offHandLevel = offHandBuilder.getLevel(entry.getKey());

        // Check if the offhand already has the enchantment
        if (offHandLevel > 0) {
            // If offhand has a lower level, upgrade it
            if (offHandLevel < mainHandLevel) {
                EnchantmentHelper.apply(offHandStack, builder -> builder.add(entry.getKey(), mainHandLevel));
            }
            // No further action needed if the levels are equal or offhand has a higher level
        } else {
            // If the offhand does not have the enchantment, transfer it
            EnchantmentHelper.apply(offHandStack, builder -> builder.add(entry.getKey(), mainHandLevel));
        }

        // Remove the enchantment from the main hand item if it was transferred/upgraded to the offhand
        EnchantmentHelper.apply(mainHandStack, builder -> builder.remove(enchantmentRegistryEntry -> enchantmentRegistryEntry.equals(registryEntry)));
    }

    /**
     * Corrects the {@code cost} variable to equal the total amount of enchantments transferred multiplied by itself.
     */
    private int initializeCost(PlayerEntity player, ItemEnchantmentsComponent.Builder enchantmentLevel, Object2IntMap.Entry<RegistryEntry<Enchantment>> entry, int totalTransferred) {
        int cost = MathUtil.multiplyEnchantments(enchantmentLevel, entry, totalTransferred);

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
    private void success(World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack mainHandStack, int cost) {
        player.sendMessage(Text.translatable("speedrunnermod.transferred_enchantments").formatted(ItemUtil.toFormatting(Formatting.AQUA, Formatting.WHITE)), options().client.itemMessages.isActionbar());
        world.playSound(null, pos, SoundEvents.BLOCK_SMITHING_TABLE_USE, SoundCategory.BLOCKS, 1.0F, world.random.nextFloat() * 0.1F + 0.9F);
        player.addExperienceLevels(-cost);
        player.setStackInHand(hand, mainHandStack);
    }

    /**
     * Failed to transfer any enchantments.
     */
    private void fail(PlayerEntity player, int cost) {
        player.sendMessage(Text.translatable("speedrunnermod.no_enchantments_transferred").formatted(ItemUtil.toFormatting(Formatting.AQUA, Formatting.WHITE)), options().client.itemMessages.isActionbar());
        if (!(player.experienceLevel >= cost)) {
            player.sendMessage(Text.translatable("speedrunnermod.levels_needed", cost), false);
        }
    }
}