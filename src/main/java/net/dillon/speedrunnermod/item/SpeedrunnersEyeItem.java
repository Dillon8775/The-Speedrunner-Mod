package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.tag.ModStructureTags;
import net.dillon.speedrunnermod.util.ItemUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.StructureTags;
import net.minecraft.registry.tag.TagKey;
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
import net.minecraft.world.gen.structure.Structure;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * An {@code eye of ender} item that locates {@code most overworld structures.}
 */
public class SpeedrunnersEyeItem extends Item implements TutorialMode {
    private String structureType = "Village";
    private TagKey<Structure> type = StructureTags.VILLAGE;

    public SpeedrunnersEyeItem(Settings settings) {
        super(settings.rarity(Rarity.RARE));
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if (!world.isClient) {
            if (world.getRegistryKey() == World.OVERWORLD) {
                if (player.isSneaking()) {
                    switch (structureType) {
                        case "Village" -> {
                            structureType = "Ruined Portal";
                            type = StructureTags.RUINED_PORTAL;
                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                        }
                        case "Ruined Portal" -> {
                            structureType = "Shipwreck";
                            type = StructureTags.SHIPWRECK;
                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_BOAT_PADDLE_WATER, SoundCategory.NEUTRAL, 5.0F, 1.0F);
                        }
                        case "Shipwreck" -> {
                            structureType = "Ocean Monument";
                            type = StructureTags.ON_OCEAN_EXPLORER_MAPS;
                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ELDER_GUARDIAN_AMBIENT, SoundCategory.HOSTILE, 1.0F, 1.0F);
                        }
                        case "Ocean Monument" -> {
                            structureType = "Woodland Mansion";
                            type = StructureTags.ON_WOODLAND_EXPLORER_MAPS;
                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_VINDICATOR_AMBIENT, SoundCategory.HOSTILE, 1.0F, 1.0F);
                        }
                        case "Woodland Mansion" -> {
                            structureType = "Desert Pyramid";
                            type = ModStructureTags.DESERT_PYRAMIDS;
                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_SAND_PLACE, SoundCategory.BLOCKS, 3.0F, 1.0F);
                            if (options().main.tutorialMode && options().tutorialMode.obtainedSpeedrunnerPickaxe && options().tutorialMode.obtainedSpeedrunnerBoat && options().tutorialMode.obtainedInfernoEye && options().tutorialMode.usedInfernoEye && options().tutorialMode.obtainedPiglinAwakener && options().tutorialMode.usedPiglinAwakener && options().tutorialMode.obtainedBlazeSpotter && options().tutorialMode.usedBlazeSpotter && options().tutorialMode.obtainedSpeedrunnersEye && !options().tutorialMode.changedSpeedrunnersEyeLocator) {
                                this.send("speedrunnermod.tutorial_mode.changed_speedrunners_eye_locator", player);
                                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ARROW_HIT_PLAYER, SoundCategory.PLAYERS, 1.0F, 1.0F);
                                options().tutorialMode.changedSpeedrunnersEyeLocator = true;
                                ModOptions.saveConfig();
                            }
                        }
                        case "Desert Pyramid" -> {
                            structureType = "Ancient City";
                            type = ModStructureTags.ANCIENT_CITIES;
                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_WARDEN_HEARTBEAT, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                        }
                        case "Ancient City" -> {
                            structureType = "Trial Chamber";
                            type = StructureTags.ON_TRIAL_CHAMBERS_MAPS;
                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_TRIAL_SPAWNER_AMBIENT, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                        }
                        case "Trial Chamber" -> {
                            structureType = "Village";
                            type = StructureTags.VILLAGE;
                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_VILLAGER_AMBIENT, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                        }
                    }

                    player.sendMessage(Text.translatable("item.speedrunnermod.speedrunners_eye.looking_for", structureType).formatted(ItemUtil.toFormatting(Formatting.AQUA, Formatting.WHITE)), options().client.itemMessages.isActionbar());
                } else {
                    ServerWorld serverWorld = (ServerWorld)world;
                    ItemUtil.findStructureAndShoot(world, player, itemStack, type);
                    BlockPos playerpos = player.getBlockPos();
                    BlockPos blockPos = serverWorld.locateStructure(type, playerpos, 100, false);
                    int structureDistance = MathHelper.floor(ItemUtil.getDistance(playerpos.getX(), playerpos.getZ(), blockPos.getX(), blockPos.getZ()));
                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
                    player.sendMessage(Text.translatable("item.speedrunnermod.speedrunners_eye.blocks_away", structureType, structureDistance).formatted(ItemUtil.toFormatting(Formatting.AQUA, Formatting.WHITE)), options().client.itemMessages.isActionbar());

                    if (options().main.tutorialMode && options().tutorialMode.obtainedSpeedrunnerPickaxe && options().tutorialMode.obtainedSpeedrunnerBoat && options().tutorialMode.obtainedInfernoEye && options().tutorialMode.usedInfernoEye && options().tutorialMode.obtainedPiglinAwakener && options().tutorialMode.usedPiglinAwakener && options().tutorialMode.obtainedBlazeSpotter && options().tutorialMode.usedBlazeSpotter && options().tutorialMode.obtainedSpeedrunnersEye && options().tutorialMode.changedSpeedrunnersEyeLocator && !options().tutorialMode.usedSpeedrunnersEye) {
                        this.send("speedrunnermod.tutorial_mode.used_speedrunners_eye", player);
                        this.send("speedrunnermod.tutorial_mode.obtain_dragons_pearl", player);
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
                player.sendMessage(Text.translatable("item.speedrunnermod.speedrunners_eye.wrong_dimension").formatted(ItemUtil.toFormatting(Formatting.AQUA, Formatting.WHITE)), options().client.itemMessages.isActionbar());
            }
        }

        return ActionResult.CONSUME;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (options().client.itemTooltips) {
            tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_eye.tooltip"));
        }
        tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_eye.looking_for.tooltip", structureType).formatted(Formatting.BOLD));
    }
}