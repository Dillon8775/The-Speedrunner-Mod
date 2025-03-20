package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.tag.ModStructureTags;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
import net.dillon.speedrunnermod.util.ModUtil;
import net.dillon.speedrunnermod.util.TutorialMode;
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
public class SpeedrunnersEyeItem extends Item implements StateOfTheArtItem, TutorialMode {
    private BlockPos currentBlockPos = null;

    public SpeedrunnersEyeItem(Settings settings) {
        super(settings.maxCount(16).component(ModDataComponentTypes.BOOLEAN, false).component(ModDataComponentTypes.LOCATING_STRUCTURE, StructureTags.VILLAGE).rarity(Rarity.RARE));
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
                        if (options().main.tutorialMode && options().tutorialMode.obtainedSpeedrunnerPickaxe && options().tutorialMode.obtainedSpeedrunnerBoat && options().tutorialMode.obtainedInfernoEye && options().tutorialMode.usedInfernoEye && options().tutorialMode.obtainedPiglinAwakener && options().tutorialMode.usedPiglinAwakener && options().tutorialMode.obtainedBlazeSpotter && options().tutorialMode.usedBlazeSpotter && options().tutorialMode.obtainedSpeedrunnersEye && !options().tutorialMode.changedSpeedrunnersEyeLocator) {
                            this.send("speedrunnermod.tutorial_mode.changed_speedrunners_eye_locator", player);
                            this.playDing(player);
                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ARROW_HIT_PLAYER, SoundCategory.PLAYERS, 1.0F, 1.0F);
                            options().tutorialMode.changedSpeedrunnersEyeLocator = true;
                            ModOptions.saveConfig();
                        }
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
                    ServerWorld serverWorld = (ServerWorld)world;
                    // if structure blockpos is the same as before, don't warn the player about calculating distance, as it doesn't need to since the location is the same
                    BlockPos playerpos = player.getBlockPos();
                    BlockPos oldBlockPos = currentBlockPos;
                    BlockPos newBlockPos = serverWorld.locateStructure(itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE), playerpos, 100, false);

                    if (oldBlockPos == null || !oldBlockPos.equals(newBlockPos)) {
                        player.sendMessage(this.calculatingText(), false);
                        itemStack.set(ModDataComponentTypes.BOOLEAN, true);
                    }

                    ModUtil.findStructureAndShoot(world, player, itemStack, itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE));
                    int structureDistance = MathHelper.floor(ModUtil.getDistance(playerpos.getX(), playerpos.getZ(), newBlockPos.getX(), newBlockPos.getZ()));
                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
                    player.sendMessage(this.locationText(structureDistance, this.structureTexts(itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE))), options().client.itemMessages.isActionbar());
                    // check and set currentBlockPos to the new (or current) structure location
                    if (!newBlockPos.equals(oldBlockPos)) {
                        itemStack.set(ModDataComponentTypes.BOOLEAN, false);
                        currentBlockPos = newBlockPos;
                    }

                    if (options().main.tutorialMode && options().tutorialMode.obtainedSpeedrunnerPickaxe && options().tutorialMode.obtainedSpeedrunnerBoat && options().tutorialMode.obtainedInfernoEye && options().tutorialMode.usedInfernoEye && options().tutorialMode.obtainedPiglinAwakener && options().tutorialMode.usedPiglinAwakener && options().tutorialMode.obtainedBlazeSpotter && options().tutorialMode.usedBlazeSpotter && options().tutorialMode.obtainedSpeedrunnersEye && options().tutorialMode.changedSpeedrunnersEyeLocator && !options().tutorialMode.usedSpeedrunnersEye) {
                        this.send("speedrunnermod.tutorial_mode.used_speedrunners_eye.easy", player);
                        this.send("speedrunnermod.tutorial_mode.obtain_dragons_pearl.easy", player);
                        options().tutorialMode.usedSpeedrunnersEye = true;
                        ModOptions.saveConfig();
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