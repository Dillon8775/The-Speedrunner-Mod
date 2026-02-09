package net.dillon.speedrunnermod.mixin.main.tutorial;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.packet.client.UpdateLastCompletedTutorialStepTranslationsS2CPacket;
import net.dillon.speedrunnermod.server.ServerStorage;
import net.dillon.speedrunnermod.tag.ModBlockTags;
import net.dillon.speedrunnermod.tutorial.TutorialStep;
import net.dillon.speedrunnermod.util.ModUtil;
import net.dillon.speedrunnermod.village.ModVillagers;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.EndPortalBlock;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnderEyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.VillagerData;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

import static net.dillon.speedrunnermod.option.ModOptions.*;
import static net.dillon.speedrunnermod.util.ModUtil.sendWithPrefix;

/**
 * A class which stores {@code all mixins related to Tutorial Mode.}
 */
public final class TutorialModeImpl {

    @Mixin(Item.class)
    public static class ItemMixin {

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
                            isDoomMode() ? "speedrunnermod.tutorial_mode.craft_speedrunner_armor" : "speedrunnermod.tutorial_mode.craft_inferno_eye");
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
                if (bl && isDoomMode()) {
                    ModUtil.completeStepS2C(TutorialStep.CRAFT_SPEEDRUNNER_ARMOR, player, "speedrunnermod.tutorial_mode.craft_speedrunner_shield");
                }

                for (int i = 0; i < player.getInventory().size(); i++) {
                    if (player.getInventory().getStack(i).isOf(ModItems.SPEEDRUNNER_SHIELD)) {
                        ModUtil.completeStepS2C(TutorialStep.CRAFT_SPEEDRUNNER_SHIELD, player, "speedrunnermod.tutorial_mode.craft_speedrunners_eye");
                        break;
                    }
                }

                if (stack.isOf(Items.TOTEM_OF_UNDYING) && isDoomMode()) {
                    ModUtil.completeStepS2C(TutorialStep.OBTAIN_TOTEM_OF_UNDYING, player, "speedrunnermod.tutorial_mode.free_fall_into_void");
                }

                if (stack.isOf(ModItems.SPEEDRUNNERS_TOTEM) && isDoomMode()) {
                    ModUtil.completeStepS2C(TutorialStep.OBTAIN_SPEEDRUNNERS_TOTEM, player,
                            "speedrunnermod.tutorial_mode.speedrunners_totem_description",
                            "speedrunnermod.tutorial_mode.break_doom_block");
                }

                if (stack.isOf(ModItems.INFERNO_EYE)) {
                    ModUtil.completeStepS2C(TutorialStep.CRAFT_INFERNO_EYE, player, "speedrunnermod.tutorial_mode.inferno_eye_description");
                }

                if (stack.isOf(ModItems.PIGLIN_AWAKENER) && isEasyMode()) {
                    ModUtil.completeStepS2C(TutorialStep.CRAFT_PIGLIN_AWAKENER, player, "speedrunnermod.tutorial_mode.use_piglin_awakener");
                }

                if (stack.isOf(ModItems.BLAZE_SPOTTER) && isEasyMode()) {
                    ModUtil.completeStepS2C(TutorialStep.CRAFT_BLAZE_SPOTTER, player, "speedrunnermod.tutorial_mode.use_blaze_spotter");
                }

                if (stack.isOf(ModItems.ENDER_THRUSTER) && isEasyMode()) {
                    ModUtil.completeStepS2C(TutorialStep.OBTAIN_ENDER_THRUSTER, player, "speedrunnermod.tutorial_mode.use_ender_thruster");
                }

                if (stack.isOf(ModItems.SPEEDRUNNERS_EYE)) {
                    ModUtil.completeStepS2C(TutorialStep.CRAFT_SPEEDRUNNERS_EYE, player, "speedrunnermod.tutorial_mode.change_speedrunners_eye_locator");
                }

                if (stack.isOf(Items.ENDER_EYE) && isBalancedMode()) {
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
                            isDoomMode() ? "speedrunnermod.tutorial_mode.find_experience_ore" : "speedrunnermod.tutorial_mode.use_annul_eye");
                }

                if (stack.isOf(ModItems.SPEEDRUNNERS_WORKBENCH)) {
                    ModUtil.completeStepS2C(TutorialStep.CRAFT_SPEEDRUNNERS_WORKBENCH, player,
                            "speedrunnermod.tutorial_mode.speedrunners_workbench_description",
                            "speedrunnermod.tutorial_mode.transfer_enchantments");
                }

                if (stack.isOf(ModItems.ENDER_MATTER) && !isDoomMode()) {
                    ModUtil.completeStepS2C(TutorialStep.OBTAIN_ENDER_MATTER, player,
                            isEasyMode() ? "speedrunnermod.tutorial_mode.craft_dragons_sword" :
                                    "speedrunnermod.tutorial_mode.craft_infini_pearl");
                }

                if (stack.isOf(ModItems.DRAGONS_SWORD) && !isDoomMode()) {
                    ModUtil.completeStepS2C(TutorialStep.OBTAIN_DRAGONS_SWORD, player,
                            "speedrunnermod.tutorial_mode.dragons_sword_description",
                            "speedrunnermod.tutorial_mode.craft_infini_pearl");
                }

                if (stack.isOf(ModItems.INFINI_PEARL) && !isDoomMode()) {
                    ModUtil.completeStepS2C(TutorialStep.OBTAIN_INFINI_PEARL, player, "speedrunnermod.tutorial_mode.completed");
                }
            }
        }

        /**
         * Injects tutorial mode updates when updating inventory.
         */
        @Inject(method = "inventoryTick", at = @At("HEAD"))
        private void modifiedInventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot, CallbackInfo ci) {
            this.tutorialMode(stack, entity, ci);
        }

        /**
         * Injects tutorial mode updates when crafting.
         */
        @Inject(method = "onCraftByPlayer", at = @At("HEAD"))
        private void modifiedOnCraftByPlayer(ItemStack stack, PlayerEntity player, CallbackInfo ci) {
            this.tutorialMode(stack, player, ci);
        }
    }

    @Mixin(EnderEyeItem.class)
    public static class EnderEyeItemMixin {

        /**
         * Completes the use ender eye tutorial step on balanced mode.
         */
        @Inject(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EyeOfEnderEntity;initTargetPos(Lnet/minecraft/util/math/Vec3d;)V"))
        private void completeStep(World world, PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
            if (isBalancedMode()) {
                ModUtil.completeStepS2C(TutorialStep.USE_ENDER_EYE, player, "speedrunnermod.tutorial_mode.enter_end.balanced");
            }
        }
    }

    @Mixin(ServerWorld.class)
    public static class ServerWorldMixin {

        /**
         * Completes tutorial step when entering end.
         */
        @Inject(method = "onDimensionChanged", at = @At("TAIL"))
        private void tutorialModeDimensionChange(Entity entity, CallbackInfo ci) {
            if (entity instanceof ServerPlayerEntity player && ServerStorage.isTutorialModeEnabledForPlayer(player) && player.getEntityWorld().getRegistryKey() == World.END) {
                if (isDoomMode()) {
                    ModUtil.completeStepS2C(TutorialStep.ENTER_END, player,
                            "speedrunnermod.tutorial_mode.entered_end.doom",
                            "speedrunnermod.tutorial_mode.obtain_totem");
                } else {
                    ModUtil.completeStepS2C(TutorialStep.ENTER_END, player,
                            isEasyMode() ? "speedrunnermod.tutorial_mode.entered_end.easy" :
                                    "speedrunnermod.tutorial_mode.entered_end.normal");
                }
            }
        }
    }

    @Mixin(EndPortalBlock.class)
    public static class EndPortalBlockMixin {

        /**
         * Resumes tutorial mode after exiting the end.
         */
        @Inject(method = "onEntityCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;detachForDimensionChange()V"))
        private void exitEndTutorialMode(BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler handler, boolean bl, CallbackInfo ci) {
            if (entity instanceof ServerPlayerEntity player && ServerStorage.isTutorialModeEnabledForPlayer(player.getUuid()) && ServerStorage.hasCompletedStep(player, TutorialStep.KILL_DRAGON)) {
                if (isDoomMode()) {
                    ModUtil.completeStepS2C(TutorialStep.EXIT_END, player, "speedrunnermod.tutorial_mode.exit_end.doom");
                } else {
                    List<String> translations = new ArrayList<>();
                    String s = "speedrunnermod.tutorial_mode.find_experience_ore";
                    translations.add(s);
                    sendWithPrefix(s, player);
                    ServerPlayNetworking.send(player, new UpdateLastCompletedTutorialStepTranslationsS2CPacket(translations));
                }
            }
        }
    }

    @Mixin(ExperienceDroppingBlock.class)
    public static class ExperienceDroppingBlockMixin {

        /**
         * Completes the {@code mine experience ore} tutorial step.
         */
        @Inject(method = "onStacksDropped", at = @At("TAIL"))
        private void completeMinedExperienceOre(BlockState state, ServerWorld world, BlockPos pos, ItemStack tool, boolean dropExperience, CallbackInfo ci) {
            PlayerEntity player = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, true);
            if (state.isIn(ModBlockTags.EXPERIENCE_ORES) && player != null) {
                ModUtil.completeStepS2C(TutorialStep.MINE_EXPERIENCE_ORE, player,
                        "speedrunnermod.tutorial_mode.craft_speedrunners_workbench");
            }
        }
    }

    @Mixin(VillagerEntity.class)
    public static abstract class VillagerEntityMixin {
        @Shadow
        public abstract VillagerData getVillagerData();

        /**
         * Completes the interact with Retired Speedrunner step.
         */
        @Inject(method = "beginTradeWith", at = @At("TAIL"))
        private void speedrunnersWorkbenchBlock(PlayerEntity customer, CallbackInfo ci) {
            if (this.getVillagerData().profession().matchesKey(ModVillagers.RETIRED_SPEEDRUNNER_KEY)) {
                ModUtil.completeStepS2C(TutorialStep.INTERACT_WITH_RETIRED_SPEEDRUNNER, customer,
                        "speedrunnermod.tutorial_mode.retired_speedrunner_description",
                        isDoomMode() ? "speedrunnermod.tutorial_mode.use_annul_eye" :
                                isEasyMode() ? "speedrunnermod.tutorial_mode.craft_ender_thruster" :
                                        "speedrunnermod.tutorial_mode.craft_wither_bone");
            }
        }
    }

    @Mixin(WitherEntity.class)
    public static class WitherEntityMixin extends HostileEntity {

        public WitherEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
            super(entityType, world);
        }

        /**
         * Checks if the wither is dead, to then complete step.
         */
        @Override
        public void onDeath(DamageSource source) {
            super.onDeath(source);
            if (this.getAttacker() instanceof PlayerEntity player && isDoomMode()) {
                ModUtil.completeStepS2C(TutorialStep.KILL_WITHER, player, "speedrunnermod.tutorial_mode.kill_dragon");
            }
        }
    }
}