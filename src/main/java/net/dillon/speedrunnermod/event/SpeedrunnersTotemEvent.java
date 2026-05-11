package net.dillon.speedrunnermod.event;

import net.dillon.speedrunnermod.entity.ModStatuses;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.item.SpeedrunnersTotemItem;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.DeathProtection;

public interface SpeedrunnersTotemEvent {
    void invoke(LivingEntity entity, ItemStack stack, DamageSource source);

    /**
     * Allows the player to use the speedrunner's totem.
     */
    @Author(Authors.YELEEFFF)
    static boolean canUseSpeedrunnersTotem(Player player, DamageSource source) {
        if (player.getMainHandItem().getItem().equals(Items.TOTEM_OF_UNDYING) || player.getOffhandItem().getItem().equals(Items.TOTEM_OF_UNDYING)) {
            return true;
        }

        Inventory inventory = player.getInventory();
        ItemStack totem = null;

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (stack.getItem() instanceof SpeedrunnersTotemItem) {
                totem = stack;
                break;
            }
        }

        if (totem == null) {
            return true;
        }

        SpeedrunnersTotemEvent.EVENT.invoker().invoke(player, totem, source);
        totem.shrink(1);
        return false;
    }

    Event<SpeedrunnersTotemEvent> EVENT = EventFactory.createArrayBacked(SpeedrunnersTotemEvent.class,
            (listeners) -> (LivingEntity entity, ItemStack stack, DamageSource source) -> {
        if (!(stack.getItem() instanceof SpeedrunnersTotemItem)) {
            return;
        }

        for (SpeedrunnersTotemEvent listener : listeners) {
            listener.invoke(entity, stack, source);

            entity.setHealth(1.0F);
            DeathProtection protection = stack.get(DataComponents.DEATH_PROTECTION);
            protection.applyEffects(stack, entity);
            entity.level().broadcastEntityEvent(entity, ModStatuses.ADD_SPEEDRUNNER_TOTEM_PARTICLES);
            if (entity instanceof ServerPlayer player) {
                CriteriaTriggers.USED_TOTEM.trigger(player, new ItemStack(ModItems.SPEEDRUNNERS_TOTEM));
            }
        }
    });
}