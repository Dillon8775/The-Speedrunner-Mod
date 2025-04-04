package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.tag.ModStructureTags;
import net.dillon.speedrunnermod.util.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.StructureTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * An {@code eye of ender} item that locates {@code most overworld structures.}
 */
public class SpeedrunnersEyeItem extends Item implements StateOfTheArtItem {
    private BlockPos currentBlockPos;

    public SpeedrunnersEyeItem(Settings settings) {
        super(settings.maxCount(16).component(ModDataComponentTypes.LOCATING_STRUCTURE, StructureTags.VILLAGE).rarity(Rarity.RARE));
    }

    @ChatGPT(Credit.PARTIAL_CREDIT)
    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if (!world.isClient) {
            if (world.getRegistryKey() == World.OVERWORLD) {
                if (player.isSneaking()) {
                    if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(StructureTags.VILLAGE)) {
                        itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, StructureTags.RUINED_PORTAL);
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    } else if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(StructureTags.RUINED_PORTAL)) {
                        itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, StructureTags.SHIPWRECK);
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_BOAT_PADDLE_WATER, SoundCategory.NEUTRAL, 5.0F, 1.0F);
                    } else if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(StructureTags.SHIPWRECK)) {
                        itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, StructureTags.ON_OCEAN_EXPLORER_MAPS);
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ELDER_GUARDIAN_AMBIENT, SoundCategory.HOSTILE, 1.0F, 1.0F);
                    } else if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(StructureTags.ON_OCEAN_EXPLORER_MAPS)) {
                        itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, StructureTags.ON_WOODLAND_EXPLORER_MAPS);
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_VINDICATOR_AMBIENT, SoundCategory.HOSTILE, 1.0F, 1.0F);
                    } else if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(StructureTags.ON_WOODLAND_EXPLORER_MAPS)) {
                        itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, ModStructureTags.DESERT_PYRAMIDS);
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_SAND_PLACE, SoundCategory.BLOCKS, 3.0F, 1.0F);
                        options().tutorialMode.completeStep(TutorialStep.CHANGE_SPEEDRUNNERS_EYE_LOCATOR, player, "speedrunnermod.tutorial_mode.use_speedrunners_eye");
                    } else if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(ModStructureTags.DESERT_PYRAMIDS)) {
                        itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, ModStructureTags.ANCIENT_CITIES);
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_WARDEN_HEARTBEAT, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    } else if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(ModStructureTags.ANCIENT_CITIES)) {
                        itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, StructureTags.ON_TRIAL_CHAMBERS_MAPS);
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_BREEZE_CHARGE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    } else if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(StructureTags.ON_TRIAL_CHAMBERS_MAPS)) {
                        itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, StructureTags.VILLAGE);
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_VILLAGER_AMBIENT, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    }

                    player.sendMessage(Text.translatable("item.speedrunnermod.eye.looking_for", this.structureTexts(itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE))), options().client.itemMessages.isActionbar());
                } else {
                    player.sendMessage(this.calculatingText(), false);
                    ServerWorld serverWorld = (ServerWorld)world;
                    BlockPos playerpos = player.getBlockPos();
                    ModUtil.findStructureAndShoot(world, player, itemStack, itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE));
                    BlockPos blockPos = serverWorld.locateStructure(itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE), playerpos, 100, false);
                    int structureDistance = MathHelper.floor(ModUtil.getDistance(playerpos.getX(), playerpos.getZ(), blockPos.getX(), blockPos.getZ()));
                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
                    player.sendMessage(this.locationText(structureDistance, this.structureTexts(itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE))), options().client.itemMessages.isActionbar());

                    if (options().main.playingMode.easy() || options().main.playingMode.doom()) {
                        options().tutorialMode.completeStep(TutorialStep.USE_SPEEDRUNNERS_EYE, player,
                                "speedrunnermod.tutorial_mode.craft_dragons_pearl", "speedrunnermod.tutorial_mode.dragons_pearl_recipe");
                    } else {
                        options().tutorialMode.completeStep(TutorialStep.USE_SPEEDRUNNERS_EYE, player, "speedrunnermod.tutorial_mode.craft_ender_eye");
                    }

                    if (!player.getAbilities().creativeMode) {
                        itemStack.decrement(1);
                    }
                }

                player.incrementStat(Stats.USED.getOrCreateStat(this));
                player.swingHand(hand, true);
                return ActionResult.SUCCESS;
            } else {
                player.sendMessage(Text.translatable("item.speedrunnermod.speedrunners_eye.wrong_dimension").formatted(ModUtil.toFormatting(Formatting.AQUA, Formatting.WHITE)), options().client.itemMessages.isActionbar());
            }
        }

        return ActionResult.CONSUME;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (options().client.itemTooltips) {
            tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_eye.tooltip"));
        }
        tooltip.add(Text.translatable("item.speedrunnermod.eye.looking_for.tooltip", this.structureTexts(stack.get(ModDataComponentTypes.LOCATING_STRUCTURE))));
    }
}