package net.dillon.speedrunnermod.mixin.main.item;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.dillon.speedrunnermod.tutorial.TutorialStep;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.*;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Shadow
    public abstract ItemStack getDefaultStack();

    /**
     * Main tutorial mode stuff.
     */
    @Unique
    private void tutorialMode(ItemStack stack, Entity entity, CallbackInfo ci) {
        if (entity instanceof PlayerEntity player) {
            if (stack.isOf(ModItems.SPEEDRUNNER_PICKAXE)) {
                ModUtil.completeStepS2C(TutorialStep.CRAFT_SPEEDRUNNER_PICKAXE, player,
                        "speedrunnermod.tutorial_mode.speedrunner_pickaxe_description",
                        "speedrunnermod.tutorial_mode.speedrunner_paddle_description",
                        "speedrunnermod.tutorial_mode.craft_speedrunner_paddle");
            }

            if (stack.isOf(ModItems.SPEEDRUNNER_PADDLE)) {
                ModUtil.completeStepS2C(TutorialStep.CRAFT_SPEEDRUNNER_PADDLE, player,
                        "speedrunnermod.tutorial_mode.craft_speedrunner_boat");
            }

            if (stack.isOf(ModItems.SPEEDRUNNER_BOAT)) {
                ModUtil.completeStepS2C(TutorialStep.CRAFT_SPEEDRUNNER_BOAT, player,
                        "speedrunnermod.tutorial_mode.speedrunner_boat_description",
                        isPlayingModeDoom() ? "speedrunnermod.tutorial_mode.craft_speedrunner_armor" : "speedrunnermod.tutorial_mode.craft_inferno_eye");
            }

            boolean[] speedrunnerArmorItem = new boolean[4];
            for (int i = 0; i < player.getInventory().size(); i++) {
                if (player.getInventory().getStack(i).isOf(ModItems.SPEEDRUNNER_HELMET)) {
                    speedrunnerArmorItem[0] = true;
                }
                if (player.getInventory().getStack(i).isOf(ModItems.SPEEDRUNNER_CHESTPLATE)) {
                    speedrunnerArmorItem[1] = true;
                }
                if (player.getInventory().getStack(i).isOf(ModItems.SPEEDRUNNER_LEGGINGS)) {
                    speedrunnerArmorItem[2] = true;
                }
                if (player.getInventory().getStack(i).isOf(ModItems.SPEEDRUNNER_BOOTS)) {
                    speedrunnerArmorItem[3] = true;
                }
            }
            boolean bl = speedrunnerArmorItem[0] && speedrunnerArmorItem[1] && speedrunnerArmorItem[2] && speedrunnerArmorItem[3];
            if (bl) {
                ModUtil.completeStepS2C(TutorialStep.CRAFT_SPEEDRUNNER_ARMOR, player, "speedrunnermod.tutorial_mode.craft_speedrunner_shield");
            }

            for (int i = 0; i < player.getInventory().size(); i++) {
                if (player.getInventory().getStack(i).isOf(ModItems.SPEEDRUNNER_SHIELD)) {
                    ModUtil.completeStepS2C(TutorialStep.CRAFT_SPEEDRUNNER_SHIELD, player, "speedrunnermod.tutorial_mode.craft_speedrunners_eye");
                    break;
                }
            }

            if (stack.isOf(Items.TOTEM_OF_UNDYING)) {
                ModUtil.completeStepS2C(TutorialStep.OBTAIN_TOTEM_OF_UNDYING, player, "speedrunnermod.tutorial_mode.free_fall_into_void");
            }

            if (stack.isOf(ModItems.SPEEDRUNNERS_TOTEM)) {
                ModUtil.completeStepS2C(TutorialStep.OBTAIN_SPEEDRUNNERS_TOTEM, player,
                        "speedrunnermod.tutorial_mode.speedrunners_totem_description",
                        "speedrunnermod.tutorial_mode.break_doom_block");
            }

            if (stack.isOf(ModItems.INFERNO_EYE)) {
                ModUtil.completeStepS2C(TutorialStep.CRAFT_INFERNO_EYE, player, "speedrunnermod.tutorial_mode.inferno_eye_description");
            }

            if (stack.isOf(ModItems.PIGLIN_AWAKENER)) {
                ModUtil.completeStepS2C(TutorialStep.CRAFT_PIGLIN_AWAKENER, player, "speedrunnermod.tutorial_mode.use_piglin_awakener");
            }

            if (stack.isOf(ModItems.BLAZE_SPOTTER)) {
                ModUtil.completeStepS2C(TutorialStep.CRAFT_BLAZE_SPOTTER, player, "speedrunnermod.tutorial_mode.use_blaze_spotter");
            }

            if (stack.isOf(ModItems.ENDER_THRUSTER)) {
                ModUtil.completeStepS2C(TutorialStep.OBTAIN_ENDER_THRUSTER, player, "speedrunnermod.tutorial_mode.use_ender_thruster");
            }

            if (stack.isOf(ModItems.SPEEDRUNNERS_EYE)) {
                ModUtil.completeStepS2C(TutorialStep.CRAFT_SPEEDRUNNERS_EYE, player, "speedrunnermod.tutorial_mode.change_speedrunners_eye_locator");
            }

            if (stack.isOf(Items.ENDER_EYE) && isPlayingModeBalanced()) {
                ModUtil.completeStepS2C(TutorialStep.CRAFT_ENDER_EYE, player, "speedrunnermod.tutorial_mode.use_ender_eye");
            }

            if (stack.isOf(ModItems.DRAGONS_PEARL)) {
                ModUtil.completeStepS2C(TutorialStep.CRAFT_DRAGONS_PEARL, player,
                        "speedrunnermod.tutorial_mode.dragons_pearl_recipe",
                        "speedrunnermod.tutorial_mode.obtained_dragons_pearl",
                        "speedrunnermod.tutorial_mode.craft_annul_eye");
            }

            if (stack.isOf(ModItems.ANNUL_EYE)) {
                ModUtil.completeStepS2C(TutorialStep.CRAFT_ANNUL_EYE, player,
                        isPlayingModeDoom() ? "speedrunnermod.tutorial_mode.find_experience_ore" : "speedrunnermod.tutorial_mode.use_annul_eye");
            }

            if (stack.isOf(ModItems.SPEEDRUNNERS_WORKBENCH)) {
                ModUtil.completeStepS2C(TutorialStep.CRAFT_SPEEDRUNNERS_WORKBENCH, player,
                        "speedrunnermod.tutorial_mode.speedrunners_workbench_description",
                        "speedrunnermod.tutorial_mode.transfer_enchantments");
            }

            if (stack.isOf(ModItems.WITHER_BONE)) {
                ModUtil.completeStepS2C(TutorialStep.OBTAIN_WITHER_BONE, player, "speedrunnermod.tutorial_mode.craft_wither_sword");
            }

            if (stack.isOf(ModItems.WITHER_SWORD)) {
                ModUtil.completeStepS2C(TutorialStep.OBTAIN_WITHER_SWORD, player,
                        "speedrunnermod.tutorial_mode.wither_sword_description",
                        "speedrunnermod.tutorial_mode.almost_done",
                        "speedrunnermod.tutorial_mode.obtain_ender_matter");
            }

            if (stack.isOf(ModItems.ENDER_MATTER)) {
                ModUtil.completeStepS2C(TutorialStep.OBTAIN_ENDER_MATTER, player,
                        isPlayingModeEasy() ? "speedrunnermod.tutorial_mode.craft_dragons_sword" :
                                "speedrunnermod.tutorial_mode.craft_infini_pearl");
            }

            if (stack.isOf(ModItems.DRAGONS_SWORD)) {
                ModUtil.completeStepS2C(TutorialStep.OBTAIN_DRAGONS_SWORD, player,
                        "speedrunnermod.tutorial_mode.dragons_sword_description",
                        "speedrunnermod.tutorial_mode.craft_infini_pearl");
            }

            if (stack.isOf(ModItems.INFINI_PEARL)) {
                ModUtil.completeStepS2C(TutorialStep.OBTAIN_INFINI_PEARL, player, "speedrunnermod.tutorial_mode.completed");
            }
        }
    }

    /**
     * For tutorial mode and {@code Expert Shepherd} advancement.
     */
    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void modifiedInventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot, CallbackInfo ci) {
        if (entity instanceof ServerPlayerEntity player) {
            int j = 0;
            for (int i = 0; i < player.getInventory().size(); i++) {
                if (player.getInventory().getStack(i).isOf(Items.LIME_WOOL)) {
                    j += player.getInventory().getStack(i).getCount();
                }
                if (j >= 64) {
                    ModCriterions.TRIGGERED_BY_ITEM.trigger(player, Items.LIME_WOOL.getDefaultStack());
                    break;
                }
            }
        }
        this.tutorialMode(stack, entity, ci);
    }

    /**
     * For tutorial mode.
     */
    @Inject(method = "onCraftByPlayer", at = @At("HEAD"))
    private void modifiedOnCraftByPlayer(ItemStack stack, PlayerEntity player, CallbackInfo ci) {
        this.tutorialMode(stack, player, ci);
    }

    /**
     * Adds tooltips to certain items, for item descriptions, craftables, and enchanted books.
     */
    @Inject(method = "appendTooltip", at = @At("HEAD"))
    private void appendTooltips(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type, CallbackInfo ci) {
        if (stack.isIn(ModItemTags.PIGLIN_AWAKENER_CRAFTABLES)) {
            textConsumer.accept(Text.translatable("item.speedrunnermod.piglin_awakener_craftable").formatted(Formatting.GOLD));
        }
        if (stack.isOf(Items.ENCHANTED_BOOK)) {
            ItemEnchantmentsComponent itemEnchantmentsComponent = EnchantmentHelper.getEnchantments(stack);
            for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : itemEnchantmentsComponent.getEnchantmentEntries()) {
                if (entry.getKey().matchesKey(ModEnchantments.DASH)) {
                    textConsumer.accept(Text.translatable("enchantment.speedrunnermod.dash.tooltip").formatted(Formatting.GRAY));
                }
                if (entry.getKey().matchesKey(ModEnchantments.COOLDOWN)) {
                    textConsumer.accept(Text.translatable("enchantment.speedrunnermod.cooldown.tooltip").formatted(Formatting.GRAY));
                }
            }
        }
        if (stack.isOf(Items.TOTEM_OF_UNDYING)) {
            textConsumer.accept(Text.translatable("item.totem_of_undying.tooltip"));
        }
        if (options().main.lavaBoats.getCurrentValue() && (stack.isIn(ModItemTags.FIREPROOF_BOATS) || stack.isIn(ModItemTags.FIREPROOF_CHEST_BOATS))) {
            textConsumer.accept(Text.translatable("item.speedrunnermod.boat.tooltip").formatted(Formatting.GRAY));
        }
        if (stack.isIn(ModItemTags.FASTER_BOATS) || stack.isIn(ModItemTags.FASTER_CHEST_BOATS)) {
            textConsumer.accept(Text.translatable("item.speedrunnermod.boat.tooltip.fast").formatted(Formatting.GRAY));
        }
    }
}