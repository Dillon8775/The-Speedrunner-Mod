package net.dillon.speedrunnermod.mixin.main.item;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.item.ModBlockItems;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.item.TutorialMode;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(Item.class)
public class ItemMixin implements TutorialMode {

    /**
     * Main tutorial mode stuff.
     */
    @Unique
    private void tutorialMode(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        if (options().main.tutorialMode) {
            if (options().main.playingMode.easy()) {
                if (entity instanceof PlayerEntity player) {

                    if (stack.isOf(ModItems.SPEEDRUNNER_PICKAXE)) {
                        if (!options().tutorialMode.obtainedSpeedrunnerPickaxe) {
                            this.send("speedrunnermod.tutorial_mode.obtained_speedrunner_pickaxe", player);
                            this.send("speedrunnermod.tutorial_mode.obtain_speedrunner_boat", player);
                            options().tutorialMode.obtainedSpeedrunnerPickaxe = true;
                            ModOptions.saveConfig();
                        }
                    }

                    if (stack.isOf(ModItems.SPEEDRUNNER_BOAT)) {
                        if (options().tutorialMode.obtainedSpeedrunnerPickaxe && !options().tutorialMode.obtainedSpeedrunnerBoat) {
                            this.send("speedrunnermod.tutorial_mode.obtained_speedrunner_boat", player);
                            this.send("speedrunnermod.tutorial_mode.obtain_inferno_eye", player);
                            options().tutorialMode.obtainedSpeedrunnerBoat = true;
                            ModOptions.saveConfig();
                        }
                    }

                    if (stack.isOf(ModItems.INFERNO_EYE)) {
                        if (options().tutorialMode.obtainedSpeedrunnerPickaxe && options().tutorialMode.obtainedSpeedrunnerBoat && !options().tutorialMode.obtainedInfernoEye) {
                            this.send("speedrunnermod.tutorial_mode.obtained_inferno_eye", player);
                            options().tutorialMode.obtainedInfernoEye = true;
                            ModOptions.saveConfig();
                        }
                    }

                    if (stack.isOf(ModItems.PIGLIN_AWAKENER)) {
                        if (options().tutorialMode.obtainedSpeedrunnerPickaxe && options().tutorialMode.obtainedSpeedrunnerBoat && options().tutorialMode.obtainedInfernoEye && options().tutorialMode.usedInfernoEye && !options().tutorialMode.obtainedPiglinAwakener) {
                            this.send("speedrunnermod.tutorial_mode.obtained_piglin_awakener", player);
                            options().tutorialMode.obtainedPiglinAwakener = true;
                            ModOptions.saveConfig();
                            boolean hasGold = false;
                            for (int i = 0; i < player.getInventory().size(); i++) {
                                ItemStack inventorySlot = player.getInventory().getStack(i);
                                if (inventorySlot.isOf(Items.GOLD_INGOT)) {
                                    hasGold = true;
                                }
                            }
                            if (!hasGold) {
                                ItemStack gold = new ItemStack(Items.GOLD_INGOT, 64);
                                for (int i = 0; i < player.getInventory().size(); i++) {
                                    ItemStack blankSlot = player.getInventory().getStack(i);
                                    if (blankSlot.isEmpty()) {
                                        player.getInventory().setStack(i, gold);
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    if (stack.isOf(ModItems.BLAZE_SPOTTER)) {
                        if (options().tutorialMode.obtainedSpeedrunnerPickaxe && options().tutorialMode.obtainedSpeedrunnerBoat && options().tutorialMode.obtainedInfernoEye && options().tutorialMode.usedInfernoEye && options().tutorialMode.obtainedPiglinAwakener && options().tutorialMode.usedPiglinAwakener && !options().tutorialMode.obtainedBlazeSpotter) {
                            this.send("speedrunnermod.tutorial_mode.obtained_blaze_spotter", player);
                            options().tutorialMode.obtainedBlazeSpotter = true;
                            ModOptions.saveConfig();
                        }
                    }

                    if (stack.isOf(ModItems.SPEEDRUNNERS_EYE)) {
                        if (options().tutorialMode.obtainedSpeedrunnerPickaxe && options().tutorialMode.obtainedSpeedrunnerBoat && options().tutorialMode.obtainedInfernoEye && options().tutorialMode.usedInfernoEye && options().tutorialMode.obtainedPiglinAwakener && options().tutorialMode.usedPiglinAwakener && options().tutorialMode.obtainedBlazeSpotter && options().tutorialMode.usedBlazeSpotter && !options().tutorialMode.obtainedSpeedrunnersEye) {
                            this.send("speedrunnermod.tutorial_mode.obtained_speedrunners_eye", player);
                            options().tutorialMode.obtainedSpeedrunnersEye = true;
                            ModOptions.saveConfig();
                        }
                    }

                    if (stack.isOf(ModItems.DRAGONS_PEARL)) {
                        if (options().tutorialMode.obtainedSpeedrunnerPickaxe && options().tutorialMode.obtainedSpeedrunnerBoat && options().tutorialMode.obtainedInfernoEye && options().tutorialMode.usedInfernoEye && options().tutorialMode.obtainedPiglinAwakener && options().tutorialMode.usedPiglinAwakener && options().tutorialMode.obtainedBlazeSpotter && options().tutorialMode.usedBlazeSpotter && options().tutorialMode.obtainedSpeedrunnersEye && options().tutorialMode.changedSpeedrunnersEyeLocator && options().tutorialMode.usedSpeedrunnersEye && !options().tutorialMode.obtainedDragonsPearl) {
                            this.send("speedrunnermod.tutorial_mode.obtained_dragons_pearl", player);
                            this.send("speedrunnermod.tutorial_mode.obtain_annul_eye", player);
                            options().tutorialMode.obtainedDragonsPearl = true;
                            ModOptions.saveConfig();
                        }
                    }

                    if (stack.isOf(ModItems.ANNUL_EYE)) {
                        if (options().tutorialMode.obtainedSpeedrunnerPickaxe && options().tutorialMode.obtainedSpeedrunnerBoat && options().tutorialMode.obtainedInfernoEye && options().tutorialMode.usedInfernoEye && options().tutorialMode.obtainedPiglinAwakener && options().tutorialMode.usedPiglinAwakener && options().tutorialMode.obtainedBlazeSpotter && options().tutorialMode.usedBlazeSpotter && options().tutorialMode.obtainedSpeedrunnersEye && options().tutorialMode.changedSpeedrunnersEyeLocator && options().tutorialMode.usedSpeedrunnersEye && options().tutorialMode.obtainedDragonsPearl && !options().tutorialMode.obtainedAnnulEye) {
                            this.send("speedrunnermod.tutorial_mode.obtained_annul_eye", player);
                            options().tutorialMode.obtainedAnnulEye = true;
                            ModOptions.saveConfig();
                        }
                    }

                    if (stack.isOf(ModBlockItems.SPEEDRUNNERS_WORKBENCH)) {
                        if (options().tutorialMode.killedDragon && options().tutorialMode.brokenExperienceOre && !options().tutorialMode.obtainedSpeedrunnersWorkbench) {
                            this.send("speedrunnermod.tutorial_mode.obtained_speedrunners_workbench", player);
                            options().tutorialMode.obtainedSpeedrunnersWorkbench = true;
                            ModOptions.saveConfig();
                        }
                    }
                }
            }
        }
    }

    /**
     * For tutorial mode.
     */
    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void inventoryTickTutorialMode(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        this.tutorialMode(stack, world, entity, slot, selected, ci);
    }

    /**
     * For tutorial mode.
     */
    @Inject(method = "onCraftByPlayer", at = @At("HEAD"))
    private void onCraftByPlayerTutorialMode(ItemStack stack, World world, PlayerEntity player, CallbackInfo ci) {
        this.tutorialMode(stack, world, player, 0, false, ci);
    }

    /**
     * Adds tooltips to items that can be used to craft the {@code piglin awakener.}
     */
    @Inject(method = "appendTooltip", at = @At("TAIL"))
    private void appendTooltips(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type, CallbackInfo ci) {
        if (options().client.itemTooltips) {
            if (stack.isIn(ModItemTags.PIGLIN_AWAKENER_CRAFTABLES)) {
                tooltip.add(Text.translatable("item.speedrunnermod.piglin_awakener_craftable").formatted(Formatting.GOLD));
            }
            if (stack.isOf(ModBlockItems.SPEEDRUNNERS_WORKBENCH)) {
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_workbench.tooltip.line1").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_workbench.tooltip.line2").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_workbench.tooltip.line3").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_workbench.tooltip.line4").formatted(Formatting.GRAY));
            }
            if (stack.isOf(Items.ENCHANTED_BOOK)) {
                ItemEnchantmentsComponent itemEnchantmentsComponent = EnchantmentHelper.getEnchantments(stack);
                for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : itemEnchantmentsComponent.getEnchantmentEntries()) {
                    if (entry.getKey().matchesKey(ModEnchantments.DASH)) {
                        tooltip.add(Text.translatable("enchantment.speedrunnermod.dash.tooltip").formatted(Formatting.GRAY));
                    }
                    if (entry.getKey().matchesKey(ModEnchantments.COOLDOWN)) {
                        tooltip.add(Text.translatable("enchantment.speedrunnermod.cooldown.tooltip").formatted(Formatting.GRAY));
                    }
                }
            }
            if (stack.isOf(Items.TOTEM_OF_UNDYING)) {
                tooltip.add(Text.translatable("item.totem_of_undying.tooltip"));
            }
            if (stack.isIn(ModItemTags.FIREPROOF_BOATS) || stack.isIn(ModItemTags.FIREPROOF_CHEST_BOATS)) {
                tooltip.add(Text.translatable("item.speedrunnermod.boat.tooltip").formatted(Formatting.GRAY));
            }
            if (stack.isIn(ModItemTags.FASTER_BOATS) || stack.isIn(ModItemTags.FASTER_CHEST_BOATS)) {
                tooltip.add(Text.translatable("item.speedrunnermod.boat.tooltip.fast").formatted(Formatting.GRAY));
            }
        }
        if (options().client.textureTooltips) {
            if (stack.isIn(ModItemTags.Block.TEXTURE_CREATOR_MANNYQUESO)) {
                tooltip.add(Text.translatable("speedrunnermod.texture_creator.mannyqueso"));
            }
        }
    }
}