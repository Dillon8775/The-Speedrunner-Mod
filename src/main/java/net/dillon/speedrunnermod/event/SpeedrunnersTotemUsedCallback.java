package net.dillon.speedrunnermod.event;

import net.dillon.speedrunnermod.entity.ModStatuses;
import net.dillon.speedrunnermod.item.SpeedrunnersTotemItem;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

@Author(Authors.YELEEFFF)
@FunctionalInterface
public interface SpeedrunnersTotemUsedCallback {
    void invoke(LivingEntity entity, ItemStack stack, DamageSource source);

    Event<SpeedrunnersTotemUsedCallback> EVENT = EventFactory.createArrayBacked(SpeedrunnersTotemUsedCallback.class,
            (listeners) -> (LivingEntity entity, ItemStack stack, DamageSource source) -> {
        if (!(stack.getItem() instanceof SpeedrunnersTotemItem)) {
            return;
        }

        for (SpeedrunnersTotemUsedCallback listener : listeners) {
            listener.invoke(entity, stack, source);

            entity.setHealth(1.0F);
            entity.level().broadcastEntityEvent(entity, ModStatuses.ADD_SPEEDRUNNER_TOTEM_PARTICLES);
            if (entity instanceof ServerPlayer player) {
                CriteriaTriggers.USED_TOTEM.trigger(player, new ItemStack(Items.TOTEM_OF_UNDYING));
            }
        }
    });
}