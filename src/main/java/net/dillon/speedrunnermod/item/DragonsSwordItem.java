package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;
import static net.dillon.speedrunnermod.option.ModOptions.isEasyMode;

/**
 * A weapon that {@code one-shots} the {@code ender dragon.}
 */
public class DragonsSwordItem extends Item implements SpeedrunnerItem {

    public DragonsSwordItem(Item.Properties settings) {
        super(settings.sword(ModToolMaterials.DRAGONS_SWORD, 6, -2.4F).rarity(Rarity.EPIC));
    }

    /**
     * Kills the ender dragon instantly.
     */
    @Override
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target instanceof EnderDragon dragon && attacker instanceof Player player) {
            if (isEasyMode() && dragon.level() instanceof ServerLevel serverWorld) {
                dragon.hurtServer(serverWorld, dragon.damageSources().playerAttack(player), 1000.0F); // Enough damage to kill the dragon
            } else {
                if (isDoomMode()) {
                    attacker.hurt(attacker.damageSources().mobAttack(attacker), ModUtil.randomFloatInclusive(2.0F, 3.0F));
                    attacker.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, ModUtil.secondsAsTicks(5), 0, false, true, true));
                    attacker.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, ModUtil.secondsAsTicks(2), 0, false, true, true));
                    this.playWorldSound(SoundEvents.SHIELD_BLOCK.value(), player.level(), player);
                    player.sendSystemMessage(Component.translatable("item.speedrunnermod.dragons_sword.failed").withStyle(ChatFormatting.LIGHT_PURPLE));
                }
            }
            for (int i = 0; i < 25; i++) {
                stack.hurtAndBreak(ModToolMaterials.DRAGONS_SWORD.durability(), attacker, EquipmentSlot.MAINHAND);
            }
        }
        super.hurtEnemy(stack, target, attacker);
    }

    /**
     * The Dragon's sword always has an enchantment glint.
     */
    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.dragons_sword.tooltip")
                .withStyle(this.isDisabled() ? ChatFormatting.STRIKETHROUGH : ChatFormatting.WHITE).withStyle(ChatFormatting.GRAY));
        if (this.isDisabled()) {
            SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.dragons_sword.doom_mode").withStyle(ChatFormatting.RED));
        }
        this.addStateOfTheArtItemTooltip(textConsumer);
    }

    @Override
    public ModOptions.Mode[] disabledModes() {
        return new ModOptions.Mode[]{
                ModOptions.Mode.DOOM
        };
    }
}