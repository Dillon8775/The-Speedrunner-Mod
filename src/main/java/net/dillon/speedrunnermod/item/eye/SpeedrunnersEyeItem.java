package net.dillon.speedrunnermod.item.eye;

import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.helper.ModHelper;
import net.dillon.speedrunnermod.item.SpeedrunnerItem;
import net.dillon.speedrunnermod.option.eum.Mode;
import net.dillon.speedrunnermod.tag.ModStructureTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.tags.StructureTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.SwingAnimation;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

/**
 * An {@code eye of ender} item that locates {@code most overworld structures.}
 */
public class SpeedrunnersEyeItem extends Item implements SpeedrunnerItem {

    public SpeedrunnersEyeItem(Properties settings) {
        super(settings
                .component(ModDataComponentTypes.LOCATING_STRUCTURE, StructureTags.VILLAGE)
                .rarity(Rarity.RARE)
        );
    }

    @Override
    public InteractionResult use(Level world, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        if (world.isClientSide()) {
            return InteractionResult.CONSUME;
        } else if (world.dimension() != Level.OVERWORLD) {
            ModHelper.sendMessageWithActionbarPref(player, Component.translatable("item.speedrunnermod.speedrunners_eye.wrong_dimension"), ChatFormatting.AQUA, ChatFormatting.WHITE);
        } else {
            if (player.isShiftKeyDown()) {
                if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(StructureTags.VILLAGE)) {
                    itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, StructureTags.RUINED_PORTAL);
                    this.playWorldSound(SoundEvents.PORTAL_AMBIENT, world, player);
                } else if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(StructureTags.RUINED_PORTAL)) {
                    itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, StructureTags.SHIPWRECK);
                    this.playWorldSound(SoundEvents.BOAT_PADDLE_LAND, 3.0F, 1.0F, world, player);
                } else if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(StructureTags.SHIPWRECK)) {
                    itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, StructureTags.MINESHAFT);
                    this.playWorldSound(SoundEvents.SPIDER_AMBIENT, world, player);
                } else if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(StructureTags.MINESHAFT)) {
                    itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, ModStructureTags.IGLOOS);
                    this.playWorldSound(SoundEvents.POLAR_BEAR_AMBIENT, world, player);
                } else if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(ModStructureTags.IGLOOS)) {
                    itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, ModStructureTags.DESERT_PYRAMIDS);
                    this.playWorldSound(SoundEvents.SAND_PLACE, 4.0F, 1.0F, world, player);
                } else if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(ModStructureTags.DESERT_PYRAMIDS)) {
                    itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, StructureTags.ON_JUNGLE_PYRAMID_MAPS);
                    this.playWorldSound(SoundEvents.OCELOT_AMBIENT, 5.0F, 1.0F, world, player);
                } else if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(StructureTags.ON_JUNGLE_PYRAMID_MAPS)) {
                    itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, StructureTags.ON_WOODLAND_MANSION_MAPS);
                    this.playWorldSound(SoundEvents.VINDICATOR_AMBIENT, world, player);
                } else if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(StructureTags.ON_WOODLAND_MANSION_MAPS)) {
                    itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, ModStructureTags.PILLAGER_OUTPOSTS);
                    this.playWorldSound(SoundEvents.PILLAGER_AMBIENT, world, player);
                } else if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(ModStructureTags.PILLAGER_OUTPOSTS)) {
                    itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, StructureTags.ON_OCEAN_MONUMENT_MAPS);
                    this.playWorldSound(SoundEvents.ELDER_GUARDIAN_AMBIENT, world, player);
                } else if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(StructureTags.ON_OCEAN_MONUMENT_MAPS)) {
                    itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, ModStructureTags.ANCIENT_CITIES);
                    this.playWorldSound(SoundEvents.WARDEN_HEARTBEAT, world, player);
                } else if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(ModStructureTags.ANCIENT_CITIES)) {
                    itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, StructureTags.ON_BURIED_TRIAL_CHAMBERS_MAPS);
                    this.playWorldSound(SoundEvents.TRIAL_SPAWNER_DETECT_PLAYER, world, player);
                } else if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(StructureTags.ON_BURIED_TRIAL_CHAMBERS_MAPS)) {
                    itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, ModStructureTags.TRAIL_RUINS);
                    this.playWorldSound(SoundEvents.BRUSH_GRAVEL, 3.0F, 1.0F, world, player);
                } else if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(ModStructureTags.TRAIL_RUINS)) {
                    itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, StructureTags.VILLAGE);
                    this.playWorldSound(SoundEvents.VILLAGER_AMBIENT, world, player);
                }

                ModHelper.sendMessageWithActionbarPref(player, Component.translatable("item.speedrunnermod.eye.looking_for", this.structureTexts(itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE))));
            } else {
                ModHelper.sendCalculatingMessage(player);

                ServerLevel serverWorld = (ServerLevel)world;
                BlockPos playerpos = player.blockPosition();
                ModHelper.findStructureAndShoot(world, player, itemStack, itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE));
                BlockPos blockPos = serverWorld.findNearestMapStructure(itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE), playerpos, 100, false);
                int structureDistance = Mth.floor(ModHelper.getDistance(playerpos.getX(), playerpos.getZ(), blockPos.getX(), blockPos.getZ()));
                this.playPitchedLaunchSound(0.4F, world, player);
                ModHelper.sendMessageWithActionbarPref(player, this.locationText(structureDistance, this.structureTexts(itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE))));

                this.decrementIfPossible(player, itemStack);
            }

            player.awardStat(Stats.ITEM_USED.get(this));
            player.swing(hand, SwingAnimation.DEFAULT, true);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.CONSUME;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        this.addStructureTooltip(stack, textConsumer);
    }

    @Override
    public Mode[] disabledModes() {
        return new Mode[]{
        };
    }
}